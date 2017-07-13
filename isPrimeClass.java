import acm.program.*;
import acm.util.*;
public class Hello extends ConsoleProgram{
	
	
	public void run(){
    
		int number = readInt("please enter the number for test :");
		if(isPrime(number)){
			println("this is  a prime");
		}else{
			println("this is not a prime");
		}
		
	}
	
	
	/**
	 * addressing purpose : this is important to test your knowledge of boolean methods and the use of that 
	 * you have to know that the verbose boolean means 
	 * if( n % 3 == 0) for example 
	 * returns true or false 
	 * however ,why not write a format like  return n % 3 ;
	 * this is a good software engineering 
	 * 
	 * precondition : obtain a number ,the method is aimed to test if the number is prime or not 
	 * postcondition: if the number is prime return true ,otherwise return false 
	 * algorithm : simply use brute force , get the value first 
	 * the mechanism of the prime number based on mathematical definition ,
	 * the test value starts off from 1 up to number less than itself 1 
	 **/
	
	
	 public boolean isPrime(int testNumber){
		 for(int i = 2; i < testNumber; i++){
			 if(testNumber % i == 0){
				 return false;
			 }
		 }
		 
		 return true;
	 }
	 
     
   

}