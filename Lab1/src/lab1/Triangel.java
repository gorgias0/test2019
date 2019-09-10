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
public class Triangel extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;
    
    public String toString() {
        return "triangel";
    }

    public Triangel(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = Math.sqrt(sideA*sideA+sideB*sideB);
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double side2) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }
    /*Assumes a right triangle where sideC is hypotenuse */
    public double area() {
        return (sideA * sideB) / 2;
    }

    public double omkrets() {
        return sideA + sideB + sideC;
    }

}
