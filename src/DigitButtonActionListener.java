import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitButtonActionListener implements ActionListener {
    private JTextField textField;
    private char action;
    public DigitButtonActionListener(JTextField textField, char action){
        this.textField = textField;
        this.action = action;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder builder = new StringBuilder(textField.getText());
        if((builder.toString().equals("Infinity") ) || (builder.toString().equals("NaN")))
            builder.setLength(0);
        if(builder.length()>0 && builder.charAt(builder.length()-1)=='0') {
            builder.deleteCharAt(builder.length()-1);
        }
        textField.setText(builder.append(action).toString());
    }
}
