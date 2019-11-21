package models;

/**
 *
 * @author Daniel Lindkvist
 */
public class WarehouseWorker extends Employee {
    
    private final double bonusRatio = 1.2;
    public static int count = 0;
    private final String title = "WarehouseWorker";
    
    public WarehouseWorker(String name, Gender gender, double salary, int workyears) {
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
        return super.toString()+", Title: WarehouseWorker";
    }
    
}
