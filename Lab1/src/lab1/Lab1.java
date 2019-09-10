/*
 * Lab 1 Geometrical shapes
 */
package lab1;

import java.util.Scanner;

/**
 *
 * @author Daniel Lindkvist
 */
public class Lab1 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = "";
        Shape sh = null;
        do {
            System.out.println("Välj en figur.\n1 för cirkel\n2 för rektangel\n3 för triangel");
            System.out.println("Skriv exit för att avsluta.");
            input = s.nextLine().toLowerCase();

            switch (input) {
                case "1":
                    sh = makeCirkel();
                    break;
                case "2":
                    sh = makeRektangel();
                    break;
                case "3":
                    sh = makeTriangel();
                    break;
                case "exit":
                    System.out.println("Programmet avslutas.");
                    System.exit(0);
                default:
                    System.out.println("Felaktig inmatning. Använd 1, 2, 3 eller exit.");
                    continue;
            }

            System.out.println("Ange A för area, O för omkrets eller B för båda.");
            input = s.nextLine().toLowerCase();

            switch (input) {
                case "a":
                    System.out.println("Arean på denna " + sh.toString() + " = " + sh.area() + "\n");
                    break;
                case "o":
                    System.out.println("Omkretsen på denna " + sh.toString() + " = " + sh.omkrets() + "\n");
                    break;
                case "b": {
                    System.out.println("Arean på denna " + sh.toString() + " = " + sh.area());
                    System.out.println("Omkretsen på denna " + sh.toString() + " = " + sh.omkrets() + "\n");
                    break;
                }
                default:
                    break;
            }

        } while (true);
    }

    public static Cirkel makeCirkel() {
        System.out.println("Ange radie för att skapa en cirkel: ");
        Scanner s = new Scanner(System.in);
        int radie = s.nextInt();
        Cirkel c = new Cirkel(radie);
        return c;
    }

    public static Rektangel makeRektangel() {
        System.out.print("Ange längd och bredd för att skapa en rektangel:\nLängd: ");
        Scanner s = new Scanner(System.in);
        int length = s.nextInt();
        System.out.print("Bredd: ");
        int width = s.nextInt();
        Rektangel r = new Rektangel(width, length);
        return r;
    }

    public static Triangel makeTriangel() {
        Scanner s = new Scanner(System.in);
        System.out.println("Ange katet + katet för att skapa en triangel.");
        System.out.print("Katet 1: ");
        int k1 = s.nextInt();
        System.out.print("Katet 2: ");
        int k2 = s.nextInt();
        Triangel t = new Triangel(k1, k2);
        return t;

    }

}
