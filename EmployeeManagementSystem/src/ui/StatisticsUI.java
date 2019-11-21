package ui;

import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;
import models.*;
import utilities.BetterScanner;


/**
 *
 * @author Daniel Lindkvist
 */
public class StatisticsUI {
    
    private static EmployeeDirectory employees;
    private static BetterScanner scanner;
    
    public StatisticsUI(EmployeeDirectory employees, BetterScanner scanner) {
        StatisticsUI.employees = employees;
        StatisticsUI.scanner = scanner;
    }
    
    private StatisticsUI(){} // no instance without arguments allowed
    
    public void showStatisticsMenu() {
        while (true) {
            System.out.println("### Statisticsmenu ###");
            System.out.println("1. to show medium salary");
            System.out.println("2. to show median salary");
            System.out.println("3. to show gender data");
            System.out.println("4. to show salary statistics");
            System.out.println("5. to return to main menu");

            String input = scanner.nextLine();
            switch (input) {
                case "1": showMediumSalary(); break;
                case "2": showMedianSalary(); break;
                case "3": showGenderStatistics(); break;
                case "4": showMonthlySalaryStatistics(); break;
                case "5": System.out.println("Returning to main menu..."); return;
                default: System.out.println("Invalid input, try again.");
            }
        }
    }
    
    private void showMediumSalary(){
        double sum = 0;
        double managers = 0;
        double drivers = 0;
        double dairyworkers = 0;
        double warehouseworkers = 0;
        for(Employee e: employees.getValues()) {
            sum += e.getSalary();
            if (e instanceof Manager)
                managers += e.getSalary();
            if(e instanceof Driver)
                drivers += e.getSalary();
            if(e instanceof DairyWorker)
                dairyworkers += e.getSalary();
            if(e instanceof WarehouseWorker)
                warehouseworkers += e.getSalary();
        }
        System.out.println("Medium salary for:");
        System.out.println("Whole Company:    " + Main.twoDecimals(sum / employees.size()));
        System.out.println("Managers:         " + Main.twoDecimals(managers / Manager.count));
        System.out.println("Drivers:          " + Main.twoDecimals(drivers / Driver.count));
        System.out.println("Dairyworkers:     " + Main.twoDecimals(dairyworkers / DairyWorker.count));
        System.out.println("Warehouseworkers: " + Main.twoDecimals(warehouseworkers / WarehouseWorker.count));
    }
    
    private void showGenderStatistics(){
        int male = 0;
        int female = 0;
        int other = 0;
        
        for(Employee e: employees.getValues()) {
            switch (e.getGender()) {
                case MALE: male++; break;
                case FEMALE: female++; break;
                case OTHER: other++; break;
            }
        }
        System.out.println("Gender statistics in company:");
        System.out.println("Male: "+male+" Female: "+female+" Other: "+other);
        double maleP = male / (double) employees.size() * 100;
        double femaleP = female / (double) employees.size() * 100;
        double otherP = other / (double) employees.size() * 100;
        System.out.println("Male: " + Main.twoDecimals(maleP) +"%");
        System.out.println("Female: " + Main.twoDecimals(femaleP) +"%");
        System.out.println("Other: " + Main.twoDecimals(otherP) +"%");
    }
    
    private void showMedianSalary() {
        DoubleStream salaries = employees.getValues().stream().mapToDouble(Employee::getSalary).sorted();
        int size = employees.size();
        Double median = size % 2 == 0 ?
                salaries.skip(size/2-1).limit(2).average().getAsDouble():
                salaries.skip(size/2).findFirst().getAsDouble();
        
        System.out.println("Median salary in company is: " + median);
    }
    
    public void showMonthlySalaryStatistics() {
        DoubleStream salaries = employees.getValues().stream().mapToDouble(Employee::getSalary);
        DoubleSummaryStatistics sum = salaries.summaryStatistics();
        
        System.out.println("Highest salarie in company: " + Main.twoDecimals(sum.getMax()));
        System.out.println("Lowest salarie in company: " + Main.twoDecimals(sum.getMin()));
        System.out.println("Medium salarie in company: " + Main.twoDecimals(sum.getAverage()));
        System.out.println("Total monthly cost for salaries: " + Main.twoDecimals(sum.getSum())+"\n");
        
        //System.out.println("Total monthly cost for salaries: " + salaries.reduce(0d, (a,b) -> a+b));
    }
    
  
}
