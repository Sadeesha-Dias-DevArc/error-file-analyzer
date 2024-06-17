import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextFileGenerator {
    public static void generateTextFile(String analysisResult, String fileName) {
        String dest = "../textfiles/AnalysisReport.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            // Write header
            writer.write("Error File High Level Overview Analysis");
            writer.newLine();
            writer.newLine();
            
            // Write analyzed date and time
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("Analyzed Date and Time: " + currentDateTime);
            writer.newLine();
            
            // Write file name
            writer.write("Analyzed File: " + fileName);
            writer.newLine();
            writer.newLine();
            
            // Write analysis details
            writer.write(analysisResult);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
