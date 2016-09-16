package ua.epam;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int rand(int range[]) {
        Random random = new Random();
        int number = random.nextInt(range[1] - range[0]) + range[0];

        return number;
    }

    private static boolean checkNumber(int temp, int range[]) {
        if ( temp < range[0] || temp > range[1] ) {
            return false;
        }

        return true;
    }

    private static int scanNumber(int range[]){
        Scanner scanner = new Scanner(System.in);

        int temp;

        try {
            temp = scanner.nextInt();
            if ( checkNumber(temp, range) ) {
                return temp;
            } else {
                System.out.println("You entered a number outside the range.");
                System.out.println("Please type a number in the range from " + range[0] + " to " + range[1]);
                scanNumber(range);
            }
        } catch (Exception e) {
            System.out.println("You entered an invalid value.");
            System.out.println("Please type a number in the range from " + range[0] + " to " + range[1]);
            return scanNumber(range);
        }

        return temp;
    }

    private static void changeRange(int number, int temp, int range[]) {
        if (temp > range[0] && temp < range[1]) {
            if (temp > number ) {
                range[1] = temp;
            } else if (temp < number) {
                range[0] = temp;
            }
        }
    }

    private static int[] checkInsert(int number, int temp, int insert[], int range[]){
        for (int i = 0; i < insert.length; i++) {
            if (temp == insert[i]) {
                System.out.println("You've entered this number previously.");
                System.out.println("Please type a number in the range from " + range[0] + " to " + range[1]);
                return insert;
            }
        }

        int t[] = Arrays.copyOf(insert, (insert.length + 1));
        t[t.length-1] = temp;
        changeRange(number, temp, range);

        return t;
    }

    private static int[] tryGuess(int number,int insert[], int range[]) {
        int temp = scanNumber(range);
        int t[] = checkInsert(number, temp, insert, range);

        if (!(Arrays.equals(t, insert))){
            return t;
        }

        return insert;
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int range[] = {min, max};
        int number = rand(range);
        int count = 0;
        int[] insert = new int[0];
        boolean win = false;
        int size = insert.length;

        System.out.println("Please type a number in the range from " + range[0] + " to " + range[1]);

        while (!win) {
            count++;
            insert = tryGuess(number, insert, range);
            if (insert[insert.length-1] == number) {
                win = true;
                System.out.println("Congratulations!");
                System.out.println("You won with " + count + " attempts");
            } else if (size != insert.length) {
                size = insert.length;
                System.out.println("Sorry.. It's a wrong number. Try again.");
                System.out.println("Please type a number in the range from " + range[0] + " to " + range[1]);
            }
        }
    }
}
