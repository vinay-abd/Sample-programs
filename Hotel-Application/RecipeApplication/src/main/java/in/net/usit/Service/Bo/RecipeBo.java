package in.net.usit.Service.Bo;

import in.net.usit.To.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeBo {
	
	public void addNewRecipe(Recipe recipe);
	
	public List<Recipe> getAllRecipeList();
	
	public Optional<Recipe> getRecipe(int index);
	
	//public void updateRecipe(int index, Recipe recipe);
	
	public Optional<Recipe> deleteRecipe(int index);

	public void updateRecipe(int index,Recipe recipe);

}
