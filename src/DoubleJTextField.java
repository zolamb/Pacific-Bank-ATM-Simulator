import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//CREDIT TO:  http://stackoverflow.com/questions/18084104/accept-only-numbers-and-a-dot-in-java-textfield
//Specific User:  http://stackoverflow.com/users/2070363/wender
public class DoubleJTextField extends JTextField {
    public DoubleJTextField(){
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!isNumber(ch) && !isValidSignal(ch) && !validatePoint(ch)  && ch != '\b') {
                    e.consume();
                }
            }
        });

    }

    private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }

    private boolean isValidSignal(char ch){
        if( (getText() == null || "".equals(getText().trim()) ) && ch == '-'){
            return true;
        }

        return false;
    }

    private boolean validatePoint(char ch){
        if(ch != '.'){
            return false;
        }

        if(getText() == null || "".equals(getText().trim())){
            setText("0.");
            return false;
        }else if("-".equals(getText())){
            setText("-0.");
        }

        return true;
    }
}