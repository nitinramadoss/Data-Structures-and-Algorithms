
/**
 * Build a heap data structure
 *
 * @author (Nitin Ramadoss)
 * @version (07/14/19)
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;
    private int size;
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
     new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
        size = data.length;
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        } 
      
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      BuildHeap();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }
    
    public double Parent(int i){return (i-1)/2;}  
    public int LeftChild(int i){return (2*i) + 1;}   
    public int RightChild(int i){return (2*i) + 2;}
    
    //my implementation of the Heap
    
    
   
   public void HeapSort(){
       BuildHeap();
       /*int heapSize = size -1;
       for(int n = 0; n <= size - 1; n++){
         swapIndex(0, heapSize);   
         heapSize--;
         SiftDown(0);
       } */
       
   }
   
   public void BuildHeap(){
       for(int i = size/2; i >= 0; i--){   
           SiftDown(i);     
        }         
   }
    
    public void SiftDown(int i){
       int minIndex = i;
       int left = LeftChild(i);
       if(left <= size - 1 && data[left] < data[minIndex]){
         minIndex = left;  
        }
       int right = RightChild(i);
       if(right <= size - 1 && data[right] < data[minIndex])
         minIndex = right;
       
       if(i != minIndex){
         swapIndex(i, minIndex);
         SiftDown(minIndex);
       }
   }
    
    public void swapIndex(int i, int j){  
       swaps.add(new Swap(i, j)); 
       int tmp = data[i];
       data[i] = data[j];
       data[j] = tmp;    
    }
    
    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

//comment test



    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

