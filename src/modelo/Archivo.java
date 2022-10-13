package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Archivo {
    private  String path;

    public Archivo(String path){
        super();
        this.path = path;
    }


    @Override
    public String toString(){
        String content = "";

        File myFile = new File(path);
        Scanner myScanner;

        try {
           myScanner = new Scanner(myFile);

           while (myScanner.hasNext()){
               content += myScanner.nextLine()+"\n";
           }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return content;
    }

}
