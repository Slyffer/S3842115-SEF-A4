import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.IOException;

//Creates these Variables
public class Houses {
	private String houseID;
	private String houseAddress;
	private String houseType;
	private double housePrice;
	private int numBedrooms;
	private int numBathrooms;
	private int numCarspace;
	private double houseSize;  //Assume this is input as Meters Squared already
	private String description;
	

	//This creates a class/Object with these details and these names
	public Houses (String id, String address, String type, double price, int bedrooms, int bathrooms, int carSpace, double size, String houseDescription) {
		houseID = id;
		houseAddress = address;
		houseType = type;
		housePrice = price;
		numBedrooms = bedrooms;
		numBathrooms = bathrooms;
		numCarspace = carSpace;
		houseSize = size;
		description = houseDescription; 
		
	}
	
	
	
	//This function makes sure all details/variables are accurate and within restrictions
	public boolean AddHouse() {
		//Calls the ID check function to make sure its valid if valid returns true if not false
		if (IdCheck(houseID) != true) { //if not valid ends here
			System.out.println("does not match");
			return false; 
		}
		
		//Counts the number of words in the string and returns them as an INT
		int houseDescCount = countWordSize(description);
		//If the counted words is more than 10 or less than 5 it returns false
		if (houseDescCount > 10 || houseDescCount < 5) {
			return false;
		}
		
		//makes sure if bedrooms are more than 4 AND bathrooms less than 3 returns false
		if (numBedrooms > 4 && numBathrooms < 3) {
			return false;
		}
		
		//if size is less than 50 AND price is greater than 350000 returns false
		if (houseSize < 50 && housePrice > 350000) {
			return false;
		}
		
		//if its an Appartment AND doesnt have a car space returns error
		if (houseType == "Appartment" && numCarspace < 1) {
			return false;
		}
		
		//if its a house AND the price is less than 100000 OR price is greater the 1500000 return error
		if (houseType == "House" && (housePrice < 100000 || housePrice > 1500000)) {
			return false;
		}
		
		//if its a house and the house price is more than 750000 and bedrooms are less than three and bath rooms less than 2 retrun false
		if (houseType == "House" && (housePrice > 750000) && (numBedrooms < 3) && (numBathrooms < 2)) {
			return false;
		}
		
		//if all pass
		else {
			//this turns all non string values to strings
			String HP = String.valueOf(housePrice);
			String Nbed = String.valueOf(numBedrooms);
			String Nbath = String.valueOf(numBathrooms);
			String Hcar = String.valueOf(numCarspace);
			String HS = String.valueOf(houseSize);

			
			
			//this turns all the now set strings into an array
			String[] Submit_Array = {houseID, houseAddress, houseType, HP, Nbed, Nbath, Hcar, HS, description};
			
			//This turns the array to a string seperated by commas
			String SubmitString = String.join(",", Submit_Array);
			
			//Trys submitting the string to a text file
			try {
				//Submits to text file in src 
				String FileName = "src\\HouseData.txt"; 
				FileWriter fw = new FileWriter(FileName, true);
				fw.write(SubmitString);
				fw.write("\n");
				fw.close();
			}
			//if cant print throws error
			catch(IOException ioe){
				System.err.println("IOException: " + ioe.getMessage());
				
			}
		
		//if gets to this point everything worked so return true
		return true;
		
		}
	}
	
	
	//This updates a houses details - Enter the desired details you wish to change them too
	public boolean updateHouse(String newAddress, String newType, double newPrice, int newBedrooms, int newBathrooms, int newCarSpace, double newSize, String newDescripion) {
		
		//COunts number of words in description Calls the ID check function to make sure its valid if valid returns true if not false
		int houseDescCount = countWordSize(newDescripion);
		if (houseDescCount > 10 || houseDescCount < 5) {
			return false;
		}
		
		//Counts the number of words in the string and returns them as an INT
		if (newBathrooms > 4 && newBathrooms < 3) {
			return false;
		}
		
		//if size is less than 50 AND price is over 350000 return false
		if (newSize < 50 && newSize > 350000) {
			System.out.println("size");
			return false;
		}
		
		//if apartment and less than 1 car space return error
		if (newType == "Appartment" && newCarSpace < 1) {
			return false;
		}
		
		//if a house and new price is more less than 100000 or more than 1500000 return error
		if (newType == "House" && (newPrice < 100000 || newPrice > 1500000)) {
			return false;
		}
		
		//if its a house and the house price is more than 750000 and bedrooms are less than three and bath rooms less than 2 retrun false
		if (newType == "House" && newPrice > 750000 && newBedrooms < 3 && newBathrooms < 2) {
			return false;
		}
		
		//if the bedrooms are less than 3 and the new price is more the 10% larger than old price return error
		if (newBedrooms < 3 && newPrice > (housePrice * 1.1)) {
			return false;
		}
		
		//if the new price is more than 20% larger than old price return false
		if (newPrice > (housePrice * 1.2)) {
			return false;
		}
		
		//if new not a townhouse new address must be the same as old address
		if (newType != "Townhouse" && (newAddress != houseAddress)) {
			return false;
		}
		
		//new if new size is larger
		if (newSize > houseSize) {
			
			//if the new size is less than 5% larger retrun false
			if (newSize < (houseSize * 1.05)) {
				return false;
				
			}
			
			//if new size is 10% larger return error
			if (newSize > (houseSize * 1.1)) {
				return false;
			}
		}
		
		//convert non string values to string
		String HP = String.valueOf(newPrice);
		String Nbed = String.valueOf(newBedrooms);
		String Nbath = String.valueOf(newBathrooms);
		String Hcar = String.valueOf(newCarSpace);
		String HS = String.valueOf(newSize);

		//add all now set string values to array
		String[] Submit_Array = {houseID, newAddress, newType, HP, Nbed, Nbath, Hcar, HS, newDescripion};
		
		//turn array to CSV via turning array to string with commas between values
		String SubmitString = String.join(",", Submit_Array);
		
		//try to send to file
		try {
			String FileName = "src\\HouseData.txt"; 
			FileWriter fw = new FileWriter(FileName, true);
			fw.write(SubmitString);
			fw.write("\n");
			fw.close();
		}
		//if cant error
		catch(IOException ioe){
			System.err.println("IOException: " + ioe.getMessage());
			
		}
	//if all statements are true return true
	return true;
		
		
	}
	
	
	//this counts the number of words in a string
	public int countWordSize (String house_description) {
		//gets string and tokenizes it
		StringTokenizer stringTokenizer1 = new StringTokenizer(house_description);
		
		//Returns the count/number of words as an int
		return stringTokenizer1.countTokens();
	}
	
	
	//Makes sure the ID is matched pattern
	public boolean IdCheck (String matched_Info) {
		//takes inputted string and matches to Regex below
		String my_pattern = "^[A-Z]{3}[1-9]{7}$";
		Pattern r = Pattern.compile(my_pattern);
		
		
		Matcher m = r.matcher(matched_Info);
		
		if (m.find()) { //if found/valid return true
			return true;
		}
		else return false; //if not found/valid return false
		
	}
	
	
	
}
