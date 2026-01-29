import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener {
    private JTextField text;

    public calculator() {
        // Create the frame
        setTitle("Simple Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        text = new JTextField();
        text.setEditable(false);
        text.setPreferredSize(new Dimension(400, 100));
        text.setFont(new Font("Digital-7", Font.PLAIN, 36));
        add(text, BorderLayout.NORTH);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 4));
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            p.add(button);
        }

        add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789+-*/".contains(command)) {
            // Append the number or operator to the existing text
            text.setText(text.getText() + command);

        } else if (command.equals("C")) {
            // Clear the display
            text.setText("");

        } else if (command.equals("=")) {
            try {
                // Evaluate the entire expression
                String expression = text.getText();
                double result = evaluateExpression(expression);
                text.setText(String.valueOf(result));
            } catch (Exception ex) {
                text.setText("Error");
            }
        }
    }

    // Method to evaluate the expression with basic operator precedence
    private double evaluateExpression(String expression) {
        // Remove spaces from the expression
        expression = expression.replace(" ", "");

        double result = 0;
        double currentNumber = 0;
        char lastOperator = '+';

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }
            if (!Character.isDigit(ch) || i == expression.length() - 1) {
                // Process the last operation
                switch (lastOperator) {
                    case '+':
                        result += currentNumber;
                        break;
                    case '-':
                        result -= currentNumber;
                        break;
                    case '*':
                        result *= currentNumber;
                        break;
                    case '/':
                        if (currentNumber != 0) {
                            result /= currentNumber;
                        } else {
                            throw new ArithmeticException("Division by zero");
                        }
                        break;
                }

                lastOperator = ch;
                currentNumber = 0;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        calculator cal = new calculator();
        cal.setVisible(true);
    }
}
