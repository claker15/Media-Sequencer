import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by shark on 2/5/2016.
 */
public class MediaMain {

    public static void main(String[] args) {
        MainView mv = new MainView();
        File[] list = mv.showDirectoryChooser();
        mv.showView(list);





    }

}
