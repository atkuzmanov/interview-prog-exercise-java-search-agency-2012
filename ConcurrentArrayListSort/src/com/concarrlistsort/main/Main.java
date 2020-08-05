package com.concarrlistsort.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * Task 2:
 * Write a Java program to sort an ArrayList of Integers and an ArrayList of Strings concurrently 
 * and output “Strings” if the ArrayList of Strings took longer to sort, or “Integers” if the 
 * ArrayList of Integers took longer to sort.
 */

/*
 * For the purpose of this task I have not utilised Object Oriented Programming due
 * to it's simplicity. Instead I've gone for a more functional approach.
 * All the methods are synchronized to avoid concurrency display problems except the run()
 * methods of the threads as we want them to run concurrently.
 */

public class Main {
	 
	private static final int MAX_SIZE = 1000000; //Constant MAX_SIZE - ArrayLists size
	private static List<Integer> intarr;
	private static List<String> strarr;
	private static long intStartTime;
	private static long strStartTime;
	private static long endTime;
	
	public static synchronized void main(String[] args) {
		intarr = new ArrayList<Integer>();
		strarr = new ArrayList<String>();
		
		/* Filling the ArraysLists with random values. */
		fillIntArr(intarr);
		fillStrArr(strarr);
		
		/* Shuffling the ArrayLists */
		Collections.shuffle(intarr);
		Collections.shuffle(strarr);
		
		test();
		display();
	}
	
	/*
	 * Method which creates and runs two Threads concurrently.
	 * Each Thread stores it's start time and then performs sorting.
	 * The first thread sorts the ArrayList of Integers.
	 * The second one sorts the ArrayList of Strings.
	 * The finish time is then stored.
	 * @param - n/a
	 * @return - void
	 */
	private static synchronized void test(){
		Runnable r1 = new Runnable() {
			  public void run() {
			    try {
			        intStartTime = System.currentTimeMillis();
			        Collections.sort(intarr);
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			  }
			};
			
			Runnable r2 = new Runnable() {
			  public void run() {
			    try {
			        strStartTime = System.currentTimeMillis();
			        Collections.sort(strarr);
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			  }
			};
		
			Thread thr1 = new Thread(r1);
			Thread thr2 = new Thread(r2);
			thr1.start();
			thr2.start();

			endTime = System.currentTimeMillis();
	}
	
	/*
	 * Method which displays the results.
	 * It calculates time it took each Thread to run and perform the sorting.
	 * Then in checks if the ArrayList of Integers took longer to sort than 
	 * the ArrayList of Strings or if it is the other way around, or if they both
	 * took the same time to sort and displays the result.
	 * @param - n/a
	 * @return - void
	 */
	private static synchronized void display(){
		long r1time = endTime - intStartTime;
		long r2time = endTime - strStartTime;
			System.out.println("R1 " + (r1time));
			System.out.println("R2 " + (r2time));	
			if((r1time) == (r2time)){
				System.out.println("Equal");
			} else if((r1time) > (r2time)){
				System.out.println("Integers");
			} else {
				System.out.println("Strings");
			}
	}
	
	/*
	 * Method for filling an ArrayList of Integers with random values.
	 */
	private static synchronized void fillIntArr(List<Integer> iarr){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis()+rand.nextInt(512));
		for (int i=0; i<MAX_SIZE; i++)
		{
			Integer r = rand.nextInt(Integer.MAX_VALUE) + (((int)(Math.random()) * Integer.MAX_VALUE));
			iarr.add(r);
		}
	}

	/*
	 * Method for filling an ArrayList of Strings with random values.
	 */
	private static synchronized void fillStrArr(List<String> sarr){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i=0; i<MAX_SIZE; i++)
		{
			String s = "az" + rand.nextInt(256) + (((int)(Math.random()) * Integer.MAX_VALUE));
			sarr.add(s);
		}
	}
}
