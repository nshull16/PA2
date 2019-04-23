import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CommunicationsMonitor {
	
	private HashMap<Integer, List<ComputerNode>> map;
	private List<List<Integer>> triples;
	private boolean activeGraph;
	private boolean activeList;
	
	public CommunicationsMonitor(){
		map = new HashMap<Integer, List<ComputerNode>>();
		triples = new ArrayList<List<Integer>>();
		activeGraph = false;
		
	}
	
	public void addCommunication(int c1, int c2, int timestamp){
		if(!activeGraph){
			List<Integer> tripleAddition = new ArrayList<Integer>();
			tripleAddition.add(c1);
			tripleAddition.add(c2);
			tripleAddition.add(timestamp);
			triples.add(tripleAddition);
		}
		else{
			System.out.println("There's already a graph made.");
		}
	}
	
	public void createGraph(){
		if(triples.size() > 0){
			triples = MergeSort(triples);
			for(List<Integer> triple : triples){
				List<ComputerNode> c1List, c2List;
				ComputerNode c2Node = new ComputerNode(triple.get(1), triple.get(2));
				ComputerNode c1Node = new ComputerNode(triple.get(0), triple.get(2));
				boolean newC1Node = true, newC2Node = true;
				if(map.get(triple.get()) == null){
					c1List = new ArrayList<ComputerNode>();
				}
				else{
					c1List = map.get(triple.get(0));
					if(c1List.get(c1List.size() - 1).getTimeStamp() == triple.get(2)){
						c1Node = c1List.get(c1List.size() - 1);
						newC1Node = false;
					}
					else{
						c1List.get(c1List.size() - 1).addOutNeighbor(c1Node);
					}
				}
				if(map.get(triple.get(1)) == null){
					c2List = new ArrayList<ComputerNode>();
				}
				else{
					c1List.get(c1List.size() - 1).addOutNeighbor(c1Node);
				}
			}
			if(map.get(triple.get(1)) == null){
				c2List = new ArrayList<ComputerNode>();
			}
			else{
				c2List = map.get(triple.get(1));
				if(c2List.get(c2List.size() - 1).getTimeStamp() == triple.get(2)){
					c2Node = c2List.get(c2List.size() - 1);
					newC2Node = false;
				}
				else{
					c2List.get(c2List.size() - 1).addOutNeighbor(c2Node);
				}
			}
			c1Node.addOutNeighbor(c2Node);
			c2Node.addOutNeighbor(c1Node);
			if(newC1Node){
				c1List.add(c1Node);
			}
			if(newC2Node){
				c2List.add(c2Node);
			}
			map.put(triple.get(0), c1List);
			map.put(triple.get(1), c2List);
		}
		activeGraph = true;
	}
	
	public HashMap<Integer, List<ComputerNode>> getComputerMapping(){
		if(activeGraph){
			return map;
		}
		else{
			System.out.println("No active graph");
			return new HashMapM<Integer, List<ComputerNode>>();
		}
	}
	
	public List<ComputerNode> getComputerMapping(int c){
		if(activeGraph){
			if(map.get(c) != null){
				return map.get(c);
			}
			return new ArrayList<ComputerNode>();
		}
		else{
			System.out.println("No active graph");
			return new ArrayList<ComputerNode>();
		}
	}
	
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		ComputerNode endOfList = null;
		if(activeGraph){
			ArrayList<ComputerNode> list = new ArrayList<ComputerNode>();
			for(ComputerNode cn : mapping.get(c1)){
				if(cn.getTimestamp() >= x && !activeList){
					DFS(n, c2, y, list, endOfList);
					break;
				}
			}
			if(!list.isEmpty()){
				endOfList = list.get(0);
				while(endOfList.getPred() != null){
					endOfList = endOfList.getPred();
					list.add(endOfList);
				}
			}
			Collections.reverse(list);
			return list;
		}
		else{
			System.out.println("No active graph");
			return new ArrayList<ComputerNode>();
		}
	}
	
	
	public void DFS(ComputerNode n, int c2, int time, ArrayList<ComputerNode> list, ComputerNode endOfList){
		for(ComputerNode neighbor : n.getOutNeightbors()){
			neighbor.setColor(0);
			neighbor.setPred(null);
		}
		DFSVisit(n, c2, time, list, endOfList);
	}
	
	public void DFSVisit(ComputerNode n, int c2, int time, ArrayList<ComputerNode> list, ComputerNode endOfList){
		n.setColor(1);
		for(ComputerNode neighbor : n.getOutNeighbors()){
			if(neighbor.getID() == c2 && neighbor.getTimestamp() <= time && !listMade){
				neighbor.setPred(n);
				list.add(neighbor);
				activeList = true;
				return;
			}
			else if(neighbor.getColor() == 0){
				neighbor.setPred(n);
				DFSVisit(neighbor, c2, time, list, endOfList);
			}
		}
		n.setColor(2);
	}
	private List<List<Integer>> MergeSort(List<List<Integer>> list){
		int n = list.size();
		if(n == 1)
			return list;
		List<List<Integer>> first = list.subList(0, n/2);
		List<List<Integer>> second = lest.subList(n/2, n);
		return Merge(MergeSort(first), MergeSort(second));
	}
	
	private List<List<Integer>> Merge(List<List<Integer>> A, List<List<Integer>> B){
		List<List<Integer>> placeholder = new ArrayList<List<Integer>>();
		return placeholder;
	}
	
	
}
