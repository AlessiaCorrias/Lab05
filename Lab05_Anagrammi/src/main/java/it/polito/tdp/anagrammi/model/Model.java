package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.DB.DizionarioDAO;

public class Model {

	private DizionarioDAO dao;
	
	public Model() {
		this.dao= new DizionarioDAO();
	}

	
	
	private List<String> corretti ;
	private List<String> errati;
	private List<String> soluzione;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso.
	 * @param parola parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagramma(String parola, int flag) {
		this.corretti = new ArrayList<>() ;
		this.errati = new ArrayList<>();
		this.soluzione = new ArrayList<>();
		
		parola=parola.toUpperCase() ;
		
		List<Character> disponibili = new ArrayList<>() ;
		for(int i=0; i<parola.length(); i++) {
			disponibili.add(parola.charAt(i)) ;
		}
		
		// avvia la ricorsione
		cerca("", 0, disponibili) ; 
		
		for (String s : soluzione) {
		if(dao.isCorretto(s)) { //parola è nel dizionario
			this.corretti.add(s) ;
			} else {
				this.errati.add(s);
			}
		}
		
		
		if(flag ==0)
		return this.corretti ;
		else 
		return this.errati;
	}
	
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma.
	 * 
	 * @param parziale parte iniziale dell'anagramma costruito finora
	 * @param livello livello della ricorsione, sempre uguale a parziale.length()
	 * @param disponibili insieme delle lettere non ancora utilizzate
	 */
	private void cerca( String parziale, int livello, List<Character> disponibili) {
		if(disponibili.size()==0) { // livello==parola.length()
			// caso terminale
			
			this.soluzione.add(parziale);
		}
		
		// caso normale
		// provare ad aggiungere a 'parziale' tutti i caratteri presenti tra
		// i 'disponibili'
		for(Character ch: disponibili) {
			String tentativo = parziale + ch ;
			
			List<Character> rimanenti = new ArrayList<>(disponibili) ;
			rimanenti.remove(ch) ;
			
			cerca( tentativo, livello+1, rimanenti) ;
		}
	}
	


}
