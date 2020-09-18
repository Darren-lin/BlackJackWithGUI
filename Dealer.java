import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


/**
 * This class creates a Dealer object.
 *
 * @author Darren Lin
 * @version May 22, 2020
 * @author Period: 4
 * @author Assignment: Black Jack Project
 *
 * @author Sources: none
 */
public class Dealer
{
    private Stack<Card> hand = new Stack<Card>();

    private int handVal;


    /**
     * Constructor for a Dealer object
     * 
     * @param handVal
     *            the value of the Dealer's hand
     */
    Dealer( int handVal )
    {
        this.handVal = handVal;
    }


    /**
     * This method determines whether or not the Dealer needs to hit or not. If
     * the Dealer's hand value is less than 17 the dealer is required to hit.
     * 
     * @return true if the dealer needs to hit, false if dealer doesn't need to
     *         hit
     */
    public boolean hasToHit()
    {
        if ( handVal < 17 )
        {
            return true;
        }
        return false;
    }


    /**
     * This method gets the value of the Dealer's hand.
     * 
     * @return handVal the value of the Dealer's hand
     */
    public int getHandVal()
    {
        handVal = 0;
        boolean hasAce = false;
        int aceCount = 0;
        for ( int i = 0; i < hand.size(); i++ )
        {
            if ( hand.get( i ).getRank() == 0 )
            {
                aceCount++;
            }
            handVal += hand.get( i ).getNumVal();

        }
        while ( aceCount > 0 && handVal > 21 )
        {
            handVal -= 10;
            aceCount--;
        }
        return handVal;
    }


    /**
     * This method returns a string that only reveals the Dealer's first card.
     * 
     * @return result A String representation of the Dealer's first card
     */
    public String showFirstCard()
    {
        Card card1 = hand.peek();
        return "Dealer's hand: [" + card1.toString() + ", ?]";
    }


    /**
     * This method returns a string that reveals the Dealer's entire hand before
     * hitting.
     * 
     * @return A String representation of the Dealer's entire hand before
     *         hitting
     */
    public String getHand()
    {
        Card card1 = hand.peek();
        Card card2 = hand.get( 0 );
        return "Dealer's hand: [" + card1.toString() + ", " + card2.toString()
            + "]";
    }


    /**
     * This method returns a string that reveals the Dealer's entire hand.
     * 
     * @return A String representation of the Dealer's entire hand.
     */
    public String getAllHand()
    {

        return "Dealer's hand: " + hand;
    }


    /**
     * This method adds a Card object to the Dealer's hand.
     * 
     * @param card
     *            to be added to the Dealer's hand
     */
    public void draw( Card card )
    {
        hand.add( card );
    }


    /**
     * This method returns true or false depending on if the Dealer's hand value
     * is greater than 21. If the hand value is greater than 21 the Dealer busts
     * and the method returns true, if not then false is returned.
     * 
     * @return true if Dealer's hand value is greater than 21. false if Dealer's
     *         hand is less than or equal to 21
     */
    public boolean bust()
    {
        if ( handVal > 21 )
        {
            return true;
        }
        return false;
    }


    /**
     * This method deletes the Dealer's hand.
     */
    public void tossHand()
    {
        hand.removeAllElements();
    }
}
