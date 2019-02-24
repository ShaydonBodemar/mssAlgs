//Shaydon Bodemar
//Tool used to create examples for MSS Algorithm Tester
//2 - 23 - 2019

import java.util.*;
import java.io.*;

public class extool {
        public static void main(String[] args) {
                boolean repeat = false;
                do {
                        Scanner input = new Scanner(System.in);

                        System.out.println("\n\nEnter a positive integer to determine a range for the numbers to be generated within.\nThe range will be from + to - the entry.");
                        int r = input.nextInt();
                        input.nextLine();

                        System.out.println("\n\nEnter an integer for the number of entries to be generated within the test file.");
                        int num = input.nextInt();
                        input.nextLine();

                        System.out.println("\n\nEnter a filename for the file to be generated.");
                        String filename = input.nextLine();

                        generateFile(r,num,filename);
                        System.out.println("File created.\n\nWould you like to repeat?");
                        char yn = input.nextLine().charAt(0);
                        if(yn == 'y') repeat=true;
                        else if(yn == 'n') repeat=false;
                } while(repeat);
        }


        public static void generateFile(int r, int num, String filename) {
                try {
                        PrintWriter writer = new PrintWriter(filename, "UTF-8");
                        Random rand = new Random();
                        writer.print((rand.nextInt(r + 1 + r) - r));
                        for(int i = 0; i < num; i++) {
                                writer.print(" ");
                                writer.print((rand.nextInt(r + 1 + r) - r));
                        }
                        writer.close();
                }
                catch(FileNotFoundException e) {
                }
                catch(UnsupportedEncodingException e) {
                }
        }
}
