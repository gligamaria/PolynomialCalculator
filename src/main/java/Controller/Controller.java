package Controller;

import Model.*;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    Polynomial firstPolynomial;
    Polynomial secondPolynomial;

    View view;
    Operations operations = new Operations();
    PolynomialService polynomialService = new PolynomialService();

    public Controller(View view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        StringHandler stringHandler = new StringHandler();
        Polynomial result;

        if(e.getActionCommand().equals("delete first pol")){
            view.firstPolynomial.setText("");
        }
        else if(e.getActionCommand().equals("delete second pol")){
            view.secPolynomial.setText("");
        }
        else if(e.getActionCommand().equals("integrate")){
            String firstInput = view.firstPolynomial.getText();
            firstPolynomial = stringHandler.stringToPolynomial(firstInput);

            result = operations.integration(firstPolynomial);
            view.result.setText(stringHandler.polynomialToStringDifferentiate(result));
        }
        else if(e.getActionCommand().equals("differentiate")){
            String firstInput = view.firstPolynomial.getText();
            firstPolynomial = stringHandler.stringToPolynomial(firstInput);

            result = operations.differentiate(firstPolynomial);
            result = polynomialService.clearPolynomial(result);
            view.result.setText(stringHandler.polynomialToString(result));
        }
        else if(e.getActionCommand().equals("add")){
            String firstInput = view.firstPolynomial.getText();
            firstPolynomial = stringHandler.stringToPolynomial(firstInput);

            String secondInput = view.secPolynomial.getText();
            secondPolynomial = stringHandler.stringToPolynomial(secondInput);

            result = operations.addition(firstPolynomial,secondPolynomial);
            view.result.setText(stringHandler.polynomialToString(result));
        }
        else if(e.getActionCommand().equals("subtract")){
            String firstInput = view.firstPolynomial.getText();
            firstPolynomial = stringHandler.stringToPolynomial(firstInput);

            String secondInput = view.secPolynomial.getText();
            secondPolynomial = stringHandler.stringToPolynomial(secondInput);

            result = operations.subtraction(firstPolynomial,secondPolynomial);
            view.result.setText(stringHandler.polynomialToString(result));
        }
        else if(e.getActionCommand().equals("multiply")){
            String firstInput = view.firstPolynomial.getText();
            firstPolynomial = stringHandler.stringToPolynomial(firstInput);

            String secondInput = view.secPolynomial.getText();
            secondPolynomial = stringHandler.stringToPolynomial(secondInput);

            result = operations.multiplication(firstPolynomial,secondPolynomial);
            view.result.setText(stringHandler.polynomialToString(result));
        }
        else if(e.getActionCommand().equals("divide")){

            String firstInput = view.firstPolynomial.getText();
            firstPolynomial = stringHandler.stringToPolynomial(firstInput);

            String secondInput = view.secPolynomial.getText();
            secondPolynomial = stringHandler.stringToPolynomial(secondInput);

            ArrayList<Polynomial> results = operations.division(firstPolynomial,secondPolynomial);
            if (results != null){
                view.result.setText("Quotidian: " + stringHandler.polynomialToStringDifferentiate(results.get(0)));
                view.remainder.setText("Remainder: "+ stringHandler.polynomialToStringDifferentiate(results.get(1)));
                view.remainder.setVisible(true);
            }
           else view.result.setText("ERROR! Division by 0!");
        }
    }
}
