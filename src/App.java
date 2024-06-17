public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Analyzer!");
        ErrorAnalysisResult result = UniqueErrorIdentifier.errorAnalyzer();

        if (result.getFileName() != null) {
            TextFileGenerator.generateTextFile(result.getAnalysisData(), result.getFileName());
        } else {
            System.out.println(result.getAnalysisData());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(("Analysis done!"));
        System.exit(0);
    }
}
