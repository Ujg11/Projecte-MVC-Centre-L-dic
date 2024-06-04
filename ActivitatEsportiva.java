import java.time.LocalDate;
import java.time.LocalTime;

public class ActivitatEsportiva extends Activitat
{
	private String[] dies;

	private LocalDate dataInici;
	private LocalDate dataFi;
	private LocalTime[] horesInici;
	private LocalTime[] horesFi;

	public ActivitatEsportiva(String nom, String des, int maxPart, String a, String p, String[] dies, LocalDate dataIcici,
						LocalDate dataFi, LocalTime[] horaInici, LocalTime[] horaFi)
	{
		super(nom, des, maxPart, a, p);
		this.dies = dies;
		this.dataInici = dataIcici;
		this.dataFi = dataFi;
		this.horesInici = horaInici;
		this.horesFi = horaFi;
	}

	public boolean esPossibleInscriures(LocalDate data)
	{
		if (data.isAfter(this.dataFi))
			return (false);
		return (true);
	}

	public boolean admetInscripcio()
	{
		LocalDate dataActual = LocalDate.now();

		if (esPossibleInscriures(dataActual) && this.llocPerInscripcio())
			return (true);
		return (false);
	}

	public boolean ferInscripcio(Persona p)
	{
		LocalDate dataActual = LocalDate.now();
		
		if (p == null)
			return (false);
		if (esPossibleInscriures(dataActual) && this.llocPerInscripcio())
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
		return (1);
	}

	public String[] getDies() {
		return dies;
	}

	public void setDies(String[] dies) {
		this.dies = dies;
	}

	public LocalDate getDataInici() {
		return dataInici;
	}

	public void setDataInici(LocalDate dataIcici) {
		this.dataInici = dataIcici;
	}

	public LocalDate getDataFi() {
		return dataFi;
	}

	public void setDataFi(LocalDate dataFi) {
		this.dataFi = dataFi;
	}

	public LocalTime[] getHoresInici() {
		return horesInici;
	}

	public void setHoresInici(LocalTime[] horaInici) {
		this.horesInici = horaInici;
	}

	public LocalTime[] getHoresFi() {
		return horesFi;
	}

	public void setHoresFi(LocalTime[] horaFi) {
		this.horesFi = horaFi;
	}
}
