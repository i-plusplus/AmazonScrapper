/**
 * Created by paras.mal on 22/1/17.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Scrapper {

    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "/home/paras.mal/Downloads/chromedriver");
        // declaration and instantiation of objects/variables
        WebDriver driver = new ChromeDriver();
        int count = 0;
        for(int i = 1;i<20;i++) {
            String baseUrl = "http://amazon.in/s/ref=lp_3403628031_nr_n_2?rh=n%3A1984443031%2Cn%3A%211984444031%2Cn%3A3403628031%2Cn%3A3403857031&page=" + i;
            String expectedTitle = "Welcome: Mercury Tours";
            String actualTitle = "";

            // launch Firefox and direct it to the Base URL
            driver.get(baseUrl);

            WebElement element = null;
            try{
                element = driver.findElement(By.id("mainResults"));
            }catch (Exception e){
                element = driver.findElement(By.id("atfResults"));
            }
            element = element.findElement(By.tagName("ul"));
            List<WebElement> elements = element.findElements(By.tagName("li"));


            for (WebElement e : elements) {
                try {
                    count++;
                    System.out.print(count + ",");
                    try {
                        System.out.print(e.getAttribute("data-asin"));
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");

                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div/div[3]/div/a/h2")).getText() + "\"");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");
                    try {
                        System.out.print("\"" + e.findElement(By.className("a-color-price")).getText() + "\"");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");
                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div/div[3]/div[2]/span[2]")).getText() + "\"");

                    } catch (Exception ex) {
                        System.out.print("exceptoin");

                    }
                    System.out.print(",");
                    int size = e.findElement(By.xpath("div")).findElements(By.xpath("div")).size();

                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div/div[" + size + "]/a")).getText() + "\"");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");
                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div/div[" + size + "]/span/span/a/i[1]/span")).getAttribute("innerHTML") + "\"");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }

                    System.out.println();
                } catch (Exception exp) {

                }
            }

        }
        driver.close();

        // exit the program explicitly
        System.exit(0);
    }



}
