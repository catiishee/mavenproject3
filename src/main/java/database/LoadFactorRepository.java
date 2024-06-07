/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import react.LoadFactor;

/**
 *
 * @author user
 */
public class LoadFactorRepository {

    public List<LoadFactor> getLoadFactors() {
        String sql = "SELECT * FROM LoadFactor";
        List<LoadFactor> loadFactors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LoadFactor loadFactor = new LoadFactor();
                loadFactor.setId(rs.getLong("id"));
                loadFactor.setReactorId(rs.getLong("reactor_id"));
                loadFactor.setLoadFactor(rs.getFloat("load_factor"));
                loadFactor.setYear(rs.getInt("year"));
                loadFactors.add(loadFactor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loadFactors;
    }
}
