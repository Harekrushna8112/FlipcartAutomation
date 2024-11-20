package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    
         
         
            
    public static void  searchForProduct(WebDriver driver, By locator, String product) {
        Boolean success;
        try {
            WebDriverWait wait = new WebDriverWait((WebDriver) driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement searchbox = driver.findElement(locator);
            searchbox.clear();
            searchbox.sendKeys(product);
            searchbox.sendKeys(Keys.ENTER);
            success=true;        
        } catch (Exception e) {
            System.out.println("Exception Occured! "+ e.getMessage());
            success= false;    
        }
    }

    public static void clicElement (WebDriver driver, By locator){
        System.out.println("Clicking");
        Boolean success;
        try{
            WebDriverWait wait = new WebDriverWait((WebDriver) driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement click = driver.findElement(locator);
            click.click();
            success= true;
        }catch (Exception e){
            System.out.println("Exception Occured! "+ e.getMessage());
            success= false;
        }
    }

    public static Boolean rating(WebDriver driver,By locator){
        Boolean success;
        try{
            int count=0;
            List<WebElement> starRatings= driver.findElements(locator);
            for(WebElement starRating : starRatings){
                String rating = starRating.getText();
                count++;

            }
            System.out.println("Count of StarRating less then or equal to 4.0 is  :   "+count);
            success = true;

        }catch (Exception e){
            System.out.println("Exception Occured! "+ e.getMessage());
            success = false;
        }
        return success;
    }

    public static Boolean ptpdIphone(WebDriver driver, By locator, int discount){
        Boolean success;
        try{
            WebDriverWait wait = new WebDriverWait((WebDriver) driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            List<WebElement> productrows = driver.findElements(locator);
            HashMap<String,String> iphoneptpdmap= new HashMap<>();
            for(WebElement productrow : productrows){
                Thread.sleep(1000);
                try{
                    String discountpercent = productrow.findElement(By.xpath(".//div[@class='UkUFwK']")).getText();
                    // System.out.println(discountpercent);
                    int discountValue = Integer.parseInt(discountpercent.replaceAll("[^\\d]",""));
                    // System.out.println(discountValue);
                    if(discountValue>discount){
                         String iphoneTitle= productrow.findElement(By.xpath(".//div[@class='KzDlHZ']")).getText();
                         iphoneptpdmap.put(discountpercent,iphoneTitle);
                    }
                }catch(Exception e){
                // e.printStackTrace();
                }
            }

            for(Map.Entry<String,String>iphoneptpd : iphoneptpdmap.entrySet()){
                System.out.println("Iphone discount percentage :: "+iphoneptpd.getKey() + "And its title is ::  "+ iphoneptpd.getValue() );

            }
            success=true;
        }catch(Exception e){
            System.out.println("Exception Occured! ");
            // e.printStackTrace();
            success= false;
        }
        return success;
    }

    public static Boolean printTitleAndImageUrlOfcoffeeMug(WebDriver driver,By locator){
        Boolean success;
        try{
            // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            List<WebElement>userReviewElements= driver.findElements(locator);
            Set<Integer> userReviewSet= new HashSet<>();
            for(WebElement userReviewElement : userReviewElements){
                String review = userReviewElement.getText();
                // System.out.println("Review are  :    "+review);
                int userReview = Integer.parseInt(review.replaceAll("[^\\d]",""));
                // System.out.println("In integer review are  :  "+userReview);
                userReviewSet.add(userReview);
                
            }
            List<Integer>userReviewCountList = new ArrayList<>(userReviewSet);
            Collections.sort(userReviewCountList,Collections.reverseOrder());
            // System.out.println("User review count are  :  "+userReviewCountList);
            NumberFormat numberFormat=NumberFormat.getInstance(Locale.US);
            LinkedHashMap<String,String>productDetailsMap = new LinkedHashMap<>();
            for(int i =0;i<5;i++){
                String formattedUserReviewCount = "("+numberFormat.format(userReviewCountList.get(i))+")";
                String productTitle = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'" +formattedUserReviewCount+ "')]/../../a[@class='wjcEIp']")).getText();
                String productImageUrl= driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'" +formattedUserReviewCount+ "')]/../..//img[@class='DByuf4']")).getAttribute("src");
                String hightestReviewCountAndProductTitle= String.valueOf(i+1)+"/ hightest review count : "+formattedUserReviewCount+", Title of the product : "+productTitle+ ", Image url is : "+productImageUrl;
                productDetailsMap.put(hightestReviewCountAndProductTitle,productImageUrl);
            }
            for(Map.Entry<String,String>productDetails : productDetailsMap.entrySet()){
                System.out.println(productDetails.getKey()+"product image URL is : "+productDetails.getValue());
            }
            success=true;
        }catch(Exception e){
            System.out.println("Exception Occured! ");
            // e.printStackTrace();
            success= false;
        }
        return success;
    }



}
// List<WebElement> ratings= driver.findElements(By.xpath("//div[@class='XQDdHH']"));
        // int count = 0;
        // for(WebElement rating : ratings){
        //     String value = rating.getText();
        //     String numericString = value.replaceAll("[^0-9.]", "");
        //     if(!numericString.isEmpty()){
        //         double num = Double.parseDouble(numericString);
        //         if(num<=4.0){
        //             count++;
        //         }
        //     }
            
        // }
        // System.out.println("Product less then or equal to 4 star is   : "+count);