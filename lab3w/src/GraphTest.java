import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BridgeWordsTest {

    private Map<String, Map<String, Integer>> adjMap;
    private BridgeWords bridgeWords;

    @BeforeEach
    void setUp() {
        adjMap = new HashMap<>();
        bridgeWords = new BridgeWords(adjMap);
    }

    @Test
    void testBridgeWordsExist() {
        adjMap.put("hello", Map.of("hi", 1));
        adjMap.put("hi", Map.of("world", 1));
        adjMap.put("world",Map.of());

        // Capture the output
        String result = captureOutput(() -> bridgeWords.findBridgeWords("hello", "world"));
        assertEquals("The bridge words from hello to world are: hi.", result.trim());
    }

    @Test
    void testNoBridgeWords() {
        adjMap.put("start", Map.of("middle", 1));
        adjMap.put("middle", Map.of("end", 1));

        // Capture the output
        String result = captureOutput(() -> bridgeWords.findBridgeWords("start", "middle"));
        assertEquals("No bridge words from start to middle!", result.trim());
    }

    @Test
    void testMultipleBridgeWords() {
        adjMap.put("hello", Map.of("hi", 1, "there", 1));
        adjMap.put("hi", Map.of("world", 1));
        adjMap.put("there", Map.of("world", 1));
        adjMap.put("world",Map.of());

        // Capture the output
        String result = captureOutput(() -> bridgeWords.findBridgeWords("hello", "world"));
        assertTrue(result.trim().contains("The bridge words from hello to world are:"));
        assertTrue(result.contains("hi"));
        assertTrue(result.contains("there"));
    }

    @Test
    void testWord1NotInGraph() {
        adjMap.put("hello", Map.of("hi", 1));
        adjMap.put("hi", Map.of("world", 1));

        // Capture the output
        String result = captureOutput(() -> bridgeWords.findBridgeWords("unknown", "world"));
        assertEquals("No unknown or world in the graph!", result.trim());
    }

    @Test
    void testWord2NotInGraph() {
        adjMap.put("hello", Map.of("hi", 1));
        adjMap.put("hi", Map.of("world", 1));

        // Capture the output
        String result = captureOutput(() -> bridgeWords.findBridgeWords("hello", "unknown"));
        assertEquals("No hello or unknown in the graph!", result.trim());
    }

    @Test
    void testBothWordsNotInGraph() {
        adjMap.put("hello", Map.of("hi", 1));
        adjMap.put("hi", Map.of("world", 1));

        // Capture the output
        String result = captureOutput(() -> bridgeWords.findBridgeWords("unknown1", "unknown2"));
        assertEquals("No unknown1 or unknown2 in the graph!", result.trim());
    }

    private String captureOutput(Runnable runnable) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }

        return out.toString();
    }

    static class BridgeWords {
        private Map<String, Map<String, Integer>> adjMap;

        BridgeWords(Map<String, Map<String, Integer>> adjMap) {
            this.adjMap = adjMap;
        }

        public void findBridgeWords(String word1, String word2) {
            if (!adjMap.containsKey(word1) || !adjMap.containsKey(word2)) {
                System.out.println("No " + word1 + " or " + word2 + " in the graph!");
                return;
            }

            Set<String> bridgeWords = new HashSet<>();
            Map<String, Integer> word1Neighbors = adjMap.get(word1);

            for (String neighbor : word1Neighbors.keySet()) {
                if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
                    bridgeWords.add(neighbor);
                }
            }

            if (bridgeWords.isEmpty()) {
                System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
            } else {
                System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".");
            }
        }
    }
}
