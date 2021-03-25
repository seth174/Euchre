
import java.util.Scanner;
import java.util.ArrayList;
public class Game 
{
	private static final int chosenCardIndex = 20;
	private Card chosenCard;
	private Player dealer;
	public Player currentPlayer;
	private Card[] pile;
	private Card pickedCard;
	private Suit trump;
	private Suit notTrump;
	private Deck gameDeck;
	private ArrayList<Player> playerList;
	private Card firstLed;
	private Suit firstLedSuit;
	private Player wonLast;
	private Team teamOne;
	private Team teamTwo;
	private boolean firstLedTrump;
	private int playedCards;
	
	
	public Game()
	{
		pile = new Card[4];
		chosenCard = null;
		currentPlayer = null;
		pickedCard = null;
		gameDeck = new Deck();
		trump = null;
		firstLed = null;
		
		playerList = new ArrayList<>();
		
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Please enter the name of the four players");
		Player one = new User(myScanner.nextLine());
		Player two = new CPU(myScanner.nextLine());
		Player three = new CPU(myScanner.nextLine());
		Player four = new CPU(myScanner.nextLine());
		playerList.add(one);
		playerList.add(two);
		playerList.add(three);
		playerList.add(four);
		
		teamOne = new Team("Team one");
		teamTwo = new Team("Team two");
		
		playerList.get(0).setTeam(teamOne);
		playerList.get(1).setTeam(teamTwo);
		playerList.get(2).setTeam(teamOne);
		playerList.get(3).setTeam(teamTwo);
		
		playerList.get(0).setTeamMate(playerList.get(2));
		playerList.get(1).setTeamMate(playerList.get(3));
		playerList.get(2).setTeamMate(playerList.get(0));
		playerList.get(3).setTeamMate(playerList.get(1));
		
		for(int i = 0; i < 4; i++)
		{
			if(i == 3)
			{
				playerList.get(i).setLeft(playerList.get(0));
			}
			else
			{
				playerList.get(i).setLeft(playerList.get(i + 1));
			}
			
		}
		
		dealer = playerList.get(0);
		wonLast = null;
		firstLedTrump = false;
		playedCards = 0;
	}
	
	public void playGame()
	{	
		boolean game = true;
		while(game)
		{
			if(dealer.getTeam().getGameScore() >= 10 || 
					dealer.getLeft().getTeam().getGameScore() >= 10)
				break;
			System.out.println("Dealer is " + dealer.getName());
			deal();
			pickCard();
			if(pickedCard == null)
			{
				notTrump = chosenCard.getSuit();
				chooseTrump();
			}
			else
			{
				swapCard();
				setTrump(pickedCard.getSuit());
			}
			
			sortHands();
			for(int i = 0; i < 5; i++)
			{
				playHand();
				whoWon();
				newRound();
			}
			System.out.println("End Round");
			whoWonRound();
			System.out.println("Team won with a winning score of: " + 
			Math.max(playerList.get(0).getTeam().getGameScore(), 
					playerList.get(1).getTeam().getGameScore()));
			newHand();
		}
		System.out.println("End of game");
		
		
		
	}
	
	private void deal()
	{
		gameDeck.shuffle();
		gameDeck.deal(dealer, dealer.getLeft(), dealer.getLeft().getLeft(), 
				dealer.getLeft().getLeft().getLeft());
		chosenCard = gameDeck.getCard(chosenCardIndex);
		System.out.println("Flipped over card: " + chosenCard);
		wonLast = dealer.getLeft();
		sortHandsNoTrump();
	}
	
	private void clearPile()
	{
		for(int i = 0; i < pile.length; i++)
		{
			pile[i] = null;
		}
	}
	
	private void newHand()
	{
		dealer = dealer.getLeft();
		chosenCard = null;
		currentPlayer = dealer;
		pickedCard = null;
		firstLed = null;
		wonLast = dealer.getLeft();
		resetRoundScore();
	}
	
	private void newRound()
	{
		firstLed = null;
		clearPile();
		currentPlayer = wonLast;
		sortHands();
		playedCards = 0;
	}
	
	private void resetRoundScore()
	{
		for(Player p: playerList)
		{
			p.resetRoundScore();
		}
		teamOne.resetRoundScore();
		teamTwo.resetRoundScore();
	}
	
	private void sortHands()
	{
		for(Player p: playerList)
		{
			p.sortHand(trump);
		}
	}
	
	private void sortHandsNoTrump()
	{
		for(Player p: playerList)
			p.sortHand();
	}
	
