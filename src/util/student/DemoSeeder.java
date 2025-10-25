
package util.student;

import Business.Business;
import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.StudentDirectory;
import Business.Profiles.StudentProfile;
import Business.UserAccounts.UserAccountDirectory;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.Persona.*;
/**
 *
 * @author shaoweili
 */


public final class DemoSeeder {
    private DemoSeeder() {}

    public static void seedStudent(Business business, Department dept) {
        // ---- Business-side person/profile/login (used by your login pane)
        String nuid   = "00223344";
        String uname  = "vickyli";
        String passwd = "123456";
        
        
        // If the account already exists, don’t seed twice
        if (business.getUserAccountDirectory()
                    .AuthenticateUser(uname, passwd) != null) {
            return;
        }

        PersonDirectory bpd = business.getPersonDirectory();
        Person bPerson = bpd.newPerson(nuid);
        try { bPerson.setFirstName("Vicky"); bPerson.setLastName("Li"); } catch (Exception ignore) {}

        StudentDirectory bsd = business.getStudentDirectory();
        StudentProfile bStudent = bsd.newStudentProfile(bPerson);

        UserAccountDirectory uad = business.getUserAccountDirectory();
        uad.newUserAccount(bStudent, uname, passwd);

        // department data the student portal can read
        if (business.getModelDepartment() == null) {
            business.setModelDepartment(dept);
        }

        // create and find course
        CourseCatalog cc = dept.getCourseCatalog();

        Course c5100 = byNumberOrCreate(cc, "INFO5100", "Application Engineering", 4);
        Course c4200 = byNumberOrCreate(cc, "INFO4200", "Data Management",         4);
        Course c5000 = byNumberOrCreate(cc, "INFO5000", "Foundations of Info Sci", 4);

        // Create and find the model-side student
        Persona.PersonDirectory mpd = dept.getPersonDirectory();
        Persona.Person mPerson = mpd.newPerson(nuid);
        try { mPerson.setFirstName("Vicky"); mPerson.setLastName("Li"); } catch (Exception ignore) {}

        Persona.StudentDirectory msd = dept.getStudentDirectory();
        Persona.StudentProfile mStudent = msd.newStudentProfile(mPerson);

        //pre save some student data from summer2025 semester
        String summer = "Summer2025";
        CourseSchedule summerSched = dept.newCourseSchedule(summer);

        CourseOffer off4200 = summerSched.newCourseOffer(c4200.getCourseNumber());
        off4200.generatSeats(30);
        CourseOffer off5000 = summerSched.newCourseOffer(c5000.getCourseNumber());
        off5000.generatSeats(30);

        CourseLoad summerCL = mStudent.newCourseLoad(summer);
        var sa4200 = summerCL.newSeatAssignment(off4200);
        var sa5000 = summerCL.newSeatAssignment(off5000);

        // Assign letter grades (so they count as earned in audit part)
        safeSetGrade(sa4200, "A-");   
        safeSetGrade(sa5000, "B+"); 

        // make fall 2025 as in progress semester and save
        String fall = "Fall2025";
        CourseSchedule fallSched = dept.newCourseSchedule(fall);
        CourseOffer off5100 = fallSched.newCourseOffer(c5100.getCourseNumber());
        off5100.generatSeats(30);

        CourseLoad fallCL = mStudent.newCourseLoad(fall);
        fallCL.newSeatAssignment(off5100);
    }
    }
}
