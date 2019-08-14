package com.company;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {

    public Locale Start() {
        Scanner sc = new Scanner(System.in);
        Locale lc;
        int choice;

        while(true){
            try{
                System.out.println("Choose option: ");
                choice = Integer.parseInt(sc.next());
                if (choice == 1) {
                    lc = new Locale("Vi");
                    break;
                }

                if (choice == 2) {
                    lc = new Locale("En");
                    break;
                }
                else {
                    System.out.println("Option only between 1-2");
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Invalid Input !!!");
            }
        }
        return lc;
    }

    public void Login(Locale lc) {
        Scanner sc = new Scanner(System.in);

        ResourceBundle bundle = ResourceBundle.getBundle("resource.lang", lc);
        while (true) {
            System.out.println(bundle.getString("Bank_Acc"));
            String ac = sc.nextLine();

            String pattern = "[\\d]{10}";
            if (ac.matches(pattern)) {
                break;
            } else {
                System.out.println(bundle.getString("Acc_Error"));
            }
        }

        while (true) {

            System.out.println(bundle.getString("Acc_Pass"));

            String pass = sc.nextLine();
            Pattern pat = Pattern.compile("(([A-Za-z].*[0-9])|([0-9].*[A-Za-z]))");
            Matcher match = pat.matcher(pass);
            boolean b = match.find();
            boolean b2 = pass.contains("");
            boolean b3 = true;

            if (pass.length() < 8 || pass.length() > 31) {
                b2 = false;
            }

            if (b && b2 && b3) {
                break;
            } else {
                System.out.println(bundle.getString("Pass_Error"));
            }
        }
    }

    public void Captcha(Locale lc) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        String capt = "";
        int count = 0;
        ResourceBundle bundle = ResourceBundle.getBundle("resource.lang", lc);
        while (count != 4) {
            int num = rd.nextInt(91) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90)) {
                char temp = (char) num;
                capt += temp;
                count += 1;
            }
        }

        System.out.println("Captcha: " + capt);
        while (true) {
            System.out.println(bundle.getString("Captcha"));
            String captcha = sc.nextLine();
            if (capt.contains(captcha)) {
                System.out.println(bundle.getString("Login"));
                break;
            } else {
                System.out.println("Cap_Error");
            }
        }
    }
}
