package com.dao;
import java.sql.*;

import com.connection.*;
import com.exception.*;
import com.model.*;

public class PlayerManagementDAO {
    
	DBConnection connect = new DBConnection();
	RanksManagementDAO ranksManagementDAO = new RanksManagementDAO();
	
	
    public void createPlayer(Player player) {
        try {
        	Connection connection = connect.getConnection();
        	String query = "INSERT INTO Player (player_id, player_name, player_age, player_department) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, player.getPlayerId());
            statement.setString(2, player.getPlayerName());
            statement.setInt(3, player.getPlayerAge());
            statement.setString(4, player.getPlayerDepartment());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void readAllPlayers() {
        try(Connection connection = connect.getConnection();) {
        	String query = "SELECT * FROM Player";
        	Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Player ID: " + resultSet.getInt("player_id"));
                System.out.println("Name: " + resultSet.getString("player_name"));
                System.out.println("Age: " + resultSet.getInt("player_age"));
                System.out.println("Department: " + resultSet.getString("player_department"));
                ranksManagementDAO.readRank(resultSet.getInt("player_id"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public boolean readPlayer(int playerId) {
        try (Connection connection = connect.getConnection()) {
            String query = "SELECT * FROM Player WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    

    public void deletePlayer(int playerId) {
    	try (Connection connection = connect.getConnection()) {
    		String query = "DELETE FROM Player WHERE player_id = ?";
    		PreparedStatement statement = connection.prepareStatement(query);
    		statement.setInt(1, playerId);
    		
    		statement.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    
    
    public void updatePlayer(Player player) {
        try (Connection connection = connect.getConnection()) {
            String query = "UPDATE Player SET player_name = ?, player_age = ? WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getPlayerAge());
            statement.setInt(3, player.getPlayerId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateAge(int id, int age) {
        String query = "UPDATE Player SET player_age = ? WHERE player_id = ?";
        try(Connection connection = connect.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, age);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(int id, String role) {
        String query = "UPDATE Player SET player_department = ? WHERE player_id = ?";
        try(Connection connection = connect.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, role);
            statement.setInt(2, id);
            statement.executeUpdate();
            

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void topPlayers(String format, String role, int limit) throws UserException {
        try (Connection connection = connect.getConnection()) {
            String query = "SELECT p.player_id, p.player_name, r." + format + "_rank " +
                           "FROM Player p " +
                           "JOIN Ranks r ON p.player_id = r.player_id " +
                           "WHERE p.player_department = ? AND r." + format + "_rank IS NOT NULL " +
                           "ORDER BY r." + format + "_rank ASC LIMIT ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, role);
            statement.setInt(2, limit);

            ResultSet resultSet = statement.executeQuery();

//            System.out.println("Top " + limit + " " + format + " " + role );
            while (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
	            System.out.println(playerName);
            }
        } catch (SQLException e) {
            throw new UserException("Input error");
        }
    }    

}
