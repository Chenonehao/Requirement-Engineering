package entity;

import java.util.Arrays;
import java.util.HashMap;

public class NFR {
    public String NFRname;
    public String NFRcontent;
    public HashMap<String, Integer> words = new HashMap<>();

    public void excludeNoise() {
        split();
        for (String word:Dic.dic) {
            words.remove(word);
        }
    }

    private void split() {
        NFRcontent = NFRcontent.replace(".", "");
        NFRcontent = NFRcontent.replaceAll("\\s+", " ");
        for (String word : Arrays.asList(NFRcontent.split(" "))) {
            word=word.toLowerCase();
            if (words.containsKey(word)) words.put(word, words.get(word) + 1);
            else words.put(word, 1);
        }
        words.remove("");
    }
}
