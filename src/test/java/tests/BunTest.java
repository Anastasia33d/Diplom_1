package tests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters(name="Наименование и цена булочки: {0} {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 200.0f},
                {"red bun", 300.0f}
        });
    }

    //Проверка корректности возвращаемого наименования булочки
    @Test
    public void getNameTest() {
        assertEquals("Наименование булочки не соответствует ожиданиям", name, bun.getName());
    }

    //Проверка корректности возвращаемой цены булочки
    @Test
    public void getPriceTest() {
        assertEquals("Цена булочки не соответствует ожиданиям", price, bun.getPrice(), 0.001f);
    }
}
