import java.util.List;

public class ComputerNode {
	
	private int id;
	private int timestamp;
	private List<ComputerNode> neighbors;
	public ComputerNode(int id, int timestamp){
		this.id = id;
		this.timestamp = timestamp;
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getTimeStamp(){
		return this.timestamp;
	}
	public List<ComputerNode> getOutNeighbors(){
		return this.neighbors;
	}
	
	public void addOutNeighbor(ComputerNode outNeighbor){
		neighbors.add(outNeighbor);
	}
}
