import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Note to self: It may be in my best interest to slpit some of this up into deffernet and easier to debug methods. Especially if it is to become a project to work on longer than a few months.
 */
public class MainView {
    Frame frame;
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<File> filelist = new ArrayList<File>();


    public MainView(){
        frame = new Frame("Main Frame");
    }
    public void showView(final ArrayList<File> files){
        for (int i = 0; i < files.size(); i++){
            buttons.add(new Button(files.get(i).getName()));
        }
        frame.setSize(200,200);
        frame.setLayout(new FlowLayout());
        for (int j = 0; j < buttons.size(); j++){
            frame.add(buttons.get(j));
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Button source = (Button) e.getSource();
                    for (int j = 0; j < files.size(); j++) {
                        if (source.getLabel().equals(files.get(j).getName()) && files.get(j).isFile()) {
                            try {
                                Desktop.getDesktop().open(files.get(j));
                                frame.remove(source);
                                addToViewedMedia(files.get(j));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        else if (source.getLabel().equals(files.get(j).getName()) && files.get(j).isDirectory()) {
                            File[] tempFiles = files.get(j).listFiles();
                            ArrayList<File> temp = new ArrayList<File>();
                            for (int i = 0; i < tempFiles.length; i++) {
                                temp.add(tempFiles[i]);
                            }
                            for (int k = 0; k < buttons.size(); k++) {
                                frame.remove(buttons.get(k));
                            }
                            buttons.clear();
                            showView(filterArrayList(temp));
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
    public ArrayList<File> showDirectoryChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File currentDirectory = chooser.getSelectedFile();
            File[] filesinFolder = currentDirectory.listFiles();
            for (int i = 0; i < filesinFolder.length; i++) {
                filelist.add(filesinFolder[i]);
            }
            ArrayList<File >filelist1 = filterArrayList(filelist);
            return filelist1;
        }
        else {
            return null;
        }
    }
    public ArrayList<File> filterArrayList(ArrayList<File> list) {
        File viewedList = new File("viewedcontent.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(viewedList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (line.equals(list.get(i).getPath())) {
                        list.remove(i);
                    }
                    else {
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    public void addToViewedMedia(File file) {
        File fileWritingTo = new File("viewedcontent.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileWritingTo, true));
            writer.append(file.toString() + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
