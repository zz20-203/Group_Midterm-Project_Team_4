/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccounts;

import Business.Profiles.Profile;
import java.time.LocalDateTime;



/**
 *
 * @author kal bugrara
 */
public class UserAccount {
    
    Profile profile;
    String username;
    String password;
    LocalDateTime lastAccessed;
    LocalDateTime lastUpdated;
    String status = "Active"; 


    public UserAccount (Profile profile, String un, String pw){
        username = un;
         password = pw;
         this.profile = profile;
         this.lastUpdated = LocalDateTime.now();
    }

    public String getPersonId(){
        return profile.getPerson().getPersonId();
    }
    public String getUserLoginName(){
        return username;
    }

    public boolean isMatch(String id){
    if(getPersonId().equals(id)) return true;
    return false;
    }
    public boolean IsValidUser(String un, String pw){

        if (username.equalsIgnoreCase(un) && password.equals(pw)) return true;
        else return false;

    }
    public String getRole(){
        return profile.getRole();
    }

    public Profile getAssociatedPersonProfile(){
        return profile;
    }
        public LocalDateTime getLastAccessed() {
    return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
    public void setUserLoginName(String username) {
        this.username = username;
        touchUpdated();
    }
    
    public void touchUpdated(){
        this.lastUpdated = LocalDateTime.now();
    }
        
    @Override
        public String toString(){
            
            return getUserLoginName();
        }
        
        
    
}

