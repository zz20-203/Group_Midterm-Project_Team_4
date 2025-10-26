
package util.student;

import Business.Business;
import Business.Person.Person;
import Business.UserAccounts.UserAccount;
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
        final String nuid   = "00223344";
        final String uname  = "shaoweili";
        final String passwd = "123456";

        // --- Business-side person/profile/login ---
        PersonDirectory bpd = business.getPersonDirectory();
        Person bPerson = bpd.newPerson(nuid);
        try { bPerson.setFirstName("Shaowei"); bPerson.setLastName("Li"); } catch (Exception ignore) {}

        StudentDirectory bsd = business.getStudentDirectory();
        StudentProfile   bStudent = bsd.newStudentProfile(bPerson);

        UserAccountDirectory uad = business.getUserAccountDirectory();

        // Create the account once (by username). If present, leave it alone.
        UserAccount existing = null;
        for (UserAccount ua : uad.getUserAccountList()) {
            if (uname.equals(ua.getUserLoginName())) { existing = ua; break; }
        }
        if (existing == null) {
            uad.newUserAccount(bStudent, uname, passwd);
        }

        if (business.getModelDepartment() == null) {
            business.setModelDepartment(dept);
        }

        // demo data
        CourseCatalog cc = dept.getCourseCatalog();
        Course c5100 = byNumberOrCreate(cc, "INFO5100", "Application Engineering", 4);
        Course c4200 = byNumberOrCreate(cc, "INFO4200", "Data Management", 4);
        Course c5000 = byNumberOrCreate(cc, "INFO5000", "Foundations of Info Sci", 4);

        info5100.university.example.Persona.PersonDirectory mpd = dept.getPersonDirectory();
        info5100.university.example.Persona.Person mPerson = mpd.newPerson(nuid);
        try { mPerson.setFirstName("Shaowei"); mPerson.setLastName("Li"); } catch (Exception ignore) {}
        info5100.university.example.Persona.StudentDirectory msd = dept.getStudentDirectory();
        info5100.university.example.Persona.StudentProfile mStudent = msd.newStudentProfile(mPerson);

        String summer = "Summer2025";
        CourseSchedule summerSched = dept.newCourseSchedule(summer);
        CourseOffer off4200 = summerSched.newCourseOffer(c4200.getCourseNumber()); off4200.generatSeats(30);
        CourseOffer off5000 = summerSched.newCourseOffer(c5000.getCourseNumber()); off5000.generatSeats(30);
        CourseLoad summerCL = mStudent.newCourseLoad(summer);
        SeatAssignment sa4200 = summerCL.newSeatAssignment(off4200);
        SeatAssignment sa5000 = summerCL.newSeatAssignment(off5000);
        safeSetGrade(sa4200, "A-");
        safeSetGrade(sa5000, "B+");

        String fall = "Fall2025";
        CourseSchedule fallSched = dept.newCourseSchedule(fall);
        CourseOffer off5100 = fallSched.newCourseOffer(c5100.getCourseNumber()); off5100.generatSeats(30);
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

