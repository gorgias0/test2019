/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author Daniel Lindkvist
 */
public class Cirkel extends Shape {

    private final double pi = 3.14;
    private double radie;
    
    public String toString() {
        return "cirkel";
    }

    public Cirkel(double radie) {
        this.radie = radie;
    }

    public double getRadie() {
        return radie;
    }

    public void setRadie(double radie) {
        this.radie = radie;
    }

    public double area() {
        return radie * radie * pi;
    }

    public double omkrets() {
        return 2 * radie * pi;
    }
}
