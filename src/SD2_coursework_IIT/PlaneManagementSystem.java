package SD2_coursework_IIT;

import java.util.Scanner;

/**
 * This class represents the plane management system.
 */

public class PlaneManagementSystem {
	
	
	/**
	 * This method validate the row letter
	 * @param txt - text to display
	 * @param input - Scanner object
	 * @return return a valid row letter from converting to uppercase
	 */
	public static String Enter_str(String txt,Scanner input) {
		
		while(true) {
			try {
				String[] rowLetterList = {"A","B","C","D"};
				System.out.print(txt);
				String letter = input.next().toUpperCase();
				for(String item : rowLetterList) {
					if(item.equals(letter)) {
						return letter;
					}
				}
				System.out.println("\tPlease enter a valid letter\n");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	/**
	 * This method validate the input whether it's an integer or a sting
	 * @param txt - text to display
	 * @param input - Scanner object
	 * @return it returns a valid integer
	 */
	public static int Enter_int(String txt,Scanner input) {
		
		while(true) {
			try {
				System.out.print(txt);
				int num = input.nextInt();
				return num;
			}catch(Exception e) {
				System.out.println("\tPlease enter an intiger\n");
				input.nextLine();     // get a another input to clear the next line character "\n"
			}
		}
	}

	
	/**
	 * This method checks whether the entered seat is valid or invalid
	 * @param rowLetter - Letter of the row
	 * @param seatNum - Number of the seat
	 * @return if the seat position is valid it returns 1 otherwise it returns 0
	 */
	public static int isValidSeat(String rowLetter,int seatNum) {
		
		if((rowLetter.equals("A") || rowLetter.equals("D")) && 0<seatNum && seatNum<=14) {
			return 1;
		}else if((rowLetter.equals("B") || rowLetter.equals("C")) && 0<seatNum && seatNum<=12) {
			return 1;
		}else {
			System.out.println("\tinvalid seat possition!\n");
			return 0;
		}
	}
	
	
	/**
	 * This method checks whether the entered email is in formats
	 * @param txt - display text
	 * @param input - Scanner object
	 * @return email - returns a valid email
	 */
	public static String isValidEmail(String txt,Scanner input) {
		
		while(true) {
			try {
				System.out.print(txt);
				String email = input.next();
				if(email.contains("@") && email.contains(".")) {
					return email;
				}else {
					System.out.println("\nInvalid email format, Please enter a valid email with '@' and '.' characters\n");
				}
			}catch(Exception e) {
				System.out.println("Error :"+e);
			}
		}
	}
	
	
	
	
	/**
	 * This method books the seat and get details of the person, then it creates a Ticket
	   object and to the ticket array
	 * @param rowLetter - Letter of the row
	 * @param seatNum - number of the seat
	 * @param seatList - seat array
	 * @param ticketList - ticket object array
	 * @param input - Scanner object
	 */
	public static void buy_seat(String rowLetter,int seatNum,int[][] seatList,Ticket[][] ticketList,Scanner input) {
		
		int row;
		
		if(rowLetter.equals("A") && seatList[0][seatNum-1]==0) {
			seatList[0][seatNum-1]=1;
			System.out.println("\nYou have successfully booked the seat\n");
			row = 0;
		}else if(rowLetter.equals("B") && seatList[1][seatNum-1]==0) {
			seatList[1][seatNum-1]=1;
			System.out.println("\nYou have successfully booked the seat\n");
			row = 1;
		}else if(rowLetter.equals("C") && seatList[2][seatNum-1]==0){
			seatList[2][seatNum-1]=1;
			System.out.println("\nYou have successfully booked the seat\n");
			row = 2;
		}else if(rowLetter.equals("D") && seatList[3][seatNum-1]==0){
			seatList[3][seatNum-1]=1;
			System.out.println("\nYou have successfully booked the seat\n");
			row = 3;
		}else {
			System.out.println("\nSorry!, The seat has been already booked\n");
			return;
		}
		
		System.out.println("\t\t---------Now enter your personal details--------\n");
		
		System.out.print("Enter person's name :");
		String name = input.next();
		System.out.print("Enter person's surname :");
		String surname = input.next();
		String email = isValidEmail("Enter person's Email :", input);
		System.out.println();  // line break
		
		Person person = new Person(name, surname, email);  //person object
		
		int price;
		if(seatNum<=5) {   // Getting the seat price
			price = 200;
		}else if(seatNum<=9) {
			price = 150;
		}else {
			price = 180;
		}
		
		Ticket ticket = new Ticket(rowLetter, seatNum, price, person);	// ticket object
		
		ticketList[row][seatNum-1] = ticket;	// adding ticket object to array
		
		ticket.save(rowLetter, seatNum); // create file and save ticket details
		
	}
	
	
	/**
	 * This method cancel the seat and removes the Ticket object from the ticket array
	 * @param cancelRowLetter - Letter of the row
	 * @param cancelSeatNum - Number of the seat
	 * @param seatList - seat array
	 * @param ticketList - ticket object array
	 */
	public static void cancel_seat(String cancelRowLetter,int cancelSeatNum,int[][] seatList,Ticket[][] ticketList) {
		
		int row;
		
		if(cancelRowLetter.equals("A") && seatList[0][cancelSeatNum-1]==1){
			seatList[0][cancelSeatNum-1]=0;
			System.out.println("\nThe seat has been cancled sucessfully\n");
			row = 0;
		}else if(cancelRowLetter.equals("B") && seatList[1][cancelSeatNum-1]==1) {
			seatList[1][cancelSeatNum-1]=0;
			System.out.println("\nThe seat has been cancled sucessfully\n");
			row = 1;
		}else if(cancelRowLetter.equals("C") && seatList[2][cancelSeatNum-1]==1){
			seatList[2][cancelSeatNum-1]=0;
			System.out.println("\nThe seat has been cancled sucessfully\n");
			row = 2;
		}else if(cancelRowLetter.equals("D") && seatList[3][cancelSeatNum-1]==1){
			seatList[3][cancelSeatNum-1]=0;
			System.out.println("\nThe seat has been cancled sucessfully\n");
			row = 3;
		}else {
			System.out.println("\nNothing to cancle,the seats haven't booked\n");
			return;
		}
		
		for(Ticket[] ticket_row : ticketList)
		{
			for(Ticket ticket_seat : ticket_row)
			{
				if(ticket_seat != null && cancelRowLetter.equals(ticket_seat.getRow()) && ticket_seat.getSeat() == cancelSeatNum){
					ticketList[row][cancelSeatNum-1].deleteFile(cancelRowLetter, cancelSeatNum); // delete created ticket file
					ticketList[row][cancelSeatNum-1] = null;   // remove ticket object
					return;
				}
			}
		}
	}

	
	/**
	 * This method use ASCII character system to find the first available seat
	 * @param seatList - Seat array
	 */
	public static void find_first_available(int[][] seatList) {
		int charNum=65;  
		int b=1;
		for(int[] seatrow: seatList)
		{
			for(int seat : seatrow) 
			{
				char charcter = (char) charNum;  // converting integer to a character
				if(seat == 0) {
					System.out.println("\nThe first available seat is row:"+charcter+" seat:"+b+"\n");
					return;
				}
				b+=1;
			}
			charNum+=1; // iterate the character from A to D
		}
	}

	
	/**
	 * This method show the reserved and non reserved seats in each row
	 * @param seatList - seat array
	 */
	public static void show_seating_plan(int[][] seatList){
		
		System.out.println();
		
		for(int[] seatrow: seatList)
		{
			for(int seat : seatrow)
			{
				if(seat==1) {
					System.out.print("X"+" ");
				}else {
					System.out.print("O"+" ");
				}
			}
			System.out.println(); // line break
		}
		System.out.println();
	}
	
	
	/**
	 * This method show the ticket information stored in ticket array
	 * @param ticketList - Ticket object array
	 * @see Ticket#print_ticketInfo()
	 */
	public static void print_tickets_info(Ticket[][] ticketList) {
		
		int sumOfTicketPrice = 0;
		
		System.out.println("\n----------------------------------");
		for(Ticket[] ticket_row : ticketList) 
		{
			for(Ticket ticket_seat : ticket_row)
			{
				if(ticket_seat != null)
				{
					ticket_seat.print_ticketInfo();
					sumOfTicketPrice += ticket_seat.getPrice();
					System.out.println("----------------------------------");
				}
			}
		}
		
		if(sumOfTicketPrice==0){
			System.out.println("\nThere is no ticket information to display.\n");
			System.out.println("----------------------------------");
		}else {
			System.out.println("\nTotal price of tickets :£"+sumOfTicketPrice+"\n");
		}
	}

	
	/**
	 * This method check the seat is available or not
	 * @param rowLetter - Letter of the row
	 * @param seatNum - Number of the seat
	 * @param ticketList - Ticket array
	 */
	public static void search_ticket(String rowLetter, int seatNum,Ticket[][] ticketList) {	
		
		for(Ticket[] ticket_row : ticketList) 
		{
			for(Ticket ticket_seat : ticket_row)
			{
				if(ticket_seat != null && rowLetter.equals(ticket_seat.getRow()) && ticket_seat.getSeat() == seatNum) {
					System.out.println("\nSorry this seat is reserved\n");
					System.out.println("----------------------------------");
					ticket_seat.print_ticketInfo();
					System.out.println("----------------------------------\n");
					return;
				}
			}
		}
		System.out.println("\nThis seat is available\n");
	}
	
	
	public static void main(String[] args) {
		
		int[][] planeSeates = new int[4][];  // raged seat array
		planeSeates[0] = new int[14];
		planeSeates[1] = new int[12];
		planeSeates[2] = new int[12];
		planeSeates[3] = new int[14];
		
		Ticket[][] ticketList = new Ticket[4][];  // Ticket array
		ticketList[0] = new Ticket[14];
		ticketList[1] = new Ticket[12];
		ticketList[2] = new Ticket[12];
		ticketList[3] = new Ticket[14];

		Scanner input = new Scanner(System.in);
		
		System.out.println("\n! Welcome to the Plane Management application ! \n");
		
		boolean status = true;
		while(status) {
		
			System.out.println("\n\t************************************************\n"
							   + "\t*                 MENU OPTIONS                 *\n"
							   + "\t************************************************\n"
							   + "\t    1) Buy a seat                               \n"
							   + "\t    2) Cancel a seat                            \n"
							   + "\t    3) Find first available seat                \n"
							   + "\t    4) Show seating plan                        \n"
							   + "\t    5) Print ticket information and total sales \n"
							   + "\t    6) Search ticket                            \n"
							   + "\t    0) Quit                                     \n"
							   + "\t************************************************\n");
		
		
			int validOption = Enter_int("Please enter an option :",input);
			
			switch(validOption) {
			case 1:
				String validRowLetter = Enter_str("Enter row letter :",input);
				int validSeatNum = Enter_int("Enter seat number :",input);
				int val = isValidSeat(validRowLetter, validSeatNum);
				if(val==1) {
					buy_seat(validRowLetter, validSeatNum, planeSeates, ticketList,input);
				}
				break;
			case 2:
				String cancelRowLetter = Enter_str("Enter row letter to cancel :",input);
				int cancelSeatNum = Enter_int("Enter seat number to cancel :",input);
				int val2 = isValidSeat(cancelRowLetter, cancelSeatNum);
				if(val2==1) {
					cancel_seat(cancelRowLetter, cancelSeatNum, planeSeates, ticketList);
				}
				break;
			case 3:
				find_first_available(planeSeates);
				break;
			case 4:
				show_seating_plan(planeSeates);
				break;
			case 5:
				print_tickets_info(ticketList);
				break;
			case 6:
				String searchRowLetter = Enter_str("Enter row letter to search :",input);
				int searchSeatNum = Enter_int("Enter seat number to search :",input);				
				int val3 = isValidSeat(searchRowLetter, searchSeatNum);
				if(val3==1) {
					search_ticket(searchRowLetter, searchSeatNum, ticketList);
				}
				break;
			case 0:
				status = false;
				input.close();
				System.out.println("\n\t\t--Exited from the program--\n");
				break;	
			default:
				System.out.println("\tInvalid option number !\n");
			}
		}
		
	}
}
