
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.TextMenu;

public class LineFollower {
    private LineColors cl;
    private DifferentialPilot pilot;

    /**
     * Constructor of the line follower
     *
     * @param lineColor the line color initialised
     */
    public LineFollower(LineColors lineColor) {
        this.cl = lineColor;
        this.pilot = new DifferentialPilot(1.745896, 4.98, Motor.C, Motor.A);
    }

    /**
     * this function asks for the ID of the color to follow
     * Graphic interface
     *
     * @return return ID
     */
    public int colorToFollow() {
        String[] colorSelect = new String[cl.getColorID()];
        for (int i = 0; i < colorSelect.length; i++)
            colorSelect[i] = ""+i;

        // Menu
        TextMenu modeMenu = new TextMenu(colorSelect, 1, "Color to follow");
        while(Button.ESCAPE.isUp()) {
            LCD.clear();
            int mode = modeMenu.select();
            switch(mode) {
                case 0:
                    LCD.clear();
                    return 0;
                case 1:
                    LCD.clear();
                    return 1;

                default:
                    break;
            }
            LCD.clear();
            break;
        }
        return -1;
    }

    public void motorSetSpeed(int motorRightA, int motorLeftC){
        Motor.A.setSpeed(motorRightA);
        Motor.C.setSpeed(motorLeftC);
    }

    /**
     * Line Follower Algorithm.
     *
     * @param colorToFollow int of the line color to follow
     */
    public void startRace(int colorToFollow) {
        int speed_droite = 700;
        int speed_gauche = 700;
        boolean turn = false;

        LCD.clear();
        LCD.drawString("Robocop lightning\ndeployed !!",0,2);

        while(Button.ESCAPE.isUp()){
            if(cl.getColorSeenID() == colorToFollow){
                motorSetSpeed(speed_droite, speed_gauche);
                Motor.A.forward();
                Motor.C.forward();
            }else{//pas la meme couleur donc il va chercher la couleur
                if(turn){//tourner a gauche -- turn=true c'est gauche -- turn=false c'est droite
                    int c = 0;
                    int cc = 0;
                    while(true){
                        motorSetSpeed(450, 450);
                        Motor.A.backward();
                        Motor.C.forward();
                        cc++;
                        if(cl.getColorSeenID() == colorToFollow){
                            turn = false;
                            c = 1;
                            break;
                        }
                        if(cc == 100) break;//ancienne valeur 100
                    }
                    if(c == 1){
                        continue;
                    }
                    c = 0;
                    cc = 0;
                    while(true){
                        motorSetSpeed(450, 450);
                        Motor.A.forward();
                        Motor.C.backward();
                        cc++;
                        if(cl.getColorSeenID() == colorToFollow){
                            turn = true;
                            c = 1;
                            break;
                        }
                        if(cc == 130) break;//ancienne valeur 150
                    }
                    if (c == 1){
                        continue;
                    } 
                }else{
                    int c = 0;
                    int cc = 0;
                    while(true){
                        motorSetSpeed(450, 450);
                        Motor.C.backward();
                        Motor.A.forward();
                        cc++;
                        if(cl.getColorSeenID() == colorToFollow){
                            turn = true;
                            c = 1;
                            break;
                        }
                        if(cc == 100) break;
                    }
                    if(c == 1){
                        continue;
                    } 
                    c = 0;
                    cc = 0;
                    while(true){
                        motorSetSpeed(450, 450);
                        Motor.C.forward();
                        Motor.A.backward();
                        cc++;
                        if(cl.getColorSeenID() == colorToFollow){
                            turn = false;
                            c = 1;
                            break;
                        }
                        if(cc == 130) break;
                    }
                    if (c == 1){
                        continue;
                    } 
                }       
            }
        }
        LCD.drawString("Robocop out\nof line!",0,3);
        pilot.stop();
        Button.waitForAnyPress();
        LCD.clear();
    }
}