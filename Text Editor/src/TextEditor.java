import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener
{
    // Declaring Properties of Text Editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //File Menu Items
    JMenuItem newFile, openFile, saveFile;
    //Edit Menu Items
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){

        // Intialize a Frame
        frame = new JFrame();

        // Initialize a new menuBar
        menuBar = new JMenuBar();

        //Initialize text area
        textArea = new JTextArea();

        // Initialize Menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file Menu Items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        // add actionlistener to file menuitems
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add menuitems to File Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize Edit Menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // add actionlistener to Edit menuitems
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add menuitems to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        // set menuBar to frame
        frame.setJMenuBar(menuBar);

        //Create Content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to the panel
        panel.add(textArea,BorderLayout.CENTER);

        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Add scrollpane to  the panel
        panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);

        // setting Dimensions of the Frame
        frame.setBounds(100,100,500,500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    public static void main(String[] args) {
       TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(e.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(e.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(e.getSource()==selectAll)
        {
            //perform selectall operation
            textArea.selectAll();
        }
        if(e.getSource()==close){
            System.exit(0);
        }
        if(e.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on Open button
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                // getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "";
                    String output= "";
                    // Read contents of file line by line
                    while ((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";

                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
        if(e.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose options from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                        //Initialize file writer
                        FileWriter fileWriter = null;
                        fileWriter = new FileWriter(file);
                        //Initialize buffered writer
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        //write contents of text area to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        if(e.getSource()==newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
        }
    }
