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
        try {
            String[] words = sentence.split(BLANK_SPACE_PATTERN);

            List<WordInfo> wordInfos = getWordInfos(words);

            Map<String, List<WordInfo>> wordInfosMap = getWordInfosMap(wordInfos);

            wordInfos = getOrderedWordInfos(wordInfosMap);

            return createFinalWordInfoSentence(wordInfos);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private String createFinalWordInfoSentence(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String wordInfoSentence = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
            joiner.add(wordInfoSentence);
        }
        return joiner.toString();
    }

    private List<WordInfo> getOrderedWordInfos(Map<String, List<WordInfo>> wordInfosMap) {
        List<WordInfo> wordInfos;
        List<WordInfo> tempWordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfosMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            tempWordInfos.add(wordInfo);
        }
        wordInfos = tempWordInfos;

        wordInfos.sort((fistWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - fistWordInfo.getWordCount());
        return wordInfos;
    }

    private List<WordInfo> getWordInfos(String[] words) {
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfos.add(wordInfo);
        }
        return wordInfos;
    }

    private Map<String, List<WordInfo>> getWordInfosMap(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> wordInfosMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfos) {
            if (!wordInfosMap.containsKey(wordInfo.getValue())) {
                ArrayList<WordInfo> tempWordInfos = new ArrayList<>();
                tempWordInfos.add(wordInfo);
                wordInfosMap.put(wordInfo.getValue(), tempWordInfos);
            } else {
                wordInfosMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordInfosMap;
    }


}
