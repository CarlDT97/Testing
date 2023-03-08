package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.ncsu.csc326.coffeemaker.*;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class MakeCoffeeTest {
    private CoffeeMaker coffeeMaker;
    private Recipe r1;
    private Recipe r2;

    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("5");
        r1.setAmtMilk("2");
        r1.setAmtSugar("1");
        r1.setPrice("5");

        r2 = new Recipe();
        r2.setName("OtherCoffee");
        r2.setAmtChocolate("0");
        r2.setAmtCoffee("20");
        r2.setAmtMilk("2");
        r2.setAmtSugar("1");
        r2.setPrice("5");
    }

    @Test
    public void testCoffeeNullRecipe(){
        //recipe does not exists
        assertEquals(5, coffeeMaker.makeCoffee(0, 5));

    }

    @Test
    public void testSuccessfulCoffee(){
        coffeeMaker.addRecipe(r1);
        //correct amount paid, no change
        assertEquals(0, coffeeMaker.makeCoffee(0, 5));

    }
    @Test
    public void testSuccessfulCoffeeChange(){
        coffeeMaker.addRecipe(r1);
        //too high payment, receive 1 dollar in change
        assertEquals(1, coffeeMaker.makeCoffee(0, 6));
    }

    @Test
    public void testCoffeeInsufficientAmtPaid(){
        coffeeMaker.addRecipe(r1);
        //too low payment, receive the whole payment back
        assertEquals(4, coffeeMaker.makeCoffee(0, 4));
    }

    @Test
    public void testCoffeeInsufficientInventory(){
        coffeeMaker.addRecipe(r2);

        //not enough coffee in the inventory, whole payment back
        assertEquals(5, coffeeMaker.makeCoffee(0, 5));
    }
}