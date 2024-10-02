package cm.main;

import cm.data.StudentDAO;
import cm.student.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Presentation {

    public static Scanner console = new Scanner(System.in);
    public static int optionSelected;

    public static void main(String[] args) {

        System.out.println("\n¡Welcome to the Student System App!\n");
        integrateLoop();

    }

    public static void integrateLoop() {
        do {
            try {
                System.out.println("***Student System App***");
                showMenu();
                optionSelected = console.nextInt();
                if (optionSelected >= 1 && optionSelected < 7) {
                    System.out.println("You have selected the option: " + optionSelected + "\n");
                    manageAction(optionSelected);
                } else {
                    System.out.println("Invalid option. Please select a number between 1 and 6.");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("An unexpected error occurred: Invalid input, cannot be a character/string. Please try again. \n");
                console.next();
            }
        } while (true);

    }

    public static void showMenu() {
        System.out.println(
                "\n1.List students \n" +
                        "2.Search students \n" +
                        "3.Insert student \n" +
                        "4.Modify student \n" +
                        "5.Delete student \n" +
                        "6.Exit \n" +
                        "¿What you want to do?: \n");
    }

    public static void manageAction(int optionSelected) {
        switch (optionSelected) {
            case 1:
                listStudent();
                break;
            case 2:
                searchId();
                break;
            case 3:
                addStudent();
                break;
            case 4:
                modifyStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                System.out.println("You have made a successful exit, we look forward to seeing you again.");
                System.exit(0);
                break;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void addStudent() {

        try {
            console.nextLine();
            System.out.println("Enter the student's name: ");
            String name = console.nextLine();
            if (isNumeric(name)) {
                throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
            }
            System.out.println("Enter the student's surname: ");
            String surname = console.nextLine();
            if (isNumeric(surname)) {
                throw new IllegalArgumentException("Invalid input: Surname cannot be a number. Please, try again. \n");
            }
            System.out.println("Enter the student's phone number: ");
            int phoneNumber = console.nextInt();
            console.nextLine(); //--> Para limpiar el salto de linea residual y no lo lea el buffer al querer leer el email
            System.out.println("Enter the student's email: ");
            String email = console.nextLine();
            if (isNumeric(email)) {
                throw new IllegalArgumentException("Invalid input: Email cannot be a number. Please, try again. \n");
            }

            Student student = new Student(name, surname, phoneNumber, email);
            boolean newStudent = StudentDAO.insertStudent(student);
            if (newStudent) {
                System.out.println("Student added: " + student);
                System.out.println();

                System.out.println("¿Do you want to enter another student in the database? \n" +
                        "1.Yes \n" +
                        "2.No. Back to Menu. \n");

                int option = console.nextInt();
                if (option == 1) {
                    addStudent();
                } else {
                    integrateLoop();
                }

            } else {
                System.out.println("The student was not added: " + student);
                System.out.println();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void listStudent() {
        System.out.println("Listado de estudiantes: ");
        StudentDAO.listStudent().forEach(System.out::println);
        System.out.println();
    }

    public static void searchId() {
        System.out.println("Enter the id of the student you want to search for: ");
        int idStudent = console.nextInt();
        Student student = new Student(idStudent);
        boolean newStudent = StudentDAO.findById(student);
        if (newStudent) {
            System.out.println("Student found: " + student);
            System.out.println();
        } else {
            System.out.println("Student not found");
            System.out.println();
        }
    }

    public static void modifyStudent() {
        try {
            System.out.println("Enter the id of the student you want to modify: ");

            int idStudent = console.nextInt();
            console.nextLine();
            System.out.println("Please enter your new name: ");
            String name = console.nextLine();
            if (isNumeric(name)) {
                throw new IllegalArgumentException("Invalid input: Name cannot be a number. Please, try again. \n");
            }
            System.out.println("Plase enter your new surname: ");
            String surname = console.nextLine();
            if (isNumeric(surname)) {
                throw new IllegalArgumentException("Invalid input: Surname cannot be a number. Please, try again. \n");
            }
            System.out.println("Plase enter your new phone number: ");
            int phoneNumber = console.nextInt();
            console.nextLine();
            System.out.println("Plase enter your new email: ");
            String email = console.nextLine();
            if (isNumeric(email)) {
                throw new IllegalArgumentException("Invalid input: Email cannot be a number. Please, try again. \n");
            }
            Student student = new Student(idStudent, name, surname, phoneNumber, email);
            boolean newStudent = StudentDAO.modifyStudent(student);
            if (newStudent) {
                System.out.println("Modified student: " + student);
                System.out.println();

                System.out.println("¿You want to modify another student in the database? \n" +
                        "1.Yes \n" +
                        "2.No. Back to Menu .\n");

                int option = console.nextInt();
                if (option == 1) {
                    modifyStudent();
                } else {
                    integrateLoop();
                }

            } else {
                System.out.println("The student was not modified: " + student);
                System.out.println();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void deleteStudent() {
        System.out.println("Enter the id of the student you want to delete: ");
        int idStudent = console.nextInt();
        Student student = new Student(idStudent);
        boolean studenDelete = StudentDAO.deleteStudent(student);
        if (studenDelete) {
            System.out.println("The student has been removed " + student);
            System.out.println();

            System.out.println("¿You want to remove another student in the database? \n" +
                    "1.Yes \n" +
                    "2.No. Back to Menu .\n");

            int option = console.nextInt();
            if (option == 1) {
                deleteStudent();
            } else {
                integrateLoop();
            }

        } else {
            System.out.println("The student was not removed " + student);
            System.out.println();
        }
    }
}
