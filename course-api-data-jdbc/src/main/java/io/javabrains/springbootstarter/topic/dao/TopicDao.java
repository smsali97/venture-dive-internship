package io.javabrains.springbootstarter.topic.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.javabrains.springbootstarter.topic.entity.Topic;

@Transactional
@Repository
public class TopicDao implements ITopicDao {
	
	
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public TopicDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<Topic> getAllTopics() {
		String sql = "SELECT id, name, description from topics";
		RowMapper<Topic> rowMapper = new TopicRowMapper();
		return this.jdbcTemplate.query(sql,rowMapper);
	}
	@Override
	public Topic getTopicById(String topicId) {
		String sql = "SELECT id, name, description FROM topics WHERE id = ?";
		RowMapper<Topic> rowMapper = new BeanPropertyRowMapper<Topic>(Topic.class);
		Topic topic = jdbcTemplate.queryForObject(sql, rowMapper, topicId);
		return topic;
	}
	@Override
	public void addTopic(Topic topic) {
		//Add article
		String sql = "INSERT INTO topics (id, name, description) values (?, ?, ?)";
		jdbcTemplate.update(sql, topic.getId(), topic.getName(), topic.getDescription());
		
		//Fetch article id
		sql = "SELECT id FROM topics WHERE name = ? and description=?";
		String articleId = jdbcTemplate.queryForObject(sql, String.class, topic.getName(), topic.getDescription());
		
		//Set article id 
		topic.setId(articleId);
	}
	@Override
	public void deleteTopic(String topicId) {
		String sql = "DELETE FROM topics WHERE id=?";
		jdbcTemplate.update(sql, topicId);
	}
	@Override
	public boolean topicExists(String name, String description) {
		String sql = "SELECT count(*) FROM topics WHERE name = ? and description=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, name, description);
		if(count == 0) {
    		        return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void updateTopic(Topic topic) {
		String sql = "UPDATE topics SET name=?, description=? WHERE id=?";
		jdbcTemplate.update(sql, topic.getName(), topic.getDescription(), topic.getId());
	}
}
