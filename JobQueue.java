
/**
 * Process multiple jobs with threads
 *
 * @author (Nitin Ramadoss)
 * @version (07/16/19)
 */
import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;
    private int[] priorityQueue;
    private int[] assignedWorker;
    private int size;
    private long[] nextFreeTime;
    
    
    private long[] startTime;
    private int count = 0;
    
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        nextFreeTime = new long[numWorkers];
        size = m;
        jobs = new int[m]; //holds the time
        priorityQueue = new int[m]; //holds the priority
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt(); //holds the time in seconds for each job
            priorityQueue[i] = i; //holds the priority for each job
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]); //prints the thread followed by a space and the start time of processing
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        BuildHeap();

    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        for(int i : jobs)
           ExtractMax();
           
        writeResponse();
        out.close();
    }
    
    public int Parent(int i){return (i-1)/2;}  
    public int LeftChild(int i){return (2*i) + 1;}   
    public int RightChild(int i){return (2*i) + 2;}
    
    public void BuildHeap(){
       for(int i = size/2; i >= 0; i--){   
           SiftDown(i);     
        }         
    }
    
    public void SiftUp(int i){
      while(i > 0 && priorityQueue[Parent(i)] < priorityQueue[i]){  
       swapNodes(priorityQueue[Parent(i)],priorityQueue[i]); 
       i = Parent(i); 
      } 
    }
    
    public void SiftDown(int i){
       int minIndex = i;
       int left = LeftChild(i);
       if(left <= size - 1 && priorityQueue[left] < priorityQueue[minIndex]){
         minIndex = left;  
        }
       int right = RightChild(i);
       if(right <= size - 1 && priorityQueue[right] < priorityQueue[minIndex])
         minIndex = right;
       
       if(i != minIndex){
         swapNodes(i, minIndex);
         SiftDown(minIndex);
       }
    }
    
    public void swapNodes(int i, int j){   
       int tmp = priorityQueue[i];
       priorityQueue[i] = priorityQueue[j];
       priorityQueue[j] = tmp;    
    }
    
    public void ExtractMax(){
      /*if(count < numWorkers)
        startTime[count] = 0;
      else
        startTime[count % numWorkers] += jobs[count - numWorkers];  */
        
      //calculate bestWorker  
      int duration = jobs[count];
      int bestWorker = 0;
      for (int j = 0; j < numWorkers; ++j) {
          if (nextFreeTime[j] < nextFreeTime[bestWorker])
          bestWorker = j;
      }
      assignedWorker[count] = bestWorker;
      startTime[count] = nextFreeTime[bestWorker];
      nextFreeTime[bestWorker] += duration;     
 
      int root = priorityQueue[0];
      priorityQueue[0] = priorityQueue[size-1];
      size--;
      
      count++;
      SiftDown(0); 
    }
    
    
    
    

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
