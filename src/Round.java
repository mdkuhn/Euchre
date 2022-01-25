

import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class Round {

  private IdealDeck ideal;  // used to do card-comparisons for each play
  private GameDeck deck;    // used to deal cards and hold the blind

  private Team team1;
  private Team team2;
  private Player dealer;
  private Player player2;
  private Player player3;
  private Player player4;
  private PlayerNode dealerNode;
  private PlayerNode player2Node;
  private PlayerNode player3Node;
  private PlayerNode player4Node;
  private PlayerNode lead;
  private PlayerNode bidder;
  private int currTeam1Tricks = 0;
  private int currTeam2Tricks = 0;
  private Suit trump;

  /**
   * Plays until all players are out of cards
   */
  public Round(IdealDeck ideal, Team team1, Team team2, Player player1, Player player2,
               Player player3,
               Player player4) {

    // private field assignment
    this.team1 = team1;
    this.team2 = team2;
    this.dealer = player1;
    this.player2 = player2;
    this.player3 = player3;
    this.player4 = player4;

    // construct circular list of players to simulate the table, and to help for throw rotations
    this.dealerNode = new PlayerNode(this.dealer);
    this.player2Node = new PlayerNode(this.player2);
    this.player3Node = new PlayerNode(this.player3);
    this.player4Node = new PlayerNode(this.player4);
    this.dealerNode.setNext(this.player2Node);
    this.player2Node.setNext(this.player3Node);
    this.player3Node.setNext(this.player4Node);
    this.player4Node.setNext(this.dealerNode);

    // how this works in my current approach: set up one ideal deck for the entire game to
    // continuously modify, and create a new game deck for each round played
    this.ideal = ideal;
    deck = new GameDeck();
    System.out.println(player1.getName() + " begins the deal...");
    this.Deal();
    this.getBids();
    this.playRound();

  }

  /**
   * Helper method used to facilitate bidding
   */
  public void getBids() {

    Scanner scnr = new Scanner(System.in);
    Random rand = new Random();
    // assuming that the cards have been dealt, show the next card, i.e. the first of the "blind"
    // for now, let me (or the person overseeing System.in) make all plays for player
    Card flipped = deck.pluck(3).get(0);
    // Below output example: The Ace of Spades is turned up.
    //                       Michael, would you like to play Hearts?
    System.out.println("The " + flipped.cardToString() + " is turned up.");
    this.bidder = dealerNode.getNext();
    boolean trumpSet = false;

    // go from player to player, asking if they would like play the trump turned up for the round
    while (!trumpSet) {
      // ask the bidder if they would like to set the trump for the round
      System.out.println(this.bidder.getPlayer().getName() + ", would you like to play " +
              flipped.suitToString() + "? (Enter show to see your cards)");
      String response = scnr.nextLine().replace(" ", "").toLowerCase();
      if (response.equals("show")) {
        bidder.getPlayer().handToString();
        System.out.println("\nWould you like to set the trump to " + flipped.suitToString() + "?");
        response = scnr.nextLine().replace(" ", "").toLowerCase();
      }
      // if they answer no, continue to next bidder
      if (!response.equals("yes")) {
        bidder = bidder.getNext();
        // case: dealer turns down the trump, exit the loop
        if (bidder.equals(dealerNode.getNext())) {
          System.out.println("The round's trump is up for bids.");
          break;
        }
      }
      // bidder answers yes
      else {
        this.trump = flipped.getSuit();
        trumpSet = true;
        System.out.println(this.trump.toString() + " will be trump.\n" +
                dealerNode.getPlayer().getName() + ", pick a card to swap with the " +
                flipped.cardToString() + " by entering its position in your hand (first, " +
                "second, ..., fifth)");
        // displays the hand
        System.out.println("Your hand:");
        dealer.handToString();
        // gets user response
        String swap = scnr.nextLine().replace(" ", "").toLowerCase();
        // swaps the card turned over with the card of user's choice
        if (swap.equals("first")) {
          dealer.cardSwap(0, flipped, deck);
        } else if (swap.equals("second")) {
          dealer.cardSwap(1, flipped, deck);
        } else if (swap.equals("third")) {
          dealer.cardSwap(2, flipped, deck);
        } else if (swap.equals("fourth")) {
          dealer.cardSwap(3, flipped, deck);
        } else if (swap.equals("fifth")) {
          dealer.cardSwap(4, flipped, deck);
        }
        // response could not be interpreted, swap random card from hand
        else {
          System.out.println("Couldn't understand the response, swapping random card...");
          dealer.cardSwap(rand.nextInt(5), flipped, deck);
        }
        System.out.println(dealerNode.getNext().getPlayer().getName() + ", your lead.");
        trumpSet = true;
      }
    }

    // next case: card flipped up is refused by all players, begin open bidding
    if (!trumpSet) {
      while (!trumpSet) {

        // rule: stick the dealer, i.e. have the dealer set the suit for the round
        if (bidder.equals(dealer)) {
          System.out.println("Dealer must select a suit. What would you like to play?");
          String response = scnr.nextLine().replace(" ", "").toLowerCase();
          this.trump = this.getSuit(response);
          if (this.trump == null) {
            System.out.println("Dealer may not pass. Randomly assigning a suit:");
            int random = rand.nextInt(4);
            if (random == 0) {
              this.trump = Suit.clubs;
            }
            if (random == 1) {
              this.trump = Suit.diamonds;
            }
            if (random == 2) {
              this.trump = Suit.hearts;
            }
            if (random == 3) {
              this.trump = Suit.hearts;
            }
            trumpSet = true;
            System.out.println(this.trump.toString() + " is trump. " +
                    dealerNode.getNext().getPlayer().getName() + ", your lead.");
          } else {
            trumpSet = true;
            System.out.println(this.trump.toString() + " is trump. " +
                    dealerNode.getNext().getPlayer().getName() + ", your lead.");
          }
        }

        // bidder not the dealer, proceed normally
        else {
          System.out.println(this.bidder.getPlayer().getName() + ", would you like to set the " +
                  "trump for the round, and if so what suit?");
          String response = scnr.nextLine().replace(" ", "").toLowerCase();
          if (response.equals("yes")) {
            System.out.println("And which suit?");
            response = scnr.nextLine().replace(" ", "").toLowerCase();
          }
          this.trump = getSuit(response);
          if (this.trump == null) {
            System.out.println(bidder.getPlayer().getName() + " passes.");
            bidder = bidder.getNext();
          } else {
            trumpSet = true;
            System.out.println(this.trump.toString() + " is trump. " +
                    dealerNode.getNext().getPlayer().getName() + ", your lead.");
          }
        }
        // end of loop
      }
      // end of trump assignment, establish the ideal deck's trump ranking
      ideal.setTrump(trump);
    }
    // end of bidding
  }

  /**
   * Deals each of the players their cards, first dealing three cards to all then dealing two cards
   */
  public void Deal() {
    deck.Shuffle();
    deck.firstHand(dealer);
    deck.firstHand(player2);
    deck.firstHand(player3);
    deck.firstHand(player4);
    deck.secondHand(dealer);
    deck.secondHand(player2);
    deck.secondHand(player3);
    deck.secondHand(player4);
  }

  /**
   * Driver for the round's play. Executes each of the five "tosses" (where players throw cards)
   * and ultimately assigns points to the winning team
   */
  private void playRound() {

    // counts which round of tosses the game round is currently in, starting at 1
    int playNumber = 1;

    try {
      // play 5 tosses
      while (playNumber <= 5) {
        // if on the first round, person to the "left" of the dealer leads. else the person who won
        // the last round leads
        // getThrows() retrieves all player throws and assigns the trick to the team
        if (playNumber == 1) {
          this.getThrows(dealerNode.getNext());
        } else {
          this.getThrows(this.lead);
        }

        // begin next round of tosses
        ++playNumber;
      }
    } catch (IllegalArgumentException iae) {
      System.out.println("Moving on to the next round");
    }

    // uses the number of tricks from team one to assign points from the round
    switch (this.currTeam1Tricks) {
      case 0:
        team2.addPoints(2);
      case 1:
        team2.addPoints(1);
      case 2:
        team2.addPoints(1);
      case 3:
        team1.addPoints(1);
      case 4:
        team1.addPoints(1);
      case 5:
        team1.addPoints(2);
    }
    currTeam1Tricks = 0;
    currTeam2Tricks = 0;
    dealer.clearHand();
    player2.clearHand();
    player3.clearHand();
    player4.clearHand();

  }

  /**
   * Collects plays from each player, beginning with the lead (who won the last round), and
   * assigns tricks to the teams
   *
   * @param lead player who throws first
   */
  public void getThrows(PlayerNode lead) {
    // method variable declarations
    Scanner scnr = new Scanner(System.in);
    LinkedList<Card> plays = new LinkedList<Card>();
    PlayerNode current = lead;  // organizes the turns for the round
    String play = "";
    Card bestPlay = null;
    Team winningTeam = null;

    for (int i = 0; i < 4; i++) {
      // the lead for the round will have already been announced. Assume valid input
      play = scnr.nextLine().replace(" ", "").toLowerCase();
      if (play.equals("show")) {
        current.getPlayer().handToString();
        play = scnr.nextLine().replace(" ", "").toLowerCase();
      }

      // renege check, i.e. if the play is not the same suit as the lead, the player has no other
      // cards
      // of the lead suit. Exclude this process from the lead
      if (i != 0 && !current.getPlayer().getCard(play).getSuit().equals(ideal.getLead())) {
        if (current.getPlayer().hasSuit(ideal.getLead())) {
          System.out.println("Player reneged, end of round");
          // determine which team the player belongs to
          if (team1.hasPlayer(current.getPlayer())) {
            System.out.println("Assigning two points to " + team2.getName());
            currTeam1Tricks = 0;
          }
          throw new IllegalArgumentException("Player reneged. End of round");
        }
        else {
          System.out.println("Assigning two points to " + team1.getName());
          currTeam1Tricks = 5;
          throw new IllegalArgumentException();
        }
      }

      // add the play to the collection of plays
      plays.add(current.getPlayer().getCard(play));

      // case: lead's play, set the lead suit of the round
      if (i == 0) {
        ideal.setLead(plays.get(0).getSuit());
        bestPlay = plays.get(0);
        if (team1.hasPlayer(current.getPlayer())) { winningTeam = team1; }
        else { winningTeam = team2; }
      }

      // case: not lead's play, determine if a new best play has been made
      else {
        if (ideal.indexOf(plays.get(i)) < ideal.indexOf(bestPlay)) {
          bestPlay = plays.get(i);
          if (team1.hasPlayer(current.getPlayer())) { winningTeam = team1; }
          else { winningTeam = team2; }
        }
      }

      // get the next play
      current = current.getNext();
      System.out.println(current.getPlayer().getName() + ", your turn.");

      }

    // determine which team to assign the trick
    if (winningTeam.equals(team1)) { ++currTeam1Tricks; }
    else { ++currTeam2Tricks; }

    }


  /**
   * Helper method which returns a suit from the given String input
   *
   * @param input String input
   * @return the Suit conversion of the input string, or null if the String does not match a Suit
   */
  public Suit getSuit(String input) {
    if (input == null) {
      return null;
    }
    if (input.equals(Suit.clubs.toString())) {
      return Suit.clubs;
    }
    if (input.equals(Suit.diamonds.toString())) {
      return Suit.diamonds;
    }
    if (input.equals(Suit.hearts.toString())) {
      return Suit.hearts;
    }
    if (input.equals(Suit.spades.toString())) {
      return Suit.spades;
    } else {
      return null;
    }
  }

}

// TODO // (1) work on how players will "play" cards, how points will be assigned, how the round is
// TODO // going to terminate
// TODO // (1 partial solution) game round will terminate after playing 5 rounds of tosses.
// TODO // (2) how to assign tricks to a team from outside the main while loop?
// TODO // (2 solution) make team1 and team2 tricks global class variables

// design decision: if the player plays an illegal card (reneges) - automatic two points?
// design decision: stick the dealer?
// design decision: farmer's hand?