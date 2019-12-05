package ui;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        Map<String, List<Employee>> employeesByWorkRole =  employees.getValues().stream().collect(Collectors.groupingBy(Employee::getWorkRole));
        DoubleSummaryStatistics sum = employees.getValues().stream().mapToDouble(Employee::getSalary).summaryStatistics();
        
        System.out.println("Total monthly salaryexpenses in company: " + Main.twoDecimals(sum.getSum()));
        System.out.println("Average salary in company: " + Main.twoDecimals(sum.getAverage()));
        
        System.out.println("\nAverage salary per workrole:");
        for (String s : employeesByWorkRole.keySet()) {
            List<Employee> l = employeesByWorkRole.get(s);
            Double avg =  l.stream().mapToDouble(Employee::getSalary).average().getAsDouble();
            System.out.println(s + ": " + Main.twoDecimals(avg));
        }
        System.out.println("\n");
    }
    
    
    private void showGenderStatistics() {
        int tot = employees.size();
        Map<Gender, List<Employee>> employeesByGender =  employees.getValues().stream().collect(Collectors.groupingBy(Employee::getGender));
        int male = employeesByGender.get(Gender.MALE).size();
        int female = employeesByGender.get(Gender.FEMALE).size();
        int other = employeesByGender.get(Gender.OTHER).size();
        
        System.out.println("Gender statistics in company:");
        System.out.println("Male: " + male + " = " + Main.twoDecimals((double) male / tot * 100) + "%");
        System.out.println("Female: " + female + " = " + Main.twoDecimals((double) female / tot * 100) + "%");
        System.out.println("Other: " + other + " = " + Main.twoDecimals((double) other / tot * 100) + "%\n");
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
    }
    
  
}
