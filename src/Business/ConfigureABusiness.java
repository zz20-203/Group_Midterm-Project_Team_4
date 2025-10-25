package Business;

import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.FacultyProfile;
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
//// person representing sales organization        
//        Person person001 = persondirectory.newPerson("John Smith");
//        Person person002 = persondirectory.newPerson("Gina Montana");
//        Person person003 = persondirectory.newPerson("Adam Rollen");
// 
//        Person person005 = persondirectory.newPerson("Jim Dellon");
//        Person person006 = persondirectory.newPerson("Anna Shnider");
//        Person person007 = persondirectory.newPerson("Laura Brown");
//        Person person008 = persondirectory.newPerson("Jack While");
//        Person person009 = persondirectory.newPerson("Fidelity"); //we use this as customer
//
//// Create Admins to manage the business
//        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
//        EmployeeProfile employeeprofile0 = employeedirectory.newEmployeeProfile(person001);
//        
//        StudentDirectory studentdirectory = business.getStudentDirectory();
//        StudentProfile studentprofile0 = studentdirectory.newStudentProfile(person003);
//        
////        FacultyDirectory facultydirectory = business.getFacultyDirectory();
////        FacultyProfile facultyprofile0 = facultydirectory.newFacultyProfile(person003);
//        
//
//   
//// Create User accounts that link to specific profiles
//        UserAccountDirectory uadirectory = business.getUserAccountDirectory();
//        UserAccount ua3 = uadirectory.newUserAccount(employeeprofile0, "admin", "****"); /// order products for one of the customers and performed by a sales person
//        UserAccount ua4 = uadirectory.newUserAccount(studentprofile0, "adam", "****"); /// order products for one of the customers and performed by a sales person
////        UserAccount ua5 = uadirectory.newUserAccount(facultyprofile0, "faculty", "****");
//        return business;
        
    //TestCases - could be deleted
        // Admin 
        Person adminPerson = persondirectory.newPerson("1001");     
        adminPerson.setName("John Smith");                          

        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
        EmployeeProfile adminProfile = employeedirectory.newEmployeeProfile(adminPerson);

        // Student
        
        StudentDirectory studentdirectory = business.getStudentDirectory();
        
        Person studentPerson1 = persondirectory.newPerson("3001");
        studentPerson1.setName("Adam Rollen");
        StudentProfile studentProfile1 = studentdirectory.newStudentProfile(studentPerson1);

        Person studentPerson2 = persondirectory.newPerson("3002");
        studentPerson2.setName("Jamie Lee");
        StudentProfile studentProfile2 = studentdirectory.newStudentProfile(studentPerson2);

        Person studentPerson3 = persondirectory.newPerson("3003");
        studentPerson3.setName("Sophie Chen");
        StudentProfile studentProfile3 = studentdirectory.newStudentProfile(studentPerson3);

        // Faculty 

        FacultyDirectory facultydirectory = business.getFacultyDirectory();

        Person facultyPerson1 = persondirectory.newPerson("2001");
        facultyPerson1.setName("Lucy Green");
        FacultyProfile facultyProfile1 = facultydirectory.newFacultyProfile(facultyPerson1);

        Person facultyPerson2 = persondirectory.newPerson("2002");
        facultyPerson2.setName("David Brown");
        FacultyProfile facultyProfile2 = facultydirectory.newFacultyProfile(facultyPerson2);

        Person facultyPerson3 = persondirectory.newPerson("2003");
        facultyPerson3.setName("Emily Zhang");
        FacultyProfile facultyProfile3 = facultydirectory.newFacultyProfile(facultyPerson3);


        // Create User Accounts 
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();


        // Admin
        uadirectory.newUserAccount(adminProfile, "admin", "****");

        // Students
        uadirectory.newUserAccount(studentProfile1, "adam", "****");
        uadirectory.newUserAccount(studentProfile2, "jamie", "****");
        uadirectory.newUserAccount(studentProfile3, "sophie", "****");

        // Faculty
        uadirectory.newUserAccount(facultyProfile1, "lucy", "****");
        uadirectory.newUserAccount(facultyProfile2, "david", "****");
        uadirectory.newUserAccount(facultyProfile3, "emily", "****");

        return business;    
    }
}