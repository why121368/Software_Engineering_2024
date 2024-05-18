//version1
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//
//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("请提供文件路径作为命令行参数.");
//            return;
//        }
//
//        String filePath = args[0];
//        String content = readFile(filePath);
//        if (content == null) {
//            System.out.println("读取文件失败.");
//            return;
//        }
//
//        Graph graph = createGraph(content);
//        graph.printGraph();
//    }
//
//    private static String readFile(String filePath) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return content.toString().replaceAll("[^a-zA-Z ]", " ").toLowerCase();
//    }
//
//    private static Graph createGraph(String content) {
//        Graph graph = new Graph();
//        String[] words = content.split("\\s+");
//
//        for (int i = 0; i < words.length - 1; i++) {
//            if (!words[i].isEmpty() && !words[i + 1].isEmpty()) {
//                graph.addEdge(words[i], words[i + 1]);
//            }
//        }
//
//        return graph;
//    }
//}
//
//class Graph {
//    private Map<String, Map<String, Integer>> adjMap;
//
//    public Graph() {
//        this.adjMap = new HashMap<>();
//    }
//
//    public void addEdge(String from, String to) {
//        adjMap.putIfAbsent(from, new HashMap<>());
//        adjMap.get(from).merge(to, 1, Integer::sum);
//    }
//
//    public void printGraph() {
//        for (String from : adjMap.keySet()) {
//            Map<String, Integer> neighbors = adjMap.get(from);
//            for (String to : neighbors.keySet()) {
//                int weight = neighbors.get(to);
//                System.out.println(from + " -> " + to + " [weight=" + weight + "]");
//            }
//        }
//    }
//}

//version2
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//
//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("请提供文件路径作为命令行参数.");
//            return;
//        }
//
//        String filePath = args[0];
//        String content = readFile(filePath);
//        if (content == null) {
//            System.out.println("读取文件失败.");
//            return;
//        }
//
//        Graph graph = createGraph(content);
//        graph.printGraph();
//    }
//
//    private static String readFile(String filePath) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return content.toString().replaceAll("[^a-zA-Z ]", " ").toLowerCase();
//    }
//
//    private static Graph createGraph(String content) {
//        Graph graph = new Graph();
//        String[] words = content.split("\\s+");
//
//        for (int i = 0; i < words.length - 1; i++) {
//            if (!words[i].isEmpty() && !words[i + 1].isEmpty()) {
//                graph.addEdge(words[i], words[i + 1]);
//            }
//        }
//
//        return graph;
//    }
//}
//
//class Graph {
//    private Map<String, Map<String, Integer>> adjMap;
//
//    public Graph() {
//        this.adjMap = new HashMap<>();
//    }
//
//    public void addEdge(String from, String to) {
//        adjMap.putIfAbsent(from, new HashMap<>());
//        adjMap.get(from).merge(to, 1, Integer::sum);
//    }
//
//    public void printGraph() {
//        System.out.println("有向图的邻接表表示法:");
//        for (String from : adjMap.keySet()) {
//            Map<String, Integer> neighbors = adjMap.get(from);
//            System.out.print(from + " -> ");
//            for (String to : neighbors.keySet()) {
//                int weight = neighbors.get(to);
//                System.out.print(to + " [weight=" + weight + "] ");
//            }
//            System.out.println();
//        }
//    }
//}

