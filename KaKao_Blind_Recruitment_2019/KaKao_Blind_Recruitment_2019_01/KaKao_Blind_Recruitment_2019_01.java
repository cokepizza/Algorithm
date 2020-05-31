package KaKao_Blind_Recruitment_2019_01;

import java.util.ArrayList;
import java.util.HashMap;

public class KaKao_Blind_Recruitment_2019_01 {
	public static void main(String args[]) {
		String[] res = solution(new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
		for(String pop : res) {
			System.out.println(pop);
		}
	}
	
	public static class Node {
		String id, mention;
		public Node(String id, String mention) {
			this.id = id;
			this.mention = mention;
		}
	}
	
	public static String[] solution(String[] record) {
		ArrayList <Node> word = new ArrayList <>();
		HashMap <String, String> hashMap = new HashMap<>();
		for(String pop : record) {
			String[] order = pop.split(" ");
			if(order[0].equals("Enter")) {
				word.add(new Node(order[1], "님이 들어왔습니다."));
				hashMap.put(order[1], order[2]);
			} else if(order[0].equals("Leave")) {
				word.add(new Node(order[1], "님이 나갔습니다."));
			} else if(order[0].equals("Change")) {
				hashMap.put(order[1], order[2]);
			}	
		}
		
		String[] answer = new String[word.size()];
		int index = 0;
		for(Node node : word) {
			answer[index++] = hashMap.get(node.id) + node.mention;
		}
		
		return answer;
	}
}
