
package info5100.university.example.CourseSchedule;

public class Seat {
    
    Boolean occupied; 
    int number;
    SeatAssignment seatassignment; //links back to studentprofile
    CourseOffer courseoffer;
    
    public Seat (CourseOffer co, int n){
        courseoffer = co;
        number = n;
        occupied = false;
        
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SeatAssignment getSeatAssignment() {
        return seatassignment;
    }

    public void setSeatAssignment(SeatAssignment seatassignment) {
        this.seatassignment = seatassignment;
    }

    public CourseOffer getCourseoffer() {
        return courseoffer;
    }

    public void setCourseoffer(CourseOffer courseoffer) {
        this.courseoffer = courseoffer;
    }
    
    public Boolean isOccupied(){
        return occupied;

    }
    public SeatAssignment newSeatAssignment(CourseLoad cl){
        
        seatassignment = new SeatAssignment(cl, this); //links seatassignment to seat
        occupied = true;   
        return seatassignment;
    }
    
    public CourseOffer getCourseOffer(){
        return courseoffer;
    }
    
    public int getCourseCredits(){
        return courseoffer.getCreditHours();
    }
    
    public void freeSeat(){
        seatassignment = null;
        occupied = false;    
    }
    
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
}
