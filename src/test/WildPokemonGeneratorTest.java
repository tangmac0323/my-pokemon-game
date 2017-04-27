package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Pokemon.Pokemon;
import Pokemon.WildPokemonGenerator;

public class WildPokemonGeneratorTest {

	@Test
	public void test() {
		WildPokemonGenerator pGenerator = WildPokemonGenerator.getInstance();
		
		//////////////// Common ////////////////
		System.out.println("Common:");
		for (int i = 0; i < 10; i++){
			System.out.println( "   " + i + " " + ((Pokemon) pGenerator.getCommon()).getClass().getName());
		}
		System.out.println();
		
		//////////////// Uncommon ////////////////
		System.out.println("Uncommon:");
		for (int i = 0; i < 10; i++){
			System.out.println( "   " + i + " " + ((Pokemon) pGenerator.getUncommon()).getClass().getName());
		}
		System.out.println();
		
		//////////////// Rare ////////////////
		System.out.println("Rare:");
		for (int i = 0; i < 5; i++){
			System.out.println( "   " + i + " " + ((Pokemon) pGenerator.getRare()).getClass().getName());
		}
		System.out.println();
		
		//////////////// Epic ////////////////
		System.out.println("Epic:");
		for (int i = 0; i < 5; i++){
			System.out.println( "   " + i + " " + ((Pokemon) pGenerator.getEpic()).getClass().getName());
		}
		System.out.println();
		
		//////////////// Legend ////////////////
		System.out.println("Legend:");
		for (int i = 0; i < 5; i++){
			System.out.println( "   " + i + " " + ((Pokemon) pGenerator.getLegend()).getClass().getName());
		}
	}

}
