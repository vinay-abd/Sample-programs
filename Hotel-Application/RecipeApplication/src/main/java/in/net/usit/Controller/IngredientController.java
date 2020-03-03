package in.net.usit.Controller;

import in.net.usit.Service.Bo.IngredientBo;
import in.net.usit.Service.Bo.RecipeBo;
import in.net.usit.To.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/ingredient")
public class IngredientController {
	List<Ingredient> list = new ArrayList<Ingredient>();

	@Autowired
	private IngredientBo service;

	@Autowired
	private RecipeBo recipeService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Ingredient> getAllIngredients() {
		list = service.getAllIngredients();
		return list;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addIngredient(@RequestBody Ingredient i) {
		System.out.println("in controller ===>"+i);
		service.addIngredient(i);
	}

	@RequestMapping(value = "/inglist", method = RequestMethod.POST)
	public void addIngredientToShoppingList(@RequestBody List<Ingredient> ingredientList) {
		// int no=0;
		// Optional<Recipe> recipe=recipeService.getRecipe(no);
		System.out.println("in controller adding ing to shopping list....."+ingredientList);
		service.addIngredientToShoppingList(ingredientList);
	}

	@RequestMapping(value = "/get/{index}",method=RequestMethod.GET)
	public Optional<Ingredient> getIngredient(@PathVariable int index) {
		return service.getIngredient(index);
		//return service.getIngredient(index);
	}

	@RequestMapping(value = "/delete/{index}",method=RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable int index) {
		service.deleteIngredient(index);
	}

	@RequestMapping(value = "/edit/{index}",method=RequestMethod.POST)
	public void updateIngredient(@PathVariable int index,
			@RequestBody Ingredient newIngredient) {
		this.service.updateIngredient(index, newIngredient);

	}

}
