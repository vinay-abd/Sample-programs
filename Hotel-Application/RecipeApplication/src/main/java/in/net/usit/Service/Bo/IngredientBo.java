package in.net.usit.Service.Bo;

import in.net.usit.To.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientBo 
{
	public List<Ingredient> getAllIngredients();
	
	public void addIngredient(Ingredient ing);
	
	public void addIngredientToShoppingList(List<Ingredient> ingredientList);
	
	public Optional<Ingredient> getIngredient(int index);
	
	public void deleteIngredient(int index);
	
	//public void updateIngredient(int index, Optional<Ingredient> newIngredient);

	public void updateIngredient(int index,Ingredient newIngredient);
}
