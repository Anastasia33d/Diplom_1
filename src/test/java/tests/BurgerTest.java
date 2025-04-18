package tests;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class BurgerTest {
    private Burger burger;
    private Bun mockBun;
    private Ingredient mockIngredient1;
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();
        // Создаём мок для булочки
        mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockBun.getName()).thenReturn("Test Bun");
        // Создаём мок для первого ингредиента
        mockIngredient1 = mock(Ingredient.class);
        when(mockIngredient1.getPrice()).thenReturn(200.0f);
        when(mockIngredient1.getName()).thenReturn("Ingredient1");

        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        // Создаём мок для второго ингредиента
        mockIngredient2 = mock(Ingredient.class);
        when(mockIngredient2.getPrice()).thenReturn(300.0f);
        when(mockIngredient2.getName()).thenReturn("Ingredient2");
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
    }

    //Проверка установки булочки
    @Test
    public void setBunTest() {
        burger.setBuns(mockBun);
        assertEquals("Булочка должна быть установлена корректно", mockBun, burger.bun);
    }

    //Проверка добавления ингредиента
    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredient1);
        assertTrue("Ингредиент должен быть добавлен в список ингредиентов", burger.ingredients.contains(mockIngredient1));
    }

    //Проверка удаления ингредиента
    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);

        SoftAssertions softly = new SoftAssertions();
        // Проверяем, что ингредиент mockIngredient1 был удалён
        softly.assertThat(burger.ingredients)
                .as("Проверяем, что ингредиент '%s' был успешно удален из бургера", mockIngredient1)
                .doesNotContain(mockIngredient1);
        // Проверяем, что ингредиент mockIngredient2 остался в бургерe
        softly.assertThat(burger.ingredients)
                .as("Проверяем, что ингредиент '%s' по-прежнему присутствует в бургере", mockIngredient2)
                .contains(mockIngredient2);

        softly.assertAll();
    }

    //Проверка перемещения ингредиента
    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        // Перемещаем ингредиент с индекса 0 на индекс 1
        burger.moveIngredient(0, 1);
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));

        SoftAssertions softly = new SoftAssertions();
        // Проверка, что ингредиент mockIngredient2 теперь находится на индексе 0
        softly.assertThat(burger.ingredients.get(0))
                .as("Проверяем, что ингредиент '%s' находится на позиции 0 после перемещения", mockIngredient2)
                .isEqualTo(mockIngredient2);
        // Проверка, что ингредиент mockIngredient1 теперь находится на индексе 1
        softly.assertThat(burger.ingredients.get(1))
                .as("Проверяем, что ингредиент '%s' находится на позиции 1 после перемещения", mockIngredient1)
                .isEqualTo(mockIngredient1);

        softly.assertAll();
    }

    //Проверка получения цены бургера
    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 700.0f;
        assertEquals("Цена бургера должна быть рассчитана корректно", expectedPrice, burger.getPrice(), 0.001);
    }

    //Проверка полученяи чека
    @Test
    public void getReceiptTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Формируем ожидаемый чек.
        StringBuilder expected = new StringBuilder();
        expected.append(String.format("(==== %s ====)%n", mockBun.getName()));
        expected.append(String.format("= %s %s =%n", mockIngredient1.getType().toString().toLowerCase(),
                mockIngredient1.getName()));
        expected.append(String.format("= %s %s =%n", mockIngredient2.getType().toString().toLowerCase(),
                mockIngredient2.getName()));
        expected.append(String.format("(==== %s ====)%n", mockBun.getName()));
        expected.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals("Чек должен соответствовать ожидаемому формату", expected.toString(), burger.getReceipt());
    }
}