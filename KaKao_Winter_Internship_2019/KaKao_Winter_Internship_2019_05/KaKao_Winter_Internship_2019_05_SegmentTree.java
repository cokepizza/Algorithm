package KaKao_Winter_Internship_2019_05;

public class KaKao_Winter_Internship_2019_05_SegmentTree {
	public static void main(String args[]) {
//		int res = solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
		int res = solution(new int[] {1, 1, 1}, 3);
		System.out.println(res);
	}
	
	private static int[] stonesRef, segTree;
	public static int solution(int[] stones, int k) {
		stonesRef = stones;
		int leng = stones.length;
        segTree = new int[leng*4];
        initSegTree(0, leng-1, 1);
        
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<leng-k+1; ++i) {
        	answer = Math.min(answer, findQuery(0, leng-1, i, i+k-1, 1));	
        }
        
		return answer;
    }

	private static int findQuery(int s, int e, int rs, int re, int index) {
		int m = (s + e) / 2;
		if(rs <= s && e <= re) {
			return segTree[index];
		} else if(re < s || rs > e) {
			return -1;
		} else {
			return Math.max(findQuery(s, m, rs, re, index*2), findQuery(m+1, e, rs, re, index*2+1));
		}
	}

	private static int initSegTree(int s, int e, int index) {
		if(s == e) {
			return segTree[index] = stonesRef[s];
		}
		
		int m = (s + e) / 2;
		return segTree[index] = Math.max(initSegTree(s, m, index*2), initSegTree(m+1, e, index*2+1));
	}
}
