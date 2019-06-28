package io.javabrains.springbootstarter.topic;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TopicService topicService; // creates a singleton instance by injecting

	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		
	log.info("User has entered the /topics GET endpoint");
	
	List<Topic> topics =  topicService.getAllTopics();
	
	if ( topics.size() == 0 ) log.warn("Empty topics list returned from /topics GET ");
	
	return topics;
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		log.info("User has entered the topics/id endpoint");
		
		Topic topic = null;
		try {
			topic = topicService.getTopic(id);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn("No Element found when user searched " + id);
		}
		
		return topic;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic) {
		log.info("User has entered the /topics POST endpoint");
		
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@PathVariable String id, @RequestBody Topic topic) {
		log.info("User has entered the /topics/id PUT endpoint");
		
		topicService.updateTopic(id, topic);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@PathVariable String id) {
		log.info("User has entered the /topics/id DELETE endpoint");

		topicService.deleteTopic(id);
	}
	
}
