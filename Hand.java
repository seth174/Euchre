import java.util.Arrays;
import java.util.Comparator;
public class Hand implements Comparator<Card>
{
    private Card[] hand;
    private int remainingCards;
    private static final int NUMBEROFCARDS = 5;

    public Hand()
    {
        remainingCards = 0;
        hand = new Card[NUMBEROFCARDS];
    }

    public Card removeCard(Card card)
    {
        Card temp = null;
        boolean found = false;
        int size = remainingCards;
        for(int i = 0; i < size; i++)
        {
            if(card.equals(hand[i]))
            {
                temp = hand[i];
                removeCard();
                found = true;
            }
            if(found && i != 4)
            {
                hand[i] = hand[i + 1];
            }
        }
        int index = size - 1;
        hand[index] = null;
        return temp;
    }
    
    public boolean getSuit(Suit suit, Suit trump)
    {
    	for(int i = 0; i < NUMBEROFCARDS; i++)
    	{
    		if(hand[i] == null)
    			return false;
    		if(hand[i].getSuit() == suit && hand[i].getValue().getValue() == 11 
    				&& hand[i].isTrump(trump) && suit != trump)
    		{
    			System.out.println("here");
    			continue;
    		}

    		if(hand[i].isTrump(trump) && suit == trump)
    			return true;
    		if(hand[i].getSuit() == suit)
    			return true;
    	}
    	return false;
    }
    
    public Card swapCard(Card newCard, int index)
    {
    	Card temp = hand[index];
    	hand[index] = newCard;
    	return temp;
    }
    
    public Card swapCard(Card oldCard, Card newCard)
    {
    	for(int i = 0; i < NUMBEROFCARDS; i++)
    	{
    		if(hand[i] == oldCard)
    		{
    			hand[i] = newCard;
    		}
    	}
    	return oldCard;
    }

    public void removeCard()
    {
        remainingCards = remainingCards - 1;
    }
    
    public int getRemainingCards()
    {
    	return remainingCards;
    }
    

    public void setCard(int index, Card card)
    {
        hand[index] = card;
        remainingCards = remainingCards + 1;
    }
    
    public void setCard(Card card)
    {
    	if(remainingCards != 5)
    	{
        	hand[remainingCards] = card;
        	remainingCards = remainingCards + 1;
    	}

    }
    
    public void reset()
    {
        for(int i = 0; i < NUMBEROFCARDS; i++)
        {
            hand[i] = null;
        }
        remainingCards = 0;
    }

    public void setCards()
    {
        remainingCards = 5;
    }

    public Card getCard(int index)
    {
        if(hand.length > index)
        {
            return hand[index];
        }
        return null;
    }

    public Card getCard(Card card)
    {
        for(Card handCard: hand)
        {
            if(card.equals(handCard))
            {
                return handCard;
            }
        }
        return null;
    }
    
    public Card getCard(int value, Suit trump)
    {
    	for(int i = 0; i < remainingCards; i++)
    	{
    		if(hand[i].getValue(trump) == value)
    			return hand[i];
    	}
    	return null;
    }
    
    public Card getLargestCard(Suit trump, Card firstLed)
    {
    	Card largest = null;
    	for(int i = 0; i < remainingCards;i++)
    	{
    		if(largest == null && hand[i].getSuit() == firstLed.getSuit())
    		{
    			largest = hand[i];
    		}
    		else if(largest == null && hand[i].isTrump(trump) && hand[i].getValue().getValue() == 11
    				&& firstLed.getSuit() == trump)
    		{
    			largest = hand[i];
    		}
    		else if(largest == null)
    			continue;
    		else if(hand[i].isTrump(trump) && hand[i].getValue().getValue() == 11 && firstLed.getSuit() == trump)
    		{
    			largest = hand[i];
    		}
    		else if(hand[i].getValue(trump) > largest.getValue(trump) && hand[i].getSuit() == firstLed.getSuit())
    		{
    			largest = hand[i];
    		}			
    	}
    	return largest;
    }
    
    public Card getSmallestTrump(Suit trump)
    {
    	Card smallest = null;
    	for(int i = 0; i < remainingCards; i++)
    	{
    		if(smallest == null && hand[i].isTrump(trump))
    		{
    			smallest = hand[i];
    		}
    		else if(smallest == null)
    			continue;
    		else if(smallest.getValue(trump) > hand[i].getValue(trump))
    		{
    			smallest = hand[i];
    		}
    	}
    	return smallest;
    }
    
