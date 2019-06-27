package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
// Business Service
public class TopicService {
	private List<Topic> topics = new ArrayList<Topic>(Arrays.asList(
			new Topic("spring","Spring Framwork","Spring Framework Description"),
			new Topic("java","Core Java","Core Java Description"),
			new Topic("javascript","JavaScript","JavaScript Description")
			));
	
	
	public List<Topic> getAllTopics() {
		return topics;
	}
	
	public Topic getTopic(String id) {
		return topics.stream().filter(e -> e.getId().equals(id)).findFirst().get();
	}
	
	public void addTopic(Topic topic) {
		topics.add(topic);
	}
	
	public void deleteTopic(String id) {
		topics.removeIf( e -> e.getId().equals(id) );
	}
	
	public void updateTopic(String id, Topic topic) {
		for (int i = 0; i < topics.size(); i++ ) {
			if (topics.get(i).getId().equals(id) ) { 
				topics.set(i, topic);
				return;
			}
		}
	}
	
}
