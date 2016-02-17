import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 */
public class MediaMain {

    public static void main(String[] args) {
        MainView mv = new MainView();
        ArrayList<File> list = mv.showDirectoryChooser();
        mv.showView(list);





    }

}
