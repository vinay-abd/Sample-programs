package in.net.usit.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import in.net.usit.Service.Bo.RecipeBo;
import in.net.usit.To.Ingredient;
import in.net.usit.To.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("Recipe")
public class RecipeController 
{
	List<Recipe> recipeList=new ArrayList<Recipe>();
	
	@Autowired
	private RecipeBo service;
	
	@RequestMapping(value="/new",method=RequestMethod.POST)
	public void addRecipe(@RequestBody Recipe recipe)
	{
		service.addNewRecipe(recipe);
		System.out.println("recipe is saved successfullly......");
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public List<Recipe> getRecipeList()
	{
	recipeList=service.getAllRecipeList();
	System.out.println(recipeList);
	return recipeList;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Optional<Recipe> getRecipe(@PathVariable int id)
	{	//System.out.println(service.getRecipe(id));
	return service.getRecipe(id);
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public void updateRecipe(@PathVariable int id,@RequestBody Recipe recipe)
	{
		
		System.out.println("you are editing with index is :"+id);
		System.out.println("-------------------->"+recipe.toString());
		service.updateRecipe(id,recipe);
		System.out.println("recipe updated successful...");	
		
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public Optional<Recipe> DeleteRecipe(@PathVariable int id)
	{
	return service.deleteRecipe(id);
	}

	
}