//version3
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("请提供文件路径作为命令行参数.");
//            return;
//        }
//
//        String filePath = args[0];
//        String content = readFile(filePath);
//        if (content == null) {
//            System.out.println("读取文件失败.");
//            return;
//        }
//
//        Graph graph = createGraph(content);
//        graph.printGraph();
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("请输入要查找桥接词的单词对（例如：word1 word2），或输入'quit'退出：");
//            String input = scanner.nextLine();
//            if (input.equals("quit")) {
//                break;
//            }
//            String[] words = input.split(" ");
//            if (words.length == 2) {
//                graph.findBridgeWords(words[0], words[1]);
//            } else {
//                System.out.println("输入格式错误.");
//            }
//        }
//        scanner.close();
//    }
//
//    private static String readFile(String filePath) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return content.toString().replaceAll("[^a-zA-Z ]", " ").toLowerCase();
//    }
//
//    private static Graph createGraph(String content) {
//        Graph graph = new Graph();
//        String[] words = content.split("\\s+");
//
//        for (int i = 0; i < words.length - 1; i++) {
//            if (!words[i].isEmpty() && !words[i + 1].isEmpty()) {
//                graph.addEdge(words[i], words[i + 1]);
//            }
//        }
//
//        return graph;
//    }
//}
//
//class Graph {
//    private Map<String, Map<String, Integer>> adjMap;
//
//    public Graph() {
//        this.adjMap = new HashMap<>();
//    }
//
//    public void addEdge(String from, String to) {
//        adjMap.putIfAbsent(from, new HashMap<>());
//        adjMap.get(from).merge(to, 1, Integer::sum);
//    }
//
//    public void printGraph() {
//        System.out.println("有向图的邻接表表示法:");
//        for (String from : adjMap.keySet()) {
//            Map<String, Integer> neighbors = adjMap.get(from);
//            System.out.print(from + " -> ");
//            for (String to : neighbors.keySet()) {
//                int weight = neighbors.get(to);
//                System.out.print(to + " [weight=" + weight + "] ");
//            }
//            System.out.println();
//        }
//    }
//
//    public void findBridgeWords(String word1, String word2) {
//        word1 = word1.toLowerCase();
//        word2 = word2.toLowerCase();
//
//        if (!adjMap.containsKey(word1) || !adjMap.containsKey(word2)) {
//            System.out.println("No " + word1 + " or " + word2 + " in the graph!");
//            return;
//        }
//
//        Set<String> bridgeWords = new HashSet<>();
//        Map<String, Integer> neighborsOfWord1 = adjMap.get(word1);
//
//        for (String neighbor : neighborsOfWord1.keySet()) {
//            if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                bridgeWords.add(neighbor);
//            }
//        }
//
//        if (bridgeWords.isEmpty()) {
//            System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
//        } else {
//            System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".");
//        }
//    }
//}

