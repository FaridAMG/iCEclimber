package Logic;
import Json.JsonTest;

public class Game {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player player = new Player();
		player.setLives(10);
		player.setName("Popo");
		player.setPower(5);
		String jsonPlayer = JsonTest.convertJavaToJson(player);
		System.out.println(jsonPlayer);
		System.out.println("===========");
		Player player1 = JsonTest.convertJsonToJava(jsonPlayer, Player.class);
		System.out.println(player1.getName() + " " + player1.getLives() + " " + player1.getPower());
	}
}
