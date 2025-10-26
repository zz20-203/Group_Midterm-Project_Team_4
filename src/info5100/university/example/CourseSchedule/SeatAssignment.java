
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;

public class SeatAssignment {
    float grade = -1f; //(Letter grade mappings: A=4.0, A-=3.7, B+=3.3, B=3.0, )
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
    this.grade = letterToPoints(letter);
    }
    
    public String getGrade() {
    return pointsToLetter(this.grade);
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

    private float letterToPoints(String letter) {
         if (letter == null) return -1f;
        switch (letter) {
            case "A": return 4.0f; case "A-": return 3.7f;
            case "B+": return 3.3f; case "B": return 3.0f; case "B-": return 2.7f;
            case "C+": return 2.3f; case "C": return 2.0f; case "C-": return 1.7f;
            case "D": return 1.0f; case "F": return 0.0f;
            default: return -1f; // not graded / IP
        
    }
    }

    private String pointsToLetter(float pts) {
        if (pts < 0) return "-";
        if (pts >= 3.95f) return "A";
        if (pts >= 3.55f) return "A-";
        if (pts >= 3.15f) return "B+";
        if (pts >= 2.85f) return "B";
        if (pts >= 2.55f) return "B-";
        if (pts >= 2.15f) return "C+";
        if (pts >= 1.95f) return "C";
        if (pts >= 1.65f) return "C-";
        if (pts >= 0.5f)  return "D";
        return "F";
    }
}


