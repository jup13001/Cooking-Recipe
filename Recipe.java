public class Recipe 
{
	private String name = "";
	private Ingredients recipe_Ingredients = new Ingredients();
	private Instructions recipe_Instructions = new Instructions();
	
	public Recipe(){};
	
	public Recipe(String userName)
	{name = userName;}
	
	public Instructions get_Instructions(){ return recipe_Instructions;}
	public Ingredients get_Ingredients() { return recipe_Ingredients;}
	public String get_Name(){ return name; }
	
	public void edit_Name(String userString){ name = userString;} 
	
	
	
}
