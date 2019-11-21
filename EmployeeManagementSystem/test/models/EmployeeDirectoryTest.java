/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Collection;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Lindkvist
 */
public class EmployeeDirectoryTest {
    private EmployeeDirectory employees;
    
    public EmployeeDirectoryTest() {
        employees = new EmployeeDirectory();
        Employee e1 = new Manager("Nisse", Gender.MALE, 1000, 1);
        Employee e2 = new Manager("Olle", Gender.MALE, 2000, 2);
        Employee e3 = new Manager("Kalle", Gender.MALE, 3000, 3);
        
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        
    }

    @Test
    public void testAdd() {
        Employee e = new Driver("Britta", Gender.FEMALE, 4000, 4);
        employees.add(e);
        assertEquals("Testing add()", 4 ,employees.size());
    }

    @Test
    public void testRemove() {
        Employee e = employees.getEmployee(1);
        int before = employees.size();
        employees.remove(e);
        assertEquals("Testing remove()", before-- ,employees.size());
    }

    @Test
    public void testGetEmployee() {
        Employee e = employees.getEmployee(2);
        assertNotNull(e);
    }

    @Test
    public void testFindByName() {
        List<Employee> found = employees.findByName("Nisse");
        assertEquals(1, found.size());
    }

    @Test
    public void testFindByGender() {
        List<Employee> found = employees.findByGender(Gender.MALE);
        assertEquals(3, found.size());
    }

    @Test
    public void testFindBySalary() {
        List<Employee> found = employees.findBySalary(900, 2100);
        assertEquals(2, found.size());
    }

    @Test
    public void testGetValues() {
        Collection<Employee> values = employees.getValues();
        assertEquals(3, values.size());
    }

    @Test
    public void testSize() {
        assertEquals("Testing size()", 3, employees.size());
    }
    
}
