package models;

import ui.Main;

/**
 *
 * @author Daniel Lindkvist
 */
public abstract class Employee implements Comparable<Employee> {
    private int employeeID = 0;
    private String name;
    private Gender gender;
    private double salary;
    private int workyears;
     
    
    public Employee(String name, Gender gender, double salary, int workyears) {
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.workyears = workyears;
    }
    
    public abstract String getTitle();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkyears() {
        return workyears;
    }

    public void setWorkyears(int workyears) {
        this.workyears = workyears;
    }

    public void setEmployeeID(int id) {
        this.employeeID = id;
    }

    public int getEmployeeID() {
        return employeeID;
    }
    
    public double getBonus(){
        return salary + workyears*100;
    }
    
    public abstract String getWorkRole();

    @Override
    public String toString() {
        String sal = Main.twoDecimals(salary);
        return "ID: " + employeeID + ", Name: " + name + ", Gender: " + gender + ", Salary: " + sal + ", Years of employment: " + workyears;
    }

    @Override
    public int compareTo(Employee e) {
        return Integer.compare(this.employeeID, e.getEmployeeID());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.employeeID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.employeeID != other.employeeID) {
            return false;
        }
        return true;
    }

}
