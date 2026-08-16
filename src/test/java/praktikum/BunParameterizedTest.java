package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    private final String name;
    private final float price;

    public BunParameterizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Test {index}: bunName={0}, price={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f},
                {"custom bun", 0f}
        });
    }

    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.001f);
    }
}