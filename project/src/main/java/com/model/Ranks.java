package com.model;

public class Ranks {
    private int playerId;
    private int t20Rank;
    private int odiRank;
    private int testRank;

    public Ranks(int playerId, int t20Rank, int odiRank, int testRank) {
        this.playerId = playerId;
        this.t20Rank = t20Rank;
        this.odiRank = odiRank;
        this.testRank = testRank;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getT20Rank() {
        return t20Rank;
    }

    public void setT20Rank(int t20Rank) {
        this.t20Rank = t20Rank;
    }

    public int getOdiRank() {
        return odiRank;
    }

    public void setOdiRank(int odiRank) {
        this.odiRank = odiRank;
    }

    public int getTestRank() {
        return testRank;
    }

   public void setTestRank(int testRank) {
       this.testRank = testRank;
   }

@Override
public String toString() {
	return "Ranks [playerId=" + playerId + ", t20Rank=" + t20Rank + ", odiRank=" + odiRank + ", testRank=" + testRank
			+ "]";
}
   
   
}
