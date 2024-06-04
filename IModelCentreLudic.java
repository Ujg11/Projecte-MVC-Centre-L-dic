public interface IModelCentreLudic
{
	public boolean 				crearNovaActivitat(Activitat a);
	public Iterable<Activitat>	consultarActivitats(int fAdmetInscripcions, int tipusActivitat, String fPoblacio);
	public boolean				eliminarActivitat(String nom);
	public Activitat			trobarActivitat(String nom);
	public boolean				inscriurePersonaActivitat(String nomActivitat, Persona p);
	public boolean				cancelarInscripcioPersonaActivitat(String nomActivitat, String dni);
	public Iterable<Persona>	consultarInscripcionsActivitat(String nomAct);
	public boolean				poblacioCorrecte(String p);

	public boolean				desar(String nomFitxer);
	public void					recuperar(String nomFitxer);
}
