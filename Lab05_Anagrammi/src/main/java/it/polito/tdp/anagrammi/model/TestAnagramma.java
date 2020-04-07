package it.polito.tdp.anagrammi.model;

import java.util.List;

public class TestAnagramma {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		List<String> corretti = m.anagramma("roma", 0);
		System.out.println(corretti);
		
		List<String> errati = m.anagramma("roma", 1);
		System.out.println(errati);


	}

}
