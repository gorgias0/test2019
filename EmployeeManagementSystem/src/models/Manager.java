package models;

/**
 *
 * @author Daniel Lindkvist
 */
public class Manager extends Employee  {
    private final double bonusRatio = 1.5;
    public static int count = 0;
    private final String title = "Manager";
    
    public Manager(String name, Gender gender, double salary, int workyears) {
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
        return super.toString()+", Title: Manager";
    }
    
}
