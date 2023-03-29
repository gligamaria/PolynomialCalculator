package Model;

import Controller.Operations;
import Controller.StringHandler;
import junit.framework.TestCase;

import java.util.ArrayList;

public class OperationsTest extends TestCase {

    StringHandler stringHandler = new StringHandler();
    Polynomial firstPolynomial = stringHandler.stringToPolynomial("x^3-2x^2+6x-5");
    Polynomial secondPolynomial = stringHandler.stringToPolynomial("x^2-1");
    Operations operations = new Operations();

    public void testAddition() {
        Polynomial sum = operations.addition(firstPolynomial, secondPolynomial);
        assertTrue(stringHandler.polynomialToString(sum).equals("X^3-1X^2+6X-6"));
    }

    public void testSubtraction() {
        Polynomial subtraction = operations.subtraction(firstPolynomial, secondPolynomial);
        assertTrue(stringHandler.polynomialToString(subtraction).equals("X^3-3X^2+6X-4"));
    }

    public void testMultiplication() {
        Polynomial multiplication = operations.multiplication(firstPolynomial, secondPolynomial);
        assertTrue(stringHandler.polynomialToString(multiplication).equals("X^5-2X^4+5X^3-3X^2-6X+5"));
    }

    public void testDivision() {
        ArrayList<Polynomial> results = operations.division(firstPolynomial, secondPolynomial);
        assertTrue(stringHandler.polynomialToStringDifferentiate(results.get(0)).equals("X-2.00") &&
                stringHandler.polynomialToStringDifferentiate(results.get(1)).equals("7.00X-7.00"));
    }

    public void testDifferentiate() {
        Polynomial differentiate = operations.differentiate(firstPolynomial);
        assertTrue(stringHandler.polynomialToString(differentiate).equals("3X^2-4X+6"));
    }

    public void testIntegration() {
        Polynomial integrate = operations.integration(firstPolynomial);
        assertTrue(stringHandler.polynomialToStringDifferentiate(integrate).equals("0.25X^4-0.67X^3+3.00X^2-5.00X"));
                                                                    // 1/4 x^4 - 2/3 x^3 + 6/2 x^2 - 5 x
    }
}