package csc1035.project2.question;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class ExportQuestion {
    public void ExportQuestion (String line) throws IOException{
        String path = //will need to be updated
        File File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(line);
        bw.newLine();
        bw.close();
        fw.close();
    }

}
