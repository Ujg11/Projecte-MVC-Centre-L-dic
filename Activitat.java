import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Activitat implements Serializable
{
	private String					nom;
	private String					descripcio;
	private int						maximParticipants;
	protected int					personesInscrites;
	private String					adresa;
	private String					poblacio;
	protected Map<String, Persona>	inscrits;

	public Activitat(String nom, String descripcio, int maximParticipants, String adresa, String poblacio)
	{
		this.nom = nom;
		this.descripcio = descripcio;
		this.maximParticipants = maximParticipants;
		this.personesInscrites = 0;
		this.adresa = adresa;
		this.poblacio = poblacio;
		inscrits = new HashMap<>();
	}

	public boolean llocPerInscripcio()
	{
		if (personesInscrites < maximParticipants)
			return (true);
		else
			return (false);
	}

	public abstract boolean ferInscripcio(Persona p);
	public abstract boolean admetInscripcio();
	public abstract int tipusActivitat();

	public String getTipus()
	{
		return (this.getClass().getName());
	}

	public boolean cancelarInscripcio(String dni)
	{
		Persona p = this.inscrits.get(dni);

		if (p == null)
			return (false);
		if (this.inscrits.remove(dni) != null)
			return (true);
		return (false);
	}

	public Iterable<Persona> consultarInscrits()
	{
		List<Persona> persones = new ArrayList<>();

		for (Persona p : this.inscrits.values())
		{
			persones.add(p);
		}
		return (persones);
	}

	public String toString()
	{
		return (this.getNom());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getMaximParticipants() {
		return maximParticipants;
	}

	public void setMaximParticipants(int maximParticipants) {
		this.maximParticipants = maximParticipants;
	}

	public int getPersonesInscrites() {
		return personesInscrites;
	}

	public void setPersonesInscrites(int personesInscrites) {
		this.personesInscrites = personesInscrites;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPoblacio() {
		return poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}	
}