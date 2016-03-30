import java.util.ArrayList;

public class Instructions {
	
	ArrayList<String> listOf = new ArrayList<String>();
	
	public Instructions() 
	{}
	
	public void add_Instruction(String userInstruction)
	{ listOf.add(userInstruction); }
	
	public void delete_Instruction(int userSelection)
	{ listOf.remove(userSelection);}
	
	public String get_Instruction(int userSelection)
	{ return listOf.get(userSelection);}
	
	public ArrayList<String> getAll_Ingredients()
	{ return listOf;}
	
	public void edit_Instruction(int userSelection, String userNewInstruction)
	{ listOf.set(userSelection, userNewInstruction);}
}
