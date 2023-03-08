package testPackageSetPrice;

import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSetPrice {
    private CoffeeMaker cm;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    @Before
    public void setUp() throws Exception {
        cm = new CoffeeMaker();

        // Set up for r1
        r1 = new Recipe();
        r1.setName("");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("0");
        r1.setAmtMilk("0");
        r1.setAmtSugar("0");
        r1.setPrice("0");

        // Set up for r1
        r2 = new Recipe();
        r2.setName("Espresso");
        r2.setAmtChocolate("1");
        r2.setAmtCoffee("1");
        r2.setAmtMilk("1");
        r2.setAmtSugar("1");
        r2.setPrice("10");

        r3 = new Recipe();
        r3.setName("Mocha");
        r3.setAmtChocolate("1");
        r3.setAmtCoffee("1");
        r3.setAmtMilk("1");
        r3.setAmtSugar("1");
        r3.setPrice("10");
    }


    @Test
    public void testSetPrice_error_ish_Case() throws RecipeException {
        r1.setPrice("0");
        int price = r1.getPrice();
        String str = String.valueOf(price);
        if ((price != 0) && (!str.equals(""))
                && (str.matches("^[0-9]+"))) {
            pass();
        } else {
            fail("Invalid elements in price: " + price);
        }
    }

    @Test
    public void testSetPrice_Normal() throws RecipeException {
        r1.setPrice("10");
        int price = r1.getPrice();
        assertEquals(10, price);
    }
    @Test
    public void testSetPrice_input_notNormal() {
        try {
            r1.setPrice("-50");
            fail("Expected RecipeException to be thrown");
        } catch (RecipeException e) {
            assertEquals("Price must be a positive integer", e.getMessage());
        }

        try {
            r1.setPrice("abc");
            fail("Expected RecipeException to be thrown");
        } catch (RecipeException e) {
            assertEquals("Price must be a positive integer", e.getMessage());
        }

        try {
            r1.setPrice("12.34");
            fail("Expected RecipeException to be thrown");
        } catch (RecipeException e) {
            assertEquals("Price must be a positive integer", e.getMessage());
        }
    }

    public void pass() {
        assertTrue(true);
    }

}
