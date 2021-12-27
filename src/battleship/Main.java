package battleship;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

import static battleship.Statek.error;
import static battleship.Statek.errorCouse;

public class Main {
    static String[][] grid = new String[11][11];
    static String[][] gridFog = new String[11][11];
    static String[][] grid2 = new String[11][11];
    static String[][] gridFog2 = new String[11][11];

    static Statek[] statek = new Statek[5];
    static Statek[] statek2 = new Statek[5];
    static int a,b;
    static boolean endOfTheGame = false;
    static int player = 1;


    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String shot;

        gameBegin(1);
        gameBegin(2);


//        rysujPlansze(2);
//        System.out.println("---------------------");
//        rysujPlansze(1);



        while (!endOfTheGame){
            System.out.println("Player " + player + ", it's your turn:\n");

            if (player == 1) {
                rysujPlansze(3);
                System.out.println("---------------------");
                rysujPlansze(1);
                player = 2;}
            else {
                rysujPlansze(6);
                System.out.println("---------------------");
                rysujPlansze(5);
                player = 1;
            }
            shot = scanner.next();

            a = (int) shot.charAt(0) - 64;
            if (shot.length() < 3) {
                a = (int) shot.charAt(0) - 64;
                b = (int) shot.charAt(1) - 48;
            } else {
                String temp = String.valueOf(shot.charAt(1) + shot.charAt(2));
                b = Integer.parseInt(temp) == 97 ? 10 : 11;
                a = (int) shot.charAt(0) - 64;
            }

            if (a > 10 || b > 10 || a < 1 || b <1){
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            shot(player);




        }
    }

    static void shot(int player) {
        //te zmienne wyznaczaja kwadrat wokol wspolrzednych strzalu co pozwala stwierdzic,
        //czy cały statek został zatopiony
        int a1, b1, a2, b2;
        a1 = a == 1 ? a : a - 1;
        a2 = a == 10 ? a : a + 1;
        b1 = b == 1 ? b : b - 1;
        b2 = b == 10 ? b : b + 1;

        boolean shipAround = false;
        boolean koniecGry = true;
//        System.out.println("shipAround = " + shipAround);
//        System.out.println("ab = " + a +", " + b);
        if (player == 1) {

            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    if (grid[i][j].equals("O ") && (i != a || j != b)) {
                        koniecGry = false;
                        break;
                    }
                }
            }

            for (int j = a1; j <= a2; j++) {
                for (int i = b1; i <= b2; i++) {
                    if (grid[j][i].equals("O ") && (j != a || i != b)) {
                        shipAround = true;
                        // System.out.println(j + " -j+i- " + i);
                        break;
                    }

                }
            }
            if (grid[a][b].equals("~ ")) {
                grid[a][b] = "M ";
                gridFog2[a][b] = "M ";
//                rysujPlansze(2);
//                System.out.println("---------------------");
//                rysujPlansze(1);
                System.out.println("You missed!");
            } else if (koniecGry) {
                grid[a][b] = "X ";
                gridFog2[a][b] = "X ";
                rysujPlansze(1);
                endOfTheGame = true;
                System.out.println("You sank the last ship. You won. Congratulations!");
            } else if (grid[a][b].equals("O ") && shipAround) {
                grid[a][b] = "X ";
                gridFog2[a][b] = "X ";
//                rysujPlansze(2);
//                System.out.println("---------------------");
//                rysujPlansze(1);
                System.out.println("You hit a ship!");
            } else if (grid[a][b].equals("O ") && !shipAround) {
                grid[a][b] = "X ";
                gridFog2[a][b] = "X ";
//                rysujPlansze(2);
//                System.out.println("---------------------");
//                rysujPlansze(1);
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                rysujPlansze(1);
            }
            System.out.println("Press Enter and pass the move to another player");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            rysujPlansze(2);
//            System.out.println("---------------------");
//            rysujPlansze(5);
        }
        if (player == 2) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    if (grid2[i][j].equals("O ") && (i != a || j != b)) {
                        koniecGry = false;
                        break;
                    }
                }
            }

            for (int j = a1; j <= a2; j++) {
                for (int i = b1; i <= b2; i++) {
                    if (grid2[j][i].equals("O ") && (j != a || i != b)) {
                        shipAround = true;
                        // System.out.println(j + " -j+i- " + i);
                        break;
                    }

                }
            }
            if (grid2[a][b].equals("~ ")) {
                grid2[a][b] = "M ";
                gridFog[a][b] = "M ";
//                rysujPlansze(2);
//                System.out.println("---------------------");
//                rysujPlansze(5);
                System.out.println("You missed!");
            } else if (koniecGry) {
                grid2[a][b] = "X ";
                gridFog[a][b] = "X ";
                rysujPlansze(5);
                endOfTheGame = true;
                System.out.println("You sank the last ship. You won. Congratulations!");
            } else if (grid2[a][b].equals("O ") && shipAround) {
                grid2[a][b] = "X ";
                gridFog[a][b] = "X ";
//                rysujPlansze(2);
//                System.out.println("---------------------");
//                rysujPlansze(5);
                System.out.println("You hit a ship!");
            } else if (grid2[a][b].equals("O ") && !shipAround) {
                grid2[a][b] = "X ";
                gridFog[a][b] = "X ";
//                rysujPlansze(2);
//                System.out.println("---------------------");
//                rysujPlansze(5);
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                rysujPlansze(5);
            }
            System.out.println("Press Enter and pass the move to another player");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



