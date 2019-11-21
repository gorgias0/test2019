package models;

/**
 *
 * @author Daniel Lindkvist
 */
public class Driver extends Employee {
    private final double bonusRatio = 1.4;
    public static int count = 0;
    private final String title = "Driver";
    
    public Driver(String name, Gender gender, double salary, int workyears) {
        super(name, gender, salary, workyears);
        count++;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public double getBonus() {
        return super.getBonus()*bonusRatio;
    }
    
    @Override
    public String toString() {
        return super.toString()+", Title: Driver";
    }
    
}
