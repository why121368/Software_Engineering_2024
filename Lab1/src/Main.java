import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Graph {
    private final Map<String, Map<String, Integer>> adjMap = new HashMap<>();

    public void addEdge(String from, String to) {
        adjMap.putIfAbsent(from, new HashMap<>());
        adjMap.get(from).put(to, adjMap.get(from).getOrDefault(to, 0) + 1);
    }

    public Map<String, Map<String, Integer>> getAdjMap() {
        return adjMap;
    }

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

    public void insertBridgeWords(String inputText) {
        String[] words = inputText.toLowerCase().split("\\s+");
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            result.append(word1).append(" ");

            Set<String> bridgeWords = new HashSet<>();
            Map<String, Integer> word1Neighbors = adjMap.get(word1);

            if (word1Neighbors != null) {
                for (String neighbor : word1Neighbors.keySet()) {
                    if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
                        bridgeWords.add(neighbor);
                    }
                }

                if (!bridgeWords.isEmpty()) {
                    String bridgeWord = bridgeWords.stream().skip(random.nextInt(bridgeWords.size())).findFirst().orElse("");
                    result.append(bridgeWord).append(" ");
                }
            }
        }

        result.append(words[words.length - 1]);
        System.out.println("New text with bridge words: " + result);
    }
    public void randomWalkToFile(String outputPath) {
        Random random = new Random();
        List<String> nodesVisited = new ArrayList<>();
        List<String> edgesVisited = new ArrayList<>();

        String currentNode = adjMap.keySet().stream().skip(random.nextInt(adjMap.size())).findFirst().orElse(null);
        while (currentNode != null) {
            nodesVisited.add(currentNode);

            Map<String, Integer> neighbors = adjMap.get(currentNode);
            if (neighbors == null || neighbors.isEmpty()) {
                break;
            }

            List<String> neighborList = new ArrayList<>(neighbors.keySet());
            String nextNode = neighborList.get(random.nextInt(neighborList.size()));
            edgesVisited.add(currentNode + " -> " + nextNode);

            currentNode = nextNode;
            if ( edgesVisited.contains(nextNode + " -> " + currentNode)) {
                break;  // 遇到重复的边，停止遍历
            }
//            if (nodesVisited.contains(nextNode) || edgesVisited.contains(nextNode + " -> " + currentNode)) {
//                break;  // 遇到重复的边或节点，停止遍历
//            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            //writer.write("Nodes visited: " + String.join(" -> ", nodesVisited) + "\n");
            writer.write("Nodes visited: \n" + String.join(" ", nodesVisited) + "\n");
            // writer.write("Edges visited: " + String.join(", ", edgesVisited) + "\n");
            System.out.println("Random walk output written to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the file path as the first argument.");
            return;
        }

        String filePath = args[0];
        Graph graph = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String prevWord = null;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.isEmpty()) continue;
                    if (prevWord != null) {
                        graph.addEdge(prevWord, word);
                    }
                    prevWord = word;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Map<String, Integer>> adjMap = graph.getAdjMap();
        System.out.println("Graph:");
        for (Map.Entry<String, Map<String, Integer>> entry : adjMap.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            List<String> neighbors = entry.getValue().entrySet().stream()
                    .map(e -> e.getKey() + "(" + e.getValue() + ")")
                    .collect(Collectors.toList());
            System.out.println(String.join(", ", neighbors));
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Find bridge words");
            System.out.println("2. Insert bridge words in new text");
            System.out.println("3. Find shortest path between two words");
            System.out.println("4. Random walk");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter two words to find bridge words:");
                    String word1 = scanner.next();
                    String word2 = scanner.next();
                    graph.findBridgeWords(word1, word2);
                    break;
                case 2:
                    System.out.println("Enter a new text:");
                    String newText = scanner.nextLine();
                    graph.insertBridgeWords(newText);
                    break;
                case 3:
                    System.out.println("Enter two words to find the shortest path:");
                    word1 = scanner.next();
                    word2 = scanner.next();
                    graph.findShortestPath(word1, word2);
                    break;
                case 4:
                    System.out.println("Enter the output file path for random walk:");
                    String outputPath = scanner.nextLine();
                    graph.randomWalkToFile(outputPath);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}