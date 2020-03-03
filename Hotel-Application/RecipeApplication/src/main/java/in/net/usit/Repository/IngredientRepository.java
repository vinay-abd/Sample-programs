package in.net.usit.Repository;

import java.util.Optional;

import in.net.usit.To.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	void save(Optional<Ingredient> i);

}
