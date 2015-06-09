package fr.utbm.info.vi51.project.semantics;

public abstract class Semanticss {

	String nom;

	
	public Semanticss(String nom) {
		this.nom = nom;
	}


	public String getNom() {
		return nom;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNom();
	}

}
