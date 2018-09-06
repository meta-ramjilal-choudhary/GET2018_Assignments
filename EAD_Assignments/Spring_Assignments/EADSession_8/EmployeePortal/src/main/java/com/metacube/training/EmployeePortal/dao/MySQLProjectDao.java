package com.metacube.training.EmployeePortal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.metacube.training.EmployeePortal.mapper.ProjectMapper;
import com.metacube.training.EmployeePortal.model.Project;

public class MySQLProjectDao {
	public Project getProjectById(int id) {
		Connection connection = MySQLConnection.getDatabaseConnection("employee_portal");
		String queryTogetProject = "SELECT * FROM Project where project_id=?";
		Project project = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(queryTogetProject);
			preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			ProjectMapper projectMapper = new ProjectMapper();
			project = projectMapper.mapRow(resultSet,resultSet.getRow());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}
}
