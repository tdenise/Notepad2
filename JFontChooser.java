import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class JFontChooser{       
        
	public void showDialog(JFrame parent, Font initialFont) {
		JDialog dialog = new JDialog();
		dialog.setSize(400, 1000);  
		
		// load the list with all available fonts
        JList<String> fonts = new JList<>(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()
        );
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        dialog.add(new JLabel("Select font: Plain 18 points"), BorderLayout.NORTH);
        dialog.add(new JScrollPane(fonts));
        JLabel sample = new JLabel("The quick brown fox jumps over the lazy dog 0123456789");
      
        dialog.add(sample, BorderLayout.SOUTH);
        
        fonts.addListSelectionListener((le) -> {;
            sample.setFont(new Font((String)fonts.getSelectedValue(), Font.PLAIN, 18));
        });
        fonts.setSelectedIndex(0);
		
		
		dialog.setBackground(Color.LIGHT_GRAY);
        dialog.setResizable(false);
      	dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
	}
	    
	public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> { new JFontChooser(); });
	}

}
