package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.services.IModelFactoryControllerService;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.SubastasQuindio;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.RegistroViewController;

import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements IModelFactoryControllerService {

	private SubastasQuindio miSubastasQuindio;
	private RegistroViewController registroViewController;

	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		System.out.println("invocaci�n clase singleton");
		inicializarDatos();
	}

	private void inicializarDatos() {
		miSubastasQuindio = new SubastasQuindio("Subastas UQ", "Carrera 15 #12N, Armenia, Quindío");
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

	public void initRegistroViewController(RegistroViewController registroViewController) {
		this.registroViewController = registroViewController;
	}

	public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, AnuncianteException {
		return this.miSubastasQuindio.crearAnunciante(nombres, apellidos, identificacion, edad, this.miSubastasQuindio, usuario, contrasenia, email, false);
	}

	public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, CompradorException {
		return this.miSubastasQuindio.crearComprador(nombres, apellidos, identificacion, edad, this.miSubastasQuindio, usuario, contrasenia, email, false);
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------

}
