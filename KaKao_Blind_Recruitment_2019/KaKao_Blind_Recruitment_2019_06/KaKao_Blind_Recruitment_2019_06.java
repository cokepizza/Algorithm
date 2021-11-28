package KaKao_Blind_Recruitment_2019_06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

//	매칭 점수
//	https://programmers.co.kr/learn/courses/30/lessons/42893

public class KaKao_Blind_Recruitment_2019_06 {
	public static void main(String args[]) {
		int res = solution("blind", new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
//		int res = solution("Muzi", new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"});
		System.out.println(res);
	}
	
    public static class Node {
		int index;
		int baseScore;
		double linkScore;
		ArrayList <String> linkList; 
		public Node (int index, int baseScore, double linkScore) {
			this.index = index;
			this.baseScore = baseScore;
			this.linkScore = linkScore;
		}
	}
	
	public static class Res {
		int index;
		double score;
		public Res (int index, double score) {
			this.index = index;
			this.score = score;
		}
	}
	
	private static HashMap <String, Node> idMap;
    public static int solution(String word, String[] pages) {
        word = word.toUpperCase();
    	idMap = new HashMap <>();
    	init(pages);
    	execute(pages, word);
  
    	ArrayList <Res> arr = new ArrayList <>();
    	for(String pop : idMap.keySet()) {
    		Node node = idMap.get(pop);
    		arr.add(new Res(node.index, node.baseScore + node.linkScore));
    	}
    	
    	Collections.sort(arr, new Comparator <Res>() {
			@Override
			public int compare(Res o1, Res o2) {
				if(o1.score == o2.score) return o1.index - o2.index;
				else {
					if(o2.score > o1.score) return 1;
					else return -1;
				}
			}
    	});
    	
        return arr.get(0).index;
    }
    
    private static void init(String[] pages) {
		int size = pages.length;
		for(int i=0; i<size; ++i) {
			pages[i] = pages[i].toUpperCase();
		}
	}
    
    private static void execute(String[] pages, String word) {
		int size = pages.length;
		for(int i=0; i<size; ++i) {
			int frontIdx = pages[i].indexOf("<META PROPERTY=\"OG:URL\" CONTENT=");
			int backIdx = pages[i].indexOf("</HEAD>");
    		String frame = pages[i].substring(frontIdx, backIdx);
    		frontIdx = frame.indexOf("HTTPS://");
    		backIdx = frame.indexOf("\"", frontIdx);
    		String url = frame.substring(frontIdx, backIdx);

			frontIdx = pages[i].indexOf("<BODY>");
			backIdx = pages[i].indexOf("</BODY>");
			frame = pages[i].substring(frontIdx+7, backIdx);
			ArrayList <String> wordList = new ArrayList <>();
			ArrayList <String> linkList = new ArrayList <>();
			
			int s=0;
			while(s < frame.length()) {
				int fIdx = frame.indexOf("<A HREF=", s);
				int bIdx = frame.indexOf("</A>", s);
				if(fIdx == -1|| bIdx == -1) break;
                if(fIdx > bIdx) {
					s = bIdx+4;
					continue;
				}
				String wordStr = frame.substring(s, fIdx);
				wordList.add(wordStr);
				String innerFrame = frame.substring(fIdx, bIdx);
				int ffIdx = innerFrame.indexOf("HTTPS://");
				int bbIdx = innerFrame.indexOf("\"", ffIdx);
				if(ffIdx == -1|| bbIdx == -1) break;
				String linkUrl = innerFrame.substring(ffIdx, bbIdx);
				linkList.add(linkUrl);
				s = bIdx+4;
			}
			wordList.add(frame.substring(s, frame.length()));
			
			int wSize = wordList.size();
			int count = 0;
			for(int j=0; j < wSize; ++j) {
				s = 0;
				while(s < wordList.get(j).length()) {
					int pop = wordList.get(j).indexOf(word, s);
					if(pop == -1) break;
					if(!checkWord(wordList.get(j), pop, word.length())) {
						s = pop + word.length();	
						continue;
					}
					++count;
					s = pop + word.length();
				}
			}
			idMap.put(url, new Node(i, count, 0));
			idMap.get(url).linkList = linkList;
		}
		
		for(String id : idMap.keySet()) {
			int linkSize = idMap.get(id).linkList.size();
			for(String link : idMap.get(id).linkList) {
				double score = (double)idMap.get(id).baseScore / linkSize;
				if(!idMap.containsKey(link)) continue;
				idMap.get(link).linkScore += score;
			};
		}
	}

	private static boolean checkWord(String word, int pop, int size) {
		if(pop > 0 && 65 <= word.charAt(pop-1) && word.charAt(pop-1) <= 90) return false; 
		if(pop+size < word.length() && 65 <= word.charAt(pop+size) && word.charAt(pop+size) <= 90) return false;
		return true;
	}
}
