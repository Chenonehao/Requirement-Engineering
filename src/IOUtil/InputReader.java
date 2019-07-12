package IOUtil;

import entity.FR;
import entity.NFR;
import entity.NFRs;
import entity.FRs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class InputReader {
    public static void readFile(String filepath) {
        File file = new File(filepath);
        if (!file.isDirectory()) {
            System.out.println("path=" + file.getPath());
            System.out.println("absolutepath=" + file.getAbsolutePath());
            System.out.println("name=" + file.getName());
            extractProduceInfo(file);
        } else if (file.isDirectory()) {
            String[] fileList = file.list();
            for (int i = 0; i < Objects.requireNonNull(fileList).length; i++) {
                readFile(filepath + "\\" + fileList[i]);
            }
        }
    }

    public static void extractProduceInfo(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("NFR")) {
                    NFR nfr = new NFR();
                    line = bufferedReader.readLine();
                    int index = line.indexOf(":");
                    nfr.NFRname = line.substring(0, index);
                    nfr.NFRcontent = line.substring(index + 2);
                    System.out.println(nfr.NFRname + "\r\n" + nfr.NFRcontent);
                    NFRs.NFRlist.add(nfr);
                } else if (line.contains("FR")) {
                    FR fr = new FR();
                    line = bufferedReader.readLine();
                    int index = line.indexOf(":");
                    fr.FRname = line.substring(0, index);
                    fr.FRcontent = line.substring(index + 2);
                    System.out.println(fr.FRname + "\r\n" + fr.FRcontent);
                    FRs.FRlist.add(fr);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
