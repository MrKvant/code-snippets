import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> lines = readFromConsole();

        Integer countOfElements = getCountOfElements(lines);
        List<Integer> elements = getElements(lines);

        // Algorithm: O(n)
        Integer result = findMaxLongSequence(elements);

        System.out.println(result);
    }

    private static Integer findMaxLongSequence(List<Integer> elements) {
        int currentMax = 0;
        int longOfSequence = 0;
        for (Integer elem : elements)
        {
            if (elem == 1) {
                ++longOfSequence;
            }
            else {
                currentMax = Math.max(longOfSequence, currentMax);
                longOfSequence = 0;
            }

        }

        // For case if all element are "1"
        currentMax = Math.max(longOfSequence, currentMax);

        return currentMax;
    }


    private static List<String> readFromFile(String fileName) {
        BufferedReader reader;

        List<String> listOfLines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                listOfLines.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return listOfLines;
    }

    private static List<String> readFromConsole() throws IOException {

        List<String> listOfLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        listOfLines.add(reader.readLine());
        int count = Integer.parseInt(listOfLines.get(0));

        while(count-- != 0){
            listOfLines.add(reader.readLine());
        }
        return listOfLines;
    }

    private static void writeToFile(Integer result, String fileName) {
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            writer.append(String.valueOf(result));
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static Integer getCountOfElements(List<String> linesFromFile) {
        return Integer.valueOf(linesFromFile.get(0));
    }

    private static List<Integer> getElements(List<String> linesFromFile) {
        linesFromFile = new ArrayList<>(linesFromFile);
        linesFromFile.remove(0);
        return linesFromFile.stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
    }
}