import java.util.Random;

public class Deck 
{
    private Card[] deck;
    private static final int DECKSIZE = 24;
    private Random r;

    public Deck()
    {
        r = new Random();
        deck = new Card[DECKSIZE];
        int counter = 0;
        for(Suit suit: Suit.values())
        {
            for(Value value: Value.values())
            {
                deck[counter] = new Card(suit, value);
                counter = counter + 1;
            }
        }
    }

    public void shuffle()
    {
        Card[] shuffled = new Card[DECKSIZE];
        int index = 0;
        for(int i = 0; i < deck.length; i++)
        {
            index = r.nextInt(deck.length - i);
            shuffled[i] = deck[index];
            Card temp = deck[deck.length - 1 - i];
            deck[deck.length - i - 1] = deck[index];
            deck[index] = temp;
        }
        deck = shuffled;
    }

    public void print()
    {
        for(Card card: deck)
        {
            System.out.println(card);
        }
    }
    
}
