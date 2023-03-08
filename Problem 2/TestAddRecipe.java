package testPackageAddRecipe;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAddRecipe {
    private CoffeeMaker cm;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;
    @Before
    public void setUp() throws Exception {
        cm = new CoffeeMaker();

        recipe1 = new Recipe();
        recipe1.setName(""); // We leave the name empty
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("0");
        recipe1.setAmtMilk("0");
        recipe1.setAmtSugar("0");
        recipe1.setPrice("0");

        recipe2 = new Recipe();
        recipe2.setName("Latte");
        recipe2.setAmtChocolate("0");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("25");


        recipe3 = new Recipe();
        recipe3.setName("Espresso");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("1");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("30");

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
        boolean value = cm.addRecipe(recipe5);
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
}
