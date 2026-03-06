package com.a.firstaid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements TopicAdapter.OnTopicClickListener {

    private List<Topic> allTopics;
    private TopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        initData();
        setupRecyclerView();
        setupSearch();
        setupEmergencyFab();
    }

    private void initData() {
        allTopics = new ArrayList<>();
        
        allTopics.add(new Topic("CPR", 
                getString(R.string.title_cpr), 
                getString(R.string.desc_cpr), 
                getString(R.string.cat_life_threatening),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        getString(R.string.step_cpr_1),
                        getString(R.string.step_cpr_2),
                        getString(R.string.step_cpr_3),
                        getString(R.string.step_cpr_4),
                        getString(R.string.step_cpr_5),
                        getString(R.string.step_cpr_6)
                )));

        allTopics.add(new Topic("Choking", 
                getString(R.string.title_choking), 
                getString(R.string.desc_choking), 
                getString(R.string.cat_life_threatening),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        getString(R.string.step_choking_1),
                        getString(R.string.step_choking_2),
                        getString(R.string.step_choking_3),
                        getString(R.string.step_choking_4)
                )));

        allTopics.add(new Topic("Bleeding", 
                getString(R.string.title_bleeding), 
                getString(R.string.desc_bleeding), 
                getString(R.string.cat_injuries),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        getString(R.string.step_bleeding_1),
                        getString(R.string.step_bleeding_2),
                        getString(R.string.step_bleeding_3)
                )));

        allTopics.add(new Topic("Burns", 
                getString(R.string.title_burns), 
                getString(R.string.desc_burns), 
                getString(R.string.cat_injuries),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        getString(R.string.step_burns_1),
                        getString(R.string.step_burns_2),
                        getString(R.string.step_burns_3)
                )));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_topics);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TopicAdapter(new ArrayList<>(allTopics), this);
        recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        TextInputEditText searchEditText = findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTopics(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterTopics(String query) {
        List<Topic> filteredList = allTopics.stream()
                .filter(topic -> topic.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                                 topic.getCategory().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateList(filteredList);
    }

    private void setupEmergencyFab() {
        ExtendedFloatingActionButton fab = findViewById(R.id.fab_emergency);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:911"));
            startActivity(intent);
        });
    }

    @Override
    public void onTopicClick(Topic topic) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC, topic.getId());
        startActivity(intent);
    }
}