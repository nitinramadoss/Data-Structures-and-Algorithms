
/**
 * Tree Data Structure
 *
 * @author (Nitin Ramadoss)
 * @version (06/24/19)
 */
import java.util.*;
import java.io.*;
import java.util.LinkedList.*;
import java.util.ArrayList;
class TreeHeight
{
   public static void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new TreeHeight().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
               
            }
    
   public void run() throws IOException {
        Height th = new Height();
        Height.FastScanner scanner = th.new FastScanner();
        Height.CalcHeight tree = th.new CalcHeight();
        tree.read();
        


}


class Height {
    public class FastScanner { //nested class
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
        
    public class CalcHeight{ //nested class, non-static inner class
        int n; //number of nodes
        int parent[];
        int level = 0;
        int nextIndex = 0;
        int[] depths; 
        Queue<Node> queue = new LinkedList();
        
        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n]; //fills parent array with input
            
       }
       
       void height(){
          
           for(int i : parent){
           if(i == -1){
           queue.add(new Node(1, parent[0],parent.indexOf(i)));    
           }
    }
           
           
       }
        
     int addNode(int index){
         
            
      } 
            
            
      }  
     
 }
}

public class Node{
   private int depth;
   private int key;
   private int index;
   
   Node(int depth, int key, int index){
       this.depth = depth;
       this.key = key;
       this.index = index;
   }
   
   public int getDepth(){
       return this.depth;
    }
   public int getKey(){
       return this.key;
    }
   public int getIndex(){
       return this.index;
    }   
}
