/**
 * Class RGb colors red, green, blue used by LineColors
 * @author Oussama, Achraf
 */
class RGB {

	private final int red;
	private final int blue;
	private final int green;

	public RGB(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int sqr(int x){
		return x * x;
	}

	public int getRouge() {
		return red;
	}

	public int getBleu() {
		return blue;
	}

	public int getVert() {
		return green;
	}

	public String getStringRGBmin() {
		return getRouge() + " " + getVert() + " " + getBleu() + "\n";
	}
	

	public double compareInteger(int r, int g, int b) {
		int dist_r = r - red;
		int dist_g = g - green;
		int dist_b = b - blue;

		if(Math.abs(dist_r) >= 55 || Math.abs(dist_g) >= 55 || Math.abs(dist_b) >= 55) {
			return Double.MAX_VALUE;
		}

		return Math.sqrt(sqr(dist_r) + sqr(dist_g) + sqr(dist_b));//la distance entre les couleurs capté
	}
}