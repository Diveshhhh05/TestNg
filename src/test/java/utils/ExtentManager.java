package utils;  
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
            
            String reportPath = "reports/ExtentReport.html";
            
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            
            reporter.config().setDocumentTitle("Automation Test Report");   
            reporter.config().setReportName("Functional Test Results");     

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Tester", "QA Engineer");
        }
        return extent;
    }
}

