/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.StudentDirectory;
import Business.Profiles.FacultyDirectory;
import info5100.university.example.Department.Department;

import Business.UserAccounts.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory; //all people profiles regardless of the role

    EmployeeDirectory employeedirectory;
    UserAccountDirectory useraccountdirectory;
    StudentDirectory studentdirectory;
    FacultyDirectory facultydirectory;
    
    private Department modelDepartment;

    public Department getModelDepartment() { return modelDepartment; }
    public void setModelDepartment(Department modelDepartment) { this.modelDepartment = modelDepartment; }
    


    public Business(String n) {
        name = n;

        persondirectory = new PersonDirectory();
        employeedirectory = new EmployeeDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        studentdirectory = new StudentDirectory();


    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }


    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public StudentDirectory getStudentDirectory(){
        return studentdirectory;
    }
    

    public FacultyDirectory getFacultyDirectory(){ // ADD
        return facultydirectory;
    }

}
