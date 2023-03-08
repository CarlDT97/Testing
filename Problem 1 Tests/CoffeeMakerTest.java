package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.ncsu.csc326.coffeemaker.*;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;

    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
    }

    @Test
    public void testAddRecipe() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setPrice("1");
        recipe.setAmtCoffee("2");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("0");
        assertTrue(coffeeMaker.addRecipe(recipe));
    }

    @Test
    public void testDeleteRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        coffeeMaker.addRecipe(recipe);
        assertEquals("Test Recipe", coffeeMaker.deleteRecipe(0));
    }

    @Test
    public void testEditRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        coffeeMaker.addRecipe(recipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Edited Recipe");
        assertEquals("Test Recipe", coffeeMaker.editRecipe(0, newRecipe));
    }

    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("5", "5", "5", "5");
        assertEquals("Coffee: 20\nMilk: 20\nSugar: 20\nChocolate: 20\n", coffeeMaker.checkInventory());
    }

    @Test
    public void testCheckInventory() throws InventoryException {
        coffeeMaker.addInventory("2", "1", "1", "0");
        String expected = "Coffee: 17\nMilk: 16\nSugar: 16\nChocolate: 15\n";
        assertEquals(expected, coffeeMaker.checkInventory());
    }

    @Test
    public void testMakeCoffee() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setPrice("2");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("0");
        coffeeMaker.addRecipe(recipe);

        assertEquals(0, coffeeMaker.makeCoffee(0, 2));
    }


    @Test
    public void testGetRecipes() {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        coffeeMaker.addRecipe(recipe);
        assertNotNull(coffeeMaker.getRecipes());
    }

}