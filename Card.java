
/**
 * This class creates a Card object.
 *
 * @author Darren Lin
 * @version May 22, 2020
 * @author Period: 4
 * @author Assignment: Black Jack Project
 *
 * @author Sources: none
 */
public class Card
{
    private int rank;

    private int suit;

    private int numVal;

    private String[] ranks = { "Ace", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };

    private String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };


    /**
     * Constructor for a Card object.
     * 
     * @param rank
     *            value of card
     * @param suit
     *            of card
     */
    Card( int rank, int suit )
    {
        this.rank = rank;
        this.suit = suit;
    }


    /**
     * Returns the rank of the Card.
     * 
     * @return rank value of Card
     */
    public int getRank()
    {
        return rank;
    }


    /**
     * Returns the suit of the Card.
     * 
     * @return suit of card
     */
    public int getSuit()
    {
        return suit;
    }


    /**
     * Returns the number value of the Card.
     * 
     * @return numVal the number value of the card
     */
    public int getNumVal()
    {
        if ( rank >= 10 )
        {
            numVal = 10;
        }
        else if ( rank == 0 )
        {
            numVal = 11;
        }
        else
        {
            numVal = rank + 1;
        }
        return numVal;

    }


    /**
     * Returns the rank and suit of the card in the format "(Rank) of (Suit)"
     * 
     * @return ranks[rank] + " of " + suits[suit] the string representation of a
     *         Card
     */
    public String toString()
    {
        return ranks[rank] + " of " + suits[suit];
    }

}
