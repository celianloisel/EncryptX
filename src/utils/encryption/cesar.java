package utils.encryption;

import java.util.Scanner;

public class cesar {
    public static void choose () {
        Scanner sc = new Scanner(System.in);

        //for choose encrypt or decrypt
        while (true) {
            System.out.println("   Do you want to encrypt or decrypt? (E/D): ");
            String choice = sc.nextLine().toUpperCase();
            if (choice.equals("E")) {
                cesar.checkMessageRestriction(true);//call my function
            } else if (choice.equals("D")) {
                cesar.checkMessageRestriction(false);//call my function
            } else {
                System.out.println("Please enter 'E' to encrypt or 'D' to decrypt.");
            }

        }


    }


    //this is my function to encrypt
    public static void cesarEncryption (String message, int shift) {

        int i; //for loop
        char charToEncrypt;
        char calculation;
        int moduleShift = shift%26;
        //for each letters, apply the shift
        //each letters is in ascii
        for (i=0; i<message.length(); i++) {
            charToEncrypt = message.charAt(i);
            //Calculation:
            //charToEncrypt - 96 : it's for the conversion of the ASCII alphabet to normal
            //+ shift's to implement the shift that the user wants
            //%26 here it's to make the alphabet circular
            //+ 96  just for the conversion of the normal alphabet to ascii alphabet for the computer
            calculation = (char) (((charToEncrypt - 'a' + moduleShift) % 26) + 'a');
            //display the result
            System.out.print( calculation);


        }


    }
    public static void cesarDecryption (String message, int shift) {
        int i; //for loop
        char charToDecrypt;
        char calculation;
        int moduleShift= shift%26;
        //for each lettres, apply the shift
        //each letters is in ascii
        for (i=0; i<message.length(); i++) {
            charToDecrypt = message.charAt(i);
            //Calculation:
            //charToEncrypt - 96 : it's for the conversion of the ASCII alphabet to normal
            //+ shift's to implement the shift that the user wants
            //%26 here it's to make the alphabet circular
            //+ 96  just for the conversion of the normal alphabet to ascii alphabet for the computer
            calculation = (char) (((charToDecrypt - 'a' - moduleShift + 26) % 26) + 'a');
            //display the result
            System.out.print( calculation);
        }





    }

    //This function will check whether the message has been written correctly
    public static void checkMessageRestriction (Boolean isEncrypt){
        Scanner sc = new Scanner(System.in);
        int a ;//a for attemps
        String message ="";


        //for each round, check that everything is in lower case
        // if so, run cesarEncryption
        //Else, return to the base
        System.out.print("Enter the message ONLY LOWER CASE, no spaces or special characters): ");
        for (a=0;a<3;a++){
            message = sc.nextLine();
            if (message.matches("[a-z]+")){ //check that everything is respected with the regex
                cesar.checkShiftRestriction(message, isEncrypt);//run the function to check the shift
                a=99;//break
            }
            else {
                System.out.println("The message must contain only letters. You have " + (3 - a) + " attempt(s) left.");
            }
        }
    }
    //this function is to check the shift
    public static void checkShiftRestriction (String message, Boolean isEncrypt){
        Scanner sc = new Scanner(System.in);
        int a;//It's for attemps of the user
        String shift;
        //for each round, check that the number is positive
        // if so, run cesarEncryption
        //else, return to the menu
        System.out.print("How much do you want to shift? ");
        for (a=0;a<=3;a++) {
            shift = sc.nextLine();// Capture integer input for shift
            if (shift.matches("^[1-9][0-9]*$")) { //check that everything is respected with the regex
                System.out.println("Sucess!");
                if(isEncrypt){
                    cesar.cesarEncryption(message, Integer.parseInt(shift));//call my function
                }else{
                    cesar.cesarDecryption(message,Integer.parseInt(shift));//call my function
                }
                a=99;
            } else {
                System.out.println("Please enter a POSITIVE number " + (3 - a) + " attempt(s) left.");
            }


        }

    }



}














