package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dic {
    public static ArrayList<String> dic = new ArrayList<>(Arrays.asList("the","of","be","on","shall","to","will","in","of",
            "and","for","no","a","an","by","are","not","there","from","if","with","this","their","used","using",
            "all","other","can"));
    public static HashMap<String,Integer> wordsOfIns1= new HashMap<>();
    public static HashMap<String,Integer> wordsOfIns2= new HashMap<>();
    public static HashMap<String,Integer> wordsOfIns3= new HashMap<>();

    public static HashMap<String,Integer> words_tf = new HashMap<>();
    public static HashMap<String,Integer> words_df = new HashMap<>();
    public static HashMap<String,Double> words_idf= new HashMap<>();
    public static HashMap<String,Double> words_tf_idf1= new HashMap<>();
    public static HashMap<String,Double> words_tf_idf2= new HashMap<>();
    public static HashMap<String,Double> words_tf_idf3= new HashMap<>();

    public static void initWords(){
        addAllWords(wordsOfIns1,NFRs.NFRlist.get(0).words);
        addAllWords(wordsOfIns2,NFRs.NFRlist.get(1).words);
        addAllWords(wordsOfIns3,NFRs.NFRlist.get(2).words);
        for (FR fr:FRs.FRlist) {
            if(fr.relate1==1)addAllWords(wordsOfIns1,fr.words);
            if(fr.relate2==1)addAllWords(wordsOfIns2,fr.words);
            if(fr.relate3==1)addAllWords(wordsOfIns3,fr.words);
        }

        addAllWords(words_tf,wordsOfIns1);
        addAllWords(words_tf,wordsOfIns2);
        addAllWords(words_tf,wordsOfIns3);

        initDF(words_df,wordsOfIns1);
        initDF(words_df,wordsOfIns2);
        initDF(words_df,wordsOfIns3);

        initIDF();

        initTFIDF();

    }

    private static void addAllWords(HashMap<String,Integer> words, HashMap<String,Integer> NEWwords){
        for (String word:NEWwords.keySet()) {
            if(words.containsKey(word))words.put(word,words.get(word)+NEWwords.get(word));
            else words.put(word,NEWwords.get(word));
        }
    }

    private static void initDF(HashMap<String,Integer> words, HashMap<String,Integer> NEWwords){
        for(String word:NEWwords.keySet()){
            if(words.containsKey(word))words.put(word,words.get(word)+1);
            else words.put(word,1);
        }
    }

    private static void initIDF(){
        int count =0;
        for(String word:words_tf.keySet()){
            count+=words_tf.get(word);
        }
        for(String word:words_df.keySet()){
            words_idf.put(word,Math.pow(Math.log(count/words_df.get(word)),3));
        }
    }

    private static void initTFIDF(){
        for(String word:words_tf.keySet()){
            if(wordsOfIns1.containsKey(word))
                words_tf_idf1.put(word,wordsOfIns1.get(word)*words_idf.get(word));
            else words_tf_idf1.put(word,0.0);
        }
        for(String word:words_tf.keySet()){
            if(wordsOfIns2.containsKey(word))
                words_tf_idf2.put(word,wordsOfIns2.get(word)*words_idf.get(word));
            else words_tf_idf2.put(word,0.0);
        }
        for(String word:words_tf.keySet()){
            if(wordsOfIns3.containsKey(word))
                words_tf_idf3.put(word,wordsOfIns3.get(word)*words_idf.get(word));
            else words_tf_idf3.put(word,0.0);
        }
    }
}
