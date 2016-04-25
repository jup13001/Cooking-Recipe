import java.util.HashMap;

public class Menu
{
	private HashMap<Integer,Recipe[]> map = new HashMap<Integer, Recipe[]>();

	public Menu(){}

	/*
	 * Important: Method will only work if the invalid characters have already been checked
	 */
	public void putRecipe(Recipe recipeName)
	{
		char firstcharofRecipeName = recipeName.get_Name().toLowerCase().charAt(0); 
		int ascii = firstcharofRecipeName;
		
		if (map.containsKey(firstcharofRecipeName))
		{
			Recipe[] keyArray = map.get(firstcharofRecipeName);
			Recipe[] tempArray = new Recipe[keyArray.length + 1];
			
			for (int i= 0; i<keyArray.length ; i++) 
			{ tempArray[i] = keyArray[i];}
			
			tempArray[tempArray.length-1] = recipeName;
			
			map.put(ascii, tempArray);
		}
		else
		{
			Recipe[] arrayForNewKey = new Recipe[1];
			arrayForNewKey[0] = recipeName;
			map.put(ascii, arrayForNewKey);
		}	
	}
	
	public void remove(Recipe recipeName)
	{
		char firstcharofRecipeName = recipeName.get_Name().charAt(0); 
		int ascii = recipeName.get_Name().charAt(0);  
		
		if (map.containsKey(firstcharofRecipeName))
		{
			map.remove(ascii, recipeName);
		}
		else
			System.out.println("Does not exist");
	}
	
	public Recipe getRecipe(String recipeName){
		Recipe returnedRecipe = null;
		
		int checkForKey = recipeName.toLowerCase().charAt(0);
		if (map.containsKey(checkForKey)){
			Recipe[] keyArray = map.get(checkForKey); 
			for (int i = 0; i<keyArray.length; i++){
				if (keyArray[i].get_Name().equals(recipeName)){
					returnedRecipe = keyArray[i];
				}
			}
		}
		return returnedRecipe;
	}
}