//version 4
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("请提供文件路径作为命令行参数.");
//            return;
//        }
//
//        String filePath = args[0];
//        String content = readFile(filePath);
//        if (content == null) {
//            System.out.println("读取文件失败.");
//            return;
//        }
//
//        Graph graph = createGraph(content);
//        graph.printGraph();
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("请输入要查找桥接词的单词对（例如：word1 word2），或输入'quit'退出：");
//            String input = scanner.nextLine();
//            if (input.equals("quit")) {
//                break;
//            }
//            String[] words = input.split(" ");
//            if (words.length == 2) {
//                graph.findBridgeWords(words[0], words[1]);
//            } else {
//                System.out.println("输入格式错误.");
//            }
//        }
//
//        while (true) {
//            System.out.println("请输入新文本以插入桥接词，或输入'quit'退出：");
//            String newText = scanner.nextLine();
//            if (newText.equals("quit")) {
//                break;
//            }
//            String updatedText = graph.insertBridgeWords(newText);
//            System.out.println("处理后的新文本：");
//            System.out.println(updatedText);
//        }
//        scanner.close();
//    }
//
//    private static String readFile(String filePath) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return content.toString().replaceAll("[^a-zA-Z ]", " ").toLowerCase();
//    }
//
//    private static Graph createGraph(String content) {
//        Graph graph = new Graph();
//        String[] words = content.split("\\s+");
//
//        for (int i = 0; i < words.length - 1; i++) {
//            if (!words[i].isEmpty() && !words[i + 1].isEmpty()) {
//                graph.addEdge(words[i], words[i + 1]);
//            }
//        }
//
//        return graph;
//    }
//}
//
//class Graph {
//    private Map<String, Map<String, Integer>> adjMap;
//    private Random random;
//
//    public Graph() {
//        this.adjMap = new HashMap<>();
//        this.random = new Random();
//    }
//
//    public void addEdge(String from, String to) {
//        adjMap.putIfAbsent(from, new HashMap<>());
//        adjMap.get(from).merge(to, 1, Integer::sum);
//    }
//
//    public void printGraph() {
//        System.out.println("有向图的邻接表表示法:");
//        for (String from : adjMap.keySet()) {
//            Map<String, Integer> neighbors = adjMap.get(from);
//            System.out.print(from + " -> ");
//            for (String to : neighbors.keySet()) {
//                int weight = neighbors.get(to);
//                System.out.print(to + " [weight=" + weight + "] ");
//            }
//            System.out.println();
//        }
//    }
//
//    public void findBridgeWords(String word1, String word2) {
//        word1 = word1.toLowerCase();
//        word2 = word2.toLowerCase();
//
//        if (!adjMap.containsKey(word1) || !adjMap.containsKey(word2)) {
//            System.out.println("No " + word1 + " or " + word2 + " in the graph!");
//            return;
//        }
//
//        Set<String> bridgeWords = new HashSet<>();
//        Map<String, Integer> neighborsOfWord1 = adjMap.get(word1);
//
//        for (String neighbor : neighborsOfWord1.keySet()) {
//            if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                bridgeWords.add(neighbor);
//            }
//        }
//
//        if (bridgeWords.isEmpty()) {
//            System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
//        } else {
//            System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".");
//        }
//    }
//
//    public String insertBridgeWords(String newText) {
//        StringBuilder result = new StringBuilder();
//        String[] words = newText.toLowerCase().replaceAll("[^a-zA-Z ]", " ").split("\\s+");
//
//        for (int i = 0; i < words.length - 1; i++) {
//            result.append(words[i]).append(" ");
//            String bridgeWord = findRandomBridgeWord(words[i], words[i + 1]);
//            if (bridgeWord != null) {
//                result.append(bridgeWord).append(" ");
//            }
//        }
//        result.append(words[words.length - 1]);
//
//        return result.toString();
//    }
//
//    private String findRandomBridgeWord(String word1, String word2) {
//        if (!adjMap.containsKey(word1) || !adjMap.containsKey(word2)) {
//            return null;
//        }
//
//        List<String> bridgeWords = new ArrayList<>();
//        Map<String, Integer> neighborsOfWord1 = adjMap.get(word1);
//
//        for (String neighbor : neighborsOfWord1.keySet()) {
//            if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                bridgeWords.add(neighbor);
//            }
//        }
//
//        if (bridgeWords.isEmpty()) {
//            return null;
//        }
//
//        return bridgeWords.get(random.nextInt(bridgeWords.size()));
//    }
//}

//version 5.0

//功能需求：随机游走
//        进入该功能时，程序随机的从图中选择一个节点，以此为起点沿出边进行随机遍历，记录经过的所有节点和边，直到出现第一条重复的边为止，或者进入的某个节点不存在出边为止。在遍历过程中，用户也可随时停止遍历。
//        
//        将遍历的节点输出为文本，并以文件形式写入
//        磁盘。
//        
//        例如：
//        –
//        to seek out new life and new worlds to explore
//        strange new civilizations
//        –
//        to explore strange new worlds to explore
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
        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String node : adjMap.keySet()) {
            if (node.equals(start)) {
                distances.put(node, 0);
            } else {
                distances.put(node, Integer.MAX_VALUE);
            }
            nodes.add(node);
        }

        while (!nodes.isEmpty()) {
            String current = nodes.poll();
            if (distances.get(current) == Integer.MAX_VALUE) {
                break;
            }

            Map<String, Integer> neighbors = adjMap.get(current);
            if (neighbors != null) {
                for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                    int alt = distances.get(current) + neighbor.getValue();
                    if (alt < distances.get(neighbor.getKey())) {
                        distances.put(neighbor.getKey(), alt);
                        previousNodes.put(neighbor.getKey(), current);
                        nodes.remove(neighbor.getKey());
                        nodes.add(neighbor.getKey());
                    }
                }
            }
        }

        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No path from " + start + " to " + end);
            return;
        }

        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("Shortest path from " + start + " to " + end + ": " + String.join(" -> ", path) + " (Length: " + distances.get(end) + ")");
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
            writer.write("Nodes visited: " + String.join(" -> ", nodesVisited) + "\n");
            writer.write("Edges visited: " + String.join(", ", edgesVisited) + "\n");
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

