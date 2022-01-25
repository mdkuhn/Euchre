
/**
 * Defines a card object from 24 options (9-Ace of Hearts, Clubs, Diamonds, Spades)
 */
public class Card {
  private Suit suit;
  private String name;

  public Card(String name, Suit suit) {
    this.name = name;
    this.suit = suit;
  }

  public Suit getSuit() { return this.suit; }

  public String suitToString() { return "" + this.suit; }

  public String cardToString() { return "" + name + " of " + suit; }

}
