/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.Person.Person;
import Business.UserAccounts.UserAccount;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Department.Department;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Jerry Xu
 */
public class ViewStudentProfileJPanel extends javax.swing.JPanel {

    private JPanel CardSequencePanel;
    private Business business;
    private UserAccount studentUserAccount;
    
    // Constants adapted from GraduationAuditJPanel for calculations
    private static final int REQUIRED_CREDITS_DEFAULT = 32;
    private static final String[] KNOWN_SEMESTERS = {"Summer2025","Fall2025","Spring2026"};
    private static final double PASS_MIN_POINTS = 2.0;

    /**
     * Creates new form ViewStudentProfileJPanel
     */
    public ViewStudentProfileJPanel(JPanel clp, Business b, UserAccount ua) {
        initComponents();
        this.CardSequencePanel = clp;
        this.business = b;
        this.studentUserAccount = ua;
        
        populateAllFields();
    }
    
    private void populateAllFields() {
        // 1. Populate basic info from the Business-level Person object
        Person person = studentUserAccount.getAssociatedPersonProfile().getPerson();
        txtFirstName.setText(person.getFirstName());
        txtLastName.setText(person.getLastName());
        txtPID.setText(person.getPersonId());
        txtEmail.setText(studentUserAccount.getUserLoginName());
        txtHobbiesInterests.setText("N/A"); // This field doesn't exist in the data model
        
        // 2. Find the corresponding academic profile in the department
        Department dept = business.getModelDepartment(); // Assuming one main department for now
        info5100.university.example.Persona.StudentProfile academicProfile = 
                dept.getStudentDirectory().findStudent(person.getPersonId());
        
        // 3. Calculate and populate academic info if the profile exists
        if (academicProfile != null) {
            calculateAndPopulateAcademicInfo(academicProfile);
        } else {
            // Handle case where student might not have an academic record yet
            txtCredits.setText("0");
            txtCreditsTotal.setText(String.valueOf(REQUIRED_CREDITS_DEFAULT));
            txtGPA.setText("N/A");
        }
    }
    
    private void calculateAndPopulateAcademicInfo(info5100.university.example.Persona.StudentProfile sp) {
        // This logic is adapted from GraduationAuditJPanel.java
        int earned = 0;
        double gpaSum = 0.0;
        int gradedCredits = 0;

        for (String sem : KNOWN_SEMESTERS) {
            CourseLoad cl = sp.getCourseLoadBySemester(sem);
            if (cl == null) continue;

            for (SeatAssignment sa : cl.getSeatAssignments()) {
                CourseOffer co = sa.getCourseOffer();
                info5100.university.example.CourseCatalog.Course course =
                        (co == null) ? null : co.getSubjectCourse();

                int cr = (course == null) ? 0 : course.getCredits();
                String letter = safeGrade(sa);

                if (letter != null && !letter.isBlank() && !"-".equals(letter.trim())) {
                    double pts = letterToPoints(letter);
                    if (pts >= PASS_MIN_POINTS) earned += cr;
                    if (pts >= 0.0) {
                        gpaSum += pts * cr;
                        gradedCredits += cr;
                    }
                }
            }
        }
        
        txtCredits.setText(String.valueOf(earned));
        txtCreditsTotal.setText(String.valueOf(REQUIRED_CREDITS_DEFAULT));
        
        String gpaStr = (gradedCredits == 0) ? "N/A" : String.format("%.2f", gpaSum / gradedCredits);
        txtGPA.setText(gpaStr);
    }
    
    // Helper methods adapted from GraduationAuditJPanel
    private String safeGrade(SeatAssignment sa) {
        try {
            Object v = sa.getClass().getMethod("getGrade").invoke(sa);
            return v == null ? null : v.toString();
        } catch (Exception ignored) { }
        return null;
    }
    
    private double letterToPoints(String letter) {
        switch (letter) {
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            default:   return -1.0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStudentMyProfile = new javax.swing.JLabel();
        lblPID = new javax.swing.JLabel();
        txtPID = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtHobbiesInterests = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        lblHobbiesInterests = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtCredits = new javax.swing.JTextField();
        lblFirstName = new javax.swing.JLabel();
        lblCredits = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblGPA = new javax.swing.JLabel();
        txtCreditsTotal = new javax.swing.JTextField();
        lblSlash = new javax.swing.JLabel();
        txtGPA = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(640, 480));

        lblStudentMyProfile.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblStudentMyProfile.setText("Student Profile");

        lblPID.setText("Personal ID");

        txtPID.setEditable(false);
        txtPID.setBackground(new java.awt.Color(204, 204, 204));

        lblEmail.setText("Email");

        txtEmail.setEditable(false);
        txtEmail.setBackground(new java.awt.Color(204, 204, 204));

        txtHobbiesInterests.setEditable(false);
        txtHobbiesInterests.setBackground(new java.awt.Color(204, 204, 204));

        lblLastName.setText("Last Name");

        lblHobbiesInterests.setText("Hobbies & Interests");

        txtLastName.setEditable(false);
        txtLastName.setBackground(new java.awt.Color(204, 204, 204));

        txtCredits.setEditable(false);
        txtCredits.setBackground(new java.awt.Color(204, 204, 204));

        lblFirstName.setText("First Name");

        lblCredits.setText("Earned Credits");

        txtFirstName.setEditable(false);
        txtFirstName.setBackground(new java.awt.Color(204, 204, 204));

        lblGPA.setText("GPA");

        txtCreditsTotal.setEditable(false);
        txtCreditsTotal.setBackground(new java.awt.Color(204, 204, 204));

        lblSlash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSlash.setText("/");
        lblSlash.setToolTipText("");

        txtGPA.setEditable(false);
        txtGPA.setBackground(new java.awt.Color(204, 204, 204));

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addGap(43, 43, 43)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblLastName)
                                        .addComponent(lblFirstName))
                                    .addComponent(lblPID, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPID, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblGPA)
                                    .addComponent(lblCredits))
                                .addGap(234, 234, 234))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHobbiesInterests)
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGPA)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblSlash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCreditsTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtHobbiesInterests, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(lblStudentMyProfile)))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblStudentMyProfile)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPID)
                    .addComponent(txtPID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHobbiesInterests)
                    .addComponent(txtHobbiesInterests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCredits)
                    .addComponent(txtCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditsTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSlash))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGPA)
                    .addComponent(txtGPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnBack)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CardSequencePanel.remove(this);
        ((CardLayout) CardSequencePanel.getLayout()).previous(CardSequencePanel);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblCredits;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGPA;
    private javax.swing.JLabel lblHobbiesInterests;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblPID;
    private javax.swing.JLabel lblSlash;
    private javax.swing.JLabel lblStudentMyProfile;
    private javax.swing.JTextField txtCredits;
    private javax.swing.JTextField txtCreditsTotal;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGPA;
    private javax.swing.JTextField txtHobbiesInterests;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPID;
    // End of variables declaration//GEN-END:variables
}