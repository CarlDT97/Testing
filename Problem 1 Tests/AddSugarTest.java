package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddSugarTest {
    private Inventory inv;


    /* Set up */
    @Before
    public void setUp() throws Exception {
        inv = new Inventory();
    }


    @Test
    public void testSugarDecimalAmount() {
        try {
            inv.addSugar("10.2");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }
        assertEquals(15,inv.getSugar());
    }

    @Test
    public void testSugarNegativeAmount() {
        try {
            inv.addSugar("-20");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }
        assertEquals(15,inv.getSugar());
    }

    @Test
    public void testSugarStringAmount() {
        try {
            inv.addSugar("hello");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }
        assertEquals(15,inv.getSugar());
    }

    @Test
    public void testNotEnoughSugar() {
        try {
            inv.addSugar("0");
        } catch (InventoryException e) {
            assertEquals("Units of sugar must be a positive integer", e.getMessage());
        }
        assertEquals(15,inv.getSugar());
    }

    @Test
    public void testAddSugarSuccessfully() throws InventoryException {
        inv.addSugar("10");
        assertEquals(25,inv.getSugar());
    }


}