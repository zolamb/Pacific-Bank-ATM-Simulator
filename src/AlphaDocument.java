import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

//CREDIT TO:  https://www.experts-exchange.com/questions/20975134/Accepting-only-letters-and-spaces-in-JTextField.html
//USER:  https://www.experts-exchange.com/members/abstractionz.html
public class AlphaDocument extends PlainDocument {

    private int limit = 0;

    public AlphaDocument(){
        super();
    }

    public AlphaDocument(int limit){
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String s, AttributeSet attributeSet)
            throws BadLocationException {
        char[] inputChars = s.toCharArray();
        boolean success = false;
        try{
            for(int i = 0; i < inputChars.length; i++){
                if(Character.isLetter(inputChars[i]) || inputChars[i] == ' '){
                    success = true;
                }
                else{
                    success = false;
                    break;
                }
            }

            if(success && limit == 0 || this.getLength() + s.length() <= limit){
                super.insertString(offset, s, attributeSet);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

}

