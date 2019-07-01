package io.javabrains.springbootstarter.topic.service;

import java.util.List;

import io.javabrains.springbootstarter.topic.entity.Topic;

public interface ITopicService {
	
	List<Topic> getAllTopics();
    Topic getTopic(String topicId);
    void addTopic(Topic Topic);
    void updateTopic(Topic Topic);
    void deleteTopic(String topicId);
}
