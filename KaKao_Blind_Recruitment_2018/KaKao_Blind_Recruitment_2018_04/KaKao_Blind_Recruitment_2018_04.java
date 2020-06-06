package KaKao_Blind_Recruitment_2018_04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class KaKao_Blind_Recruitment_2018_04 {
	public static void main(String args[]) {
//		String res = solution(1, 1, 5, new String[] {"08:00", "08:01", "08:02", "08:03"});
//		String res = solution(2, 10, 2, new String[] {"09:10", "09:09", "08:00"});
//		String res = solution(2, 1, 2, new String[] {"09:00", "09:00", "09:00", "09:00"});
//		String res = solution(1, 1, 5, new String[] {"00:01", "00:01", "00:01", "00:01", "00:01"});
//		String res = solution(1, 1, 1, new String[] {"23:59"});
//		String res = solution(10, 60, 45, new String[] {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"});
//		String res = solution(3, 1, 1, new String[] {"00:01", "00:03", "00:05"});
		String res = solution(3, 10, 2, new String[] {"00:01", "9:01", "9:19", "9:19"});
		
		System.out.println(res);
	}
	
	public static String solution(int n, int t, int m, String[] timetable) {
        LinkedList <Integer> list = new LinkedList <>();
        
        int leng = timetable.length;
        for(int i=0; i<leng; ++i) {
        	String pop = timetable[i];
        	String[] time = pop.split(":");
        	int hour = Integer.parseInt(time[0]) * 60;
        	int minute = Integer.parseInt(time[1]);
        	list.add(hour + minute);
        }
        
        Collections.sort(list);
        
        ArrayList <ArrayList <Integer>>	queue = new ArrayList <>();
        int defaultTime = 9 * 60;
        for(int i=0; i<n; ++i) {
        	queue.add(new ArrayList <Integer>());
        	for(int j=0; j<m; ++j) {
        		if(list.isEmpty()) break;
        		int pop = list.peekFirst();
        		if(pop <= defaultTime) {
        			list.pollFirst();
        			queue.get(i).add(pop);
        		} else {
        			break;
        		}
        	}
        	defaultTime += t;
        }
        
//        for(int i=0; i<n; ++i) {
//        	for(int j=0; j<queue.get(i).size(); ++j) {
//        		System.out.print(getTimeFromInt(queue.get(i).get(j)) + " ");
//        	}
//        	System.out.println();
//        }
//        System.out.println();
        
        int lastTime = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; --i) {
        	defaultTime -= t;
        	int size = queue.get(i).size();
        	if(size < m) {
        		if(lastTime != Integer.MAX_VALUE) {
        			return getTimeFromInt(lastTime-1);
        		}
        		return getTimeFromInt(defaultTime);
        	}
        	
        	for(int j=size-1; j>=0; --j) {
        		int pop = queue.get(i).get(j);
        		if(lastTime > pop) {
        			if(lastTime != Integer.MAX_VALUE) return getTimeFromInt(lastTime-1);
        			lastTime = pop;
        		}
        	}
        	
        	if(i == 0) return getTimeFromInt(queue.get(i).get(0)-1);
        }
		
        return "";
    }

	private static String getTimeFromInt(int time) {
		int hour = time / 60;
		int minute = time % 60;
		String hourStr = String.valueOf(hour);
		String minuteStr = String.valueOf(minute);
		
		if(hour < 10) {
			hourStr = "0" + hourStr;
		}
		if(minute < 10) {
			minuteStr = "0" + minuteStr;	
		}
		  
		return  hourStr + ":" + minuteStr;
	}
}
