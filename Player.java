
import java.util.Collections;
import java.util.LinkedList;

public class Player {

  private String playerName;
  private LinkedList<Card> hand = new LinkedList<>();

  public Player(String playerName) {
    this.playerName = playerName;
  }

  public String getName() { return this.playerName; }

  public void handToString() {
    System.out.print("[");
    for (int i = 0; i < 5; i++) {
      if (i != 4) {
        System.out.print(hand.get(i).cardToString() + ", ");
      } else {
        System.out.print(hand.get(i).cardToString() + "]");
      }
    }
  }

  /**
   * Builds the hand from a card-list input
   * @param deal the cards to add to the hand
   */
  public void addToHand(LinkedList<Card> deal) { hand.addAll(deal); }

  public void cardSwap(int index, Card card, GameDeck deck) {
    deck.setTop(hand.get(index));
    hand.set(index, card);
  }

  public void clearHand() { hand.clear(); }

  public Card getCard(String position) {
    // FIXME how to handle invalid input
    switch (position) {
      case ("first"): return this.hand.get(0);
      case ("second"): return this.hand.get(1);
      case ("third"): return this.hand.get(2);
      case ("fourth"): return this.hand.get(3);
      case ("fifth"): return this.hand.get(4);
    }
    return null;  // invalid input
  }

  public boolean hasSuit(Suit suit) {
    for (Card card: hand) {
      if (card.getSuit().equals(suit)) {
        return true;
      }
    }
    return false;
  }


}
