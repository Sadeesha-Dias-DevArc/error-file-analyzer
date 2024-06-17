public class ErrorAnalysisResult {
    private String analysisData;
    private String fileName;

    public ErrorAnalysisResult(String analysisData, String fileName) {
        this.analysisData = analysisData;
        this.fileName = fileName;
    }

    public String getAnalysisData() {
        return analysisData;
    }

    public String getFileName() {
        return fileName;
    }
}
