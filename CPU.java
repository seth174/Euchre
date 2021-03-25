import java.lang.Math;
public class CPU extends Player
{
	private static final int ACE = 14;
	private static final int KING = 13;
	private static final int QUEEN = 12;
	private static final int JACK = 11;
	private static final int TEN = 10;
	private static final int NINE = 9;
	public CPU(String name)
	{
		super(name);
	}
	
	public Card playCard(Card[] pile, int playedCards, Suit trump, Card firstLed)
	{
		System.out.println(super.toString());
		if(playedCards == 0)
		{
			if(super.getCard(ACE, trump) != null)
			{
				return super.getCard(ACE, trump);
			}
			else if(super.getCard(KING, trump) != null)
			{
				return super.getCard(KING, trump);
			}
			else if(super.getCard(QUEEN, trump) != null)
			{
				return super.getCard(QUEEN, trump);
			}
			else
			{
				return super.getCard(0);
			}
		}
		else if(playedCards == 1)
		{
			int opponentLargest = pile[0].getValue(trump); 
			if(hand.getSuit(firstLed.getSuit(), trump))
			{
				Card temp = hand.getLargerCard(opponentLargest, trump, firstLed);
				if(temp == null)
				{
					return hand.getSmallestCard(trump, firstLed);
				}
				else
				{
					return temp;
				}
			}
			else if(hand.getSuit(trump, trump))
			{
				Card temp = hand.getLargerCard(opponentLargest, trump);
				return temp;
			}
			else
			{
				return hand.getSmallestCard(trump);
			}
		}
		else if(playedCards == 2)
		{
			int opponentScore = pile[1].getValue(trump);
			int teamMateScore = pile[0].getValue(trump);
			if(hand.getSuit(firstLed.getSuit(), trump))
			{
				if(teamMateScore > opponentScore)
				{
					return hand.getSmallestCard(trump, firstLed);
				}
				else 
				{
					Card temp = hand.getLargerCard(opponentScore, trump, firstLed);
					if(temp == null)
					{
						return hand.getSmallestCard(trump, firstLed);
					}
					else
					{
						return temp;
					}
				}
			}
			else if(teamMateScore > opponentScore)
			{
				return hand.getSmallestCard(trump);
			}
			else
			{
				Card temp = hand.getLargerCard(opponentScore, trump);
				if(temp == null)
				{
					return hand.getSmallestCard(trump);
				}
				else
				{
					return temp;
				}
			}
		}
		else
		{
			int opponentLargest = Math.max(pile[0].getValue(trump), pile[2].getValue(trump));
			int teamMateScore = pile[1].getValue(trump);
			if(hand.getSuit(firstLed.getSuit(), trump))
			{
				if(teamMateScore > opponentLargest)
				{
					return hand.getSmallestCard(trump, firstLed);
				}
				else
				{
					if(hand.getLargestCard(trump, firstLed).getValue(trump) > opponentLargest)
					{
						return hand.getLargestCard(trump, firstLed);
					}
					else
					{
						return hand.getSmallestCard(trump, firstLed);
					}
				}
			}
			else if(opponentLargest > teamMateScore)
			{
				Card temp = hand.getLargerCard(opponentLargest, trump);
				if(temp == null)
				{
					return hand.getSmallestCard(trump);
				}
				else
				{
					return temp;
				}
			}
			else
			{
				return hand.getSmallestCard(trump);
			}
		}

	}
	
	public Suit chooseTrump(Suit trump)
	{
		System.out.println(super.toString());
		int hearts = 0;
		int spades = 0;
		int diamonds = 0;
		int clubs = 0;
		for(int i = 0; i < 5; i++)
		{
			if(hand.getCard(i).getSuit().getSuit().equals("hearts") && trump != Suit.HEARTS)
				hearts += hand.getCard(i).getValue(Suit.HEARTS);
			else if(hand.getCard(i).getSuit().getSuit().equals("clubs") && trump != Suit.CLUBS)
				clubs += hand.getCard(i).getValue(Suit.CLUBS);
			else if(hand.getCard(i).getSuit().getSuit().equals("diamonds") && trump != Suit.DIAMONDS)
				diamonds += hand.getCard(i).getValue(Suit.DIAMONDS);
			else if(hand.getCard(i).getSuit().getSuit().equals("spades") && trump != Suit.SPADES)
				spades += hand.getCard(i).getValue(Suit.SPADES);
		}
		if(hearts > spades && hearts > diamonds && hearts > clubs && hearts > 75)
		{
			return Suit.HEARTS;
		}
		else if(spades > hearts && spades > diamonds && spades > clubs && spades > 75)
		{
			return Suit.SPADES;
		}
		else if(diamonds > spades && diamonds > hearts && diamonds > clubs && diamonds > 75)
		{
			return Suit.DIAMONDS;
		}
		else if(clubs > spades && clubs > diamonds && clubs > hearts && clubs > 75)
		{
			return Suit.HEARTS;
		}
		else
		{
			return null;
		}	
	}
	
	public Card chooseCard(Card card)//add who is dealer
	{
		System.out.println(super.toString());
		int value = 0;
		Suit temp = card.getSuit();
		for(int i = 0; i < 5; i++)
		{
			
			if(hand.getCard(i).isTrump(temp));
				value += hand.getCard(i).getValue(temp);
		}
		
		if(value > 70)
			return card;
		else
			return null;
	}
	
	
	public Card swapCard(Card card)
	{
		System.out.println(super.toString());
		Card oldCard = hand.getSmallestCard(card.getSuit());
		return hand.swapCard(oldCard, card);
	}
}
