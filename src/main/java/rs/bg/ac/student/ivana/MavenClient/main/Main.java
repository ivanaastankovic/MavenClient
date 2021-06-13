package rs.bg.ac.student.ivana.MavenClient.main;

import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;

public class Main {
    public static void main(String[] args) {
        Cordinator.getInstance().openLoginForm();
    }
}
