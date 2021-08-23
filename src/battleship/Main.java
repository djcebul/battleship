package battleship;

import java.util.Arrays;
import java.util.Scanner;

import static battleship.Statek.error;
import static battleship.Statek.errorCouse;

public class Main {
    static String[][] grid = new String[11][11];

    static Statek[] statek = new Statek[5];

    public static void main(String[] args) {
        rysujPlansze(0);
        var scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        do {
            error(0);
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[0] = new Statek(5, x, y, "Aircraft Carrier");
            dodajStatek(0);
            rysujPlansze(1);

        }
        while (error);
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        do {
            error(1);
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[1] = new Statek(4, x, y, "Battleship");
            dodajStatek(1);
            rysujPlansze(1);
        }
        while (error);
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        do {
            error(2);
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[2] = new Statek(3, x, y, "Submarine");
            dodajStatek(2);
            rysujPlansze(1);
        }
        while (error);
        System.out.println("Enter the coordinates of the Cruiser (3 cells)");
        do {
            error(3);
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[3] = new Statek(3, x, y, "Cruiser");
            dodajStatek(3);
            rysujPlansze(1);
        }
        while (error);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        do {
            error(4);
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[4] = new Statek(2, x, y, "Destroyer");
            dodajStatek(4);
            rysujPlansze(1);
        }
        while (error);
    }

    /**
     * Obsluga bledow
     */
    static void error(int nrStatku) {
       if (error) {
           switch (errorCouse) {
               case "wrongLocation":
                   System.out.println("Error! Wrong ship location! Try again:");
                   error = false;
                   break;
               case "wrongLength":
                   System.out.println("Error! Wrong length of the " + statek[nrStatku].nazwa + "! Try again:");
                   error = false;
                   break;

           }
       }
    }

    /**
        Rysuje plansze
        @param rodzajPlanszy  0 - pierwsze rysowanie planszy, przy rozpoczeciu gry
                              1 - drukuje aktualny stan pola bitwy z tablicy grid
     */
    static void rysujPlansze(int rodzajPlanszy) {
        if (rodzajPlanszy == 0) {


            grid[0][0] = "   ";
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    grid[i][j] = "~ ";
                }
                grid[0][i] = String.valueOf(i) + " ";
                grid[i][0] = " " + new String(Character.toChars(i + 64)) + " ";
            }
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }
        }
        if (rodzajPlanszy == 1 && !error) {

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }
        }
    }

    /**
     * Dodaje statek do tablicy grid
     * @param nrStatku 0 - 5 masztowiec
     *                 1 - 4 masztowiec
     *                 2 - 3 masztowiec
     *                 3 - 3 masztowiec
     *                 4 - 2 masztowiec
     */


    static void dodajStatek(int nrStatku) {
        if (!error) {
            String ship;
            int l = 0;

            do {
                for (int i = 1; i < 11; i++) {

                    for (int j = 1; j < 11; j++) {
                        ship = String.valueOf(i) + String.valueOf(j);

                        if (statek[nrStatku].rysunek[l].equals(ship)) {
                            grid[i][j] = "O ";
                        }
                    }
                }
                l++;
            } while (l < 5);
        }
    }
}

