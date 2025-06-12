package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static final String REPORT_FOLDER = "reports";

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        try {
        	
            // Ensure folder exists
            new File(REPORT_FOLDER).mkdirs();

            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportPath = REPORT_FOLDER + "/Test-Report-" + timestamp + ".html";

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("BookStore_RestAssured_AutomationReport");
            sparkReporter.config().setReportName("Book Store API");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ExtentReports: " + e.getMessage());
        }
    }
}
