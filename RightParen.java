public class RightParen implements Token{
    /**
     * RightParen cannot operate
     * @return null
     */
    public Polynomial operate(){ return null;}
    
    /**
     * RightParen has no priority, is not an operator
     * @return int 0
     */
    public int getPriority(){
        return 0;
    }
    
    /**
     * RightParen is not an operator
     * @return false
     */
    public boolean isOperator(){
        return false;
    }
    
    /**
     * RightParen is not an operand 
     * @return false
     */
    public boolean isOperand(){
        return false;
    }
    
    /**
     * String representation of RightParen
     * @return ")"
     */
    public String toString(){
        String s = ")";
        return s;
    }
}