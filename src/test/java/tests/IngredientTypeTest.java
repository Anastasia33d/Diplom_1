package tests;
import org.junit.Test;
import praktikum.IngredientType;
import static org.junit.Assert.*;
public class IngredientTypeTest {

    @Test
    public void enumValuesTest() {
        // Проверяем количество значений в enum
        assertEquals(2, IngredientType.values().length);

        // Проверяем наличие всех ожидаемых значений
        assertTrue(containsEnumValue(IngredientType.values(), "SAUCE"));
        assertTrue(containsEnumValue(IngredientType.values(), "FILLING"));
    }

    @Test
    public void testValueOf() {
        // Проверяем корректность преобразования строки в enum
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
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