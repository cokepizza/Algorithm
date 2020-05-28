package KaKao_Blind_Recruitment_2018_01;

import java.util.HashSet;
import java.util.LinkedList;

public class KaKao_Blind_Recruitment_2018_01 {
	public static void main(String args[]) {
		int res = solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
//		int res = solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
//		int res = solution(2, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
//		int res = solution(5, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
//		int res = solution(2, new String[] {"Jeju", "Pangyo", "NewYork", "newyork"});
//		int res = solution(0, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
//		int res = solution(0, new String[] {"zz", "zz", "zz"});
		System.out.println(res);
	}
	
	public static int solution(int cacheSize, String[] cities) {
        LinkedList <String> cache = new LinkedList <>();
        HashSet <String> holdSet = new HashSet <>();
        int leng = cities.length;
        int timer = 0;
        for(int i=0; i<leng; ++i) {
        	String pop = cities[i].toLowerCase();
        	
        	//	cache hit
        	if(holdSet.contains(pop)) {
        		cache.remove(pop);
        		cache.add(pop);
        		timer += 1;
        		continue;
        	}
        	
        	//	cache miss
        	if(cache.size() >= cacheSize) {
        		String swipeOut = cache.pollFirst();
        		holdSet.remove(swipeOut);
    		}
        	if(cache.size() + 1 <= cacheSize) {
        		cache.addLast(pop);
            	holdSet.add(pop);
        	}
        	timer += 5;
        }
        
        return timer;
    }
}
