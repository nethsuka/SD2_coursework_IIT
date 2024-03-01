package SD2_coursework_IIT;

import java.util.Scanner;

public class w2053733_PlaneManagement {
	
	static Scanner input = new Scanner(System.in);

	
	public static String Enter_str(String txt) {
		Scanner strObj = new Scanner(System.in);
		while(true) {
			try {
				String[] rowLetterList = {"A","B","C","D"};
				System.out.print(txt);
				String letter = strObj.next().toUpperCase();
				for(String item : rowLetterList) {
					if(item.equals(letter)) {
						return letter;
					}
				}
				System.out.println("Please enter a valid letter");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public static int Enter_int(String txt) {
		while(true) {
			try {
				Scanner intObj = new Scanner(System.in);
					System.out.print(txt);
					int num = intObj.nextInt();
					return num;
			}catch(Exception e) {
				System.out.println("Please enter an intiger");
			}
		}
	}
	
	public static int isValidSeat(String rowLetter,int seatNum) {
		if((rowLetter.equals("A") || rowLetter.equals("D")) && 0<seatNum && seatNum<=14) {
			return 1;
		}else if((rowLetter.equals("B") || rowLetter.equals("C")) && 0<seatNum && seatNum<=12) {
			return 1;
		}else {
			System.out.println("invalid seat possition!");
			return 0;
		}
	}
	
	public static void buy_seat(String rowLetter,int seatNum,int[][] seatList,Ticket[][] ticketList) {
		
		int row;
		if(rowLetter.equals("A") && seatList[0][seatNum-1]==0) {
			seatList[0][seatNum-1]=1;
			row = 0;
			System.out.println("You have successfully booked the seat");
		}else if(rowLetter.equals("B") && seatList[1][seatNum-1]==0) {
			seatList[1][seatNum-1]=1;
			row = 1;
			System.out.println("You have successfully booked the seat");
		}else if(rowLetter.equals("C") && seatList[2][seatNum-1]==0){
			seatList[2][seatNum-1]=1;
			row = 2;
			System.out.println("You have successfully booked the seat");
		}else if(rowLetter.equals("D") && seatList[3][seatNum-1]==0){
			seatList[3][seatNum-1]=1;
			row = 3;
			System.out.println("You have successfully booked the seat");
		}else {
			System.out.println("Sorry!, The seat has been already booked");
			return;
		}
		
		System.out.print("Enter person's name :");
		String name = input.next();
		System.out.print("Enter person's surname :");
		String surname = input.next();
		System.out.print("Enter person's Email :");
		String email = input.next();
		
		Person person = new Person(name, surname, email);
		int price;
		if(seatNum-1<=5) {
			price = 200;
		}else if(seatNum-1<=9) {
			price = 150;
		}else {
			price = 180;
		}
		
		Ticket ticket = new Ticket(rowLetter, seatNum, price, person);
		ticketList[row][seatNum-1] = ticket;
		
	}
	
	public static void cancel_seat(String cancelRowLetter,int cancelSeatNum,int[][] seatList,Ticket[][] ticketList) {
		
		int row;
		if(cancelRowLetter.equals("A") && seatList[0][cancelSeatNum-1]==1) {
			seatList[0][cancelSeatNum-1]=0;
			row = 0;
			System.out.println("The seat has been cancled sucessfully");
		}else if(cancelRowLetter.equals("B") && seatList[1][cancelSeatNum-1]==1) {
			seatList[1][cancelSeatNum-1]=0;
			row = 1;
			System.out.println("The seat has been cancled sucessfully");
		}else if(cancelRowLetter.equals("C") && seatList[2][cancelSeatNum-1]==1){
			seatList[2][cancelSeatNum-1]=0;
			row = 2;
			System.out.println("The seat has been cancled sucessfully");
		}else if(cancelRowLetter.equals("D") && seatList[3][cancelSeatNum-1]==1){
			seatList[3][cancelSeatNum-1]=0;
			row = 3;
			System.out.println("The seat has been cancled sucessfully");
		}else {
			System.out.println("Nothing to cancle,the seats haven't booked");
			return;
		}
		
		for(Ticket[] ticket_row : ticketList) {
			for(Ticket ticket_seat : ticket_row) {
				if(ticket_seat != null) {
					if(cancelRowLetter.equals(ticket_seat.getRow()) && ticket_seat.getSeat() == cancelSeatNum) {
						ticketList[row][cancelSeatNum-1] = null;
					}
				}
			}
		}
		
	}
	
	public static void find_first_available(int[][] seatList) {
		int charNum=65;
		int b=1;
		for(int[] seatrow: seatList) {
			for(int seat : seatrow) {
				char charcter = (char) charNum;
				if(seat == 0) {
					System.out.println("The first available seat is row:"+charcter+" seat:"+b);
					return;
				}
				b+=1;
			}
			charNum+=1;
		}
	}
	
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
	
	public static void print_tickets_info(Ticket[][] ticketList) {
		int sumOfTicketPrice = 0;
		for(Ticket[] ticket_row : ticketList) {
			for(Ticket ticket_seat : ticket_row) {
				if(ticket_seat != null) {
					ticket_seat.print_ticketInfo();
					sumOfTicketPrice += ticket_seat.getPrice();
					System.out.println("----------------------------------");
				}
			}
		}
		if(sumOfTicketPrice==0) {
			System.out.println("There is no ticket information to display.");
		}else {
			System.out.println("Total price of tickets :£"+sumOfTicketPrice);
		}
	}
	
	public static void search_ticket(String rowLetter, int seatNum,Ticket[][] ticketList) {
		for(Ticket[] ticket_row : ticketList) {
			for(Ticket ticket_seat : ticket_row) {
				if(ticket_seat != null) {
					if(rowLetter.equals(ticket_seat.getRow()) && ticket_seat.getSeat() == seatNum) {
						System.out.println("----------------------------------");
						ticket_seat.print_ticketInfo();
						System.out.println("----------------------------------");
						return;
					}else {
						System.out.println("This seat is available");
						return;
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		int[][] planeSeates = new int[4][];
		planeSeates[0] = new int[14];
		planeSeates[1] = new int[12];
		planeSeates[2] = new int[12];
		planeSeates[3] = new int[14];
		
		Ticket[][] ticketList = new Ticket[4][];
		ticketList[0] = new Ticket[14];
		ticketList[1] = new Ticket[12];
		ticketList[2] = new Ticket[12];
		ticketList[3] = new Ticket[14];
		
		System.out.println("! Welcome to the Plane Management application ! \n");
		
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
		
//		Scanner input = new Scanner(System.in);
		boolean status = true;
		while(status) {
		
			int validOption = Enter_int("Please enter an option :");
			
			switch(validOption) {
			case 1:
				String validRowLetter = Enter_str("Enter row letter :");
				int validSeatNum = Enter_int("Enter seat number :");
				int val = isValidSeat(validRowLetter, validSeatNum);
				System.out.println(val);
				if(val==1) {
					buy_seat(validRowLetter, validSeatNum, planeSeates, ticketList);
				}
				break;
			case 2:
				String cancelRowLetter = Enter_str("Enter row letter to cancel :");
				int cancelSeatNum = Enter_int("Enter seat number to cancel :");
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
				String searchRowLetter = Enter_str("Enter row letter :");
				int searchSeatNum = Enter_int("Enter seat number :");
				search_ticket(searchRowLetter, searchSeatNum, ticketList);
				break;
			case 0:
				status = false;
				break;	
			default:
				System.out.println("Pls enter a valid option number !");
			}
		}
		
	}
}
