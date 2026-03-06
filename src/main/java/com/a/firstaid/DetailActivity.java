package com.a.firstaid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TOPIC = "extra_topic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MaterialToolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        String topicId = getIntent().getStringExtra(EXTRA_TOPIC);
        Topic topic = TopicData.getTopicById(this, topicId);

        if (topic != null) {
            populateDetails(topic);
        }
    }

    private void populateDetails(Topic topic) {
        TextView categoryView = findViewById(R.id.text_detail_category);
        TextView titleView = findViewById(R.id.text_detail_title);
        TextView descView = findViewById(R.id.text_detail_desc);
        LinearLayout stepsContainer = findViewById(R.id.steps_container);

        categoryView.setText(topic.getCategory());
        titleView.setText(topic.getTitle());
        descView.setText(topic.getDescription());

        stepsContainer.removeAllViews();
        int stepNumber = 1;
        for (String stepContent : topic.getSteps()) {
            addStepView(stepsContainer, stepNumber++, stepContent);
        }
    }

    private void addStepView(LinearLayout container, int number, String content) {
        View stepView = LayoutInflater.from(this).inflate(R.layout.item_step, container, false);
        
        TextView numberView = stepView.findViewById(R.id.text_step_number);
        TextView contentView = stepView.findViewById(R.id.text_step_content);

        numberView.setText(String.valueOf(number));
        contentView.setText(content);

        container.addView(stepView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}