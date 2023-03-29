package Model;

import Controller.PolynomialService;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial{
    private ArrayList<Monomial> monomialsList = new ArrayList<>();
    PolynomialService polynomialService = new PolynomialService();

    public Polynomial() {
        this(new ArrayList<Monomial>());
    }

    public Polynomial(Monomial term) {
        this(new ArrayList<Monomial>());
        monomialsList.add(term);
    }

    public Polynomial(ArrayList<Monomial> monomialsList) {
        this.monomialsList = monomialsList;
        polynomialService.sort(this);
    }
    public void addMonomial(Monomial monomial){
        boolean exists = false;
        for(Monomial randomMonomial : monomialsList){
            if(randomMonomial.getPower().equals(monomial.getPower())){
                exists = true;
                randomMonomial.setCoefficient(randomMonomial.getCoefficient().intValue() + monomial.getCoefficient().intValue());
            }
        }
        if(!exists){
            monomialsList.add(monomial);
            sort();
        }
    }

    public void addMonomialFloat(Monomial monomial){
        boolean exists = false;
        for(Monomial randomMonomial : monomialsList){
            if(randomMonomial.getPower().equals(monomial.getPower())){
                exists = true;
                randomMonomial.setCoefficient(randomMonomial.getCoefficient().floatValue() + monomial.getCoefficient().floatValue());
            }
        }
        if(!exists){
            monomialsList.add(monomial);
            sort();
        }
    }

    public void sort(){
        int i,j, size = monomialsList.size();
        for(i = 0; i < size; i++){
            for(j = i + 1; j < size; j++) {
                if (monomialsList.get(i).getPower() < monomialsList.get(j).getPower()) {
                    Collections.swap(monomialsList, i, j);
                }
            }
        }
    }

    public ArrayList<Monomial> getMonomialsList() {
        return monomialsList;
    }

}
