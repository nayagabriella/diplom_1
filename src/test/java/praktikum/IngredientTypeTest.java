package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValuesCount() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length);
    }

    @Test
    public void testIngredientTypeSauce() {
        assertNotNull(IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void testIngredientTypeFilling() {
        assertNotNull(IngredientType.valueOf("FILLING"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}