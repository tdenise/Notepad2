//
// Homework: #4
// Due: Thursday April 25, 2019 
// Course: cs-2450-02-sp19
//
// Description:
// Implement the font viewer function in the Notepad application
//

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Notepad implements ActionListener{
	JLabel jlab;
	JTextArea text = new JTextArea(10, 10);
	int i = 0;
    JTextPane textPane;
    int lineCount;
    static JFrame jfrm = new JFrame("Untitled - Notepad");


    Notepad() {
        // Create a new JFrame container.   
    	jfrm.setSize(800, 600);
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
        jmFile.setMnemonic('F');
        JMenuItem jmiNew = new JMenuItem("New");
        jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        JMenuItem jmiOpen = new JMenuItem("Open...");
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem jmiSave = new JMenuItem("Save");
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem jmiSaveAs = new JMenuItem("Save as...");
        JMenuItem jmiPgSetup = new JMenuItem("Page Setup...");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiPrint = new JMenuItem("Print...");
        jmiPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
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
        jmEdit.setMnemonic('E');
        JMenu jmOptions = new JMenu("Format");
        jmOptions.setMnemonic('O');
        JMenu jmView = new JMenu("View");
        jmView.setMnemonic('V');
        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic('H');
        
        // Create edit menu items
        JMenuItem jmiUndo = new JMenuItem("Undo", KeyEvent.VK_U);
        jmiUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        JMenuItem jmiCut = new JMenuItem("Cut", KeyEvent.VK_T);
        jmiCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        JMenuItem jmiCopy = new JMenuItem("Copy", KeyEvent.VK_C);
        jmiCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem jmiPaste = new JMenuItem("Paste", KeyEvent.VK_P);
        jmiPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        JMenuItem jmiDelete = new JMenuItem("Delete", KeyEvent.VK_L);
        jmiDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        JMenuItem jmiSearch = new JMenuItem("Search with Bing...", KeyEvent.VK_E);
        jmiSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        JMenuItem jmiFind = new JMenuItem("Find...", KeyEvent.VK_F);
        jmiFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        JMenuItem jmiFindN = new JMenuItem("Find Next", KeyEvent.VK_N);
        jmiFindN.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
        JMenuItem jmiReplace = new JMenuItem("Replace...", KeyEvent.VK_R);
        jmiReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        JMenuItem jmiGoTo = new JMenuItem("Go To...", KeyEvent.VK_G);
        jmiGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
        JMenuItem jmiSelect = new JMenuItem("Select All", KeyEvent.VK_A);
        jmiSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        JMenuItem jmiTime = new JMenuItem("Time/Date", KeyEvent.VK_F5);
        jmiTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
        jmEdit.addSeparator();
        jmEdit.add(jmiCut);
        jmEdit.add(jmiCopy);
        jmEdit.add(jmiPaste);
        jmEdit.add(jmiDelete);
        jmEdit.addSeparator();
        jmEdit.add(jmiSearch);
        jmEdit.add(jmiFind);
        jmEdit.add(jmiFindN);
        jmEdit.add(jmiReplace);
        jmEdit.add(jmiGoTo);
        jmEdit.addSeparator();
        jmEdit.add(jmiSelect);
        jmEdit.add(jmiTime);

        
        // Create view menu items.
        JMenuItem jmiStatusBar = new JMenuItem("Status Bar", KeyEvent.VK_S);
        JMenuItem jmiZoom = new JMenuItem("Zoom", KeyEvent.VK_Z);
        jmView.add(jmiZoom);
        jmView.add(jmiStatusBar);

//        // Create the Reset menu item. 
//        JMenuItem jmiReset = new JMenuItem("Reset");
//        jmOptions.addSeparator();
//        jmOptions.add(jmiReset);
        
        // Create menu items for format
        JCheckBoxMenuItem jmiWordWrap = new JCheckBoxMenuItem("Word Wrap");
        
        JMenuItem jmiFont = new JMenuItem("Font...", KeyEvent.VK_F);
        jmOptions.add(jmiWordWrap);
        jmOptions.add(jmiFont);
             
        JMenu jmiColor = new JMenu("Color ");
        jmOptions.add(jmiColor);
        JMenuItem jmiForeground = new JMenuItem("Foreground", KeyEvent.VK_F);
        JMenuItem jmiBackground = new JMenuItem("Background", KeyEvent.VK_B);
        jmiColor.add(jmiForeground);
        jmiColor.add(jmiBackground);
        
        // Create menu items for help menu 
        JMenuItem jmiView = new JMenuItem("View Help", KeyEvent.VK_H);
        JMenuItem jmiAbout = new JMenuItem("About", KeyEvent.VK_A);
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
		jmiPrint.addActionListener(this);
		jmiOpen.addActionListener(this);
		jmiGoTo.addActionListener(this);
		jmiFont.addActionListener(this);
		jmiReplace.addActionListener(this);
		jmiTime.addActionListener(this);
		jmiNew.addActionListener(this);
		jmiCut.addActionListener(this);
		jmiCopy.addActionListener(this);
		jmiPaste.addActionListener(this);
		jmiDelete.addActionListener(this);
		jmiSelect.addActionListener(this);
		jmiView.addActionListener(this);
		jmiSaveAs.addActionListener(this);
		jmiWordWrap.addActionListener(this);
		jmiFind.addActionListener(this);
		jmiForeground.addActionListener(this);
		jmiBackground.addActionListener(this);
		jmiSearch.addActionListener(this);

		
        // Add the label to the content pane. 
        text.add(jlab);

        // Add the menu bar to the frame. 
        jfrm.setJMenuBar(jmb);
        
        // Add panel to the content pane.
        jfrm.getContentPane().add(jsp);
        
        // center on default screen
      	// jfrm.setLocationRelativeTo(null);

        // Display the frame.   
        jfrm.setVisible(true);
    }

    // Handle menu item action events. 
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Get the action command from the menu selection. 
        String comStr = ae.getActionCommand();
        
        ImageIcon noteIcon = new ImageIcon("notepad.png");
 
     	
        // If user chooses Exit, then exit the program. 
        if (comStr.equals("Exit")) {
            saveDialog();
            //if() {
            //	System.exit(0);
            //}
        }else if(comStr.equals("Print")){
        	jlab.setText("Printing...");
        }else if(comStr.equals("About")) {
        	JOptionPane.showMessageDialog(null, "(c) D. Tran", "About Notepad",
        		    JOptionPane.INFORMATION_MESSAGE, noteIcon);
        }else if(comStr.equals("Open...")){
        	openChooser();
        }else if(comStr.equals("Save as...")){
        	saveChooser();
        }else if(comStr.equals("Save")){
        	
        }else if(comStr.equals("Go To...")) {
        	createGoToDialog(); 
        }else if(comStr.equals("Font...")) {
        	 final JFontChooser jfont = new JFontChooser();
             jfont.setVisible(true);
             final int opt = jfont.showDialog(jfrm);
             if (opt == JFontChooser.OK_OPTION) {
            	 text.setFont(jfont.getSelectedFont());
             }
        }else if(comStr.equals("Replace...")) {
        	createReplaceDialog();
        }else if(comStr.equals("Time/Date")) {
        	displayDate();
        }else if(comStr.equals("Cut")) {
        	text.cut();
        	text.copy();
        }else if(comStr.equals("Copy")) {
        	text.copy();
        }else if(comStr.equals("Paste")) {
        	text.paste();
        }else if(comStr.equals("Delete")) {
        	text.cut();
        }else if(comStr.equals("Select All")) {
        	text.selectAll();
        }else if(comStr.equals("View Help")) {
        	goHelpWebsite();
        }else if(comStr.equals("New")) {
        	text.setText("");
        }else if(comStr.equals("Word Wrap")) {
        	 AbstractButton button = (AbstractButton) ae.getSource();
             if (button.isSelected()) {
                 text.setLineWrap(true);
             } else {
                 text.setLineWrap(false);
             }
             text.revalidate();
        }else if(comStr.equals("Find...")) {
        	findDialog();
        }else if(comStr.equals("Foreground")) {
        	//colorDialog();
        }else if(comStr.equals("Background")) {
        	colorBackgroundDialog();
        }else if(comStr.equals("Search with Bing...")) {
        	goSearchWebsite();
        }       
            
    }
    
  
    public void colorForegroundDialog() {
        JButton colorButton=new JButton();
        JColorChooser colorPicker =new JColorChooser();
        JDialog dialog = new JDialog();
        
        JPanel panel=new JPanel();
        dialog.setContentPane(panel);
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //2nd colorchooser appears for foreground
                Color fgColor=JColorChooser.showDialog(null, "Foreground color", null);
                text.setForeground(fgColor);
            }
        });
        colorButton.setText("Pick a color");
        panel.add(colorButton);
        //dialog.add(panel);
        dialog.setSize(400, 400);
        dialog.setVisible(true);
    }
    
    public void colorBackgroundDialog() {
        JButton colorButton=new JButton();
        JColorChooser colorPicker =new JColorChooser();
        JDialog dialog = new JDialog();
        
        JPanel panel=new JPanel();
        dialog.setContentPane(panel);
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //1st color chooser for background color
                Color bgColor = JColorChooser.showDialog(null, "Background color", null);
                dialog.getContentPane().setBackground(bgColor);
           
            }
        });
        colorButton.setText("Pick a color");
        panel.add(colorButton);
        //dialog.add(panel);
        dialog.setSize(400, 400);
        dialog.setVisible(true);
    }
        

	public void saveDialog() {
    	JDialog dialog = new JDialog(jfrm, "Notepad", true);
    	dialog.setSize(624, 240);
    	JButton save = new JButton("Save");
    	JButton saveAs = new JButton("Save As");
    	JButton cancel = new JButton("Cancel");
    	JLabel label = new JLabel("Do you want to save changes to Untitled?");
    	
    	JPanel panel = new JPanel();
    	panel.add(label);
    	
    	JPanel panelButtons = new JPanel();
    	panelButtons.add(save);
    	panelButtons.add(saveAs);
    	panelButtons.add(cancel);
    	
    	JPanel panel2 = new JPanel();
    	panel2.add(panel);
    	panel2.add(panelButtons);
     	
    	// bundle buttons
    	
    	// put jlabel in its own jpanel
    	
    	dialog.getContentPane().add(panel2);
    	dialog.setBackground(Color.LIGHT_GRAY);
    	dialog.setResizable(false);
    	dialog.setVisible(true);
    	
    
    }
    
    public void displayDate() {
    	Date now = new Date();
        //Set date format as you want
        SimpleDateFormat sf = new SimpleDateFormat("h:mm a d/MM/yyyy"); 
        text.setText(sf.format(now));
    }
    

    // Error message pops up when nothing to replace
    // or when term looking for is not there
    public void errorMessageDialog() {
    	JDialog dialog = new JDialog(jfrm, "Replace", true);
    	dialog.setSize(100, 100);
    	
  //  	JLabel errorMessage = new JLabel("Cannot find "\n" + term + \n"");
    	
    	
    	dialog.setVisible(true);
    }
       
    public void findDialog() {
    	
    	JDialog dialog = new JDialog();
    	JLabel jLabel1 = new JLabel();
    	JTextField jTextField1 = new JTextField();
    	JButton jButton1 = new JButton();
    	JButton jButton2 = new JButton();
    	JRadioButton jRadioButton1 = new JRadioButton();
    	JRadioButton jRadioButton2 = new JRadioButton();
    	JCheckBox jCheckBox1 = new JCheckBox();
    	JCheckBox jCheckBox2 = new JCheckBox();

        jLabel1.setText("Find what: ");
        jTextField1.setText(" "); 
        jButton1.setText("Find Next");
        jButton2.setText("Cancel");
        jRadioButton1.setText("Up");   
        
        jButton2.addActionListener(this);
        jRadioButton1.addActionListener(this);

        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Down");
        jRadioButton2.addActionListener(this);

        jCheckBox1.setText("Match case");
        jCheckBox1.addActionListener(this);

        jCheckBox2.setText("Wrap around");
        jCheckBox2.addActionListener(this);

        GroupLayout layout = new GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(85, 85, 85)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(330, 330, 330)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
    	dialog.setVisible(true);
    	
    	
//    	String findstr = jTextField1.getText().toUpperCase(); // User Input Word to find
//    	int findstrLength = findstr.length();                   
//    	String findtextarea = text.getText().toUpperCase(); // TextArea Content
//    	Highlighter h = text.getHighlighter();
//    	h.removeAllHighlights();
//    	try
//    	    {
//    	        int index=0;
//    	        while(index>=0)                             // What should I put here ??
//    	        {
//    	            index = findtextarea.indexOf(findstr,index);
//    	            h.addHighlight(index,index+findstrLength, DefaultHighlighter.DefaultPainter);
//    	        }
//    	    } catch (BadLocationException e) {
//				e.printStackTrace();
//			}
    }
    
    public void createReplaceDialog() {
    	JDialog dialog = new JDialog(jfrm, "Replace ", false);
    	dialog.setSize(400, 300);
    	
    	// contains buttons
    	JPanel panelB1 = new JPanel(); 
    	JPanel panelB2 = new JPanel();
    	JPanel panelB3 = new JPanel();
    	JPanel panelB4 = new JPanel();
    	
    	JPanel panelText = new JPanel(); // contains TextFields
    	JPanel panelLabels = new JPanel(); // contains Labels
    	JPanel panelButtons = new JPanel(); // set boxLayout & add buttons
    	JPanel panelCheck = new JPanel(); // contains checkboxes
    	
    	panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.PAGE_AXIS));
    	panelCheck.setLayout(new BorderLayout());
    	    	
    	JButton buttonReplace = new JButton("Replace "); // when pressed replaces first term ahead of caret with new term 
    	JButton buttonReplaceAll = new JButton("Replace All"); // when pressed replaces all values with new term
    	JButton buttonFNext = new JButton("Find Next "); // when pressed finds term ahead of caret
    	JButton buttonCancel = new JButton("Cancel "); // when pressed dialog is closed
    	
    	JLabel labelFind = new JLabel("Find what: ");
    	JLabel labelReplace = new JLabel("Replace with: ");
    	
    	JCheckBox check = new JCheckBox("Match case ");
    	JCheckBox check2 = new JCheckBox("Wrap around ");
    	
    	JTextField field1 = new JTextField();
    	JTextField field2 = new JTextField();
    	
    	panelCheck.add(BorderLayout.CENTER, check);
    	panelCheck.add(check2);
    	
    	buttonReplace.setPreferredSize(new Dimension(100, 30));
    	buttonReplaceAll.setPreferredSize(new Dimension(100, 30));
    	buttonFNext.setPreferredSize(new Dimension(100, 30));
    	buttonCancel.setPreferredSize(new Dimension(100, 30));
    	
    	panelB1.add(buttonFNext);
    	panelB2.add(buttonReplace);
    	panelB3.add(buttonReplaceAll);
    	panelB4.add(buttonCancel);
    	
    	panelButtons.add(panelB1);
    	panelButtons.add(panelB2);
    	panelButtons.add(panelB3);
    	panelButtons.add(panelB4);
    	
    	buttonReplace.addActionListener(this);
    	buttonReplaceAll.addActionListener(this);
    	buttonFNext.addActionListener(this);
    	buttonCancel.addActionListener(this);
    	
    	dialog.getContentPane().add(panelCheck);
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
    
    public void openChooser() {
    	
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text Files(*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System
                .getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(selectedFile));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String all = sb.toString();
                text.setText(all);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                } catch (IOException ex) {
                   
                }
            }
        }
	   	 
    }
    
    public void saveChooser() {
    	JFileChooser chooser = new JFileChooser();
    	int option = chooser.showSaveDialog(null);
    	
    	try(FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt")) {    	    
    		if (option == JFileChooser.APPROVE_OPTION) {
	    	File fileToSave = chooser.getSelectedFile();
	    	System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    		}
    	}catch(Exception ae) {}
    	
    }
    
    private void goHelpWebsite() {
    	try {
    		Desktop.getDesktop().browse(new URI("https://binged.it/302MdwR"));
    	} catch (URISyntaxException | IOException ex) {
          
        }    
    }
    
    private void goSearchWebsite() {
    	try {
    		Desktop.getDesktop().browse(new URI("https://www.bing.com/?scope=web&mkt=en-US"));
    	} catch (URISyntaxException | IOException ex) {
          
        }    
    }
    
    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.   
        SwingUtilities.invokeLater(() -> {
            new Notepad();
        });
    }
}
