package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class InventoryTest {
    private Inventory inv;
    private Recipe r;
    private Recipe r2;
    private Recipe r3;


    /* Set up */
    @Before
    public void setUp() throws Exception {
        inv = new Inventory();

        r = new Recipe();
        r.setName("Espresso");
        r.setAmtChocolate("0");
        r.setAmtCoffee("10");
        r.setAmtMilk("1");
        r.setAmtSugar("1");
        r.setPrice("25");

        r2 = new Recipe();
        r2.setName("Americano");
        r2.setAmtChocolate("2");
        r2.setAmtCoffee("8");
        r2.setAmtMilk("50");
        r2.setAmtSugar("2");
        r2.setPrice("40");

        r3 = new Recipe();
        r3.setName("Instant Coffee");
        r3.setAmtChocolate("1");
        r3.setAmtCoffee("1");
        r3.setAmtMilk("1");
        r3.setAmtSugar("1");
        r3.setPrice("12");
    }



    /* Test for setter and getter values in the inventory  */

    @Test
    public void testSetGetValues() {
        inv.setChocolate(20);
        inv.setCoffee(20);
        inv.setMilk(20);
        inv.setSugar(20);

        assertEquals(20, inv.getChocolate());
        assertEquals(20, inv.getCoffee());
        assertEquals(20, inv.getMilk());
        assertEquals(20, inv.getSugar());
    }




    /* Test for chocolate in inventory */

    @Test
    public void testAddChocolate() throws InventoryException {
        inv.addChocolate("4");
        String inventory = inv.toString();
        String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 19\n";

        assertEquals(expected,inventory);
    }

    @Test
    public void testAddChocolate_Exception() {
        try {
            inv.addChocolate("-2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of chocolate must be a positive integer", e.getMessage());
        }

        try {
            inv.addChocolate("1.2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of chocolate must be a positive integer", e.getMessage());
        }

        try {
            inv.addChocolate("a");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of chocolate must be a positive integer", e.getMessage());
        }
    }



    /* Test for coffee in inventory */

    @Test
    public void testAddCoffee() throws InventoryException {
        inv.addCoffee("3");
        String inventory = inv.toString();
        String expected = "Coffee: 18\nMilk: 15\nSugar: 15\nChocolate: 15\n";

        assertEquals(expected,inventory);
    }

    @Test
    public void testAddCoffee_Exception() {
        try {
            inv.addCoffee("-2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of coffee must be a positive integer", e.getMessage());
        }

        try {
            inv.addCoffee("1.2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of coffee must be a positive integer", e.getMessage());
        }

        try {
            inv.addCoffee("a");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of coffee must be a positive integer", e.getMessage());
        }
    }




    /* Test for milk in inventory */

    @Test
    public void testAddMilk() throws InventoryException {
        inv.addMilk("2");
        String inventory = inv.toString();
        String expected = "Coffee: 15\nMilk: 17\nSugar: 15\nChocolate: 15\n";

        assertEquals(expected,inventory);
    }

    @Test
    public void testAddMilk_Exception() {
        try {
            inv.addMilk("-2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of milk must be a positive integer", e.getMessage());
        }

        try {
            inv.addMilk("1.2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of milk must be a positive integer", e.getMessage());
        }

        try {
            inv.addMilk("z");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of milk must be a positive integer", e.getMessage());
        }
    }



    /* Test for sugar in inventory */

    @Test
    public void testAddSugar() throws InventoryException {
        inv.addSugar("10");
        String inventory = inv.toString();
        String expected = "Coffee: 15\nMilk: 15\nSugar: 25\nChocolate: 15\n";

        assertEquals(expected,inventory);
    }

    @Test
    public void testAddSugar_Exception() {
        try {
            inv.addSugar("2.0");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }

        try {
            inv.addSugar("-2");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }

        try {
            inv.addSugar("");
            fail("Expected Inventory Exception to be thrown");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }
    }



    /* Test for checking if there are enough ingredients */

    @Test
    public void testEnoughIngredients() {
        assertTrue(inv.enoughIngredients(r));
    }

    @Test
    public void testEnoughChocolateIngredients_Exception() {
        inv.setChocolate(0);
        assertFalse(inv.enoughIngredients(r2));
    }

    @Test
    public void testEnoughCoffeeIngredients_Exception() {
        inv.setCoffee(0);
        assertFalse(inv.enoughIngredients(r2));
    }
    @Test
    public void testEnoughMilkIngredients_Exception() {
        inv.setMilk(0);
        assertFalse(inv.enoughIngredients(r2));
    }

    @Test
    public void testEnoughSugarIngredients_Exception() {
        inv.setSugar(0);
        assertFalse(inv.enoughIngredients(r2));
    }



    /* Test for using inventory ingredients */

    @Test
    public void testUseIngredients() {
        inv.useIngredients(r3);
        String inventory = inv.toString();
        String expected = "Coffee: 14\nMilk: 14\nSugar: 14\nChocolate: 14\n";

        assertEquals(expected,inventory);
    }

    @Test
    public void testUseIngredients_Exception() {
        assertFalse(inv.useIngredients(r2));
    }
}
