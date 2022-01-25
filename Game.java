
import java.util.*;

public class Game {

  private static void gameDriver() {

    // game setup
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Euchre table!");

    // create player variables, assigned when user determines how teams are set
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    // create teams
    Team team1;
    Team team2;

    // set up ideal deck
    IdealDeck ideal = new IdealDeck();

    // ask the player if they would like to randomly or manually assign teams
    System.out.print("Would you like to randomly assign teams? (enter 'yes' or 'no')");
    String randomResponse = scanner.nextLine();

    if (!randomResponse.equals("no")) {
      // assign randomly
      System.out.print("Who's all playing today? (enter as a comma-separated list)");
      String[] names = scanner.nextLine().split(",");
      System.out.println("Randomly assigning teams!");
      Random randGen = new Random();

      // create new Set of unique random values to assign teams, convert to ArrayList
      Set<Integer> set = new LinkedHashSet<Integer>();
      while (set.size() < 4) {
        set.add(randGen.nextInt(4));
      }
      ArrayList<Integer> intSet = new ArrayList<>(set);

      // assign teams by random values in set
      player1 = new Player(names[intSet.get(0)]);
      player2 = new Player(names[intSet.get(1)]);
      player3 = new Player(names[intSet.get(2)]);
      player4 = new Player(names[intSet.get(3)]);
      team1 = new Team(player1, player2);
      team2 = new Team(player3, player4);

      // show the user who the teams are
      System.out.println("Team One: " + team1.getPlayers());
      System.out.println("Team Two: " + team2.getPlayers());
    }
    else {
      // assign teams by user-input
      System.out.println("Who will be on Team One?");
      String[] TeamOne = scanner.nextLine().split(",");
      player1 = new Player(TeamOne[0]);
      player2 = new Player(TeamOne[1]);
      System.out.println("And Team Two?");
      String[] TeamTwo = scanner.nextLine().split(",");
      player3 = new Player(TeamTwo[0]);
      player4 = new Player(TeamTwo[1]);
      team1 = new Team(player1, player2);
      team2 = new Team(player3, player4);
    }

    // assign team names
    System.out.println("One more step before the game starts: ");
    System.out.print("What will Team One be named?");
    String teamOneName = scanner.nextLine();
    team1.setName(teamOneName);
    System.out.print("And how about Team Two?");
    String teamTwoName = scanner.nextLine();
    team2.setName(teamTwoName);

    // game play
    System.out.println("Let's get started!");
    // need to establish a "round counter" so that player turns may rotate
    int roundNumber = 0;
    while (team1.getPoints() < 10 && team2.getPoints() < 10) {
      switch (roundNumber % 4) {
        case (0):
          new Round(ideal, team1, team2, player1, player3, player2, player4);
        case(1):
          new Round(ideal, team1, team2, player3, player2, player4, player1);
        case(2):
          new Round(ideal, team1, team2, player2, player4, player1, player3);
        case(3):
          new Round(ideal, team1, team2, player4, player1, player3, player2);
      }
      ++roundNumber;
    }

  }

  public static void main(String[] args) {
    gameDriver();
  }

}