//        System.out.println("koniecGry = " + koniecGry);
//        System.out.println(a1 + ", " + a2 + ", " + b1 + ", " + b2);
//        //rysujPlansze(1);
//        System.out.println("shipAround = " + shipAround);

    }

    static void gameBegin(int player) {
        var scanner = new Scanner(System.in);
        if (player == 1) {
            System.out.println("Player 1, place your ships on the game field\n");

            rysujPlansze(0);
            System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):");
            do {
                error(0);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek[0] = new Statek(5, x, y, "Aircraft Carrier");
                dodajStatek(0, 1);
                if (!error) rysujPlansze(1);

            }
            while (error);
            System.out.println("Enter the coordinates of the Battleship (4 cells):");
            do {
                error(1);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek[1] = new Statek(4, x, y, "Battleship");
                dodajStatek(1, 1);
                if (!error) rysujPlansze(1);
            }
            while (error);
            System.out.println("Enter the coordinates of the Submarine (3 cells):");
            do {
                error(2);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek[2] = new Statek(3, x, y, "Submarine");
                dodajStatek(2, 1);
                if (!error) rysujPlansze(1);
            }
            while (error);
            System.out.println("Enter the coordinates of the Cruiser (3 cells)");
            do {
                error(3);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek[3] = new Statek(3, x, y, "Cruiser");
                dodajStatek(3, 1);
                if (!error) rysujPlansze(1);
            }
            while (error);
            System.out.println("Enter the coordinates of the Destroyer (2 cells):");
            do {
                error(4);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek[4] = new Statek(2, x, y, "Destroyer");
                dodajStatek(4, 1);
                if (!error) rysujPlansze(1);
            }
            while (error);
            System.out.println("Press Enter and pass the move to another player");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (player == 2) {

            do {
                System.out.println("Player 2, place your ships to the game field\n");
                rysujPlansze(4);
                System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
                error(0);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek2[0] = new Statek(5, x, y, "Aircraft Carrier");
                dodajStatek(0, 2);
                if (!error) rysujPlansze(5);

            }
            while (error);
            System.out.println("Enter the coordinates of the Battleship (4 cells):");
            do {
                error(1);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek2[1] = new Statek(4, x, y, "Battleship");
                dodajStatek(1, 2);
                if (!error) rysujPlansze(5);
            }
            while (error);
            System.out.println("Enter the coordinates of the Submarine (3 cells):");
            do {
                error(2);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek2[2] = new Statek(3, x, y, "Submarine");
                dodajStatek(2, 2);
                if (!error) rysujPlansze(5);
            }
            while (error);
            System.out.println("Enter the coordinates of the Cruiser (3 cells)");
            do {
                error(3);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek2[3] = new Statek(3, x, y, "Cruiser");
                dodajStatek(3, 2);
                if (!error) rysujPlansze(5);
            }
            while (error);
            System.out.println("Enter the coordinates of the Destroyer (2 cells):");
            do {
                error(4);
                String x, y;
                x = scanner.next();
                y = scanner.next();
                statek2[4] = new Statek(2, x, y, "Destroyer");
                dodajStatek(4, 2);
                if (!error) rysujPlansze(5);
            }
            while (error);
            System.out.println("Press Enter and pass the move to another player");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
                case "toClose":
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    error = false;
                    break;

            }
        }
    }

    /**
     Rysuje plansze
     @param rodzajPlanszy  0 - pierwsze rysowanie planszy, przy rozpoczeciu gry
     1 - drukuje aktualny stan pola bitwy z tablicy grid
     2 - rysuje plansze zawsze pusta
     3 - rysuje plansze wraz ze strzalami gracza
     4 - pierwsze ryusowanie planszy drugiego gracza
     5 - drukuje aktualny stan pola drugiego gracza
     6 - rysuje plansze wraz ze strzalami drugiego gracza
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
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    (gridFog[i][j]) = grid[i][j];
                }

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
        if (rodzajPlanszy == 2){
            gridFog[0][0] = "   ";
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    gridFog[i][j] = "~ ";
                }
                gridFog[0][i] = String.valueOf(i) + " ";
                gridFog[i][0] = " " + new String(Character.toChars(i + 64)) + " ";
            }
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(gridFog[i][j]);
                }
                System.out.println();
            }
        }
        if (rodzajPlanszy == 3){
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(gridFog[i][j]);
                }
                System.out.println();
            }
        }
        if (rodzajPlanszy == 4) {
            grid2[0][0] = "   ";
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    grid2[i][j] = "~ ";
                }
                grid2[0][i] = String.valueOf(i) + " ";
                grid2[i][0] = " " + new String(Character.toChars(i + 64)) + " ";
            }
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    gridFog2[i][j] = grid2[i][j];
                }
            }
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(grid2[i][j]);
                }
                System.out.println();
            }
        }
        if (rodzajPlanszy == 5) {

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(grid2[i][j]);
                }
                System.out.println();
            }
        }

        if (rodzajPlanszy == 6) {

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(gridFog2[i][j]);
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


    static void dodajStatek(int nrStatku, int player) {
        if (!error) {
            String ship;
            int l = 0;
            int x1, x2, y1, y2;
            if (player == 1) {
                x1 = statek[nrStatku].maszt1x == 1 || statek[nrStatku].maszt2x == 1 ? statek[nrStatku].maszt1x : Math.min(statek[nrStatku].maszt1x, statek[nrStatku].maszt2x) -1;
                x2 = statek[nrStatku].maszt2x == 10 || statek[nrStatku].maszt1x == 10 ? statek[nrStatku].maszt2x : Math.max(statek[nrStatku].maszt1x, statek[nrStatku].maszt2x) + 1;
                y1 = statek[nrStatku].maszt1y == 1 || statek[nrStatku].maszt2y == 1 ? statek[nrStatku].maszt1y : Math.min(statek[nrStatku].maszt1y, statek[nrStatku].maszt2y) - 1;
                y2 = statek[nrStatku].maszt2y == 10 || statek[nrStatku].maszt1y == 10 ? statek[nrStatku].maszt2y : Math.max(statek[nrStatku].maszt1y, statek[nrStatku].maszt2y) + 1;

                // System.out.println(x1 +", " + x2 + ", " + y1 + ", " + y2);

                for (int j = y1; j <= y2; j++) {
                    for (int i = x1; i <= x2; i++) {
                        if (grid[j][i].equals("O ")) {
                            error = true;
                            errorCouse = "toClose";
                            break;
                        }
                    }
                    if (error) break;
                }


                if (!error) {
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
            if (player == 2) {
                x1 = statek2[nrStatku].maszt1x == 1 || statek2[nrStatku].maszt2x == 1 ? statek2[nrStatku].maszt1x : Math.min(statek2[nrStatku].maszt1x, statek2[nrStatku].maszt2x) -1;
                x2 = statek2[nrStatku].maszt2x == 10 || statek2[nrStatku].maszt1x == 10 ? statek2[nrStatku].maszt2x : Math.max(statek2[nrStatku].maszt1x, statek2[nrStatku].maszt2x) + 1;
                y1 = statek2[nrStatku].maszt1y == 1 || statek2[nrStatku].maszt2y == 1 ? statek2[nrStatku].maszt1y : Math.min(statek2[nrStatku].maszt1y, statek2[nrStatku].maszt2y) - 1;
                y2 = statek2[nrStatku].maszt2y == 10 || statek2[nrStatku].maszt1y == 10 ? statek2[nrStatku].maszt2y : Math.max(statek2[nrStatku].maszt1y, statek2[nrStatku].maszt2y) + 1;

                for (int j = y1; j <= y2; j++) {
                    for (int i = x1; i <= x2; i++) {
                        if (grid2[j][i].equals("O ")) {
                            error = true;
                            errorCouse = "toClose";
                            break;
                        }
                    }
                    if (error) break;
                }


                if (!error) {
                    do {
                        for (int i = 1; i < 11; i++) {
                            for (int j = 1; j < 11; j++) {
                                ship = String.valueOf(i) + String.valueOf(j);
                                if (statek2[nrStatku].rysunek[l].equals(ship)) {
                                    grid2[i][j] = "O ";
                                }
                            }
                        }
                        l++;
                    } while (l < 5);
                }
            }
        }
    }
}

