package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Daniel Lindkvist
 */
public class BetterScanner {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    
    
    public String nextLine() {
        return SCANNER.nextLine();
    }
    
    public String next() {
        return SCANNER.next();
    }
    
    public int readInt(String question) {
        Integer returnvalue = null;
        while (returnvalue == null) {
            System.out.print(question);
            try {
                returnvalue = SCANNER.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Only numbers allowed. Please try again.");
            } finally {
                SCANNER.nextLine();
            }
        }
        return returnvalue;
    }
    
    public double readDouble(String question) {
        Double returnvalue = null;
        while (returnvalue == null) {
            System.out.print(question);
            try {
                returnvalue = SCANNER.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Only numbers allowed. Please try again.");
            } finally {
                SCANNER.nextLine();
            }
        }
        return returnvalue;
    }
    
    
    public boolean readBoolean(String question) {
        Boolean returnvalue = null;
        while (returnvalue == null) {
            System.out.print(question);
            try {
                returnvalue = SCANNER.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Only true or false is allowed. Please try again.");
            } finally {
                SCANNER.nextLine();
            }
        }
        return returnvalue;
    }
   
}
