package in.net.usit.Service.BoImpl;

import in.net.usit.Repository.IngredientRepository;
import in.net.usit.Repository.RecipeRepository;
import in.net.usit.Service.Bo.IngredientBo;
import in.net.usit.To.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientBoImpl implements IngredientBo
{
	
	@Autowired
	private IngredientRepository ingredientService;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	List<Ingredient> ingList=new ArrayList<Ingredient>();

	@Override
	public List<Ingredient> getAllIngredients() {
		// TODO Auto-generated method stub
		
		ingList=ingredientService.findAll();
		return ingList;
	}

	@Override
	public void addIngredient(Ingredient i) {
		// TODO Auto-generated method stub
//		ingList=ingredientService.findAll();
		// i=new Ingredient(i.getName(),i.getAmmount());
//		ingList.add(i);
		System.out.println("adding ingredient==>"+i);
		ingredientService.save(i);
	}

	@Override
	public void addIngredientToShoppingList(List<Ingredient> ingredientList) {
		// TODO Auto-generated method stu
		System.out.println("***************in DAO class*************"+ingredientList);
		List<Ingredient> list=getAllIngredients();
		System.out.println("after getting all ingredients.....from DB=>"+list);
		for (Ingredient ingredient : ingredientList) {
			list.add(new Ingredient(ingredient.getName(),ingredient.getAmmount()));
		}
		
		System.out.println("Final list is ......."+list);
		ingredientService.saveAll(list);
	}

	@Override
	public Optional<Ingredient> getIngredient(int index) {
		// TODO Auto-generated method stuj
		return ingredientService.findById(index);
		//return ingredientService.findById(index);
	}

	@Override
	public void deleteIngredient(int index) {
		ingredientService.deleteById(index);
		//ingredientService.deleteById(index);
		
	}

	@Override
	public void updateIngredient(int index,Ingredient newIngredient) {
		System.out.println("**********NEW INGREDIENT IS *************"+newIngredient);
	//	System.out.println("fwtched recipe according to ingredients"+newIngredient.getRecipe());
		//System.out.println("X value is "+x);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(newIngredient);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Ingredient i=new Ingredient();
		
			i=newIngredient;
		//	i.setRecipe(newIngredient.getRecipe())
			i.setId(index);
		
		
//		if(i.isPresent())
//		{
//			i=newIngredient;
//			
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+i);
			ingredientService.save(i);
/*
		}
*/		
		//i.setId(newIngredient.getId());
		//i.setName(newIngredient.getName());
		//i.setAmmount(newIngredient.getAmmount());
		
		
		//getAllIngredients().set(index, i);
		
	}


}
