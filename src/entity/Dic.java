package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Dic {
    public static ArrayList<String> dic = new ArrayList<>(Arrays.asList("the","of","be","on","shall","to","will","in","of",
            "and","for","no","a","an","by","are","not","there","from","if"));
    public static HashMap<String,Integer> wordsOfIns1= new HashMap<>();
    public static HashMap<String,Integer> wordsOfIns2= new HashMap<>();
    public static HashMap<String,Integer> wordsOfIns3= new HashMap<>();

    public static void initWords(){
        addAllWords(wordsOfIns1,NFRs.NFRlist.get(0).words);
        addAllWords(wordsOfIns2,NFRs.NFRlist.get(1).words);
        addAllWords(wordsOfIns3,NFRs.NFRlist.get(2).words);
        for (FR fr:FRs.FRlist) {
            if(fr.relate1==1)addAllWords(wordsOfIns1,fr.words);
            if(fr.relate2==1)addAllWords(wordsOfIns2,fr.words);
            if(fr.relate3==1)addAllWords(wordsOfIns3,fr.words);
        }


    }

    private static void addAllWords(HashMap<String,Integer> words, HashMap<String,Integer> NEWwords){
        for (String word:NEWwords.keySet()) {
            if(words.containsKey(word))words.put(word,words.get(word)+NEWwords.get(word));
            else words.put(word,NEWwords.get(word));
        }
    }
}
