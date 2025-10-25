/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import java.util.ArrayList;
import Business.Person.Person;

/**
 *
 * @author Li Zhang
 */
public class FacultyDirectory {
    ArrayList<FacultyProfile> facultyList = new ArrayList<>();

    public FacultyProfile newFacultyProfile(Person p){
        FacultyProfile fp = new FacultyProfile(p);
        facultyList.add(fp);
        return fp;
    }

    public FacultyProfile findFaculty(String id){
        for (FacultyProfile fp : facultyList)
            if (fp.isMatch(id)) return fp;
        return null;
    }

    public ArrayList<FacultyProfile> getFacultyList(){ return facultyList; }
}
