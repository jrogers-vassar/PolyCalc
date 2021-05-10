public interface Calculation extends Token
{
    
    /**
     * returns false for calculations
     * @return always false
     */
    public boolean isOperand();
    
    /**
     * sets value of polynomial a
     * @param Polynomial to set
     */
    public void setA(Polynomial a);
    
    /**
     * sets value of polynomial b
     * @param Polynomial to set
     */
    public void setB(Polynomial b);
    
    /**
     * returns false true for calculations 
     * @return always true
     */
    public boolean isOperator();
    
    /**
     * returns priority. add/sub has lower priority than mult/div
     * @return int represents priority 
     */
    public int getPriority();
    
    /**
     * operates on a and b
     * @return Polynomial result of operation 
     */
    public Polynomial operate();
}
     

