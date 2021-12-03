package Code20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//	https://www.acmicpc.net/problem/20055

public class Code20055 {
	public static class Node {
		int num, robot;
		public Node(int num, int robot) {
			this.num = num;
			this.robot = robot;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Node []a = new Node[2 * n];
		
		for(int i=0; i<2*n; ++i) {
			int value = Integer.parseInt(st.nextToken());
			a[i] = new Node(value, 0);
		}
		
		int res = 0;
		while(true) {
			++res;
			moveConveyor(a);
			moveRobots(a);
			upRobot(a);
			if(checkEnd(a, k)) break;
		}
		
		System.out.println(res);
	}

	private static void moveConveyor(Node[] a) {
		Node last = a[a.length-1];
		for(int i=a.length-1; i>=1; --i) {
			a[i] = a[i-1];
		}
		a[0] = last;
		a[a.length / 2 - 1].robot = 0;
	}
	
	private static void moveRobots(Node[] a) {
		for(int i=a.length-1; i>=1; --i) {
			if(a[i].num >= a[i-1].robot && a[i].robot == 0) {
				a[i].num -= a[i-1].robot;
				a[i].robot += a[i-1].robot;
				a[i-1].robot = 0;
			}
		}
		a[a.length / 2 - 1].robot = 0;
	}

	private static void upRobot(Node[] a) {
		if(a[0].num > 0) {
			a[0].robot += 1;
			a[0].num -= 1;
		}
	}
	
	private static boolean checkEnd(Node[] a, int k) {
		for(int i=0; i<a.length; ++i) {
			if (a[i].num == 0) --k;
		}
		
		return k <= 0 ? true : false;
	}
}
