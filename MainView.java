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
    }
    public void showView(final File[] files){
        for (int i = 0; i < files.length; i++){
            buttons.add(new Button(files[i].getName()));
        }
        frame.setSize(200,200);
        frame.setLayout(new FlowLayout());
        for (int i = 0; i < buttons.size(); i++){
            frame.add(buttons.get(i));
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < files.length;i++) {
                        if (files[i].getName().equals())
                    }
                }
            });
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
    public File[] showDirectoryChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File currentDirectory = chooser.getSelectedFile();
            File[] filesinFolder = currentDirectory.listFiles();
            return filesinFolder;
        }
        else {
            return null;
        }
    }

}