    public Card getSmallestCard(Suit trump)
    {
    	Card smallest = hand[0];
    	for(int i = 1; i < remainingCards; i++)
    	{
    		if(hand[i].getValue(trump) < smallest.getValue(trump))
    			smallest = hand[i];
    	}
    	return smallest;
    }
    
    public Card getSmallestCard(Suit trump, Card firstLed)
    {
    	Card smallest = null;
    	for(int i = 0; i < remainingCards;i++)
    	{
    		if(smallest == null && hand[i].getSuit() == firstLed.getSuit())
    		{
    			smallest = hand[i];
    		}
    		else if(smallest == null && hand[i].isTrump(trump) && hand[i].getValue().getValue() == 11
    				&& firstLed.getSuit() == trump)
    		{
    			smallest = hand[i];
    		}
    		else if(smallest == null)
    			continue;
    		else if(hand[i].isTrump(trump) && hand[i].getValue().getValue() == 11 
    				&& firstLed.getSuit() == trump && smallest.getValue(trump) > hand[i].getValue(trump))
    		{
    			smallest = hand[i];
    		}
    		else if(hand[i].getValue(trump) < smallest.getValue(trump) && hand[i].getSuit() == firstLed.getSuit())
    		{
    			smallest = hand[i];
    		}			
    	}
    	return smallest;
    }
    
    public Card getLargerCard(int valueToBeat, Suit trump)
    {
    	Card largerCard = null;
    	for(int i = 0; i < remainingCards; i++)
    	{
    		if(hand[i].isTrump(trump))
    		{
    			if(hand[i].getValue(trump) > valueToBeat && largerCard == null)
    			{
    				largerCard = hand[i];
    			}
    			else if(largerCard != null)
    			{
    				if(hand[i].getValue(trump) > valueToBeat && 
    						largerCard.getValue(trump) > hand[i].getValue(trump))
    				{
    					largerCard = hand[i];
    				}
    			}
    		}
    	}
    	return largerCard;
    }
    
    public Card getLargerCard(int valueToBeat, Suit trump, Card firstLed)
    {
    	Card largerCard = null;
    	for(int i = 0; i < remainingCards; i++)
    	{
    		if(hand[i].getSuit() == firstLed.getSuit() || (firstLed.isTrump(trump) && trump == firstLed.getSuit()))
    		{
    			if(hand[i].getValue(trump) > valueToBeat && largerCard == null)
    			{
    				largerCard = hand[i];
    			}
    			else if(largerCard != null)
    			{
    				if(hand[i].getValue(trump) > valueToBeat && 
    						largerCard.getValue(trump) > hand[i].getValue(trump))
    				{
    					largerCard = hand[i];
    				}
    			}
    		}
    	}
    	return largerCard;
    }

    public void print()
    {
        for(Card card: hand)
        {
            System.out.println(card);
        }
    }

    public void sort(Suit trump)
	{
		Arrays.sort(hand, new Comparator<Card>() {
            public int compare(Card o1, Card o2)
            {
            if(o1 == null || o2 == null)
            	return 0;
            if(o1.isTrump(trump) && !(o2.isTrump(trump)))
            {
				return -1;
            }
			else if(!(o1.isTrump(trump)) && o2.isTrump(trump))
            {
				return 1;    
            }
			else if(o1.getValue(trump) == 26 && o2.getValue(trump) < 26)
            {
				return -1;
            }
			else if(o1.getValue(trump) < 26 && o2.getValue(trump) == 26)
            {
				return 1;
            }
            else
            {
                int value = o1.getSuit().compareTo(o2.getSuit());
				if(value != 0)
					return value;
				else
				{
					if(o1.getValue(trump) > o2.getValue(trump)) 
						return -1;
					else
						return 1;
				}
            }
        }
        });
	}
    
    public void sort()
	{
		Arrays.sort(hand, new Comparator<Card>() {
            public int compare(Card o1, Card o2)
            {
                int value = o1.getSuit().compareTo(o2.getSuit());
				if(value != 0)
					return value;
				else
				{
					return o2.getValue().compareTo(o1.getValue());
				}

        }
        });
	}
    

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
    
}
