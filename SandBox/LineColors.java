import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;
import lejos.util.TextMenu;

public class LineColors {
	private static List<RGB> couleurs;
	private static int colorID;

	public LineColors() {
		couleurs = null;
		colorID = 0;
	}

	public boolean isCalibrated() {
		return this.colorID != 0;
	}

	public int getColorID() {
		return this.colorID;
	}

	public List<RGB> getColors() {
		return couleurs;
	}

	public static void teaching(int n) {
		for(int i = 0; i < n; i++) {
			LCD.clear();
        	LCD.drawString("Calibrate color", 0, 0);
        	LCD.drawInt(i, 0, 1);
			Button.waitForAnyPress();
			teachColors();
		}
	}

	public static void teachColors() {
		ColorSensor cs = new ColorSensor(SensorPort.S1);
        Color colorr;

        RGB[] rgb = new RGB[20];
        for(int i = 0; i < 20; i++) {
        	LCD.drawString("Calibrating (20)", 0, 2);
        	LCD.drawInt(i, 0, 3);
        	if(i == 10) {
        		Button.waitForAnyPress();
        	}
            colorr = cs.getColor();
            LCD.drawString("+("+colorr.getRed()+" "+colorr.getGreen()+" "+colorr.getBlue()+")", 0, 4);

            int red = colorr.getRed();
            int green = colorr.getGreen();
            int blue = colorr.getBlue();

            rgb[i] = new RGB(red, green, blue);
        }
        Button.waitForAnyPress();
        RGB res = centroid(rgb);
        LCD.drawString("++("+res.getRouge()+" "+res.getVert()+" "+res.getBleu()+")", 0, 3);

        couleurs.add(res);
	}

	public static void TestColorTeached() {
		LCD.clear();
		ColorSensor cs = new ColorSensor(SensorPort.S1);
        Color couleur;
        Button.waitForAnyPress();
        couleur = cs.getColor();
        double distMin = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0; i < couleurs.size(); i++) {
			LCD.drawString(i+" " + couleurs.size(), 0, 3);
			double dist = couleurs.get(i).compareInteger(couleur.getRed(), couleur.getGreen(), couleur.getBlue());
			LCD.drawString(" " + dist, 0, 4);
			if(dist <= 70) {
				if(dist < distMin) {
					distMin = dist;
					index = i;
				}
			}
        }
        if(index == 0){
            LCD.drawString("Color: " + index, 0, 0);
        }else{
            LCD.drawString("Color:" + index, 0, 0);
        }
        LCD.drawString("--"+couleur.getRed() +" "+ couleur.getGreen() +" "+couleur.getBlue(), 0, 1);
        Button.waitForAnyPress();
	}

	public int getColorSeenID() {
		ColorSensor cs = new ColorSensor(SensorPort.S1);
		Color color = cs.getColor();
		int index = -1;
		double distMin = Double.MAX_VALUE;
		for(int i = 0; i < couleurs.size(); i++) {
			double dist = couleurs.get(i).compareInteger(color.getRed(), color.getGreen(), color.getBlue());
			if(dist <= 100) {
				if(dist < distMin) {
					distMin = dist;
					index = i;
				}
			}
		}
		return index;
	}

	public static RGB centroid(RGB[] tab) {
		int red = tab[0].getRouge(), green = tab[0].getVert(), blue = tab[0].getBleu();
		for(int i = 1; i < 20; i++) {
			red = ((i * red) + tab[i].getRouge())/(i+1);
			green = ((i * green) + tab[i].getVert())/(i+1);
			blue = ((i * blue) + tab[i].getBleu())/(i+1);
		}
		return new RGB(red, green, blue);
	}

	public void init() {
		String[] modes = {"teaching", "Test colors"};
		TextMenu modeMenu=new TextMenu(modes,1,"Calibrating Color");

		while(Button.ESCAPE.isUp()) {
			int mode = modeMenu.select();
		    if (mode < 0) {
				LCD.clear();
		    	return;
			}
		    LCD.clear();
		    if(mode == 0) {
		    	String[] modes2 = {"2", "3"};
	    		TextMenu modeMenu2 = new TextMenu(modes2,1,"Colors number");
	    		if(couleurs == null) {
	    			couleurs = new ArrayList<RGB>();
			    	while(Button.ESCAPE.isUp()) {
			    		LCD.clear();
			    		int mode2=modeMenu2.select();
			    		switch(mode2) {
			    			case 0:
			    				colorID = 2;
			    				teaching(2);
			    				break;
			    			case 1:
			    				colorID = 3;
			    				teaching(3);
			    				break;

			    			default:
			    				break;
			    		}
			    		LCD.clear();
			    		break;
			    	}
	    		} else {
	    			LCD.clear();
	    			LCD.drawString("nothing to do\nReady to go !", 0, 0);
	    			Button.waitForAnyPress();
	    		}
		    } else if(mode == 1) {
		    	TestColorTeached();
		    }
		    LCD.clear();
		}
		LCD.clear();
	}
}
