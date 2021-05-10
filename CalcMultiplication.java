public class CalcMultiplication implements Calculation{
    Polynomial a = new Polynomial();
    Polynomial b = new Polynomial();
    
    
    /**
     * constructor
     */
    public CalcMultiplication(){};
    
    /**
     * sets value of polynomial a
     * @param Polynomial to set
     */
    public void setA(Polynomial a){
        this.a = a;
    }
    
    /**
     * sets value of polynomial b
     * @param Polynomial to set
     */
    public void setB(Polynomial b){
        this.b = b;
    }
    
    /**
     * returns priority for given calculation
     * @return int representation of priority. higher than add/sub
     */
    public int getPriority(){
        return 2;
    }
    
    /**
     * operates on a and b
     * @return result of operation 
     */
    public Polynomial operate(){
        Polynomial result = a.multiplyPolynomial(b);
        return result;
    }
    
    /**
     * returns false true for calculations 
     * @return always true
     */
    public boolean isOperator(){
        return true;
    }
    
    /**
     * returns false for calculations
     * @return always false
     */
    public boolean isOperand(){
        return false;
    }
    
    /**
     * subtraction to string
     * @return String "*";
     */
    public String toString(){
        String s = "*";
        return s;
    }
}