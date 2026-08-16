package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

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
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(1, 0);

        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockIngredient1.getPrice()).thenReturn(10.0f);
        when(mockIngredient2.getPrice()).thenReturn(20.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // 50 * 2 (булки) + 10 + 20 = 130
        assertEquals(130.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(50.0f);

        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("Hot Sauce");
        when(mockIngredient1.getPrice()).thenReturn(10.0f);

        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("Cutlet");
        when(mockIngredient2.getPrice()).thenReturn(20.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Test Bun ====)"));
        assertTrue(receipt.contains("= sauce Hot Sauce ="));
        assertTrue(receipt.contains("= filling Cutlet ="));
        assertTrue(receipt.contains("Price:"));
        assertTrue(receipt.contains("130"));
    }
}