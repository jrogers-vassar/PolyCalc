public class CalcAddition implements Calculation{
    Polynomial a = new Polynomial();
    Polynomial b = new Polynomial();
    
    /**
     * returns priority. add/sub has lower priority than mult/div
     * @return int represents priority 
     */
    public int getPriority(){
        return 1;
    }
    
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
     * constructor 
     */
    
    public CalcAddition(){};

    /**
     * operates on a and b
     * @return result of operation 
     */
    public Polynomial operate(){
        Polynomial result = a.addPolynomial(b);
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
     * @return String "+";
     */
    public String toString(){
        String s = "+";
        return s;
    }
}

