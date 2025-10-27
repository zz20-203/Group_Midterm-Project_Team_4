/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Person.Person;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.FacultyProfile;
import Business.Profiles.Profile;
import Business.Profiles.StudentProfile;
import util.student.DemoSeeder;

import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

import info5100.university.example.Department.Department;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;

import UserInterface.WorkAreas.AdminRole.AdminRoleWorkAreaJPanel;
import UserInterface.WorkAreas.FacultyRole.FacultyWorkAreaJPanel;
import UserInterface.WorkAreas.StudentRole.SignUpJPanel;
import UserInterface.WorkAreas.StudentRole.StudentWorkAreaJPanel;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kal bugrara
 */
public class ProfileWorkAreaMainFrame extends javax.swing.JFrame {

    Business business;
    Department dept;

    /**
     * Creates new form PricingMainFrame
     */
    public static final String HOME_CARD = "studentHome";
    private static final String SIGNUP_CARD = "signup";

    public ProfileWorkAreaMainFrame() {
        initComponents();
        CardSequencePanel.setLayout(new java.awt.CardLayout());
        business = ConfigureABusiness.initialize();
        
        dept = business.getDepartmentList().findDepartment("Information Systems");
        if (dept == null) {
            dept = business.getDepartmentList().newDepartment("Information System");
            business.getDepartmentList().newDepartment("Mathematics");
            business.getDepartmentList().newDepartment("Philosophy");
        }

        business.setModelDepartment(dept);

        // Seed one demo student + model data
        DemoSeeder.seedStudent(business, dept);
        
        CourseCatalog cc = dept.getCourseCatalog();
        Course c5100 = cc.newCourse("Application Engineering & Development", "INFO5100", 4);
        Course c6150 = cc.newCourse("Web Design & UX", "INFO6150", 4);
        Course c6205 = cc.newCourse("Program Structure & Algorithms", "INFO6205", 4);

        // --- Schedule and Offering Creation ---
        CourseSchedule fall = dept.newCourseSchedule("Fall2025");
        CourseOffer fall5100Offer = fall.newCourseOffer(c5100.getCourseNumber());
        fall5100Offer.generatSeats(30);
        fall.newCourseOffer(c6150.getCourseNumber()).generatSeats(30);

        CourseSchedule spring = dept.newCourseSchedule("Spring2026");
        spring.newCourseOffer(c6205.getCourseNumber()).generatSeats(30);
        spring.newCourseOffer(c5100.getCourseNumber()).generatSeats(30);

        // --- FACULTY SEEDING AND ASSIGNMENT ---
        final String facultyNuid = "1002"; // Alice Teacher's NUID from ConfigureABusiness
        
        // Ensure the academic-side Person and FacultyProfile exist for Alice
        info5100.university.example.Persona.Person academicPerson = dept.getPersonDirectory().findPerson(facultyNuid);
        if (academicPerson == null) {
            academicPerson = dept.getPersonDirectory().newPerson(facultyNuid);
            // We can find the business-side person to copy details if needed
            Person businessPerson = business.getPersonDirectory().findPerson(facultyNuid);
            if (businessPerson != null) {
                academicPerson.setFirstName(businessPerson.getFirstName());
                academicPerson.setLastName(businessPerson.getLastName());
            }
            // Create the academic faculty profile
            dept.getFacultydirectory().newFacultyProfile(academicPerson);
        }
        
        // Assign the faculty to the course offering
        info5100.university.example.Persona.Faculty.FacultyProfile academicFacultyProfile = 
            dept.getFacultydirectory().findTeachingFaculty(facultyNuid);
        if (academicFacultyProfile != null) {
            fall5100Offer.AssignAsTeacher(academicFacultyProfile);
        }
        
                // --- SEED 3 STUDENTS FOR PERFORMANCE REPORT ---

        // Student 1: Alice Able, Grade A+
        final String s1_nuid = "003000001";
        final String s1_uname = "able";
        final String s1_passwd = "password";
        
        Person s1_bPerson = business.getPersonDirectory().newPerson(s1_nuid);
        s1_bPerson.setFirstName("Alice");
        s1_bPerson.setLastName("Able");
        s1_bPerson.setEmail("aliceable@northeastern.edu");
        StudentProfile s1_bProfile = business.getStudentDirectory().newStudentProfile(s1_bPerson);
        business.getUserAccountDirectory().newUserAccount(s1_bProfile, s1_uname, s1_passwd);
        
        info5100.university.example.Persona.Person s1_aPerson = dept.getPersonDirectory().newPerson(s1_nuid);
        s1_aPerson.setFirstName("Alice");
        s1_aPerson.setLastName("Able");
        info5100.university.example.Persona.StudentProfile s1_aProfile = dept.getStudentDirectory().newStudentProfile(s1_aPerson);
        
        CourseLoad s1_fallLoad = s1_aProfile.newCourseLoad("Fall2025");
        SeatAssignment s1_sa = s1_fallLoad.newSeatAssignment(fall5100Offer);
        if (s1_sa != null) {
            s1_sa.setGrade("A"); 
        }

        // Student 2: Bob Baker, Grade A-
        final String s2_nuid = "003000002";
        final String s2_uname = "baker";
        final String s2_passwd = "password";
        
        Person s2_bPerson = business.getPersonDirectory().newPerson(s2_nuid);
        s2_bPerson.setFirstName("Bob");
        s2_bPerson.setLastName("Baker");
        s2_bPerson.setEmail("bob_baker@gmail.com");
        StudentProfile s2_bProfile = business.getStudentDirectory().newStudentProfile(s2_bPerson);
        business.getUserAccountDirectory().newUserAccount(s2_bProfile, s2_uname, s2_passwd);
        
        info5100.university.example.Persona.Person s2_aPerson = dept.getPersonDirectory().newPerson(s2_nuid);
        s2_aPerson.setFirstName("Bob");
        s2_aPerson.setLastName("Baker");
        info5100.university.example.Persona.StudentProfile s2_aProfile = dept.getStudentDirectory().newStudentProfile(s2_aPerson);
        
        CourseLoad s2_fallLoad = s2_aProfile.newCourseLoad("Fall2025");
        SeatAssignment s2_sa = s2_fallLoad.newSeatAssignment(fall5100Offer);
        if (s2_sa != null) {
            s2_sa.setGrade("B+"); 
        }

        // Student 3: Charles Chen, Grade C+
        final String s3_nuid = "003000003";
        final String s3_uname = "chen";
        final String s3_passwd = "password";
        
        Person s3_bPerson = business.getPersonDirectory().newPerson(s3_nuid);
        s3_bPerson.setFirstName("Charles");
        s3_bPerson.setLastName("Chen");
        s3_bPerson.setEmail("charlie18922157212@qq.com");
        StudentProfile s3_bProfile = business.getStudentDirectory().newStudentProfile(s3_bPerson);
        business.getUserAccountDirectory().newUserAccount(s3_bProfile, s3_uname, s3_passwd);
        
        info5100.university.example.Persona.Person s3_aPerson = dept.getPersonDirectory().newPerson(s3_nuid);
        s3_aPerson.setFirstName("Charles");
        s3_aPerson.setLastName("Chen");
        info5100.university.example.Persona.StudentProfile s3_aProfile = dept.getStudentDirectory().newStudentProfile(s3_aPerson);
        
        CourseLoad s3_fallLoad = s3_aProfile.newCourseLoad("Fall2025");
        SeatAssignment s3_sa = s3_fallLoad.newSeatAssignment(fall5100Offer);
        if (s3_sa != null) {
            s3_sa.setGrade("C-"); 
        }
    }

