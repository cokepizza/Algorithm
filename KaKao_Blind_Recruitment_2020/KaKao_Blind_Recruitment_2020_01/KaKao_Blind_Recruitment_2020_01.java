package KaKao_Blind_Recruitment_2020_01;

//	문자열 압축
//	https://programmers.co.kr/learn/courses/30/lessons/60057

public class KaKao_Blind_Recruitment_2020_01 {
	public static void main(String args[]) {
//		int res = solution("aabbaccc");
//		int res = solution("ababcdcdababcdcd");
//		int res = solution("abcabcdede");
//		int res = solution("abcabcabcabcdededededede");
		int res = solution("xababcdcdababcdcd");
		System.out.println(res);
	}
	
	public static int solution(String s) {
		int leng = s.length();
		int answer = Integer.MAX_VALUE;
		for(int i=1; i<=leng/2+1; ++i) {
			String result = "";
			String selected = s.substring(0, i);
			int counter = 1;
			for(int j=i; j<leng; j+=i) {
				int maxLeng = Math.min(j+i, leng);
				String pop = s.substring(j, maxLeng);
				if(selected.equals(pop)) {
					++counter;
				} else {
					if(counter == 1) {
						result += selected;
					} else {
						result += String.valueOf(counter) + selected;
					}
					selected = pop;
					counter = 1;
				}
			}
			
			if(counter == 1) {
				result += selected;
			} else {
				result += String.valueOf(counter) + selected;
			}
			
			answer = Math.min(answer, result.length());
		}

	    return answer;
	}
}
