package io.javabrains.springbootstarter.topic.dao;

import java.util.List;

import io.javabrains.springbootstarter.topic.entity.Topic;

public interface ITopicDao {
    List<Topic> getAllTopics();
    Topic getTopicById(String topicId);
    void addTopic(Topic Topic);
    void updateTopic(Topic Topic);
    void deleteTopic(String topicId);
    boolean topicExists(String name, String description);
}
