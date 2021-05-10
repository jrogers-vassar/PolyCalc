import java.io.*;


public class ReadFile{
    /**
     * Reads a file line by line and evaluates any expressions within
     * @param String input string
     * @throws IOException if file cannot be read
     */
    public void readFile(String s){
        File file = new File(s);
        
        try{
        BufferedReader b = new BufferedReader(new FileReader(file));
        PolyCalc calc = new PolyCalc();
        for(String line = b.readLine(); line!= null; line = b.readLine()){
            System.out.println("String detected: "+line);
            line = line.replaceAll(" ", "");
            line = line.trim();
            
            if(line.contains("=")){
                char c = line.charAt(0); // isolates variable
                line = line.substring(2, line.length()); // removes c and '='
                for(char key : calc.memory.keySet()){ 
                    // this loop will check map for any keys in the remaining line
                    // and replace them w/ corresponding polynomial
                    // as well as surround polynomial in parentheses
                    if(line.indexOf(key) >= 0){
                        String keystring = String.valueOf(key);
                        line = line.replaceAll(keystring, "("+keystring+")");
                        line = line.replaceAll(keystring, calc.memory.get(key).toString());
                        
                    }
            }
                // evaluates line now that it's ready
                Token t = calc.readString(line);
                // if t is null, readString failed
                if(t!=null){
                calc.memory.put(c, t);
                System.out.println("Variable "+c+" has been set to "+t+"\n");
            }
            }
            
            else{ // for a calculation without an equals sign
                for(char key : calc.memory.keySet()){
                    // same as other for loop: replaces keys
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
        catch(IOException e){
            System.out.println("Could not be read");
        }
        
    }
    
}