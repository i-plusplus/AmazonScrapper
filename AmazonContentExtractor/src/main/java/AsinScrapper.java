import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

/**
 * Created by paras.mal on 22/1/17.
 */
public class AsinScrapper {


    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "/home/paras.mal/Downloads/chromedriver");
        // declaration and instantiation of objects/variables
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/tmp/file.csv")));
        WebDriver driver = new ChromeDriver();
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(new File("/home/paras.mal/Documents/amazon.csv")));
        String line = reader.readLine();
        try{
        while(line != null) {
            String asin = line.split(",")[1];
            String baseUrl = "http://www.amazon.in/gp/offer-listing/" + asin;
            driver.get(baseUrl);
            WebElement element = null;
            try{
                element = driver.findElement(By.id("olpTabContent"));
            }catch (Exception e){
                line = reader.readLine();
                continue;
               // element = driver.findElement(By.id("atfResults"));
            }
            element = element.findElement(By.xpath("div/div[2]"));


            List<WebElement> elements = element.findElements(By.xpath("div"));
            String name = driver.findElement(By.id("olpProductDetails")).findElement(By.xpath("div/h1")).getText();
            String customerReviews = "", avgreview = "";
            try {
                customerReviews = driver.findElement(By.id("olpProductDetails")).findElement(By.xpath("div[3]/span/span[3]/a")).getText();
            }catch (Exception e){

            }
            try{
                avgreview = driver.findElement(By.id("olpProductDetails")).findElement(By.xpath("div[3]/span/span[1]/a[2]/i/span")).getAttribute("innerHTML");
            }catch (Exception e){

            }
            int c = 0;
            for (int j = 1;j<elements.size();j++) {

                WebElement e = elements.get(j);
                try {
                    c++;
                    System.out.print(c + "," + asin + "," + name + "," + customerReviews + "," + avgreview + ",");
                    writer.write(c + "," + asin + ",\"" + name + "\",\"" + customerReviews + "\",\"" + avgreview + "\",");
                    try {
                        System.out.print("");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");
                    writer.write(",");
                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div[1]/span[1]/span")).getText() + "\"");
                        writer.write("\"" + e.findElement(By.xpath("div[1]/span[1]/span")).getText() + "\"");
                        } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");
                    writer.write(",");
                    try{
                        System.out.print("\"" + e.findElement(By.className("olpShippingPrice")).findElement(By.tagName("span")).getText() + "\"");
                        writer.write("\"" + e.findElement(By.className("olpShippingPrice")).findElement(By.tagName("span")).getText() + "\"");
                    }catch (Exception ex){
                        System.out.print("exception");
                    }
                    System.out.print(",");
                    writer.write(",");
                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div[3]/h3/span/a")).getText() + "\"");
                        writer.write("\"" + e.findElement(By.xpath("div[3]/h3/span/a")).getText() + "\"");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");
                    }
                    System.out.print(",");
                    writer.write(",");
                    try {
                        System.out.print("\"" + e.findElement(By.xpath("div[3]/p")).getText() + "\"");
                        writer.write("\"" + e.findElement(By.xpath("div[3]/p")).getText() + "\"");
                    } catch (Exception ex) {
                        System.out.print("exceptoin");

                    }
                    System.out.println();
                    writer.newLine();
                } catch (Exception exp) {

                }
            }
            line = reader.readLine();

        }}catch (Exception ex){

        }
        writer.flush();
        writer.close();
        driver.close();

        // exit the program explicitly
        System.exit(0);
    }


}
