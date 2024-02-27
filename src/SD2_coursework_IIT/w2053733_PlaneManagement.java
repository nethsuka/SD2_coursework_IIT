package SD2_coursework_IIT;

import java.util.Scanner;

public class w2053733_PlaneManagement {
	
//	static Scanner input = new Scanner(System.in);
	
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
	
	public static void buy_ticket(String rowLetter,int seatNum,int[][] seatList) {
		if(rowLetter.equals("A") && seatList[0][seatNum-1]==0) {
			seatList[0][seatNum-1]=1;
			System.out.println("You have successfully booked the seat");
		}else if(rowLetter.equals("B") && seatList[1][seatNum-1]==0) {
			seatList[1][seatNum-1]=1;
			System.out.println("You have successfully booked the seat");
		}else if(rowLetter.equals("C") && seatList[2][seatNum-1]==0){
			seatList[2][seatNum-1]=1;
			System.out.println("You have successfully booked the seat");
		}else if(rowLetter.equals("D") && seatList[3][seatNum-1]==0){
			seatList[3][seatNum-1]=1;
			System.out.println("You have successfully booked the seat");
		}else {
			System.out.println("Sorry!, The seat has been already booked");
		}
	}
	
	public static void cancel_ticket(String cancelRowLetter,int cancelSeatNum,int[][] seatList) {
		if(cancelRowLetter.equals("A") && seatList[0][cancelSeatNum-1]==1) {
			seatList[0][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully");
		}else if(cancelRowLetter.equals("B") && seatList[1][cancelSeatNum-1]==1) {
			seatList[1][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully");
		}else if(cancelRowLetter.equals("C") && seatList[2][cancelSeatNum-1]==1){
			seatList[2][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully");
		}else if(cancelRowLetter.equals("D") && seatList[3][cancelSeatNum-1]==1){
			seatList[3][cancelSeatNum-1]=0;
			System.out.println("The seat has been cancled sucessfully");
		}else {
			System.out.println("Nothing to cancle,the seat haven't booked");
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
	
	
	public static void main(String[] args) {
		
		int[][] planeSeates = new int[4][];
		planeSeates[0] = new int[14];
		planeSeates[1] = new int[12];
		planeSeates[2] = new int[12];
		planeSeates[3] = new int[14];
		
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
					buy_ticket(validRowLetter, validSeatNum, planeSeates);
				}
				break;
			case 2:
				String cancelRowLetter = Enter_str("Enter row letter to cancel :");
				int cancelSeatNum = Enter_int("Enter seat number to cancel :");
				int val2 = isValidSeat(cancelRowLetter, cancelSeatNum);
				if(val2==1) {
					cancel_ticket(cancelRowLetter, cancelSeatNum, planeSeates);
				}
				break;
			case 3:
				find_first_available(planeSeates);
				break;
			case 4:
				show_seating_plan(planeSeates);
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
