package KaKao_Winter_Internship_2019_04;

import java.util.HashMap;

public class KaKao_Winter_Internship_2019_04_UnionFind {
	public static void main(String args[]) {
		long []res = solution(10, new long[] {1,3,4,1,3,1});
		int size = res.length;
		for(int i=0; i<size; ++i) {
			System.out.print(res[i] + " ");	
		}
	}
	
	private static HashMap <Long, Long> hashMap;
	private static HashMap <Long, Long> maxMap;
	public static long[] solution(long k, long[] room_number) {
        hashMap = new HashMap<>();
        maxMap = new HashMap<>();
        int leng = room_number.length;
        long[] answer = new long[leng];
        
        for(int i=0; i<leng; ++i) {
        	long key = room_number[i];
        	if(hashMap.containsKey(key)) {
        		long parent = findParent(key);
        		long maxNum = maxMap.get(parent);
        		hashMap.put(maxNum+1, (long) -1);
        		maxMap.put(maxNum+1, maxNum+1);
        		answer[i] = maxNum+1;
        		
        		if(hashMap.containsKey(maxNum)) {
        			merge(maxNum+1, maxNum);
        		}
        		if(hashMap.containsKey(maxNum+2)) {
        			merge(maxNum+1, maxNum+2);
        		}
        	} else {
        		hashMap.put(key, (long) -1);
        		maxMap.put(key, key);
        		answer[i] = key;
        		
        		if(hashMap.containsKey(key+1)) {
        			merge(key, key+1);
        		}
        		if(hashMap.containsKey(key-1)) {
        			merge(key, key-1);
        		}
        	}
        }
        
        
        return answer;
    }
	
	private static void merge(long a, long b) {
		long parentA = findParent(a);
		long parentB = findParent(b);
		
		if(parentA == parentB) return;
		
		long sizeA = hashMap.get(parentA);
		long sizeB = hashMap.get(parentB);
		
		long behind = Math.max(maxMap.get(parentA), maxMap.get(parentB));
		if(sizeA > sizeB) {
			hashMap.put(parentA, parentB);
			hashMap.put(parentB, sizeA + sizeB);	
			maxMap.put(parentB, behind);
		} else {
			hashMap.put(parentB, parentA);
			hashMap.put(parentA, sizeA + sizeB);
			maxMap.put(parentA, behind);
		}
	}

	private static long findParent(long key) {
		long parent = hashMap.get(key);
		if(parent < 0) {
			return key;
		}
		
		long pParent = findParent(parent);
		hashMap.put(key, pParent);
		return pParent;
	}
	
}