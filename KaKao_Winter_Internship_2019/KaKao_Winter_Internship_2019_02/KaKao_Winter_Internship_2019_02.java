package KaKao_Winter_Internship_2019_02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class KaKao_Winter_Internship_2019_02 {
	public static void main(String args[]) {
		int []res = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
//		int []res = solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
//		int []res = solution("{{20,111},{111}}");
//		int []res = solution("{{123}}");
//		int []res = solution("{{4,2,3},{3},{2,3,4,1},{2,3}});
		
		for(int i=0; i<res.length; ++i) {
			System.out.print(res[i] + " ");
		}
	}
	
	public static int[] solution(String s) {
	    s = s.substring(1, s.length() -1);
	    String[] arr = s.split("},");
	    int leng = arr.length;
	    arr[leng-1] = arr[leng-1].substring(0, arr[leng-1].length() - 1);
	    for(int i=0; i<leng; ++i) {
	    	arr[i] = arr[i].substring(1, arr[i].length());
	    }
	    
	    Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
	    });
	    
	    int[] answer = new int[leng];
	    int index = 0;
	    HashSet <Integer> nums = new HashSet <>();
	    for(String str: arr) {
	    	String []splited = str.split(",");
	    	for(String pop : splited) {
	    		int num = Integer.parseInt(pop);
		    	if(!nums.contains(num)) {
		    		nums.add(num);
		    		answer[index++] = num;
		    	}	
	    	}
    	}
	    
	    return answer;
    }
}
