/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

public class Person {

    String personId;  

    // --- NEW fields ---
    private String firstName;
    private String lastName;  

    
    public Person(String id) {
        this.personId = id;
    }

    
    public String getPersonId() {
        return personId;
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
    
    public boolean isMatch(String id) {
    return id != null && id.equals(this.personId);
    }

    @Override
    public String toString() {
        return personId;
    }
}
