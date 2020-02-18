package Code1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Code1339_2 {
	public static class Node {
		int index, value;
		public Node (int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		HashMap <Integer, Integer> hashMap = new HashMap<>();
		ArrayList <Node> list = new ArrayList <>();
		
		for(int i=0; i<n; ++i) {
			String word = br.readLine();
			int leng = word.length();
			for(int j=0; j<leng; ++j) {
				int index = word.charAt(j) - 65;
				if(hashMap.containsKey(index)) {
					hashMap.put(index, hashMap.get(index) + (int)(Math.pow(10, leng - j - 1)));
				} else {
					hashMap.put(index, (int)(Math.pow(10, leng - j - 1)));
				}
			}
		}
		
		for(Integer pop : hashMap.keySet()) {
			list.add(new Node(pop, hashMap.get(pop)));
		};
		
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.value - o1.value;
			}
		});
		
		int count = 9;
		int sum = 0;
		for(Node node: list) {
			sum += node.value * count--;
		}
		
		System.out.println(sum);
	}
}
