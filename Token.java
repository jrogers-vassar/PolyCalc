public interface Token{
    
    /**
     * determines whether or not this token is an operand
     * @return boolean
     */
    public boolean isOperand();
    
    /**
     * determines whether or not this token is an operator
     * @return boolean
     */
    public boolean isOperator();
    
    /**
     * determines this tokens priority (for operators)
     * @return int representation of priority. 2 for mult/div 1 for add/sub
     */
    public int getPriority();
    
    /**
     * String representation of token
     * @return String 
     */
    public String toString();
}