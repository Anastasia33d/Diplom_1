package tests;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import praktikum.IngredientType;
import static org.junit.Assert.*;
public class IngredientTypeTest {

    @Test
    public void enumValuesTest() {
        SoftAssertions softly = new SoftAssertions();

        // Проверяем, что в enum содержится 2 значения
        softly.assertThat(IngredientType.values().length)
                .as("Проверяем, что количество значений в IngredientType равно 2")
                .isEqualTo(2);

        // Проверяем наличие значения "SAUCE" в enum
        softly.assertThat(containsEnumValue(IngredientType.values(), "SAUCE"))
                .as("Проверяем, что IngredientType содержит значение 'SAUCE'")
                .isTrue();

        // Проверяем наличие значения "FILLING" в enum
        softly.assertThat(containsEnumValue(IngredientType.values(), "FILLING"))
                .as("Проверяем, что IngredientType содержит значение 'FILLING'")
                .isTrue();

        softly.assertAll();
    }

    @Test
    public void testValueOf() {
        SoftAssertions softly = new SoftAssertions();

        // Проверяем корректность преобразования строки "SAUCE" в элемент enum
        softly.assertThat(IngredientType.valueOf("SAUCE"))
                .as("Проверяем, что IngredientType.valueOf('SAUCE') возвращает IngredientType.SAUCE")
                .isEqualTo(IngredientType.SAUCE);

        // Проверяем корректность преобразования строки "FILLING" в элемент enum
        softly.assertThat(IngredientType.valueOf("FILLING"))
                .as("Проверяем, что IngredientType.valueOf('FILLING') возвращает IngredientType.FILLING")
                .isEqualTo(IngredientType.FILLING);

        softly.assertAll();
    }

    private boolean containsEnumValue(IngredientType[] values, String valueName) {
        for (IngredientType value : values) {
            if (value.name().equals(valueName)) {
                return true;
            }
        }
        return false;
    }
}