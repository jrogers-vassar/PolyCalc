import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class PolyCalcGUI{
    
    /**
     * constructor. creates polycalc gui
     */
    PolyCalcGUI(){
        PolyCalc calc = new PolyCalc();
        
        JFrame window = new JFrame("PolyCalc");
        window.setSize(280, 200);
        window.setLocationRelativeTo(null);
        
        
        
        JPanel content = new JPanel();
        window.setContentPane(content);
        JLabel text = new JLabel("Please input an expression or set a variable");
        
        content.add(text);
        JTextField textarea = new JTextField("", 25);
        content.add(textarea);
        JButton evaluateButton = new JButton("   Click Here to Evaluate the Expression    ");
        

        content.add(evaluateButton);
        JLabel result = new JLabel("");
        content.add(result);
        
        evaluateButton.addActionListener(new ActionListener(){
            /**
             * What happens when the button is pressed.
             * Certain errors will call println()
             * This could be fixed by throwing exceptions instead of using
             * println(), but alas I am out of time 
             */
            public void actionPerformed(ActionEvent e){
            String line = textarea.getText();
            
            
            
            line = line.replaceAll(" ", "");
            line = line.trim();
            
            if(line.contains("=")){
                char c = line.charAt(0);
                if(c=='x'){
                    result.setText("x is not a valid variable");
                }
                else if(c=='^'){
                    result.setText("^ is not a valid variable");
                }
                else if(Character.isDigit(c)){
                    result.setText("Numbers can't be variables");
                }
                else{ // see ReadFile class for comments on this code, it's about the same 
                line = line.substring(2, line.length());
                for(char key : calc.memory.keySet()){
                    if(line.indexOf(key) >= 0){
                        String keystring = String.valueOf(key);
                        line = line.replaceAll(keystring, "("+keystring+")");
                        line = line.replaceAll(keystring, calc.memory.get(key).toString());
                        
                    }
            }
                Token t = calc.readStringGUI(line);
                if(t!=null){
                calc.memory.put(c, t);
                result.setText("Variable "+c+" has been set to "+t+"\n");
            }
                else{
                    result.setText("Could not be evaluated");
                }
            
            }
        }
            
            else{
                for(char key : calc.memory.keySet()){
                    if(line.indexOf(key) >= 0){
                        String keystring = String.valueOf(key);
                        line = line.replaceAll(keystring, "("+keystring+")");
                        line = line.replaceAll(keystring, calc.memory.get(key).toString());
                    }
            }
            Token t = calc.readStringGUI(line);
      
            if(t!=null){
            result.setText(t+"\n");
        }
            else{
                result.setText("Could not be evaluated");
            }
        
        }
    }
});

window.setVisible(true);
window.toFront();
}

    
 
    public static void main(String[] args){
        PolyCalcGUI p = new PolyCalcGUI();
    }
    
}
