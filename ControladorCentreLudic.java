import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControladorCentreLudic
{
	private IModelCentreLudic	model;
	private IVistaCentreLudic	vista;

	public ControladorCentreLudic(IVistaCentreLudic v, IModelCentreLudic m)
	{
		this.model = m;
		this.vista = v;

		initListeners();
		iniciarVista();
	}

	protected void iniciarVista()
	{
		//Iterable<Activitat> llistaActivitats = model.consultarActivitats(1, 0, null);
		//vista.mostrarLlistaActivitats(llistaActivitats);
	}

	protected void initListeners()
	{
		vista.addListenerCrearActivitatEsportiva(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaInserirActivitatEsportivaPulsada();
			}
		});
		vista.addListenerCrearActivitatCultural(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaInserirActivitatCulturalPulsada();
			}
		});
		vista.addListenerCrearActivitatFormativa(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaInserirActivitatFormativaPulsada();
			}
		});
		vista.addListenerConsultarActivitat(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaConsultarActivitatPulsada();
			}
		});
		vista.addListenerEliminarActivitat(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaEliminarActivitatPulsada();
			}
		});
		vista.addListenerAplicarFiltresLlistaActivitats(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaFiltresLlistaActivitatsPulsada();
			}
		});
		vista.addListenerInscriureParticipant(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaInscriureParticipantPulsada();
			}
		});
		vista.addListenerCancelarInscripcio(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				teclaCancelarInscripcioPulsada();
			}
		});
		vista.addListenerConsultarActivitats(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				vista.clearActivitatCultural();
				vista.clearActivitatEsportiva();
				vista.clearActivitatFormativa();
				vista.clearInscriureParticipant();
				vista.clearEliminarParticipant();
				vista.clearActivitatEscollida();
				vista.clearFiltres();
				vista.mostrarLlistaActivitats(model.consultarActivitats(1, 0, null));
			}
		});
	}

	protected void teclaInserirActivitatEsportivaPulsada()
	{
		ActivitatEsportiva a = vista.getActivitatEsportivaNova();

		if (a != null)
		{
			if (!model.crearNovaActivitat(a))
				vista.mostrarMissatgeError("Ja existeix una activitat amb aquest nom, probi un altre", "Error!");
			else
			{
				if (!model.desar(AplicacioCentreLudic.getNOM_FITXER()))
					vista.mostrarMissatgeWarning("La informació no s'ha pogut desar correctament", "Error al guardar automaticament");
				vista.clearActivitatEsportiva();
				vista.mostrarMissatgeSuccess("Activitat creada correctament!", "Creació d'activitat esportiva");
				//vista.mostrarLlistaActivitats(model.consultarActivitats(1, 0, null));
			}
		}
	}

	protected void teclaInserirActivitatCulturalPulsada()
	{
		ActivitatCultural a = vista.getActivitatCulturalNova();

		if (a != null)
		{
			if (!model.crearNovaActivitat(a))
				vista.mostrarMissatgeError("Ja existeix una activitat amb aquest nom, probi un altre", "Error!");
			else
			{
				if (!model.desar(AplicacioCentreLudic.getNOM_FITXER()))
					vista.mostrarMissatgeWarning("La informació no s'ha pogut desar correctament", "Error al guardar automaticament");
				vista.clearActivitatCultural();
				vista.mostrarMissatgeSuccess("Activitat creada correctament!", "Creació d'activitat cultural");
				//vista.mostrarLlistaActivitats(model.consultarActivitats(1, 0, null));
			}
		}
	}

	protected void teclaInserirActivitatFormativaPulsada()
	{
		ActivitatFormativa a = vista.getActivitatFormativaNova();

		if (a != null)
		{
			if (!model.crearNovaActivitat(a))
				vista.mostrarMissatgeError("Ja existeix una activitat amb aquest nom, probi un altre", "Error!");
			else
			{
				if (!model.desar(AplicacioCentreLudic.getNOM_FITXER()))
					vista.mostrarMissatgeWarning("La informació no s'ha pogut desar correctament", "Error al guardar automaticament");
				vista.clearActivitatFormativa();
				vista.mostrarMissatgeSuccess("Activitat creada correctament!", "Creació d'activitat formativa");
				//vista.mostrarLlistaActivitats(model.consultarActivitats(1, 0, null));
			}	
		}
	}

	//Busca entre les activitats si existeix la que hi ha escrita a la vista.
	//Ha de mostrar els atributs de l'activitat i la llista dels seus participants
	protected void teclaConsultarActivitatPulsada()
	{
		String nomActivitat = vista.getNomActivitatABuscar();

		if (nomActivitat == null)
			vista.mostrarMissatgeError("Introdueix el nom de la Activitat", "Error al buscar activitat");
		else
		{
			Activitat a = model.trobarActivitat(nomActivitat);
			if (a == null)
				vista.mostrarMissatgeError("Activitat no trobada", "Error al buscar activitat");
			else
			{
				vista.mostrarInformacioActivitat(a);
				Iterable<Persona> inscrits = model.consultarInscripcionsActivitat(nomActivitat);
				vista.mostrarLlistaParticipants(inscrits);
				vista.clearActivitatEscollida();
			}
		}
	}

	protected void teclaEliminarActivitatPulsada()
	{
		String nomActivitat = vista.getNomActivitatABuscar();

		if (nomActivitat != null)
		{
			if (model.eliminarActivitat(nomActivitat))
			{
				vista.mostrarMissatgeSuccess("Activitat eliminada correctament", "Administrador");
				if (!model.desar(AplicacioCentreLudic.getNOM_FITXER()))
					vista.mostrarMissatgeWarning("La informació no s'ha pogut desar correctament", "Error al guardar automaticament");
				vista.clearActivitatEscollida();
				vista.mostrarLlistaActivitats(model.consultarActivitats(1, 0, null));
			}
			else
				vista.mostrarMissatgeError("La activitat a borrar no s'ha trobat", "Error al borrar activitat");
		}
	}
	
	protected void teclaFiltresLlistaActivitatsPulsada()
	{
		int		admetInscripcions = vista.getFiltreInscripcions();
		int		tipusActivitat = vista.getFiltreTipusActivitat();
		String	poblacio = vista.getFiltrePoblacio();
		
		if (poblacio != null && !model.poblacioCorrecte(poblacio))
			vista.mostrarMissatgeError("No hi ha cap activitat en aquesta població", "Error al filtrar per Població");
		else
		{
			Iterable<Activitat> ll = model.consultarActivitats(admetInscripcions, tipusActivitat, poblacio);
			vista.mostrarLlistaActivitats(ll);
		}
	}

	protected void teclaInscriureParticipantPulsada()
	{
		String nomActivitat = vista.getNomActivitatConsultada();
		String dni = vista.getDNIinscripcio(); 
		String telefon = vista.getTelefonInscripcio();
		
		if (telefon != null && !sonNumeros(telefon))
			vista.mostrarMissatgeError("El telefon només pot contenir números", "Error al inscriure participant");
		else if (nomActivitat != null && dni != null && telefon != null)
		{
			Persona p = new Persona(dni, telefon);
			if (!model.inscriurePersonaActivitat(nomActivitat, p))
				vista.mostrarMissatgeError("No s'ha pogut inscriure un nou participant", "Error al inscriure");
			else
			{
				vista.mostrarMissatgeSuccess("Participant amb DNI= " + dni + " inscrit correctament", "Inscripció realitzada");
				if (!model.desar(AplicacioCentreLudic.getNOM_FITXER()))
					vista.mostrarMissatgeWarning("La informació no s'ha pogut desar correctament", "Error al guardar automaticament");
				vista.clearInscriureParticipant();
				vista.mostrarLlistaParticipants(model.consultarInscripcionsActivitat(nomActivitat));
			}
		}
		else
			vista.mostrarMissatgeError("Omple les dades correctament", "Error al Inscriure un nou Participant");
	}

	private boolean sonNumeros(String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return (false);
		}
		return (true);
	}

	protected void teclaCancelarInscripcioPulsada()
	{
		String nomActivitat = vista.getNomActivitatConsultada();
		String dni = vista.getDNICancelarInscripcio();
		
		if (nomActivitat != null && dni != null)
		{
			if(!model.cancelarInscripcioPersonaActivitat(nomActivitat, dni))
				vista.mostrarMissatgeError("No s'ha pogut cancelar la inscripció, dni no trobat", "Error al cancelar inscripció");
			else
			{
				vista.mostrarMissatgeSuccess("Participant amb DNI= " + dni + " cancel·lat correctament", "Cancelació realitzada");
				if (!model.desar(AplicacioCentreLudic.getNOM_FITXER()))
					vista.mostrarMissatgeWarning("La informació no s'ha pogut desar correctament", "Error al guardar automaticament");
				vista.clearEliminarParticipant();
				vista.mostrarLlistaParticipants(model.consultarInscripcionsActivitat(nomActivitat));
			}
		}
		else
			vista.mostrarMissatgeError("Omple les dades correctament", "Error al cancelar inscripció");
	}
}