//version 5.1
//import java.io.*;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Graph {
//    private final Map<String, Map<String, Integer>> adjMap = new HashMap<>();
//
//    public void addEdge(String from, String to) {
//        adjMap.putIfAbsent(from, new HashMap<>());
//        adjMap.get(from).put(to, adjMap.get(from).getOrDefault(to, 0) + 1);
//    }
//
//    public Map<String, Map<String, Integer>> getAdjMap() {
//        return adjMap;
//    }
//
//    public void findShortestPath(String start, String end) {
//        if (!adjMap.containsKey(start) || !adjMap.containsKey(end)) {
//            System.out.println("No path from " + start + " to " + end);
//            return;
//        }
//
//        Map<String, Integer> distances = new HashMap<>();
//        Map<String, String> previousNodes = new HashMap<>();
//        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));
//
//        for (String node : adjMap.keySet()) {
//            if (node.equals(start)) {
//                distances.put(node, 0);
//            } else {
//                distances.put(node, Integer.MAX_VALUE);
//            }
//            nodes.add(node);
//        }
//
//        while (!nodes.isEmpty()) {
//            String current = nodes.poll();
//            if (distances.get(current) == Integer.MAX_VALUE) {
//                break;
//            }
//
//            Map<String, Integer> neighbors = adjMap.get(current);
//            if (neighbors != null) {
//                for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
//                    int alt = distances.get(current) + neighbor.getValue();
//                    if (alt < distances.get(neighbor.getKey())) {
//                        distances.put(neighbor.getKey(), alt);
//                        previousNodes.put(neighbor.getKey(), current);
//                        nodes.remove(neighbor.getKey());
//                        nodes.add(neighbor.getKey());
//                    }
//                }
//            }
//        }
//
//        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
//            System.out.println("No path from " + start + " to " + end);
//            return;
//        }
//
//        List<String> path = new LinkedList<>();
//        for (String at = end; at != null; at = previousNodes.get(at)) {
//            path.add(at);
//        }
//        Collections.reverse(path);
//
//        System.out.println("Shortest path from " + start + " to " + end + ": " + String.join(" -> ", path) + " (Length: " + distances.get(end) + ")");
//    }
//
//    public void findBridgeWords(String word1, String word2) {
//        if (!adjMap.containsKey(word1) || !adjMap.containsKey(word2)) {
//            System.out.println("No " + word1 + " or " + word2 + " in the graph!");
//            return;
//        }
//
//        Set<String> bridgeWords = new HashSet<>();
//        Map<String, Integer> word1Neighbors = adjMap.get(word1);
//
//        for (String neighbor : word1Neighbors.keySet()) {
//            if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                bridgeWords.add(neighbor);
//            }
//        }
//
//        if (bridgeWords.isEmpty()) {
//            System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
//        } else {
//            System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".");
//        }
//    }
//
//    public void insertBridgeWords(String inputText) {
//        String[] words = inputText.toLowerCase().split("\\s+");
//        Random random = new Random();
//        StringBuilder result = new StringBuilder();
//
//        for (int i = 0; i < words.length - 1; i++) {
//            String word1 = words[i];
//            String word2 = words[i + 1];
//            result.append(word1).append(" ");
//
//            Set<String> bridgeWords = new HashSet<>();
//            Map<String, Integer> word1Neighbors = adjMap.get(word1);
//
//            if (word1Neighbors != null) {
//                for (String neighbor : word1Neighbors.keySet()) {
//                    if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                        bridgeWords.add(neighbor);
//                    }
//                }
//
//                if (!bridgeWords.isEmpty()) {
//                    String bridgeWord = bridgeWords.stream().skip(random.nextInt(bridgeWords.size())).findFirst().orElse("");
//                    result.append(bridgeWord).append(" ");
//                }
//            }
//        }
//
//        result.append(words[words.length - 1]);
//        System.out.println("New text with bridge words: " + result);
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Please provide the file path as the first argument.");
//            return;
//        }
//
//        String filePath = args[0];
//        Graph graph = new Graph();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            String prevWord = null;
//            while ((line = reader.readLine()) != null) {
//                line = line.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
//                String[] words = line.split("\\s+");
//                for (String word : words) {
//                    if (word.isEmpty()) continue;
//                    if (prevWord != null) {
//                        graph.addEdge(prevWord, word);
//                    }
//                    prevWord = word;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Map<String, Map<String, Integer>> adjMap = graph.getAdjMap();
//        System.out.println("Graph:");
//        for (Map.Entry<String, Map<String, Integer>> entry : adjMap.entrySet()) {
//            System.out.print(entry.getKey() + " -> ");
//            List<String> neighbors = entry.getValue().entrySet().stream()
//                    .map(e -> e.getKey() + "(" + e.getValue() + ")")
//                    .collect(Collectors.toList());
//            System.out.println(String.join(", ", neighbors));
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\nSelect an option:");
//            System.out.println("1. Find bridge words");
//            System.out.println("2. Insert bridge words in new text");
//            System.out.println("3. Find shortest path between two words");
//            System.out.println("4. Exit");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();  // Consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter two words to find bridge words:");
//                    String word1 = scanner.next();
//                    String word2 = scanner.next();
//                    graph.findBridgeWords(word1, word2);
//                    break;
//                case 2:
//                    System.out.println("Enter a new text:");
//                    String newText = scanner.nextLine();
//                    graph.insertBridgeWords(newText);
//                    break;
//                case 3:
//                    System.out.println("Enter two words to find the shortest path:");
//                    word1 = scanner.next();
//                    word2 = scanner.next();
//                    graph.findShortestPath(word1, word2);
//                    break;
//                case 4:
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//}

