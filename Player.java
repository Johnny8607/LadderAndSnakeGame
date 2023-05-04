

public class Player {
	
	private String name;
	private int dice_value;
	private int position;
	
	public Player() {
		
	}
	
	public Player(String name, int dice_value) {
		this.name = name;
		this.dice_value = dice_value;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDiceValue() {
		return dice_value;
	}

	public int getPosition() {
		return position;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDiceValue(int dice_value) {
		this.dice_value = dice_value;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	

}
