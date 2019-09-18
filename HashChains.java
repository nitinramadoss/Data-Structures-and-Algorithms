
/**
 * Create a Hash Table utilizing the chaining scheme
 *
 * @author (Nitin Ramadoss)
 * @version (07/26/19)
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ListIterator;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    
    private List<String>[] HashTable;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                if (!CheckHashTable(query.s)) //if it is not in the HashTable then add the String
                    Add(query.s);
                break;
            case "del":
                if (CheckHashTable(query.s)) //if element is in the HashTable then delete it
                    Delete(query.s);
                break;
            case "find":
                writeSearchResult(CheckHashTable(query.s));
                break;
            case "check":
                int index = query.ind;  
                if(HashTable[index] != null){
                for (String s: HashTable[index])
                        out.print(s + " ");
                } 
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        HashTable = new List[bucketCount]; //initialize cardinality of Hash Table
        //create empty chains
        for (int i = 0; i < bucketCount; i++)
            HashTable[i] = new ArrayList<String>(); 
        
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }
    
    public Boolean CheckHashTable(String query){
      boolean wasFound = false;
      if(!HashTable[hashFunc(query)].isEmpty()){
      for(ListIterator<String> iter = HashTable[hashFunc(query)].listIterator(); iter.hasNext();){
        if(iter.next().equals(query))
        return !wasFound;  
      }
    }
       return wasFound;
    }
    
    public void Add(String query){
      HashTable[hashFunc(query)].add(0, query);
    }
    
    public void Delete(String query){  
      HashTable[hashFunc(query)].remove(query);  
    }
    
    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
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

