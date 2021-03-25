public enum Suit
{
    CLUBS("clubs"),
    SPADES("spades"),
    DIAMONDS("diamonds"),
    HEARTS("hearts");
	
	private String suit;
	
	private Suit(String suit)
	{
		this.suit = suit;
	}
	
	public String getSuit()
	{
		return suit;
	}
	

}