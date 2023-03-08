package edu.ncsu.csc326.coffeemaker;

//import edu.ncsu.csc326.coffeemaker.exceptions.RecipeBookException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

// @Before
// @Test
public class RecipeBookTest {

    private CoffeeMaker cm;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;

    @Before
    public void setUp() throws Exception {
        /*
         * We create a new coffee maker.
         */
        cm = new CoffeeMaker();
        // m = new Main();

        /*
         * Create a new recipe which will consist of undesired values.
         * We will leave the name empty and create a recipe which don't contain anything
         */
        recipe1 = new Recipe();
        recipe1.setName(""); // We leave the name empty
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("0");
        recipe1.setAmtMilk("0");
        recipe1.setAmtSugar("0");
        recipe1.setPrice("0");

        /*
         * Create new recipe that has negative values in ingredients
         */
        recipe2 = new Recipe();
        recipe2.setName("Latte");
        recipe2.setAmtChocolate("0");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("25");

        /*
         * Create a normal recipe that we expect
         */

        recipe3 = new Recipe();
        recipe3.setName("Espresso");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("1");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("30");

        /*
         * We create an identical espresso recipe but name it with capital letters
         * Should not be allowed to be added into the recipe book
         */
        recipe4 = new Recipe();
        recipe4.setName("ESPRESSO");
        recipe4.setAmtChocolate("0");
        recipe4.setAmtCoffee("3");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("50");

        recipe5 = new Recipe();
        recipe5.setName("Mocha");
        recipe5.setAmtChocolate("1");
        recipe5.setAmtCoffee("3");
        recipe5.setAmtMilk("1");
        recipe5.setAmtSugar("1");
        recipe5.setPrice("2");
    }
    @Test
    public void testAddRecipeToMultipleElementArray() {

        cm.addRecipe(recipe1);
        cm.addRecipe(recipe2);
        boolean result = cm.addRecipe(recipe3);
        assertTrue(result);
        assertEquals(recipe1, cm.getRecipes()[0]);
        assertEquals(recipe2, cm.getRecipes()[1]);
        assertEquals(recipe3, cm.getRecipes()[2]);
    }

    @Test
    public  void testForFullArray(){
        cm.addRecipe(recipe1);
        cm.addRecipe(recipe2);
        cm.addRecipe(recipe3);
        cm.addRecipe(recipe4);
        boolean value =cm.addRecipe(recipe5);
        assertFalse(value);
    }
    @Test
    public void testAddDuplicateRecipeToMultipleElementArray() {
        cm.addRecipe(recipe1);
        cm.addRecipe(recipe2);
        boolean result = cm.addRecipe(recipe1);
        assertFalse(result);
        assertEquals(recipe1, cm.getRecipes()[0]);
        assertEquals(recipe2, cm.getRecipes()[1]);
    }
    /* ------------------------Tests------------------------ */


    @Test
    /*
     * This test will check that if we add two new recipes to our array,
     * one with the name "Espresso" and one with the name "ESPRESSO" it should fail.
     */
    public void testAddRecipeDuplicate_LowerVsCapital() {
        cm.addRecipe(recipe3); // Recipe add Espresso
        cm.addRecipe(recipe4); // Recipe add ESPRESSO
        boolean r1 = containsRecipe("Espresso"); // will be true since we add them above
        boolean r2 = containsRecipe("ESPRESSO"); // will be true since we add them above
        boolean outCome = r1 && r2; // fail test boolean
        // if the array hold r1 and r2 it should set outcome to fail
        assertFalse(outCome); // we will compare r1 boolean to outcome
    }

    /*
    @Test // Testing to add the same recipe twice, should fail
    public void testAddRecipeDuplicate_Normal() {
        cm.addRecipe(recipe3);
        cm.addRecipe(recipe3);
        int count = countRecipesName("Espresso");
        if (count >= 2) {
            fail("Duplicate");
        } else {
            assertTrue(true);
        }
    }
    */
    @Test /* Test for adding a recipe */
    public void testAddRecipe() {
        cm.addRecipe(recipe2);
        boolean exist = containsRecipe("Latte");
        if (!exist) {
            fail("Recipe was not added test fail");
        } else {
            assertEquals("Latte", recipe2.getName());
            assertEquals(0, recipe2.getAmtChocolate());
            assertEquals(3, recipe2.getAmtCoffee());
            assertEquals(1, recipe2.getAmtMilk());
            assertEquals(1, recipe2.getAmtSugar());
            assertEquals(25, recipe2.getPrice());
        }
    }
    @Test /* Testing to add a recipe with an empty name to the array */
    public void testAddRecipe_NoName() {
        cm.addRecipe(recipe1);
        boolean exist = containsRecipe("");
        if (exist) {
            Assertions.fail("There is a recipe with an empty name");
        } else {
            pass();
        }
    }

