import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestPathTest {

    private Map<String, Map<String, Integer>> adjMap = new HashMap<>();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testFindShortestPath_Path1() {
        adjMap.put("A", new HashMap<>());
        adjMap.put("B", new HashMap<>());
        adjMap.put("C", new HashMap<>());
        adjMap.get("A").put("B", 1);
        adjMap.get("B").put("C", 2);

        findShortestPath("A", "C");

        assertEquals("Shortest path from A to C: A -> B -> C (Distance: 3)", outContent.toString().trim());
    }

    @Test
    public void testFindShortestPath_Path2() {
        findShortestPath("A", "C");

        // Assert distances
        assertEquals("No path from A to C", outContent.toString().trim());
    }

    @Test
    public void testFindShortestPath_Path3() {
        adjMap.put("A", new HashMap<>());
        adjMap.put("B", new HashMap<>());
        adjMap.get("A").put("B", 1);

        findShortestPath("A", "C");

        // Assert distances
        assertEquals("No path from A to C", outContent.toString().trim());
    }

    @Test
    public void testFindShortestPath_Path4() {
        findShortestPath("A", "A");

        // Assert distances
        assertEquals("Shortest path from A to A: A (Distance: 0)", outContent.toString().trim());
    }

    private Map<String, Integer> distances = new HashMap<>();
    private Map<String, String> previousNodes = new HashMap<>();
    private PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(node -> distances.getOrDefault(node, Integer.MAX_VALUE)));

    public void findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(node -> distances.getOrDefault(node, Integer.MAX_VALUE)));

        // Initialize distances for all nodes
        for (String node : adjMap.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            adjMap.get(node).keySet().forEach(neighbor -> distances.putIfAbsent(neighbor, Integer.MAX_VALUE));
        }
        distances.put(start, 0);
        nodes.addAll(distances.keySet());

        while (!nodes.isEmpty()) {
            String current = nodes.poll();
            if (current == null || distances.get(current) == Integer.MAX_VALUE) {
                continue; // Skip processing if no more reachable nodes or infinite distance
            }

            Map<String, Integer> neighbors = adjMap.get(current);
            if (neighbors != null) {
                for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                    String neighborNode = neighbor.getKey();
                    int alt = distances.get(current) + neighbor.getValue();
                    if (alt < distances.get(neighborNode)) {
                        distances.put(neighborNode, alt);
                        previousNodes.put(neighborNode, current);
                        nodes.remove(neighborNode);
                        nodes.add(neighborNode); // Update priority queue
                    }
                }
            }
        }

        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No path from " + start + " to " + end);
            return;
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("Shortest path from " + start + " to " + end + ": " + String.join(" -> ", path) + " (Distance: " + distances.get(end) + ")");
    }
}
