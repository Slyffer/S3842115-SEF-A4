import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;

class TestHouseClass {

	@Test
	void testCaseAdd1() { //Test Valid Houses
		Houses House1 = new Houses("BCA1354597", "1 Ryan Street", "Townhouse", 609000, 4, 4, 2, 50, "it is a big house man");
		Houses House2 = new Houses("XYZ1234567", "52 Blake Street", "House", 700000, 4, 4, 1, 50, "it is a big house man");
		Houses House3 = new Houses("AAA1234567", "22 John Street", "Appartment", 500000, 4, 3, 3, 50, "it is a big house man");

		//tests if addHouse works
		assertAll ("", ()->assertEquals (true, 
				House1.AddHouse()), 
				    ()->assertEquals (true, 
				House2.AddHouse()), 
				    ()->assertEquals (true, 
				House3.AddHouse())); 	
	}
	
	
	@Test
	void testCaseAdd2() { // With invalid IDs
		Houses House1 = new Houses("NNN1110111", "52 Bob Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJ2222222", "52 Green Street", "Appartment", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("ppp4444444", "52 Jane Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");

		//tests if add house works
		assertAll ("", ()->assertEquals (false, 
				House1.AddHouse()), 
				    ()->assertEquals (false, 
				House2.AddHouse()), 
				    ()->assertEquals (false, 
				House3.AddHouse()));
	
	}
	@Test
	void testCaseAdd3() { //Adding with non valid decsription
		Houses House1 = new Houses("BCA1354597", "1 Ryan Street", "Townhouse", 609000, 4, 4, 2, 50, "This description for the house is way too ling and should result in an error");
		Houses House2 = new Houses("XYZ1234567", "52 Blake Street", "House", 700000, 4, 4, 1, 50, "Too short");
		Houses House3 = new Houses("AAA1234567", "22 John Street", "Appartment", 500000, 4, 3, 3, 50, "");

		//tests if addHouse works
		assertAll ("", ()->assertEquals (false, 
				House1.AddHouse()), 
				    ()->assertEquals (false, 
				House2.AddHouse()), 
				    ()->assertEquals (false, 
				House3.AddHouse())); 
		
		
	}
	@Test
	void testCaseAdd4() { //Adding an Invalid Sized House
		Houses House1 = new Houses("BCA1354597", "1 Ryan Street", "House", 609000, 4, 4, 2, 49.99, "it is a big house man");
		Houses House2 = new Houses("XYZ1234567", "52 Blake Street", "House", 700000, 4, 4, 1, 12, "it is a big house man");
		Houses House3 = new Houses("AAA1234567", "22 John Street", "House", 500000, 4, 3, 3, 30, "it is a big house man");

		//tests if addHouse works
		assertAll ("", ()->assertEquals (false, 
				House1.AddHouse()), 
				    ()->assertEquals (false, 
				House2.AddHouse()), 
				    ()->assertEquals (false, 
				House3.AddHouse())); 
		
	
	}
	
	@Test
	void testCaseAdd5() { //Testing invalid prices
		Houses House1 = new Houses("BCA1354597", "1 Ryan Street", "House", 99999, 4, 4, 2, 50, "it is a big house man");
		Houses House2 = new Houses("XYZ1234567", "52 Blake Street", "House", 1500001, 4, 4, 1, 50, "it is a big house man");
		Houses House3 = new Houses("AAA1234567", "22 John Street", "House", 2, 4, 3, 3, 50, "it is a big house man");

		//tests if addHouse works
		assertAll ("", ()->assertEquals (false, 
				House1.AddHouse()), 
				    ()->assertEquals (false, 
				House2.AddHouse()), 
				    ()->assertEquals (false, 
				House3.AddHouse())); 
		
	}
	
	@Test
	void testCaseAdd6() { //Adding house with price over 750000 with less than 3 bedrooms and less than 1 bathroom
		Houses House1 = new Houses("BCA1354597", "1 Ryan Street", "House", 800000, 2, 1, 2, 50, "it is a big house man");
		Houses House2 = new Houses("XYZ1234567", "52 Blake Street", "House", 1490000, 1, 1, 1, 50, "it is a big house man");
		Houses House3 = new Houses("AAA1234567", "22 John Street", "House", 1000000, 0, 1, 3, 50, "it is a big house man");

		//tests if addHouse works
		assertAll ("", ()->assertEquals (false, 
				House1.AddHouse()), 
				    ()->assertEquals (false, 
				House2.AddHouse()), 
				    ()->assertEquals (false, 
				House3.AddHouse())); 	
	}
	
	
	
	@Test
	void testCaseEdit1() { //editing values with valid values
		Houses House1 = new Houses("NNN1111111", "52 Bob Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJJ2222222", "52 Green Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("PPP4444444", "52 Jane Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		
		//removes the data file since i didnt know how to access and edit it
		File removeFile = new File("src\\HouseData.txt");
		if(removeFile.delete()) {
			System.out.println("Deleted the file: " + removeFile.getName());
		}
		else {
			System.out.println("No FIle was deleted");
		}
		
		//adds new houses then edits one then adds it too
		assertAll ("", ()->assertEquals (true, 
				House1.updateHouse("52 Bob Street", "House", 600000, 4, 9, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (true, 
				House2.updateHouse("52 Green Street", "House", 611111, 4, 5, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (true, 
				House3.updateHouse("52 Jane Street", "House", 620000, 4, 3, 3, 50, "This is a modified house now")));
	
	}
	
	@Test
	void testCaseEdit2() { //increasing price by more than 10% with less than 3 bedrooms
		Houses House1 = new Houses("NNN1111111", "52 Bob Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJJ2222222", "52 Green Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("PPP4444444", "52 Jane Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		
		//removes the data file since i didnt know how to access and edit it
		File removeFile = new File("src\\HouseData.txt");
		if(removeFile.delete()) {
			System.out.println("Deleted the file: " + removeFile.getName());
		}
		else {
			System.out.println("No FIle was deleted");
		}
		
		//adds new houses then edits one then adds it too
		assertAll ("", ()->assertEquals (false, 
				House1.updateHouse("52 Bob Street", "House", 700000, 2, 9, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House2.updateHouse("52 Green Street", "House", 670000, 1, 5, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House3.updateHouse("52 Jane Street", "House", 676000, 1, 3, 3, 50, "This is a modified house now")));
	
	}
	
	@Test
	void testCaseEdit3() { //increasing price by more than 20%
		Houses House1 = new Houses("NNN1111111", "52 Bob Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJJ2222222", "52 Green Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("PPP4444444", "52 Jane Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		
		//removes the data file since i didnt know how to access and edit it
		File removeFile = new File("src\\HouseData.txt");
		if(removeFile.delete()) {
			System.out.println("Deleted the file: " + removeFile.getName());
		}
		else {
			System.out.println("No FIle was deleted");
		}
		
		//adds new houses then edits one then adds it too
		assertAll ("", ()->assertEquals (false, 
				House1.updateHouse("52 Bob Street", "House", 750000, 4, 9, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House2.updateHouse("52 Green Street", "House", 800000, 4, 5, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House3.updateHouse("52 Jane Street", "House", 950000, 4, 3, 3, 50, "This is a modified house now")));
	
	}
	
	@Test
	void testCaseEdit4() { //editing townhouse address
		Houses House1 = new Houses("NNN1111111", "52 Bob Street", "Townhouse", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJJ2222222", "52 Green Street", "Townhouse", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("PPP4444444", "52 Jane Street", "Townhouse", 600000, 4, 4, 3, 50, "it is a big house man");
		
		//removes the data file since i didnt know how to access and edit it
		File removeFile = new File("src\\HouseData.txt");
		if(removeFile.delete()) {
			System.out.println("Deleted the file: " + removeFile.getName());
		}
		else {
			System.out.println("No FIle was deleted");
		}
		
		//adds new houses then edits one then adds it too
		assertAll ("", ()->assertEquals (true, 
				House1.updateHouse("52 This is changed Street", "Townhouse", 600000, 4, 9, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (true, 
				House2.updateHouse("52 this is edited Street", "Townhouse", 611111, 4, 5, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (true, 
				House3.updateHouse("52 not oringal name Street", "Townhouse", 620000, 4, 3, 3, 50, "This is a modified house now")));
	
	}
	
	@Test
	void testCaseEdit5() { //editing a non townhouse address
		Houses House1 = new Houses("NNN1111111", "52 Bob Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJJ2222222", "52 Green Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("PPP4444444", "52 Jane Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		
		//removes the data file since i didnt know how to access and edit it
		File removeFile = new File("src\\HouseData.txt");
		if(removeFile.delete()) {
			System.out.println("Deleted the file: " + removeFile.getName());
		}
		else {
			System.out.println("No FIle was deleted");
		}
		
		//adds new houses then edits one then adds it too
		assertAll ("", ()->assertEquals (false, 
				House1.updateHouse("52 should fail Street", "House", 600000, 4, 9, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House2.updateHouse("52 this is not correct Street", "House", 611111, 4, 5, 3, 50, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House3.updateHouse("52 error Street", "House", 620000, 4, 3, 3, 50, "This is a modified house now")));
	
	}

	
	@Test
	void testCaseEdit6() { //chanign size by less than 5% or more than 10%
		Houses House1 = new Houses("NNN1111111", "52 Bob Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House2 = new Houses("JJJ2222222", "52 Green Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		Houses House3 = new Houses("PPP4444444", "52 Jane Street", "House", 600000, 4, 4, 3, 50, "it is a big house man");
		
		//removes the data file since i didnt know how to access and edit it
		File removeFile = new File("src\\HouseData.txt");
		if(removeFile.delete()) {
			System.out.println("Deleted the file: " + removeFile.getName());
		}
		else {
			System.out.println("No FIle was deleted");
		}
		
		//adds new houses then edits one then adds it too
		assertAll ("", ()->assertEquals (false, 
				House1.updateHouse("52 Bob Street", "House", 600000, 4, 9, 3, 51, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House2.updateHouse("52 Green Street", "House", 611111, 4, 5, 3, 75, "This is a modified house now")), 
				    ()->assertEquals (false, 
				House3.updateHouse("52 Jane Street", "House", 620000, 4, 3, 3, 500, "This is a modified house now")));
	
	}
}
