package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public Logger logger;
    public static WebDriver driver;
    public Properties properties;

    @BeforeClass(groups = {"sanity","regression","master", "data driven"})
    @Parameters({"browser", "os"})

    public void setup(String br, String os) throws IOException {

        FileReader file = new FileReader("/Users/ritiktripathi/IdeaProjects/opencartV1/src/test/resources/config.properties");
        properties= new Properties();
        properties.load(file);

        logger= LogManager.getLogger(this.getClass());

        if(properties.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (br.toLowerCase())
            {
                case "chrome": driver= new ChromeDriver(); break;
                case "safari": driver= new SafariDriver(); break;
                case "firefox": driver= new FirefoxDriver(); break;
                default:
                    System.out.println("Incorrect browser.. check the testNG xml file.."); return;
            }
        }

        if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();

            switch (os.toLowerCase()){
                case "window11": capabilities.setPlatform(Platform.WIN11); break;
                case "linux": capabilities.setPlatform(Platform.LINUX); break;
                case "mac": capabilities.setPlatform(Platform.MAC); break;
                default:
                    System.out.println("No matching os found. Please check related xml file.."); return;
            }

            switch (br.toLowerCase()){
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
                case "safari": capabilities.setBrowserName("safari"); break;
                case "firefox": capabilities.setBrowserName("firefox"); break;
                default:
                    System.out.println(" Invalid browser user name. Please check related xml file..");
            }
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"sanity","regression","master", "data driven"})
   public void tearDown(){
        driver.quit();
    }

    public String getRandomTextString(){
        return RandomStringUtils.randomAlphabetic(5);
    }
    public String getRandomNumericString(){
        return RandomStringUtils.randomAlphabetic(10);
    }
    public String getRandomAlphaNumericString(){
        return (RandomStringUtils.randomAlphabetic(3)+RandomStringUtils.randomNumeric(3));
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

//        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";

        String targetFilePath=System.getProperty("user.dir")+ "/screenshots/"+ tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);
        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;

    }

}
