import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UniqueErrorIdentifier {
    public static void errorAnalyzer() {
        // Open a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the file to analyze");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting...");
            return;
        }

        File file = fileChooser.getSelectedFile();
        StringBuilder inputStringBuilder = new StringBuilder();

        // Read the file content
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        String input = inputStringBuilder.toString();
        HashMap<String, Integer> errorCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("-1/NO.{0,50}");
        Matcher matcher = pattern.matcher(input);
        int totalErrorCount = 0;

        while (matcher.find()) {
            String error = matcher.group();
            errorCounts.put(error, errorCounts.getOrDefault(error,0) + 1);
            totalErrorCount++;
        }

        System.err.println("Total error count: " + totalErrorCount);
        System.out.println("Number of unique errror: " + errorCounts.size());
        System.out.println("Unique errors and their counts: ");
        for (HashMap.Entry<String, Integer> entry : errorCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
}
