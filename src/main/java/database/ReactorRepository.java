/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import react.DatabaseReactor;

/**
 *
 * @author user
 */
public class ReactorRepository {

  public List<DatabaseReactor> getAllReactorsByRegion() {
        String sql = "SELECT r.*, rg.region_name AS aggregation, t.type_name AS type " +
                     "FROM Reactor r " +
                     "JOIN Country c ON r.country_id = c.id " +
                     "JOIN Region rg ON c.region_id = rg.id " +
                     "JOIN Type t ON r.type_id = t.id";
        return getReactors(sql);
    }

    public List<DatabaseReactor> getAllReactorsByCountry() {
        String sql = "SELECT r.*, c.country_name AS aggregation, t.type_name AS type " +
                     "FROM Reactor r " +
                     "JOIN Country c ON r.country_id = c.id " +
                     "JOIN Type t ON r.type_id = t.id";
        return getReactors(sql);
    }

    public List<DatabaseReactor> getAllReactorsByOperator() {
        String sql = "SELECT r.*, o.operator_name AS aggregation, t.type_name AS type " +
                     "FROM Reactor r " +
                     "JOIN Operator o ON r.operator_id = o.id " +
                     "JOIN Type t ON r.type_id = t.id";
        return getReactors(sql);
    }

    private List<DatabaseReactor> getReactors(String sql) {
        List<DatabaseReactor> reactors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DatabaseReactor reactor = new DatabaseReactor();
                reactor.setId(rs.getLong("id"));
                reactor.setType(rs.getString("type"));
                reactor.setThermalCapacity(rs.getInt("thermal_capacity"));
                reactor.setReactorName(rs.getString("reactor_name"));
                reactor.setConnectionDate(rs.getDate("connection_date"));
                reactor.setShutdownDate(rs.getDate("shutdown_date"));
                reactor.setAggregation(rs.getString("aggregation"));
                reactors.add(reactor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reactors;
    }
}
