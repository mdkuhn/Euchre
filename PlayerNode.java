
public class PlayerNode {
  private Player player;
  private PlayerNode next;

  public PlayerNode(Player player) { this.player = player; }

  public PlayerNode(Player player, PlayerNode next) {
    this.player = player;
    this.next = next;
  }

  protected PlayerNode getNext() { return this.next; }

  protected Player getPlayer() { return this.player; }

  protected void setNext(PlayerNode next) { this.next = next; }

}
