
import java.util.*;
public class User extends Player
{
	Scanner myScanner;
	public User(String name)
	{
		super(name);
		myScanner = new Scanner(System.in);
	}
	
	public Card playCard(Card[] pile, int playedCards, Suit trump, Card firstLed)
	{
		System.out.println(this.toString());
		System.out.println("Which card would you like to play?");
		int index = 0;
		while(true)
		{
			String input = myScanner.nextLine();
			if(convertUserInput(input))
			{
				index = Integer.parseInt(input);
				break;
			}
			else
				System.out.println("Enter a valid numer");
		}
		Card temp = hand.getCard(index);
		System.out.println(super.getName() + " played " + temp);
		return temp;
	}
	
	public Suit chooseTrump(Suit trump)
	{
		Suit temp = trump;
		while(temp == trump)
		{
			System.out.println(super.toString());
			System.out.println("Which trump would you like to choose? You cannot choose " + trump);
			String input = myScanner.nextLine();
			temp = stringtoSuit(input);
			System.out.println("You chose " + temp);
		}
		return temp;
	}
	
	public Card chooseCard(Card card)
	{
		System.out.println(super.toString());
		System.out.println("Would you like to order this card?" + card);
		
		String input = myScanner.nextLine();
		if(input.equals("yes"))
			return card;
		else
			return null;
	}
	
	public Card swapCard(Card card)
	{
		int index =-5;
		while(index > 5 || index < 0)
		{
			System.out.println("Which card would you like to drop for " + card + "?");
			System.out.println(super.toString());
			System.out.println("Use the index starting at 0");
			System.out.println("press five to drop the card you just picked up");
			index = myScanner.nextInt();
		}
		if(index == 5)
		{
			return card;
		}
		else
		{
			return super.swapCard(card, index);
		}
	}
	
	
	private Suit stringtoSuit(String suit)
	{
		if(suit.equals(Suit.HEARTS.getSuit()))
			return Suit.HEARTS;
		if(suit.equals(Suit.SPADES.getSuit()))
			return Suit.SPADES;
		if(suit.equals(Suit.DIAMONDS.getSuit()))
			return Suit.DIAMONDS;
		if(suit.equals(Suit.CLUBS.getSuit()))
			return Suit.CLUBS;
		return null;
	}
	
	private boolean convertUserInput(String number)
	{
		try
		{
			Integer.parseInt(number);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
