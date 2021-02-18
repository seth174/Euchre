public enum Value 
{
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private int value;

    private Value(int number)
    {
        value = number;
    }

    public int getValue()
    {
        return value;
    }

}
