package com.dao;
import java.sql.*;

import com.connection.DBConnection;
import com.model.*;


public class RanksManagementDAO {
    
	DBConnection connect = new DBConnection();

	
    public void createRank(Ranks rank) {
        try (Connection connection = connect.getConnection()) {
            String query = "INSERT INTO Ranks (player_id, t20_rank, odi_rank, test_rank) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, rank.getPlayerId());
            statement.setInt(2, rank.getT20Rank());
            statement.setInt(3, rank.getOdiRank());
            statement.setInt(4, rank.getTestRank());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void readRank(int playerId) {
        try (Connection connection = connect.getConnection()) {
            String query = "SELECT * FROM Ranks WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("T20 Ranking: " + resultSet.getInt("t20_rank"));
                System.out.println("ODI Ranking: " + resultSet.getInt("odi_rank"));
                System.out.println("Test Ranking: " + resultSet.getInt("test_rank"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    
    public void deleteRank(int playerId) {
        try (Connection connection = connect.getConnection()) {
            String query = "DELETE FROM Ranks WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void updateRank(Ranks rank) {
        try (Connection connection = connect.getConnection()) {
            String query = "UPDATE Ranks SET t20_rank = ?, odi_rank = ?, test_rank = ? WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, rank.getT20Rank());
            statement.setInt(2, rank.getOdiRank());
            statement.setInt(3, rank.getTestRank());
            statement.setInt(4, rank.getPlayerId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
    public void updateT20Ranking(int playerId, int newRank) {
        try (Connection connection = connect.getConnection()) {
            // Check if the new rank (21) is available
            String checkQuery = "SELECT COUNT(*) FROM Ranks WHERE t20_rank = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, newRank);
                if (checkStmt.executeQuery().next()) {
                    // Update the rank for player 24 to 21
                    String updateQuery = "UPDATE Ranks SET t20_rank = ? WHERE player_id = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, newRank);
                        updateStmt.setInt(2, playerId);
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Update ranks for players after 24 by incrementing their t20_rank
                    String incrementQuery = "UPDATE Ranks SET t20_rank = t20_rank + 1 WHERE player_id > ?";
                    try (PreparedStatement incrementStmt = connection.prepareStatement(incrementQuery)) {
                        incrementStmt.setInt(1, playerId);
                        incrementStmt.executeUpdate();
                    }
                    // Allocate player_id 24 to rank 21
                    String allocateQuery = "UPDATE Ranks SET t20_rank = ? WHERE player_id = ?";
                    try (PreparedStatement allocateStmt = connection.prepareStatement(allocateQuery)) {
                        allocateStmt.setInt(1, newRank);
                        allocateStmt.setInt(2, playerId);
                        allocateStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    
    
    public void checkT20Rank(int id, int rank) {
        String query = "UPDATE Ranks SET t20_rank = ? WHERE player_id = ?";
        try(Connection connection = connect.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, rank);
            statement.setInt(2, id);
            statement.executeUpdate();
            
            // Check if any one holds the given rank in t20_rank column
            String checkQuery = "SELECT * FROM Ranks WHERE t20_rank = ?";
            try(PreparedStatement checkStatement = connection.prepareStatement(checkQuery)){
                checkStatement.setInt(1, rank);
                ResultSet resultSet = checkStatement.executeQuery();
                if(resultSet.next()) {
                    // If the rank is held by someone else, update the rank of the player who held it
                    String updateQuery = "UPDATE Ranks SET t20_rank = ? WHERE player_id = ?";
                    try(PreparedStatement updateStatement = connection.prepareStatement(updateQuery)){
                        updateStatement.setInt(1, rank + 1);
                        updateStatement.setInt(2, resultSet.getInt("player_id"));
                        updateStatement.executeUpdate();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
   
    
 
    public void checkODIRank(int id, int rank) {
    	String query = "UPDATE Ranks SET odi_rank = ? WHERE player_id = ?";
    	try(Connection connection = connect.getConnection();
    		PreparedStatement statement = connection.prepareStatement(query)){
    		//fill the code if any one holds given rank in odi_rank column check which is available and update to that
            statement.setInt(1, rank);
            statement.setInt(2, id);
            statement.executeUpdate();
            
            // Check if any one holds the given rank in t20_rank column
            String checkQuery = "SELECT * FROM Ranks WHERE odi_rank = ?";
            try(PreparedStatement checkStatement = connection.prepareStatement(checkQuery)){
                checkStatement.setInt(1, rank);
                ResultSet resultSet = checkStatement.executeQuery();
                if(resultSet.next()) {
                    // If the rank is held by someone else, update the rank of the player who held it
                    String updateQuery = "UPDATE Ranks SET odi_rank = ? WHERE player_id = ?";
                    try(PreparedStatement updateStatement = connection.prepareStatement(updateQuery)){
                        updateStatement.setInt(1, rank + 1);
                        updateStatement.setInt(2, resultSet.getInt("player_id"));
                        updateStatement.executeUpdate();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
    		
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void checkTestRank(int id, int rank) {
    	String query = "UPDATE Ranks SET test_rank = ? WHERE player_id = ?";
    	try(Connection connection = connect.getConnection();
    		PreparedStatement statement = connection.prepareStatement(query)){
    		//fill the code if any one holds given rank in test_rank column check which is available and update to that
            statement.setInt(1, rank);
            statement.setInt(2, id);
            statement.executeUpdate();
            
            // Check if any one holds the given rank in t20_rank column
            String checkQuery = "SELECT * FROM Ranks WHERE test_rank = ?";
            try(PreparedStatement checkStatement = connection.prepareStatement(checkQuery)){
                checkStatement.setInt(1, rank);
                ResultSet resultSet = checkStatement.executeQuery();
                if(resultSet.next()) {
                    // If the rank is held by someone else, update the rank of the player who held it
                    String updateQuery = "UPDATE Ranks SET test_rank = ? WHERE player_id = ?";
                    try(PreparedStatement updateStatement = connection.prepareStatement(updateQuery)){
                        updateStatement.setInt(1, rank + 1);
                        updateStatement.setInt(2, resultSet.getInt("player_id"));
                        updateStatement.executeUpdate();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
    		
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    

    

}
