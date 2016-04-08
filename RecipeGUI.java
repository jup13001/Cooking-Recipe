import java.awt.*;
import javax.swing.JFrame;
import java.awt.GridLayout;
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
import java.util.HashMap;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import javax.swing.AbstractAction;
import javax.swing.Action;


public class RecipeGUI {
	CardLayout cardLayout;
	JPanel mainPanel;
	public int time;
	public Timer timer;
	private JFrame frame;
	private JTextField textField;
	private JTextField txtHours;
	private JTextField txtMins;
	private JLabel lblTimer;
	private JTextField ingredients_textField;
	private JTextField instructions_textField;
	private final Action action = new SwingAction();
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
		frame.setBounds(100, 100, 496, 592);
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

		mainPanel.add(firstScreen, "First Screen");
		mainPanel.add(selectRecipe, "Select Recipe");
		mainPanel.add(inputRecipe, "Input Recipe");
		mainPanel.add(selectedRecipe, "Selected Recipe");
		selectedRecipe.setLayout(null);

		JLabel lblrecipeName = new JLabel("(Recipe name)", SwingConstants.CENTER);
		lblrecipeName.setBounds(79, 33, 325, 30);
		selectedRecipe.add(lblrecipeName);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 68, 444, 8);
		selectedRecipe.add(separator_1);

		JLabel lbllistOfIngredients = new JLabel("(List of ingredients?)");
		lbllistOfIngredients.setBounds(12, 69, 237, 111);
		selectedRecipe.add(lbllistOfIngredients);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 178, 454, 13);
		selectedRecipe.add(separator_2);

		JLabel lbltheInstructions = new JLabel("(The instructions)");
		lbltheInstructions.setBounds(12, 193, 454, 222);
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
		
		JButton btnEndTimer = new JButton("Reset Timer"); //Need to figure out how to actually just "Stop/resume" the timer
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
		DefaultListModel listModel = new DefaultListModel();
		
		JButton removeSelectedRecipe = new JButton("Remove Recipe");
		removeSelectedRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.removeElement(lblrecipeName.getText());
				cardLayout.show(mainPanel, "Select Recipe");
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
		textField.setBounds(158, 53, 282, 29);
		inputRecipe.add(textField);
		textField.setColumns(10);

		JLabel lblRecName = new JLabel("Recipe name:");
		lblRecName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRecName.setBounds(22, 50, 150, 29);
		inputRecipe.add(lblRecName);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 95, 454, 9);
		inputRecipe.add(separator);

		JLabel lblEnterIngredients = new JLabel("Enter Ingredients");
		lblEnterIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterIngredients.setBounds(146, 95, 161, 38);
		inputRecipe.add(lblEnterIngredients);

		JLabel lblEnterInstructions = new JLabel("Enter Instructions");
		lblEnterInstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterInstructions.setBounds(146, 258, 161, 38);
		inputRecipe.add(lblEnterInstructions);

		JLabel lblGetTimer = new JLabel("Time:");
		lblGetTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGetTimer.setBounds(22, 493, 70, 29);
		inputRecipe.add(lblGetTimer);

		txtHours = new JTextField();
		txtHours.setToolTipText("hours");
		txtHours.setColumns(10);
		txtHours.setBounds(88, 493, 56, 29);
		inputRecipe.add(txtHours);

		txtMins = new JTextField();
		txtMins.setToolTipText("mins");
		txtMins.setColumns(10);
		txtMins.setBounds(184, 493, 56, 29);
		inputRecipe.add(txtMins);

		JLabel lblHrs = new JLabel("hours");
		lblHrs.setBounds(146, 493, 37, 29);
		inputRecipe.add(lblHrs);

		JLabel lblMinutes = new JLabel("minutes");
		lblMinutes.setBounds(240, 493, 50, 29);
		inputRecipe.add(lblMinutes);
		
		
		
		JButton Done = new JButton("Done");
		Done.setBounds(339, 493, 127, 29);
		inputRecipe.add(Done);
		
		Done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//Enter what happens when user is finished entering their recipe
				String recipeName = textField.getText();
				Recipe newRecipeName;
				JFrame parent = new JFrame();
				String warningMessage =  "Oops! \nLooks like there is already an exisiting recipe with that name! \nPlease rename your recipe";
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
					JOptionPane.showMessageDialog(parent, warningMessage);
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
				
			}}
		)
			;
		
		
		ingredients_textField = new JTextField();
		ingredients_textField.setBounds(12, 132, 454, 115);
		inputRecipe.add(ingredients_textField);
		ingredients_textField.setColumns(10);
		
		instructions_textField = new JTextField();
		instructions_textField.setBounds(12, 307, 454, 140);
		inputRecipe.add(instructions_textField);
		instructions_textField.setColumns(10);
		
		
		

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
				//Put info from the selected recipe into the fields on this panel
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