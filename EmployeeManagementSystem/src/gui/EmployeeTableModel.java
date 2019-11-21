/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Employee;

/**
 *
 * @author Daniel Lindkvist
 */
public class EmployeeTableModel extends AbstractTableModel {
    
    private List<Employee> list;
    
    public EmployeeTableModel(List<Employee> list)  {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }
    public String getColumnName(int column) {
        String name = "";
        switch(column) {
            case 0:
                name = "ID";
                break;
            case 1:
                name = "Name";
                break;
            case 2:
                name = "Title";
                break;
            case 3:
                name = "Gender";
                break;
            case 4:
                name = "Workyears";
                break;
            case 5:
                name = "Salary";
                break;        
            case 6:
                name = "Bonus";
                break;                
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Object value = "??";
        Employee e = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = e.getEmployeeID();
                break;
            case 1:
                value = e.getName();
                break;
            case 2:
                value = e.getTitle();
                break;
            case 3:
                value = e.getGender();
                break;
            case 4:
                value = e.getWorkyears();
                break;
            case 5:
                value = e.getSalary();
                break;
            case 6:
                value = e.getBonus();
                break;
        }

        return value;
    }
    


    
}
