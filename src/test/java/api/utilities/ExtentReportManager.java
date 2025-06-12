package api.utilities;

import api.utilities.ConfigManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

  
    public void onStart(ITestContext testContext) {
        // Load env configs
        String env = System.getProperty("env", "qa");  //dev, qa, prod
        ConfigManager.load(env);

        // Get shared ExtentReports instance
        extent = ExtentManager.getInstance();

        // Set common system info once
        extent.setSystemInfo("Application", "Books Store API");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", ConfigManager.get("env_name"));
        extent.setSystemInfo("Base URL", ConfigManager.get("base_url"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Tester", "Aamir");
    }


    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName())
                     .createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test Passed");
    }


    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName())
                     .createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkip(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName())
                     .createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

 
    public void onFinish(ITestContext testContext) {
        extent.flush(); // Finalize report
    }
}
