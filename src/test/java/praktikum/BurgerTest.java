package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockSauce);
        assertEquals(Arrays.asList(mockSauce), burger.ingredients);
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.removeIngredient(0);

        assertEquals(Arrays.asList(mockFilling), burger.ingredients);
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(1, 0);

        assertEquals(Arrays.asList(mockFilling, mockSauce), burger.ingredients);
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockSauce.getPrice()).thenReturn(10.0f);
        when(mockFilling.getPrice()).thenReturn(20.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        // 50 * 2 (булки) + 10 + 20 = 130
        assertEquals(130.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("Hot Sauce");
        when(mockSauce.getPrice()).thenReturn(10.0f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("Cutlet");
        when(mockFilling.getPrice()).thenReturn(20.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String expectedReceipt = String.format(
                "(==== Test Bun ====)%n" +
                        "= sauce Hot Sauce =%n" +
                        "= filling Cutlet =%n" +
                        "(==== Test Bun ====)%n" +
                        "%nPrice: %f%n", 130.0f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}