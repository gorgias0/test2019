package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Daniel Lindkvist
 */
public class EmployeeDirectory {
    
    private final Map<Integer, Employee> employeeMap = new LinkedHashMap<>();
    
    
    /**
     * Adds an employee to the collection and assigns an employeeID
     * @param em Employee to add
     */
    public void add(Employee em) {
        em.setEmployeeID(getNewEmployeeID());
        employeeMap.put(em.getEmployeeID(), em);
    }
    
    /**
     * 
     * @param em Employee to remove
     * @return returns the removed employee or null
     */
    public Employee remove(Employee em)  {
        return employeeMap.remove(em.getEmployeeID());
    }
    
    /**
     * 
     * @param id employeeID
     * @return Employee with matching id or null if not found
     */
    public Employee getEmployee(int id) {
        return employeeMap.get(id);
    }
    
    /**
     * 
     * @param name name or part of name to search for
     * @return a List of Employees with matching names
     * or an empty List
     */
    public List<Employee> findByName(String name) {
        List<Employee> result = new ArrayList<>();
        employeeMap.values().stream().filter((e) -> e.getName().toLowerCase().
                contains(name.toLowerCase().trim())).forEach((e) -> {result.add(e);});

        return result;
    }
    
     /**
     * 
     * @param gender enum Gender
     * @return a List of Employees with matching gender
     * or an empty List
     */   
    public List<Employee> findByGender(Gender gender) {
        List<Employee> result = new ArrayList<>();
        employeeMap.values().stream().filter((e) -> (e.getGender() == gender)).forEachOrdered((e) -> {
            result.add(e);
        });
        return result;
    }
    
    public List<Employee> findBySalary(double min, double max) {
        List<Employee> result = new ArrayList<>();
        employeeMap.values().stream().filter((e) -> (e.getSalary() >= min && e.getSalary() <= max)).
                forEach(e -> result.add(e));
        return result;
    }
    
    
    /**
     * 
     * @return a new unique employeeID 
     */
    private int getNewEmployeeID() {
        return size() == 0 ? 0 : getValues().stream().mapToInt(Employee::getEmployeeID).max().getAsInt() + 1;
    }
    
    public Collection<Employee> getValues() {
        return employeeMap.values();
    }
    
    public int size() {
        return employeeMap.size();
    }
   
}
