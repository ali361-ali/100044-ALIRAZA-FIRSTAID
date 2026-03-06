package com.a.firstaid;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopicData {
    public static List<Topic> getAllTopics(Context context) {
        List<Topic> topics = new ArrayList<>();
        
        topics.add(new Topic("CPR", 
                context.getString(R.string.title_cpr), 
                context.getString(R.string.desc_cpr), 
                context.getString(R.string.cat_life_threatening),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        context.getString(R.string.step_cpr_1),
                        context.getString(R.string.step_cpr_2),
                        context.getString(R.string.step_cpr_3),
                        context.getString(R.string.step_cpr_4),
                        context.getString(R.string.step_cpr_5),
                        context.getString(R.string.step_cpr_6)
                )));

        topics.add(new Topic("Choking", 
                context.getString(R.string.title_choking), 
                context.getString(R.string.desc_choking), 
                context.getString(R.string.cat_life_threatening),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        context.getString(R.string.step_choking_1),
                        context.getString(R.string.step_choking_2),
                        context.getString(R.string.step_choking_3),
                        context.getString(R.string.step_choking_4)
                )));

        topics.add(new Topic("Bleeding", 
                context.getString(R.string.title_bleeding), 
                context.getString(R.string.desc_bleeding), 
                context.getString(R.string.cat_injuries),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        context.getString(R.string.step_bleeding_1),
                        context.getString(R.string.step_bleeding_2),
                        context.getString(R.string.step_bleeding_3)
                )));

        topics.add(new Topic("Burns", 
                context.getString(R.string.title_burns), 
                context.getString(R.string.desc_burns), 
                context.getString(R.string.cat_injuries),
                R.drawable.ic_first_aid,
                Arrays.asList(
                        context.getString(R.string.step_burns_1),
                        context.getString(R.string.step_burns_2),
                        context.getString(R.string.step_burns_3)
                )));
                
        return topics;
    }

    public static Topic getTopicById(Context context, String id) {
        for (Topic topic : getAllTopics(context)) {
            if (topic.getId().equals(id)) return topic;
        }
        return null;
    }
}