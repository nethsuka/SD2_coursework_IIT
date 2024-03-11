package SD2_coursework_IIT;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class W2053733_PlaneManagementTest {

	@Test
	void testEnter_int() {
		Scanner input = new Scanner(System.in);
		assertEquals(4, W2053733_PlaneManagement.Enter_int("Enter seat number to cancel :", input));
	}

}
