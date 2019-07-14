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
        for (String word:Dic.dic) {
            words.remove(word);
        }
    }

    private void split() {
        FRcontent = FRcontent.replace(".", "");
        FRcontent = FRcontent.replaceAll("\\s+", " ");
        for (String word : Arrays.asList(FRcontent.split(" "))) {
            word=word.toLowerCase();
            if (words.containsKey(word)) words.put(word, words.get(word) + 1);
            else words.put(word, 1);
        }
        words.remove("");
    }

    public double calculateRelate(HashMap<String,Integer> map){
        int count =0;
        for (String word:words.keySet()
             ) {
            if(map.containsKey(word))count++;
        }
        return (double)count/words.size();
    }

    public int calculateReliability(HashMap<String,Integer> map){
        int count =0;
        for (String word:words.keySet()
                ) {
            if(map.containsKey(word))count+=map.get(word);
        }
        return count;
    }
}
