
public class Team {

  private final Player player1;
  private final Player player2;
  private int points;
  private String name;

  /**
   * Constructor for the Team class, creates a new Team
   * @param player1 member of the team
   * @param player2 another member of the team
   */
  public Team(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  public String getPlayers() {
    return player1.getName() + " and " + player2.getName();
  }
  public void setName(String name) {
    this.name = name;
  }

  public void addPoints(int points) { this.points += points; }

  /**
   * Getter method for the number of team points thus far
   * @return private field points
   */
  public int getPoints() { return this.points; }

  public boolean hasPlayer(Player player) {
    return player1.equals(player) || player2.equals(player);
  }

  public String getName() { return this.name; }


}
