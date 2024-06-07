/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Repository {

    public ResultSet executeQuery(String sqlQuery) {
        ResultSet resultSet = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
        }
        return resultSet;
    }
    
}
