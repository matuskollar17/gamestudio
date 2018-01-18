package gamestudio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class time {

	public static void main(String[] args) {
		
		//Converting milliseconds to Date using java.util.Date
	       //current time in milliseconds
	       long currentDateTime = System.currentTimeMillis();
	      
	       //vypisanie current casu
	       System.out.println(currentDateTime);
	       
	       
	       Scanner scanner = new Scanner(System.in);
	       System.out.println("cakanie na odratanie casu");
	       String line = scanner.nextLine();
	       
	       
	       System.out.println(line);
	       //druhy cas
	       System.out.println(System.currentTimeMillis());
	       System.out.println("zadaj zas");
	       String line1 = scanner.nextLine();
	       
	       System.out.println(line1);
	       //druhy cas - prvy /1000
	       long tcount = (int) ((System.currentTimeMillis() - currentDateTime) / 1000);
	       System.out.println("sekundy");
	       System.out.println(tcount);
	       
	       
	       System.out.println("retype");
	       
	  //    int x = long.intValue();
	       
	       
	       //creating Date from millisecond
	       Date currentDate = new Date(currentDateTime);
	      
	       //printing value of Date
	       System.out.println("current Date: " + currentDate);
	      
	       //DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
	      
	       DateFormat df = new SimpleDateFormat("HH:mm:ss");
	       
	       
	       //formatted value of current Date
	       System.out.println("Milliseconds to Date: " + df.format(currentDate));
	      
	       //Converting milliseconds to Date using Calendar
	      // Calendar cal = Calendar.getInstance();
	    //   cal.setTimeInMillis(currentDateTime);
	     //  System.out.println("Milliseconds to Date using Calendar:"
	           //    + df.format(cal.getTime()));
	      
	       //copying one Date's value into another Date in Java
	     //  Date now = new Date();
	     //  Date copiedDate = new Date(now.getTime());
	      
	     //  System.out.println("original Date: " + df.format(now));
	   //    System.out.println("copied Date: " + df.format(copiedDate));
	    }


	
		

	}


