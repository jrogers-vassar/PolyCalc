import java.util.*;


public class PolyCalc{
    
    LinkedList<Token> tokens = new LinkedList<Token>(); 
    HashMap<Character, Token> memory 
        = new HashMap<Character, Token>();
   
        
    /**
     * Utilizes read, toPostfix and evaluate
     * 
     * @param String The string to be read
     * @return Token (Polynomial) that is evaluated
     */
    public Token readString(String s){
        if(!(tokens.size() == 0)){
            for(int i = 0; i<tokens.size(); i++){
            tokens.remove(i);
        }
    }
        if(read(s)){ // FSM, tokens placed into ArrayList
        if(toPostfix()){ // Tokens converted to postfix, parentheses removed
        if(evaluate()){ // Tokens put into stack and evaluated two at a time
        Token p = tokens.get(0);
        if(p instanceof Polynomial){ // this is in order to simplify the result
            Polynomial poly = (Polynomial) p;
            poly.SimplifyTerms();
            p = poly;
        }
        return p;
        
        
    }
    }
}

        System.out.println("Invalid Expression\n");
        tokens.clear();

        return null; // "null" expressions will be discarded in interactive mode
        
        
    }
    
    /**
     * Same as above method but for GUI. Does not println errors
     * @param String input string
     * @return Token (Polynomial) that is returned 
     */
    public Token readStringGUI(String s){
        if(!(tokens.size() == 0)){
            for(int i = 0; i<tokens.size(); i++){
            tokens.remove(i);
        }
    }
        if(read(s)){ // FSM, tokens placed into ArrayList
        if(toPostfix()){ // Tokens converted to postfix, parentheses removed
        if(evaluate()){ // Tokens put into stack and evaluated two at a time
        Token p = tokens.get(0);
        if(p instanceof Polynomial){ // this is in order to simplify the result
            Polynomial poly = (Polynomial) p;
            poly.SimplifyTerms();
            p = poly;
        }
        return p;
        
        
    }
    }
}

        tokens.clear();

        return null; // "null" expressions will be discarded in interactive mode

    }
        
    
    
    /**
     * Constructor for a PolyCalc
     */
    public PolyCalc(){};
    
    /*
     * Checks if given char is a valid operator
     * @param char character to be checked
     * @return true if valid false if not 
     */
    public static boolean isOperator(char c){
        if (c=='+' || c=='-' || c=='*' || c=='/'){
            return true;
        }
        return false;
    }
    
    /**
     * Takes a char and turns it into the appropriate token (Calculation)
     * @param char character to be evaluated
     * @return Token (Calculation) of corresponding operator 
     */
    public Token whatOperator(char c){
        Token token = null;
        if(c=='+') token = new CalcAddition();
        if(c=='-') token = new CalcSubtraction();
        if(c=='*') token = new CalcMultiplication();
        if(c=='/') token = new CalcDivision();
        return token;
    }
    
    /**
     * Shortcut for turning strings into Polynomials of a single monomial length
     * @param String base of monomial
     * @param String power of monomial
     * @return Polynomial of given base and power
     */
    public Polynomial makeMono(String base, String power){
        double intbase = 1;
        if(!base.equals("")){
        intbase = Double.parseDouble(base);
    }
        int intpower = Integer.parseInt(power);
        Monomial m = new Monomial(intbase, intpower);
        Polynomial p = new Polynomial();
        p.addTerm(m);
        return p;
    }
    

