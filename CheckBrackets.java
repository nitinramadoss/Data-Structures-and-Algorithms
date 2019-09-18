
/**
 * Check Brackets of String
 *
 * @author (Nitin Ramadoss)
 * @version (06/23/19)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
class CheckBrackets{
   public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream); 
        String text = reader.readLine();
       
        Boolean incorrectClosingBracket = false; //variables or objects I added
        String output = "Success";      
        
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);    
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                Bracket openBracket = new Bracket(next, position);
                opening_brackets_stack.push(openBracket);
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if(!opening_brackets_stack.empty() && opening_brackets_stack.peek().Match(next)){
                opening_brackets_stack.pop();
                }else{   
                incorrectClosingBracket = true;
                output = String.valueOf(position+1);
                break;
                }
                           
            }
        }

        if(!opening_brackets_stack.empty() && !incorrectClosingBracket){
            
            if(opening_brackets_stack.size() != 1){
              for(int i = opening_brackets_stack.size()-1; i >= 1; i--) //reduce stack to find position of first unmatched open bracket
              opening_brackets_stack.pop();
            }
        output = String.valueOf(opening_brackets_stack.peek().position + 1);
       }
       
        //print output
        System.out.println(output);
    }  
}

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

  
    
    




