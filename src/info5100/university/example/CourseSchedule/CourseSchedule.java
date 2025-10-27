
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import java.util.ArrayList;

public class CourseSchedule {

    CourseCatalog coursecatalog;

    ArrayList<CourseOffer> schedule;
    String semester;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public CourseSchedule(String s, CourseCatalog cc) {
        semester = s;
        coursecatalog = cc;
        schedule = new ArrayList();

    }

    public CourseOffer newCourseOffer(String  n) {

        Course c = coursecatalog.getCourseByNumber(n);
        if(c==null) return null;
        CourseOffer co;
        co = new CourseOffer(c);
        schedule.add(co);
        return co;
    }

    public CourseOffer getCourseOfferByNumber(String n) {

        for (CourseOffer co : schedule) {

            if (co.getCourseNumber().equals(n)) {
                return co;
            }
        }
        return null;
    }

    public int calculateTotalRevenues() {
        int sum = 0;
        for (CourseOffer co : schedule) {

            sum = sum + co.getTotalCourseRevenues();

        }
        return sum;
    }
    
    public java.util.List<CourseOffer> getCourseOfferList() {   
        return schedule;
    }

    public CourseOffer getCourseOffer(String number) {
        return getCourseOfferByNumber(number); 
    }


}