package it.polito.tdo.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set<Integer> tentativi;
	
	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	this.tentativi = new HashSet<Integer>();
    	
	}
	
	public int tentativo(int tentativo) {
		
		//controllo se il tentativo è valido
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e "+NMAX+" e non puoi inserire due volte lo stesso numero");
		}
		
		//il tentativo è valido
		
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(tentativo == segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(this.tentativiFatti == TMAX) {
			this.inGioco = false;
			throw new IllegalStateException("HAI PERSO, IL SEGRETO ERA: "+segreto);
		}
		
		
		if(tentativo < this.segreto) {
			return -1;
		} else {
			return 1;
		}
		
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo > NMAX) {
			return false;
		}
		
		if(tentativi.contains(tentativo)) {
			return false;
		}
		
		return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
