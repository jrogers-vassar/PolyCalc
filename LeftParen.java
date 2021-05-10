public class LeftParen implements Token{
    /**
     * LeftParen cannot operate
     * @return null
     */
    public Polynomial operate(){ return null;}
    
    /**
     * LeftParen has no priority, is not an operator
     * @return int 0
     */
    public int getPriority(){
        return 0;
    }
    
    /**
     * LeftParen is not an operator
     * @return false
     */
    public boolean isOperator(){
        return false;
    }
    
    /**
     * LeftParen is not an operand 
     * @return false
     */
    public boolean isOperand(){
        return false;
    }
    
    /**
     * String representation of LeftParen
     * @return "("
     */
    public String toString(){
        String s = "(";
        return s;
    }
}