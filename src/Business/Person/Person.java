/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

/**
 *
 * @author kal bugrara
 */
public class Person {

    String id;
    String firstName;
    String lastName;
    String email;

    public Person(String id) {

        this.id = id;
    }

    public String getPersonId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }
    
    public String getName() {
    String first = (firstName != null) ? firstName : "";
    String last = (lastName != null) ? lastName : "";
    String fullName = (first + " " + last).trim(); //
    return fullName.isEmpty() ? "N/A" : fullName;
    }
    
    public void setName(String fullName) {
    if (fullName == null || fullName.trim().isEmpty()) {
        this.firstName = "";
        this.lastName = "";
        return;
    }

    String[] parts = fullName.trim().split("\\s+", 2); 
    this.firstName = parts[0];

    if (parts.length > 1) {
        this.lastName = parts[1];
    } else {
        this.lastName = ""; 
    }
    }

    @Override
    public String toString() {
        return getPersonId();
    }
}
