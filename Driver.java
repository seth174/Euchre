public class Driver {

    public static void main(String[] args)
    {
        Suit s = Suit.SPADES;
        System.out.println(s);

        Value j = Value.JACK;
        System.out.println(j);

        Card one = new Card(Suit.CLUBS, Value.NINE);

        System.out.println(one);

        System.out.println(one.getValue(Suit.CLUBS));

        Deck myDeck = new Deck();
        myDeck.shuffle();
        myDeck.print();
    }    
}
