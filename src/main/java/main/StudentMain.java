package main;

import controller.StudentController;
import viewer.StudentViewer;

public class StudentMain {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        StudentViewer studentViewer = new StudentViewer(studentController);

        studentViewer.showMenu();
    }
}
