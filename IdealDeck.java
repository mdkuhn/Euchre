

import java.util.Collections;
import java.util.LinkedList;

/**
 * Creates a deck which may used to compare player cards and facilitate gameplay
 *
 */
public class IdealDeck {

  private Suit trump;
  private Suit lead;
  private LinkedList<Card> idealDeck;

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


  public IdealDeck() {
    idealDeck = new LinkedList<>();

    // brute-force define and add all 24 cards, then shuffle
    idealDeck.add(AceHearts);
    idealDeck.add(AceClubs);
    idealDeck.add(AceDiamonds);
    idealDeck.add(AceSpades);

    idealDeck.add(KingHearts);
    idealDeck.add(KingClubs);
    idealDeck.add(KingDiamonds);
    idealDeck.add(KingSpades);

    idealDeck.add(QueenHearts);
    idealDeck.add(QueenClubs);
    idealDeck.add(QueenDiamonds);
    idealDeck.add(QueenSpades);

    idealDeck.add(JackHearts);
    idealDeck.add(JackClubs);
    idealDeck.add(JackDiamonds);
    idealDeck.add(JackSpades);

    idealDeck.add(TenHearts);
    idealDeck.add(TenClubs);
    idealDeck.add(TenDiamonds);
    idealDeck.add(TenSpades);

    idealDeck.add(NineHearts);
    idealDeck.add(NineClubs);
    idealDeck.add(NineDiamonds);
    idealDeck.add(NineSpades);

    Shuffle();
  }

  public void setTrump(Suit trump) {
    // changes the linked list ranking cards to set the 6 strongest to the trump suit
    this.trump = trump;
    this.TrumpRank();
  }

  public Suit getTrump() { return this.trump; }

  public void setLead(Suit lead) {
    // changes the linked list to set the next 6 strongest cards by lead (after trump)
    this.lead = lead;
    this.LeadRank();
  }

  public Suit getLead() { return this.lead; }

  /**
   * Sorts the deck by strength after setting the trump
   */
  public void TrumpRank() {
    boolean swapped = false;

    switch (trump) {
      // manually place 6 most powerful cards in the first 6 positions based on trump
      case diamonds:
        Collections.swap(idealDeck, 0, idealDeck.indexOf(JackDiamonds));
        Collections.swap(idealDeck, 1, idealDeck.indexOf(JackHearts));
        Collections.swap(idealDeck, 2, idealDeck.indexOf(AceDiamonds));
        Collections.swap(idealDeck, 3, idealDeck.indexOf(KingDiamonds));
        Collections.swap(idealDeck, 4, idealDeck.indexOf(QueenDiamonds));
        Collections.swap(idealDeck, 5, idealDeck.indexOf(TenDiamonds));
        Collections.swap(idealDeck, 6, idealDeck.indexOf(NineDiamonds));
        swapped = true;

      case spades:
        if (!swapped) {
          Collections.swap(idealDeck, 0, idealDeck.indexOf(JackSpades));
          Collections.swap(idealDeck, 1, idealDeck.indexOf(JackClubs));
          Collections.swap(idealDeck, 2, idealDeck.indexOf(AceSpades));
          Collections.swap(idealDeck, 3, idealDeck.indexOf(KingSpades));
          Collections.swap(idealDeck, 4, idealDeck.indexOf(QueenSpades));
          Collections.swap(idealDeck, 5, idealDeck.indexOf(TenSpades));
          Collections.swap(idealDeck, 6, idealDeck.indexOf(NineSpades));
          swapped = true;
        }

      case clubs:
        if (!swapped) {
          Collections.swap(idealDeck, 0, idealDeck.indexOf(JackClubs));
          Collections.swap(idealDeck, 1, idealDeck.indexOf(JackSpades));
          Collections.swap(idealDeck, 2, idealDeck.indexOf(AceClubs));
          Collections.swap(idealDeck, 3, idealDeck.indexOf(KingClubs));
          Collections.swap(idealDeck, 4, idealDeck.indexOf(QueenClubs));
          Collections.swap(idealDeck, 5, idealDeck.indexOf(TenClubs));
          Collections.swap(idealDeck, 6, idealDeck.indexOf(NineClubs));
          swapped = true;
        }

      case hearts:
        if (!swapped) {
          Collections.swap(idealDeck, 0, idealDeck.indexOf(JackHearts));
          Collections.swap(idealDeck, 1, idealDeck.indexOf(JackDiamonds));
          Collections.swap(idealDeck, 2, idealDeck.indexOf(AceHearts));
          Collections.swap(idealDeck, 3, idealDeck.indexOf(KingHearts));
          Collections.swap(idealDeck, 4, idealDeck.indexOf(QueenHearts));
          Collections.swap(idealDeck, 5, idealDeck.indexOf(TenHearts));
          Collections.swap(idealDeck, 6, idealDeck.indexOf(NineHearts));
          swapped = true;
        }
    }

  }

