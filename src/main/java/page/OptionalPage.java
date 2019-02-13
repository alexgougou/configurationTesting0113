package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class OptionalPage extends BasePage {

    public OptionalPage addStock(String stockname) {
        //判断股票是否已经存在自选列表中

        if (getStockAll().contains(stockname)) {
            System.out.println("股票已经在自选列表中");
            return this;
        }

        else {
            find(By.id("action_create_cube")).click();
            find(By.className("android.widget.EditText")).sendKeys(stockname);
            find(By.id("follow_btn")).click();
            find(By.id("action_close")).click();
            return this;
        }
    }

    public OptionalPage delStock(String stockname) {
        if (!getStockAll().contains(stockname)){
            System.out.println("股票不在自选列表中，无法删除");
            return this;
        }
        //*[contains(@resource-id, 'portfolio_stockName') and contains(@resource-id, stockname)]
        for (WebElement e : Driver.getCurrentDriver().findElements(By.id("portfolio_stockName"))) {
            if (e.getText().equals(stockname)){
                e.click();
            }

        }
        find(By.xpath("//*[@text='设自选']")).click();
        find(By.xpath("//*[@text='删除自选']")).click();
        find(By.id("action_back")).click();
        return this;
    }

    public ArrayList<String> getStockAll() {
        ArrayList<String> arraystock = new ArrayList<String>();
        for (WebElement e : Driver.getCurrentDriver().findElements(By.id("portfolio_stockName"))) {
            arraystock.add(e.getText());
        }
        return arraystock;
    }
}
