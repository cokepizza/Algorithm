package KCode5;

public class KCode5 {
	public static void main(String args[]) { 
		int[][] result = solution(5, new int[][]{{1,0,0,1}, {1,1,1,1}, {2,1,0,1}, {2,2,1,1}, {5,0,0,1}, {5,1,0,1}, {4,2,1,1}, {3,2,1,1}});
//		int[][] result = solution(5, new int[][]{{0,0,0,1}, {2,0,0,1}, {4,0,0,1}, {0,1,1,1}, {1,1,1,1}, {2,1,1,1}, {3,1,1,1}, {2,0,0,0}, {1,1,1,0}, {2,2,0,1}});
	}
	
	private static int n;
	private static boolean[][][]arr;
	public static int[][] solution(int inputN, int[][] build_frame) {
		n = inputN;
		arr = new boolean[n][n][2];
        int leng = build_frame.length;
        for(int i=0; i<leng; ++i) {
        	int[] pop = build_frame[i];
        	int x = pop[0]; int y = pop[1]; int a = pop[2]; int b = pop[3];
        	if(b == 1) {
        		//	설치
        		if(a == 1 && x+1 < n || a == 0 && y + 1 < n) {
        			//	보 설치, 기둥 설치
        			if(checkValid(y, x, a)) {	
        				arr[y][x][a] = true;
        			}
        		}
        	} else {
        		//	삭제
    			arr[y][x][a] = false;
    			boolean pass = true;
    			for(int j=0; j<n; ++j) {
    				for(int k=0; k<n; ++k) {
    					if(arr[y][x][a]) {
    						pass &= checkValid(j, k, a);	
    					}
    				}
    			}
    			if(!pass) arr[y][x][a] = true;
        	}
        }
        
        return new int[][] {{}};
    }
	private static boolean checkValid(int y, int x, int a) {
		if(a == 1) {
			if((y > 0 && arr[y-1][x][1-a]) || (y > 0 && x+1 < n && arr[y-1][x+1][1-a]) || (x > 0 && x+1 < n && arr[y][x-1][a] && arr[y][x+1][a])) return true;
		} else {
			if(y == 0 || (y > 0 && arr[y-1][x][a]) || arr[y][x][1-a] || (x > 0 && arr[y][x-1][1-a])) return true;
		}
		return false;
	}
}
