package IOUtil;

import entity.FR;
import entity.FRs;
import entity.NFR;
import entity.NFRs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class InputReader {
    public static String instanceName;

    public static void readFile(String filepath) {
        File file = new File(filepath);
        if (!file.isDirectory()) {
            if (file.getName().contains("ans"))
                readAns(file);
            else {
                extractProduceInfo(file);
                instanceName = file.getName();
            }
        } else if (file.isDirectory()) {
            String[] fileList = file.list();
            for (int i = 0; i < Objects.requireNonNull(fileList).length; i++) {
                readFile(filepath + "\\" + fileList[i]);
            }
        }
    }

    public static void readAns(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("FR")) {
                    FR fr = FRs.FRlist.get(i);
                    temp = line.split(",");
                    fr.relate1 = Integer.parseInt(temp[1]);
                    fr.relate2 = Integer.parseInt(temp[2]);
                    fr.relate3 = Integer.parseInt(temp[3]);
                }
                i++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    public static void extractProduceInfo(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            System.out.println(file.getName());
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("NFR")) {
                    NFR nfr = new NFR();
                    int index = line.indexOf(":");
                    System.out.println(line + "  " + index);
                    nfr.NFRname = line.substring(0, index);
                    nfr.NFRcontent = line.substring(index + 2);
                    NFRs.NFRlist.add(nfr);
                } else if (line.contains("FR")) {
                    FR fr = new FR();
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
