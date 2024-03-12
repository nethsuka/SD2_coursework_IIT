package SD2_coursework_IIT;


/**
 * This class represent the Person
 */
public class Person {
	
	private String name;
	private String surname;
	private String email;
	
	public Person(String name, String surname, String email) { // constructor 
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void print_personInfo() {     // print person's information
		System.out.println("Name :"+name);
		System.out.println("Surname :"+surname);
		System.out.println("Email :"+email);
	}
	
}
