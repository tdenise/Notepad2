//
// Name: Tran, Denise
// Homework: #3
// Due: Thursday April 11, 2019 
// Course: cs-2450-02-sp19
//
// Description:
// Implement the Open and About menus of the Notepad application in homework 2.  
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotepadApp2 implements ActionListener{
	JLabel jlab;

    NotepadApp2() {
        // Create a new JFrame container.   
        JFrame jfrm = new JFrame("Untitled - Notepad");
        jfrm.setSize(940, 780);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label that will display the menu selection & menu bar
        jlab = new JLabel();
        JMenuBar jmb = new JMenuBar();
        
        // Create textArea
        JTextArea text = new JTextArea(10, 10);
        JScrollPane jsp = new JScrollPane(text);
        
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
        JMenuItem jmiAbout = new JMenuItem("About");
        JMenu jmHelp = new JMenu("Help");

        // Create the Reset menu item. 
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        // Add to menu bar
        jmb.add(jmFile);
        jmb.add(jmEdit);
        jmb.add(jmOptions);
        jmHelp.add(jmiAbout);
        jmb.add(jmView);
        jmb.add(jmHelp);  
        
        // Add action listeners for the menu items. 
        jmiAbout.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);
		jmiPrint.addActionListener(this);

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
        
        // Create icon
     	ImageIcon noteIcon = new ImageIcon("notepad.png");
        
        // If user chooses Exit, then exit the program. 
        if (comStr.equals("Exit")) {
            System.exit(0);
        }else if(comStr.equals("Print")){
        	jlab.setText("Printing...");
        }else if(comStr.equals("About")) {
        	JOptionPane.showMessageDialog(null, "(c) D. Tran", "About Notepad",
        		    JOptionPane.INFORMATION_MESSAGE, noteIcon);
        }else if(comStr.equals("Open...")){
        	
        }
    }
    

    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.   
        SwingUtilities.invokeLater(() -> {
            new NotepadApp2();
        });
    }
}
