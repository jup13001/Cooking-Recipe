
import java.util.ArrayList;

public class Ingredients 
{
	ArrayList<String[]> listOf = new ArrayList<String[]>();
	
	public Ingredients() {}
	
	public void add_Ingredient(String userName, String userDescription)
	{ String[] input = {userName, userDescription};
	listOf.add(input); }
	
	public void delete_Ingredient(int userSelection)
	{ listOf.remove(userSelection);}
	
	public String get_Ingredient_Name(int userSelection)
	{ return listOf.get(userSelection)[0];}
	
	public String get_Ingredient_Description(int userSelection)
	{ return listOf.get(userSelection)[1];}
	
	public ArrayList<String[]> getAll_Ingredients()
	{ return listOf;}
	
	public void edit_Name (int userSelection, String userString)
	{ listOf.get(userSelection)[0]= userString;}
	
	public void edit_Description(int userSelection, String userString)
	{ listOf.get(userSelection)[1]= userString;}
	
	
	
}
