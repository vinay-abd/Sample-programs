package in.net.usit.To;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Ingredients" ,schema="test")
public class Ingredient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private int ammount;
	
/*	@ManyToOne()
	@Column(name="recipe")
	private Recipe recipe;*/
	
	public Ingredient(String name, int ammount) {
		this.name = name;
		this.ammount = ammount;
	}

	/*public Ingredient(String name, int ammount, Recipe recipe) {
		super();
		this.name = name;
		this.ammount = ammount;
		//this.recipe = recipe;
	}*/

	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
