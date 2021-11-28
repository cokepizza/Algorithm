package KaKao_Blind_Recruitment_2020_06;

//	외벽 점검
//	https://programmers.co.kr/learn/courses/30/lessons/60062
//	비트마스크, 완전 탐색

public class KaKao_Blind_Recruitment_2020_06_2 {
	public static void main(String args[]) {
		int result = solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4});
//		int result = solution(12, new int[] {1, 3, 4, 9, 10}, new int[] {3, 5, 7});
		System.out.println(result);
	}
	
	private static int minCount = Integer.MAX_VALUE;
	private static int gn;
	private static int []gWeak;
	private static int []gDist;
	
	public static int solution(int n, int[] weak, int[] dist) {
		gn = n;
		gWeak = weak;
		gDist = dist;
		
		for(int i=0; i<weak.length; ++i) {
			getMinCount(1, i, 0);
		}
		
		return minCount == Integer.MAX_VALUE ? -1 : minCount;
	}

	private static void getMinCount(int count, int dep, int visited) {
		if(count > gDist.length) return;
		if(count >= minCount) return;
		
		for(int i=0; i<gWeak.length; ++i) {
			int des = (dep + i) % gWeak.length;
			int dist = gWeak[des] - gWeak[dep] + (gWeak[des] < gWeak[dep] ? gn : 0);
			if(dist > gDist[gDist.length - count]) break;
			visited |= 1 << des;
		}
		
		if(visited == (1 << gWeak.length) -1) {
			minCount = Math.min(minCount, count);
			return;
		}
		
		for(int i=0; i<gWeak.length; ++i) {
			if((visited & (1 << i)) == 0) {
				getMinCount(count+1, i, visited);	
			}
		}
	}
}
