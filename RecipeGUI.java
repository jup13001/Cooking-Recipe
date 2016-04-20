import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.border.MatteBorder;
import javax.swing.JTextPane;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;


public class RecipeGUI {
	CardLayout cardLayout;
	JPanel mainPanel;
	public int time;
	public Timer timer;
	private JFrame frame;
	private JTextField textField;
	private JLabel lblTimer;
	private final Action action = new SwingAction();
	private JTable table;
	private JTable ingredientsTable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeGUI window = new RecipeGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecipeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 621);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		mainPanel = new JPanel(new CardLayout());
		frame.getContentPane().add(mainPanel, "Main Panel");
		cardLayout = (CardLayout) mainPanel.getLayout();
		//PANELS
		JPanel selectRecipe = new JPanel();
		JPanel inputRecipe = new JPanel();	
		inputRecipe.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel firstScreen = new JPanel();
		JPanel selectedRecipe = new JPanel();
		
		DefaultListModel listModel = new DefaultListModel();
		DefaultListModel instructionListModel = new DefaultListModel();
		DefaultListModel ingredientsListModel = new DefaultListModel();

		mainPanel.add(firstScreen, "First Screen");
		mainPanel.add(selectRecipe, "Select Recipe");
		mainPanel.add(inputRecipe, "Input Recipe");
		mainPanel.add(selectedRecipe, "Selected Recipe");
		selectedRecipe.setLayout(null);

		
	

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 68, 444, 8);
		selectedRecipe.add(separator_1);

		JLabel lbllistOfIngredients = new JLabel("Ingredients");
		lbllistOfIngredients.setBounds(205, 68, 89, 43);
		selectedRecipe.add(lbllistOfIngredients);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 208, 454, 13);
		selectedRecipe.add(separator_2);

		JLabel lbltheInstructions = new JLabel("Instructions");
		lbltheInstructions.setBounds(205, 219, 89, 37);
		selectedRecipe.add(lbltheInstructions);

		lblTimer = new JLabel("00:00", SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTimer.setBounds(180, 476, 134, 56);
		selectedRecipe.add(lblTimer);

		
		JButton btnSelectedBack = new JButton("Back");
		btnSelectedBack.setBounds(12, 13, 70, 24);
		btnSelectedBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "First Screen");
			}
		});
		selectedRecipe.add(btnSelectedBack);
		
		JButton btnStartTimer = new JButton("Start Timer");
		btnStartTimer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				//Start timer --- get from other text boxes
				time = 10;
				Clock alarm =  new Clock(time);
				timer = new Timer(1000, alarm);
				timer.start();

			}
		});
		btnStartTimer.setBounds(356, 476, 110, 25);
		selectedRecipe.add(btnStartTimer);
		
		JButton btnEndTimer = new JButton("End Timer"); //Need to figure out how to actually just "Stop/resume" the timer
		btnEndTimer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				//End Timer
				timer.stop();
			}
		});
		btnEndTimer.setBounds(356, 507, 110, 25);
		selectedRecipe.add(btnEndTimer);
		
		JList list_1 = new JList();
		list_1.setBounds(12, 334, 1, 1);
		selectedRecipe.add(list_1);
		
		//list model is the list of recipes in the menu
	
		
		JButton removeSelectedRecipe = new JButton("Remove Recipe");
		removeSelectedRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		removeSelectedRecipe.setBounds(12, 507, 126, 25);
		selectedRecipe.add(removeSelectedRecipe);
		
		JButton editSelectedRecipe = new JButton("Edit Recipe");
		editSelectedRecipe.setBounds(12, 476, 126, 23);
		selectedRecipe.add(editSelectedRecipe);
		

		firstScreen.setLayout(null);
		selectRecipe.setLayout(null);
		inputRecipe.setLayout(null);
		//TITLE 
		JLabel lblTitle = new JLabel("Cooking Program", SwingConstants.CENTER);
		lblTitle.setBounds(0, 70, 478, 29);
		lblTitle.setFont(lblTitle.getFont().deriveFont(24f));
		firstScreen.add(lblTitle);

		JButton btnSelectRecipe = new JButton("Select Recipe");
		btnSelectRecipe.setBounds(180, 259, 116, 40);
		btnSelectRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Select Recipe");
			}
		});
		firstScreen.add(btnSelectRecipe);

		JButton btnInputRecipe = new JButton("Input Recipe");
		btnInputRecipe.setBounds(180, 349, 116, 40);
		btnInputRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Input Recipe");
			}
		});
		firstScreen.add(btnInputRecipe);	

		JButton btnSelectBack = new JButton("Back");
		btnSelectBack.setBounds(10, 13, 109, 24);
		btnSelectBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "First Screen");
			}
		});
		selectRecipe.add(btnSelectBack);

		JButton btnInputBack = new JButton("Back");
		btnInputBack.setBounds(12, 13, 70, 24);
		btnInputBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "First Screen");
			}
		});
		inputRecipe.add(btnInputBack);

		textField = new JTextField();
		textField.setBounds(203, 54, 282, 29);
		inputRecipe.add(textField);
		textField.setColumns(10);

		JLabel lblRecName = new JLabel("Recipe name:");
		lblRecName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRecName.setBounds(54, 54, 150, 29);
		inputRecipe.add(lblRecName);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 95, 580, 9);
		inputRecipe.add(separator);

		JLabel lblEnterIngredients = new JLabel("Enter Ingredients");
		lblEnterIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterIngredients.setBounds(213, 94, 161, 38);
		inputRecipe.add(lblEnterIngredients);

		JLabel lblEnterInstructions = new JLabel("Enter Instructions");
		lblEnterInstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterInstructions.setBounds(215, 257, 161, 38);
		inputRecipe.add(lblEnterInstructions);

		JLabel lblGetTimer = new JLabel("Overall Time:");
		lblGetTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGetTimer.setBounds(10, 533, 128, 38);
		inputRecipe.add(lblGetTimer);

		JLabel lblHrs = new JLabel("hours");
		lblHrs.setBounds(213, 542, 37, 29);
		inputRecipe.add(lblHrs);

		JLabel lblMinutes = new JLabel("minutes");
		lblMinutes.setBounds(340, 542, 50, 29);
		inputRecipe.add(lblMinutes);
		
		DefaultTableModel ingredients = new DefaultTableModel();
		ingredientsTable = new JTable();
		table = new JTable();
		DefaultTableModel instructions = new DefaultTableModel();
		
		
		
		JButton Done = new JButton("Done");
		Done.setBounds(465, 542, 127, 29);
		inputRecipe.add(Done);
		
		Done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//Enter what happens when user is finished entering their recipe
				String recipeName = textField.getText();
				Recipe newRecipeName;
				JFrame parent = new JFrame();
				String warningMessage =  "Oops! \nLooks like there is already an exisiting recipe with that name! \nPlease rename your recipe";
				String emptywarningMessage = "Please insert a recipe name";
				
				
				System.out.println(ingredients);
				if (listModel.isEmpty())
				{
					newRecipeName = new Recipe(recipeName);
					listModel.addElement(newRecipeName.get_Name());
					
				}
				else if (listModel.contains(recipeName))
				{
				    JOptionPane.showMessageDialog(parent, warningMessage);
				    newRecipeName = new Recipe(recipeName);
				}
				else if (recipeName.startsWith(" ")) 
				{
					
					String editedRecipeName = recipeName.replaceFirst("^ *","");
					if (listModel.contains(editedRecipeName))
					{
						JOptionPane.showMessageDialog(parent, warningMessage);
						newRecipeName = new Recipe(editedRecipeName);
						listModel.addElement(newRecipeName.get_Name());
					}
					else
					{
						newRecipeName = new Recipe(editedRecipeName);
						listModel.addElement(newRecipeName.get_Name());
					}
				}
				else if (recipeName.isEmpty())
				{
					JOptionPane.showMessageDialog(parent, emptywarningMessage);
					if (listModel.contains(recipeName))
					{
						JOptionPane.showMessageDialog(parent, warningMessage);
						newRecipeName = new Recipe(recipeName);
						listModel.addElement(newRecipeName.get_Name());
					}
					else if (recipeName.startsWith(" "))
					{
						recipeName.replaceFirst("^ *", "");
						newRecipeName = new Recipe(recipeName);
						listModel.addElement(newRecipeName.get_Name());
					}
				}
				else
				{
					newRecipeName = new Recipe(recipeName);
					listModel.addElement(newRecipeName.get_Name());
					
				}	
				if(!recipeName.isEmpty() && ingredientsTable.getValueAt(0, 0) != null && table.getValueAt(0, 0) != null)
				{
				int messageType = JOptionPane.QUESTION_MESSAGE;
				String[] options = {"Menu", "Continue "};
				int code = JOptionPane.showOptionDialog(null, "Would you like to continue or return to main menu?", "Options", 0, messageType, null, options, "Menu");
				if (code == 0)
				{
					cardLayout.show(mainPanel, "First Screen");
				}
				else if(code == 1 )
				{
				frame.setVisible(false);
				recipeOut newpanel = new recipeOut();
				newpanel.NewScreen();
				
				
				
				}
				
				}
				
			}}
		)
			;
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 131, 580, 115);
		inputRecipe.add(scrollPane_1);
		
		
		ingredientsTable.setModel(ingredients);
			ingredients.setColumnCount(2);
				ingredients.setRowCount(5);
		scrollPane_1.setViewportView(ingredientsTable);
		ingredientsTable.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(104, 306, 384, 107);
		inputRecipe.add(scrollPane_2);
		
		
		table.setModel(instructions);
		scrollPane_2.setViewportView(table);
		table.setBackground(Color.WHITE);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		instructions.setColumnCount(3);
		instructions.setRowCount(5);
		table.getTableHeader().setReorderingAllowed(false);
  		
		JButton btnAddMoreFields = new JButton("Add More Fields");
		btnAddMoreFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String[] typeofObject = {"Ingredients", "Instructions"};
				 Integer[] amount = {1,2, 3, 4, 5, 6, 7, 8, 9, 10};
			        JComboBox combo = new JComboBox(typeofObject);
			        JComboBox combo2 = new JComboBox(amount);
			        JPanel panel = new JPanel(new GridLayout(0, 1));
			        panel.add(new JLabel("Type of Object"));
			        panel.add(combo);
			        panel.add(new JLabel("Number of Fields"));
			        panel.add(combo2);
			        int result = JOptionPane.showConfirmDialog(null, panel, "Add More Fields",
			            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			       
			        String resultofObject = combo.getSelectedItem().toString();
			        int resultofField = (int) combo2.getSelectedItem();
			      int current_row_of_ingredients = ingredients.getRowCount();
			      int current_row_of_instructions = instructions.getRowCount();
			      
			        if (resultofObject.equals("Ingredients"))
			        {
			        ingredients.setRowCount(current_row_of_ingredients + resultofField);
			        }
			        if (resultofObject.equals("Instructions"))
			        {
			        	instructions.setRowCount(current_row_of_instructions + resultofField);
			        }
			        
			}
		});
		btnAddMoreFields.setBounds(465, 493, 127, 29);
		inputRecipe.add(btnAddMoreFields);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setMaxWidth(42);
		table.getColumnModel().getColumn(2).setPreferredWidth(42);
		table.getColumnModel().getColumn(2).setMaxWidth(42);
		table.setBounds(12,331,580,163);
		
		
		
		JComboBox comboHours = new JComboBox(new Object[]{});
		comboHours.setModel(new DefaultComboBoxModel
				(new String[] {"0", "1", "2", "3", "4" ,"5" ,"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		comboHours.setSelectedIndex(0);
		comboHours.setMaximumRowCount(12);
		comboHours.setBounds(138, 541, 66, 28);
		inputRecipe.add(comboHours);
		 		
		 JComboBox comboMins = new JComboBox(new Object[]{});
		 comboMins.setModel(new DefaultComboBoxModel
				 (new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		 comboMins.setSelectedIndex(0);
		 comboMins.setMaximumRowCount(12);
		 comboMins.setBounds(262, 541, 66, 28);
		inputRecipe.add(comboMins);
		 		
		
		instructionListModel.addElement("Add sugar");
		ingredientsListModel.addElement("sugar");
		

		//OTHER STUFF
		
		listModel.addElement("Brownies");
		listModel.addElement("Chocolate cake");
		listModel.addElement("Vanilla cake");
		listModel.addElement("And");
		listModel.addElement("Other stuff");
		listModel.addElement("Vera");
		listModel.addElement("Julie");
		listModel.addElement("Steven");
		listModel.addElement("And");
		listModel.addElement("Quincy");
		listModel.addElement("Are");
		listModel.addElement("Working");
		listModel.addElement("On");
		listModel.addElement("A");
		listModel.addElement("Cooking");
		listModel.addElement("/");
		listModel.addElement("Recipe");
		listModel.addElement("Program");
		listModel.addElement("Thing");
		listModel.addElement("Lol");
		listModel.addElement("Idk");
		listModel.addElement("What I'm");
		listModel.addElement("Doing");
		listModel.addElement("(DONT ASK)");
		listModel.addElement("GG NO RE");
		// Double click
		int counter;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 456, 373);
		selectRecipe.add(scrollPane,BorderLayout.CENTER);
										
												//On press of a list, we should be able to "go to" that specific recipe
		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setForeground(Color.LIGHT_GRAY);
		list.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) 
			{
				cardLayout.show(mainPanel, "Selected Recipe");
				String recipeSelectedName = list.getSelectedValue().toString();
				Recipe recipe = new Recipe(recipeSelectedName);
				JLabel lblRecipeName = new JLabel(recipe.get_Name(), SwingConstants.CENTER);
				lblRecipeName.setBounds(79, 33, 325, 30);
				selectedRecipe.add(lblRecipeName);
				//JTable ingredients = new JTable(recipe.get_Ingredients().getAll_Ingredients(), SwingConstants.CENTER);
				//ingredients.setBounds(30, 70, 500, 700);
				//selectedRecipe.add(ingredients);
				
			}
		}
		});
												
		// Press enter
		list.addKeyListener(new KeyAdapter() {
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				cardLayout.show(mainPanel, "Selected Recipe");
				//Put info from the selected recipe into the fields on this panel
				
				 
				
			}
		}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setFont(list.getFont().deriveFont(20.f));
		list.setVisibleRowCount(10);
														
		JButton btnRemoveRecipeinList = new JButton("Remove Recipe");
		btnRemoveRecipeinList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedIndex =list.getSelectedIndex();
				String recipeSelected = list.getSelectedValue().toString();
				Recipe recipe = new Recipe();
				recipe.remove_Recipe(recipeSelected);
				if (selectedIndex != -1) {
				    listModel.remove(selectedIndex);
				    }
			}
		});
		btnRemoveRecipeinList.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{}
		});
		btnRemoveRecipeinList.setBounds(320, 11, 150, 29);
		selectRecipe.add(btnRemoveRecipeinList);

		
	}
	public class Clock implements ActionListener{
		public Clock(int numb){
			//Get text from textfield: time = (int)(Double.parseDouble(text.getText())
			time = numb;
		}
		public void actionPerformed(ActionEvent e) {
			time--;
			if(time >= 1)
				lblTimer.setText("Time left " + time);
			else{
				timer.stop();
				lblTimer.setText("DONE!!!!!");
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}