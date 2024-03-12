package SD2_coursework_IIT;

import java.util.Scanner;

/**
 * This class represents the plane management system.
 */

public class PlaneManagementSystem {
	
	/**
	 * This field represents the index number of the ticket array when adding
	   a ticket to the ticket array.
	 */
	static int ticketArrayIndex = 0;
	
	
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
				System.out.println("Please enter a valid letter\n");
			}catch(Exception e) {
				System.out.println(e);
				input.nextLine();
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
				System.out.println("Please enter an intiger\n");
				input.nextLine();
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
			System.out.println("invalid seat possition!\n");
			return 0;
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
	public static void buy_seat(String rowLetter,int seatNum,int[][] seatList,Ticket[] ticketList,Scanner input) {
		
		if(rowLetter.equals("A") && seatList[0][seatNum-1]==0) {
			seatList[0][seatNum-1]=1;
			System.out.println("You have successfully booked the seat\n");
		}else if(rowLetter.equals("B") && seatList[1][seatNum-1]==0) {
			seatList[1][seatNum-1]=1;
			System.out.println("You have successfully booked the seat\n");
		}else if(rowLetter.equals("C") && seatList[2][seatNum-1]==0){
			seatList[2][seatNum-1]=1;
			System.out.println("You have successfully booked the seat\n");
		}else if(rowLetter.equals("D") && seatList[3][seatNum-1]==0){
			seatList[3][seatNum-1]=1;
			System.out.println("You have successfully booked the seat\n");
		}else {
			System.out.println("Sorry!, The seat has been already booked\n");
			return;
		}
		
		System.out.println("---------Now enter your personal details--------\n");
		
		System.out.print("Enter person's name :");
		String name = input.next();
		System.out.print("Enter person's surname :");
		String surname = input.next();
		System.out.print("Enter person's Email :");
		String email = input.next();
		System.out.println(); // line break
		
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
		ticketList[ticketArrayIndex] = ticket;	// adding ticket object to array
		ticketArrayIndex+=1;
		
		ticket.save(rowLetter, seatNum); // create file and save ticket details
		
	}
	
	
	/**
	 * This method cancel the seat and removes the Ticket object from the ticket array
	 * @param cancelRowLetter - Letter of the row
	 * @param cancelSeatNum - Number of the seat
	 * @param seatList - seat array
	 * @param ticketList - ticket object array
	 */
	public static void cancel_seat(String cancelRowLetter,int cancelSeatNum,int[][] seatList,Ticket[] ticketList) {
		
		if(cancelRowLetter.equals("A") && seatList[0][cancelSeatNum-1]==1) {
			seatList[0][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully\n");
		}else if(cancelRowLetter.equals("B") && seatList[1][cancelSeatNum-1]==1) {
			seatList[1][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully\n");
		}else if(cancelRowLetter.equals("C") && seatList[2][cancelSeatNum-1]==1){
			seatList[2][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully\n");
		}else if(cancelRowLetter.equals("D") && seatList[3][cancelSeatNum-1]==1){
			seatList[3][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully\n");
		}else {
			System.out.println("Nothing to cancle,the seats haven't booked\n");
			return;
		}
		
		int index = 0;
		for(Ticket item : ticketList) {
			if(item != null) {
				if(cancelRowLetter.equals(item.getRow()) && item.getSeat() == cancelSeatNum) {
					break;
				}
			}
			index+=1;
		}
		
		ticketList[index].deleteFile(cancelRowLetter, cancelSeatNum); // delete created ticket file
		
		ticketList[index] = null;	// remove ticket object
	}

	
	/**
	 * This method use ASCII character system to find the first available seat
	 * @param seatList - Seat array
	 */
	public static void find_first_available(int[][] seatList) {
		int charNum=65;  
		int b=1;
		for(int[] seatrow: seatList) {
			for(int seat : seatrow) {
				char charcter = (char) charNum;  // converting integer to a character
				if(seat == 0) {
					System.out.println("The first available seat is row:"+charcter+" seat:"+b+"\n");
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
		for(int[] seatrow: seatList) {
			for(int seat : seatrow) {
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
	public static void print_tickets_info(Ticket[] ticketList) {
		int sumOfTicketPrice = 0;
		System.out.println();
		for(Ticket item : ticketList) {
			if(item != null) {
				item.print_ticketInfo();
				sumOfTicketPrice += item.getPrice();
				System.out.println("----------------------------------");
			}
		}
		
		if(sumOfTicketPrice==0) {
			System.out.println("There is no ticket information to display.");
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
	public static void search_ticket(String rowLetter, int seatNum,Ticket[] ticketList) {	
		int index = 0;
		for(Ticket item : ticketList) {
			if(item!=null) {
				if(rowLetter.equals(ticketList[index].getRow()) && ticketList[index].getSeat()==seatNum){
					break;
				}
			}
			index+=1;
		}
		
		if (index == ticketList.length) {
			System.out.println("This seat is available\n");
		} else {
			System.out.println("Sorry this seat is reserved");
			System.out.println("----------------------------------");
			ticketList[index].print_ticketInfo();
			System.out.println("----------------------------------\n");
		}
	}
	
	
	public static void main(String[] args) {
		
		int[][] planeSeates = new int[4][];  // raged seat array
		planeSeates[0] = new int[14];
		planeSeates[1] = new int[12];
		planeSeates[2] = new int[12];
		planeSeates[3] = new int[14];
		
		Ticket[] ticketList = new Ticket[52];  // Ticket array

		Scanner input = new Scanner(System.in);
		
		System.out.println("! Welcome to the Plane Management application ! \n");
		
		boolean status = true;
		while(status) {
		
			System.out.println("************************************************\n"
							 + "*                 MENU OPTIONS                 *\n"
							 + "************************************************\n"
							 + "    1) Buy a seat                               \n"
							 + "    2) Cancel a seat                            \n"
							 + "    3) Find first available seat                \n"
							 + "    4) Show seating plan                        \n"
							 + "    5) Print ticket information and total sales \n"
							 + "    6) Search ticket                            \n"
							 + "    0) Quit                                     \n"
							 + "************************************************\n");
		
		
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
				System.out.println("--Exited from the program--");
				break;	
			default:
				System.out.println("Invalid option number !\n");
			}
		}
		
	}
}
