
/**
 * Find the Maximum in a stack
 *
 * @author (Nitin Ramadoss)
 * @version (06/26/19)
 */
import java.util.*;
import java.io.*;

public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>(); //auxiliary Stack to hold all the calculated maximum values
        int max = Integer.MIN_VALUE;

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
                if(maxStack.empty() || !(value < max)){
                max = Math.max(value, max);
                maxStack.push(max);
            }
            } else if ("pop".equals(operation)) {
                 
                if(stack.peek().equals(maxStack.peek())){
                maxStack.pop(); //remove top element of maxStack
                max = maxStack.peek();
               }
                
               stack.pop();  
            } else if ("max".equals(operation)) {
                System.out.println(maxStack.peek());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}

