import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationButtonActionListener implements ActionListener {
    private JTextField textField;
    private char action;
    public OperationButtonActionListener(JTextField textField, char action){
        this.textField = textField;
        this.action = action;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder builder = new StringBuilder(textField.getText());
        if (builder.length()==0 || !isDigit( builder.charAt(builder.length()-1)))
            return;
        textField.setText(builder.append(action).toString());
    }
    private boolean isDigit(char c) {
        return (c>='0') && (c<='9');
    }
}
