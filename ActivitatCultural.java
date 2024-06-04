//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.LocalTime;

public class ActivitatCultural extends Activitat
{
	private double preu;
	private LocalDateTime horariActivitat;

	public ActivitatCultural(String nom, String descripcio, int maximParticipants, String adresa, String poblacio,
							double preu, LocalDateTime horari)
	{
		super(nom, descripcio, maximParticipants, adresa, poblacio);
		this.preu = preu;
		this.horariActivitat = horari;
	}

	public boolean esPossibleInscriures()
	{
		LocalDateTime dataActual = LocalDateTime.now();
		return (dataActual.isBefore(this.horariActivitat.minusHours(48)));//restem 48 h a l'hora d'inici i mirem si es pot
	}

	public boolean admetInscripcio()
	{
		if (esPossibleInscriures() && llocPerInscripcio())
			return (true);
		return (false);
	}

	public boolean ferInscripcio(Persona p)
	{
		if (p == null)
			return (false);
		if (esPossibleInscriures() && llocPerInscripcio())
		{
			if (!this.inscrits.containsKey(p.getDNI()))
			{
				this.inscrits.put(p.getDNI(), p);
				this.personesInscrites++;
				return (true);
			}
		}
		return (false);
	}

	public int tipusActivitat()
	{
		return (2);
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

	public LocalDateTime getHorariActivitat() {
		return horariActivitat;
	}

	public void setHorariActivitat(LocalDateTime horariActivitat) {
		this.horariActivitat = horariActivitat;
	}
}