    public void insert(JPanel jpanel) {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SplitHomeArea = new javax.swing.JSplitPane();
        actionsidejpanel = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        UserNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();
        PasswordTextField = new javax.swing.JPasswordField();
        CardSequencePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1050, 550));

        actionsidejpanel.setBackground(new java.awt.Color(0, 153, 153));
        actionsidejpanel.setMinimumSize(new java.awt.Dimension(200, 200));

        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogin.setPreferredSize(new java.awt.Dimension(70, 25));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("User Name");

        UserNameTextField.setText("admin");
        UserNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Password");

        btnSignUp.setText("Sign up");
        btnSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignUp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSignUp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSignUp.setPreferredSize(new java.awt.Dimension(70, 25));
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpLoginButtonActionPerformed(evt);
            }
        });

        PasswordTextField.setText("****");

        javax.swing.GroupLayout actionsidejpanelLayout = new javax.swing.GroupLayout(actionsidejpanel);
        actionsidejpanel.setLayout(actionsidejpanelLayout);
        actionsidejpanelLayout.setHorizontalGroup(
            actionsidejpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsidejpanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(actionsidejpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(UserNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(PasswordTextField)
                    .addGroup(actionsidejpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addComponent(btnSignUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        actionsidejpanelLayout.setVerticalGroup(
            actionsidejpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsidejpanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(UserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        SplitHomeArea.setLeftComponent(actionsidejpanel);

        CardSequencePanel.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Education Going Digital .... Info 5100 ");
        CardSequencePanel.add(jLabel3, "card2");

        SplitHomeArea.setRightComponent(CardSequencePanel);

        getContentPane().add(SplitHomeArea, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        String un = UserNameTextField.getText();
        String pw = new String(PasswordTextField.getPassword());

        UserAccountDirectory uad = business.getUserAccountDirectory();
        UserAccount useraccount = uad.AuthenticateUser(un, pw);
        if (useraccount == null) {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password.");
            return;
        }
        
        FacultyWorkAreaJPanel facultyworkarea;
        AdminRoleWorkAreaJPanel adminworkarea;
        Profile profile = useraccount.getAssociatedPersonProfile();

        if (profile instanceof EmployeeProfile) {
            adminworkarea = new AdminRoleWorkAreaJPanel(business, CardSequencePanel,useraccount);
            CardSequencePanel.removeAll();
            CardSequencePanel.add("Admin", adminworkarea);
            ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
        }
        
        if (profile instanceof StudentProfile) {
            String personId = useraccount.getPersonId();
            StudentWorkAreaJPanel studentPanel = new StudentWorkAreaJPanel(CardSequencePanel, dept, personId, HOME_CARD);
            CardSequencePanel.add(HOME_CARD, studentPanel);
            ((CardLayout) CardSequencePanel.getLayout()).show(CardSequencePanel, HOME_CARD);
        }

        if (profile instanceof FacultyProfile) {
            facultyworkarea = new FacultyWorkAreaJPanel(business, useraccount, CardSequencePanel);
            CardSequencePanel.removeAll();
            CardSequencePanel.add("faculty", facultyworkarea);
            ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void btnSignUpLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpLoginButtonActionPerformed
        SignUpJPanel p = new SignUpJPanel(CardSequencePanel, business, dept);
        CardSequencePanel.add(SIGNUP_CARD, p);                     
        CardLayout cl = (CardLayout) CardSequencePanel.getLayout();    cl.show(CardSequencePanel, SIGNUP_CARD);
    }//GEN-LAST:event_btnSignUpLoginButtonActionPerformed

    private void UserNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserNameTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProfileWorkAreaMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfileWorkAreaMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfileWorkAreaMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfileWorkAreaMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfileWorkAreaMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardSequencePanel;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JSplitPane SplitHomeArea;
    private javax.swing.JTextField UserNameTextField;
    private javax.swing.JPanel actionsidejpanel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
    public void clearLoginFields() {
        UserNameTextField.setText("");
        PasswordTextField.setText("");
        UserNameTextField.requestFocusInWindow();
    }
}