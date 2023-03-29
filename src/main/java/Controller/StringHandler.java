package Controller;
import Controller.PolynomialService;
import Model.Monomial;
import Model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler {


    public Polynomial stringToPolynomial (String input) {

        Polynomial polynomial = new Polynomial();
        Pattern pattern = Pattern.compile("(([+-]?([0-9]{1,})?([.][0-9]{1,})?)?([X])([\\\\^][0-9]{1,})?)|(([+-]?[0-9]{1,})([.][0-9]{1,})?)");
        input = input.replace("x", "X");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            Monomial newMonomial = new Monomial();
            String monomial = matcher.group();
            int i = 0, coefficient = 0, power = 0, length = monomial.length();
            int foundNegative = 1;
            char currentChar;
            if(monomial.charAt(0) == 'X' || (((monomial.charAt(0) == '-') ||
                    (monomial.charAt(0) == '+')) && length >= 2 && monomial.charAt(1) == 'X')){
                if(monomial.charAt(0) == '-')
                newMonomial.setCoefficient(-1);
                else newMonomial.setCoefficient(1);
            }
            else{
                while (i < length && monomial.charAt(i) != 'X') {
                    currentChar = monomial.charAt(i);
                    if (currentChar == '-')
                        foundNegative = -1;
                    if (Character.isDigit(currentChar)) {
                        coefficient = coefficient * 10 + Integer.parseInt(String.valueOf(currentChar));
                    }
                    i++;
                }
                newMonomial.setCoefficient(coefficient * foundNegative);
            }
            if (monomial.contains("X")) {
                if (monomial.indexOf("X") == length - 1) {
                    power = 1;
                } else {
                    String[] monomialParts = monomial.split("X");
                    i = 0;
                    length = monomialParts[1].length();
                    while (i < length) {
                        currentChar = monomialParts[1].charAt(i);
                        if (Character.isDigit(currentChar)) {
                            power = power * 10 + Integer.parseInt(String.valueOf(currentChar));
                        }
                        i++;
                    }
                }
            }
            newMonomial.setPower(power);
            polynomial.addMonomial(newMonomial);
        }
        polynomial.sort();
        return polynomial;
    }

    public String polynomialToString(Polynomial polynomial){

        String output = new String();
        int i = 1;
        if(checkIfZero(polynomial))
            return "0";
        else for(Monomial monomial : polynomial.getMonomialsList()){

            int coefficient = monomial.getCoefficient().intValue();

            if(coefficient < 0){
                output = output.concat("-");
            }
            else if (i != 1 && coefficient != 0) output = output.concat("+");

            if(coefficient != 0){
                if(coefficient != 1 || monomial.getPower()==0)
                    output = output.concat(Integer.toString(Math.abs(monomial.getCoefficient().intValue())));
            }

            if(monomial.getPower() != 0)
                output = output.concat("X");

            if (monomial.getPower() != 1 && monomial.getPower() != 0){
                output = output.concat(("^"));
                output = output.concat(Integer.toString(monomial.getPower()));
            }
            i++;
        }
        return output;
    }

    public String polynomialToStringDifferentiate(Polynomial polynomial){

        String output = new String();
        int i = 1;
        PolynomialService polynomialService = new PolynomialService();

        if(polynomialService.getPolynomialDegree(polynomial) == 1 &&
                polynomial.getMonomialsList().get(0).getCoefficient().floatValue() == 0.00 &&
                polynomial.getMonomialsList().get(0).getPower() == 1)
        return "-";
        for(Monomial monomial : polynomial.getMonomialsList()){

            float coefficient = monomial.getCoefficient().floatValue();

            if(coefficient < 0.0){
                output = output.concat("-");
            }
            else if (i != 1 && coefficient != 0.0) output = output.concat("+");

            if(coefficient != 0.0){
                if(coefficient != 1.0 || monomial.getPower()==0.0)
                    output = output.concat(String.format("%.02f", Math.abs(monomial.getCoefficient().floatValue())));
            }

            if(monomial.getPower() != 0)
                output = output.concat("X");

            if (monomial.getPower() != 1 && monomial.getPower() != 0){
                output = output.concat(("^"));
                output = output.concat(Integer.toString(monomial.getPower()));
            }
            i++;
        }
        return output;
    }

    public boolean checkIfZero(Polynomial polynomial){

        PolynomialService polynomialService = new PolynomialService();
        return polynomialService.getPolynomialDegree(polynomial) == 1 &&
                polynomial.getMonomialsList().get(0).getCoefficient().intValue() == 0 &&
                polynomial.getMonomialsList().get(0).getPower() == 1;
    }
}




