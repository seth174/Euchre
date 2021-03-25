public class Driver {

    public static void main(String[] args)
    {
    	/*
        Suit s = Suit.SPADES;
        System.out.println(s);

        Value j = Value.JACK;
        System.out.println(j);

        Card one = new Card(Suit.CLUBS, Value.NINE);

        System.out.println(one);

        System.out.println(one.getValue(Suit.CLUBS));

        Deck myDeck = new Deck();
        myDeck.shuffle();
		
		
    	
        Card jack = new Card(Suit.DIAMONDS, Value.JACK);
        Card queen = new Card(Suit.DIAMONDS, Value.QUEEN);
        Card king = new Card(Suit.DIAMONDS, Value.KING);
        Card ace = new Card(Suit.DIAMONDS, Value.ACE);
        Card ten = new Card(Suit.DIAMONDS, Value.TEN);

        Hand myHand = new Hand();
        myHand.setCard(0, jack);
        myHand.setCard(1, queen);
        myHand.setCard(2, king);
        myHand.setCard(3, ace);
        myHand.setCard(4, ten);
        myHand.setCards();
        myHand.print();

        myHand.sort();

        System.out.println("------------------");
        myHand.print();

        
        
    	
    	
    	Player seth = new User("seth");
    	Player bob = new User("bob");
    	Player joe = new User("joe");
    	Player anna = new User("anna");
    	
    	seth.setLeft(bob);
    	bob.setLeft(joe);
    	joe.setLeft(anna);
    	anna.setLeft(seth);
    	
    	Deck myDeck = new Deck();
    	
    	myDeck.deal(seth, bob, joe, anna);
    	System.out.println(seth.toString());
    	System.out.println(bob.toString());
    	System.out.println(joe.toString());
    	System.out.println(anna.toString());
    	
    	seth.chooseCard(new Card(Suit.HEARTS, Value.JACK));
    	*/
    	
    	Game myGame = new Game();
    	myGame.playGame();
    	
    	
    	
    	
    	
        
        
    }    
}
