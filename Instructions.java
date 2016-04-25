import java.util.ArrayList;

public class Instructions {
	private ArrayList<Object[]> listOf = new ArrayList<Object[]>();
	
	public Instructions() 
	{}
	
	public void add_Instruction(String userInstruction, Stopwatch userMinutes, Stopwatch userHours)
	{ Object[] new_Instruction = new Object[3];
	new_Instruction[0] = userInstruction;
	new_Instruction[1] = userMinutes;
	new_Instruction[2] = userHours; 
	listOf.add(new_Instruction);}
	
	public void delete_Instruction(int userSelection)
	{ listOf.remove(userSelection);}
	
	public Object[] get_Instruction(int userSelection)
	{ return listOf.get(userSelection);}
	
	public ArrayList<Object[]> getAll_Instruction()
	{ return listOf;}
	
	//public void edit_Instruction(int userSelection)
	//{ }
}