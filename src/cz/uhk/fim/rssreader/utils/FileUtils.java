package cz.uhk.fim.rssreader.utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {

    public static final String BACKGROUND_COLOR = "0x119add";
    public static final String HEADER_COLOR = "0xffffff";
    public static final String TEXT_COLOR = "0x1a1a1a";
    public static final String INFO_COLOR = "0xdddddd";

    public static String loadStringFromFile(String filepath)throws IOException {

        return new String(Files.readAllBytes(Paths.get(filepath)));

    }

    public static void saveStringToFile(String filepath, byte []data)throws IOException{
        Path path = Paths.get(filepath);
        Files.write(path,data);
    }

    public static boolean validateInput(JLabel label,String str){
        label.setText("Nice, sweat and sexy, man!");
        label.setForeground(Color.GREEN);
        if(str.length()==0){
            label.setText("Empty input!");
            label.setForeground(Color.RED);
            return false;
        }

        if(str.contains(" ")){
            label.setText("Invalid characters in input!");
            label.setForeground(Color.RED);
            return false;
        }


        if(str=="LOAD_ERROR"){
            label.setText(ErrorsType.LOAD_ERROR.getErrorMessage());
            label.setForeground(Color.RED);
            return false;
        }

        if(str=="SAVE_ERROR"){
            label.setText(ErrorsType.SAVE_ERROR.getErrorMessage());
            label.setForeground(Color.RED);
            return false;
        }

        return true;
    }
}
