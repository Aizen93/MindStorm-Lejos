import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class Hello {
  public static void main (String[] args) {
    System.out.println("Hello World !");
	LCD.drawString("Hello \nMy \nWorld !", 0, 3);
    Button.waitForAnyPress();
  }
}