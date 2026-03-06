package com.a.firstaid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<Topic> topics;
    private final OnTopicClickListener listener;

    public interface OnTopicClickListener {
        void onTopicClick(Topic topic);
    }

    public TopicAdapter(List<Topic> topics, OnTopicClickListener listener) {
        this.topics = topics;
        this.listener = listener;
    }

    public void updateList(List<Topic> newList) {
        this.topics = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = topics.get(position);
        holder.bind(topic, listener);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    static class TopicViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView desc;
        private final TextView category;
        private final ImageView icon;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            desc = itemView.findViewById(R.id.text_desc);
            category = itemView.findViewById(R.id.text_category);
            icon = itemView.findViewById(R.id.image_icon);
        }

        public void bind(final Topic topic, final OnTopicClickListener listener) {
            title.setText(topic.getTitle());
            desc.setText(topic.getDescription());
            category.setText(topic.getCategory());
            
            // Set category color based on type
            if (topic.getCategory().contains("Life")) {
                category.setTextColor(itemView.getContext().getColor(R.color.primary));
            } else {
                category.setTextColor(itemView.getContext().getColor(android.R.color.darker_gray));
            }

            if (topic.getIconRes() != 0) {
                icon.setImageResource(topic.getIconRes());
            }
            itemView.setOnClickListener(v -> listener.onTopicClick(topic));
        }
    }
}