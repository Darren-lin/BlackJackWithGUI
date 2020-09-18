import java.awt.event.*;
import javax.swing.*;

/**
 * This class runs the BlackJack GUI and game.
 *
 * @author Darren Lin
 * @version May 22, 2020
 * @author Period: 4
 * @author Assignment: Black Jack Project
 *
 * @author Sources: none
 */
public class BlackJack {
	private boolean dealerBlackJack = false;

	private boolean playerBlackJack = false;

	private Deck deck;

	private BlackJackGUI GUI;

	private Dealer dealer;

	private Player player;

	/**
	 * Creates a BlackJack object with a GUI and play, hit, and stand buttons
	 */
	public BlackJack() {
		GUI = new BlackJackGUI();
		GUI.setPlayAction(new PlayAction());
		GUI.setHitAction(new HitAction());
		GUI.setStandAction(new StandAction());
		GUI.enablePlayAndBet();
	}

	/**
	 * This class runs the method when the Play button is pressed by the user.
	 *
	 * @author Darren Lin
	 * @version May 22, 2020
	 * @author Period: 4
	 * @author Assignment: Black Jack Project
	 *
	 * @author Sources: none
	 */
	class PlayAction implements ActionListener {
		/**
		 * This method is ran when the user clicks the Play button. This method checks
		 * if a valid number is inputted into the bet amount. This method also deals 2
		 * cards each to the Player and Dealer. The method will continue to the game
		 * method or enable the hit and stand buttons.
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			if (GUI.validBet()) {
				if (GUI.getStringBet()) {
					GUI.clearDealerHand();
					GUI.clearPlayerHand();
					GUI.setOutputText("Please enter a bet.\nYou have $" + GUI.getTotalMoney());
				} else if (GUI.getBet() == 0) {
					GUI.clearDealerHand();
					GUI.clearPlayerHand();
					GUI.setOutputText("Please bet an amount greater than 0.\nYou have $" + GUI.getTotalMoney());

				} else if (GUI.getBet() < 0) {
					GUI.clearDealerHand();
					GUI.clearPlayerHand();
					GUI.setOutputText("Please bet a positive amount.\nYou have $" + GUI.getTotalMoney());
				}

				else if (GUI.getTotalMoney() < GUI.getBet()) {
					GUI.clearDealerHand();
					GUI.clearPlayerHand();
					GUI.setOutputText("Please bet a lower amount.\nYou have $" + GUI.getTotalMoney());
				} else if (GUI.getTotalMoney() >= GUI.getBet() && GUI.getBet() > 0) {
					GUI.setOutputText("");
					GUI.setTotalMoney();
					GUI.toggleBetButtonsFalse();
					deck = new Deck();
					deck.shuffle();
					dealer = new Dealer(0);
					player = new Player(0, 0);
					int i = 0;
					while (i < 2) {
						player.draw(deck.dealCard());
						dealer.draw(deck.dealCard());
						i++;
					}
					if (player.getHandVal() == 21) {
						playerBlackJack = true;
					}
					if (dealer.getHandVal() == 21) {
						dealerBlackJack = true;
					}
					GUI.showPlayerHand(player);
					GUI.showDealersFirstCard(dealer);
					if (!playerBlackJack && !player.bust()) {
						GUI.enableHitAndStand();
					} else {
						game();
					}
				}
			}
		}

	}

	/**
	 * This class is run when the Hit button is pressed by the User.
	 *
	 * @author Darren Lin
	 * @version May 22, 2020
	 * @author Period: 4
	 * @author Assignment: Black Jack Project
	 *
	 * @author Sources: none
	 */
	class HitAction implements ActionListener {
		/**
		 * This method is run when the Hit button is clicked by the user. This method
		 * checks the value of the Player's hand and only allows the Player to hit if
		 * their hand value is less than 21. If the Player's hand value is equal to 21,
		 * the method will automatically run the game method. If the Player's hand value
		 * is greater than 21, the Player will bust and will lose automatically.
		 */
		public void actionPerformed(ActionEvent e) {
			if (!player.bust() && player.getHandVal() < 21) {
				player.draw(deck.dealCard());
				GUI.showPlayerHand(player);
				if (player.getHandVal() == 21) {
					game();
				}
			}
			if (player.bust() || player.getHandVal() > 21) {
				GUI.setOutputText("Dealer Wins\nDealer had: " + dealer.getHandVal() + "\nPlayer busted with: "
						+ player.getHandVal());
				game();
			}
		}
	}

