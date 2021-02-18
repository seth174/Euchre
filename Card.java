public class Card {
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
        if(suit.toString() == trump.toString())
        {
            return value.getValue() + TRUMPBONUS;
        }
        else
        {
            return value.getValue();
        }
    }

}
