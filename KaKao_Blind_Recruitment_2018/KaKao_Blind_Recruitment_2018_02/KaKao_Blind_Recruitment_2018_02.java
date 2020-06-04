package KaKao_Blind_Recruitment_2018_02;

public class KaKao_Blind_Recruitment_2018_02 {
    public static void main(String args[]) {
//    	int res = solution("1S2D*3T");
    	int res = solution("1D2S#10S");
//    	int res = solution("1D2S0T");
//    	int res = solution("1S*2T*3S");
//    	int res = solution("1D#2S*3S");
//    	int res = solution("1T2D3D#");
//    	int res = solution("1D2S3T*");
    	
    	System.out.println(res);
    }
    
	public static int solution(String dartResult) {
		int leng = dartResult.length();
		int []nums = new int[3];
		int counter = 0;
		
		for(int i=0; i<leng; ++i) {
			char pop = dartResult.charAt(i);
			if(pop == 'S') {
				nums[counter-1] = (int)Math.pow(nums[counter-1], 1); 
			} else if(pop == 'D') {
				nums[counter-1] = (int)Math.pow(nums[counter-1], 2);
			} else if(pop == 'T') {
				nums[counter-1] = (int)Math.pow(nums[counter-1], 3);
			} else if(pop == '*') {
				if(counter > 1) {
					nums[counter-2] = nums[counter-2] * 2;
				}
				if(counter > 0) {
					nums[counter-1] = nums[counter-1] * 2;	
				}
			} else if(pop == '#') {
				nums[counter-1] = nums[counter-1] * -1;	
			} else {
				if(i+1 < leng) {
					char next = dartResult.charAt(i+1);
					if(next == '0') {
						nums[counter++] = 10;
						++i;
						continue;
					}
				}
				
				nums[counter++] = (int)pop - '0';
			}
		}
        
		int answer = 0;
		for(int i=0; i<counter; ++i) {
			answer += nums[i];
		}
        return answer;
    }
}
