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

        // Specify FlowLayout for the layout manager.   
        jfrm.setSize(940, 780);

        // Terminate the program when the user closes the application.   
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label that will display the menu selection. 
        jlab = new JLabel();

        // Create the menu bar. 
        JMenuBar jmb = new JMenuBar();
        
        // Create textArea
        JTextArea text = new JTextArea(10, 10);
        JScrollPane jsp = new JScrollPane(text);
        
        // Set font
        Font font = new Font("Courier New", Font.PLAIN, 12);
        text.setFont(font);


        // Create the File menu. 
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiPrint = new JMenuItem("Print");
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        // Create the Format menu. 
        JMenu jmOptions = new JMenu("Format");

        // Create the Reset menu item. 
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        // Finally, add the entire options menu to 
        // the menu bar 
        jmb.add(jmOptions);

        // Create the Help menu. 
        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);
        
        // Create the Edit menu.
        JMenu jmEdit = new JMenu("Edit");
        jmb.add(jmEdit);
        
        // Create the View menu
        JMenu jmView = new JMenu("View");
        jmb.add(jmView);
        
        // Add action listeners for the menu items. 
        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);
		jmiPrint.addActionListener(this);

        // Add the label to the content pane. 
        jfrm.add(jlab);

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

        // If user chooses Exit, then exit the program. 
        if (comStr.equals("Exit")) {
            System.exit(0);
        }else if(comStr.equals("Print")){
        	jlab.setText("Printing...");
        }else if(comStr.equals("About")) {
        	jlab.setText("by D. Tran");
        }
    }

    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.   
        SwingUtilities.invokeLater(() -> {
            new NotepadApp2();
        });
    }
}
