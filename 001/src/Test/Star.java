package Test;

public class Star {

//	field
	
	private String Star = "*";
	private int MaxStar = 10;
	
//	constructor
	

//	method
	public void WriteStar(){
		
		for(int i = 1; i <= MaxStar; i++) {
			
			if(i <= (MaxStar/2)) {
				for(int j = 0; j < i; j++) {
					System.out.print(Star);
				}
			}
			else {
				for(int j = MaxStar; j > i; j--) {
					System.out.print(Star);
				}
			}
			
			if(i<MaxStar) {
				System.out.println("");
			}
			
		}
	}
	
	public void setMaxStar(int maxStar) {
		MaxStar = maxStar;
	}

}
