
public class RoundDriverTester {

  public static void main(String[] args) {
    Player player1 = new Player("Michael");
    Player player2 = new Player("David");
    Player player3 = new Player("Kuhn");
    Player player4 = new Player("Juri");
    Team team1 = new Team(player1, player2);
    Team team2 = new Team(player3, player4);
    IdealDeck ideal = new IdealDeck();

    // the round method creates a new game deck, only visible within the class, shuffles it, and
    // deals out the cards. testing this:
    printRules();
    try {
      new Round(ideal, team1, team2, player1, player2, player3, player4);
    } catch (IndexOutOfBoundsException iobe) {
      System.out.println("Not that hard to just deal some cards...");
    }
  }

    public static void printRules() {
      System.out.println("Welcome to Euchre!");
      System.out.println("The game will continue until one team has reached ten points");
      System.out.println("All plays will be made based on the position of the card in your deck. " +
              "To make a play, enter its position (first, second, third, fourth, fifth), and to " +
              "show your deck simply enter \"show\"");
      System.out.println("Now testing a round");
    }
}

