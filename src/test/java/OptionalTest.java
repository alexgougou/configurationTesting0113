import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.annotation.Order;
import page.MainPage;
import page.OptionalPage;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTest {
    static MainPage mainPage;
    static OptionalPage optionalPage;

    @BeforeAll
    static void beforeAll() {
        mainPage = MainPage.start();
        optionalPage = mainPage.gotoOptional();

    }

    @ParameterizedTest
    @Order(1)
    @CsvSource({

            "阿里巴巴",
            "搜狗",
            "拼多多",
            "拼多多"
    })
    void addStockTest(String stockname){
        ArrayList<String> arraystockname = new ArrayList<String>();
        arraystockname = optionalPage.getStockAll();
        System.out.println("添加前股票列表" + arraystockname);
        optionalPage.addStock(stockname);
        arraystockname = optionalPage.getStockAll();
        System.out.println("添加后股票列表" + arraystockname);
        assertTrue(arraystockname.contains(stockname));
        //assertThat(content, equalTo(name));
    }

    @ParameterizedTest
    @Order(2)
    @CsvSource({

            "阿里巴巴",
            "搜狗",
            "拼多多",
            "拼多多"
    })
    void delStock(String stockname){
        ArrayList<String> arraystockname = new ArrayList<String>();
        arraystockname = optionalPage.getStockAll();
        System.out.println("删除前股票列表" + arraystockname);
        optionalPage.delStock(stockname);
        arraystockname = optionalPage.getStockAll();
        System.out.println("删除后股票列表" + arraystockname);
        assertTrue(!arraystockname.contains(stockname));
        //assertThat(content, equalTo(name));
    }

}
