import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

/**
 * 
 * @author Nathan Shull, Tyler Krueger
 *
 */
public class CommunicationsMonitor {
	
	private HashMap<Integer, List<ComputerNode>> map;
	private List<List<Integer>> triples;
	private boolean activeGraph;
	
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
	}
	
	public void createGraph() {
        if (triples.size() > 0) {
            triples = MergeSort(triples);
            for (List<Integer> triple : triples) {
                List<ComputerNode> c1List, c2List;
                ComputerNode c2Node = new ComputerNode(triple.get(1), triple.get(2));
                ComputerNode c1Node = new ComputerNode(triple.get(0), triple.get(2));
                boolean newC1Node = true;
                boolean newC2Node = true;
                if (map.get(triple.get(0)) == null) {
                    c1List = new ArrayList<ComputerNode>();
                } else {
                    c1List = map.get(triple.get(0));
                    if (c1List.get(c1List.size() - 1).getTimestamp() == triple.get(2)) {
                        c1Node = c1List.get(c1List.size() - 1);
                        newC1Node = false;
                    } else {
                        c1List.get(c1List.size() - 1).addOutNeighbor(c1Node);
                    }
                }
                if (map.get(triple.get(1)) == null) {
                    c2List = new ArrayList<ComputerNode>();
                } else {
                    c2List = map.get(triple.get(1));
                    if (c2List.get(c2List.size() - 1).getTimestamp() == triple.get(2)) {
                        c2Node = c2List.get(c2List.size() - 1);
                        newC2Node = false;
                    } else {
                        c2List.get(c2List.size() - 1).addOutNeighbor(c2Node);
                    }
                }
                c1Node.addOutNeighbor(c2Node);
                c2Node.addOutNeighbor(c1Node);
                if (newC1Node) {
                    c1List.add(c1Node);
                }
                if (newC2Node) {
                    c2List.add(c2Node);
                }
                map.put(triple.get(0), c1List);
                map.put(triple.get(1), c2List);
            }
        }
        activeGraph = true;
	}
	
	public HashMap<Integer, List<ComputerNode>> getComputerMapping(){
		if(activeGraph){
			return map;
		}
		else{
			return null;
		}
	}
	
	public List<ComputerNode> getComputerMapping(int c){
		if(activeGraph){
			return map.get(c);
		}
		else{
			return null;
		}
	}
	
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		if(activeGraph){
			ArrayList<ComputerNode> list = new ArrayList<ComputerNode>();
			ComputerNode lastNode = null;
			if(map.get(c1) != null){
				for(ComputerNode cn : map.get(c1)){
					if(cn.getTimestamp() >= x){
						lastNode = DFS(cn, c2, y);
						break;
					}
				}
			}
			if(lastNode != null){
				list.add(lastNode);
				while(lastNode.getPred() != null){
					lastNode = lastNode.getPred();
					list.add(lastNode);
				}
				Collections.reverse(list);
				return list;
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
	
	
	public ComputerNode DFS(ComputerNode n, int c2, int time){
		n.setColor(0);
		n.setPred(null);
		initDFS(n);
		if(n.getID() == c2 && n.getTimestamp() <= time){
			return n;
		}
		return DFSVisit(n, c2, time);
	}
	
	public ComputerNode DFSVisit(ComputerNode n, int c2, int time){
		n.setColor(1);
		for(ComputerNode neighbor : n.getOutNeighbors()){
			if(neighbor.getID() == c2 && neighbor.getTimestamp() <= time){
				neighbor.setPred(n);
				return neighbor;
			}
			else if(neighbor.getColor() == 0){
				neighbor.setPred(n);
				ComputerNode returnedNode = DFSVisit(neighbor, c2, time);
				if(returnedNode != null){
					return returnedNode;
				}
			}
		}
		n.setColor(2);
		return null;
	}
	
	private void initDFS(ComputerNode n){
		for(ComputerNode neighbor : n.getOutNeighbors()){
			if(neighbor.getColor() != 0){
				neighbor.setColor(0);
				neighbor.setPred(null);
				if(neighbor.getOutNeighbors() != null){
					initDFS(neighbor);
				}
			}
		}
	}
	
	private List<List<Integer>> MergeSort(List<List<Integer>> list){
		int listsize = list.size();
		if(listsize == 1)
			return list;
		List<List<Integer>> first = list.subList(0, listsize/2);
		List<List<Integer>> second = list.subList(listsize/2, listsize);
		return Merge(MergeSort(first), MergeSort(second));
	}
	
	private List<List<Integer>> Merge(List<List<Integer>> A, List<List<Integer>> B){
		int asize = A.size();
		int bsize = B.size();
		int i = 0;
		int j = 0;
		List<List<Integer>> C = new ArrayList<List<Integer>>();
		while(i < asize && j < bsize){
			if(A.get(i).get(2) == B.get(j).get(2)){
				if(A.get(i).get(0) == B.get(i).get(0) && A.get(i).get(1) == B.get(i).get(1)){
					C.add(A.get(i));
					i++;
					j++;
				}
				else if(A.get(i).get(0) == B.get(i).get(1) && A.get(i).get(1) == B.get(i).get(0)){
					C.add(A.get(i));
					i++;
					j++;
				}
				else{
					C.add(A.get(i));
					i++;
					C.add(B.get(j));
					j++;
				}
			}
			else if(A.get(i).get(2) > B.get(j).get(2)){
				C.add(B.get(j));
				j++;
			}
			else if(A.get(i).get(2) < B.get(j).get(2)){
				C.add(A.get(i));
				i++;
			}
		}
		if(i >= asize){
			for(; j < bsize; j++){
				C.add(B.get(j));
			}
		}
		else{
			for(; i < asize; i++){
				C.add(A.get(i));
			}
		}
		return C;
	}
	
	
}
