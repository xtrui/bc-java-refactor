import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String BLANK_SPACE_PATTERN = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String BLANK_SPACE = " ";

    public String getResult(String sentence) {

        if (sentence.split(BLANK_SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {

            try {

                String[] words = sentence.split(BLANK_SPACE_PATTERN);

                List<WordInfo> wordInfos = getWordInfos(words);

                Map<String, List<WordInfo>> wordMap = getListMap(wordInfos);

                List<WordInfo> tempWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordMap.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    tempWordInfos.add(wordInfo);
                }
                wordInfos = tempWordInfos;

                wordInfos.sort((fistWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - fistWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordInfo wordInfo : wordInfos) {
                    String wordInfoSentence = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
                    joiner.add(wordInfoSentence);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordInfo> getWordInfos(String[] words) {
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfos.add(wordInfo);
        }
        return wordInfos;
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordMap.containsKey(wordInfo.getValue())) {
                ArrayList wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordMap.put(wordInfo.getValue(), wordInfos);
            } else {
                wordMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordMap;
    }



}
