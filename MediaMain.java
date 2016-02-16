import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 
 */
public class MediaMain {

    public static void main(String[] args) {
        MainView mv = new MainView();
        File[] list = mv.showDirectoryChooser();
        mv.showView(list);





    }

}
