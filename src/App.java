public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Analyzer!");
        String[] result = UniqueErrorIdentifier.errorAnalyzer();

        if (!result[1].isEmpty()) {
            PdfGenerator.printToPDF(result[0], result[1]);
        } else {
            System.out.println(result[0]);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //System.out.println(("Analysis done!"));
        System.exit(0);
    }
}
