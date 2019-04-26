//
// Name: Tran, Denise
// Homework: #4
// Due: Thursday April 25, 2019 
// Course: cs-2450-02-sp19
//
// Description:
// Implement the font viewer function in the Notepad application
//

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.PlainDocument;


public class Notepad implements ActionListener{
	JLabel jlab;
	JTextArea text = new JTextArea(10, 10);
	int i = 0;
    JTextPane textPane;
    int lineCount;
    static JFrame jfrm = new JFrame("Untitled Notepad");


    Notepad() {
        // Create a new JFrame container.   
        jfrm.setSize(940, 780);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label that will display the menu selection & menu bar
        jlab = new JLabel();
        JMenuBar jmb = new JMenuBar();
        
        
        JScrollPane jsp = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        // Set font
        Font font = new Font("Courier New", Font.PLAIN, 12);
        text.setFont(font);
        
        
        // Create the File menu. 
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiNew = new JMenuItem("New");
        JMenuItem jmiOpen = new JMenuItem("Open...");
        JMenuItem jmiSave = new JMenuItem("Save");
        JMenuItem jmiSaveAs = new JMenuItem("Save as...");
        JMenuItem jmiPgSetup = new JMenuItem("Page Setup...");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiPrint = new JMenuItem("Print...");
        jmFile.add(jmiNew);
        jmFile.add(jmiOpen);
        jmFile.add(jmiSave);
        jmFile.add(jmiSaveAs);
        jmFile.addSeparator();
        jmFile.add(jmiPgSetup);
        jmFile.add(jmiPrint);
        jmFile.addSeparator();
        jmFile.add(jmiExit);

        
        // Create menus
        JMenu jmEdit = new JMenu("Edit");
        JMenu jmOptions = new JMenu("Format");
        JMenu jmView = new JMenu("View");
        JMenu jmHelp = new JMenu("Help");
        
        // Create edit menu items
        JMenuItem jmiUndo = new JMenuItem("Undo");
        JMenuItem jmiCut = new JMenuItem("Cut");
        JMenuItem jmiCopy = new JMenuItem("Copy");
        JMenuItem jmiPaste = new JMenuItem("Paste");
        JMenuItem jmiDelete = new JMenuItem("Delete");
        JMenuItem jmiSearch = new JMenuItem("Search with Bing...");
        JMenuItem jmiFind = new JMenuItem("Find Next");
        JMenuItem jmiReplace = new JMenuItem("Replace...");
        JMenuItem jmiGoTo = new JMenuItem("Go To...");
        JMenuItem jmiSelect = new JMenuItem("Select All");
        JMenuItem jmiTime = new JMenuItem("Time/Date");
        jmEdit.add(jmiUndo);
        jmEdit.addSeparator();
        jmEdit.add(jmiCut);
        jmEdit.add(jmiCopy);
        jmEdit.add(jmiPaste);
        jmEdit.add(jmiDelete);
        jmEdit.addSeparator();
        jmEdit.add(jmiSearch);
        jmEdit.add(jmiFind);
        jmEdit.add(jmiReplace);
        jmEdit.add(jmiGoTo);
        jmEdit.addSeparator();
        jmEdit.add(jmiSelect);
        jmEdit.add(jmiTime);

        
        // Create view menu items.
        JMenuItem jmiStatusBar = new JMenuItem("Status Bar");
        JMenuItem jmiZoom = new JMenuItem("Zoom");
        jmView.add(jmiZoom);
        jmView.add(jmiStatusBar);

//        // Create the Reset menu item. 
//        JMenuItem jmiReset = new JMenuItem("Reset");
//        jmOptions.addSeparator();
//        jmOptions.add(jmiReset);
        
        // Create menu items for format
        JMenuItem jmiWordWrap = new JMenuItem("Word Wrap");
        JMenuItem jmiFont = new JMenuItem("Font...");
        jmOptions.add(jmiWordWrap);
        jmOptions.add(jmiFont);
        
        // Create menu items for help menu 
        JMenuItem jmiView = new JMenuItem("View Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiView);
        jmHelp.addSeparator();
        jmHelp.add(jmiAbout);
        
        // Add to menu
        jmb.add(jmFile);
        jmb.add(jmEdit);
        jmb.add(jmOptions);
        jmb.add(jmView);
        jmb.add(jmHelp);  
        
        // Add action listeners for the menu items. 
        jmiAbout.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);
		jmiPrint.addActionListener(this);
		jmiOpen.addActionListener(this);
		jmiGoTo.addActionListener(this);
		jmiFont.addActionListener(this);
		jmiReplace.addActionListener(this);

		
        // Add the label to the content pane. 
        text.add(jlab);

        // Add the menu bar to the frame. 
        jfrm.setJMenuBar(jmb);
        
        // Add panel to the content pane.
        jfrm.getContentPane().add(jsp);
        
        // center on default screen
      	jfrm.setLocationRelativeTo(null);

        // Display the frame.   
        jfrm.setVisible(true);
    }

