public abstract class Player 
{
    private String name;
    protected Hand hand;
    private Player teamMate;
    private Player left;
    private int roundScore;
    private Team team;

    public Player(String name)
    {
        this.name = name;
        hand = new Hand();
    }
    
    public void setTeam(Team team)
    {
    	this.team = team;
    }
    
    public Team getTeam()
    {
    	return team;
    }

    public void setTeamMate(Player teamMate)
    {
        this.teamMate = teamMate;
    }
    
    public void removeCard(Card card)
    {
    	hand.removeCard(card);
    }
    
    public boolean getSuit(Suit suit, Suit trump)
    {
    	return hand.getSuit(suit, trump);
    }
    
    
    public void setLeft(Player p)
    {
    	left = p;
    }

    public Player getLeft()
    {
    	return left;
    }
    
    public Player getTeamMate()
    {
        return teamMate;
    }

    public String getName()
    {
        return name;
    }

    public void setCard(int index, Card card)
    {
        hand.setCard(index, card);
    }
    
    public void setCard(Card card)
    {
    	hand.setCard(card);
    }

    public Card getCard(int index)
    {
       return hand.getCard(index);
    }

    public Card getCard(Card card)
    {
        return hand.getCard(card);
    }
    
    public Card getCard(int value, Suit trump)
    {
    	return hand.getCard(value, trump);
    }
    
    public void setCards()
    {
    	hand.setCards();
    }
    
    public int getRemainingCards()
    {
    	return hand.getRemainingCards();
    }
    
    public void sortHand(Suit trump)
    {
    	hand.sort(trump);
    }
    
    public void sortHand()
    {
    	hand.sort();
    }
    
    public Card swapCard(Card card, int index)
    {
    	return hand.swapCard(card, index);
    }
    
    public String toString()
    {
    	StringBuilder string = new StringBuilder();
    	string.append(getName() + "\n");
    	for(int i = 0; i < 5; i++)
    	{
    		if(hand.getCard(i) == null)
    			break;
    		string.append(i + " - "+ hand.getCard(i) + "\n");
    	}
    	return string.toString();
    }
    
    public int getRoundScore()
    {
    	return roundScore;
    }
    
    public void incrementRoundScore()
    {
    	roundScore += 1;
    }
    
    public void resetRoundScore()
    {
    	roundScore = 0;
    }

    public abstract Card playCard(Card[] pile, int playedCards, Suit trump, Card firstLed);

    public abstract Suit chooseTrump(Suit trump);
    
    public abstract Card chooseCard(Card card);
    
    public abstract Card swapCard(Card card);
    
    


}
