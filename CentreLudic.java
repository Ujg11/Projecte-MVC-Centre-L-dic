import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CentreLudic implements IModelCentreLudic, Serializable
{
	private Map<String, Activitat> activitats;

	CentreLudic()
	{
		this.activitats = new TreeMap<>();
	}

	public boolean crearNovaActivitat(Activitat a)
	{
		if (a == null)
			return (false);
		if (!this.activitats.containsKey(a.getNom()))
		{
			this.activitats.put(a.getNom(), a);
			return (true);
		}
		return (false);
	}

	public boolean poblacioCorrecte(String p)
	{
		for (Activitat a : activitats.values())
		{
			if (a.getPoblacio().startsWith(p))
				return (true);
		}
		return (false);
	}

	// Admet inscripcions: 1 Indiferent, 2 Si, 3 No
	// Tipus activitat: 0 indiferent, 1 esportiva, 2 cultural, 3 formativa
	public Iterable<Activitat> consultarActivitats(int fAdmetInscripcions, int tipusActivitat, String fPoblacio)
	{
		List<Activitat> llista = new ArrayList<>();
		boolean			flag = true;

		for (Activitat a : this.activitats.values())
		{
			flag = true;
			if ((fAdmetInscripcions == 2 && !a.admetInscripcio()) ||
				(fAdmetInscripcions == 3 && a.admetInscripcio()))
				flag = false;
			if (tipusActivitat != 0 && tipusActivitat != a.tipusActivitat())
				flag = false;
			if (fPoblacio != null && !a.getPoblacio().startsWith(fPoblacio))
				flag = false;
			if (flag)
				llista.add(a);
		}
		return (llista);
	}

	public Activitat trobarActivitat(String nom)
	{
		return (this.activitats.get(nom));
	}

	public boolean eliminarActivitat(String nom)
	{
		Activitat a = this.activitats.remove(nom);
		if (a != null)
			return (true);
		return (false);
	}

	public boolean inscriurePersonaActivitat(String nomActivitat, Persona p)
	{
		Activitat	a = this.activitats.get(nomActivitat);

		if (a != null)
			return (a.ferInscripcio(p));
		return (false);
	}

	public boolean cancelarInscripcioPersonaActivitat(String nomActivitat, String dni)
	{
		Activitat	a = this.activitats.get(nomActivitat);

		if (a != null)
		{
			return (a.cancelarInscripcio(dni));
		}
		return (false);
	}

	public Iterable<Persona> consultarInscripcionsActivitat(String nomAct)
	{
		Activitat a = this.activitats.get(nomAct);

		if (a != null)
			return (a.consultarInscrits());
		return (new ArrayList<>());
	}

	public void recuperar(String nomFitxer)
	{
		CentreLudic c;
		try {
			FileInputStream		fis = new FileInputStream(nomFitxer);
			ObjectInputStream	ois = new ObjectInputStream(fis);
			c = (CentreLudic) ois.readObject();
			ois.close();
		}
		catch (IOException e)
		{
			c = new CentreLudic();
		}
		catch (ClassNotFoundException e)
		{
			c = new CentreLudic();
		}
		this.activitats = c.activitats;
	}

	public boolean desar(String nomFitxer)
	{
		try {
			FileOutputStream fos = new FileOutputStream(nomFitxer);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			return (true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return (false);
		}
	}

}
