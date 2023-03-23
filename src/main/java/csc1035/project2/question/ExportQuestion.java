package csc1035.project2.question;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ExportQuestion {
    public void ExportQuestion (String line) {
        String path = //will need to be updated
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(line);
        bw.newLine();
        bw.close();
        fw.close();
    }

}
