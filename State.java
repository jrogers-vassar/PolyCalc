public enum State{
    /**
     * initial state. waiting on calculation 
     */
    WCALC(false),
    /**
     * left parentheses state
     */
    LPAREN(false),
    /**
     * WNEG = waiting on negative. This is the state if the first character is '-'
     */
    WNEG(false),
    /**
     * coefficient state. 
     */
    COEF(true),
    /**
     * waiting on decimal. '.' was input, waiting for more digits 
     */
    WDEC(false),
    /**
     * decimal state
     */
    DEC(true),
    /**
     * variable state. 'x' has been input 
     */
    VARIABLE(true),
    /**
     * waiting on power. '^' has been input 
     */
    WPOWER(false),
    /**
     * power state
     */
    POWER(true),
    /**
     * Right Parentheses state
     */
    RPAREN(true),
    /**
     * waiting on operator. '+','-','*','/' has been input, waiting on digits or lparen
     */
    WOPERATOR(false),
    
    /**
     * error state. expression is invalid 
     */
    ERROR(false);

    
    private final boolean isAccept;
    
    /**
     * constructor
     * @param boolean isAccept 
     */
    State(boolean isAccept){ this.isAccept = isAccept; }
    /**
     * returns true or false based on isAccept's value 
     */
    public boolean isAccept(){ return isAccept; }
}