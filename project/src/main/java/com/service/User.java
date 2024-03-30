package com.service;

import java.util.*;

import com.dao.*;
import com.model.*;
import com.validate.Validate;
import com.exception.*;

public class User {
	
	private Scanner in;

	public User() throws UserException{
		in = new Scanner(System.in);
    	boolean flag = true;
    	while(flag) {
	    	System.out.println();
	    	System.out.println("Click the below options");
	    	System.out.println("-----------------------");
	    	System.out.println("1. Add a player's details");		
	    	System.out.println("2. Update player's details");
	    	System.out.println("3. Delete player's details");
	    	System.out.println("4. List all players details");
	    	System.out.println("5. List Test Squad");
	    	System.out.println("6. List ODI Squad");
	    	System.out.println("7. List T20 Squad");
	    	System.out.println("8. Exit");
	    	System.out.println();
	    	System.out.println("******************************");

	    	int a = in.nextInt();
	    	PlayerManagementDAO playerManagementDAO = new PlayerManagementDAO();
	    	RanksManagementDAO ranksManagementDAO = new RanksManagementDAO();
	    	
	    	
	        switch(a) {
	        	//create Player
	        	//name, id, ranking, testRanking, ODIRanking, T20Ranking, location, age, dept	
	        case 1 :  
	            System.out.println("Enter the player id, name, age, department, T20Ranking, ODIRanking, TestRanking");
	            String playerDetails = in.next();
	            String[] splitString = playerDetails.split(":");
	            if(Validate.validateAge(Integer.parseInt(splitString[2])) && Validate.validateName(splitString[1])) {
	                Player player = new Player(Integer.parseInt(splitString[0]), splitString[1], Integer.parseInt(splitString[2]), splitString[3]);
	                Ranks rank = new Ranks(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[4]), Integer.parseInt(splitString[5]), Integer.parseInt(splitString[6]));
	                playerManagementDAO.createPlayer(player);
	                ranksManagementDAO.createRank(rank);
	                System.out.println("Created successfully of " + player.toString());
	            }
	            System.out.println();
	            System.out.println("***************************");
	            break;

	        
	        		
	        	//update Player
	        	case 2:	
			        System.out.println("Enter the player id to be updated");
			        int player_id = in.nextInt();
			        if (!playerManagementDAO.readPlayer(player_id)) {
			            System.out.println("Player_id not found");
			            System.out.println();
			            System.out.println("***************************");
			            break;
			        } else {
			            System.out.println("1. Update Age");
			            System.out.println("2. Update Department");
			            int choice = in.nextInt();
			            
			            switch(choice) {
				            case 1: 
				            	System.out.println("Enter the age to be updated");
					            playerManagementDAO.updateAge(player_id, in.nextInt());
					            System.out.println("Age updated successfully");
					            break;		
				            
				            case 2: 
				            	System.out.println("Enter the role to be updated");
					            playerManagementDAO.updateRole(player_id, in.next());
					            System.out.println("Role updated successfully");
					            break;
					        
					        default:
				        		break;

			            }
			            System.out.println("***************************");
			            break;
			        }

	        		
	        	//delete Player
	        	case 3:
			        System.out.println("Enter the player_id");
			        int id = in.nextInt();
			        //below code rank must come first because foreign key wont be deleted
			        if (!playerManagementDAO.readPlayer(id)) {
			            System.out.println("Player_id not found");
			            System.out.println();
			            System.out.println("***************************");
			        } else {
				        ranksManagementDAO.deleteRank(id);
				        playerManagementDAO.deletePlayer(id);
				        System.out.println("Deleted successfully");
				        System.out.println("***************************");
			        }
			        break;
	        		
	        	//read Player
	        	case 4: 
	        		playerManagementDAO.readAllPlayers();
	        		System.out.println("***************************");
	        		break;
	        		
	        		
	        	//print Test Players
	        	case 5:
	                System.out.println("****** TEST SQUAD ******");
	                System.out.println();
//	        		Top 5 batsman
	        		playerManagementDAO.topPlayers("Test", "Batsmen", 5);
//	        		Top 1 Wicket Keeper	       		
	        		playerManagementDAO.topPlayers("Test", "Wicketkeeper", 1);
//	        		Top 2 All Rounders
	        		playerManagementDAO.topPlayers("Test", "Allrounder", 2);
//	        		Top 3 Bowlers
	        		playerManagementDAO.topPlayers("Test", "Bowler", 3);
	                System.out.println();
	                System.out.println("**********************");
	        		break;
			        
	        	//print ODI Players
	        	case 6:
	                System.out.println("****** ODI SQUAD ******");
	                System.out.println();
//	        		Top 5 batsman
	        		playerManagementDAO.topPlayers("ODI", "Batsmen", 5);
//	        		Top 1 Wicket Keeper	       		
	        		playerManagementDAO.topPlayers("ODI", "Wicketkeeper", 1);
//	        		Top 2 All Rounders
	        		playerManagementDAO.topPlayers("ODI", "Allrounder", 2);
//	        		Top 3 Bowlers
	        		playerManagementDAO.topPlayers("ODI", "Bowler", 3);
	                System.out.println();
	                System.out.println("**********************");

	        		break;
	        		
	        	//print T20 Players
	        	case 7:
	                System.out.println("****** T20 SQUAD ******");
	                System.out.println();
//	        		Top 5 batsman
	        		playerManagementDAO.topPlayers("T20", "Batsmen", 5);
//	        		Top 1 Wicket Keeper	       		
	        		playerManagementDAO.topPlayers("T20", "Wicketkeeper", 1);
//	        		Top 2 All Rounders
	        		playerManagementDAO.topPlayers("T20", "Allrounder", 2);
//	        		Top 3 Bowlers
	        		playerManagementDAO.topPlayers("T20", "Bowler", 3);
	                System.out.println();
	                System.out.println("**********************");
	        		break;
	        		
	        	//exit statement
	        	default:
	        		flag = false;
	        }
    	}
	}
	
}



























//update Rank code
//case 3: 
//System.out.println("Enter the T20 Rank to be updated");
//ranksManagementDAO.updateT20Rank(player_id, in.nextInt());
//break;
//
//case 4: 
//System.out.println("Enter the ODI Rank to be updated");
//ranksManagementDAO.updateODIRanking(player_id, in.nextInt());
//break;
//
//case 5: 
//System.out.println("Enter the Test Rank to be updated");
//ranksManagementDAO.updateTestRanking(player_id, in.nextInt());
//break;