  /**
   * Sorts the deck by strength after setting the lead
   */
  public void LeadRank() {
    switch (lead) {
      // manually place next 6-7 (depending on trump suit) most powerful cards in the next 6-7
      // positions based on lead

      case diamonds:
        if (trump.equals(Suit.diamonds)) {
          // do nothing here: the cards are already ranked in sorted order
        }
        // insert first three cards ranked until the jack
        Collections.swap(idealDeck, 7, idealDeck.indexOf(AceDiamonds));
        Collections.swap(idealDeck, 8, idealDeck.indexOf(KingHearts));
        Collections.swap(idealDeck, 9, idealDeck.indexOf(QueenDiamonds));
        // if hearts is trump, do not move the jack of diamonds
        if (trump.equals(Suit.hearts)) {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(TenDiamonds));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(NineDiamonds));
        }
        // hearts not trump, include all in suit
        else {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(JackDiamonds));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(TenDiamonds));
          Collections.swap(idealDeck, 12, idealDeck.indexOf(NineDiamonds));
          break;
        }

      case spades:
        if (trump.equals(Suit.spades)) {
          // do nothing here: the cards are already ranked in sorted order
        }
        // insert first three cards ranked until the jack
        Collections.swap(idealDeck, 7, idealDeck.indexOf(AceSpades));
        Collections.swap(idealDeck, 8, idealDeck.indexOf(KingSpades));
        Collections.swap(idealDeck, 9, idealDeck.indexOf(QueenSpades));
        // if clubs is trump, do not move the jack of diamonds
        if (trump.equals(Suit.clubs)) {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(TenClubs));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(NineClubs));
        }
        // clubs not trump, include all in suit
        else {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(JackClubs));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(TenClubs));
          Collections.swap(idealDeck, 12, idealDeck.indexOf(NineClubs));
          break;
        }


      case clubs:
        if (trump.equals(Suit.clubs)) {
          // do nothing here: the cards are already ranked in sorted order
        }
        // insert first three cards ranked until the jack
        Collections.swap(idealDeck, 7, idealDeck.indexOf(AceClubs));
        Collections.swap(idealDeck, 8, idealDeck.indexOf(KingClubs));
        Collections.swap(idealDeck, 9, idealDeck.indexOf(QueenClubs));
        // if clubs is trump, do not move the jack of diamonds
        if (trump.equals(Suit.spades)) {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(TenClubs));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(NineClubs));
        }
        // clubs not trump, include all in suit
        else {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(JackClubs));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(TenClubs));
          Collections.swap(idealDeck, 12, idealDeck.indexOf(NineClubs));
          break;
        }


      case hearts:
        if (trump.equals(Suit.hearts)) {
          // do nothing here: the cards are already ranked in sorted order
        }
        // insert first three cards ranked until the jack
        Collections.swap(idealDeck, 7, idealDeck.indexOf(AceHearts));
        Collections.swap(idealDeck, 8, idealDeck.indexOf(KingHearts));
        Collections.swap(idealDeck, 9, idealDeck.indexOf(QueenHearts));
        // if clubs is trump, do not move the jack of diamonds
        if (trump.equals(Suit.diamonds)) {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(TenHearts));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(NineHearts));
        }
        // clubs not trump, include all in suit
        else {
          Collections.swap(idealDeck, 10, idealDeck.indexOf(JackHearts));
          Collections.swap(idealDeck, 11, idealDeck.indexOf(TenHearts));
          Collections.swap(idealDeck, 12, idealDeck.indexOf(NineHearts));
          break;
        }
    }
  }

  public int indexOf(Card card) { return idealDeck.indexOf(card); }

  public void Shuffle() {
    Collections.shuffle(idealDeck);
  }


}

// TODO: how to account for cards being removed?
// first solution attempt: create two separate decks within the round: one "game deck" which may be
// removed from and dealt out, and then one "ideal deck" which is primarily used to sort the
// cards in order of strength