//version 5.2
//import java.io.*;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Graph {
//    private final Map<String, Map<String, Integer>> adjMap = new HashMap<>();
//    private final Map<String, Integer> distances = new HashMap<>(); // 添加距离变量
//
//    // 添加初始化距离方法
//    public void initializeDistances(String start) {
//        for (String node : adjMap.keySet()) {
//            if (node.equals(start)) {
//                distances.put(node, 0);
//            } else {
//                distances.put(node, Integer.MAX_VALUE);
//            }
//        }
//    }
//
//    public void addEdge(String from, String to) {
//        adjMap.putIfAbsent(from, new HashMap<>());
//        adjMap.get(from).put(to, adjMap.get(from).getOrDefault(to, 0) + 1);
//    }
//
//    public Map<String, Map<String, Integer>> getAdjMap() {
//        return adjMap;
//    }
//
//    public void findShortestPath(String start, String end) {
//        initializeDistances(start);
//
//        if (!adjMap.containsKey(start) || !adjMap.containsKey(end)) {
//            System.out.println("No path from " + start + " to " + end);
//            return;
//        }
//
//        if (!adjMap.containsKey(start) || !adjMap.containsKey(end)) {
//            System.out.println("No path from " + start + " to " + end);
//            return;
//        }
//
//        Map<String, Integer> distances = new HashMap<>();
//        Map<String, String> previousNodes = new HashMap<>();
//        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));
//
//        for (String node : adjMap.keySet()) {
//            if (node.equals(start)) {
//                distances.put(node, 0);
//            } else {
//                distances.put(node, Integer.MAX_VALUE);
//            }
//            nodes.add(node);
//        }
//
//        while (!nodes.isEmpty()) {
//            String current = nodes.poll();
//            if (distances.get(current) == Integer.MAX_VALUE) {
//                break;
//            }
//
//            Map<String, Integer> neighbors = adjMap.get(current);
//            if (neighbors != null) {
//                for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
//                    int alt = distances.get(current) + neighbor.getValue();
//                    if (alt < distances.get(neighbor.getKey())) {
//                        distances.put(neighbor.getKey(), alt);
//                        previousNodes.put(neighbor.getKey(), current);
//                        nodes.remove(neighbor.getKey());
//                        nodes.add(neighbor.getKey());
//                    }
//                }
//            }
//        }
//
//        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
//            System.out.println("No path from " + start + " to " + end);
//            return;
//        }
//
//        List<String> path = new LinkedList<>();
//        for (String at = end; at != null; at = previousNodes.get(at)) {
//            path.add(at);
//        }
//        Collections.reverse(path);
//
//        System.out.println("Shortest path from " + start + " to " + end + ": " + String.join(" -> ", path) + " (Length: " + distances.get(end) + ")");
//    }
//
//    public void findBridgeWords(String word1, String word2) {
//        if (!adjMap.containsKey(word1) || !adjMap.containsKey(word2)) {
//            System.out.println("No " + word1 + " or " + word2 + " in the graph!");
//            return;
//        }
//
//        Set<String> bridgeWords = new HashSet<>();
//        Map<String, Integer> word1Neighbors = adjMap.get(word1);
//
//        for (String neighbor : word1Neighbors.keySet()) {
//            if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                bridgeWords.add(neighbor);
//            }
//        }
//
//        if (bridgeWords.isEmpty()) {
//            System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
//        } else {
//            System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".");
//        }
//    }
//
//    public void insertBridgeWords(String inputText) {
//        String[] words = inputText.toLowerCase().split("\\s+");
//        Random random = new Random();
//        StringBuilder result = new StringBuilder();
//
//        for (int i = 0; i < words.length - 1; i++) {
//            String word1 = words[i];
//            String word2 = words[i + 1];
//            result.append(word1).append(" ");
//
//            Set<String> bridgeWords = new HashSet<>();
//            Map<String, Integer> word1Neighbors = adjMap.get(word1);
//
//            if (word1Neighbors != null) {
//                for (String neighbor : word1Neighbors.keySet()) {
//                    if (adjMap.containsKey(neighbor) && adjMap.get(neighbor).containsKey(word2)) {
//                        bridgeWords.add(neighbor);
//                    }
//                }
//
//                if (!bridgeWords.isEmpty()) {
//                    String bridgeWord = bridgeWords.stream().skip(random.nextInt(bridgeWords.size())).findFirst().orElse("");
//                    result.append(bridgeWord).append(" ");
//                }
//            }
//        }
//
//        result.append(words[words.length - 1]);
//        System.out.println("New text with bridge words: " + result);
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Please provide the file path as the first argument.");
//            return;
//        }
//
//        String filePath = args[0];
//        Graph graph = new Graph();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            String prevWord = null;
//            while ((line = reader.readLine()) != null) {
//                line = line.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
//                String[] words = line.split("\\s+");
//                for (String word : words) {
//                    if (word.isEmpty()) continue;
//                    if (prevWord != null) {
//                        graph.addEdge(prevWord, word);
//                    }
//                    prevWord = word;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Map<String, Map<String, Integer>> adjMap = graph.getAdjMap();
//        System.out.println("Graph:");
//        for (Map.Entry<String, Map<String, Integer>> entry : adjMap.entrySet()) {
//            System.out.print(entry.getKey() + " -> ");
//            List<String> neighbors = entry.getValue().entrySet().stream()
//                    .map(e -> e.getKey() + "(" + e.getValue() + ")")
//                    .collect(Collectors.toList());
//            System.out.println(String.join(", ", neighbors));
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\nSelect an option:");
//            System.out.println("1. Find bridge words");
//            System.out.println("2. Insert bridge words in new text");
//            System.out.println("3. Find shortest path between two words");
//            System.out.println("4. Exit");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();  // Consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter two words to find bridge words:");
//                    String word1 = scanner.next();
//                    String word2 = scanner.next();
//                    graph.findBridgeWords(word1, word2);
//                    break;
//                case 2:
//                    System.out.println("Enter a new text:");
//                    String newText = scanner.nextLine();
//                    graph.insertBridgeWords(newText);
//                    break;
//                case 3:
//                    System.out.println("Enter two words to find the shortest path:");
//                    word1 = scanner.next();
//                    word2 = scanner.next();
//                    graph.findShortestPath(word1, word2);
//                    break;
//                case 4:
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//}
