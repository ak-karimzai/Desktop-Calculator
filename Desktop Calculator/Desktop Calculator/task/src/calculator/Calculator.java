package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Calculator extends JFrame {
    private JLabel equationLabel;
    private JLabel resultLabel;
    private JPanel pane;
    private JPanel pane2;
    private Color errorColor = Color.RED.darker();
    private Color noErrorColor = Color.BLACK;
    private Map<String, JButton> buttonNameAndText;
    private Evaluate evaluate = new Evaluate();
    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(null);
        setVisible(true);

        pane = new JPanel();
        pane.setLayout(new GridLayout(2, 1));
        pane2 = new JPanel();
        pane2.setLayout(new GridLayout(6, 4, 5, 5));

        equationLabel = new JLabel();
        equationLabel.setName("EquationLabel");
        equationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        equationLabel.setForeground(noErrorColor);

        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        addButtons();

        pane.add(resultLabel);
        pane.add(equationLabel);

        add(pane);
        add(pane2);
        setVisible(true);
        setLayout(new GridLayout(2, 1));
    }

    private void addButtons() {
        buttonNameAndText = new LinkedHashMap<>();
        buttonNameAndText.put("Seven", new JButton("7"));
        buttonNameAndText.put("Eight", new JButton("8"));
        buttonNameAndText.put("Nine", new JButton("9"));
        buttonNameAndText.put("Add", new JButton("\u002B"));
        buttonNameAndText.put("Four", new JButton("4"));
        buttonNameAndText.put("Five", new JButton("5"));
        buttonNameAndText.put("Six", new JButton("6"));
        buttonNameAndText.put("Subtract", new JButton("-"));
        buttonNameAndText.put("One", new JButton("1"));
        buttonNameAndText.put("Two", new JButton("2"));
        buttonNameAndText.put("Three", new JButton("3"));
        buttonNameAndText.put("Multiply", new JButton("\u00D7"));
        buttonNameAndText.put("PlusMinus", new JButton("\u00B1"));
        buttonNameAndText.put("Zero", new JButton("0"));
        buttonNameAndText.put("Dot", new JButton("."));
        buttonNameAndText.put("Divide", new JButton("\u00F7"));
        buttonNameAndText.put("Parentheses", new JButton("()"));
        buttonNameAndText.put("SquareRoot", new JButton("\u221A"));
        buttonNameAndText.put("PowerTwo", new JButton("X\u00b2"));
        buttonNameAndText.put("PowerY", new JButton("X\u207f"));


        for (var key : buttonNameAndText.keySet()) {
            var button = buttonNameAndText.get(key);
            button.addActionListener(e -> {
                addButtonText(e);
            });
            button.setName(key);
            pane2.add(button);
        }

        var evalButton = new JButton("=");
        evalButton.setName("Equals");
        evalButton.addActionListener(e -> {
            evaluateEquation();
        });

        var clearButton = new JButton("C");
        clearButton.setName("Clear");
        clearButton.addActionListener(e -> {
            resultLabel.setText("");
            equationLabel.setText("");
        });

        var deleteButton = new JButton("Del");
        deleteButton.setName("Delete");
        deleteButton.addActionListener(e -> {
            var text = equationLabel.getText();
            if (text.length() > 0) {
                equationLabel.setText(text.substring(0, text.length() - 1));
            }
        });
        pane2.add(evalButton);
        pane2.add(clearButton);
        pane2.add(deleteButton);
    }

    private void evaluateEquation() {
        var res = "";
        try {
            var equation = equationLabel.getText();
            res = evaluate.eval(equation);
        } catch (EquationInvalidException exception) {
            res = "0";
            equationLabel.setForeground(errorColor);
            equationLabel.setText(equationLabel.getText());
        } finally {
            resultLabel.setText(res);
        }
    }

    private String getButtonText(JButton button) {
        var buttonName = button.getName();
        var res = "";
        if (buttonName.equals("PowerTwo")) {
            res = "^(2)";
        } else if (buttonName.equals("PowerY")) {
            res = "^";
        } else if (buttonName.equals("Parentheses")) {
            var textEquation = equationLabel.getText();
            var leftAndRightParen = GrammerChecker.countParenthesis(textEquation);

            if (leftAndRightParen.getFirst() == 0 ||
                    leftAndRightParen.getFirst() == leftAndRightParen.getSecond() ||
                    textEquation.charAt(textEquation.length() - 1) == '(' ||
                    GrammerChecker.isOperator(textEquation.charAt(textEquation.length() - 1))) {
                res = "(";
            } else {
                res = ")";
            }
        } else if (buttonName.equals("SquareRoot")) {
            res = "\u221A(";
        } else if (buttonName.equals("PlusMinus")) {
            var inputedEquation = equationLabel.getText();
            if (inputedEquation.startsWith("(-")) {
                equationLabel.setText(inputedEquation.substring(2));
            } else if (inputedEquation.length() > 0 &&
                       inputedEquation.charAt(inputedEquation.length() - 1) == '(') {
                equationLabel.setText(inputedEquation.concat("-"));
            } else {
                equationLabel.setText("(-".concat(inputedEquation));
            }
        } else {
            res = button.getText();
        }
        return res;
    }
    private void addButtonText(ActionEvent event) {
        JButton src = (JButton) event.getSource();
        var equationLabelText = equationLabel.getText();
        var buttonText = getButtonText(src);
        var addText = "";

        if (buttonText.length() == 0) return;

        if (equationLabelText.length() > 0 &&
                GrammerChecker.isOperator(equationLabelText.charAt(equationLabelText.length() - 1))) {
            if (evaluate.operatorPrecedence(buttonText.charAt(0)) >=
                    evaluate.operatorPrecedence(equationLabelText.charAt(equationLabelText.length() - 1))) {
                equationLabelText = equationLabelText.substring(0, equationLabelText.length() - 1);
            }
        }

        addText = equationLabelText.concat(buttonText);
        if (GrammerChecker.isOperator(buttonText.charAt(0))) {
            addText = GrammerChecker.getValidEquation(addText);
        }
        equationLabel.setText(addText);
        equationLabel.setForeground(noErrorColor);

    }
}
