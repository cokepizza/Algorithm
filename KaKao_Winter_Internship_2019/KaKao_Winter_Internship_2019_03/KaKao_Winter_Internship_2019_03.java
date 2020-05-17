package KaKao_Winter_Internship_2019_03;

import java.util.HashSet;

public class KaKao_Winter_Internship_2019_03 {
	public static void main(String args[]) {
//		int res = solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "abc1**"});
//		int res = solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"*rodo", "*rodo", "******"});
		int res = solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"});
		System.out.println(res);
	}
	
	private static String[] userId, bannedId;
	private static int userLeng, banLeng;
	private static HashSet <Integer> result = new HashSet<>();
    public static int solution(String[] user_id, String[] banned_id) {
    	userId = user_id;
    	bannedId = banned_id;
    	userLeng = user_id.length;
    	banLeng = banned_id.length;
    	
    	int answer = recur(0, 0);
        return answer;
    }
    
	private static int recur(int visitied, int bIdx) {
		if(banLeng == bIdx) {
			if(result.contains(visitied)) return 0;
			result.add(visitied);
			return 1;
		}
		
		int count = 0;
		
		for(int i=0; i<userLeng; ++i) {
			if((visitied & (1 << i)) == 0 && validate(userId[i], bannedId[bIdx])) {
				count += recur(visitied | (1 << i), bIdx+1);
			}
		}
		
		return count;
	}

	private static boolean validate(String user, String ban) {
		if(user.length() != ban.length()) return false;
		
		for(int i=0; i<user.length(); ++i) {
			if(user.charAt(i) != ban.charAt(i) && ban.charAt(i) != '*') return false;
		}
		
		return true;
	}
}
