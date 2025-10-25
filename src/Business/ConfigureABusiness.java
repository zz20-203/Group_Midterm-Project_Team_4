/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/
package Business;

import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.StudentDirectory;
import Business.Profiles.StudentProfile;

import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.Persona.*;


/**
 *
 * @author kal bugrara
 */
class ConfigureABusiness {

    static Business initialize() {
        Business business = new Business("Information Systems");

// Create Persons
      PersonDirectory persondirectory = business.getPersonDirectory();
// person representing sales organization        
        Person person001 = persondirectory.newPerson("John Smith");
        Person person002 = persondirectory.newPerson("Gina Montana");
        Person person003 = persondirectory.newPerson("Adam Rollen");
 
        Person person005 = persondirectory.newPerson("Jim Dellon");
        Person person006 = persondirectory.newPerson("Anna Shnider");
        Person person007 = persondirectory.newPerson("Laura Brown");
        Person person008 = persondirectory.newPerson("Jack While");
        Person person009 = persondirectory.newPerson("Fidelity"); //we use this as customer

// Create Admins to manage the business
        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
        EmployeeProfile employeeprofile0 = employeedirectory.newEmployeeProfile(person001);
        
        StudentDirectory studentdirectory = business.getStudentDirectory();
        StudentProfile studentprofile0 = studentdirectory.newStudentProfile(person003);

//create a pre-save student account that can use to log in and navigate in student portal
// Use NUID as the "person id" on the Business side for simplicity.
        String nuid   = "00223344";
        String uname  = "shaoweili";
        String passwd = "123456";
        
        Person sPerson = persondirectory.newPerson(nuid);
        try {
            sPerson.setFirstName("Shaowei");
            sPerson.setLastName("Li");
        } catch (Exception ignore){}

        StudentProfile sProfile = studentdirectory.newStudentProfile(sPerson);
        
        
// Create User accounts that link to specific profiles
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();
        UserAccount ua3 = uadirectory.newUserAccount(employeeprofile0, "admin", "****"); /// order products for one of the customers and performed by a sales person
        UserAccount ua4 = uadirectory.newUserAccount(studentprofile0, "adam", "****"); /// order products for one of the customers and performed by a sales person
         
        //login for Shaowei Li
        UserAccount ua5  = uadirectory.newUserAccount(sProfile, uname, passwd);
        
        // use the model side (info5100) for account information — department, courses, semester, enrollment
        Department dept = new Department("Information System");
        business.setModelDepartment(dept);

        CourseCatalog cc = dept.getCourseCatalog();

        // Semester and offering courses
        String sem = "Fall2025";
        CourseSchedule fall = dept.newCourseSchedule(sem);
        CourseOffer info5100Offer = fall.newCourseOffer("INFO5100");

        // Mirror the same student on the model side and enroll them
        info5100.university.example.Persona.PersonDirectory mpd = dept.getPersonDirectory();
        info5100.university.example.Persona.Person mPerson = mpd.newPerson(nuid);
        try {
            mPerson.setFirstName("Shaowei");
            mPerson.setLastName("Li");
        } catch (Exception ignore) { }

        info5100.university.example.Persona.StudentDirectory msd = dept.getStudentDirectory();
        info5100.university.example.Persona.StudentProfile mProfile = msd.newStudentProfile(mPerson);

        // Giving a courseload and a seat in INFO5100 for Fall2025
        info5100.university.example.CourseSchedule.CourseLoad cl = mProfile.newCourseLoad(sem);
        cl.newSeatAssignment(info5100Offer);

        return business;
        

    }

}
