import java.awt.event.ActionListener;

public interface IVistaCentreLudic
{
	public void addListenerCrearActivitatEsportiva(ActionListener l);
	public void addListenerCrearActivitatCultural(ActionListener l);
	public void addListenerCrearActivitatFormativa(ActionListener l);
	public void addListenerConsultarActivitat(ActionListener l);
	public void addListenerEliminarActivitat(ActionListener l);
	public void addListenerAplicarFiltresLlistaActivitats(ActionListener l);
	public void addListenerInscriureParticipant(ActionListener l);
	public void addListenerCancelarInscripcio(ActionListener l);

	public void	addListenerConsultarActivitats(ActionListener l);
	
	public void mostrarLlistaActivitats(Iterable<Activitat> llista);
	public void mostrarLlistaParticipants(Iterable<Persona> llista);
	public void mostrarInformacioActivitat(Activitat a);
	public ActivitatEsportiva	getActivitatEsportivaNova();
	public ActivitatCultural	getActivitatCulturalNova();
	public ActivitatFormativa	getActivitatFormativaNova();
	public String				getNomActivitatABuscar();
	public void	mostrarMissatgeError(String missatge, String titol);
	public void	mostrarMissatgeWarning(String missatge, String titol);
	public void mostrarMissatgeSuccess(String missatge, String titol);
	public int	getFiltreInscripcions();
	public int	getFiltreTipusActivitat();
	public String	getFiltrePoblacio();
	public String	getNomActivitatConsultada();
	public String	getDNIinscripcio();
	public String	getTelefonInscripcio();
	public String	getDNICancelarInscripcio();
	public void		setVisible(boolean v);
	public void		clearActivitatCultural();
	public void		clearActivitatEsportiva();
	public void		clearActivitatFormativa();
	public void		clearInscriureParticipant();
	public void		clearEliminarParticipant();
	public void		clearActivitatEscollida();
	public void		clearFiltres();
}
