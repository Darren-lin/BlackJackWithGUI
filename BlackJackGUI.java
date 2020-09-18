import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * This class creates a Graphical User Interface for the BlackJack class.
 * 
 * @author Darren Lin
 * @version May 22, 2020
 * @author Period: 4
 * @author Assignment: Black Jack Project
 *
 * @author Sources: none
 */
public class BlackJackGUI extends JFrame
{
    private JButton hit = new JButton( "Hit" );

    private JButton play = new JButton( "Play" );

    private JButton stand = new JButton( "Stand" );

    private JLabel bet = new JLabel( "Bet Amount: $" );

    private JLabel moneyAmount = new JLabel( "Money Amount: $" );

    private JLabel totalMoney = new JLabel( "1000" );

    private JPanel inputPanel = new JPanel();

    private JPanel userPanel = new JPanel();

    private JTextArea playerCards = new JTextArea();

    private JTextArea dealerCards = new JTextArea();

    private JTextArea outputText = new JTextArea();

    private JTextField betAmount = new JTextField( 6 );


    /**
     * Constructor for the BlackJackGUI
     */
    public BlackJackGUI()
    {
        // Creates frame
        JFrame mainFrame = new JFrame();
        mainFrame.setSize( 850, 500 );

        // adds buttons
        mainFrame.add( inputPanel, BorderLayout.SOUTH );
        inputPanel.add( moneyAmount );
        inputPanel.add( totalMoney );
        inputPanel.add( hit, null );
        inputPanel.add( play, null );
        inputPanel.add( stand, null );
        inputPanel.add( bet, null );
        inputPanel.add( betAmount );

        // add player and dealer cards
        mainFrame.add( userPanel, BorderLayout.CENTER );
        userPanel.setLayout( new BoxLayout( userPanel, BoxLayout.PAGE_AXIS ) );
        userPanel.add( playerCards, null );
        userPanel.add( dealerCards, null );
        userPanel.add( outputText );

        // enable/disable buttons for start
        hit.setEnabled( false );
        stand.setEnabled( false );
        play.setEnabled( true );
        bet.setEnabled( true );

        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setVisible( true );
        mainFrame.setResizable( false );

    }


    /**
     * This method returns an integer value that represents the bet amount that
     * the user input into the GUI
     * 
     * @return an integer representation of the bet
     */
    public int getBet()
    {
        return Integer.parseInt( betAmount.getText() );
    }


    /**
     * This method returns true if the value of betAmount is a number and false
     * otherwise.
     * 
     * @return true or false depending on the value of betAmount
     */
    public boolean validBet()
    {
        try
        {
            int money = Integer.parseInt( betAmount.getText() );
            return true;
        }
        catch ( NumberFormatException e )
        {
            outputText.setText(
                "Please enter a number\nYou have $" + totalMoney.getText() );
            return false;
        }
    }


    /**
     * This method returns true or false depending on whether the bet is empty.
     * 
     * @return true if betAmount is empty, false if betAmount has a value.
     */
    public boolean getStringBet()
    {
        return betAmount.getText().isEmpty();
    }


    /**
     * This method returns the total amount of money that the Player has.
     * 
     * @return an integer value that represents the total money that the Player
     *         has
     */
    public int getTotalMoney()
    {
        return Integer.parseInt( totalMoney.getText() );
    }


    /**
     * This method subtracts the bet amount inputted by the Player from the
     * total money they have.
     */
    public void setTotalMoney()
    {
        int money = Integer.parseInt( totalMoney.getText() );
        money -= Integer.parseInt( betAmount.getText() );
        totalMoney.setText( String.valueOf( money ) );
    }


    /**
     * This method is called when the Dealer and the Player tie. This method
     * adds the bet amount back to the Player's total money.
     */
    public void setTie()
    {
        int money = Integer.parseInt( totalMoney.getText() );
        money += Integer.parseInt( betAmount.getText() );
        totalMoney.setText( String.valueOf( money ) );
    }


    /**
     * This method is called when the Player wins. This method multiplies the
     * bet amount by 2 and adds that amount to the Player's total money.
     */
    public void setWinnings()
    {
        int money = Integer.parseInt( totalMoney.getText() );
        money += 2 * Integer.parseInt( betAmount.getText() );
        totalMoney.setText( String.valueOf( money ) );
    }


    /**
     * This method adds an Action Listener to the Hit button
     * 
     * @param e
     *            the event
     */
    public void setHitAction( ActionListener e )
    {
        hit.addActionListener( e );
    }


    /**
     * This method adds an Action Listener to the Play button
     * 
     * @param e
     *            the event
     */
    public void setPlayAction( ActionListener e )
    {
        play.addActionListener( e );
    }


    /**
     * This method adds an Action Listener to the Stand button
     * 
     * @param e
     *            the event
     */
    public void setStandAction( ActionListener e )
    {
        stand.addActionListener( e );
    }


    /**
     * This method displays the Player's hand value and the Cards.
     * 
     * @param player
     *            the Player that we want to display
     */
    public void showPlayerHand( Player player )
    {
        playerCards.setText( "PLAYER\nPlayer Hand Value: "
            + player.getHandVal() + "\n" + player.getAllHand() );

    }


    /**
     * This method clears the Player's area display.
     */
    public void clearPlayerHand()
    {
        playerCards.setText( "" );
    }


    /**
     * This method clears the Dealer's area display.
     */
    public void clearDealerHand()
    {
        dealerCards.setText( "" );
    }


    /**
     * This method displays the Dealer's first Card.
     * 
     * @param dealer
     *            the Dealer that we want to display
     */
    public void showDealersFirstCard( Dealer dealer )
    {
        dealerCards.setText(
            "DEALER\nDealer Hand Value: ?\n" + dealer.showFirstCard() );
    }


    /**
     * This method displays the Dealer's hand value and Cards.
     * 
     * @param dealer
     *            the Dealer that we want to display
     */
    public void showDealersEntireHand( Dealer dealer )
    {
        dealerCards.setText( "DEALER\nDealer Hand Value: "
            + dealer.getHandVal() + "\n" + dealer.getAllHand() );
    }


    /**
     * This method sets the output text to the string paramater.
     * 
     * @param string
     *            the message that we want to display
     */
    public void setOutputText( String string )
    {
        outputText.setText( string );
    }


    /**
     * This method enables the hit and stand button, and disables the play
     * button.
     */
    public void enableHitAndStand()
    {
        hit.setEnabled( true );
        stand.setEnabled( true );
        play.setEnabled( false );
    }


    /**
     * This method enables the play button, and disables the hit and stand
     * button.
     */
    public void enablePlayAndBet()
    {
        hit.setEnabled( false );
        stand.setEnabled( false );
        play.setEnabled( true );
    }


    /**
     * This method disables the betAmount JTextField.
     */
    public void toggleBetButtonsFalse()
    {
        betAmount.setEditable( false );
        betAmount.setEnabled( false );
    }


    /**
     * This method enables the betAmount JTextField
     */
    public void toggleBetButtonsTrue()
    {
        betAmount.setEditable( true );
        betAmount.setEnabled( true );
    }

}
