package Controller;

import Model.Monomial;
import Model.Polynomial;

import java.util.ArrayList;

public class Operations {
    PolynomialService polynomialService = new PolynomialService();

    public Polynomial addition(Polynomial firstPolynomial, Polynomial secondPolynomial){

        Polynomial toAdd, result;
        if(polynomialService.getPolynomialDegree(firstPolynomial) > polynomialService.getPolynomialDegree(secondPolynomial)){
            result = firstPolynomial;
            toAdd = secondPolynomial;
        }
        else{
            result = secondPolynomial;
            toAdd = firstPolynomial;
        }
        for(Monomial monomial : toAdd.getMonomialsList())
            result.addMonomialFloat(monomial);

        result.sort();
        result = polynomialService.clearPolynomial(result);

        if(polynomialService.getPolynomialDegree(result) == -1)
        {
            Monomial zeroMonomial = new Monomial();
            zeroMonomial.setCoefficient(0.0);
            zeroMonomial.setPower(1);
            result = new Polynomial(zeroMonomial);
        }

        return result;

    }
    public Polynomial subtraction(Polynomial firstPolynomial, Polynomial secondPolynomial){
        secondPolynomial = polynomialService.inversePolynomial(secondPolynomial);
        return addition(firstPolynomial,secondPolynomial);
    }
    public Polynomial multiplication(Polynomial firstPolynomial, Polynomial secondPolynomial){

        Polynomial result = new Polynomial();

        firstPolynomial = polynomialService.clearPolynomial(firstPolynomial);
        secondPolynomial = polynomialService.clearPolynomial(secondPolynomial);

        if(polynomialService.getPolynomialDegree(firstPolynomial) == -1 ||
            polynomialService.getPolynomialDegree(secondPolynomial) == -1)
        {
            Monomial zeroMonomial = new Monomial();
            zeroMonomial.setCoefficient(0);
            zeroMonomial.setPower(1);
            result = new Polynomial(zeroMonomial);
        }
        else {
            ArrayList<Monomial> firstList = firstPolynomial.getMonomialsList();
            ArrayList<Monomial> secondList = secondPolynomial.getMonomialsList();

            for(Monomial monomial1 : firstList){
                for (Monomial monomial2 : secondList){
                    Monomial newMonomial = new Monomial();

                    newMonomial.setCoefficient(monomial1.getCoefficient().floatValue() * monomial2.getCoefficient().floatValue());
                    newMonomial.setPower(monomial1.getPower() + monomial2.getPower());

                    result.addMonomial(newMonomial);
                }
            }
        }
        return result;
    }
    public ArrayList<Polynomial> division(Polynomial firstPolynomial, Polynomial secondPolynomial){

        Polynomial denominator, numerator;
        Polynomial remainder = new Polynomial();
        Polynomial quotidian = new Polynomial();

        boolean ended = false;

        firstPolynomial.sort();
        secondPolynomial.sort();
        firstPolynomial = polynomialService.clearPolynomial(firstPolynomial);
        secondPolynomial = polynomialService.clearPolynomial(secondPolynomial);
        int firstDegree = polynomialService.getPolynomialDegree(firstPolynomial);
        int secondDegree = polynomialService.getPolynomialDegree(secondPolynomial);

        if ( firstDegree > secondDegree || (firstDegree == secondDegree &&
                firstPolynomial.getMonomialsList().get(0).getCoefficient().intValue() >=
                        secondPolynomial.getMonomialsList().get(0).getCoefficient().intValue())){
            numerator = firstPolynomial;
            denominator = secondPolynomial;
        } else {
            numerator = secondPolynomial;
            denominator = firstPolynomial;
        }

        if(polynomialService.getPolynomialDegree(denominator) != -1){
            while (!ended) {

                Monomial firstNumeratorTerm = numerator.getMonomialsList().get(0);
                Monomial firstDenominatorTerm = denominator.getMonomialsList().get(0);

                Monomial quotidianTerm = new Monomial();
                quotidianTerm.setCoefficient(firstNumeratorTerm.getCoefficient().floatValue()/firstDenominatorTerm.getCoefficient().floatValue());
                quotidianTerm.setPower(firstNumeratorTerm.getPower()-firstDenominatorTerm.getPower());

                quotidian.addMonomialFloat(quotidianTerm);

                remainder = subtraction(numerator, multiplication(new Polynomial(quotidianTerm),denominator));

                firstDegree = polynomialService.getPolynomialDegree(remainder);
                secondDegree = polynomialService.getPolynomialDegree(denominator);
                if ( firstDegree < secondDegree || (remainder.getMonomialsList().get(0).getCoefficient().floatValue()==0.0)) {
                    ended = true;
                }
                else numerator = remainder;

            }
            ArrayList<Polynomial> results = new ArrayList<>();
            results.add(quotidian);
            results.add(remainder);
            return results;
        }
        return null;

    }
    public Polynomial differentiate(Polynomial polynomial){
        for (Monomial monomial: polynomial.getMonomialsList()){
            monomial.setCoefficient(monomial.getCoefficient().intValue()*monomial.getPower());
            monomial.setPower(monomial.getPower()-1);
        }
        polynomial = polynomialService.clearPolynomial(polynomial);
        return polynomial;
    }
    public Polynomial integration(Polynomial polynomial){
        for (Monomial monomial: polynomial.getMonomialsList()){
            monomial.setPower(monomial.getPower()+1);
            monomial.setCoefficient(monomial.getCoefficient().floatValue()/(monomial.getPower()));
        }
        return polynomial;
    }
}
