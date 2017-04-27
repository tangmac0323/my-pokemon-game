package Pokemon;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class WildPokemonGenerator implements Serializable{

	private static final long serialVersionUID = 3246898710827826642L;
	
	private static ArrayList<Pokedex> CommonPokedexList;
	private static ArrayList<Pokedex> UncommonPokedexList;
	private static ArrayList<Pokedex> RarePokedexList;
	private static ArrayList<Pokedex> EpicPokedexList;
	private static ArrayList<Pokedex> LegendPokedexList;
	
	private static WildPokemonGenerator uniqueInstance;
	
	public static WildPokemonGenerator getInstance(){
		if (uniqueInstance == null){
			uniqueInstance = new WildPokemonGenerator();
		}
		
		return uniqueInstance;
	}
	
	// constructor to initiate the list
	private WildPokemonGenerator() {
		CommonPokedexList = new ArrayList<Pokedex>();
		UncommonPokedexList = new ArrayList<Pokedex>();
		RarePokedexList = new ArrayList<Pokedex>();
		EpicPokedexList = new ArrayList<Pokedex>();
		LegendPokedexList = new ArrayList<Pokedex>();
		
		// build the list
		for (Pokedex dex : Pokedex.values()) {
			if (dex.getQuality() == PokemonQuality.COMMON){
				CommonPokedexList.add(dex);
			}
			else if (dex.getQuality() == PokemonQuality.UNCOMMON){
				UncommonPokedexList.add(dex);
			}
			else if (dex.getQuality() == PokemonQuality.RARE){
				RarePokedexList.add(dex);
			}
			else if (dex.getQuality() == PokemonQuality.EPIC){
				EpicPokedexList.add(dex);
			}
			else{
				LegendPokedexList.add(dex);
			}
		}
	}
	
	public Object getCommon(){
		Random generator = new Random();
		int randomIndex = generator.nextInt(CommonPokedexList.size());
		Pokedex specy = CommonPokedexList.get(randomIndex);
		
		// constructor
		return pokemonConstructor(specy);
	}
	
	public Object getUncommon(){
		Random generator = new Random();
		int randomIndex = generator.nextInt(UncommonPokedexList.size());
		Pokedex specy = UncommonPokedexList.get(randomIndex);
		
		// constructor
		return pokemonConstructor(specy);
	}
	
	public Object getRare(){
		Random generator = new Random();
		int randomIndex = generator.nextInt(RarePokedexList.size());
		Pokedex specy = RarePokedexList.get(randomIndex);
		
		// constructor
		return pokemonConstructor(specy);
	}
	
	public Object getEpic(){
		Random generator = new Random();
		int randomIndex = generator.nextInt(EpicPokedexList.size());
		Pokedex specy = EpicPokedexList.get(randomIndex);
		
		// constructor
		return pokemonConstructor(specy);
	}
	
	public Object getLegend(){
		Random generator = new Random();
		int randomIndex = generator.nextInt(LegendPokedexList.size());
		Pokedex specy = LegendPokedexList.get(randomIndex);
		
		// constructor
		return pokemonConstructor(specy);
	}
	
		
	private Object pokemonConstructor(Pokedex specy){
		// create a corresponding class
		String className = specy.name();	// get the class name
		Object obj = null;
		
		// construct using reflection
		try {
			Class<?> c = Class.forName("Pokemon." + className);
			Constructor<?> cons = c.getConstructors()[0];
			obj = cons.newInstance(specy.getName());
		}
		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
