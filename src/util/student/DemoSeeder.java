
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
import info5100.university.example.CourseSchedule.SeatAssignment;

/**
 *
 * @author shaoweili
 */

//try to save one demo student data so that we can have the historical data to navigate in student portal
public final class DemoSeeder {

    private DemoSeeder() {}

    public static void seedStudent(Business business, Department dept) {
        // ---- Business-side person/profile/login (used by your login pane)
        String nuid   = "00223344";
        String uname  = "vickyli";
        String passwd = "123456";
        
         // If the account already exists, don’t seed twice
        if (business.getUserAccountDirectory().AuthenticateUser(uname, passwd) != null) {
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
        info5100.university.example.Persona.PersonDirectory mpd = dept.getPersonDirectory();
        info5100.university.example.Persona.Person mPerson = mpd.newPerson(nuid);
        try { mPerson.setFirstName("Vicky"); mPerson.setLastName("Li"); } catch (Exception ignore) {}

        info5100.university.example.Persona.StudentDirectory msd = dept.getStudentDirectory();
        info5100.university.example.Persona.StudentProfile mStudent = msd.newStudentProfile(mPerson);


        //pre save some student data from summer2025 semester
        String summer = "Summer2025";
        CourseSchedule summerSched = dept.newCourseSchedule(summer);

        CourseOffer off4200 = summerSched.newCourseOffer(c4200.getCourseNumber());
        off4200.generatSeats(30);
        CourseOffer off5000 = summerSched.newCourseOffer(c5000.getCourseNumber());
        off5000.generatSeats(30);

        CourseLoad summerCL = mStudent.newCourseLoad(summer);

        SeatAssignment sa4200 = summerCL.newSeatAssignment(off4200);
        SeatAssignment sa5000 = summerCL.newSeatAssignment(off5000);

        // Assign letter grades so they count as “earned”
        safeSetGrade(sa4200, "A-");
        safeSetGrade(sa5000, "B+");

        // Assign letter grades (so they count as earned in audit part)
        String fall = "Fall2025";
        CourseSchedule fallSched = dept.newCourseSchedule(fall);
        CourseOffer off5100 = fallSched.newCourseOffer(c5100.getCourseNumber());
        off5100.generatSeats(30);

        CourseLoad fallCL = mStudent.newCourseLoad(fall);
        fallCL.newSeatAssignment(off5100);
    }
    
    private static Course byNumberOrCreate(CourseCatalog cc, String number, String name, int credits) {
        Course c = cc.getCourseByNumber(number);
        if (c == null) {
            // Course constructor is (name, number, credits)
            c = cc.newCourse(name, number, credits);
        }
        return c;
    }
    
    //Assign a letter grade via reflection to contain any differences.
    private static void safeSetGrade(Object seatAssignment, String letter) {
        try {
            seatAssignment.getClass().getMethod("setGrade", String.class).invoke(seatAssignment, letter);
            return;
        } catch (Exception ignore) { }
        try {
            seatAssignment.getClass().getMethod("assignGrade", String.class).invoke(seatAssignment, letter);
        } catch (Exception ignore) { }
        }
    }

