//Shaydon Bodemar
//Program to find Maximum Subsequence Sum and perform analysus on efficiency of a variety of algorithms
//2 - 25 - 2019

import java.util.*;
import java.lang.*;
import java.io.*;


public class mssAlgs {
        public static void main(String[] args) {

                //initialize scanner for user input
                Scanner input = new Scanner(System.in);
                boolean repeat = true;

                //loop which continues if user specifies
                while(repeat) {
                        //prompt for name of file to be read
                        System.out.println("\n\nWelcome to the MSS Algorithm Tester!\nWhat is the name of the file you would like to select?");
                        String filename = input.nextLine();

                        //prompt for selection of algorithm
                        System.out.println("\n\nWhat Algorithm would would you like to use? Enter the associated number. (1-5)");
                        System.out.println("1: MSS1 (3 Embedded Loops)\n2: MSS2 (2 Embedded Loops)\n3: MSS3 (Recursive QuickSelect)\n4: MSS4 (Single Loop)\n5: All 4 Algorithms At Once");
                        int algSelect = 0;
                        try {
                                algSelect = input.nextInt();
                        }
                        catch(InputMismatchException e) {
                                System.out.println("\n\nThe algorithm selection entered was not an int 1-5. Try again.");
                        }
                        input.nextLine();

                        System.out.println("\n---------------------------------------------------------------------------------------------------\n");
                        //call to angHandler method to run algorithm(s) selected
                        algHandler(filename,algSelect);

                        //ask user if he/she would like to read a new file in
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
                //ends if no numbers were read in
                if(numSeq.length == 0) {
                        return;
                }

                //determination of algorithm methods to be called
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
                                System.out.println("The algorithm selection was invalid, please enter a number 1-5 to select.");
                }
        }


        public static int[] arrayCreator(String filename) {
                //initialization of ArrayList for reading in data and reader once file is opened
                List<Integer> numbers = new ArrayList<Integer>();
                File file = new File(filename);
                BufferedReader freader = null;

                try {
                        //begin reading specified file
                        freader = new BufferedReader(new FileReader(file));
                        String text = null;

                        //read through the line of data
                        while((text = freader.readLine()) != null) {
                                //split string up by spaces, and find integers in each entry
                                String[] parts = text.split(" ");
                                for(int i = 0; i < parts.length; i++) {
                                        numbers.add(Integer.parseInt(parts[i]));
                                }
                        }
                }
                catch(FileNotFoundException e) {
                        System.out.println("Invalid filename, file not found. Ensure file is spelled correctly and present in this directory.");
                }
                catch(IOException e) {
                        System.out.println("File format incompatible. Try another file, ensure format is correct.");
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

                //transfer ArrayList into a normal array of type int[]
                int[] numSeq = new int[numbers.size()];
                for(int i = 0; i < numSeq.length; i++) {
                        numSeq[i] = numbers.get(i);
                }
                return numSeq;

        }


        public static void MSS1(int[] numSeq) {
                long startTime = System.nanoTime();
                int n = numSeq.length, maxSum = 0;
                for(int i = 0; i < n; i++) {
                        for(int j = i; j < n; j++) {
                                int sum = 0;
                                for(int k = i; k <= j; k++) {
                                        sum += numSeq[k];
                                }
                                if(sum > maxSum) {
                                        maxSum = sum;
                                }
                        }
                }
                long elapTime = System.nanoTime() - startTime;

                System.out.println("MSS1 Result: " + maxSum + "\nMSS1 Execution Time: " + elapTime);

        }


        public static void MSS2(int[] numSeq) {
                long startTime = System.nanoTime();
                int n = numSeq.length, maxSum = 0;
                for(int i = 0; i < n; i++) {
                        int sum = 0;
                        for(int j = i; j < n; j++) {
                                sum += numSeq[j];
                                if(sum > maxSum) {
                                        maxSum = sum;
                                }
                        }
                }
                long elapTime = System.nanoTime() - startTime;

                System.out.println("MSS2 Result: " + maxSum + "\nMSS2 Execution Time: " + elapTime);
        }


        public static void MSS3(int[] numSeq) {
                long startTime = System.nanoTime();
                //call to recursive method, with timing done on oppsite end sof the operation ny System.nanoTime()
                int maxSum = maxSumRec(numSeq, 0, numSeq.length - 1);
                long elapTime = System.nanoTime() - startTime;

                System.out.println("MSS3 Result: " + maxSum + "\nMSS3 Execution Time: " + elapTime);
        }


        public static int maxSumRec(int[] seq, int low, int high) {
                if(low == high) {
                        if(seq[low] > 0) return seq[low];
                        else return 0;
                }
                int middle = (low + high)/2;
                int maxLeftSum = maxSumRec(seq, low, middle);
                int maxRightSum = maxSumRec(seq, middle + 1, high);
                int maxLeftBoundSum = 0, maxRightBoundSum = 0, sum = 0;
                for(int i = middle; i >= low; i--) {
                        sum += seq[i];
                        if(sum > maxLeftBoundSum) {
                                maxLeftBoundSum = sum;
                        }
                }
                sum = 0;
                for(int j = middle + 1; j <= high; j++) {
                        sum += seq[j];
                        if(sum > maxRightBoundSum) {
                                maxRightBoundSum = sum;
                        }
                }

                return max3(maxLeftSum, maxRightSum, maxLeftBoundSum + maxRightBoundSum);
        }


        //finds maximum value out of 3 provided
        public static int max3(int test1, int test2, int test3) {
                if(test1 >= test2 && test1 >= test3) return test1;
                else if(test2 >= test3) return test2;
                else return test3;
        }


        public static void MSS4(int[] numSeq) {
                long startTime = System.nanoTime();
                int maxSum = 0, sum = 0;
                for(int i = 0; i < numSeq.length; i++) {
                        sum += numSeq[i];
                        if(sum > maxSum) {
                                maxSum = sum;
                        }
                        else if(sum < 0) {
                                sum = 0;
                        }
                }
                long elapTime = System.nanoTime() - startTime;

                System.out.println("MSS4 Result: " + maxSum + "\nMSS4 Execution Time: " + elapTime);
        }
}
