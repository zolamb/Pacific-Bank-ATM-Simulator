import javax.swing.*;
import java.awt.*;
import java.util.Vector;

//Credit to:   http://www.jroller.com/santhosh/entry/make_jcombobox_popup_wide_enough
public class WideComboBox extends JComboBox {

    public WideComboBox() {
    }

    public WideComboBox(final Object items[]){
        super(items);
    }

    public WideComboBox(Vector items) {
        super(items);
    }

    public WideComboBox(ComboBoxModel aModel) {
        super(aModel);
    }

    private boolean layingOut = false;

    public void doLayout(){
        try{
            layingOut = true;
            super.doLayout();
        }finally{
            layingOut = false;
        }
    }

    public Dimension getSize(){
        Dimension dim = super.getSize();
        if(!layingOut)
            dim.width = Math.max(dim.width, getPreferredSize().width);
        return dim;
    }
}