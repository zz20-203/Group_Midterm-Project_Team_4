\# Digital University System — Midterm Group Project 

\#\# Team Information  
\- \*\*Li Zhang\*\* (NUID: 003189807\) — Administrator Use Case    
\- \*\*Jerry Xu\*\* (NUID: 003155254\) — Faculty Use Case    
\- \*\*Shaowei Li\*\* (NUID: 002066350\) — Student Use Case  

\#\# Project Overview

This project integrates the “Digital University System” model with a **role-based access control (RBAC)** layer and a Java Swing UI using **CardLayout**. Users authenticate with a username/password and are routed to role-specific dashboards. Each role implements a complete workflow end-to-end (Admin, Faculty, Student).

\#\#\# Key Objectives  
\- Implement secure authentication and login validation  
\- Enforce role-based authorization to limit feature access appropriately  
\- Develop interface workflows for each role using Java Swing and CardLayout  
\- Maintain data consistency and implement effective error handling

\#\#\# Key Features  
\- Role-based login with per-role work areas   
\- Admin: manage different accounts/roles  
\- Faculty: manage courses, view student progress  
\- Student:  registration, coursework, transcript, graduation audit

\---

\#\# Installation & Setup Instructions

\#\#\# Prerequisites  
\- Java JDK 17 or higher  
\- IntelliJ IDEA / NetBeans / Eclipse  
\- No external libraries required (Java Swing is included by default)

