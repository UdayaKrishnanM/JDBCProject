package com.model;

public class Player {

	private int playerId;
    private String playerName;
    private int playerAge;
    private String playerDepartment;
    
    public Player() {
    	
    }
    
    public Player(int playerId, String playerName, int playerAge, String playerDepartment) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerDepartment = playerDepartment;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

	public String getPlayerDepartment() {
		return playerDepartment;
	}

	public void setPlayerDepartment(String playerDepartment) {
		this.playerDepartment = playerDepartment;
	}
    
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", playerAge=" + playerAge
				+ ", playerDepartment=" + playerDepartment + "]";
	}
    
}
