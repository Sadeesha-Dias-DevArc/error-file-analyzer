import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UniqueErrorIdentifier {
    public static ErrorAnalysisResult errorAnalyzer() {
        // Open a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the file to analyze");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting...");
            return new ErrorAnalysisResult("No file selected. Exiting...\n", null);
        }

        File file = fileChooser.getSelectedFile();

        // Prompt the user to select the length of the regex pattern
        String[] regexPatternLengthOptions = {"40", "45", "50", "60", "75", "80"};
        String selectOption = (String) JOptionPane.showInputDialog(
            null,
            "Select the length of the pattern:",
            "Pattern Length",
            JOptionPane.QUESTION_MESSAGE,
            null,
            regexPatternLengthOptions,
            regexPatternLengthOptions[2]
        );

        if (selectOption == null) {
            System.out.println("No pattern length selected. Exiting...");
            return new ErrorAnalysisResult("No pattern length selected. Exiting...\n", null);
        }

        int patternLenght = Integer.parseInt(selectOption);

        StringBuilder inputStringBuilder = new StringBuilder();

        // Read the file content
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ErrorAnalysisResult("Error reading file: " + e.getMessage(), null);
        }

        String input = inputStringBuilder.toString();
        HashMap<String, Integer> errorCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("-1/NO.{0," + patternLenght + "}");
        Matcher matcher = pattern.matcher(input);
        int totalErrorCount = 0;

        while (matcher.find()) {
            String error = matcher.group();
            errorCounts.put(error, errorCounts.getOrDefault(error,0) + 1);
            totalErrorCount++;
        }

        StringBuilder result = new StringBuilder();

        System.err.println("Total error count: " + totalErrorCount);
        result.append("Total error count: ").append(totalErrorCount).append("\n");

        System.out.println("Number of unique errror: " + errorCounts.size());
        result.append("Number of unique errors: ").append(errorCounts.size()).append("\n");

        System.out.println("Unique errors and their counts: ");
        result.append("Unique errors and their counts:\n");

        for (HashMap.Entry<String, Integer> entry : errorCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        result.append("Analyzed file: ").append(file.getName()).append("\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the toast message
        ShowToastMessage.toastMessage("Analysis Complete!", 3000);

        return new ErrorAnalysisResult(result.toString(), file.getName());
    }
    
}
