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
            System.out.println(fr.NFRname+"  \n"+fr.words);
        }
        for (FR fr : FRs.FRlist) {
            fr.excludeNoise();
            System.out.println(fr.words);
            System.out.println(fr.relate1 + " " + fr.relate2 + "  " + fr.relate3);
        }
        Dic.initWords();
        System.out.println("wordsOfIns1"+"\n"+Dic.wordsOfIns1);
        System.out.println("wordsOfIns2"+"\n"+Dic.wordsOfIns2);
        System.out.println("wordsOfIns3"+"\n"+Dic.wordsOfIns3);

        System.out.println("words_tf"+"\n"+Dic.words_tf);
        System.out.println("words_df"+"\n"+Dic.words_df);
        System.out.println("words_idf"+"\n"+Dic.words_idf);


        System.out.println("words_tf_idf1"+"\n"+Dic.words_tf_idf1);
        System.out.println("words_tf_idf2"+"\n"+Dic.words_tf_idf2);
        System.out.println("words_tf_idf3"+"\n"+Dic.words_tf_idf3);
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

            OutputWriter.writeFile(fr.FRname+","+determinetfidf(1,fr.calculateSimByTFIDF(1)) + "," + determinetfidf(2,fr.calculateSimByTFIDF(2))
                    + "," + determinetfidf(3,fr.calculateSimByTFIDF(3)));

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

    private static int determinetfidf(int id,double sim){
        switch (id){
            case 1:
                if(sim>0.55)return 1;
                else return 0;
            case 2:
                if(sim>0.20)return 1;
                else return 0;
            case 3:
                if(sim>0.45)return 1;
                else return 0;
        }
        return 0;
    }

}


