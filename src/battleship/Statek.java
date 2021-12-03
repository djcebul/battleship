package battleship;

public class Statek {
    int maszty, maszt1y, maszt1x, maszt2y, maszt2x;

    String[] rysunek = {"~ ", "~ ", "~ ", "~ ", "~ ", "~ "};
    String nazwa;
    public static String errorCouse;
    boolean poziomo;
    public static boolean error = false;

    public Statek(int maszty, String maszt1, String maszt2, String nazwa) {
        this.nazwa = nazwa;
        this.maszty = maszty;


        /** Przypisanie wspolrzednych do zmiennych liczbowych
        *  np. A1 = 11
        *      D9 = 49 itd.                                  */

            if (maszt1.length() < 3) {
                this.maszt1y = (int) maszt1.charAt(0) - 64;
                this.maszt1x = (int) maszt1.charAt(1) - 48;
            } else {
                this.maszt1x = 10;
                this.maszt1y = (int) maszt1.charAt(0) - 64;
            }
            if (maszt2.length() < 3) {
                this.maszt2y = (int) maszt2.charAt(0) - 64;
                this.maszt2x = (int) maszt2.charAt(1) - 48;
            } else {
                this.maszt2x = 10;
                this.maszt2y = (int) maszt2.charAt(0) - 64;
            }


            if (this.maszt1x - this.maszt2x != 0 && this.maszt1y - this.maszt2y != 0) {
                error = true;
                errorCouse = "wrongLocation";
            }





        /**
            Okreslenie czy statek ulozony jest poziomo, czy pionowo
            i wpisanie jego wspolrzednych do tablicy rysunek.
            Nie jest wazne czy najpierw jest podana przez uzytkownika poczatkowa, czy koncowa wspolrzedna, np.
            A1, A4 -OK
            D5, D2 -OK
            Nastepnie sprawdzana jest wprowadzona dlugosc statku, np.
            A1 - A5 = 5 ? // czy pieciomasztowiec ma 5 masztow,
            jesli nie program odsyla do obslugi bledow
         */

        if (this.maszt1y - this.maszt2y == 0) {
            poziomo = true;
            int l = Math.min(maszt1x, maszt2x);
            for (int i = 0; i < this.maszty; i++) {
                this.rysunek[i] = this.maszt1y + String.valueOf(l);
                l++;
            }

            if (this.maszty - 1 != Math.abs(this.maszt1x-this.maszt2x) && !error) {
                error = true;
                errorCouse = "wrongLength";
            }
        }
        else {
            poziomo = false;
            int l = Math.min(maszt1y, maszt2y);
            for (int i = 0; i < this.maszty; i++) {
                this.rysunek[i] = String.valueOf(l) + String.valueOf(this.maszt1x);
                l++;
            }
            if (this.maszty - 1 != Math.abs(this.maszt1y-this.maszt2y) && !error) {
                error = true;
                errorCouse = "wrongLength";
            }
        }

        }
    }

