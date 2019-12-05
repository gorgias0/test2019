package ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import models.EmployeeDirectory;
import models.*;
import utilities.BetterScanner;
import models.Gender;

/**
 *
 * @author Daniel Lindkvist
 */
public final class EmployeeUI {
    private static EmployeeDirectory employees;
    private static BetterScanner scanner;
    
    Comparator<Employee> salaryComparator = (Employee o1, Employee o2)-> {
        return Double.compare(o1.getSalary(), o2.getSalary());
    };
    
    Comparator<Employee> nameComparator = (Employee o1, Employee o2) -> {
        return o1.getName().compareToIgnoreCase(o2.getName());  
    };
    
    public EmployeeUI(EmployeeDirectory employees, BetterScanner scanner) {
        EmployeeUI.employees = employees;
        EmployeeUI.scanner = scanner;
    }
    
    
    public void showAllEmployees() {
        employees.getValues().forEach(System.out::println);
    }
   
    
    public void showAllSorted() {
        ArrayList emplist = new ArrayList(employees.getValues());
        System.out.println("Please choose sort order.\n1. for name\n2. for name descending");
        System.out.println("3. for salary\n4. for salary descending");
        String input = scanner.nextLine();
        switch (input) {
            case "1": emplist.sort(nameComparator);
            break;
            case "2": emplist.sort( nameComparator.reversed());
            break;
            case "3": emplist.sort(salaryComparator);
            break;
            case "4": emplist.sort(salaryComparator.reversed());
            break;
            default: System.out.println("Not a valid option. Default sort order is used.");
        }
        emplist.forEach(System.out::println);
    }    
    
    public void showAllBonuses() {
        employees.getValues().forEach(employee -> System.out.println(Main.twoDecimals(employee.getBonus())));
    }
    
    public void printSearchResults(List<Employee> list) {
        System.out.println("Number of Employees found = " + list.size());
        list.forEach(System.out::println);
        System.out.println("");
    }
    
    public void searchByName() {
        System.out.print("Type in a name or part of name to search for: ");
        String input = scanner.nextLine();
        List result = employees.findByName(input);
        printSearchResults(result);
    }
    
    public void searchByGender() {
        System.out.println("Search by gender:");
        System.out.println("1. for " + Gender.FEMALE);
        System.out.println("2. for " + Gender.MALE);
        System.out.println("3. for " + Gender.OTHER);
        
        String input = scanner.nextLine();
        List<Employee> result = new ArrayList<>();
        
        switch (input) {
            case "1": result = employees.findByGender(Gender.FEMALE); break;
            case "2": result = employees.findByGender(Gender.MALE); break;
            case "3": result = employees.findByGender(Gender.OTHER); break;
        }
        printSearchResults(result);
    }
    
    private void removeEmployee() {

        int id = scanner.readInt("Please input the ID of employee to remove: ");

        Employee em = employees.getEmployee(id);
        if (em != null) {
            System.out.println("Removing employee: " + em.toString());
            employees.remove(em);
        } else {
            System.out.println("No employee found with ID: "+id);
        }
    }
    
    private void editEmployee() {
        int id = scanner.readInt("Enter ID of employee to change: ");

        Employee e = employees.getEmployee(id);
        if(e == null) {
            System.out.println("No such employee in system. Returning to menu.");
            return;
        }
        System.out.println("Employee selected: "+e);
        boolean loop = true;
        while (loop) {
            System.out.println("What do you want to change:");
            System.out.println("1. for name\n2. for salary\n3. for gender\n4. for workyears\n5. to return to menu");
            String input = scanner.nextLine();
            switch (input){
                case "1": System.out.print("Enter new name: "); e.setName(scanner.nextLine()); break;
                case "2": e.setSalary(scanner.readDouble("Enter new salary: ")); break;
                case "3": e.setGender(askForGender()); break;
                case "4": e.setWorkyears(scanner.readInt("Enter number of workyears:")); break;
                case "5": System.out.println("Returning to menu"); loop = false; break;
                default: System.out.println("Invalid input, please try again.");
            }
        }
    }
    
    
    private Gender askForGender() {
        Gender g = null;
        while (g == null) {
            System.out.print("Gender?\n1 for male, 2 for female, 3 for other: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1": g = Gender.MALE; break;
                case "2": g = Gender.FEMALE; break;
                case "3": g = Gender.OTHER; break;
                default: System.out.println("Invalid input, try again."); break;
            }
        }
        return g;
    }
    
    private void addNewEmployee() {
        Employee newEmployee;
        String name;
        Gender gender;
        double salary;
        int workyears = 0;
        
        System.out.println("Name?");
        name = scanner.nextLine();
        
        gender = askForGender();
        salary = scanner.readDouble("Salary?");
        
        System.out.println("What role does this employee have?");
        System.out.println("1. for manager");
        System.out.println("2. for driver");
        System.out.println("3. for dairyworker");
        System.out.println("4. for warehouseworker");
        
        String input = scanner.nextLine();
        
        switch (input) {
            case "1": newEmployee = new Manager(name,gender,salary,workyears); break;
            case "2": newEmployee = new Driver(name,gender,salary,workyears); break;
            case "3": newEmployee = new DairyWorker(name,gender,salary,workyears); break;
            case "4": newEmployee = new WarehouseWorker(name,gender,salary,workyears); break;
            default: System.out.println("Invalid input, returning to menu"); return;
        }
        employees.add(newEmployee);
        System.out.println("Number of employees in system is: " + employees.size());
        
    }
    
    private void showSearchMenu() {
        
        while (true) {
            System.out.println("#### Search menu ####");
            System.out.println("1. to search by name");
            System.out.println("2. to search by gender");
            System.out.println("3. to return to menu");
            
            String input = scanner.nextLine();
            
            switch(input) {
                case "1": searchByName(); break;
                case "2": searchByGender(); break;
                case "3": System.out.println("Returning to menu"); return;
                default: System.out.println("Invalid input, try again"); break;
            }
        }        
        
    }
    
    public void showEmployee() {
        int id = scanner.readInt("Please input an ID of employee to show: ");
        Employee em = employees.getEmployee(id);
        if (em != null) {
            System.out.println("ID: "+em.getEmployeeID());
            System.out.println("Title: "+ em.getTitle());
            System.out.println("Name: "+ em.getName());
            System.out.println("Gender: " + em.getGender());
            System.out.println("Years of employment: " + em.getWorkyears());
            System.out.println("Salary: " + Main.twoDecimals(em.getSalary()));
            System.out.println("Bonus: " + Main.twoDecimals(em.getBonus()));
        } else {
            System.out.println("No employee with id: "+id+" found");
        }
    }
    
    public void showEmployeeMenu() {
      
        while (true) {
            System.out.println("#### Employee menu ####");
            System.out.println("1. to add new employee");
            System.out.println("2. to edit employee");
            System.out.println("3. to remove employee");
            System.out.println("4. to show one employee");
            System.out.println("5. to show all employees");
            System.out.println("6. to search for employees...");
            System.out.println("7. to return to the main menu");
            
            String input = scanner.nextLine();
            
            switch (input) {
                case "7": System.out.println("Returning to main menu"); return;
                case "6": showSearchMenu(); break;
                case "5": showAllSorted(); break;
                case "1": addNewEmployee(); break;
                case "4": showEmployee(); break;
                case "2": editEmployee(); break;
                case "3": removeEmployee(); break;
                default: System.out.println("Invalid input, try again"); break;
                
            }
        }
    }
    
    
}
