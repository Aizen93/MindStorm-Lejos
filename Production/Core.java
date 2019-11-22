
import lejos.nxt.LCD;
import lejos.util.TextMenu;


public class Core {
    
    public static void main(String[] args) {
        String[] modes = {"Calibrate", "Line follower", "LineFollower3", "Memory road"};
        TextMenu modeMenu = new TextMenu(modes, 1, "MindStorm");
        LineColors lc = new LineColors();
        while (true) {
            int mode = modeMenu.select();
            if (mode < 0) return;
            LCD.clear();
            switch(mode){
                case 0:
                    lc.init();
                    break;
                case 1:
                    if (lc.isCalibrated()) {
                        LineFollower linefollower = new LineFollower(lc);
                        int line_to_follow = linefollower.colorToFollow();
                        linefollower.startRace(line_to_follow);
                    } else {
                        LCD.drawString("You need to\nCalibrate the\nsensor !", 0, 5);
                    }
                    break;
                case 2:
                    if (lc.isCalibrated()) {
                        LineFollower3 linefollower = new LineFollower3(lc);
                        int line_to_follow = linefollower.colorToFollow();
                        linefollower.startRace(line_to_follow);
                    } else {
                        LCD.drawString("You need to\nCalibrate the\nsensor !", 0, 5);
                    }
                    break;
                case 3:
                    //TO DO
                    break;
            }
        }
    }
}
