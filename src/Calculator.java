import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Calculator extends JFrame {
    private JTextField textField;
    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300,300,800,800);
        add(getTopPanel(),BorderLayout.NORTH);
        add(getBottomPanel(),BorderLayout.CENTER);
        add(getSidePanel(),BorderLayout.EAST);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Calculator();
    }
    public JPanel getTopPanel()
    {
        JPanel panel = new JPanel();
        textField = new JTextField();
        textField.setEnabled(false);
        panel.setLayout(new GridLayout(1,1));
        panel.add(textField);
        return panel;
    }
    private JPanel getBottomPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        for (int i= 1; i<=10;++i)
        {
            JButton  button = new JButton();
            button.setText(String.valueOf(i%10));
            button.addActionListener(new DigitButtonActionListener(textField,button.getText().charAt(0)));
            panel.add(button);
        }
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
        panel.add(clearButton);

        JButton equalButton = new JButton("=");
        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        panel.add(equalButton);
        return panel;
    }
    private JPanel getSidePanel(){
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4,1));
        char[] operations = {'+','-','*','/'};
        for (char operation:
             operations) {
            JButton button =new JButton(String.valueOf(operation));
            button.addActionListener(new OperationButtonActionListener(textField,operation));
            gridPanel.add(button);
        }
        return gridPanel;
    }
    private void calculate(){
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
        try{
            Object eql = scriptEngine.eval(textField.getText());
            textField.setText(eql.toString());
        }
        catch (ScriptException ex){
            ex.printStackTrace();
        }
    }

}
