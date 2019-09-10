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
public class Rektangel extends Shape {

    private double shortSide;
    private double longSide;
    
    public String toString() {
        return "rektangel";
    }

    public Rektangel(double shortSide, double longSide) {
        this.shortSide = shortSide;
        this.longSide = longSide;
    }

    public double getShortside() {
        return shortSide;
    }

    public void setShortside(double shortSide) {
        this.shortSide = shortSide;
    }

    public double getLongside() {
        return longSide;
    }

    public void setlongSide(double longSide) {
        this.longSide = longSide;
    }

    public double omkrets() {
        return 2 * shortSide + 2 * longSide;
    }

    public double area() {
        return shortSide * longSide;
    }

}
