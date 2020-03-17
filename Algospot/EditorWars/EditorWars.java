package EditorWars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EditorWars {
	//	enemy는 i와 적대적인 관계인 root
	private static int []parent, size, enemy;
	private static int n, m;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			StringBuilder ssb = new StringBuilder();
			parent = new int[n];
			size = new int[n];
			enemy = new int[n];
			for(int j=0; j<n; ++j) {
				parent[j] = j;
				size[j] = 1;
				enemy[j] = -1;
			}
			
			boolean pass = true;
			boolean init = false;
			for(int j=0; j<m; ++j) {
				st = new StringTokenizer(br.readLine());
				String word = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(word.equals("ACK")) {
					pass &= ack(a, b);
				} else {
					pass &= dis(a, b);
				}
				
				if(!pass && !init) {
					init = true;
					ssb.append("CONTRADICTION AT " + (j+1));
				}
			}
			if(pass) {
				ssb.append("MAX PARTY SIZE IS " + maxparty());
			}
			sb.append(ssb + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
	
	private static int maxparty() {
		int t = 0;
		for(int i=0; i<n; ++i) {
			if(parent[i] == i) {
				if(enemy[i] > parent[i]) continue;
				int enemySize = enemy[i] == -1 ? 0 : size[enemy[i]]; 
				t += Math.max(size[parent[i]], enemySize);
			}
		}
		
		return t;
	}

	private static boolean dis(int u, int v) {
		u = find(u); v = find(v);
		if(u == v) return false;
		int a = merge(u, enemy[v]);
		int b = merge(v, enemy[u]);
		enemy[a] = b; enemy[b] = a;
		return true;
	}
	
	private static boolean ack(int u, int v) {
		u = find(u); v = find(v);
		if(u == enemy[v]) return false;
		int a = merge(u, v);
		int b = merge(enemy[u], enemy[v]);
		enemy[a] = b;
		if(b != -1) enemy[b] = a;
		return true;
	}
	
	//	enemy가 없는 두 개가 합쳐질 경우 -1이 return 될 수도 있음
	private static int merge(int u, int v) {
		if(u == -1 || v == -1) return Math.max(u, v);
		u = find(u);
		v =  find(v);
		if(u == v) return u;
		if(size[u] < size[v]) return merge(v, u);
		size[u] += size[v];
		parent[v] = u;
		return u;
	}
	
	private static int find(int u) {
		if(parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}
}
