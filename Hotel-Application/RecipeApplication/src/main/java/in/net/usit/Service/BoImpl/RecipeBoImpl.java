package in.net.usit.Service.BoImpl;

import in.net.usit.Repository.RecipeRepository;
import in.net.usit.Service.Bo.RecipeBo;
import in.net.usit.To.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeBoImpl implements RecipeBo {

	List<Recipe> recipeList = new ArrayList<Recipe>();

	@Autowired
	private RecipeRepository recipeService;

	@Override
	public List<Recipe> getAllRecipeList() {
		recipeList = recipeService.findAll();
		return recipeList;
	}

	@Override
	public Optional<Recipe> getRecipe(int index) {
		// TODO Auto-generated method stub
		return recipeService.findById(index);
	}

	@Override
	public void addNewRecipe(Recipe recipe) {
		recipeService.save(recipe);

	}

	public void updateRecipe(int index, Recipe recipe) {
		Recipe rr = new Recipe();
		
		rr = recipe;
		rr.setId(index);
		recipeService.save(rr);
	}

	@Override
	public Optional<Recipe> deleteRecipe(int index) {
		// TODO Auto-generated method stub
		// index=index+1;
		// recipeList=recipeService.findAll();
		recipeService.deleteById(index);
		return null;
	}

}
