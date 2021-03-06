import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Note to self: It may be in my best interest to slpit some of this up into deffernet and easier to debug methods. Especially if it is to become a project to work on longer than a few months.
 */
public class MainView {
    Frame frame;
    JPanel panel;
    JScrollPane scrollPane;
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<File> filelist = new ArrayList<File>();
    Stack<ArrayList> fileListStack = new Stack<ArrayList>();



    public MainView(){
        frame = new Frame("Media Sequencer");
        panel = new JPanel();
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    public void showView(final ArrayList<File> files){
        buttons.add(new Button("Up Directory"));
        buttons.get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int g = 0; g < buttons.size(); g++) {
                    panel.remove(buttons.get(g));
                }
                buttons.clear();
                ArrayList<File> temp1 = filterUpDirButton(fileListStack.pop());
                showView(filterArrayList(temp1));
            }
        });
        for (int i = 0; i < files.size(); i++){
            buttons.add(new Button(files.get(i).getName()));
        }
        panel.setLayout(new GridLayout(0,3));
        for (int j = 0; j < buttons.size(); j++){
            panel.add(buttons.get(j));
        }
        for (int i = 1; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Button source = (Button) e.getSource();
                    for (int j = 0; j < files.size(); j++) {
                        if (source.getLabel().equals(files.get(j).getName()) && files.get(j).isFile()) {
                            try {
                                Desktop.getDesktop().open(files.get(j));
                                panel.remove(source);
                                addToViewedMedia(files.get(j));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        else if (source.getLabel().equals(files.get(j).getName()) && files.get(j).isDirectory()) {
                            fileListStack.push(files);
                            File[] tempFiles = files.get(j).listFiles();
                            ArrayList<File> temp = new ArrayList<File>();
                            for (int i = 0; i < tempFiles.length; i++) {
                                temp.add(tempFiles[i]);
                            }
                            for (int k = 0; k < buttons.size(); k++) {
                                panel.remove(buttons.get(k));
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
        frame.add(scrollPane);
        frame.setSize(200,200);
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
            fileListStack.push(filelist1);
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
    public ArrayList<File> filterUpDirButton(ArrayList<File> list) {
        for (int i = 0; i< list.size(); i++) {
            if (list.get(i).equals("Up Directory")) {
                list.remove(i);
            }
            else {
                continue;
            }
        }
        return list;
    }

}