

import java.util.*;

/**
 * Creates a deck which may used to facilitate gameplay and dealing, but not comparisons. This
 * deck may be removed from and shuffled.
 *
 */
public class GameDeck {

  private Suit trump;
  private Suit lead;
  private LinkedList<Card> gameDeck;
  private int currCard;  // private counting variable used in the pluck() method

  // brute-force define all 24 cards to add to the ideal deck

  private final Card AceHearts = new Card("Ace", Suit.hearts);
  private final Card AceClubs = new Card("Ace", Suit.clubs);
  private final Card AceDiamonds = new Card("Ace", Suit.diamonds);
  private final Card AceSpades = new Card("Ace", Suit.spades);

  private final Card KingHearts = new Card("King", Suit.hearts);
  private final Card KingClubs = new Card("King", Suit.clubs);
  private final Card KingDiamonds = new Card("King", Suit.diamonds);
  private final Card KingSpades = new Card("King", Suit.spades);

  private final Card QueenHearts = new Card("Queen", Suit.hearts);
  private final Card QueenClubs = new Card("Queen", Suit.clubs);
  private final Card QueenDiamonds = new Card("Queen", Suit.diamonds);
  private final Card QueenSpades = new Card("Queen", Suit.spades);

  private final Card JackHearts = new Card("Jack", Suit.hearts);
  private final Card JackClubs = new Card("Jack", Suit.clubs);
  private final Card JackDiamonds = new Card("Jack", Suit.diamonds);
  private final Card JackSpades = new Card("Jack", Suit.spades);

  private final Card TenHearts = new Card("Ten", Suit.hearts);
  private final Card TenClubs = new Card("Ten", Suit.clubs);
  private final Card TenDiamonds = new Card("Ten", Suit.diamonds);
  private final Card TenSpades = new Card("Ten", Suit.spades);

  private final Card NineHearts = new Card("Nine", Suit.hearts);
  private final Card NineClubs = new Card("Nine", Suit.clubs);
  private final Card NineDiamonds = new Card("Nine", Suit.diamonds);
  private final Card NineSpades = new Card("Nine", Suit.spades);


  public GameDeck() {
    gameDeck = new LinkedList<>();

    // brute-force define and add all 24 cards, then shuffle
    gameDeck.add(AceHearts);
    gameDeck.add(AceClubs);
    gameDeck.add(AceDiamonds);
    gameDeck.add(AceSpades);

    gameDeck.add(KingHearts);
    gameDeck.add(KingClubs);
    gameDeck.add(KingDiamonds);
    gameDeck.add(KingSpades);

    gameDeck.add(QueenHearts);
    gameDeck.add(QueenClubs);
    gameDeck.add(QueenDiamonds);
    gameDeck.add(QueenSpades);

    gameDeck.add(JackHearts);
    gameDeck.add(JackClubs);
    gameDeck.add(JackDiamonds);
    gameDeck.add(JackSpades);

    gameDeck.add(TenHearts);
    gameDeck.add(TenClubs);
    gameDeck.add(TenDiamonds);
    gameDeck.add(TenSpades);

    gameDeck.add(NineHearts);
    gameDeck.add(NineClubs);
    gameDeck.add(NineDiamonds);
    gameDeck.add(NineSpades);

    Shuffle();
  }

  public void Shuffle() {
    Collections.shuffle(gameDeck);
  }

  /**
   * Need to make this an exclusive pluck, i.e. the method pulls 20 unique random numbers from 0-23
   *
   * @param pass indicates whether this deal is the first pass (1, deal 3 cards), the second
   *             (2, deal two cards), or simply showing the next card (for trump bidding)
   */
  public LinkedList<Card> pluck(int pass) {
    LinkedList<Card> cards = new LinkedList<>();

    // case: first pass around, deal three cards
    if (pass == 1) {
      // pull three cards off the top of the randomly constructed deck
      cards.add(gameDeck.remove(currCard));
      cards.add(gameDeck.remove(currCard));
      cards.add(gameDeck.remove(currCard));
    }
    // case: second pass around, deal two cards
    if (pass == 2) {
      cards.add(gameDeck.remove(currCard));
      cards.add(gameDeck.remove(currCard));
    }
    // case: want to see the next card. no need to increment currCard
    if (pass == 3) {
      cards.add(gameDeck.get(currCard));
    }

    return cards;

  }

  public void firstHand(Player player) {
    player.addToHand(pluck(1));

  }

  public void secondHand(Player player) {
    player.addToHand(pluck(2));
  }

  public void setTop(Card card) {
    gameDeck.set(0, card);
  }

}

// TODO: how to account for cards being removed?
// first solution attempt: create two separate decks within the round: one "game deck" which may be
// removed from and dealt out, and then one "ideal deck" which is primarily used to sort the
// cards in order of strength



