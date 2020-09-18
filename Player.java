import java.util.Stack;


/**
 * This class creates a Player object.
 *
 * @author Darren Lin
 * @version May 22, 2020
 * @author Period: 4
 * @author Assignment: Black Jack Project
 *
 * @author Sources: none
 */
public class Player
{

    private Stack<Card> hand = new Stack<Card>();

    private int amount = 1000;

    private int handVal;


    /**
     * Constructor for a Player object.
     * 
     * @param amount
     *            of money the Player has
     * @param handVal
     *            the value of the Player's hand
     */
    Player( int amount, int handVal )
    {
        this.amount = amount;
        this.handVal = handVal;
    }


    /**
     * This method returns the amount of money the Player has.
     * 
     * @return amount the total amount of money the Player has
     */
    public int getAmount()
    {
        return amount;
    }


    /**
     * This method gets the value of the Player's hand.
     * 
     * @return handVal the value of the Player's hand
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
     * This method returns a string that reveals the Player's hand before
     * hitting.
     * 
     * @return is a String representation of the Player's hand
     */
    public String getHand()
    {
        Card card1 = hand.get( 0 );
        Card card2 = hand.peek();
        return "Player's hand: [" + card1.toString() + ", " + card2.toString()
            + "]";
    }


    /**
     * This method returns a string that reveals the Player's entire hand.
     * 
     * @return A String representation of the Player's entire hand.
     */
    public String getAllHand()
    {
        return "Player's hand: " + hand;
    }


    /**
     * This method adds a Card object to the Player's hand.
     * 
     * @param card
     *            to be added to the Player's hand
     */
    public void draw( Card card )
    {
        hand.add( card );
    }


    /**
     * This method returns true or false depending on if the Player's hand value
     * is greater than 21. If the hand value is greater than 21 the Player busts
     * and the method returns true, if not then false is returned.
     * 
     * @return true if Player's hand value is greater than 21. false if Player's
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
     * This method deletes the Player's hand.
     */
    public void tossHand()
    {
        hand.removeAllElements();
    }

}
