public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Analyzer!");
        UniqueErrorIdentifier.errorAnalyzer();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(("Analysis done!"));
        System.exit(0);
    }
}
