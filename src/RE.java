import IOUtil.OutputWriter;
import entity.*;
import evaluation.Metrics;

import static IOUtil.InputReader.readFile;

public class RE {

    public static void main(String[] args) {
        readFile("input");
        readFile("ans");
        OutputWriter.init();
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
            //System.out.println(fr.calculateRelate(Dic.wordsOfIns1) + " " + fr.calculateRelate(Dic.wordsOfIns2) + "  " + fr.calculateRelate(Dic.wordsOfIns3));
            //System.out.println(fr.calculateReliability(Dic.wordsOfIns1) + " " + fr.calculateReliability(Dic.wordsOfIns2) + "  " + fr.calculateReliability(Dic.wordsOfIns3));
            System.out.println(fr.calculateSimilarity(1) + " " + fr.calculateSimilarity(2) + "  " + fr.calculateSimilarity(3));
            System.out.println(fr.calculateSimByTFIDF(1) + " " + fr.calculateSimByTFIDF(2) + "  " + fr.calculateSimByTFIDF(3));

//            OutputWriter.writeFile(fr.FRname+","+determine(1,fr.calculateSimilarity(1)) + "," + determine(2,fr.calculateSimilarity(2))
//                    + "," + determine(3,fr.calculateSimilarity(3)));

            OutputWriter.writeFile(fr.FRname+","+determine(1,fr.calculateSimilarity(1)) + "," + determine(2,fr.calculateSimilarity(2))
                    + "," + determine(3,fr.calculateSimilarity(3)));

        }
        Metrics.main(null);
    }

    private static int determine(int id,double sim){
        switch (id){
            case 1:
                if(sim>0.40)return 1;
                else return 0;
            case 2:
                if(sim>0.09)return 1;
                else return 0;
            case 3:
                if(sim>0.28)return 1;
                else return 0;
        }
        return 0;
    }

}