	/**
	 * This class is run when the Stand button is pressed by the User.
	 * 
	 * @author Darren Lin
	 * @version May 22, 2020
	 * @author Period: 4
	 * @author Assignment: Black Jack Project
	 *
	 * @author Sources: none
	 */
	class StandAction implements ActionListener {
		/**
		 * This method runs the game method.
		 */
		public void actionPerformed(ActionEvent e) {
			game();
		}
	}

	/**
	 * The game method checks if the Dealer needs to hit by checking its hand value.
	 * If the Dealer's hand value is less than 17, the Dealer needs to hit. If the
	 * Dealer busts, the method will give the Player the win if the Player has not
	 * busted yet. If the Dealer and the Player has not busted yet, this method then
	 * compares the hand values of both the Dealer and the Player and displays who
	 * wins.
	 */
	public void game() {
		GUI.showDealersEntireHand(dealer);
		if (!player.bust()) {

			if (dealer.getHandVal() == 21) {
				dealerBlackJack = true;
			}
			while (dealer.hasToHit()) {
				dealer.draw(deck.dealCard());
				GUI.showDealersEntireHand(dealer);
				if (dealer.bust() && player.getHandVal() <= 21) {
					GUI.setOutputText("Player Wins\nDealer busted with: " + dealer.getHandVal() + "\nPlayer had: "
							+ player.getHandVal());
					GUI.setWinnings();
					break;
				}

			}
		}
		if (!player.bust() && !dealer.bust()) {
			if (dealer.getHandVal() < player.getHandVal() && player.getHandVal() <= 21) {
				GUI.setWinnings();
				GUI.setOutputText(
						"Player Wins\nDealer had: " + dealer.getHandVal() + "\nPlayer had: " + player.getHandVal());
			} else if (dealer.getHandVal() > player.getHandVal() && dealer.getHandVal() <= 21) {
				GUI.setOutputText(
						"Dealer Wins\nDealer had: " + dealer.getHandVal() + "\nPlayer had: " + player.getHandVal());
			} else if (dealer.getHandVal() == player.getHandVal()) {
				GUI.setTie();
				GUI.setOutputText("Tie\nDealer had: " + dealer.getHandVal() + "\nPlayer had: " + player.getHandVal());
			} else if (player.bust() == true && dealer.getHandVal() <= 21) {
				GUI.setOutputText("Dealer Wins\nDealer had: " + dealer.getHandVal() + "\nPlayer busted with: "
						+ player.getHandVal());
			} else if (dealer.bust() == true && player.getHandVal() <= 21) {
				GUI.setWinnings();
				GUI.setOutputText("Player Wins\nDealer busted with: " + dealer.getHandVal() + "\nPlayer had: "
						+ player.getHandVal());
			} else if (dealerBlackJack && player.getHandVal() < 21) {
				GUI.setOutputText("Dealer Wins\nDealer has BlackJack: " + dealer.getHandVal() + "\nPlayer had: "
						+ player.getHandVal());

			} else if (playerBlackJack == true && dealer.getHandVal() < 21) {
				GUI.setWinnings();
				GUI.setOutputText("Player Wins\nDealer had: " + dealer.getHandVal() + "\nPlayer has BlackJack: "
						+ player.getHandVal());
			} else if (playerBlackJack == true && dealerBlackJack == true) {
				GUI.setTie();
				GUI.setOutputText("Tie\nDealer had: " + dealer.getHandVal() + "\nPlayer had: " + player.getHandVal());

			} else if (player.bust() == true && dealer.bust() == true) {
				GUI.setOutputText(
						"Dealer Wins\nDealer had: " + dealer.getHandVal() + "\nPlayer Busted: " + player.getHandVal());
			}
		}
		player.tossHand();
		dealer.tossHand();
		GUI.enablePlayAndBet();
		GUI.toggleBetButtonsTrue();
		playerBlackJack = false;
		dealerBlackJack = false;
	}

	/**
	 * Runs the Black Jack game
	 * 
	 * @param args runs the Black Jack game
	 */
	public static void main(String[] args) {
		new BlackJack();
	}

}
