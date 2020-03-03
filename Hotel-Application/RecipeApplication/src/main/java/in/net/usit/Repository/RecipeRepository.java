package in.net.usit.Repository;

import in.net.usit.To.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

	public Recipe findByIngredients(int id);
}
