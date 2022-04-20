package com.prueba.anotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.interfaces.ICreacionInformesFinancieros;
import com.interfaces.IEmpleados;

//@Component("comercialExperimentado")//registramos el bean con un id 
@Component // registramos el bean sin un id
//@Scope("prototype") // con esta acotacion modificamos el patron ya sea singleton o prototype
public class ComercialExperimentado implements IEmpleados {

	// utilizamos este campo para la inyeccion de dependecias
	private ICreacionInformesFinancieros informeNuevo;

	@Override
	public String getTareas() {
		return "soy un vendedor y me encargo de vender mucho";
	}

	// creamos un constructor para que a traves de este spring pueda
	// inyectar la dependencia con la anotation @Autowired
	@Autowired
	// como buena practica siempre que usamos inyeccion de dependencia , tenemos que
	// agregar el autowired
	// esta anotacion tambien se puede implementar en atributos (mediante el
	// concepto de reflexion)
	// investigar concepto de reflexion en java , e incluso tambien se puede en los
	// setters
	public ComercialExperimentado(ICreacionInformesFinancieros informeNuevo) {
		this.informeNuevo = informeNuevo;
	}

	@Override
	public String getInforme() {
		// utilizamos get informe sin la inyeccion de dependencia
		// return "Informe creado por el comercial con mucha expertise";

		// utilizamos el metodo get infornme con inyeccion de dependencia
		return informeNuevo.getInformeFinanciero();
	}

	// para usar init y destroy seria con las siguientes anotaciones
	// aca cambia un poco el nombre
//	PostConstruct y PreDestroy en este segundo SI o SI debenmos utilizar el scope por defecto  (singleton)
//	 esto se debe a que spring no maneja el ciclo de vida por completo del bean si trabajamos con un 
//	 scope de tipo prototype

	// ejecucion de codigo depues de la creacion del bean
	@PostConstruct
	public void ejecutaDespuesCreacion() {
		System.out.println("me estoy ejecutando tras la creacion del bean ");
	}

	// ejecucion de codigo depues del apagado del contenedor de spring
	@PreDestroy
	public void ejecutaCreacion() {
		System.out.println("me estoy ejecutando tras la destruccion del bean ");
	}

}


