/*
 *Purpose : Class is implemented for taking screenshots and place those screenshot in necessary directory
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */

package com.zoopla.utility;

import com.zoopla.base.BaseClass;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureScreenshot extends BaseClass {

    /**
     * captureScreenshot method is used to take screenshot
     * @param screenshotName testcase name is giving
     * @param result giving success or failed as input
     */
    public String captureScreenshot(String screenshotName, String result) {
        String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            Allure.addAttachment(screenshotName + "_" + date, "image/png", new FileInputStream(srcFile), "png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String destPath = "./screenshots/" + result + "/" + screenshotName + "_" + date + ".png";
        File destFile = new File(destPath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destPath;
    }
}
