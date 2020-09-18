import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * This class creates a Deck object.
 *
 * @author Darren Lin
 * @version May 22, 2020
 * @author Period: 4
 * @author Assignment: Black Jack Project
 *
 * @author Sources: none
 */
public class Deck
{
    private String[] ranks = { "Ace", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };

    private String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };

    private ArrayList<Card> deck = new ArrayList<Card>();


    /**
     * Constructor for a Deck object. Creates 52 Card objects and adds it to a
     * Deck
     */
    Deck()
    {

        for ( int suit = 0; suit < suits.length; suit++ )
        {
            for ( int rank = 0; rank < ranks.length; rank++ )
            {
                deck.add( new Card( rank, suit ) );
            }
        }
    }


    /**
     * This method shuffles the Deck object
     */
    public void shuffle()
    {
        Collections.shuffle( deck );
    }


    /**
     * This method removes a Card object from the Deck.
     * 
     * @return deck.remove(0) which is the first Card in the Deck
     */
    public Card dealCard()
    {
        return deck.remove( 0 );
    }
}
