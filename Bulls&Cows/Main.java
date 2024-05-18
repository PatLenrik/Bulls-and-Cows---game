package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        String dlzkakodu = scanner.nextLine();
        int dlzkaKodu = 0;
        try {
            dlzkaKodu = Integer.parseInt(dlzkakodu);

        } catch (NumberFormatException e) {
            System.out.println("Error: " + dlzkakodu + " isn't a valid number.");
            System.exit(0);
        }
        char[] tajnyKodRad = new char[dlzkaKodu];
        int[] tajnykodRadCisla = new int[dlzkaKodu];

        if (dlzkaKodu == 0) {
            System.out.println("Error: Grade: None.");
            System.exit(0);
        }
        if (dlzkaKodu > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        int dlzkaMoznosti = scanner.nextInt();
        if (dlzkaMoznosti > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
        if (dlzkaMoznosti < dlzkaKodu) {
            System.out.println("Error: it's not possible to generate a code with a length of " + dlzkaKodu + "with "
                    + dlzkaMoznosti + " unique symbols.");
            System.exit(0);
        }
        char[] vyber = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        int[] cisla = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
                24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };

        // System.out.print(vyber[0]);
        if (dlzkaMoznosti < 11) {
            System.out.print("The secret is prepared: ");
            for (int stars = 0; stars < dlzkaKodu; stars++) {
                System.out.print("*");
            }
            System.out.println(" (0-" + vyber[dlzkaMoznosti - 1] + ").");

        }
        if (dlzkaMoznosti > 10) {
            System.out.print("The secret is prepared: ");
            for (int stars = 0; stars < dlzkaKodu; stars++) {
                System.out.print("*");
            }
            System.out.println(" (0-9, a-" + vyber[dlzkaMoznosti - 1] + ").");

        }

        Random random = new Random();

        int prve = random.nextInt(dlzkaMoznosti);
        tajnykodRadCisla[0] = cisla[prve];
        tajnyKodRad[0] = vyber[prve];
        // System.out.println(tajnyKodRad[0]);

        boolean nove;
        int c;
        for (int vytvaraniekodu = 1; vytvaraniekodu < dlzkaKodu; vytvaraniekodu++) {

            do {
                nove = true;
                c = random.nextInt(dlzkaMoznosti);

                for (int kontrola = 0; kontrola < vytvaraniekodu; kontrola++) {
                    if (c == tajnykodRadCisla[kontrola]) {
                        nove = false;
                    }
                }
            } while (nove == false);
            tajnykodRadCisla[vytvaraniekodu] = c;
            tajnyKodRad[vytvaraniekodu] = vyber[c];
            // System.out.println(vyber[c]);
        }

        System.out.println("Okay, let's start a game!");
        int bull = 0;
        int cow = 0;
        int pokus = 1;
        while (bull != dlzkaKodu) {
            bull = 0;
            cow = 0;
            System.out.println("Turn " + pokus + ":");
            pokus++;
            String tipujuceCislo = scanner.next();
            char[] tipujuceCisloToArray = tipujuceCislo.toCharArray();

            for (int vypisat = 0; vypisat < dlzkaKodu; vypisat++) {
                System.out.print(tajnyKodRad[vypisat]);
            }
            for (int j = 0; j < dlzkaKodu; j++) {
                for (int n = 0; n < dlzkaKodu; n++) {
                    if (j == n && (tipujuceCisloToArray[n] == (tajnyKodRad[j]))) {
                        bull++;
                    }
                    if (j != n && tipujuceCisloToArray[n] == (tajnyKodRad[j])) {
                        cow++;
                    }
                }
            }
            if (cow != 0 || bull != 0) {
                System.out.print("Grade: ");
            }
            if (bull == 1) {
                System.out.print("1 bull");
            }
            if (bull > 1) {
                System.out.print(bull + " bulls");
            }
            if (cow > 0 && bull > 0) {
                System.out.print(" and ");
            }
            if (cow == 1) {
                System.out.print("1 cow");
            }
            if (cow > 1) {
                System.out.print(cow + " cows");
            }
            if (cow != 0 || bull != 0) {
                System.out.println("");
            }
            if (cow == 0 && bull == 0) {
                System.out.println("Grade: None.");
            }
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
