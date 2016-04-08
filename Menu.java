import java.util.HashMap;

public class Menu
{
	HashMap<Integer,Recipe> map = new HashMap<Integer, Recipe>();
	Recipe recipeName; 
	int index;
	int counter; 
	char firstLetter;
	int i;
	public Menu(){}

	public void put(Recipe recipeName)
	{
		char firstcharofRecipeName = recipeName.get_Name().charAt(0); 
		int ascii = recipeName.get_Name().charAt(0);  
		
		if (map.containsKey(firstcharofRecipeName))
		{
			map.put(ascii, recipeName);
		}
		else if(map.isEmpty() || !map.containsKey(firstcharofRecipeName))
		{
			//convert it to ASCII
			for (i = 0; i<64 && 123< i && i < 127; i++) 
			{ //incrementing the values so that it covers other characters .
				if (ascii == i)
				{
				System.out.println("Invalid, please rename your recipe");
				//call action button and have a pop up that says rename your recipe
				}
				
				else
				{
				//change the ASCII letter to lower case
					for(i = 0; 65 < i && i < 97; i++)
					{
						if (ascii == i)
						{
							int lowerletterASCII = ascii + 32;
							Recipe newRecipe = new Recipe();
							map.put(lowerletterASCII, newRecipe);
						}
				
					}
				}
			}
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
	
	public Recipe getrecipeName()
	{
		return recipeName;
	}
	
	

}