    @Test // Return the array
    public void testGetArray() {
        cm.addRecipe(recipe2);
        Recipe[] recipes = cm.getRecipes();
        int i = findRecipe(recipe2.getName());

        if (recipes[i].getName().equals(recipe2.getName())) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test /* Test remove a recipe from the array that don't exist*/
    public void testRemoveRecipe_null() {
        String returnValue = cm.deleteRecipe(0);
        if (returnValue == null){
            pass();
        } else {
            fail();
        }
    }

    @Test /* Test remove a recipe from the array */
    public void testRemoveRecipe() {
        cm.addRecipe(recipe2); // First we add the recipe to the array
        int index = findRecipe("Latte"); // Then we call the function for getting the index of "Latte"
        boolean exist = containsRecipe("Latte"); // Check it exist true or false

        if (exist) { // If the index is not 0
            cm.deleteRecipe(index); // We call on the function for deleting the recipe at index [index]

        }

        boolean checkExistence = containsRecipe("Latte"); // We do a second check after deleting it
        if (!checkExistence) {
            assertFalse(false);
        } else {
            fail("The recipe was not deleted");
        }
    }
    @Test
    public void testEditRecipe_null() {
        Recipe newRecipe = new Recipe(); // We create a new recipe.
        String returnValue = "";
        try { // We assign the values to the new recipe.
            newRecipe.setPrice("15");
            newRecipe.setAmtCoffee("3");
            newRecipe.setAmtMilk("2");
            newRecipe.setAmtSugar("2");
            newRecipe.setAmtChocolate("0");
            returnValue = cm.editRecipe(0, newRecipe);

        } catch (RecipeException e) {
            System.out.println(e.getMessage());
        } finally {
            if (returnValue == null) {
                pass();
            } else {
                fail();
            }
        }
    }


    @Test /*
     * System fault, test fails due to setting name to "", it should keep the name
     * of the original recipe
     */
    public void testEditRecipe_normal() {
        cm.addRecipe(recipe4); // Add the recipe to the array
        Recipe newRecipe = new Recipe(); // We create a new recipe.
        try { // We assign the values to the new recipe.
            newRecipe.setPrice("15");
            newRecipe.setAmtCoffee("3");
            newRecipe.setAmtMilk("2");
            newRecipe.setAmtSugar("2");
            newRecipe.setAmtChocolate("0");

            cm.editRecipe(0, newRecipe); // We call the edit recipe function.
        } catch (RecipeException e) {
            System.out.println(e.getMessage());
        } finally {
            Recipe updatedRecipe = cm.getRecipes()[0]; // get the recipe at index [0]
            // Check that the recipe was updated correctly, the first assert will fail due
            // to system faults.
            assertEquals("ESPRESSO", updatedRecipe.getName());
            assertEquals(15, updatedRecipe.getPrice());
            assertEquals(3, updatedRecipe.getAmtCoffee());
            assertEquals(2, updatedRecipe.getAmtMilk());
            assertEquals(2, updatedRecipe.getAmtSugar());
            assertEquals(0, updatedRecipe.getAmtChocolate());
        }
    }
    /* ------------------------Additional Test Functions------------------------ */

    public void pass() {
        assertTrue(true);
    }

    public boolean containsRecipe(String name) {
        Recipe[] recipes = cm.getRecipes(); // Get the array of recipes
        for (Recipe recipe : recipes) {
            if (recipe != null) {
                if (recipe.getName().equals(name)) { // Check if the name of the recipe at the current index matches
                    // the input name
                    return true;
                }
            }
        }
        return false;
    }

    public int countRecipesName(String name) {
        Recipe[] recipes = cm.getRecipes(); // Get the array of recipes
        int count = 0;
        for (Recipe recipe : recipes) {
            if (recipe != null) {
                if (recipe.getName().equals(name)) { // Check if the name of the recipe at the current index matches
                    count++; // the input name
                }
                return count;
            }
        }
        return -1;

    }

    public int findRecipe(String name) {
        Recipe[] recipes = cm.getRecipes(); // Get the array of recipes
        for (int i = 0; i < recipes.length; i++) {
            if (recipes[i].getName().equals(name)) { // Check if the name of the recipe at the current index matches the
                // input name
                return i; // Return the current index if there is a match
            }
        }
        return -1; // Return -1 if no recipe with the specified name is found
    }

}
