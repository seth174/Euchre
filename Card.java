
public class Card implements Comparable<Card>
 {
    private Suit suit;
    private Value value;
    private static final int TRUMPBONUS = 11;

    public Card(Suit suit, Value value)
    {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Value getValue()
    {
        return value;
    }

    public String toString()
    {
        return "The " + value.toString().toLowerCase() + " of " + suit.toString().toLowerCase();
    }

    public int getValue(Suit trump)
    {
        if(this.getValue().getValue() == 11)
        {
            if(this.isTrump(trump))
            {
                if(this.getSuit().toString().equals(trump.toString()))
                {
                    return 27;
                }
                else
                {
                    return 26;
                }
            }
            else
            {
                return value.getValue();
            }
        }
        else if(suit.toString() == trump.toString())
        {

            return value.getValue() + TRUMPBONUS;
        }
        else
        {
            return value.getValue();
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        else if(!(o instanceof Card))
        {
            return false;
        }
        else
        {
            Card temp = (Card)o;
            return temp.value == this.value && temp.suit == this.suit;
        }
        
    }

    @Override
    public int compareTo(Card o) {
        return this.suit.compareTo(o.suit);
    }

    public boolean isTrump(Suit trump)
    {
        if(this.getValue().getValue() == 11)
        {
            if((this.getSuit().toString().equals("HEARTS") || this.getSuit().toString().equals("DIAMONDS")) && 
            (trump.toString().equals("HEARTS") || trump.toString().equals("DIAMONDS")))
            {
                return true;
            }
            else if ((this.getSuit().toString().equals("SPADES") || this.getSuit().toString().equals("CLUBS")) && 
            (trump.toString().equals("SPADES") || trump.toString().equals("CLUBS")))
            {
                return true;
            }
        }
        return trump.toString().equals(this.suit.toString());
    }



}
