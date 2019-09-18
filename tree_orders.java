
/**
 * Traverse a Binary Search Tree
 *
 * @author (Nitin Ramadoss)
 * @version (07/30/19)
 */
import java.util.*;
import java.io.*;
import java.io.PrintWriter;


public class tree_orders {
    
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

    public class TreeOrders {
        int n;
        int[] key, left, right;
        public Node[] tree;
        void read() throws IOException {
            FastScanner in = new FastScanner();
            
            n = in.nextInt();  
            key = new int[n];
            left = new int[n];
            right = new int[n];
            tree = new Node[n];
            for (int i = 0; i < n; i++) {  //array of keys, left childs, and right childs
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
                
                SetUpNodes(i, key[i]);
            }
            BuildTree(left, right);
            /*for(Node n: tree){
               System.out.println("Key: " + n.getKey()); 
               if(n.left != null)
               System.out.println("Left Child: " + n.left.key); 
               if(n.right != null)
               System.out.println("Right Child: " + n.right.key);   
            } */
            //tree traversal method calls
            Node root = tree[0];
            inOrder(root);
            System.out.println();
            preOrder(root);
            System.out.println();
            postOrder(root);   
        }
        
        public void SetUpNodes(int index, int key){
           tree[index] = new Node(key);     
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
        
        void inOrder(Node node) {
            if (node == null)
               return;
               
            inOrder(node.left);
            
            System.out.print(node.getKey() + " ");
            
            inOrder(node.right);
        }

        void preOrder(Node node) {
           if (node == null)
               return;
               
           System.out.print(node.getKey() + " ");
           
           preOrder(node.left);
           
           preOrder(node.right);
        }

        void postOrder(Node node) {
            if (node == null)
               return;
      
           postOrder(node.left);
           
           postOrder(node.right);
           
           System.out.print(node.getKey() + " ");
        }
        
        class Node{
           int key;
           Node left;
           Node right;

           Node(int key){
             this.key = key;    
           }
           
           public void setLeft(int left){
             this.left = tree[left];  
           }
           
           public void setRight(int right){
             this.right = tree[right];  
           }
           
           public int getKey(){
              return this.key; 
           }
        }
    }

    static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
    }


    public void run() throws IOException {
        TreeOrders Tree = new TreeOrders();
        Tree.read();  
    }
}
