package com.example.doctorsurgery;

import java.util.Scanner;

public class EasyScanner
{
    private static final Scanner scanner = new Scanner(System.in);

    // SCANNER OBJECT METHOD FOR STRING
    public static String nextLine()
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    // SCANNER OBJECT METHOD FOR INTEGER
    public static int nextInt()
    {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    // SCANNER OBJECT METHOD FOR DOUBLE
    public static double nextDouble()
    {
        Scanner sc = new Scanner(System.in);
        double d = sc.nextDouble();
        return d;
    }

    // SCANNER OBJECT METHOD FOR CHARACTER
    public static char nextChar()
    {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        return c;
    }

    //  SCANNER OBJECT METHOD TO CHECK IF THE SCANNER HAS ANOTHER TOKEN IN ITS INPUT
    public static boolean hasNext()
    {
        return scanner.hasNext();
    }

    // SCANNER OBJECT METHOD TO CHECK IF THERE IS ANOTHER LINE IN THE INPUT OF THIS SCANNER
    public static boolean hasNextLine()
    {
        return scanner.hasNextLine();
    }

    // CLOSING SCANNER OBJECT
    public static void close()
    {
        scanner.close();
    }

    public static boolean hasNextInt()
    {
        return scanner.hasNextInt();
    }
}
