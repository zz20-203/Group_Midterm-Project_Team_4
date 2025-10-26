
package info5100.university.example.CourseCatalog;

/**
 *
 * @author kal bugrara
 */
public class Course {

    String number;
    String name;
    int credits;
    int price = 1500; //per credit hour

    public Course(String n, String numb, int ch) {
        name = n;
        number = numb;
        credits = ch;

    }

    public String getCourseNumber() {
        return number;
    }

    public int getCoursePrice() {
        return price * credits;
    }

    public int getCredits() {
        return credits;
    
    }
    
    public String getName() {
        return name;
    }
    
    public void setCourseNumber(String number) {
        this.number = number;
    }

    public void setCourseName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}