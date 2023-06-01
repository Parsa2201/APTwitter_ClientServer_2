package com.twitter.client.view;

import java.util.Scanner;

public class TwitterLog
{
    public static boolean showLog = true;
    public static Scanner scanner;

    private TwitterLog()
    {

    }

    private static void scannerInit()
    {
        if(scanner == null)
            scanner = new Scanner(System.in);
    }

    public static void println(String str)
    {
        if(showLog)
            System.out.println(str);
    }

    public static void print(String str)
    {
        if(showLog)
            System.out.print(str);
    }

    public static void printlnError(String str)
    {
        if(showLog)
        {
            System.err.println(str);
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException ignored){}
        }
    }

    public static void printError(String str)
    {
        if(showLog)
        {
            System.err.print(str);
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException ignored){}
        }
    }

    public static String nextLine()
    {
        scannerInit();
        return scanner.nextLine();
    }

    public static String nextLine(String str)
    {
        print(str);
        return nextLine();
    }
}
