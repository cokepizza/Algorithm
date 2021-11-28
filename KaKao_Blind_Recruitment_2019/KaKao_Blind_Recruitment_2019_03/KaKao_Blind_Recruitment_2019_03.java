package KaKao_Blind_Recruitment_2019_03;

import java.util.ArrayList;
import java.util.HashSet;

//	후보키
//	https://programmers.co.kr/learn/courses/30/lessons/42890

public class KaKao_Blind_Recruitment_2019_03 {
	public static void main(String args[]) {
		int res = solution(new String[][] {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}});
		
		System.out.println(res);
	}
	
    public static int solution(String[][] relation) {
        int c = relation[0].length;
		int r = relation.length;
		ArrayList <Integer> res = new ArrayList <>();
		for(int i=1; i < (1 << c); ++i) {
			HashSet <String> set = new HashSet <>();
			for(int j=0; j<r; ++j) {
				String key = "";
				for(int k=0; k<c; ++k) {
					if((i & (1 << k)) != 0) key += relation[j][k];
				}
				set.add(key);
			}
			if(set.size() == r && boolCheck(res, i)) res.add(i);
		}
        return res.size();
    }
    
    private static boolean boolCheck(ArrayList<Integer> res, int bit) {
		for(int i=0; i< res.size(); ++i) {
			if((res.get(i) & bit) == res.get(i)) return false;
		}
		return true;
	}
}
