import entity.*;

import static IOUtil.InputReader.readFile;

public class RE {

    public static void main(String[] args) {
        readFile("input");
        readFile("ans");
        for (NFR fr : NFRs.NFRlist) {
            fr.excludeNoise();
            Dic.initWords();
            System.out.println(fr.words);
        }
        for (FR fr : FRs.FRlist) {
            fr.excludeNoise();
            Dic.initWords();
            System.out.println(fr.words);
            System.out.println(fr.relate1 + " " + fr.relate2 + "  " + fr.relate3);
        }
        System.out.println(Dic.wordsOfIns1);
        System.out.println(Dic.wordsOfIns2);
        System.out.println(Dic.wordsOfIns3);
        for (FR fr : FRs.FRlist) {
            System.out.println();
            System.out.println(fr.FRname);
            System.out.println(fr.relate1 + " " + fr.relate2 + "  " + fr.relate3);
            System.out.println(fr.calculateRelate(Dic.wordsOfIns1) + " " + fr.calculateRelate(Dic.wordsOfIns2) + "  " + fr.calculateRelate(Dic.wordsOfIns3));
            System.out.println(fr.calculateReliability(Dic.wordsOfIns1) + " " + fr.calculateReliability(Dic.wordsOfIns2) + "  " + fr.calculateReliability(Dic.wordsOfIns3));
        }
    }
}


