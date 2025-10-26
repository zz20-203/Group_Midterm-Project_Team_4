/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Department;

import java.util.ArrayList;

/**
 *
 * @author Luciela us Biktria
 */

public class DepartmentList {
    private ArrayList<Department> departments;

    public DepartmentList() {
        this.departments = new ArrayList<>();
    }
    
    public Department newDepartment(String name) {
        Department dept = new Department(name);
        this.departments.add(dept);
        return dept;
    }

    public Department findDepartment(String name) {
        for (Department d : departments) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }
    
    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
