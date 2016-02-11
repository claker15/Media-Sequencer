import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by shark on 2/5/2016.
 */
public class MainView {
    Frame frame;
    ArrayList<Button> buttons = new ArrayList<Button>();

    public MainView(){
        frame = new Frame("Main Frame");
        for (int i = 1; i < 11; i++){
            buttons.add(new Button("Button" + i));
        }
    }
    public void showView(){
        frame.setSize(200,200);
        frame.setLayout(new FlowLayout());
        for (int i = 0; i < buttons.size(); i++){
            frame.add(buttons.get(i));
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
    public void showDirectoryChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File currentDirectory = chooser.getSelectedFile();
            System.out.println("File in Directory " + currentDirectory);
            File[] filesinFolder = currentDirectory.listFiles();
            for (int i = 0; i < filesinFolder.length; i++){
                System.out.println(filesinFolder[i].getName());
            }


        }
    }
}
