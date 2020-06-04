package KaKao_Blind_Recruitment_2018_01;

public class KaKao_Blind_Recruitment_2018_01 {
	public static void main(String args[]) {
		String[] res = solution(5, new int[] {9, 20, 28, 18, 11}, new int[] {30, 1, 21, 17, 28});
//		String[] res = solution(6, new int[] {46, 33, 33 ,22, 31, 50}, new int[] {27 ,56, 19, 14, 14, 10});
		
		for(int i=0; i<res.length; ++i) {
			System.out.println(res[i]);
		}
	}
	
	public static String[] solution(int n, int[] arr1, int[] arr2) {
		int leng = arr1.length;
		String[] answer = new String[leng];
		
		
		for(int i=0; i<leng; ++i) {
			int union = arr1[i] | arr2[i];
			String res = Integer.toBinaryString(union);
			
			while(res.length() < leng) {
				res = "0" + res;
			}
			
			answer[i] = "";
			for(int j=0; j<leng; ++j) {
				answer[i] += res.charAt(j) == '0' ? " " : "#"; 
			}
		}
		
        return answer;
    }
}