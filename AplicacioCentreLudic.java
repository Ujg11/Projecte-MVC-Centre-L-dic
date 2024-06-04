import javax.swing.SwingUtilities;

public class AplicacioCentreLudic
{
	private static String NOM_FITXER = "Fitxer_Activitats.obj";

	public static void main(String[] args) {
		IVistaCentreLudic vista = new FinestraCentreLudic();
		IModelCentreLudic model = new CentreLudic();
		model.recuperar(NOM_FITXER);
		ControladorCentreLudic controlador = new ControladorCentreLudic(vista, model);

		SwingUtilities.invokeLater(
			new Runnable()
			{
				public void run()
				{
					vista.setVisible(true);
				}
			}
		);
	}

	public static String getNOM_FITXER() {
		return NOM_FITXER;
	}

	public static void setNOM_FITXER(String nOM_FITXER) {
		NOM_FITXER = nOM_FITXER;
	}
}
