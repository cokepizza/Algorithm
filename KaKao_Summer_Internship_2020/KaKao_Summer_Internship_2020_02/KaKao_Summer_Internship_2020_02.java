package KaKao_Summer_Internship_2020_02;

import java.util.ArrayList;

public class KaKao_Summer_Internship_2020_02 {
	public static void main(String args[]) {
//		long res = solution("100-200*300-500+20");
		long res = solution("50*6-3*2");
		System.out.println(res);
	}
   
	public static long solution(String expression) {
        int leng = expression.length();
        String buffer = "";
        
        ArrayList <Long> numArr = new ArrayList <>();
        ArrayList <Integer> calArr = new ArrayList <>();
        long answer = -1;
        
        for(int i=0; i<leng; ++i) {
        	char pop = expression.charAt(i);
        	if(pop == '+' || pop == '-' || pop == '*') {
        		numArr.add(Long.parseLong(buffer));
        		buffer = "";
        		if(pop == '+') calArr.add(0);
        		if(pop == '-') calArr.add(1);
        		if(pop == '*') calArr.add(2);
        		
        	} else {
        		buffer += pop;
        	}
        }
        numArr.add(Long.parseLong(buffer));
        
//        System.out.println(numArr);
//        System.out.println(calArr);
        
        int numLeng = numArr.size();
        int calLeng = calArr.size();
        
        for(int i=0; i<3; ++i) {
        	for(int j=0; j<3; ++j) {
        		for(int k=0; k<3; ++k) {
        			if(i != j && j != k && i != k) {
//        				if(i == 2 && j == 0 && k == 1) {
//        					System.out.println("aa");
//        				}
        				ArrayList <Long> numArrCopy = new ArrayList <>();
        				for(int p=0; p<numLeng; ++p) {
        					numArrCopy.add(numArr.get(p));
        				}
        				
        				ArrayList <Integer> calArrCopy = new ArrayList <>();
        				for(int p=0; p<calLeng; ++p) {
        					calArrCopy.add(calArr.get(p));
        				}
        				
        				merge(numArrCopy, calArrCopy, i);
        				merge(numArrCopy, calArrCopy, j);
        				merge(numArrCopy, calArrCopy, k);
        				answer = Math.max(answer, Math.abs(numArrCopy.get(0)));
        			}
        		}
        	}
        }
        
        return answer;
    }

	private static void merge(ArrayList<Long> numArrCopy, ArrayList<Integer> calArrCopy, int cal) {
//		int calLeng = calArrCopy.size();
		for(int p=0; p<calArrCopy.size(); ++p) {
			
			if(calArrCopy.get(p) == cal) {
				long cache1 = numArrCopy.get(p);
				long cache2 = numArrCopy.get(p+1);
				numArrCopy.remove(p);
				numArrCopy.remove(p);
				if(cal == 0) {
					numArrCopy.add(p, cache1 + cache2);
				} else if(cal == 1) {
					numArrCopy.add(p, cache1 - cache2);
				} else if(cal == 2) {
					numArrCopy.add(p, cache1 * cache2);
				}
				calArrCopy.remove(p);
				--p;
			}
		}
		
	}
}
