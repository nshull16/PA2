import java.util.List;

public class ComputerNode {
	
	private int id;
	private int timestamp;
	private List<ComputerNode> outNeighbors;
	private int color;
	private ComputerNode pred;
	
	public ComputerNode(int id, int timestamp){
		this.id = id;
		this.timestamp = timestamp;
		outNeighbors = new ArrayList<ComputerNode>();
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getTimeStamp(){
		return this.timestamp;
	}
	public List<ComputerNode> getOutNeighbors(){
		return this.outNeighbors;
	}
	
	public void addOutNeighbor(ComputerNode outNeighbor){
		neighbors.add(outNeighbor);
	}
	
	public int getColor(){
		return this.color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public ComputerNode getPred(){
		return this.pred;
	}
	
	public void setPred (ComputerNode pred){
		this.pred = pred;
	}
}
