package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class RecipeTest {
    private CoffeeMaker cm;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;


    /*------------------------Setup------------------------*/

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

    /*------------------------Test for Recipe------------------------*/

    @Test
    public void testSetValue_Expected() throws RecipeException {
        r1.setName("Latte");
        r1.setAmtChocolate("1");
        r1.setAmtCoffee("1");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("10");

        assertEquals("Latte", r1.getName());
        assertEquals(10, r1.getPrice());
        assertEquals(1, r1.getAmtCoffee());
        assertEquals(1, r1.getAmtMilk());
        assertEquals(1, r1.getAmtSugar());
        assertEquals(1, r1.getAmtChocolate());
    }

    @Test
    public void testGetAttributes() {
        assertEquals("Espresso", r2.getName());
        assertEquals(10, r2.getPrice());
        assertEquals(1, r2.getAmtCoffee());
        assertEquals(1, r2.getAmtMilk());
        assertEquals(1, r2.getAmtSugar());
        assertEquals(1, r2.getAmtChocolate());
    }


    /*------------------------Test for Name in Recipe------------------------*/
    @Test
    public void testSetName_Empty() {
        r1.setName("");
        String name = r1.getName();
        if ((!name.isBlank()) && (!name.equals(""))) {
            pass();
        } else {
            fail("Name contains unwanted characters or symbols or is empty: " + name);
        }
    }

    @Test
    public void testSetName_notLetters() {
        r1.setName("#&#");
        String name = r1.getName();
        if (name.matches("^[a-zA-Z]*$")) {
            pass();
        } else {
            fail("Name contains unwanted characters or symbols or is empty: " + name);
        }
    }

    /*------------------------Test for Price in Recipe------------------------ */

    @Test
    public void testSetPriceErrorCase() throws RecipeException {
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
    public void testSetPrice_letter() {
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


    /*------------------------Test for Chocolate in Recipe------------------------*/

    @Test(expected = RecipeException.class)
    public void testSetChocolate_negative() throws RecipeException {
        r2.setAmtChocolate("-1");
    }
    @Test(expected = RecipeException.class)
    public void testSetChocolate_inputIsLetter() throws RecipeException {
        r2.setAmtChocolate("a");
    }

    /*------------------------Test for sugar in Recipe------------------------*/

    @Test(expected = RecipeException.class)
    public void testSet_Sugar_negative() throws RecipeException {
        r2.setAmtSugar("-1");
    }

    @Test(expected = RecipeException.class)
    public void testSetSugar_inputIsLetter() throws RecipeException {
        r2.setAmtSugar("a");
    }

    /*------------------------Test for milk in Recipe------------------------*/

    @Test(expected = RecipeException.class)
    public void testSetMilk_negative() throws RecipeException {
        r2.setAmtMilk("-1");
    }

    @Test(expected = RecipeException.class)
    public void testSetMilk_inputIsLetter() throws RecipeException {
        r2.setAmtMilk("a");
    }

    /*------------------------Test for amtCoffee in Recipe------------------------*/

    @Test(expected = RecipeException.class)
    public void testSetCoffee_negative() throws RecipeException {
        r2.setAmtCoffee("-1");
    }

    @Test(expected = RecipeException.class)
    public void testSetCoffee_inputIsLetter() throws RecipeException {
        r2.setAmtCoffee("a");
    }

    @Test
    public void testToString() throws RecipeException {
        Recipe recipe = new Recipe(); // We create a new recipe.
        try { // We assign the values to the new recipe.
            recipe.setName("Latte");
            recipe.setPrice("15");
            recipe.setAmtCoffee("3");
            recipe.setAmtMilk("2");
            recipe.setAmtSugar("2");
            recipe.setAmtChocolate("0");

        } finally {
            String expected = "Latte";
            String actual = recipe.toString();
            assertEquals(expected, actual);
        }

    }

    @Test
    public void testHashCode() {
        assertEquals(r1.hashCode(), r1.hashCode());
        assertNotEquals(r1.hashCode(), r3.hashCode());
    }

    @Test
    public void testIsEquals() {
        boolean valueOne = r1.equals(r2); //Should return false
        boolean valueTwo = r1.equals(r1); //Should return true
        boolean valueThree= r1.getName().equals(r1.getName()); //Should return true
        boolean valueFour= r2.getName().equals(r3.getName()); //Should return true

        assertFalse(valueOne);
        assertTrue(valueTwo);
        assertTrue(valueThree);
        assertFalse(valueFour);

    }

    @Test
    public void testEquals_NullObject() {
        Recipe recipe2 = null;
        boolean value = r3.equals(recipe2);
        assertFalse(value);
    }

    @Test
    public void testEquals_DifferentClass() {
        Object class1 = r1.getClass();
        Object class2 = new Object();
        assertNotEquals(class1, class2);
    }

    @Test
    public void testEquals_NullNameBoth() {
        r1.setName(null);
        r2.setName(null);
        assertTrue(r1.equals(r2));
    }


    @Test
    public void testEquals_NullNameThis() {
        r1.setName(null);
        assertFalse(r1.equals(r2));
    }

    @Test
    public void testEquals_SameName() {
        r1.setName("Mocha");
        r2.setName("Mocha");
        assertTrue(r1.equals(r2));
    }

    /*------------------------Additional Test Functions------------------------*/

    public void pass() {
        assertTrue(true);
    }

}