    // Handle menu item action events. 
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Get the action command from the menu selection. 
        String comStr = ae.getActionCommand();
        
        ImageIcon noteIcon = new ImageIcon("notepad.png");
     	JFileChooser chooser = new JFileChooser();
     	
        // If user chooses Exit, then exit the program. 
        if (comStr.equals("Exit")) {
            System.exit(0);
        }else if(comStr.equals("Print")){
        	jlab.setText("Printing...");
        }else if(comStr.equals("About")) {
        	JOptionPane.showMessageDialog(null, "(c) D. Tran", "About Notepad",
        		    JOptionPane.INFORMATION_MESSAGE, noteIcon);
        }else if(comStr.equals("Open...")){
        	 int result = chooser.showOpenDialog(null);
        	 switch(result) {
        	 case JFileChooser.APPROVE_OPTION:
        		 System.out.println("Open was clicked");
        		 File filename = chooser.getSelectedFile();
            	 System.out.println("File name "+ filename.getName());
        		 break;
        	 case JFileChooser.CANCEL_OPTION:
        		 System.out.println("Cancel or close icon was clicked");
        		 break;
        	 case JFileChooser.ERROR_OPTION:
        		 System.out.println("Error");
        		 break;
        	 }
        }else if(comStr.equals("Go To...")) {
        	createGoToDialog(); 
        }else if(comStr.equals("Font...")) {
        	createFontDialog();
        	
        }else if(comStr.equals("Replace...")) {
        	createReplaceDialog();
        }
        
    }
    
    public static void createFontDialog() {
    	JFontChooser.showDialog(jfrm, "Consolas");
    }
       
    public void createReplaceDialog() {
    	JDialog dialog = new JDialog(jfrm, "Replace ", false);
    	dialog.setSize(400, 300);
    	
    	JPanel panelText = new JPanel(); // contains TextFields
    	JPanel panelLabels = new JPanel(); // contains Labels
    	JPanel panelButtons = new JPanel(); // set boxLayout & add buttons
    	JPanel panelCheck = new JPanel(); // contains checkboxes
    	
    	//panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.PAGE_AXIS));
    	    	
    	JButton buttonReplace = new JButton("Replace "); // when pressed replaces first term ahead of caret with new term 
    	JButton buttonReplaceAll = new JButton("Replace All"); // when pressed replaces all values with new term
    	JButton buttonFNext = new JButton("Find Next "); // when pressed finds term ahead of caret
    	JButton buttonCancel = new JButton("Cancel "); // when pressed dialog is closed
    	buttonReplace.setSize(30, 30);
    	buttonReplaceAll.setSize(30,30);
    	buttonFNext.setSize(30,30);
    	buttonCancel.setSize(30,30);
    	
    	panelButtons.add(buttonFNext);
    	panelButtons.add(buttonReplace);
    	panelButtons.add(buttonReplaceAll);
    	panelButtons.add(buttonCancel);
    	
    	buttonReplace.addActionListener(this);
    	buttonReplaceAll.addActionListener(this);
    	buttonFNext.addActionListener(this);
    	buttonCancel.addActionListener(this);
    	  	
    	JCheckBox check = new JCheckBox();
    	JCheckBox check2 = new JCheckBox();
    	
        dialog.getContentPane().add(panelButtons);
    	dialog.setBackground(Color.LIGHT_GRAY);
      	dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
    	dialog.setVisible(true);
    }
    
    public void createGoToDialog() {
    	JTextField jtfInput = new JTextField(20);
		JLabel lineNumLabel = new JLabel("Line Number: ");
		JDialog dialog = new JDialog(jfrm, "Go To Line", true);
		JButton jbOk = new JButton("Ok");
		JButton jbCancel = new JButton("Cancel");
        dialog.setSize(640, 400);      
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        dialog.getContentPane().setLayout(gbag);
        
        gbc.weightx = 1.0;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbag.setConstraints(lineNumLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbag.setConstraints(jtfInput, gbc);
           
        gbc.insets = new Insets(1, 1, 1, 1);
                
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbag.setConstraints(jbOk, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbag.setConstraints(jbCancel, gbc);
        
        jtfInput.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent le) {
     			try{
     				int parsedInput = Integer.parseInt(jtfInput.getText());
     				text.setCaretPosition(parsedInput + 1);
            		System.out.println(parsedInput);  	     				
     			}catch (Exception e) {
     	
     			}
			}
        });
        
        dialog.getContentPane().add(lineNumLabel);
        dialog.getContentPane().add(jtfInput);
        dialog.getContentPane().add(jbOk);
        dialog.getContentPane().add(jbCancel);
        
        dialog.setBackground(Color.LIGHT_GRAY);
        dialog.setResizable(false);
      	dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.   
        SwingUtilities.invokeLater(() -> {
            new Notepad();
        });
    }
}
