package KaKao_Blind_Recruitment_2020_02;

import java.util.LinkedList;

//	괄호 변환
//	https://programmers.co.kr/learn/courses/30/lessons/60058

public class KaKao_Blind_Recruitment_2020_02 {
	public static void main(String args[]) {
//		String res = solution("(()())()");
//		String res = solution(")(");
		String res = solution("()))((()");
		System.out.println(res);
	}
	
	public static String solution(String p) {
		String answer = getCorrect(p);
		return answer;
	}
	
	private static String getCorrect(String p) {
		int leng = p.length();
		for(int i=2; i<=leng; i+=2) {
			String u = p.substring(0, i);
			String v = p.substring(i, leng);
			if(isBalanced(u) && isBalanced(v)) {
				if(isCorrect(u)) {
					return u + getCorrect(v);
				} else {
					return "(" + getCorrect(v) + ")" + getReverse(u.substring(1, u.length()-1));
				}
			}
		};
		
		return p;
	}

	private static String getReverse(String str) {
		int leng = str.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<leng; ++i) {
			char pop = str.charAt(i);
			if(pop == '(') sb.append(')');
			else if(pop == ')') sb.append('(');
		}
		
		return sb.toString();
	}

	private static boolean isCorrect(String str) {
		LinkedList <Character> queue = new LinkedList <>();
		int leng = str.length();
		for(int i=0; i<leng; ++i) {
			char pop = str.charAt(i);
			if(pop == '(') {
				queue.add(pop);
			} else {
				if(queue.isEmpty()) return false;
				char pick = queue.peekLast();
				if(pick == '(') {
					queue.pollLast();
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isBalanced(String str) {
		int leng = str.length();
		int open = 0, close = 0;
		for(int i=0; i<leng; ++i) {
			char pop = str.charAt(i);
			if(pop == '(') ++open;
			else if(pop == ')') ++close;
		}
		
		return open == close;
	}
}
