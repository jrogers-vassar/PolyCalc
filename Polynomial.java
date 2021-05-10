import java.util.*;

public class Polynomial implements Operand{
    
    private ArrayList<Monomial> termslist = new ArrayList<Monomial>();

    public int getPriority(){
        return 0;
    }
   
    public Polynomial(){};
    
    public Polynomial operate(){
        return null;
    }
    
    public boolean isOperator(){
        return false;
    }
    
    public boolean isOperand(){
        return true;
    }
    
    // adds the terms of another polynomial to this polynomial
    // i.e (5x^2+x+1) and (3x^2+2x+5) -> (5x^2+x+1+3x^2+2x+5)
    
    public void addTerms(Polynomial p){
        for (int i = 0; (i<p.termslist.size()); i++){
            termslist.add(p.termslist.get(i));
        }
    }
    
    public Polynomial addPolynomial(Polynomial p){
        Polynomial result = new Polynomial();
        this.SimplifyTerms();
        p.SimplifyTerms();
        result.addTerms(this);
        result.addTerms(p);
        result.SimplifyTerms();
        return result;
    }
    
    
    public Polynomial makeNeg(){
        Polynomial result = new Polynomial();
        for(int i = 0; i < termslist.size(); i++){
            Monomial m = termslist.get(i);
            Monomial m2 = new Monomial(m.getBase()*-1, m.getPower());
            result.addTerm(m2);
        }
        return result;
    }
    
    public Polynomial subPolynomial(Polynomial p){
        Polynomial result = new Polynomial();
        this.SimplifyTerms();
        Polynomial negp = p.makeNeg();
        result.addTerms(this);
        result.addTerms(negp);
        result.SimplifyTerms();
        return result;
    }
        
    
    public Polynomial multiplyPolynomial(Polynomial p){
        Polynomial result = new Polynomial();
        for (int i = 0; i<termslist.size(); i++){
           for (int j = 0; j<p.termslist.size(); j++){
               Monomial m = termslist.get(i).multiplyMonomial(p.termslist.get(j));
               result.addTerm(m);
            }
    }
    result.SimplifyTerms();
    return result;
}
    
    // divides class polynomial by given polynomial p
    
    public Polynomial dividebyPolynomial(Polynomial p){
        sortPolynomial();
        
        Polynomial result = new Polynomial();
        Polynomial remainder = new Polynomial();
        remainder.termslist = termslist;
        
        if(termslist.size()>0){
        if(p.termslist.size()>0){
        if(termslist.get(0).getBase() != 0){
        if(p.termslist.get(0).getBase() != 0){
        while (remainder.termslist.size() != 0
            && remainder.termslist.get(0).getPower() >= p.termslist.get(0).getPower()) {  
               
               Monomial leadR = remainder.termslist.get(0);
               Monomial leadD = p.termslist.get(0);
                Monomial t = leadR.divideMonomial(leadD);
                Polynomial tpoly = new Polynomial();
                tpoly.addTerm(t);
                
                result.addTerm(t);
                
                Polynomial s = tpoly.multiplyPolynomial(p);
              
                s = s.makeNeg();
                remainder.addTerms(s);
                remainder.SimplifyTerms();
                remainder.sortPolynomial();
                
                if(remainder.termslist.get(0).getBase() == 0){
                    remainder.termslist.remove(0);
                }
               
                remainder.sortPolynomial();
            }
            
}
  
        else{
        System.out.println("Divide by Zero Error");
    }
    }
    else{
    Monomial m = new Monomial(0, 0);
    result.addTerm(m);
}
}
} 
if(remainder.termslist.size() != 0){
    System.out.println("Remainder detected. Evaluation failed");
    return null;
}
return result;
}
    
    // Simplifies the terms of a polynomial by adding/subtracting as necessary
    // if the powers are the same, it adds the bases and removes the 
    // unnecessary term
    public void SimplifyTerms(){
        for(int i = 0; i<termslist.size(); i++){
            for (int j = 0; j<termslist.size(); j++){
                if (termslist.get(i).compareTo(termslist.get(j)) == 0
                    && i!=j){
                        termslist.get(i).setBase(
                            termslist.get(i).getBase() + termslist.get(j).getBase());
                        termslist.remove(j);
                            
                        
                        
                    }
            }
        }
        
        for(int i = termslist.size()-1; i>=0; i--){
            if(termslist.get(i).getBase() == 0){
                termslist.remove(i);
                if(termslist.size() == 0){
                    Monomial m = new Monomial(0, 0);
                    termslist.add(m);
                
                }
            }
        }
    }
    
    public void sortPolynomial(){
        Collections.sort(termslist);
    }
    
    public int getSize(){
        return termslist.size();
    }
    
    public void addTerm(Monomial m){
        termslist.add(m);
    }
    
    public void removeTerm(Monomial m){
        termslist.remove(m);
    }
   
    // polynomial represented as string    
    
    public String toString(){
        String s = "";
        
        if(termslist.size()>0){
        s += termslist.get(0).toStringAlone(); // so we don't get + 
                                               // in first term
    }
        
        for (int i = 1; i<termslist.size(); i++){
            if (termslist.get(i).getBase() >= 0){ // positive monomial
                s += " + "+termslist.get(i).toString();
            }
            else{ // negative monomial
                s += " - "+termslist.get(i).toString();
            }
        }
             
        return s;
    }
} 
