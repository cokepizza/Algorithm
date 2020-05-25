package KaKao_Code_Qualification_2017_03;

public class KaKao_Code_Qualification_2017_03 {
	public static void main(String args[]) {
//		int res = solution(15);
//		int res = solution(24);
//		int res = solution(41);
		int res = solution(2147483647);
		
		System.out.println(res);
	}
	
	public static int solution(int n) {
		int res = recur(n, 0);
		return res;
	}

	private static int recur(int n, int p) {
		if(Math.pow(3, p/2) > n) return 0;
		if(n == 1 && p == 0) return 1;
		if(n < 0) return 0;
		
		int count = 0;
		if(n % 3 == 0 && p >= 2) {
			count += recur(n/3, p-2);
		}
		count += recur(n-1, p+1);
		
		return count;
	}
}
