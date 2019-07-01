package io.javabrains.springbootstarter.topic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.topic.dao.ITopicDao;
import io.javabrains.springbootstarter.topic.entity.Topic;

@Service
// Business Service
public class TopicService implements ITopicService {
	
//	@Autowired
//	private TopicRepository topicRepository;
	
	@Autowired
	private ITopicDao topicDao;
	
	
	private List<Topic> topics = new ArrayList<Topic>(Arrays.asList(
			new Topic("spring","Spring Framwork","Spring Framework Description"),
			new Topic("java","Core Java","Core Java Description"),
			new Topic("javascript","JavaScript","JavaScript Description")
			));
	
	
	public List<Topic> getAllTopics() {		
		return topicDao.getAllTopics();
	}
	
	public Topic getTopic(String id) {
		return topicDao.getTopicById(id);
	}
	
	public void addTopic(Topic topic) {
		topicDao.addTopic(topic);
	}
	
	public void deleteTopic(String id) {
//		topics.removeIf( e -> e.getId().equals(id) );
		topicDao.deleteTopic(id);
	}
	
	public void updateTopic(Topic topic) {
//		for (int i = 0; i < topics.size(); i++ ) {
//			if (topics.get(i).getId().equals(id) ) { 
//				topics.set(i, topic);
//				return;
//			}
//		}
		
		
		topicDao.updateTopic(topic);
	}
	
}
