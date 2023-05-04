
public class Features {
	
	protected int start_position;
	protected int end_position;
	
	public Features() {
		
	}
	
	public Features(int start_position, int end_position) {
		
		this.start_position = start_position;
		this.end_position = end_position;
		
	}
	public int getStartPosition() {
		
		return start_position;
		
	}
	
	public int getEndPosition() {
		return end_position;
		
	}
	
	public void setStartPosition(int start_position) {
		this.start_position = start_position;
	}
	
	public void setEndPosition(int end_position) {
		this.end_position = end_position;
	}

}
