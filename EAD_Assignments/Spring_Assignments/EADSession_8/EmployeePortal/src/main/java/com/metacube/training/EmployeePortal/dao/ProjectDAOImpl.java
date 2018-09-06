package com.metacube.training.EmployeePortal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.metacube.training.EmployeePortal.mapper.ProjectMapper;
import com.metacube.training.EmployeePortal.model.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ProjectDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String SQL_FIND_PROJECT = "select * from project where id = ?";
	private final String SQL_DELETE_PROJECT = "delete from project where id = ?";
	private final String SQL_UPDATE_PROJECT = "update project set name = ?, description = ?, start_date  = ?, end_date  = ? where id = ?";
	private final String SQL_GET_ALL = "select * from project";
	private final String SQL_INSERT_PROJECT = "insert into project(name, description, start_date, end_date) values(?,?,?,?)";

	public Project getProjectById(int id) {
		return new MySQLProjectDao().getProjectById(id);
	}

	public List<Project> getAllProjects() {
		return jdbcTemplate.query(SQL_GET_ALL, new ProjectMapper());
	}
	
	public boolean deleteProject(Project project) {
		return jdbcTemplate.update(SQL_DELETE_PROJECT, project.getProjectId()) > 0;
	}

	public boolean updateProject(Project project) {
		return jdbcTemplate.update(SQL_UPDATE_PROJECT,project.getDescription(), project.getStartDate(),
				project.getEndDate()) > 0;
	}

	public boolean createProject(Project project) {
		return jdbcTemplate.update(SQL_INSERT_PROJECT, project.getDescription(), project.getStartDate(),
				project.getEndDate()) > 0;
	}

}