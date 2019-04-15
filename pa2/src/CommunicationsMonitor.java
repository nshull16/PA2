import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CommunicationsMonitor {
	
	private HashMap<Integer, List<ComputerNode>> map = new HashMap<Integer, List<ComputerNode>>();
	
	public CommunicationsMonitor(){
		
	}
	
	public void addCommunication(int c1, int c2, int timestamp){
		ComputerNode nodec1 = new ComputerNode(c1, timestamp);
		ComputerNode nodec2 = new ComputerNode(c2, timestamp);
		nodec1.addOutNeighbor(nodec1);
		nodec2.addOutNeighbor(nodec2);
		map.get(c1).add(nodec1);
		map.get(c2).add(nodec2);
	}
	
	public void createGraph(){
		
	}
	
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		List<ComputerNode> cnlist = new ArrayList<ComputerNode>();
		return cnlist;
	}
	
	public HashMap<Integer, List<ComputerNode>> getComputerMapping(){
		return this.map;
	}
	
	public List<ComputerNode> getComputerMapping(int c){
		return this.map.get(c);
	}
	
	
}
