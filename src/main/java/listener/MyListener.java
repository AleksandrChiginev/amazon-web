package listener;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyListener extends TestListenerAdapter {
    private static final Logger LOGGER = Logger.getLogger(MyListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("Test class started: " + iTestResult.getTestClass().getName());
        LOGGER.info("Test started: " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info("Test SUCCESS: " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        makeScreenshot(iTestResult);
        LOGGER.error("Test FAILED: " + iTestResult.getName());
        if (iTestResult.getThrowable() != null) {
            iTestResult.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot(ITestResult iTestResult) {
        byte[] bytesScreenshot = ((TakesScreenshot) iTestResult.getTestContext().getAttribute("webdriver")).getScreenshotAs(OutputType.BYTES);

        File screenshot = ((TakesScreenshot) iTestResult.getTestContext().getAttribute("webdriver"))
                .getScreenshotAs(OutputType.FILE);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");

        try {
            FileUtils.copyFile(screenshot, new File("src/main/logFiles/screenshots/screenshot_" + iTestResult.getName() + "_" + simpleDateFormat.format(new Date()) + ".png"));
        } catch (IOException e) {
            LOGGER.warn("Screenshot was NOT saved");
            e.printStackTrace();
        }
        return bytesScreenshot;
    }
}