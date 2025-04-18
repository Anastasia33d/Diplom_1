package tests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientsTest {
    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientsTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters(name = "Тип ингредиента, наименование и цена: {0} {1} {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.SAUCE, "sour cream", 200.0f},
                {IngredientType.FILLING, "cutlet", 100.0f},
                {IngredientType.FILLING, "dinosaur", 200.0f}
        });
    }

    //Проверка корректности возвращаемой цены ингредиента
    @Test
    public void getPriceTest() {
        assertEquals("Цена ингредиента рассчитана неверно", price, ingredient.getPrice(), 0.001f);
    }

    //Проверка корректности возвращаемого наименования ингредиента
    @Test
    public void getNameTest() {
        assertEquals("Наименование ингредиента не совпадает", name, ingredient.getName());
    }

    //Проверка корректности возвращаемого типа ингредиента
    @Test
    public void getTypeTest() {
        assertEquals("Тип ингредиента не совпадает", type, ingredient.getType());
    }
}