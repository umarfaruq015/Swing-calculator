
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator {
    private JFrame frame;
    private JTextField textField;
    private String currentInput = "";
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
      
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) == '=') {                
                secondNumber = Double.parseDouble(currentInput);
                switch (operator) {
                    case "+":
                        currentInput = String.valueOf(firstNumber + secondNumber);
                        break;
                    case "-":
                        currentInput = String.valueOf(firstNumber - secondNumber);
                        break;
                    case "*":
                        currentInput = String.valueOf(firstNumber * secondNumber);
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            currentInput = String.valueOf(firstNumber / secondNumber);
                        } else {
                            currentInput = "Error";
                        }
                        break;
                }
                textField.setText(currentInput);
                operator = null;
            } else if (command.charAt(0) == '.') {                
                if (!currentInput.contains(".")) {
                    currentInput += command;
                }
            } else if ("0123456789".contains(command)) {                
                currentInput += command;
                textField.setText(currentInput);
            } else {                
                if (!currentInput.isEmpty()) {
                    firstNumber = Double.parseDouble(currentInput);
                    operator = command;
                    currentInput = "";
                }
            }
        }
    }
}
