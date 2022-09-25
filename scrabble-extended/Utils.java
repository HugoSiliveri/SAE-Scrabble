import java.util.*;
import java.lang.*;

public class Utils {

    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String PINK = "\u001B[38;5;206m";
    public static final String LIGHT_BLUE = "\u001B[38;5;195m";
    public static final String DARK_BLUE = "\u001B[38;5;19m";

    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    public static final String PINK_BG = "\u001B[48;5;206m";
    public static final String LIGHT_BLUE_BG = "\u001B[48;5;195m";
    public static final String DARK_BLUE_BG = "\u001B[48;5;19m";

    public static boolean estUneMajuscule(char c) {
        return Character.isUpperCase(c);
    }

    public static int majToIndex(char c) {
        return (int) c - 65;
    }

    public static char indexToMaj(int i) {
        return (char) (i + 65);
    }

    public static char inputChar() {
        Scanner clavier = new Scanner(System.in);
        char lu = clavier.nextLine().charAt(0);
        // clavier.close();
        return lu;
    }

    public static String inputString() {
        Scanner clavier = new Scanner(System.in);
        String lu = clavier.nextLine();
        // clavier.close();
        return lu;
    }

    public static int inputInt() {
        Scanner clavier = new Scanner(System.in);
        int lu = clavier.nextInt();
        return lu;
    }

    public static void sleep(int timeMilli) {
        // Action : suspend le processus courant pendant timeMilli millisecondes
        // Procédure reprise de Ut.java
        try {
            Thread.sleep(timeMilli);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void clear () {
        // Action : efface la console (le terminal)
        // Procédure reprise de Ut.java
        System.out.print("\033[H\033[2J");
    }
}