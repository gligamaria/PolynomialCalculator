package Controller;

import Model.Monomial;
import Model.Polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolynomialService {

    public Polynomial sort(Polynomial polynomial){
        for(Monomial firstMon : polynomial.getMonomialsList()){
            for(Monomial secondMon : polynomial.getMonomialsList())
                if(firstMon.getPower() < secondMon.getPower()){
                    Collections.swap(polynomial.getMonomialsList(), polynomial.getMonomialsList().indexOf(firstMon),
                            polynomial.getMonomialsList().indexOf(secondMon));
                }

        }
        return polynomial;
    }

    public Polynomial clearPolynomial(Polynomial polynomial){

        List<Monomial> toRemove = new ArrayList<>();
        for(Monomial monomial:polynomial.getMonomialsList()){
            if(monomial.getCoefficient().floatValue() == 0.00)
                toRemove.add(monomial);
        }
        polynomial.getMonomialsList().removeAll(toRemove);
        return polynomial;
    }

    public Polynomial inversePolynomial(Polynomial polynomial){
        for(Monomial monomial: polynomial.getMonomialsList())
            monomial.setCoefficient(monomial.getCoefficient().floatValue()*(-1.00));
        return polynomial;
    }

    public Integer getPolynomialDegree(Polynomial polynomial){
        if(polynomial.getMonomialsList().isEmpty())
            return -1;
        return polynomial.getMonomialsList().get(0).getPower();
    }

}
