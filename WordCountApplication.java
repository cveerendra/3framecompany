package task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCountApplication {
    public static void main(String[] args) 
    {
        String filename = "G:\\advanceJava\\InterviewProblem\\src\\task\\textfile.txt";
        Map<String, Integer> wordCount = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] words = line.split("\\s+");
                for (String word : words) 
                {
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); 
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        Map<String, Integer> sortedWordCount = sortByValue(wordCount);
        for (Map.Entry<String, Integer> entry : sortedWordCount.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static Map<String, Integer> sortByValue(Map<String, Integer> wordCount) 
    {
        Map<String, Integer> sortedWordCount = new TreeMap<>((o1, o2) -> 
        {
            int cmp = wordCount.get(o2) - wordCount.get(o1);
            if (cmp == 0) {
                return o1.compareTo(o2);
            }
            return cmp;
        });
        sortedWordCount.putAll(wordCount);
        return sortedWordCount;
    }
}

