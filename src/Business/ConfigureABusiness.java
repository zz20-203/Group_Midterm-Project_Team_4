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


/**
 *
 * @author kal bugrara
 */
class ConfigureABusiness {

    static Business initialize() {
        Business business = new Business("Information Systems");
        Person p1 = business.getPersonDirectory().newPerson("1"); //TestCases - could be deleted
        business.getStudentDirectory().newStudentProfile(p1);  //TestCases - could be deleted
        
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
//        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
//        EmployeeProfile employeeprofile0 = employeedirectory.newEmployeeProfile(person001);
//        
//        StudentDirectory studentdirectory = business.getStudentDirectory();
//        StudentProfile studentprofile0 = studentdirectory.newStudentProfile(person003);
        


   
// Create User accounts that link to specific profiles
//TestCases - could be deleted
        // Admin 
        Person adminPerson = persondirectory.newPerson("1001");     
        adminPerson.setName("John Smith");                          

        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
        EmployeeProfile adminProfile = employeedirectory.newEmployeeProfile(adminPerson);
        
        // Create User Accounts 
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();


        // Admin
        uadirectory.newUserAccount(adminProfile, "admin", "****");
        
        
        
 //       UserAccount ua3 = uadirectory.newUserAccount(employeeprofile0, "admin", "****"); /// order products for one of the customers and performed by a sales person
 //       UserAccount ua4 = uadirectory.newUserAccount(studentprofile0, "adam", "****"); /// order products for one of the customers and performed by a sales person

        return business;

    }

}
