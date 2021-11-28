package KaKao_Blind_Recruitment_2019_05;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class KaKao_Blind_Recruitment_2019_05 {
	public static void main(String args[]) {
		int[][] res = solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
		for(int i=0; i<res.length; ++i) {
			for(int j=0; j<res[i].length; ++j) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}
	
    public static class Vertex {
		int x;
		int y;
		int index;
		public Vertex (int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
	
	public static class Node {
		Node leftNode;
		Node rightNode;
		Vertex vertex;
		
		public Node (Vertex key) {
			this.vertex = key;
		}
		
		public void setRight(Node node) {
			this.rightNode = node;
		}
		
		public void setLeft(Node node) {
			this.leftNode = node;
		}
	}
	
	private static int[][] answer;
	private static int count;
    public static int[][] solution(int[][] nodeinfo) {
        LinkedList <Vertex> list = new LinkedList <>();
		int size = nodeinfo.length;
		for(int i=0; i<size; ++i) {
			list.add(new Vertex(nodeinfo[i][0], nodeinfo[i][1], i+1));
		}
		
		Collections.sort(list, new Comparator <Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				if(o1.y == o2.y) return o1.x - o2.x;
				else return o2.y - o1.y;
			}
		});
		
		Node root = new Node(list.poll());
		
		for(Vertex newVertex : list) {
			insertTree(root, newVertex);
		}
		
		answer = new int[2][size];
		count = 0;
		dfs(root);
		count = 0;
		dfs2(root);
        
        return answer;
    }
    
    private static void dfs2(Node root) {
		if(root.leftNode != null) dfs2(root.leftNode);
		if(root.rightNode != null) dfs2(root.rightNode);
		answer[1][count++] = root.vertex.index;
	}

	private static void dfs(Node root) {
		answer[0][count++] = root.vertex.index;
		if(root.leftNode != null) dfs(root.leftNode);
		if(root.rightNode != null) dfs(root.rightNode);
	}

	private static void insertTree(Node root, Vertex newVertex) {
		Vertex center = root.vertex;
		if(center.y > newVertex.y) {
			if(center.x > newVertex.x) {
				if(root.leftNode == null) {
					root.setLeft(new Node(newVertex));
				}
				else {
					insertTree(root.leftNode, newVertex);
				}
			}
			else {
				if(root.rightNode == null) {
					root.setRight(new Node(newVertex));
				}
				else {
					insertTree(root.rightNode, newVertex);
				}
			}
		}
	}
}