	private void setTrump(Suit suit)
	{
		trump = suit;
	}
	
	private void pickCard()
	{
		int counter = 0;
		currentPlayer = dealer;
		while(pickedCard == null)
		{
			currentPlayer = currentPlayer.getLeft();
			if(counter > 2 && currentPlayer == dealer.getLeft())
				break;
			else
			{
				pickedCard = currentPlayer.chooseCard(chosenCard);
				System.out.println(currentPlayer.getName() + " has picked " + pickedCard);
			}
			counter += 1;
		}
	}
	
	private void chooseTrump()
	{
		currentPlayer = dealer;
		for(int i = 0; i < 4; i++)
		{
			currentPlayer = currentPlayer.getLeft();
			trump = currentPlayer.chooseTrump(notTrump);
			if(trump != null)
				break;
		}
		while(trump == null)
		{
			System.out.println("Stick the dealer!");
			trump = dealer.chooseTrump(notTrump);
		}
		
	}
	
	private void swapCard()
	{
		currentPlayer = dealer;
		Card temp = currentPlayer.swapCard(pickedCard);
		System.out.println("You dropped " + temp + " for " + pickedCard);
	}
	
	private void playHand()
	{
		if(currentPlayer.getTeam().getRoundScore() == 0 && 
				currentPlayer.getLeft().getTeam().getRoundScore() == 0)
		{
			System.out.println("new hand");
			currentPlayer = dealer.getLeft();
		}
		else
		{
			currentPlayer = wonLast;
		}
		System.out.println("Trump = " + trump);
		firstLed = currentPlayer.playCard(pile, playedCards, trump, firstLed);
		playedCards += 1;
		firstLedTrump = firstLed.isTrump(trump);
		if(firstLedTrump && firstLed.getValue().getValue() == 11)
			firstLedSuit = trump;
		else
			firstLedSuit =firstLed.getSuit();
		pile[0] = firstLed;
		System.out.println(currentPlayer.getName() + " Has played the " + pile[0]);
		currentPlayer.removeCard(pile[0]);
		Card temp = null;
		for(int i = 0; i < 3; i++)
		{
			currentPlayer = currentPlayer.getLeft();
			System.out.println("First led suit = " + firstLed.getSuit());
			System.out.println("Trump = " + trump.getSuit());
			if(currentPlayer.getSuit(firstLedSuit, trump))
			{
				temp = currentPlayer.playCard(pile, playedCards, trump, firstLed);
				//Doesn't work when you play the right
				while(temp.getSuit() != firstLedSuit || (temp.isTrump(trump) && firstLedSuit != trump))
				{
					
					if(temp.isTrump(trump) && firstLedSuit == trump)
						break;
					temp = currentPlayer.playCard(pile, playedCards, trump, firstLed);
				}
			}
			else
			{
				temp = currentPlayer.playCard(pile, playedCards, trump, firstLed);
			}
			System.out.println(currentPlayer.getName() + " Has played the " + temp);
			currentPlayer.removeCard(temp);

			pile[i + 1] = temp;
			temp = null;
			playedCards += 1;
		}
	}
	
	private void whoWon()
	{
		currentPlayer = wonLast;
		Player tempWon = null;
		int largest = 0;
		for(int i = 0; i < 4; i++)
		{
			
			System.out.println(currentPlayer.getName());
			System.out.println(pile[i].getValue(trump));
			if(pile[i].getSuit() != firstLed.getSuit() && 
					!(pile[i].isTrump(trump)))
			{ 
				currentPlayer = currentPlayer.getLeft();
				continue;
			}
			if(largest < pile[i].getValue(trump))
			{
				largest = pile[i].getValue(trump);
				tempWon = currentPlayer;
			}
			currentPlayer = currentPlayer.getLeft();
		}
		tempWon.incrementRoundScore();
		tempWon.getTeam().wonHand();
		
		System.out.println(tempWon.getName() + " Won the round and they now have " 
		+ (tempWon.getTeam().getRoundScore()) + " points");
		wonLast = tempWon;
	}
	
	private void whoWonRound()
	{
		Player greatestScorer = currentPlayer;
		for(Player p: playerList)
		{
			if(p.getRoundScore() > greatestScorer.getRoundScore() )
			{
				greatestScorer = p;
			}
		}
		
		if(greatestScorer.getRoundScore() + greatestScorer.getTeamMate().getRoundScore() == 5)
		{
			greatestScorer.getTeam().wonRoundEuchred();
		}
		else
		{
			greatestScorer.getTeam().wonRound();
		}
	}
	
	
	
}
