//Shaydon Bodemar
//Program to find Maximum Subsequence Sum and perform analysus on efficiency of a variety of algorithms
//2 - 25 - 2019

import java.util.*;
import java.lang.*;
import java.io.*;


public class mssAlgs {
        public static void main(String[] args) {

                Scanner input = new Scanner(System.in);
                boolean repeat = true;

                while(repeat) {
                        System.out.println("\n\nWelcome to the MSS Algorithm Tester!\nWhat is the name of the file you would like to select?");
                        String filename = input.nextLine();

                        System.out.println("\n\nWhat Algorithm would would you like to use? Enter the associated number. (1-5)");
                        System.out.println("1: MSS1 (3 Embedded Loops)\n2: MSS2 (2 Embedded Loops)\n3: MSS3 (Recursive QuickSelect)\n4: MSS4 (Single Loop)\n5: All 4 Algorithms At Once");
                        int algSelect = input.nextInt();
                        input.nextLine();

                        algHandler(filename,algSelect);

                        System.out.println("\n\nWould you like to repeat (y/n)?");
                        char yn = input.next().charAt(0);
                        input.nextLine();
                        if(yn=='n') {
                                repeat = false;
                                System.out.println("\n\nGoodbye\n\n");
                        }
                        else { continue; }
                }

        }
        

        public static void algHandler(String filename, int algSelect) {
                int[] numSeq = arrayCreator(filename);
                /*for(int i = 0; i < numSeq.length; i++) {
                        System.out.println(numSeq[i]);
                }*/


                switch(algSelect) {
                        case 1:
                                MSS1(numSeq);
                                break;
                        case 2:
                                MSS2(numSeq);
                                break;
                        case 3:
                                MSS3(numSeq);
                                break;
                        case 4:
                                MSS4(numSeq);
                                break;
                        case 5:
                                MSS1(numSeq);
                                MSS2(numSeq);
                                MSS3(numSeq);
                                MSS4(numSeq);
                                break;
                        default:
                                System.out.println("\n\nThe algorithm selection was invalid, please enter a number 1-5 to select.");
                }
        }


        public static int[] arrayCreator(String filename) {
                List<Integer> numbers = new ArrayList<Integer>();
                File file = new File(filename);

                List<Integer> numbers = new ArrayList<Integer>();
                File file = new File(filename);
                BufferedReader freader = null;

                try {
                        freader = new BufferedReader(new FileReader(file));
                        String text = null;

                        while((text = freader.readLine()) != null) {
                                String[] parts = text.split(" ");
                                //System.out.println(parts.length);
                                for(int i = 0; i < parts.length; i++) {
                                        numbers.add(Integer.parseInt(parts[i]));
                                }
                                //numbers.add(Integer.parseInt(text));
                        }
                }
                catch(FileNotFoundException e) {
                        //print message about wrong filename or DNE
                }
                catch(IOException e) {
                        //print message about incorrect file format or some shit
                }
                finally {
                        try {
                                if(freader != null) {
                                        freader.close();
                                }
                        }
                        catch(IOException e) {
                        }
                }

                int[] numSeq = new int[numbers.size()];
                for(int i = 0; i < numSeq.length; i++) {
                        numSeq[i] = numbers.get(i);
                }
                return numSeq;

        }


        public static void MSS1(int[] numSeq) {



        }


        public static void MSS2(int[] numSeq) {



        }


        public static void MSS3(int[] numSeq) {



        }


        public static void MSS4(int[] numSeq) {



        }
}
