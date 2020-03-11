package Insertion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Insertion_BST_Treap {
	
	public static class TreapPair {
		Treap left, right;
		public TreapPair (Treap left, Treap right) {
			this.left = left;
			this.right = right;
		}
	}
	
	public static class Treap {
		Treap left, right;
		int key, size;
		double priority;
		
		public Treap(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.size = 1;
			this.priority = Math.random();
		}
		
		public void setLeft(Treap treap) {
			this.left = treap;
			calcSize();
		}
		
		public void setRight(Treap treap) {
			this.right = treap;
			calcSize();
		}
		
		public void calcSize() {
			this.size = 1;
			if(this.left != null) this.size += this.left.size;
			if(this.right != null) this.size += this.right.size;
		};
	}

	private static TreapPair split(Treap root, int key) {
		if(root == null) return new TreapPair(null, null);
		
		if(root.key < key) {
			TreapPair res = split(root.right, key);
			root.setRight(res.left);
			return new TreapPair(root, res.right);
		}
		TreapPair res = split(root.left, key);
		root.setLeft(res.right);
		return new TreapPair(res.left, root);
	}
	
	//	root에 node삽입
	private static Treap insert(Treap root, Treap node) {
		if(root == null) return node;
		
		if(root.priority < node.priority) {
			TreapPair splitted = split(root, node.key);
			node.setLeft(splitted.left);
			node.setRight(splitted.right);
			return node;
		} else if(root.key < node.key) {
			root.setRight(insert(root.right, node));
		} else {
			root.setLeft(insert(root.left, node));
		}
		
		return root;
	}
	
	//	기본가정 max(a) < min(b)
	private static Treap merge(Treap a, Treap b) {
		if(a == null) return b;
		if(b == null) return a;
		
		if(a.priority < b.priority) {
			b.setLeft(merge(a, b.left));
			return b;
		}
		a.setRight(merge(a.right, b));
		return a;
	}
	
	//	root에서 key삭제
	private static Treap erase(Treap root, int key) {
		if(root == null) return root;
		
		if(root.key == key) {
			Treap remakeRoot = merge(root.left, root.right);
			root = null;
			return remakeRoot;
		}
		
		if(key < root.key) {
			root.setLeft(erase(root.left, key));
		} else {
			root.setRight(erase(root.right, key));
		}
		return root;
	}
	
	//	크기순으로(중위순회) k번째 노드
	private static Treap kth(Treap root, int k) {
		int leftSize = 0;
		if(root.left != null) leftSize = root.left.size;
		if(k <= leftSize) return kth(root.left, k);
		if(k == leftSize+1) return root;
		return kth(root.right, k - leftSize - 1);
	}
	
	//	key보다 작은 노드의 개수
	private static int countLessThan(Treap root, int key) {
		if(root == null) return 0;
		
		if(root.key >= key) {
			return countLessThan(root.left, key);
		}
		int ls = (root.left != null) ? root.left.size : 0;
		return ls + 1 + countLessThan(root.right, key);	
	}

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder ssb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			
			//	init
			Treap root = new Treap(1);
			for(int j=2; j<=n; ++j) {
				root = insert(root, new Treap(j));
			}
			
			int []arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			int []res = new int[n];
			for(int j=n-1; j>=0; --j) {
				Treap node = kth(root, j + 1 - arr[j]);
				root = erase(root, node.key);
				res[j] = node.key;
			}
			
			for(int j=0; j<n; ++j) {
				sb.append(res[j] + " ");
			}
			
			ssb.append(sb.substring(0, sb.length() - 1) + "\n");
		}
		System.out.print(ssb.substring(0, ssb.length() - 1));
	}
}
