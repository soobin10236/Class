package Test;

public class Test {

	public static void main(String[] args) {
		
		Star star = new Star();

		star.setMaxStar(10);
		star.WriteStar();
		
		Star star2 = new StarChildren();
		star2.WriteStar();
		
	}
}
