
/**
 * Determine if a binary tree is a binary search tree
 *
 * @author (Nitin Ramadoss)
 * @version (08/03/19)
 */
import java.util.*;
import java.io.*;
public class is_bst_hard {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    boolean notBST = false;
    
    public class IsBST {
        class Node {
           int key;
           Node left;
           Node right;

           Node(int key){
             this.key = key;    
           }
           
           public void setLeft(int left){
             this.left = tree[left];
             if(this.left != null && this.left.key >= this.key)
             notBST = !notBST;
           }
           
           public void setRight(int right){
             this.right = tree[right];  
           }
           
           public int getKey(){
              return this.key; 
           }
        }

        int nodes;
        Node[] tree;
        List<Integer> sort = new ArrayList();
        
        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            int key = 0;
            int[] left = new int[nodes];
            int[] right = new int[nodes];
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < nodes; i++) {
                key = in.nextInt(); 
                left[i] = in.nextInt();
                right[i] = in.nextInt();
                SetUpNodes(i, key);
            }

            BuildTree(left, right);
            
            
           if(tree.length != 0)
           inOrder(tree[0]); // in order sort the tree
            
        }
        
        public void BuildTree(int[] left, int[] right){
           int counter = 0;
           for(Node n : tree){
            if(left[counter] == -1)
               n.left = null;
            else
               n.setLeft(left[counter]);
               
            if(right[counter] == -1)
               n.right = null; 
            else
               n.setRight(right[counter]);
            
             counter++;    
           }
        }
        
        public void SetUpNodes(int index, int key){
           tree[index] = new Node(key);     
        }
        
        boolean isBinarySearchTree() {
          // Implement correct algorithm here
          if(tree.length == 0 || tree.length == 1)
            return true;
            
          for(int i = 1; i < sort.size(); ++i)
            if(sort.get(i-1) > sort.get(i))
                return false;
          return true;
        } 
        
        void inOrder(Node node) { 
            if (node == null)
               return;
            inOrder(node.left);

            //System.out.print(node.getKey() + " " );
            sort.add(node.getKey());
            inOrder(node.right);
            
            
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if(notBST == false){
            if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
            } else {
            System.out.println("INCORRECT");
            } 
        }else {
            System.out.println("INCORRECT");
        }
    }
}

