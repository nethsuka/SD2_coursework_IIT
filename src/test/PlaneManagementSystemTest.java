package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import SD2_coursework_IIT.PlaneManagementSystem;


/**
 * This class represents the unit test cases for PlaneManagementSystem class
 */
class PlaneManagementSystemTest {

	Scanner input = new Scanner(System.in);
	
	@Test
	void test_Enter_intForSeatBooking() {
		// seat number = 4
		assertEquals(4, PlaneManagementSystem.Enter_int("Enter seat number :", input));
	}

	@Test
	void test_Enter_intForSeatCanseling() {
		// seat number = 4
		assertEquals(4, PlaneManagementSystem.Enter_int("Enter seat number to cancel :", input));
	}
	
	@Test
	void test_Enter_intForSearchTicket() {
		// seat number = 4
		assertEquals(4, PlaneManagementSystem.Enter_int("Enter seat number to search :", input));
	}
	
	@Test
	void test_Enter_strForSeatBooking() {
		// row letter = A
		// input characters are not case-sensitive
		assertEquals("A", PlaneManagementSystem.Enter_str("Enter row letter :", input));
	}

	@Test
	void test_Enter_strForSeatCanseling() {
		// row letter = B
		// input characters are not case-sensitive
		assertEquals("B", PlaneManagementSystem.Enter_str("Enter row letter to cancel :", input));
	}
	
	@Test
	void test_Enter_strForSearchTicket() {
		// row letter = C
		// input characters are not case-sensitive
		assertEquals("C", PlaneManagementSystem.Enter_str("Enter row letter to search :", input));
	}
	
	@Test
	void test_isValidSeatByCorrectInput() {
		/* row letter = C
		   valid seat number = 4
		   input characters are not case-sensitive*/
		assertEquals(1, PlaneManagementSystem.isValidSeat("C", 4));
	}
	
	@Test
	void test_isValidSeatByIncorrectInput() {
		/* row letter = C
		   invalid seat number = 26*/
		assertEquals(0, PlaneManagementSystem.isValidSeat("C", 26));
	}
	
}

