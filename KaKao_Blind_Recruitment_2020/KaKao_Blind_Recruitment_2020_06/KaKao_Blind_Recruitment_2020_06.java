package KaKao_Blind_Recruitment_2020_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//	외벽 점검
//	https://programmers.co.kr/learn/courses/30/lessons/60062

public class KaKao_Blind_Recruitment_2020_06 {
	public static void main(String args[]) {
//		int result = solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4});
		int result = solution(12, new int[] {1, 3, 4, 9, 10}, new int[] {3, 5, 7});
		System.out.println(result);
	}
	
	private static ArrayList <ArrayList <Integer>> pointList;
	private static int []twiceWeak;
	private static int weakLeng;
	private static int []tDist;
    public static int solution(int n, int[] weak, int[] dist) {
        weakLeng = weak.length;
        twiceWeak = new int[weakLeng * 2];
        for(int i=0; i<weakLeng; ++i) {
        	twiceWeak[i] = weak[i];
        }
        for(int i=weakLeng; i<weakLeng*2; ++i) {
        	twiceWeak[i] = weak[i-weakLeng] + n;
        }

        //	int[] => integer[] 변환
        Integer []userDist = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        
        Arrays.sort(userDist, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
        });
        
        //	integer[] => int[] 변환        
        tDist = Arrays.stream(userDist).mapToInt(i->i).toArray();
        
        int answer = -1;
        for(int uc=1; uc<=dist.length; ++uc) {
        	pointList = new ArrayList<>();
    		getPoint(uc, -1, new ArrayList<>());
    		ArrayList<ArrayList <Integer>> distList = getDist(pointList);
    		if(validDist(distList)) {
    			answer = uc;
    			break;
    		}
        }
        return answer;
    }

	private static boolean validDist(ArrayList<ArrayList<Integer>> distList) {
		loop: for(ArrayList<Integer> list: distList) {
			for(int i=0; i<list.size(); ++i) {
				if(tDist[i] < list.get(i)) {
					continue loop;
				}
			}
			return true;
		}
		return false;
	}

	private static ArrayList<ArrayList<Integer>> getDist(ArrayList <ArrayList <Integer>> pointList) {
		ArrayList<ArrayList<Integer>> distList = new ArrayList <>();
		int index = 0;
		for(ArrayList <Integer>list: pointList) {
			distList.add(new ArrayList <>());
			for(int i=0; i<list.size()-1; ++i) {
				int point = list.get(i);
				int nextPoint = list.get(i+1) - 1;
				distList.get(index).add(twiceWeak[nextPoint] - twiceWeak[point]);
			}
			
			Collections.sort(distList.get(index), new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			
			++index;
		}
		return distList;
	}

	private static void getPoint(int remain, int s, ArrayList <Integer>list) {
		if(remain == 0) {
			int diff = weakLeng + list.get(0);
			if(weakLeng*2 >= diff && diff > list.get(list.size() - 1)) {
				ArrayList <Integer> copyList = new ArrayList <>();
				copyList.addAll(list);
				copyList.add(diff);
				pointList.add(copyList);
			}
			return;
		}
		
		for(int i=0; i<weakLeng*2; ++i) {
			if(i > s) {
				list.add(i);
				getPoint(remain-1, i, list);
				list.remove(list.size() - 1);
			}
		}
	}
}
