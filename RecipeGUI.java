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
		JPanel firstScreen = new JPanel();
		JPanel selectedRecipe = new JPanel();

		mainPanel.add(firstScreen, "First Screen");
		mainPanel.add(selectRecipe, "Select Recipe");
		mainPanel.add(inputRecipe, "Input Recipe");
		mainPanel.add(selectedRecipe, "Selected Recipe");
		selectedRecipe.setLayout(null);

		JLabel lblrecipeName = new JLabel("(Recipe name)", SwingConstants.CENTER);
		lblrecipeName.setBounds(12, 13, 454, 30);
		selectedRecipe.add(lblrecipeName);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 43, 444, 8);
		selectedRecipe.add(separator_1);

		JLabel lbllistOfIngredients = new JLabel("(List of ingredients?)");
		lbllistOfIngredients.setBounds(22, 56, 444, 124);
		selectedRecipe.add(lbllistOfIngredients);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 178, 454, 13);
		selectedRecipe.add(separator_2);

		JLabel lbltheInstructions = new JLabel("(The instructions)");
		lbltheInstructions.setBounds(12, 193, 454, 222);
		selectedRecipe.add(lbltheInstructions);

		lblTimer = new JLabel("00:00", SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTimer.setBounds(154, 476, 134, 56);
		selectedRecipe.add(lblTimer);

		JButton btnStart = new JButton("Start?");
		btnStart.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				//Start timer --- get from other text boxes
				int cookTime = 500;
				Clock alarm =  new Clock(cookTime);
				timer = new Timer(1000, alarm);
				timer.start();

			}
		});
		btnStart.setBounds(369, 507, 97, 25);
		selectedRecipe.add(btnStart);

		firstScreen.setLayout(null);
		selectRecipe.setLayout(null);
		inputRecipe.setLayout(null);
		//TITLE 
		JLabel lblTitle = new JLabel("Cooking Program", SwingConstants.CENTER);
		lblTitle.setBounds(0, 5, 478, 29);
		lblTitle.setFont(lblTitle.getFont().deriveFont(24f));
		firstScreen.add(lblTitle);

		//BUTTONS
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
		btnSelectBack.setBounds(10, 13, 70, 24);
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

		JTextArea txtEnterIngr = new JTextArea();
		txtEnterIngr.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
				}
			}
		});
		txtEnterIngr.setBounds(12, 129, 454, 129);
		inputRecipe.add(txtEnterIngr);

		JLabel lblEnterInstructions = new JLabel("Enter Instructions");
		lblEnterInstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterInstructions.setBounds(146, 258, 161, 38);
		inputRecipe.add(lblEnterInstructions);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 296, 454, 184);
		inputRecipe.add(textArea);

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

		//OTHER STUFF
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Brownies");
		listModel.addElement("Chocolate cake");
		listModel.addElement("Vanilla cake");
		listModel.addElement("Other stuff");
		listModel.addElement("asas1");
		listModel.addElement("asas2");
		listModel.addElement("asas3");
		listModel.addElement("asas4");
		listModel.addElement("asas5");
		listModel.addElement("asas6");
		listModel.addElement("asas7");
		listModel.addElement("asas8");
		listModel.addElement("asas9");
		listModel.addElement("asas10");
		listModel.addElement("asas4");
		listModel.addElement("asas5");
		listModel.addElement("asas6");
		listModel.addElement("asas7");
		listModel.addElement("asas8");
		listModel.addElement("asas9");
		listModel.addElement("asas10");

		//On press of a list, we should be able to "go to" that specific recipe
		JList list = new JList(listModel);
		// Double click
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
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
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 90, 456, 442);
		selectRecipe.add(scrollPane,BorderLayout.CENTER);

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
}