
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;

public class SeatAssignment {
    float grade; //(Letter grade mappings: A=4.0, A-=3.7, B+=3.3, B=3.0, )
    Seat seat;
    boolean like; //true means like and false means not like
    CourseLoad courseload;
    public SeatAssignment(CourseLoad cl, Seat s){
        seat = s;
        courseload = cl;
    }
     
    public boolean getLike(){
        return like;
    }
    
    public void setGrade(String letter) {
        this.grade = mapLetter(letter);
    }
    
    public void assignGrade(String letter) {
        setGrade(letter);
    }

    public void assignSeatToStudent(CourseLoad cl){
        courseload = cl;
    }
    
    public int getCreditHours(){
        return seat.getCourseCredits();
       
    }
    public Seat getSeat(){
        return seat;
    }
    public CourseOffer getCourseOffer(){
        
        return seat.getCourseOffer();
    }
    public Course getAssociatedCourse(){
        
        return getCourseOffer().getSubjectCourse();
    }
    public float GetCourseStudentScore(){
        return getCreditHours()*grade;
    }

    private float mapLetter(String letter) {
    if (letter == null) return 0f;
    switch (letter.trim().toUpperCase()) {
        case "A":  return 4.0f;
        case "A-": return 3.7f;
        case "B+": return 3.3f;
        case "B":  return 3.0f;
        case "B-": return 2.7f;
        case "C+": return 2.3f;
        case "C":  return 2.0f;
        case "C-": return 1.7f;
        case "D":  return 1.0f;
        case "F":  return 0.0f;
        default:   return 0.0f;
    }
    }
}


