
public class Main {
	public static void main(String[] args)
	{
		String input = "brownies";
		Recipe test = new Recipe(input);
		test.get_Ingredients().add_Ingredient("chocolate", "dark, 4 bars");
		test.get_Ingredients().add_Ingredient("milk", "2%, 1 cup");
		test.get_Ingredients().add_Ingredient("eggs", "white, 2 large, raw");
		System.out.println("The ingredients for " + test.get_Name() + " are:");
		
		for (int i=0; i<test.get_Ingredients().getAll_Ingredients().size(); i++)
		{System.out.println(test.get_Ingredients().get_Ingredient_Name(i)); }
	}

}

