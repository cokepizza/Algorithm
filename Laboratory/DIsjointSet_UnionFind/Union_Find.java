package DIsjointSet_UnionFind;

public class Union_Find {
	private static int []parent;
	private static void merge(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA != parentB) {
			if(parent[parentA] < parent[parentB]) {
				parent[parentA] += parent[parentB];
				parent[parentB] = parentA;
			} else {
				parent[parentB] += parent[parentA];
				parent[parentA] = parentB;
			}
		}
	}

	private static int find(int idx) {
		if(parent[idx] < 0) return idx;
		return parent[idx] = find(parent[idx]);
	}
}
