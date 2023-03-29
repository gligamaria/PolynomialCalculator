package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    public JTextField firstPolynomial = new JTextField();
    public JTextField secPolynomial = new JTextField();
    public JLabel result = new JLabel();
    public JLabel remainder = new JLabel();

    public View(String name){

        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Controller controller = new Controller(this);

        firstPolynomial.setBounds(25,25, 150,20);
        secPolynomial.setBounds(25,60, 150,20);
        result.setBounds(25,95,240,20);
        remainder.setBounds(25,130,240,20);

        result.setBackground(Color.white);
        result.setOpaque(true);
        remainder.setBackground(Color.white);
        remainder.setOpaque(true);
        remainder.setVisible(false);

        JButton deleteFirstPolButton=new JButton("Delete");
        deleteFirstPolButton.setBounds(190,25,75,20);
        deleteFirstPolButton.setBackground(Color.LIGHT_GRAY);
        deleteFirstPolButton.addActionListener(controller);
        deleteFirstPolButton.setActionCommand("delete first pol");
        this.add(deleteFirstPolButton);

        JButton deleteSecondPolButton=new JButton("Delete");
        deleteSecondPolButton.setBounds(190,60,75,20);
        deleteSecondPolButton.setBackground(Color.LIGHT_GRAY);
        deleteSecondPolButton.addActionListener(controller);
        deleteSecondPolButton.setActionCommand("delete second pol");
        this.add(deleteSecondPolButton);

        JButton integrationButton=new JButton("Integrate");
        integrationButton.setBounds(280,25,95,20);
        integrationButton.addActionListener(controller);
        integrationButton.setActionCommand("integrate");
        integrationButton.setBackground(myDarkPink);
        this.add(integrationButton);

        JButton derivativeButton=new JButton("Derivate");
        derivativeButton.setBounds(390,25,95,20);
        derivativeButton.addActionListener(controller);
        derivativeButton.setActionCommand("differentiate");
        derivativeButton.setBackground(myDarkPink);
        this.add(derivativeButton);

        JButton additionButton=new JButton("Add");
        additionButton.setBounds(280,60,95,20);
        additionButton.addActionListener(controller);
        additionButton.setActionCommand("add");
        additionButton.setBackground(myPink);
        this.add(additionButton);

        JButton subtractButton=new JButton("Subtract");
        subtractButton.setBounds(390,60,95,20);
        subtractButton.addActionListener(controller);
        subtractButton.setActionCommand("subtract");
        subtractButton.setBackground(myPink);
        this.add(subtractButton);

        JButton multiplyButton=new JButton("Multiply");
        multiplyButton.setBounds(280,95,95,20);
        multiplyButton.addActionListener(controller);
        multiplyButton.setActionCommand("multiply");
        multiplyButton.setBackground(myPink);
        this.add(multiplyButton);

        JButton divideButton=new JButton("Divide");
        divideButton.setBounds(390,95,95,20);
        divideButton.addActionListener(controller);
        divideButton.setActionCommand("divide");
        divideButton.setBackground(myPink);
        this.add(divideButton);

        this.add(firstPolynomial);
        this.add(secPolynomial);
        this.add(result);
        this.add(remainder);

        this.setSize(530,350);
        this.setLayout(null);

    }

}