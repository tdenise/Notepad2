import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class JFontChooser {

    JLabel jlab;
    JButton jbtnShow;
    JFrame jfrm = new JFrame("Font");
    JDialog dialog = new JDialog(jfrm, "Font ");
    public JFontChooser() {
    	             

        // Specify FlowLayout for the layout manager.    
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Give the frame an initial size.    
        jfrm.setSize(900, 800);

        // Terminate the program when the user closes the application. 
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label that will show the color chosen. 
        jlab = new JLabel();

        // Create button that will show the dialog. 
        jbtnShow = new JButton("Show Font Chooser");
        
        // load the list with all available fonts
        JList<String> fonts = new JList<>(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()
        );
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //load the list with all available fonts
        String[] ar = {"6", "8", "10", "12", "14", "16", "18", 
        	    "20", "22", "24", "36", "72"};
        JList<String> sizes = new JList<> (ar);
        sizes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jfrm.add(new JLabel("Select font: Plain 18 points"), BorderLayout.NORTH);
        jfrm.add(new JScrollPane(fonts));
        JLabel sample = new JLabel("The quick brown fox jumps over the lazy dog 0123456789");
      
        jfrm.add(sample, BorderLayout.SOUTH);
        
        fonts.addListSelectionListener((le) -> {;
            sample.setFont(new Font((String)fonts.getSelectedValue(), Font.PLAIN, 18));
        });
        fonts.setSelectedIndex(0);
        
        jfrm.setLocationRelativeTo(null);
        
        jfrm.setVisible(true);
        
        // Show the font chooser when the Show button 
        // is pressed. 
        jbtnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                // Pass null for the parent. This centers the  
                // dialog on the screen. The initial font is ...
                Color font = JFontChooser.showDialog(jfrm,
                        "Choose Font: ");

                if (font != null) {
					jlab.setText("Selected font is "
                            + font.toString());
                } else {
                    jlab.setText("Font selection was cancelled.");
                }
            }
        });

        // Add the Show button and label to the content pane. 
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // Display the frame.    
        jfrm.setVisible(true);
    }

    protected static Color showDialog(JFrame jfrm, String initialItem) {
    	// Pops up a Font chooser dialog with the supplied title
    	JDialog dialog = new JDialog(jfrm, "Font ");
    	JLabel fontLabel = new JLabel("font");
    	JLabel fontSzLabel = new JLabel("Font Size: ");
    	JButton jbOk = new JButton("Ok");
    	JButton jbCancel = new JButton("Cancel");
        dialog.getContentPane().setLayout(new FlowLayout());
    	dialog.setSize(900, 800);

    	
    	// load the list with all available fonts
        JList<String> fonts = new JList<>(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()
        );
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //load the list with all available fonts
        String[] ar = {"6", "8", "10", "12", "14", "16", "18", 
        	    "20", "22", "24", "36", "72"};
        JList<String> sizes = new JList<> (ar);
        sizes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);     
        
        
        dialog.getContentPane().add(new JLabel("Select font: Plain 18 points"), BorderLayout.NORTH);
        dialog.getContentPane().add(new JScrollPane(fonts));
        JLabel sample = new JLabel("The quick brown fox jumps over the lazy dog 0123456789");
        
        jfrm.add(sample, BorderLayout.SOUTH);
        
        fonts.addListSelectionListener((le) -> {;
            sample.setFont(new Font((String)fonts.getSelectedValue(), Font.PLAIN, 18));
        });
        fonts.setSelectedIndex(0);
        
        
    	dialog.getContentPane().add(jbOk);
    	dialog.add(jbCancel);
    	dialog.setBackground(Color.LIGHT_GRAY);
        dialog.setResizable(false);
       	dialog.setLocationRelativeTo(null);
    	dialog.setVisible(true);
		return null;
	}

	public static void main(String args[]) {
        // Create the frame on the event dispatching thread. 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JFontChooser();
            }
        });
    }
}