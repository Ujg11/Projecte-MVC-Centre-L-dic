import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivitatFormativa extends Activitat
{
	// Inicializar con una fecha y hora específicas (año, mes, día, hora, minuto, segundo)
	//LocalDateTime fechaHoraEspecifica = LocalDateTime.of(2022, 5, 8, 14, 30, 0);
	private LocalDateTime diaIHora;
	private LocalDate dataLimit;
	private double duradaActivitat;

	public ActivitatFormativa(String nom, String descripcio, int maximParticipants,
			String adresa, String poblacio, LocalDateTime data, LocalDate limit, double durada)
	{
		super(nom, descripcio, maximParticipants, adresa, poblacio);
		this.diaIHora = data;
		this.dataLimit = limit;
		this.duradaActivitat = durada;
	}

	public boolean esPossibleInscriures()
	{
		LocalDate dataActual = LocalDate.now();

		if (dataActual.isBefore(this.dataLimit) || dataActual.isEqual(this.dataLimit))
			return (true);
		return (false);
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
		return (3);
	}

	public LocalDateTime getDiaIHora() {
		return diaIHora;
	}

	public void setDiaIHora(LocalDateTime diaIHora) {
		this.diaIHora = diaIHora;
	}

	public LocalDate getDataLimit() {
		return dataLimit;
	}

	public void setDataLimit(LocalDate dataLimit) {
		this.dataLimit = dataLimit;
	}

	public double getDuradaActivitat() {
		return duradaActivitat;
	}

	public void setDuradaActivitat(double duradaActivitat) {
		this.duradaActivitat = duradaActivitat;
	}
}
