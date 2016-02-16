import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Note to self: It may be in my best interest to slpit some of this up into deffernet and easier to debug methods. Especially if it is to become a project to work on longer than a few months.
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
                    Button source = (Button) e.getSource();
                    for (int j = 0; j < files.length; j++) {
                        if (source.getLabel().equals(files[j].getName())) {
                            try {
                                Desktop.getDesktop().open(files[j]);
                                frame.remove(source);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
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