    /**
     * Here she is, the FSM. Takes in a string input, goes through char by char
     * and places them in a list of tokens (field tokens)
     * @param String input String to be checked
     * @return boolean whether or not it is a valid expression
     */
    public boolean read(String input){

        State curState = State.WCALC;
        input = input.trim();
        input = input.replaceAll(" ", "");
        
        int i = 0;
        
        int inputLen  = input.length();
        
        String base = ""; // method of building monomial tokens 
        String power = ""; // puts chars into a string which are then parsed and monomialized
        
        while(curState != State.ERROR && i<inputLen){
            char c = input.charAt(i);
            i++;
            
            switch (curState){
                
                case WCALC: // explanations of states can be found in "State" class
                    if(c=='('){
                        curState = State.LPAREN;
                        tokens.add(new LeftParen());
                    }
                        
                    else if (Character.isDigit(c)){
                        curState = State.COEF;
                        base+=c;
                        if(i==inputLen){ // this if statement will come up a lot.
                                         // essentially it means if this is the last token, make one more mono
                                         // before leaving the loop.
                            tokens.add(makeMono(base, "0"));
                        }
                            
                    }
                        
                    else if(c=='x'){ 
                        curState = State.VARIABLE;
                        if(i==inputLen){
                            tokens.add(makeMono("1","1"));
                        }
                    }
                        
                    else if (c=='-'){
                        curState = State.WNEG;
                    }
                        
                    
                    else curState = State.ERROR;
                    break;
                    
                case LPAREN:
                    if (Character.isDigit(c)){
                        curState = State.COEF;
                        base+=c;
                    }  
                    else if(c=='('){
                        curState = State.LPAREN;
                        tokens.add(new LeftParen());
                    }
                    else if(c=='x') curState = State.VARIABLE;
                        
                    else if(c=='-'){
                        curState = State.WNEG;
                        base+='-';
                    }
                        
                    else curState = State.ERROR;
                    break; 
                
                case WNEG:
                    if(Character.isDigit(c)){
                        curState = State.COEF;
                        
                        if(!(base.equals("-"))) base+='-';
                        base+=c;
                        
                        if(i==inputLen){
                            tokens.add(makeMono(base, "0"));
                        }
                    }
                    else if (c=='('){
                        curState = State.LPAREN;
                        tokens.add(makeMono("-1","0"));
                        tokens.add(new CalcMultiplication()); // i.e -(x+1) = -1*(x+1)
                        tokens.add(new LeftParen());
                    }
                    else if (c=='x'){
                        curState = State.VARIABLE;
                        if(i==inputLen){
                            tokens.add(makeMono("-1","1"));
                        }
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                    
                case COEF:
                    
                    
                    if(Character.isDigit(c)){
                        curState = State.COEF;
                        base+=c;
                        if( i == inputLen){
                            tokens.add(makeMono(base,"0"));
                        }
                    }
                    else if(c=='.'){
                        curState = State.WDEC;
                        base+='.';
                    }
                    else if(c=='x'){
                        curState = State.VARIABLE;
                        if(i == inputLen){ 
                            tokens.add(makeMono(base, "1"));                            
                        }
                        
                    }
                    else if(isOperator(c)){
                        curState = State.WOPERATOR;
                        tokens.add(makeMono(base, "0"));
                        tokens.add(whatOperator(c));
                        // everytime base/power are used, they are reset 
                        // to prepare to make the next one if necessary
                        base="";
                        power="";    
                    }
                    else if(c==')'){
                        curState = State.RPAREN;
                        tokens.add(makeMono(base, "0"));
                        tokens.add(new RightParen());
                        base="";
                        power="";
                    }
             
                    else{
                        curState = State.ERROR;
                    }
                    break;
                case WDEC:
                    if(Character.isDigit(c)){
                        curState = State.DEC;
                        base+=c;
                        if(i==inputLen){// this is the last char
                            tokens.add(makeMono(base, "0"));
                        }
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                case DEC:
                    if(Character.isDigit(c)){
                        curState = State.DEC;
                        base+=c;
                    }
                    else if(c=='x'){
                        curState = State.VARIABLE;
                        if(i == inputLen){ // this is the last char
                            tokens.add(makeMono(base, "1"));                            
                        }
                    }
                    else if(isOperator(c)){
                        curState = State.WOPERATOR;
                        tokens.add(makeMono(base, "0"));
                        base="";
                        power="";
                        tokens.add(whatOperator(c));
                    }
                    else if(c==')'){
                        curState = State.RPAREN;
                        tokens.add(makeMono(base, "0"));
                        base="";
                        power="";
                        tokens.add(new RightParen());
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                case VARIABLE:
                    if(c=='^') curState = State.WPOWER;
                    else if(isOperator(c)){
                        curState = State.WOPERATOR;
                        tokens.add(makeMono(base, "1"));
                        base="";
                        power="";
                        tokens.add(whatOperator(c));
                    }
                    else if(c==')'){
                        curState = State.RPAREN;
                        tokens.add(makeMono(base, "1"));
                        base="";
                        power="";
                        tokens.add(new RightParen());
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                case WPOWER:
                    if(Character.isDigit(c)){
                        curState = State.POWER;
                        power+=c;
                        if(i == inputLen){ // this is the last char
                            tokens.add(makeMono(base, power));                            
                        }
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                    
                case POWER:
                    if(Character.isDigit(c)){
                        curState = State.POWER;
                        power+=c;
                        
                        if(i == inputLen){
                            tokens.add(makeMono(base, power));
                        }
                    }
                    else if(isOperator(c)){
                        curState = State.WOPERATOR;
                        if(base.equals("-")){ // this is the last char
                            tokens.add(makeMono("-1", power));
                        }
                        else tokens.add(makeMono(base, power));
                        base="";
                        power="";
                        
                            
                        tokens.add(whatOperator(c));
                    }
                    else if(c==')'){
                        curState = State.RPAREN;
                        tokens.add(makeMono(base, power));
                        base="";
                        power="";
                        tokens.add(new RightParen());
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                    
                case RPAREN:

                    if(isOperator(c)){
                        curState = State.WOPERATOR;
                        tokens.add(whatOperator(c));
                    }
                    else if(c==')'){
                        curState = State.RPAREN;
                        tokens.add(new RightParen());
                    }
                    else if(c=='('){
                        curState = State.LPAREN;
                        tokens.add(new CalcMultiplication()); // (x+1)(x+1)
                        tokens.add(new LeftParen());
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;
                    
                case WOPERATOR:
                    if(c=='('){
                        curState = State.LPAREN;
                        tokens.add(new LeftParen());
                    }
                    else if(Character.isDigit(c)){
                        curState = State.COEF;
                        base+=c;
                        if(i==inputLen){
                            tokens.add(makeMono(base, "0"));
                        }
                    }
                    else if(c=='x'){
                        curState = State.VARIABLE;
                        if(i==inputLen){ // this is the last char
                            tokens.add(makeMono(base, "1"));
                        }
                    }
                    else{
                        curState = State.ERROR;
                    }
                    break;        
            }
        }
        if(curState.isAccept())
            return true;
            
       
        tokens.clear(); // get rid of all tokens, we don't want them for later calculations
        return false;
    }
    
        
    /**
     * Converts tokens to postfix form
     * @return boolean whether or not it was successful
     */
    public boolean toPostfix(){
        LinkedList<Token> result = new LinkedList<Token>();
        ArrayDeque<Token> stack = new ArrayDeque<Token>();
        for(int i = 0; i<tokens.size(); i++){
            Token token = tokens.get(i);
            
            if(token.isOperand()){
                result.add(token);
            }
            
            else if (token instanceof LeftParen){
                stack.addFirst(token);
            }
            else if (token instanceof RightParen){
                while(!stack.isEmpty() && !(stack.getFirst() instanceof LeftParen)){
                    result.add(stack.removeFirst());
                }
                if(stack.isEmpty()){
                    System.out.println("ERROR: Missing Parentheses");
                    tokens.clear();
                    return false;
                }
                stack.removeFirst();
            }
            
            else if (token.isOperator()){
                while (!stack.isEmpty() && !(stack.getFirst() instanceof LeftParen)
                    && stack.getFirst().getPriority() >= token.getPriority()){
                        result.add(stack.removeFirst());
                    }
                    stack.addFirst(token);
                }
        }
        
        while(!stack.isEmpty()){
            Token token = stack.remove();
            if(token instanceof LeftParen){
                System.out.println("ERROR: No right paren");
                tokens.clear();
                return false;
            }
            result.add(token);
        }
        tokens = result;
        return true;
    }
        /**
         * Evaluates postfix expression using a stack of operands
         * @return boolean whether or not evaluation was successful
         */
        public boolean evaluate(){
            LinkedList<Token> result = new LinkedList<Token>();
            ArrayDeque<Token> stack = new ArrayDeque<Token>();
            for(int i = 0; i<tokens.size(); i++){
                Token currToken = tokens.get(i);
                
                if(currToken.isOperand()){
                    stack.addFirst(currToken);
                }
                else if (currToken.isOperator()){
                    
                    Polynomial p1 = new Polynomial();
                    Polynomial p2 = new Polynomial();
                    
                    if(stack.isEmpty()){ // something has gone horribly wrong
                        System.out.println("Could not be evaluated");
                        return false;
                    }
                    
                    if(stack.getFirst() instanceof Polynomial){
                        p1 = (Polynomial) stack.removeFirst();
                        p1.SimplifyTerms();
                    }
                    
                    if(stack.isEmpty()){
                        System.out.println("Could not be evaluated");
                        return false;
                    }
                    
                    if(stack.getFirst() instanceof Polynomial){
                        p2 = (Polynomial) stack.removeFirst();
                        p2.SimplifyTerms();
                    }
                    
                    if(currToken instanceof Calculation && !(p1 == null) && !(p2 == null)){
                        Calculation c = (Calculation) currToken;
                        c.setA(p2);
                        c.setB(p1);
                        Polynomial p3 = c.operate();
                        if(!(p3 == null)){
                        p3.SimplifyTerms();
                        p3.sortPolynomial();
                        stack.addFirst(p3);
                        }
                        else{
                            return false;
                        }
                    }
                    
                }
                
            }
            if(!(stack.isEmpty())){
            result.add(stack.removeFirst());
        }
            tokens = result;
            return true; // we did it woohoo
        }  
        
        /**
         * Home screen
         */
        public static void startUp(){
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to Jack's Polynomial Calculator");
            System.out.println("Input 1 for Interactive Mode");
            System.out.println("Input 2 for File Reader Mode");
            System.out.println("Input 3 for Help");
            System.out.println("Input anything else to exit");
            String input = scan.nextLine();
            if(input.equals("1")){
                interactiveMode();
            }
            else if(input.equals("2")){
                fileReaderMode();
            }
            else if (input.equals("3")){
                help();
            }
            else{
                System.out.println("Goodbye");
            }
            
        }
        
        /**
         * Displays help for polynomial calculator 
         */
        public static void help(){
            System.out.println("You selected the Help page");
            System.out.println("Calculator can add, subtract, multiply and divide polynomials");
            System.out.println("You can save variables in the form n = ... , where n is any letter besides x and ... is a polynomial expression\n");
            System.out.println("Parentheses are supported to control order of operations");
            System.out.println("Division will fail if there is a reminder or if the divisor is 0");
            
            System.out.println("Interactive Mode allows you to manually type in expressions");
            System.out.println("File Reader Mode will take in a path to a text file and evaluate any found polynomial expressions");
            System.out.println("Enjoy!\n");
            
            startUp();
        }
        
        /**
         * Mode where users can manually input expressions, and through readString() they are evaluated
         * Also allows for the storage of variables in the field memory
         */
        
        public static void interactiveMode(){
            Scanner scan = new Scanner(System.in);
            PolyCalc calc = new PolyCalc();
            
            System.out.println("You have selected interactive mode");
 
            boolean keepGoing  = true; // user has not said stop
            while(keepGoing){
            System.out.println("Please input a calculation or store a variable");
            System.out.println("Input 'stop' to return to the home menu");
            String line = scan.nextLine();
            
            if(line.equals("stop")){
                System.out.println("Returning to home menu \n");
                keepGoing = false;
                startUp();
                break;
            }
            else{
            System.out.println("String detected: "+line);
            line = line.replaceAll(" ", "");
            line = line.trim();
            
            if(line.contains("=")){
                char c = line.charAt(0);
                if(c=='x'){
                    System.out.println("x is not a valid variable");
                }
                else if(c=='^'){
                    System.out.println("^ is not a valid variable");
                }
                else if(Character.isDigit(c)){
                    System.out.println("Numbers can't be variables");
                }
                else{ // see ReadFile class for comments on this code, it's about the same 
                line = line.substring(2, line.length());
                for(char key : calc.memory.keySet()){
                    if(line.indexOf(key) >= 0){
                        String keystring = String.valueOf(key);
                        line = line.replaceAll(keystring, "("+keystring+")");
                        line = line.replaceAll(keystring, calc.memory.get(key).toString());
                        
                    }
            }
                Token t = calc.readString(line);
                if(t!=null){
                calc.memory.put(c, t);
                System.out.println("Variable "+c+" has been set to "+t+"\n");
            }
            }
        }
            
            else{
                for(char key : calc.memory.keySet()){
                    if(line.indexOf(key) >= 0){
                        String keystring = String.valueOf(key);
                        line = line.replaceAll(keystring, "("+keystring+")");
                        line = line.replaceAll(keystring, calc.memory.get(key).toString());
                    }
            }
            Token t = calc.readString(line);
      
            if(t!=null){
            System.out.println(t+"\n");
        }
        }
                                 

        }
    }
        }
        
        /**
         * Mode uses ReadFile class to read and evaluate a given file
         */
        public static void fileReaderMode(){
            ReadFile filer = new ReadFile();
            Scanner scan = new Scanner(System.in);
            System.out.println("You have selected File Reader Mode");
            System.out.println("Please input the source to a text file");
            String s = scan.nextLine();
            filer.readFile(s);
            System.out.println("Goodbye");
            
        }
        /**
         * main
         */
        public static void main(String[] args){
           
            startUp();

         
    }
   
    }
       
    



        
    
    
