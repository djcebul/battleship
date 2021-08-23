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
            error();
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[0] = new Statek(5, x, y);
            dodajStatek(0);
            rysujPlansze(1);

        }
        while (error);
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        do {
            error();
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[1] = new Statek(4, x, y);
            dodajStatek(1);
            rysujPlansze(1);
        }
        while (error);
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        do {
            error();
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[2] = new Statek(3, x, y);
            dodajStatek(2);
            rysujPlansze(1);
        }
        while (error);
        System.out.println("Enter the coordinates of the Cruiser (3 cells)");
        do {
            error();
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[3] = new Statek(3, x, y);
            dodajStatek(3);
            rysujPlansze(1);
        }
        while (error);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        do {
            error();
            String x, y;
            x = scanner.next();
            y = scanner.next();
            statek[4] = new Statek(2, x, y);
            dodajStatek(4);
            rysujPlansze(1);
        }
        while (error);


        //System.out.println(Arrays.toString(cruiser.rysunek));
       // if (!error)plansza();


//        System.out.println(String.valueOf(cruiser.maszt1x) + String.valueOf(cruiser.maszt1y));
//        System.out.println(String.valueOf(cruiser.maszt2x) + String.valueOf(cruiser.maszt2y));





    }
    static void error() {
       if (error) {
           switch (errorCouse) {
               case "wrongLocation":
                   System.out.println("Error! Wrong ship location! Try again:");
                   error = false;
           }
       }
    }

    /**
        Rysuje pusta plansze na starcie
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


    static void dodajStatek(int nrStatku) {
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

