

public class Monomial implements Comparable, Operand{
    private double base = 0;
    private int power = 0;
    
    /**
     * A monomial does not have priority. It is not an operator
     * @return int 0
     */
    public int getPriority(){
        return 0;
    }
    
  /**
   * constructor 
   */
    public Monomial(){};
    
    /**
     * A monomial cannot operate. It is not an operator 
     * @return null
     */
    
    public Polynomial operate(){
        return null;
    }
    
    /**
     * A monomial is not an operator 
     * @return false 
     */
    public boolean isOperator(){
        return false;
    }
    
    /**
     * A monomial is an operand
     * @return true
     */
    public boolean isOperand(){
        return true;
    }
    
    /**
     * constructor
     * @param double base of monomial
     * @param int power of monomial
     */
    public Monomial(double base, int power){
        this.base = base;
        this.power = power;
    }
    
    /**
     * getter for base field
     * @return double value of base
     */
    public double getBase(){
        return base;
    }
    
    /**
     * getter for power field
     * @return int value of power
     */
    public int getPower(){
        return power;
    }
    
    /**
     * setter for base
     * @param double value to set base as 
     */
    public void setBase(double f){
        base = f;
    }
    
    /**
     * setter for power
     * @param int value to set power as 
     */
    public void setPower(int i){
        power = i;
    }
    
    /**
     * Monomial implements compareTo based on the monomial's power.
     * This makes sorting a polynomial incredibly simple
     * @param Object "Object" is a requirement for this interface. Assumed to be a monomial
     * @return int -1 if class monomial > parameter, 0 if same, 1 if class monomial < parameter
     */
    public int compareTo(Object o){
       
        Monomial m = (Monomial) o;
           
        if (power > m.getPower()){
            return -1;
        }
        else if (power < m.getPower()){
            return 1;
        }
        
        return 0;
    }
    
    /**
     * multiplies two monomials
     * @param Monomial monomial to multiply by class monomial
     * @return Monomial result of operation 
     */
    public Monomial multiplyMonomial(Monomial a){
        Monomial m = new Monomial();
        m.base = base*a.base;
        m.power = power+a.power;
        return m;
    }
    
    /**
     * divides class monomial by parameter monomial
     * @param Monomial divisor
     * return Monomial result of operation 
     */
    public Monomial divideMonomial(Monomial a){
        Monomial m = new Monomial();
        m.base = base/a.base;
        m.setPower(getPower()-a.getPower());
        return m;
    }
    
    /**
     * this is what I came up with to avoid a polynomial looking like "3x^2 - -3x"
     * if a monomial is on its own or first in a polynomial, it should have the negative sign
     * otherwise the absolute power should be taken 
     * @return String representation of monomial on its own 
     */
    public String toStringAlone(){
        
        String s = "";
        boolean floored = false; // whether or not there is a decimal
        int intBase = 0;
        
        if(base == Math.floor(base)){
            floored = true;
            intBase = (int) base;
        }
        
        if (power == 0){
            if(floored) s+=intBase;
            else s += base;
        }
        else if (power == 1){
            if(base ==1){
                s = "x";
            }
            else if (base == -1) s = "-x";
            else{
                if(floored) s+=intBase+"x";
                else s = base+"x";
            }
        }
        else{
            if(base==1){
                s = "x^"+power;
            }
            else if (base == -1) s = "-x^"+power;
            else{
                if(floored) s+=intBase+"x^"+power;
                else s = base+"x^"+power;
            }
        }
        
        
        return s;
    }
    
    /**
     * this is what polynomials will use after the first term 
     * @return String representation of monomials in a polynomial
     */
    public String toString(){
        String s = "";
        boolean floored = false; // whether or not there is a decimal
        int intBase = 0;
        
        if(base == Math.floor(base)){
            floored = true;
            intBase = (int) base;
        }

       
        if (power == 0){
            if(floored) s+=Math.abs(intBase);
            else s += Math.abs(base);
        }
        else if (power == 1){
            if (base == 1){
                s = "x";
            }
            else if (base == -1) s = "-x";
            else{
                if(floored) s+=Math.abs(intBase)+"x";
                else s = Math.abs(base)+"x";
            }
        }
        else{
            if (base == 1) s = "x^"+power;
            else if (base == -1) s = "-x^"+power;
            else{ 
            if(floored) s+=Math.abs(intBase)+"x^"+power;
            else s = Math.abs(base)+"x^"+power;
        }
    }
            
        return s;
    }

            
    
}
