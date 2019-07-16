package entity;

import java.util.Arrays;
import java.util.HashMap;

public class FR {
    public String FRname;
    public String FRcontent;
    public int relate1;
    public int relate2;
    public int relate3;
    public int reliability1;
    public int reliability2;
    public int reliability3;
    public HashMap<String, Integer> words = new HashMap<>();

    public void excludeNoise() {
        split();
        for (String word : Dic.dic) {
            words.remove(word);
        }
    }

    private void split() {
        FRcontent = FRcontent.replace(".", "");
        FRcontent = FRcontent.replaceAll("\\s+", " ");
        for (String word : Arrays.asList(FRcontent.split(" "))) {
            word = word.toLowerCase();
            if (words.containsKey(word)) words.put(word, words.get(word) + 1);
            else words.put(word, 1);
        }
        words.remove("");
    }

    public double calculateRelate(HashMap<String, Integer> map) {
        int count = 0;
        for (String word : words.keySet()
                ) {
            if (map.containsKey(word)) count++;
        }
        return (double) count / words.size();
    }

    public int calculateReliability(HashMap<String, Integer> map) {
        int count = 0;
        for (String word : words.keySet()
                ) {
            if (map.containsKey(word)) count += map.get(word);
        }
        return count;
    }

    public double calculateSimilarity(int id) {
        double sim = 0;
        int count = 0;
        switch (id) {
            case 1:
                for (FR fr : FRs.FRlist) {
                    if (fr.relate1 == 1 && !fr.FRname.equals(FRname)) {
                        sim += setBasedSimilarity(fr.words);
                        count++;
                    }
                }
                sim += setBasedSimilarity(NFRs.NFRlist.get(0).words);
                break;
            case 2:
                for (FR fr : FRs.FRlist) {
                    if (fr.relate2 == 1 && !fr.FRname.equals(FRname)) {
                        sim += setBasedSimilarity(fr.words);
                        count++;
                    }
                }
                sim += setBasedSimilarity(NFRs.NFRlist.get(1).words);
                break;
            case 3:
                for (FR fr : FRs.FRlist) {
                    if (fr.relate3 == 1 && !fr.FRname.equals(FRname)) {
                        sim += setBasedSimilarity(fr.words);
                        count++;
                    }
                }
                sim += setBasedSimilarity(NFRs.NFRlist.get(2).words);
                break;
        }
        return sim / count;
    }

    private double setBasedSimilarity(HashMap<String, Integer> otherWords) {
        int size1 = words.size();
        int size2 = otherWords.size();
        int count = 0;
        for (String word : words.keySet()) {
            if (otherWords.containsKey(word)) count++;
        }
        return (double) 2.0 * count / (size1 + size2);
    }

    public double calculateSimByTFIDF(int id) {
        double sim = 0;
        switch (id) {
            case 1:
                sim=vecMult(Dic.words_tf_idf1);
                break;
            case 2:
                sim=vecMult(Dic.words_tf_idf2);
                break;
            case 3:
                sim=vecMult(Dic.words_tf_idf3);
                break;
        }
        return sim;
    }

    private double vecMult(HashMap<String,Double> otherWords) {
        double up=0;
        double mo1=0;
        double mo2=0;
        for(String word:words.keySet()){
            if(otherWords.containsKey(word))
                up+=words.get(word)*otherWords.get(word);
        }
        for(String word:words.keySet()){
            mo1+=words.get(word)*words.get(word);
        }
        for(String word:otherWords.keySet()){
            mo2+=otherWords.get(word)*otherWords.get(word);
        }
        mo1=Math.sqrt(mo1);
        mo2=Math.sqrt(mo2);
        return up/(mo1*mo2);
    }

}