\#\#\# Setup Steps  
1\. Clone the GitHub repository: https://github.com/zz20-203/Group\_Midterm\_Project\_Team\_4  
2\. Open the project in IDE.  
3\. Run the main entry file: \`Main.java\`

\#\# Authentication & Access Control

\#\#\# Authentication  
Users log in from the login screen. The system checks their username and password in the UserAccountDirectory. Once verified, the user is taken to the dashboard for their role.

\#\#\# Authorization  
Each user is assigned a role (Admin, Faculty, or Student). Their role determines which screens and features they are allowed to use.

| Role | Allowed Actions | Not Allowed |  
|------|----------------|-------------|  
| Administrator | Manage user accounts, register students/faculty, update student and faculty records, edit own profile | Cannot access student coursework or faculty tools |  
| Faculty | Manage courses, view student academic progress, edit own profile | Cannot modify user accounts or graduation tracking |  
| Student | Register or drop courses, submit and view assignments, view transcript and graduation progress | Cannot modify system-wide records or course data |

\---

\#\# Features Implemented

\#\#\# Administrator (Implemented by Li Zhang)  
\- Administer user accounts (create, modify, and delete accounts)  
\- Register persons (students, faculty, academic staff)  
\- Manage student records (update, delete, or view student data)  
\- Manage faculty records (update, delete, or view faculty data)  
\- Manage own profile

\#\#\# Faculty (Implemented by Jerry Xu)  
\- Manage courses (view, update course details)  
\- Manage own profile  
\- Generate performance reports (student grades in the class)  
\- Manage student profiles (view hobbies, interests, academic progress)

\#\#\# Student (Implemented by Shaowei Li)  
\- Registration: enroll/drop for terms (e.g., *Summer 2025, Fall2025, Spring2026*)  
\- Coursework: upload submissions, set progress, see last-submitted timestamps  
\- Transcript: term GPA snapshot and course list with credits/grades  
\- Graduation Audit: required vs. earned and in-progress credits, progress bar, per-term status  
\- My Profile: view basic profile (NUID, first/last name, program)

\---

\#\# Usage Instructions

\#\#\# How to Log In  
1\. Launch the application.  
2\. Log in as **Admin** or Faculty or **Student** (use seeded credentials above).  
	Or click **Sign up** to create a student account (NUID \= personId)  
3\. After logging in, you will be taken to the dashboard for your assigned role.

\#\#\# Example Workflows  
\- \*\*Admin\*\* → Manage User Accounts  
\- \*\*Faculty\*\* → View Courses   
\- \*\*Student\*\* → Register Courses

\#\#\# Complete Workflows  
\- \*\***Admin Workflows**\*\*

* **Administer user accounts** → create, modify, and delete accounts)  
* **Register persons** → students, faculty, academic staff)  
* **Manage student records** → update, delete, or view student data)  
* **Manage faculty records** → (update, delete, or view faculty data)  
* **Manage own profile**

\- \*\***Faculty Workflows**\*\*

* **Manage courses** (view, update course details)  
* **Manage own profile**  
* **Generate performance reports** (student grades in the class)  
* **Manage student profiles** (view hobbies, interests, academic progress)

\- \*\***Student Workflows\*\***

* **Registration**  
  * Pick a semester → select an offering → **Enroll Course**  
  * To drop: select from *My Enrollments* → **Drop Course**  
* **Coursework**  
  * Select a term and course → **Submit** (choose file)  
  * Adjust the **Set Progress** spinner and apply  
  * “Last Submitted” and progress update instantly  
* G**raduation Audit**  
  * Shows **Required**, **Earned**, and **Credits in progress**  
  * Progress bar reflects *earned \+ in-progress* (capped at required)  
  * Per-semester status: **Completed** (all graded) or **In progress** (any ungraded)  
* **Transcript**  
  * Pick a semester to view courses, credits, grades and term GPA  
  * Ungraded courses appear with **“–”** (pending)

—

\#\# Testing Guide

\#\#\# Log in using one of the sample accounts below:  
| Role     | Username | Password |  
|----------|----------|----------|  
| Admin    | admin    | \*\*\*\*     |  
| Faculty  | alice  | \*\*\*\*     |  
| Student  | shaoweili     | 123456     |

| Test Case | Expected Behavior |  
|----------|------------------|  
| Login with an incorrect password | System shows an error and denies access |  
| Faculty attempts to open Admin panel | Access denied |  
| Admin deletes a student account | The student account is removed from the user list |  
| Faculty views a course | The course details appear in the course overview |  
| Student drops a registered course | The course is removed from the student's schedule |

\#\#\# Student – Quick Test Checklist  
1\. Login (success): Use \`shaoweili / 123456\` or sign up a new student → lands on Student menu.  
2\. Login (failure): Wrong password → error dialog; remains on login.  
3\. Role guard: From student menu, Admin/Faculty panels are not accessible.  
4\. Enroll: Registration → Fall2025 → select a course → Enroll → appears in My Enrollments; Seats Left −1.  
5\. Drop: Select enrolled course → Drop → removed from My Enrollments; Seats Left \+1.  
6\. Coursework – submit: Coursework → select course → Submit any file → “Last Submitted” shows timestamp \+ filename.  
7\. Coursework – progress: Set progress to 40 → Set Progress → row shows 40\.  
8\. Transcript & Audit:  
   \- Transcript (current term): in-progress courses show grade "–" (pending); GPA ignores them.  
   \- Graduation Audit: Earned \= graded credits (≥ C). Credits in progress \= current ungraded. 

\---

\#\# Challenges & Solutions

\#\#\# Challenges  
\- Ensuring accurate role-based access control in two models  
\- Understanding and managing the system architecture and object relationships  
\- UI consistency: centralize navigation with CardLayout; confirm dialogs for enroll/drop; clear error messages  
\- Collaborating effectively on GitHub among three contributors

\#\#\# Solutions  
\- Performed testing based on user scenarios  
\- Reviewed and analyzed the codebase along with UML diagrams  
\- Conducted team debugging sessions to resolve issues

\---

\#\# Future Enhancements  
\- Expand the system with additional university use cases and functionality  
\- Add options for exporting or downloading information (e.g. transcript/audit as PDF)  
\- Improve UI styling and overall user experience and accessibility passes  
\- Persistence layer (files/DB) beyond in-memory runtime data

\---

\#\# Contribution Breakdown  
\- Li Zhang: Coding, documentation, testing for Administrator Use Case  
\- Jerry Xu: Coding, documentation, testing for Faculty Use Case  
\- Shaowei Li: Coding, documentation, testing for Student Use Case  
\- All members contributed to integration, debugging, and presentation