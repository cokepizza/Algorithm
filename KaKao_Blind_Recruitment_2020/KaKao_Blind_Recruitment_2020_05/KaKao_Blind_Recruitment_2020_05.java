package KaKao_Blind_Recruitment_2020_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//	기둥과 보 설치
//	https://programmers.co.kr/learn/courses/30/lessons/60061

public class KaKao_Blind_Recruitment_2020_05 {
	public static void main(String args[]) { 
//		int[][] result = solution(5, new int[][]{{1,0,0,1}, {1,1,1,1}, {2,1,0,1}, {2,2,1,1}, {5,0,0,1}, {5,1,0,1}, {4,2,1,1}, {3,2,1,1}});
		int[][] result = solution(5, new int[][]{{0,0,0,1}, {2,0,0,1}, {4,0,0,1}, {0,1,1,1}, {1,1,1,1}, {2,1,1,1}, {3,1,1,1}, {2,0,0,0}, {1,1,1,0}, {2,2,0,1}});
		for(int i=0; i<result.length; ++i) {
			System.out.println(result[i][0] + " " + result[i][1] + " " + result[i][2]);
		}
	}
	
	private static int n;
	private static boolean[][][]arr;
	public static int[][] solution(int inputN, int[][] build_frame) {
		n = inputN;
		arr = new boolean[n+1][n+1][2];
        int leng = build_frame.length;
        for(int i=0; i<leng; ++i) {
        	int[] pop = build_frame[i];
        	int x = pop[0]; int y = pop[1]; int a = pop[2]; int b = pop[3];
        	if(b == 1) {
    			//	0 보 설치, 1 기둥 설치
    			if(checkValid(y, x, a)) {
    				arr[y][x][a] = true;
    			}
        	} else {
        		//	삭제
        		if(arr[y][x][a]) {
        			arr[y][x][a] = false;
        			boolean pass = true;
        			for(int j=0; j<=n; ++j) {
        				for(int k=0; k<=n; ++k) {
        					for(int p=0; p<2; ++p) {
            					if(arr[j][k][p]) {
            						pass &= checkValid(j, k, p);	
            					}    						
        					}
        				}
        			}
        			if(!pass) arr[y][x][a] = true;	
        		}
        	}
        }
        
        ArrayList <int[]> result = new ArrayList <>();
        for(int i=0; i<=n; ++i) {
        	for(int j=0; j<=n; ++j) {
        		for(int k=0; k<2; ++k) {
        			if(arr[i][j][k]) result.add(new int[] {j, i, k});	
        		}
        	}
        }
        
        Collections.sort(result, new Comparator <int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if(a[0] == b[0]) {
					if(a[1] == b[1]) {
						return a[2] - b[2];
					}
					return a[1] - b[1];
				}
				return a[0] - b[0];
			};
        });
 
        int resSize = result.size();
        int [][]ans = new int[resSize][3];
        
        for(int i=0; i<resSize; ++i) {
        	ans[i] = result.get(i);
        }
        
        return ans;
    }
	
	private static boolean checkValid(int y, int x, int a) {
		if(a == 1) {	//	보
			if((y-1 >= 0 && arr[y-1][x][1-a]) || (y-1 >= 0 && x+1 <= n && arr[y-1][x+1][1-a]) || (x+1 <= n && x-1 >= 0 && arr[y][x-1][a] && arr[y][x+1][a])) return true;
		} else {	//	기둥
			if(y == 0 || (y-1 >= 0 && arr[y-1][x][a]) || arr[y][x][1-a] || (x-1 >= 0 && arr[y][x-1][1-a])) return true;
		}
		return false;
	}
}
