package io.javabrains.springbootstarter.topic.repository;

import org.springframework.data.repository.CrudRepository;

import io.javabrains.springbootstarter.topic.entity.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {
	// get me all the topics
		
	// get topic given id
	
	// update topic given topic
	
	// delete topic given id
	
	
}
