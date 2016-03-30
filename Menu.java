import java.util.HashMap;

public class Menu
{
	HashMap<Integer,Recipe> map = new HashMap<Integer, Recipe>();
	Recipe recipeName; 
	int index;
	int counter; 
	char firstLetter;
	int i;
	
	public Menu(Recipe input)
	{
		recipeName = input;
	}
	public void put(Recipe recipeName)
	{
		while (map.containsKey(recipeName.get_Name().charAt(0))) //if the map contains a key with the same first letter of the recipe name, put it in the map 
		{
			//change the charAt to int for all lower case 
			//Get the first letter of the Name in recipeName
			 
			int ascii = recipeName.get_Name().charAt(0); 
			switch(ascii)
			{//convert it to ASCII
			case 1: for (i = 0; i<64 && 123< i && i < 127; i++)  //incrementing the values so that it covers other characters .
			
				if (ascii == i)
				{
				System.out.println("Invalid, please rename your recipe");
				return;
				}
				break;
			//change the ASCII letter to lower case
			case 2: for(i = 0; 65 < i && i < 97; i++)
			
				if (ascii == i)
				{
					ascii = ascii + 32;
					//System.out.println(counter);
					//System.out.println(ascii);
					//System.out.println(recipeName.get_Name().charAt(0));
					//System.out.println(recipeName.get_Name());
				}
				break;
			}
			counter++;
			System.out.println(counter);
			System.out.println(ascii);
			System.out.println(recipeName.get_Name().charAt(0));
			System.out.println(recipeName.get_Name());
			map.put(ascii, recipeName); //put the (ASCII, recipeName) into the map where the ASCII is the key/placeholder and recipeName is the value. 
		}

	}
	
}
