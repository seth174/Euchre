
public class Team 
{
	private String teamName;
	private int roundScore;
	private int gameScore;
	
	public Team(String name)
	{
		teamName = name;
		roundScore = 0;
		gameScore = 0;
	}
	
	public int getRoundScore()
	{
		return roundScore;
	}
	
	public int getGameScore()
	{
		return gameScore;
	}
	
	public void resetRoundScore()
	{
		roundScore = 0;
	}
	
	public void wonHand()
	{
		roundScore += 1;
	}
	
	public void wonRound()
	{
		gameScore += 1;
	}
	
	public void wonRoundEuchred()
	{
		gameScore += 2;
	}

	public void wonRoundSolo()
	{
		gameScore += 4;
	}
}
