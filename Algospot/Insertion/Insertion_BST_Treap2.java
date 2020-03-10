package Insertion;

public class Insertion_BST_Treap2 {
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
	
	private static Treap insert(Treap root, Treap target) {
		if(root == null) {
			root = target;
			return root;
		}
		
		if(root.priority < target.priority) {
			
		} else if(root.key < target.key) {
			root.setRight(insert(root.right, target));
		} else {
			root.setLeft(insert(root.left, target));
		}
		
		return root;
	}

	public static void main(String args[]) {
		Treap treap = new Treap(10);
	}
}
