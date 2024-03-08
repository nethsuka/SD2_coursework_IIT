package SD2_coursework_IIT;

import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

	private String row;
	private int seat;
	private int price;
	private Person person;

	public Ticket(String row, int seat, int price, Person person) {
		this.row = row;
		this.seat = seat;
		this.price = price;
		this.person = person;
	}
	
	public String getRow() {
		return row;
	}
	
	public void setRow(String row) {
		this.row = row;
	}
	
	public int getSeat() {
		return seat;
	}
	
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void print_ticketInfo() {
		System.out.println("Row :"+row);
		System.out.println("Seat :"+seat);
		System.out.println("Price :£"+price);
		person.print_personInfo();

	}
	
	public void save(String rowLetter, int seatNum, Person person) {
		try {
			FileWriter file = new FileWriter(rowLetter+seatNum+".txt");
			file.write("Row :"+row
					  +"\nSeat :"+seat
					  +"\nPrice :"+price
					  +"\nName :"+person.getName()
					  +"\nSurname :"+person.getSurname()
					  +"\nEmail :"+person.getEmail());
			file.close();
		}catch(IOException e) {
			System.out.println("An error occured");
		}
		
		
	}
}
