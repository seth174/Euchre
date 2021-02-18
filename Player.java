public abstract class Player 
{
    private String name;
    private Card[] hand;

    public String getName()
    {
        return name;
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
            if(card.getValue() == handCard.getValue() && card.getSuit() == handCard.getSuit())
            {
                return handCard;
            }
        }
        return null;
    }

    
}
