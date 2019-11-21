package ui;

import gui.EmployeeTableModel;
import gui.MainJFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Gender;
import utilities.*;
import models.*;

/**
 *
 * @author Daniel Lindkvist
 */
public class Main {
    
    private final static EmployeeDirectory EMPLOYEES = new EmployeeDirectory();
    private final static BetterScanner SCANNER = new BetterScanner();
    private final static EmployeeUI EMPUI = new EmployeeUI(EMPLOYEES, SCANNER);
    private final static StatisticsUI STATUI = new StatisticsUI(EMPLOYEES, SCANNER);
    


    public static void main(String[] args) {
        createTestData();
        
        List<Employee> test = EMPLOYEES.findBySalary(27000, 30000);
        System.out.println("Salaries between 27 and 30");
        test.forEach(e -> System.out.print(e.getSalary()+" : "));
        System.out.println("");
        
        while (true) {
            showMainMenu();
            String input = SCANNER.nextLine();
            switch (input) {
                case "1": EMPUI.showEmployeeMenu(); break;
                case "2": STATUI.showStatisticsMenu(); break;
                case "3": System.out.println("Application exits..."); System.exit(0);
                case "5": showGUI(); break;
                default: System.out.println("Invalid input, please try again.\n"); break;
            }
        }

    }
    
    /**
     * 
     * @param d Double
     * @return String representing d with two decimals
     */
    public static String twoDecimals(Double d) {
        return String.format(java.util.Locale.getDefault(),"%.2f", d);
    }
    
    private static void showGUI() {
        MainJFrame frame = new MainJFrame();
        frame.setVisible(true);
    }
    
    public static AbstractTableModel getTableModel() {
        return new EmployeeTableModel(new ArrayList(EMPLOYEES.getValues()));
    }
    
  
    private static void showMainMenu() {
        System.out.println("#### Employee management system - Mainmenu ####");
        System.out.println("Type a number and Enter to make a choice.");
        System.out.println("1. Edit or view Employees");
        System.out.println("2. Show statistics");
        System.out.println("3. Exit application");
    }
    
    
    
    
    
    
    
    
    
    
    
    //*********** REMOVE LATER ************//
    private static void createTestData() {
        EMPLOYEES.add(new DairyWorker("Ove", Gender.MALE, 27000, 2));
        EMPLOYEES.add(new DairyWorker("Hasse", Gender.MALE, 27000, 5));
        EMPLOYEES.add(new DairyWorker("Greta", Gender.FEMALE, 26000, 2));
        EMPLOYEES.add(new DairyWorker("Tomas", Gender.MALE, 27000, 2));
        EMPLOYEES.add(new WarehouseWorker("Nisse", Gender.MALE, 26000, 3));
        EMPLOYEES.add(new WarehouseWorker("Lisa", Gender.FEMALE, 26000, 7));
        EMPLOYEES.add(new WarehouseWorker("Jonas", Gender.MALE, 28000, 13));
        EMPLOYEES.add(new Manager("Ida", Gender.FEMALE, 33000, 14));
        EMPLOYEES.add(new Manager("Anders", Gender.MALE, 33000, 6));
        EMPLOYEES.add(new Driver("Sixten", Gender.MALE, 29800, 3));
        EMPLOYEES.add(new Driver("Lotta", Gender.FEMALE, 29800, 9));
        EMPLOYEES.add(new Driver("Barbro", Gender.OTHER, 30000, 3));
        EMPLOYEES.add(new Driver("David", Gender.MALE, 29300, 1));
    }
    
}
