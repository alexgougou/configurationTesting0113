import driver.Driver;
import driver.GlobalConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.remote.AugmenterProvider;
import org.springframework.core.annotation.Order;
import page.MainPage;
import page.OptionalPage;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTestWithData {
    static MainPage mainPage;
    static OptionalPage optionalPage;

    @BeforeAll
    static void beforeAll() {
        mainPage = MainPage.start();
        optionalPage = mainPage.gotoOptional();

    }

    @AfterAll
    static void afterAll(){
        Driver.getCurrentDriver().quit();
    }

    @ParameterizedTest
    @Order(2)
    @CsvFileSource(resources = "/data/SearchTest.csv")
    void addStockTest1(String stock){
        ArrayList<String> arraystockname = new ArrayList<String>();
        arraystockname = optionalPage.getStockAll();
        System.out.println("添加前股票列表" + arraystockname);
        optionalPage.addStock(stock);
        arraystockname = optionalPage.getStockAll();
        System.out.println("添加后股票列表" + arraystockname);
        assertTrue(arraystockname.contains(stock));
        //assertThat(content, equalTo(name));
    }

    @ParameterizedTest
    @Order(1)
    @CsvFileSource(resources = "/data/SearchTest.csv")
    void delStockTest(String stock){

        ArrayList<String> arraystockname = new ArrayList<String>();
        arraystockname = optionalPage.getStockAll();
        System.out.println("删除前股票列表" + arraystockname);
        optionalPage.delStock(stock);
        arraystockname = optionalPage.getStockAll();
        System.out.println("删除后股票列表" + arraystockname);
        assertTrue(!arraystockname.contains(stock));
        //assertThat(content, equalTo(name));
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("Getyaml")
    void delStockTest2(String stock){
        ArrayList<String> arraystockname = new ArrayList<String>();
        arraystockname = optionalPage.getStockAll();
        System.out.println("删除的股票为：" +stock);
        System.out.println("删除前股票列表" + arraystockname);
        optionalPage.delStock(stock);
        arraystockname = optionalPage.getStockAll();
        System.out.println("删除后股票列表" + arraystockname);
        assertTrue(!arraystockname.contains(stock));

    }

    static Stream<Arguments> Getyaml(){
        GlobalConfig config = GlobalConfig.load("/data/globalConfig.yaml");
        return Stream.of(Arguments.arguments(config.data.input.get(0)),
                Arguments.arguments(config.data.input.get(1)),
                Arguments.arguments(config.data.input.get(2)),
                Arguments.arguments(config.data.input.get(3)));
    }

}
