package io.javabrains.springbootstarter.topic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.javabrains.springbootstarter.topic.entity.Topic;

public class TopicRowMapper implements RowMapper<Topic>{
	@Override
	public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
		Topic topic = new Topic();
		topic.setId(rs.getString("id"));
		topic.setDescription(rs.getString("description"));
		topic.setName(rs.getString("name"));
		
		return topic;
	}
}
