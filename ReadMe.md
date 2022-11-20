# EXAMS

## Project Goal

Create an application for the teachers which can create an event of an exam stored localy on the device.

---

## Functionalities

Firstly, it is important to note that you do not need an account to use our application.

There are two main features about our application. The first one consist to add, delete and change informations about a student in their dedicated list.
The student requires a name and surname and the number of his class in order to be created. The students list is ordered by their class name then by their last name.

The second feature consist to create, delete and modify an exam.
For the exam, you will need to know the room where it will take place, the date, the subject and which students will take part.

---

## Database

We used five different tables for our database. As you could have guessed, there is the Student and the Exam. However, to avoid some repetition, we also used tables for the Room and Subject which are linked to the Exam table.
The Exam and Student have a many-to-many relationship, therefore we created an intermediate table, named ExamsStudents, to link both of their primary key. Thus, deleting an exam affect the Exams table, and also the corresponding entries in the ExamsStudents table. This also applies when we delete a student affected to an exam.

---

## Details

We provided inside the app a section called "A propos" which can give further informations about the project, and provides a link on the GitHub page of this application.