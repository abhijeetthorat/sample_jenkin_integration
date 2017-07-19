package com.fb.web;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fb.object.HomePage;
import com.fb.object.TopicsPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class FbTest {

	protected static AndroidDriver driver;
	Logger logger = Logger.getLogger("devpinoyLogger");
	Properties prop = new Properties();
	HomePage home;
	TopicsPage topics;

	@BeforeClass
	public void setUp() throws Exception {
		// Set up desired capabilities and pass the Android app-activity and
		// app-package to Appium
		File appDir=new File("/home/jombay/Desktop/appium/");
		File app = new File(appDir, "com.aza.androidtutorials.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "4.4.2");
		capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.aza.androidtutorials");
		// This package name of your app (you can get it from apk info app)
		capabilities.setCapability("appActivity", "com.firstapp.androidtutorials.MainActivity");
	//	capabilities.setCapability("app", "com.aza.androidtutorials.apk");
		// This is Launcher activity of your app
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@Test(priority = 1)
	public void verifyHomeScreen() throws Exception {
		logger.info("TheApplication is Launced !!!");
		home = new HomePage(driver);
		home.checkHomeTabs();

	}

  @Test(priority = 2)
	public void verifyHomeTabs() throws IOException, InterruptedException {
		Thread.sleep(2000);
		logger.info("TheApplication is Launced !!!");
		home = new HomePage(driver);
		home.checkHomeScreenItems();
	}

@Test(priority = 3)
	public void startTutorials() throws Exception {
		topics = new TopicsPage(driver);
		topics.verifyStartTutorialsTopics();
		topics.verifyQuestions();
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
