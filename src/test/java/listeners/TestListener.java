package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    // Using LoggerFactory from SLF4J to create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: {}", result.getName());
        logger.error("Exception: ", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Skipped: {}", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("Test Failed but within success percentage: {}", result.getName());
    }

}
