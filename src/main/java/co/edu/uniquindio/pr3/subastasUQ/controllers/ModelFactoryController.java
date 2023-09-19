package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.model.SubastasQuindio;

import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {

	private SubastasQuindio miSubastasQuindio;
	
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		System.out.println("invocaci�n clase singleton");
		inicializarDatos();
	}

	private void inicializarDatos() {

	}

	//setters() & getters del Singleton del Concesionario

	public SubastasQuindio getMiSubastasQuindio() {
		return miSubastasQuindio;
	}
	public void setMiSubastasQuindio(SubastasQuindio miSubastasQuindio) {
		this.miSubastasQuindio = miSubastasQuindio;
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------
	//Funciones del concesionario para el singleton

	//-----------------------------------------------------------------------------------------------------------------------------------------

}
