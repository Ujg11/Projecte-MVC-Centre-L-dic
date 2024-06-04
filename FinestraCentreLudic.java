import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;

public class FinestraCentreLudic extends JFrame implements IVistaCentreLudic
{
	//Panell Principal
	private JPanel		panelPrincipal;
	private JMenuItem	iniciApp;
	private JMenuItem	crearNovaActivitat;
	private JMenuItem	consultarLlistaActivitats;

	//Panel inici
	private JPanel		panelInici;

	//Panell Crear Activitat:
	private JPanel		panelCrearActivitat;
		private JComboBox	tipusActivitat; //escullim qui tipus d'activitat i activem els card layouts

		//Panell Activitat Concreta
		private JPanel	panelActivitatConcreta;
			//Podem tenir un altre Card Layout depenent de la activitat

			//Panell Inicial
			private JPanel	panelInicialCrearActivitat;

			//Activitat Cultural
			private JPanel	panelActivitatCultural;
				private JTextField		nomActivitatC, descripcioActivitatC, adresaActivitatC,
										poblacioActivitatC;
				private JSpinner		numMaximParticipantsC, preuActivitatC, horaIniciACultural, minIniciACultural;
				private JDateChooser	dataActivitatCultural;
				private JButton			botoInserirNovaActivitatC, botoClearAC, botoTornarAC;

			//Activitat Esportiva
			private JPanel	panelActivitatEsportiva;
				private JTextField		nomActivitatE, descripcioActivitatE, adresaActivitatE,
										poblacioActivitatE;
				private JSpinner		numMaximParticipantsE; //horaIniciEsportiva, minIniciEsportiva, horaFiEsportiva, minFiEsportiva;
				private JCheckBox		dilluns, dimarts, dimecres, dijous, divendres, dissabte;
				private JSpinner		dillHor, dillMin, dimaHor, dimaMin, dimeHor, dimeMin, dijHor, dijMin, divHor, divMin, dissHor, dissMin;
				private JSpinner		dillHorFi, dillMinFi, dimaHorFi, dimaMinFi, dimeHorFi, dimeMinFi, dijHorFi, dijMinFi, divHorFi, divMinFi, dissHorFi, dissMinFi;
				private JDateChooser	dataIniciEsportiva;
				private JDateChooser	dataFiEsportiva;
				private JButton			botoInserirNovaActivitatE, botoClearAE, botoTornarAE;

			//Activitat Formativa
			private JPanel	panelActivitatFormativa;
				private JTextField		nomActivitatF, descripcioActivitatF,  adresaActivitatF,
										poblacioActivitatF;
				private JSpinner		numMaximParticipantsF, horaIniciActivitatF, duracioActivitatF;
				private JDateChooser	dataLimitInscripcioF;
				private JDateChooser	dataActivitatFormativa;
				private JButton			botoInserirNovaActivitatF, botoClearAF, botoTornarAF;
	
	//Panell Llista Activitats:
	private JPanel panelLlistaActivitats;

		//Panel de les opcions
		private JPanel panelOpcions;
			//Filtres possibles
			private JComboBox	admetInscripcions;
			private JComboBox	tipusActivitatFiltre;
			private	JTextField	poblacioFiltre;

			private JButton		aplicar;
			private JTextArea	llistaActivitats;
			private JTextField	activitatAModificar;
			private JButton		consultarActivitat;
			private JButton		eliminarActivitat;
		
		//Panell de Consultar Activitat
		private JPanel	panelConsultarActivitat; //(borderLayout)
			
			private JButton	tornarDeConsultaAOpcions;
			private JPanel	panelConsultaInfoComuna;
				private JTextField	consultaNom, consultaDescripcio, consultaNumMaxParticipants, consultaAdresa,
									consultaPoblacio;

			private JPanel	panelConsultaInfoParticular;//(cardLayout)
				//Panell consulta info AEsportiva
				private JPanel		panelConsultaAEsportiva;
					private JTextField	consultaDiesAE, consultaDataIniciAE, consultaDataFiAE;
					//private JTextField	consultaHIniciAE, consultaHFiAE;
					private JTextField	conHIDill, conHIDima, conHIDime, conHIDij, conHIDiv, conHIDis;
					private JTextField	conHFDill, conHFDima, conHFDime, conHFDij, conHFDiv, conHFDis;
				
				//Panell consulta info ACultural
				private JPanel	panelConsultaACultural;
					private	JTextField	consultarPreuAC, consultarDataAC, consultarHoraAC;

				//Panell consulta info AFormativa
				private JPanel	panelConsultaAFormativa;
					private JTextField	consultarDataAF, consultarHoraAF, consultarDataLimitAF, consultarDuracioAF;

		//Panell de Gestionar Activitat
		private JPanel	panelGestionarActivitat;
				private JTextArea	llistaInscripcions;
				private JTextField	gestionaDNI, gestionaTelefon;
				private	JTextField	DNIACancelar;
				private JButton		inscriureBoto, cancelarInsBoto;		

	public FinestraCentreLudic()
	{
		super();
		this.init();
	}

	private	void init()
	{
		this.setLocation(700, 200);
		this.setTitle("Activitats Centre Lúdic");
		this.setSize(new Dimension(700, 600));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentsPanelContenido();
		this.setJMenuBar(crearMenuBar());
		//this.pack();
	}

	private void addComponentsPanelContenido()
	{
		JPanel panelContenido = new JPanel(new BorderLayout());

		panelContenido.setPreferredSize(new Dimension(475, 300));
		panelContenido.add(crearPanelPrincipal(), BorderLayout.CENTER);
		this.setContentPane(panelContenido);
	}

	private JPanel crearPanelPrincipal()
	{
		this.panelPrincipal = new JPanel(new CardLayout());
		
		this.panelPrincipal.add(crearPanelInici(), "Inici");
		this.panelPrincipal.add(crearPanelCrearActivitat(), "PanelCrearActivitat");
		this.panelPrincipal.add(crearPanelLlistarActivitats(), "PanelLlistaActivitats");
		return (this.panelPrincipal);
	}

	protected void showCardPanelPrincipal(String card)
	{
		((CardLayout) this.panelPrincipal.getLayout()).show(panelPrincipal, card);
	}

	private JPanel crearPanelInici()
	{
		this.panelInici = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 15, 15);	

		JLabel benbinguda = new JLabel("Benvinguts i benvingudes al nostre Centre Lúdic!");
		benbinguda.setFont(new Font("Arial", Font.BOLD, 24));
		benbinguda.setForeground(Color.ORANGE);
		benbinguda.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelInici.add(benbinguda, gbc);

		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		ImageIcon im = new ImageIcon("C:/Users/Lenovo/OneDrive/Documents/PAOO_repo/Projecte/imatge_inicial.jpg");
		JLabel imatge = new JLabel(im);
		this.panelInici.add(imatge, gbc);
		return (panelInici);
	}

	private JMenuBar crearMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();

		menuBar.add(crearMenuAplicacion());
		addOyentesItemsMenuVentanaPrincipal();
		return (menuBar);
	}

	private JMenu crearMenuAplicacion()
	{
		JMenu	menu = new JMenu("Opcions");
		menu.setMnemonic(KeyEvent.VK_M);

		this.iniciApp = new JMenuItem("Inici", KeyEvent.VK_SPACE);
		this.crearNovaActivitat = new JMenuItem("Crear nova activitat", KeyEvent.VK_0);
		this.consultarLlistaActivitats = new JMenuItem("Consultar Llista de les Activitats", KeyEvent.VK_1);

		menu.add(this.iniciApp);
		menu.add(this.crearNovaActivitat);
		menu.add(this.consultarLlistaActivitats);
		return (menu);
	}

	private void addOyentesItemsMenuVentanaPrincipal()
	{
		this.iniciApp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatCultural();
				clearActivitatEsportiva();
				clearActivitatFormativa();
				clearInscriureParticipant();
				clearEliminarParticipant();
				clearActivitatEscollida();
				showCardPanelPrincipal("Inici");
			}
		});
		this.crearNovaActivitat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatCultural();
				clearActivitatEsportiva();
				clearActivitatFormativa();
				clearInscriureParticipant();
				clearEliminarParticipant();
				clearActivitatEscollida();
				showCardPanelPrincipal("PanelCrearActivitat");
			}
		});
	}

	//Panel per crear les activitats on primer hi haura un JComboBox per escullir el tipus d'activitat
	//i despres apareixeran cardLayouts segons el tipus
	private JPanel crearPanelCrearActivitat()
	{
		this.panelCrearActivitat = new JPanel(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		String tipus[] = {"Tipus", "Activitat Esportiva", "Activitat Formativa", "Activitat Cultural"};
		panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
		panelSuperior.add(Box.createHorizontalStrut(20));
		panelSuperior.add(new JLabel("Escull el tipus d'activitat i ompla els buits"));
		panelSuperior.add(Box.createHorizontalStrut(10));
		this.tipusActivitat = new JComboBox<String>(tipus);
		panelSuperior.add(this.tipusActivitat);
		panelSuperior.add(Box.createHorizontalStrut(150));
		this.panelCrearActivitat.add(panelSuperior, BorderLayout.NORTH);

		this.panelActivitatConcreta = new JPanel(new CardLayout());
		this.panelInicialCrearActivitat = new JPanel();

		this.panelActivitatConcreta.add(this.panelInicialCrearActivitat, "Tipus");
		this.panelActivitatConcreta.add(crearPanelActivitatEsportiva(), "Activitat Esportiva");
		this.panelActivitatConcreta.add(crearPanelActivitatCultural(), "Activitat Cultural");
		this.panelActivitatConcreta.add(crearPanelActivitatFormativa(), "Activitat Formativa");
		this.panelCrearActivitat.add(this.panelActivitatConcreta, BorderLayout.CENTER);

		addOyentesItemsPanelCrearActivitat();
		
		return (this.panelCrearActivitat);
	}

	private void addOyentesItemsPanelCrearActivitat()
	{
		this.tipusActivitat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s = (String) tipusActivitat.getSelectedItem();
				showCardActivitatConcreta(s);
				clearActivitatCultural();
				clearActivitatEsportiva();
				clearActivitatFormativa();
			}
		});
		this.botoTornarAC.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatCultural();
				tipusActivitat.setSelectedItem("Tipus");
				showCardActivitatConcreta("Tipus");
				showCardPanelPrincipal("Inici");
			}
		});
		this.botoTornarAE.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatEsportiva();
				tipusActivitat.setSelectedItem("Tipus");
				showCardActivitatConcreta("Tipus");
				showCardPanelPrincipal("Inici");
			}
		});
		this.botoTornarAF.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatFormativa();
				tipusActivitat.setSelectedItem("Tipus");
				showCardActivitatConcreta("Tipus");
				showCardPanelPrincipal("Inici");
			}
		});
		this.botoClearAC.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatCultural();
			}
		});
		this.botoClearAE.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatEsportiva();
			}
		});
		this.botoClearAF.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivitatFormativa();
			}
		});
	}

	public void clearActivitatCultural()
	{
		nomActivitatC.setText("Nom");
		descripcioActivitatC.setText("Descripció");
		adresaActivitatC.setText("Adreça");
		poblacioActivitatC.setText("Població");
		numMaximParticipantsC.setValue(0);
		preuActivitatC.setValue(0);
		horaIniciACultural.setValue(0);
		minIniciACultural.setValue(0);
		dataActivitatCultural.setDate(null);
	}

	public void clearActivitatEsportiva()
	{
		nomActivitatE.setText("Nom");
		descripcioActivitatE.setText("Descripció");
		adresaActivitatE.setText("Adreça");
		poblacioActivitatE.setText("Població");
		numMaximParticipantsE.setValue(0);
		//horaIniciEsportiva.setValue(0);
		//minIniciEsportiva.setValue(0);
		//horaFiEsportiva.setValue(0);
		//minFiEsportiva.setValue(0);
		dilluns.setSelected(false);
		dimarts.setSelected(false);
		dimecres.setSelected(false);
		dijous.setSelected(false);
		divendres.setSelected(false);
		dissabte.setSelected(false);
		dataIniciEsportiva.setDate(null);
		dataFiEsportiva.setDate(null);
		dillHor.setValue(0);
		dillMin.setValue(0);
		dimaHor.setValue(0);
		dimaMin.setValue(0);
		dimeHor.setValue(0);
		dimeMin.setValue(0);
		dijHor.setValue(0);
		dijMin.setValue(0);
		divHor.setValue(0);
		divMin.setValue(0);
		dissHor.setValue(0);
		dissMin.setValue(0);
		dillHorFi.setValue(0);
		dillMinFi.setValue(0);
		dimaHorFi.setValue(0);
		dimaMinFi.setValue(0);
		dimeHorFi.setValue(0);
		dimeMinFi.setValue(0);
		dijHorFi.setValue(0);
		dijMinFi.setValue(0);
		divHorFi.setValue(0);
		divMinFi.setValue(0);
		dissHorFi.setValue(0);
		dissMinFi.setValue(0);
	}

	public void clearActivitatFormativa()
	{
		nomActivitatF.setText("Nom");
		descripcioActivitatF.setText("Descripció");
		adresaActivitatF.setText("Adreça");
		poblacioActivitatF.setText("Població");
		numMaximParticipantsF.setValue(0);
		horaIniciActivitatF.setValue(0);
		duracioActivitatF.setValue(0);
		dataLimitInscripcioF.setDate(null);
		dataActivitatFormativa.setDate(null);
	}

	public void clearInscriureParticipant()
	{
		gestionaDNI.setText("");
		gestionaTelefon.setText("");
	}

	public void		clearEliminarParticipant()
	{
		DNIACancelar.setText("");
	}

	public void	clearActivitatEscollida()
	{
		activitatAModificar.setText("");
	}

	//Panells: "ActivitatEsportiva", "ActivitatCultural", "ActivitatFormativa"
	protected void showCardActivitatConcreta(String card)
	{
		((CardLayout) this.panelActivitatConcreta.getLayout()).show(panelActivitatConcreta, card);
	}
	//((CardLayout) this.panelActivitatConcreta.getLayout()).next();

	private JPanel crearPanelActivitatEsportiva()
	{
		this.panelActivitatEsportiva = new JPanel();
		this.panelActivitatEsportiva.setLayout(new BoxLayout(this.panelActivitatEsportiva, BoxLayout.Y_AXIS));
		this.panelActivitatEsportiva.setBorder(BorderFactory.createTitledBorder("Creació activitat esportiva"));
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(10));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Nom de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.nomActivitatE = new JTextField("Nom");
		linea1.add(this.nomActivitatE);
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Descripció de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.descripcioActivitatE = new JTextField("Descripció");
		linea1.add(this.descripcioActivitatE);
		linea1.add(Box.createHorizontalStrut(20));
		this.panelActivitatEsportiva.add(linea1);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Número Màxim de Participants"));
		linea2.add(Box.createHorizontalStrut(10));
		SpinnerNumberModel participantsModel = new SpinnerNumberModel(0, 0, 75, 1);
		this.numMaximParticipantsE = new JSpinner(participantsModel);
		DefaultEditor editor1 = new JSpinner.DefaultEditor(numMaximParticipantsE);
		editor1.getTextField().setColumns(3);
		editor1.getTextField().setEditable(false);
		this.numMaximParticipantsE.setEditor(editor1);
		linea2.add(this.numMaximParticipantsE);
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Adreça on es realitza l'activitat"));
		linea2.add(Box.createHorizontalStrut(10));
		this.adresaActivitatE = new JTextField("Adreça");
		linea2.add(this.adresaActivitatE);
		linea2.add(Box.createHorizontalStrut(20));
		this.panelActivitatEsportiva.add(linea2);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));

		JPanel linea2_1 = new JPanel();
		linea2_1.setLayout(new BoxLayout(linea2_1, BoxLayout.X_AXIS));
		linea2_1.add(Box.createHorizontalStrut(20));
		linea2_1.add(new JLabel("Població on es realitzarà l'activitat"));
		linea2_1.add(Box.createHorizontalStrut(15));
		this.poblacioActivitatE = new JTextField("Població");
		linea2_1.add(this.poblacioActivitatE);
		linea2_1.add(Box.createHorizontalStrut(280));
		this.panelActivitatEsportiva.add(linea2_1);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(10));
		linea3.add(new JLabel("Escull els dies de la setmana en que es realitzarà l'activitat i tria l'horari. Nomes es guardarà l'horari dels marcats"));
		linea3.add(Box.createHorizontalStrut(20));
		this.panelActivitatEsportiva.add(linea3);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(10));

		JPanel linea4 = new JPanel();
		linea4.setLayout(new BoxLayout(linea4, BoxLayout.X_AXIS));
		linea4.add(Box.createHorizontalStrut(20));
		this.dilluns = new JCheckBox("Dilluns");
		linea4.add(this.dilluns);
		linea4.add(Box.createHorizontalStrut(15));
		this.dimarts = new JCheckBox("Dimarts");
		linea4.add(this.dimarts);
		linea4.add(Box.createHorizontalStrut(15));
		this.dimecres = new JCheckBox("Dimecres");
		linea4.add(this.dimecres);
		linea4.add(Box.createHorizontalStrut(15));
		this.dijous = new JCheckBox("Dijous");
		linea4.add(this.dijous);
		linea4.add(Box.createHorizontalStrut(15));
		this.divendres = new JCheckBox("Divendres");
		linea4.add(this.divendres);
		linea4.add(Box.createHorizontalStrut(15));
		this.dissabte = new JCheckBox("Dissabte");
		linea4.add(this.dissabte);
		linea4.add(Box.createHorizontalStrut(15));
		this.panelActivitatEsportiva.add(linea4);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(5));

		JPanel linea4_1 = new JPanel();
		linea4_1.setLayout(new BoxLayout(linea4_1, BoxLayout.X_AXIS));
		linea4_1.add(Box.createHorizontalStrut(30));
		linea4_1.add(new JLabel("Horari d'inici de les activitats:"));
		this.panelActivitatEsportiva.add(linea4_1);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(10));

		JPanel linea4_2 = new JPanel();
		linea4_2.setLayout(new BoxLayout(linea4_2, BoxLayout.X_AXIS));
		linea4_2.add(Box.createHorizontalStrut(20));
		linea4_2.add(new JLabel("Dilluns:"));
		linea4_2.add(Box.createHorizontalStrut(10));
		
		SpinnerNumberModel horesModelDill = new SpinnerNumberModel(0, 0, 23, 1);
		this.dillHor = new JSpinner(horesModelDill);
		DefaultEditor editorDillH = new JSpinner.DefaultEditor(dillHor);
		editorDillH.getTextField().setColumns(2);
		editorDillH.getTextField().setEditable(false);
		this.dillHor.setEditor(editorDillH);
		linea4_2.add(this.dillHor);
		linea4_2.add(new JLabel("h"));
		linea4_2.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDill = new SpinnerNumberModel(0, 0, 45, 15);
		this.dillMin = new JSpinner(minModelDill);
		DefaultEditor editorDillM = new JSpinner.DefaultEditor(dillMin);
		editorDillM.getTextField().setColumns(2);
		editorDillM.getTextField().setEditable(false);
		this.dillMin.setEditor(editorDillM);
		linea4_2.add(this.dillMin);
		linea4_2.add(new JLabel("min"));
		linea4_2.add(Box.createHorizontalStrut(10));

		linea4_2.add(new JLabel("Dimarts:"));
		linea4_2.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDima = new SpinnerNumberModel(0, 0, 23, 1);
		this.dimaHor = new JSpinner(horesModelDima);
		JSpinner.DefaultEditor editorDimaH = new JSpinner.DefaultEditor(this.dimaHor);
		editorDimaH.getTextField().setColumns(2);
		editorDimaH.getTextField().setEditable(false);
		this.dimaHor.setEditor(editorDimaH);
		linea4_2.add(this.dimaHor);
		linea4_2.add(new JLabel("h"));
		linea4_2.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDima = new SpinnerNumberModel(0, 0, 45, 15);
		this.dimaMin = new JSpinner(minModelDima);
		JSpinner.DefaultEditor editorDimaM = new JSpinner.DefaultEditor(this.dimaMin);
		editorDimaM.getTextField().setColumns(2);
		editorDimaM.getTextField().setEditable(false);
		this.dimaMin.setEditor(editorDimaM);
		linea4_2.add(this.dimaMin);
		linea4_2.add(new JLabel("min"));
		linea4_2.add(Box.createHorizontalStrut(10));

		linea4_2.add(new JLabel("Dimecres:"));
		linea4_2.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDime = new SpinnerNumberModel(0, 0, 23, 1);
		this.dimeHor = new JSpinner(horesModelDime);
		JSpinner.DefaultEditor editorDimeH = new JSpinner.DefaultEditor(this.dimeHor);
		editorDimeH.getTextField().setColumns(2);
		editorDimeH.getTextField().setEditable(false);
		this.dimeHor.setEditor(editorDimeH);
		linea4_2.add(this.dimeHor);
		linea4_2.add(new JLabel("h"));
		linea4_2.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDime = new SpinnerNumberModel(0, 0, 45, 15);
		this.dimeMin = new JSpinner(minModelDime);
		JSpinner.DefaultEditor editorDimeM = new JSpinner.DefaultEditor(this.dimeMin);
		editorDimeM.getTextField().setColumns(2);
		editorDimeM.getTextField().setEditable(false);
		this.dimeMin.setEditor(editorDimeM);
		linea4_2.add(this.dimeMin);
		linea4_2.add(new JLabel("min"));
		linea4_2.add(Box.createHorizontalStrut(30));

		this.panelActivitatEsportiva.add(linea4_2);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(10));

		JPanel linea4_3 = new JPanel();
		linea4_3.setLayout(new BoxLayout(linea4_3, BoxLayout.X_AXIS));
		linea4_3.add(Box.createHorizontalStrut(20));

		linea4_3.add(new JLabel("Dijous:"));
		linea4_3.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDij = new SpinnerNumberModel(0, 0, 23, 1);
		this.dijHor = new JSpinner(horesModelDij);
		JSpinner.DefaultEditor editorDijH = new JSpinner.DefaultEditor(this.dijHor);
		editorDijH.getTextField().setColumns(2);
		editorDijH.getTextField().setEditable(false);
		this.dijHor.setEditor(editorDijH);
		linea4_3.add(this.dijHor);
		linea4_3.add(new JLabel("h"));
		linea4_3.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDij = new SpinnerNumberModel(0, 0, 45, 15);
		this.dijMin = new JSpinner(minModelDij);
		JSpinner.DefaultEditor editorDijM = new JSpinner.DefaultEditor(this.dijMin);
		editorDijM.getTextField().setColumns(2);
		editorDijM.getTextField().setEditable(false);
		this.dijMin.setEditor(editorDijM);
		linea4_3.add(this.dijMin);
		linea4_3.add(new JLabel("min"));
		linea4_3.add(Box.createHorizontalStrut(10));

		linea4_3.add(new JLabel("Divendres:"));
		linea4_3.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDiv = new SpinnerNumberModel(0, 0, 23, 1);
		this.divHor = new JSpinner(horesModelDiv);
		JSpinner.DefaultEditor editorDivH = new JSpinner.DefaultEditor(this.divHor);
		editorDivH.getTextField().setColumns(2);
		editorDivH.getTextField().setEditable(false);
		this.divHor.setEditor(editorDivH);
		linea4_3.add(this.divHor);
		linea4_3.add(new JLabel("h"));
		linea4_3.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDiv = new SpinnerNumberModel(0, 0, 45, 15);
		this.divMin = new JSpinner(minModelDiv);
		JSpinner.DefaultEditor editorDivM = new JSpinner.DefaultEditor(this.divMin);
		editorDivM.getTextField().setColumns(2);
		editorDivM.getTextField().setEditable(false);
		this.divMin.setEditor(editorDivM);
		linea4_3.add(this.divMin);
		linea4_3.add(new JLabel("min"));
		linea4_3.add(Box.createHorizontalStrut(10));

		linea4_3.add(new JLabel("Dissabte:"));
		linea4_3.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDiss = new SpinnerNumberModel(0, 0, 23, 1);
		this.dissHor = new JSpinner(horesModelDiss);
		JSpinner.DefaultEditor editorDissH = new JSpinner.DefaultEditor(this.dissHor);
		editorDissH.getTextField().setColumns(2);
		editorDissH.getTextField().setEditable(false);
		this.dissHor.setEditor(editorDissH);
		linea4_3.add(this.dissHor);
		linea4_3.add(new JLabel("h"));
		linea4_3.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDiss = new SpinnerNumberModel(0, 0, 45, 15);
		this.dissMin = new JSpinner(minModelDiss);
		JSpinner.DefaultEditor editorDissM = new JSpinner.DefaultEditor(this.dissMin);
		editorDissM.getTextField().setColumns(2);
		editorDissM.getTextField().setEditable(false);
		this.dissMin.setEditor(editorDissM);
		linea4_3.add(this.dissMin);
		linea4_3.add(new JLabel("min"));
		linea4_3.add(Box.createHorizontalStrut(30));

		this.panelActivitatEsportiva.add(linea4_3);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(5));

		JPanel linea4_4 = new JPanel();
		linea4_4.setLayout(new BoxLayout(linea4_4, BoxLayout.X_AXIS));
		linea4_4.add(Box.createHorizontalStrut(30));
		linea4_4.add(new JLabel("Horari de fi de les activitats:"));
		this.panelActivitatEsportiva.add(linea4_4);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(10));

		JPanel linea4_5 = new JPanel();
		linea4_5.setLayout(new BoxLayout(linea4_5, BoxLayout.X_AXIS));
		linea4_5.add(Box.createHorizontalStrut(20));
		linea4_5.add(new JLabel("Dilluns:"));
		linea4_5.add(Box.createHorizontalStrut(10));
		
		SpinnerNumberModel horesModelDillFi = new SpinnerNumberModel(0, 0, 23, 1);
		this.dillHorFi = new JSpinner(horesModelDillFi);
		DefaultEditor editorDillHFi = new JSpinner.DefaultEditor(dillHorFi);
		editorDillHFi.getTextField().setColumns(2);
		editorDillHFi.getTextField().setEditable(false);
		this.dillHorFi.setEditor(editorDillHFi);
		linea4_5.add(this.dillHorFi);
		linea4_5.add(new JLabel("h"));
		linea4_5.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDillFi = new SpinnerNumberModel(0, 0, 45, 15);
		this.dillMinFi = new JSpinner(minModelDillFi);
		DefaultEditor editorDillMFi = new JSpinner.DefaultEditor(dillMinFi);
		editorDillMFi.getTextField().setColumns(2);
		editorDillMFi.getTextField().setEditable(false);
		this.dillMinFi.setEditor(editorDillMFi);
		linea4_5.add(this.dillMinFi);
		linea4_5.add(new JLabel("min"));
		linea4_5.add(Box.createHorizontalStrut(10));

		linea4_5.add(new JLabel("Dimarts:"));
		linea4_5.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDimaFi = new SpinnerNumberModel(0, 0, 23, 1);
		this.dimaHorFi = new JSpinner(horesModelDimaFi);
		JSpinner.DefaultEditor editorDimaHFi = new JSpinner.DefaultEditor(this.dimaHorFi);
		editorDimaHFi.getTextField().setColumns(2);
		editorDimaHFi.getTextField().setEditable(false);
		this.dimaHorFi.setEditor(editorDimaHFi);
		linea4_5.add(this.dimaHorFi);
		linea4_5.add(new JLabel("h"));
		linea4_5.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDimaFi = new SpinnerNumberModel(0, 0, 45, 15);
		this.dimaMinFi = new JSpinner(minModelDimaFi);
		JSpinner.DefaultEditor editorDimaMFi = new JSpinner.DefaultEditor(this.dimaMinFi);
		editorDimaMFi.getTextField().setColumns(2);
		editorDimaMFi.getTextField().setEditable(false);
		this.dimaMinFi.setEditor(editorDimaMFi);
		linea4_5.add(this.dimaMinFi);
		linea4_5.add(new JLabel("min"));
		linea4_5.add(Box.createHorizontalStrut(10));

		linea4_5.add(new JLabel("Dimecres:"));
		linea4_5.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDimeFi = new SpinnerNumberModel(0, 0, 23, 1);
		this.dimeHorFi = new JSpinner(horesModelDimeFi);
		JSpinner.DefaultEditor editorDimeHFi = new JSpinner.DefaultEditor(this.dimeHorFi);
		editorDimeHFi.getTextField().setColumns(2);
		editorDimeHFi.getTextField().setEditable(false);
		this.dimeHorFi.setEditor(editorDimeHFi);
		linea4_5.add(this.dimeHorFi);
		linea4_5.add(new JLabel("h"));
		linea4_5.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDimeFi = new SpinnerNumberModel(0, 0, 45, 15);
		this.dimeMinFi = new JSpinner(minModelDimeFi);
		JSpinner.DefaultEditor editorDimeMFi = new JSpinner.DefaultEditor(this.dimeMinFi);
		editorDimeMFi.getTextField().setColumns(2);
		editorDimeMFi.getTextField().setEditable(false);
		this.dimeMinFi.setEditor(editorDimeMFi);
		linea4_5.add(this.dimeMinFi);
		linea4_5.add(new JLabel("min"));
		linea4_5.add(Box.createHorizontalStrut(30));

		this.panelActivitatEsportiva.add(linea4_5);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(10));

		JPanel linea4_6 = new JPanel();
		linea4_6.setLayout(new BoxLayout(linea4_6, BoxLayout.X_AXIS));
		linea4_6.add(Box.createHorizontalStrut(20));

		linea4_6.add(new JLabel("Dijous:"));
		linea4_6.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDijFi = new SpinnerNumberModel(0, 0, 23, 1);
		this.dijHorFi = new JSpinner(horesModelDijFi);
		JSpinner.DefaultEditor editorDijHFi = new JSpinner.DefaultEditor(this.dijHorFi);
		editorDijHFi.getTextField().setColumns(2);
		editorDijHFi.getTextField().setEditable(false);
		this.dijHorFi.setEditor(editorDijHFi);
		linea4_6.add(this.dijHorFi);
		linea4_6.add(new JLabel("h"));
		linea4_6.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDijFi = new SpinnerNumberModel(0, 0, 45, 15);
		this.dijMinFi = new JSpinner(minModelDijFi);
		JSpinner.DefaultEditor editorDijMFi = new JSpinner.DefaultEditor(this.dijMinFi);
		editorDijMFi.getTextField().setColumns(2);
		editorDijMFi.getTextField().setEditable(false);
		this.dijMinFi.setEditor(editorDijMFi);
		linea4_6.add(this.dijMinFi);
		linea4_6.add(new JLabel("min"));
		linea4_6.add(Box.createHorizontalStrut(10));

		linea4_6.add(new JLabel("Divendres:"));
		linea4_6.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDivFi = new SpinnerNumberModel(0, 0, 23, 1);
		this.divHorFi = new JSpinner(horesModelDivFi);
		JSpinner.DefaultEditor editorDivHFi = new JSpinner.DefaultEditor(this.divHorFi);
		editorDivHFi.getTextField().setColumns(2);
		editorDivHFi.getTextField().setEditable(false);
		this.divHorFi.setEditor(editorDivHFi);
		linea4_6.add(this.divHorFi);
		linea4_6.add(new JLabel("h"));
		linea4_6.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDivFi = new SpinnerNumberModel(0, 0, 45, 15);
		this.divMinFi = new JSpinner(minModelDivFi);
		JSpinner.DefaultEditor editorDivMFi = new JSpinner.DefaultEditor(this.divMinFi);
		editorDivMFi.getTextField().setColumns(2);
		editorDivMFi.getTextField().setEditable(false);
		this.divMinFi.setEditor(editorDivMFi);
		linea4_6.add(this.divMinFi);
		linea4_6.add(new JLabel("min"));
		linea4_6.add(Box.createHorizontalStrut(10));

		linea4_6.add(new JLabel("Dissabte:"));
		linea4_6.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel horesModelDissFi = new SpinnerNumberModel(0, 0, 23, 1);
		this.dissHorFi = new JSpinner(horesModelDissFi);
		JSpinner.DefaultEditor editorDissHFi = new JSpinner.DefaultEditor(this.dissHorFi);
		editorDissHFi.getTextField().setColumns(2);
		editorDissHFi.getTextField().setEditable(false);
		this.dissHorFi.setEditor(editorDissHFi);
		linea4_6.add(this.dissHorFi);
		linea4_6.add(new JLabel("h"));
		linea4_6.add(Box.createHorizontalStrut(5));
		SpinnerNumberModel minModelDissFi = new SpinnerNumberModel(0, 0, 45, 15);
		this.dissMinFi = new JSpinner(minModelDissFi);
		JSpinner.DefaultEditor editorDissMFi = new JSpinner.DefaultEditor(this.dissMinFi);
		editorDissMFi.getTextField().setColumns(2);
		editorDissMFi.getTextField().setEditable(false);
		this.dissMinFi.setEditor(editorDissMFi);
		linea4_6.add(this.dissMinFi);
		linea4_6.add(new JLabel("min"));
		linea4_6.add(Box.createHorizontalStrut(30));

		this.panelActivitatEsportiva.add(linea4_6);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));






		JPanel linea5 = new JPanel();
		linea5.setLayout(new BoxLayout(linea5, BoxLayout.X_AXIS));
		linea5.add(Box.createHorizontalStrut(20));
		linea5.add(new JLabel("Data d'inici de l'activitat"));
		linea5.add(Box.createHorizontalStrut(20));
		this.dataIniciEsportiva = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
		linea5.add(this.dataIniciEsportiva);
		linea5.add(Box.createHorizontalStrut(40));
		linea5.add(new JLabel("Data de fi de l'activitat"));
		linea5.add(Box.createHorizontalStrut(20));
		this.dataFiEsportiva = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
		linea5.add(this.dataFiEsportiva);
		linea5.add(Box.createHorizontalStrut(140));
		this.panelActivitatEsportiva.add(linea5);
		this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));

		//JPanel linea6 = new JPanel();
		//linea6.setLayout(new BoxLayout(linea6, BoxLayout.X_AXIS));
		//linea6.add(Box.createHorizontalStrut(20));
		//linea6.add(new JLabel("Hora d'inici de l'activitat:"));
		//linea6.add(Box.createHorizontalStrut(15));
		//SpinnerNumberModel horaIniciModel = new SpinnerNumberModel(0, 0, 23, 1);
		//this.horaIniciEsportiva = new JSpinner(horaIniciModel);
		//DefaultEditor editor2 = new JSpinner.DefaultEditor(horaIniciEsportiva);
		//editor2.getTextField().setColumns(3);
		//editor2.getTextField().setEditable(false);
		//this.horaIniciEsportiva.setEditor(editor2);
		//linea6.add(this.horaIniciEsportiva);
		//linea6.add(new JLabel("h"));
		//linea6.add(Box.createHorizontalStrut(10));
		//SpinnerNumberModel minIniciModel = new SpinnerNumberModel(0, 0, 59, 1);
		//this.minIniciEsportiva = new JSpinner(minIniciModel);
		//DefaultEditor editor3 = new JSpinner.DefaultEditor(minIniciEsportiva);
		//editor3.getTextField().setColumns(3);
		//editor3.getTextField().setEditable(false);
		//this.minIniciEsportiva.setEditor(editor3);
		//linea6.add(this.minIniciEsportiva);
		//linea6.add(new JLabel("min"));
		//linea6.add(Box.createHorizontalStrut(20));

		//linea6.add(new JLabel("Hora de fi de l'activitat:"));
		//linea6.add(Box.createHorizontalStrut(15));
		//SpinnerNumberModel horaFiModel = new SpinnerNumberModel(0, 0, 23, 1);
		//this.horaFiEsportiva = new JSpinner(horaFiModel);
		//DefaultEditor editor4 = new JSpinner.DefaultEditor(horaFiEsportiva);
		//editor4.getTextField().setColumns(3);
		//editor4.getTextField().setEditable(false);
		//this.horaFiEsportiva.setEditor(editor4);
		//linea6.add(this.horaFiEsportiva);
		//linea6.add(new JLabel("h"));
		//linea6.add(Box.createHorizontalStrut(10));
		//SpinnerNumberModel minFiModel = new SpinnerNumberModel(0, 0, 59, 1);
		//this.minFiEsportiva = new JSpinner(minFiModel);
		//DefaultEditor editor5 = new JSpinner.DefaultEditor(minFiEsportiva);
		//editor5.getTextField().setColumns(3);
		//editor5.getTextField().setEditable(false);
		//this.minFiEsportiva.setEditor(editor5);
		//linea6.add(this.minFiEsportiva);
		//linea6.add(new JLabel("min"));
		//linea6.add(Box.createHorizontalStrut(20));
		//this.panelActivitatEsportiva.add(linea6);
		//this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));

		JPanel linea7 = new JPanel();
		linea7.setLayout(new BoxLayout(linea7, BoxLayout.X_AXIS));
		linea7.add(Box.createHorizontalStrut(20));
		this.botoClearAE = new JButton("Borrar Informació");
		linea7.add(this.botoClearAE);
		linea7.add(Box.createHorizontalStrut(40));
		this.botoInserirNovaActivitatE = new JButton("Inserir Activitat");
		linea7.add(this.botoInserirNovaActivitatE);
		this.panelActivitatEsportiva.add(linea7);

		this.panelActivitatEsportiva.add(Box.createVerticalStrut(15));
		this.botoTornarAE = new JButton("Tornar Inici");
		this.panelActivitatEsportiva.add(this.botoTornarAE);

		this.panelActivitatEsportiva.add(Box.createVerticalStrut(30));
		return (this.panelActivitatEsportiva);
	}

	private JPanel crearPanelActivitatCultural()
	{
		this.panelActivitatCultural = new JPanel();
		this.panelActivitatCultural.setLayout(new BoxLayout(this.panelActivitatCultural, BoxLayout.Y_AXIS));
		this.panelActivitatCultural.setBorder(BorderFactory.createTitledBorder("Creació activitat cultural"));
		this.panelActivitatCultural.add(Box.createVerticalStrut(20));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Nom de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.nomActivitatC = new JTextField("Nom");
		linea1.add(this.nomActivitatC);
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Descripció de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.descripcioActivitatC = new JTextField("Descripcio");
		linea1.add(this.descripcioActivitatC);
		linea1.add(Box.createHorizontalStrut(20));
		this.panelActivitatCultural.add(linea1);
		this.panelActivitatCultural.add(Box.createVerticalStrut(20));

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Numero Màxim de Participants"));
		linea2.add(Box.createHorizontalStrut(10));
		SpinnerNumberModel participantsModel = new SpinnerNumberModel(0, 0, 75, 1);
		
		this.numMaximParticipantsC = new JSpinner(participantsModel);
		DefaultEditor editor1 = new JSpinner.DefaultEditor(numMaximParticipantsC);
		editor1.getTextField().setColumns(3);
		editor1.getTextField().setEditable(false);
		this.numMaximParticipantsC.setEditor(editor1);
		linea2.add(this.numMaximParticipantsC);

		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Adreça on es realitza l'activitat"));
		linea2.add(Box.createHorizontalStrut(10));
		this.adresaActivitatC = new JTextField("Adreça");
		linea2.add(this.adresaActivitatC);
		linea2.add(Box.createHorizontalStrut(20));
		this.panelActivitatCultural.add(linea2);
		this.panelActivitatCultural.add(Box.createVerticalStrut(20));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Població on es realitzarà l'activitat"));
		linea3.add(Box.createHorizontalStrut(15));
		this.poblacioActivitatC = new JTextField("Població");
		linea3.add(this.poblacioActivitatC);
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Preu de l'activitat"));
		linea3.add(Box.createHorizontalStrut(15));
		SpinnerNumberModel preuModel = new SpinnerNumberModel(0, 0, 100, 0.5);
		this.preuActivitatC = new JSpinner(preuModel);
		DefaultEditor editor2 = new JSpinner.DefaultEditor(preuActivitatC);
		editor2.getTextField().setColumns(3);
		editor2.getTextField().setEditable(false);
		this.preuActivitatC.setEditor(editor2);
		linea3.add(this.preuActivitatC);
		linea3.add(Box.createHorizontalStrut(60));
		this.panelActivitatCultural.add(linea3);
		this.panelActivitatCultural.add(Box.createVerticalStrut(20));

		JPanel linea4 = new JPanel();
		linea4.setLayout(new BoxLayout(linea4, BoxLayout.X_AXIS));
		linea4.add(Box.createHorizontalStrut(20));
		linea4.add(new JLabel("Dia de l'activitat"));
		linea4.add(Box.createHorizontalStrut(15));
		this.dataActivitatCultural = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
		linea4.add(this.dataActivitatCultural);
		linea4.add(Box.createHorizontalStrut(60));
		linea4.add(new JLabel("Hora d'inici de l'activitat:"));
		linea4.add(Box.createHorizontalStrut(15));
		SpinnerNumberModel horaModel = new SpinnerNumberModel(0, 0, 23, 1);
		this.horaIniciACultural = new JSpinner(horaModel);
		DefaultEditor editor3 = new JSpinner.DefaultEditor(horaIniciACultural);
		editor3.getTextField().setEditable(false);
		editor3.getTextField().setColumns(3);
		this.horaIniciACultural.setEditor(editor3);
		linea4.add(this.horaIniciACultural);
		linea4.add(new JLabel("h"));
		linea4.add(Box.createHorizontalStrut(15));
		SpinnerNumberModel minModel = new SpinnerNumberModel(0, 0, 59, 1);
		this.minIniciACultural = new JSpinner(minModel);
		DefaultEditor editor4 = new JSpinner.DefaultEditor(minIniciACultural);
		editor4.getTextField().setEditable(false);
		editor4.getTextField().setColumns(3);
		this.minIniciACultural.setEditor(editor4);
		linea4.add(this.minIniciACultural);
		linea4.add(new JLabel("min"));
		linea4.add(Box.createHorizontalStrut(55));
		this.panelActivitatCultural.add(linea4);
		this.panelActivitatCultural.add(Box.createVerticalStrut(20));

		JPanel linea5 = new JPanel();
		linea5.setLayout(new BoxLayout(linea5, BoxLayout.X_AXIS));
		linea5.add(Box.createHorizontalStrut(20));
		this.botoClearAC = new JButton("Borrar Informació");
		linea5.add(this.botoClearAC);
		linea5.add(Box.createHorizontalStrut(40));
		this.botoInserirNovaActivitatC = new JButton("Inserir Activitat");
		linea5.add(this.botoInserirNovaActivitatC);
		this.panelActivitatCultural.add(linea5);

		this.panelActivitatCultural.add(Box.createVerticalStrut(60));
		this.botoTornarAC = new JButton("Tornar Inici");
		this.panelActivitatCultural.add(this.botoTornarAC);

		this.panelActivitatCultural.add(Box.createVerticalStrut(330));
		return (this.panelActivitatCultural);
	}

	private JPanel crearPanelActivitatFormativa()
	{
		this.panelActivitatFormativa = new JPanel();

		this.panelActivitatFormativa.setLayout(new BoxLayout(this.panelActivitatFormativa, BoxLayout.Y_AXIS));
		this.panelActivitatFormativa.setBorder(BorderFactory.createTitledBorder("Creació activitat formativa"));
		this.panelActivitatFormativa.add(Box.createVerticalStrut(20));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Nom de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.nomActivitatF = new JTextField("Nom");
		linea1.add(this.nomActivitatF);
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Descripció de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.descripcioActivitatF = new JTextField("Descripció");
		linea1.add(this.descripcioActivitatF);
		linea1.add(Box.createHorizontalStrut(20));
		this.panelActivitatFormativa.add(linea1);
		this.panelActivitatFormativa.add(Box.createVerticalStrut(20));			

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Número Màxim de Participants"));
		linea2.add(Box.createHorizontalStrut(10));
		SpinnerNumberModel participantsModel = new SpinnerNumberModel(0, 0, 75, 1);
		this.numMaximParticipantsF = new JSpinner(participantsModel);
		DefaultEditor editor1 = new JSpinner.DefaultEditor(numMaximParticipantsF);
		editor1.getTextField().setColumns(3);
		editor1.getTextField().setEditable(false);
		this.numMaximParticipantsF.setEditor(editor1);
		linea2.add(this.numMaximParticipantsF);
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Adreça on es realitza l'activitat"));
		linea2.add(Box.createHorizontalStrut(10));
		this.adresaActivitatF = new JTextField("Adreça");
		linea2.add(this.adresaActivitatF);
		linea2.add(Box.createHorizontalStrut(20));
		this.panelActivitatFormativa.add(linea2);
		this.panelActivitatFormativa.add(Box.createVerticalStrut(20));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Població on es realitzarà l'activitat"));
		linea3.add(Box.createHorizontalStrut(10));
		this.poblacioActivitatF = new JTextField("Població");
		linea3.add(this.poblacioActivitatF);
		linea3.add(Box.createHorizontalStrut(15));
		linea3.add(new JLabel("Duració de l'activitat (h)"));
		linea3.add(Box.createHorizontalStrut(15));

		SpinnerNumberModel duracio = new SpinnerNumberModel(0, 0, 15, 0.5);
		this.duracioActivitatF = new JSpinner(duracio);
		DefaultEditor editor = new JSpinner.DefaultEditor(duracioActivitatF);
		editor.getTextField().setColumns(3);
		editor.getTextField().setEditable(false);
		this.duracioActivitatF.setEditor(editor);
		linea3.add(this.duracioActivitatF);

		linea3.add(Box.createHorizontalStrut(15));
		linea3.add(new JLabel("Hora d'inici"));
		linea3.add(Box.createHorizontalStrut(10));

		SpinnerNumberModel horaModel = new SpinnerNumberModel(0, 0, 23, 1);
		this.horaIniciActivitatF = new JSpinner(horaModel);
		DefaultEditor editor2 = new JSpinner.DefaultEditor(horaIniciActivitatF);
		editor2.getTextField().setColumns(3);
		editor2.getTextField().setEditable(false);
		this.horaIniciActivitatF.setEditor(editor2);
		linea3.add(this.horaIniciActivitatF);
		linea3.add(Box.createHorizontalStrut(20));
		this.panelActivitatFormativa.add(linea3);
		this.panelActivitatFormativa.add(Box.createVerticalStrut(20));

		JPanel linea4 = new JPanel();
		linea4.setLayout((new BoxLayout(linea4, BoxLayout.X_AXIS)));
		linea4.add(Box.createHorizontalStrut(20));
		linea4.add(new JLabel("Data límit d'inscripció"));
		linea4.add(Box.createHorizontalStrut(15));
		this.dataLimitInscripcioF = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
		linea4.add(this.dataLimitInscripcioF);
		linea4.add(Box.createHorizontalStrut(50));
		linea4.add(new JLabel("Data de l'Activitat"));
		linea4.add(Box.createHorizontalStrut(15));
		this.dataActivitatFormativa = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
		linea4.add(this.dataActivitatFormativa);
		linea4.add(Box.createHorizontalStrut(90));
		this.panelActivitatFormativa.add(linea4);
		this.panelActivitatFormativa.add(Box.createVerticalStrut(20));

		JPanel linea5 = new JPanel();
		linea5.setLayout(new BoxLayout(linea5, BoxLayout.X_AXIS));
		linea5.add(Box.createHorizontalStrut(20));
		this.botoClearAF = new JButton("Borrar Informació");
		linea5.add(this.botoClearAF);
		linea5.add(Box.createHorizontalStrut(40));
		this.botoInserirNovaActivitatF = new JButton("Inserir Activitat");
		linea5.add(this.botoInserirNovaActivitatF);
		this.panelActivitatFormativa.add(linea5);

		this.panelActivitatFormativa.add(Box.createVerticalStrut(60));
		this.botoTornarAF = new JButton("Tornar Inici");
		this.panelActivitatFormativa.add(this.botoTornarAF);

		this.panelActivitatFormativa.add(Box.createVerticalStrut(300));
		return (this.panelActivitatFormativa);
	}

	private JPanel crearPanelLlistarActivitats()
	{
		this.panelLlistaActivitats = new JPanel(new CardLayout());

		this.panelLlistaActivitats.add(crearPanelOpcions(), "Opcions");
		this.panelLlistaActivitats.add(crearPanelConsultarActivitat(), "ConsultarActivitat");
		//addOyentesItemsPanelLlistarActivitats();
		return (this.panelLlistaActivitats);
	}

	public void showCardPanelLlistarActivitats(String card)
	{
		((CardLayout) this.panelLlistaActivitats.getLayout()).show(panelLlistaActivitats, card);
	}
	
	public void	addListenerConsultarActivitats(ActionListener l)
	{
		this.consultarLlistaActivitats.addActionListener(l);
		this.tornarDeConsultaAOpcions.addActionListener(l);
	}

	public void clearFiltres()
	{
		poblacioFiltre.setText("");
		admetInscripcions.setSelectedItem("Indiferent");
		tipusActivitatFiltre.setSelectedItem("Indiferent");
	}

	private JPanel crearPanelOpcions()
	{
		this.panelOpcions = new JPanel();
		this.panelOpcions.setLayout(new BoxLayout(this.panelOpcions, BoxLayout.Y_AXIS));
		this.panelOpcions.add(Box.createVerticalStrut(20));

		JPanel linea0 = new JPanel();
		linea0.setLayout(new BoxLayout(linea0, BoxLayout.X_AXIS));
		String[] opcions = {"Indiferent", "Si", "No"};
		String[] tipus = {"Indiferent", "Esportiva", "Cultural", "Formació"};
		linea0.add(Box.createHorizontalStrut(20));
		linea0.add(new JLabel("Admet inscripcions"));
		linea0.add(Box.createHorizontalStrut(5));
		this.admetInscripcions = new JComboBox<String>(opcions);
		linea0.add(this.admetInscripcions);
		linea0.add(Box.createHorizontalStrut(10));
		linea0.add(new JLabel("Tipus d'activitat"));
		linea0.add(Box.createHorizontalStrut(5));
		this.tipusActivitatFiltre = new JComboBox<String>(tipus);
		linea0.add(this.tipusActivitatFiltre);
		linea0.add(Box.createHorizontalStrut(10));
		linea0.add(new JLabel("Població"));
		linea0.add(Box.createHorizontalStrut(5));
		this.poblacioFiltre = new JTextField();
		linea0.add(this.poblacioFiltre);
		linea0.add(Box.createHorizontalStrut(10));
		this.aplicar = new JButton("Aplicar Filtres");
		linea0.add(this.aplicar);
		linea0.add(Box.createHorizontalStrut(10));
		this.panelOpcions.add(linea0);
		this.panelOpcions.add(Box.createVerticalStrut(20));


		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Llista de les activitats actuals del centre:"));
		linea1.add(Box.createHorizontalStrut(20));
		this.llistaActivitats = new JTextArea("Llista d'activitats", 10, 30);
		this.llistaActivitats.setFont(new Font("Arial", Font.PLAIN, 12));
		this.llistaActivitats.setWrapStyleWord(true);
		this.llistaActivitats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.llistaActivitats.setEditable(false);
		JScrollPane s = new JScrollPane(this.llistaActivitats);
		s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		linea1.add(s);
		linea1.add(Box.createHorizontalStrut(20));
		this.panelOpcions.add(linea1);
		this.panelOpcions.add(Box.createVerticalStrut(20));

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Activitat escollida a tractar: (Nom)"));
		linea2.add(Box.createHorizontalStrut(15));
		this.activitatAModificar = new JTextField("");
		linea2.add(this.activitatAModificar);
		linea2.add(Box.createHorizontalStrut(187));
		this.panelOpcions.add(linea2);
		this.panelOpcions.add(Box.createVerticalStrut(20));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(50));
		this.consultarActivitat = new JButton("Consultar Activitat");
		linea3.add(this.consultarActivitat);
		linea3.add(Box.createHorizontalStrut(30));
		this.eliminarActivitat = new JButton("Eliminar Activitat");
		linea3.add(this.eliminarActivitat);
		linea3.add(Box.createHorizontalStrut(50));
		this.panelOpcions.add(linea3);
		this.panelOpcions.add(Box.createVerticalStrut(20));

		this.panelOpcions.add(Box.createVerticalStrut(250));
		return (this.panelOpcions);
	}

	private JPanel crearPanelConsultarActivitat()
	{
		this.panelConsultarActivitat = new JPanel();
		this.panelConsultarActivitat.setLayout(new BoxLayout(this.panelConsultarActivitat, BoxLayout.Y_AXIS));

		this.panelConsultaInfoComuna = new JPanel();
		this.panelConsultaInfoComuna.setLayout(new BoxLayout(this.panelConsultaInfoComuna, BoxLayout.Y_AXIS));
		this.panelConsultaInfoComuna.add(Box.createVerticalStrut(20));
		
		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Nom de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.consultaNom = new JTextField("");
		this.consultaNom.setEditable(false);
		linea1.add(this.consultaNom);
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Descripció de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.consultaDescripcio = new JTextField("");
		this.consultaDescripcio.setEditable(false);
		linea1.add(this.consultaDescripcio);
		linea1.add(Box.createHorizontalStrut(20));
		this.panelConsultaInfoComuna.add(linea1);
		this.panelConsultaInfoComuna.add(Box.createVerticalStrut(20));

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Número Màxim de Participants"));
		linea2.add(Box.createHorizontalStrut(10));
		this.consultaNumMaxParticipants = new JTextField("");
		this.consultaNumMaxParticipants.setEditable(false);
		linea2.add(this.consultaNumMaxParticipants);
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Adreça on es realitza l'activitat"));
		linea2.add(Box.createHorizontalStrut(10));
		this.consultaAdresa = new JTextField("");
		this.consultaAdresa.setEditable(false);
		linea2.add(this.consultaAdresa);
		linea2.add(Box.createHorizontalStrut(20));
		this.panelConsultaInfoComuna.add(linea2);
		this.panelConsultaInfoComuna.add(Box.createVerticalStrut(20));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Població on es realitzarà l'activitat"));
		linea3.add(Box.createHorizontalStrut(15));
		this.consultaPoblacio = new JTextField("");
		this.consultaPoblacio.setEditable(false);
		linea3.add(this.consultaPoblacio);
		linea3.add(Box.createHorizontalStrut(300));
		this.panelConsultaInfoComuna.add(linea3);
		this.panelConsultaInfoComuna.add(Box.createVerticalStrut(10));

		this.panelConsultarActivitat.add(this.panelConsultaInfoComuna);
		this.panelConsultarActivitat.add(Box.createVerticalStrut(10));
		this.panelConsultarActivitat.add(crearPanelParticularConsulta());
		this.panelConsultarActivitat.add(Box.createVerticalStrut(5));
		this.panelConsultarActivitat.add(crearPanelGestionarActivitat());
		this.panelConsultarActivitat.add(Box.createVerticalStrut(10));

		JPanel panelSut = new JPanel();
		panelSut.setLayout(new BoxLayout(panelSut, BoxLayout.Y_AXIS));
		panelSut.add(Box.createVerticalStrut(10));
		JPanel lineaBoto = new JPanel();
		lineaBoto.setLayout(new BoxLayout(lineaBoto, BoxLayout.Y_AXIS));
		lineaBoto.add(Box.createHorizontalStrut(170));
		this.tornarDeConsultaAOpcions = new JButton("Tornar Enrere");
		lineaBoto.add(this.tornarDeConsultaAOpcions);
		panelSut.add(lineaBoto);
		panelSut.add(Box.createVerticalStrut(20));
		this.panelConsultarActivitat.add(panelSut, BorderLayout.SOUTH);

		return (this.panelConsultarActivitat);
	}

	private JPanel crearPanelParticularConsulta()
	{
		this.panelConsultaInfoParticular = new JPanel(new CardLayout());
		//canviara de panell segons el tipus d'activitat escollida
		this.panelConsultaInfoParticular.add(crearPanelConsultaACultural(), "PanellConsultaCultural");
		this.panelConsultaInfoParticular.add(crearPanelConsultaEsportiva(), "PanellConsultaEsportiva");
		this.panelConsultaInfoParticular.add(crearPanelConsultaAFormativa(), "PanellConsultaFormativa");
		return (this.panelConsultaInfoParticular);
	}

	protected void panelConsultaInfoParticular(String card)
	{
		((CardLayout) this.panelConsultaInfoParticular.getLayout()).show(this.panelConsultaInfoParticular, card);
	}

	private JPanel crearPanelConsultaEsportiva()
	{
		this.panelConsultaAEsportiva = new JPanel();
		this.panelConsultaAEsportiva.setLayout(new BoxLayout(this.panelConsultaAEsportiva, BoxLayout.Y_AXIS));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Dies de la setmana que es realitza:"));
		linea1.add(Box.createHorizontalStrut(20));
		this.consultaDiesAE = new JTextField("");
		this.consultaDiesAE.setEditable(false);
		linea1.add(this.consultaDiesAE);
		linea1.add(Box.createHorizontalStrut(40));
		panelConsultaAEsportiva.add(linea1);
		this.panelConsultaAEsportiva.add(Box.createVerticalStrut(20));

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Dilluns:   de "));
		this.conHIDill = new JTextField("XX:XX");
		this.conHIDill.setEditable(false);
		linea2.add(this.conHIDill);
		linea2.add(new JLabel(" a "));
		this.conHFDill = new JTextField("XX:XX");
		this.conHFDill.setEditable(false);
		linea2.add(this.conHFDill);
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Dimarts:   de "));
		this.conHIDima = new JTextField("XX:XX");
		this.conHIDima.setEditable(false);
		linea2.add(this.conHIDima);
		linea2.add(new JLabel(" a "));
		this.conHFDima = new JTextField("XX:XX");
		this.conHFDima.setEditable(false);
		linea2.add(this.conHFDima);
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Dimecres:   de "));
		this.conHIDime = new JTextField("XX:XX");
		this.conHIDime.setEditable(false);
		linea2.add(this.conHIDime);
		linea2.add(new JLabel(" a "));
		this.conHFDime = new JTextField("XX:XX");
		this.conHFDime.setEditable(false);
		linea2.add(this.conHFDime);
		linea2.add(Box.createHorizontalStrut(20));
		panelConsultaAEsportiva.add(linea2);
		this.panelConsultaAEsportiva.add(Box.createVerticalStrut(20));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Dijous:   de "));
		this.conHIDij = new JTextField("XX:XX");
		this.conHIDij.setEditable(false);
		linea3.add(this.conHIDij);
		linea3.add(new JLabel(" a "));
		this.conHFDij = new JTextField("XX:XX");
		this.conHFDij.setEditable(false);
		linea3.add(this.conHFDij);
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Divendres:   de "));
		this.conHIDiv = new JTextField("XX:XX");
		this.conHIDiv.setEditable(false);
		linea3.add(this.conHIDiv);
		linea3.add(new JLabel(" a "));
		this.conHFDiv = new JTextField("XX:XX");
		this.conHFDiv.setEditable(false);
		linea3.add(this.conHFDiv);
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Dissabte:   de "));
		this.conHIDis = new JTextField("XX:XX");
		this.conHIDis.setEditable(false);
		linea3.add(this.conHIDis);
		linea3.add(new JLabel(" a "));
		this.conHFDis = new JTextField("XX:XX");
		this.conHFDis.setEditable(false);
		linea3.add(this.conHFDis);
		linea3.add(Box.createHorizontalStrut(20));
		panelConsultaAEsportiva.add(linea3);
		this.panelConsultaAEsportiva.add(Box.createVerticalStrut(20));

		JPanel linea4 = new JPanel();
		linea4.setLayout(new BoxLayout(linea4, BoxLayout.X_AXIS));
		linea4.add(Box.createHorizontalStrut(20));
		linea4.add(new JLabel("Data d'inici:"));
		linea4.add(Box.createHorizontalStrut(10));
		this.consultaDataIniciAE = new JTextField("__/__/__");
		this.consultaDataIniciAE.setEditable(false);
		linea4.add(this.consultaDataIniciAE);
		linea4.add(Box.createHorizontalStrut(20));
		linea4.add(new JLabel("Data de fi:"));
		linea4.add(Box.createHorizontalStrut(10));
		this.consultaDataFiAE = new JTextField("__/__/__");
		this.consultaDataFiAE.setEditable(false);
		linea4.add(this.consultaDataFiAE);
		linea4.add(Box.createHorizontalStrut(20));
		linea4.add(new JLabel("Hora d'inici:"));
		linea4.add(Box.createHorizontalStrut(10));
		panelConsultaAEsportiva.add(linea4);
		
		this.panelConsultaAEsportiva.add(Box.createVerticalStrut(10));
		return (this.panelConsultaAEsportiva);
	}

	private JPanel crearPanelConsultaACultural()
	{
		this.panelConsultaACultural = new JPanel();
		this.panelConsultaACultural.setLayout(new BoxLayout(this.panelConsultaACultural, BoxLayout.Y_AXIS));
		this.panelConsultaACultural.add(Box.createVerticalStrut(20));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Preu de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(15));
		this.consultarPreuAC = new JTextField("€");
		this.consultarPreuAC.setEditable(false);
		linea1.add(this.consultarPreuAC);
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Data de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(15));
		this.consultarDataAC = new JTextField("__/__/__");
		this.consultarDataAC.setEditable(false);
		linea1.add(this.consultarDataAC);
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Hora d'inici de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(15));
		this.consultarHoraAC = new JTextField("XX:XX");
		this.consultarHoraAC.setEditable(false);
		linea1.add(this.consultarHoraAC);
		linea1.add(Box.createHorizontalStrut(90));
		this.panelConsultaACultural.add(linea1);

		this.panelConsultaACultural.add(Box.createVerticalStrut(40));
		return (this.panelConsultaACultural);
	}

	private JPanel crearPanelConsultaAFormativa()
	{
		this.panelConsultaAFormativa = new JPanel();
		this.panelConsultaAFormativa.setLayout(new BoxLayout(this.panelConsultaAFormativa, BoxLayout.Y_AXIS));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Data de l'activitat:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.consultarDataAF = new JTextField("__/__/__");
		this.consultarDataAF.setEditable(false);
		linea1.add(this.consultarDataAF);
		linea1.add(Box.createHorizontalStrut(15));
		linea1.add(new JLabel("Hora:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.consultarHoraAF = new JTextField("");
		this.consultarHoraAF.setEditable(false);
		linea1.add(this.consultarHoraAF);
		linea1.add(Box.createHorizontalStrut(15));
		linea1.add(new JLabel("Data limit d'inscripció:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.consultarDataLimitAF = new JTextField("__/__/__");
		this.consultarDataLimitAF.setEditable(false);
		linea1.add(this.consultarDataLimitAF);
		linea1.add(Box.createHorizontalStrut(15));
		linea1.add(new JLabel("Duració aproximada:"));
		linea1.add(Box.createHorizontalStrut(10));
		this.consultarDuracioAF = new JTextField("");
		this.consultarDuracioAF.setEditable(false);
		linea1.add(this.consultarDuracioAF);
		linea1.add(Box.createHorizontalStrut(15));
		this.panelConsultaAFormativa.add(linea1);

		this.panelConsultaAFormativa.add(Box.createVerticalStrut(50));
		return (this.panelConsultaAFormativa);
	}

	private JPanel crearPanelGestionarActivitat()
	{
		this.panelGestionarActivitat = new JPanel();
		this.panelGestionarActivitat.setLayout(new BoxLayout(this.panelGestionarActivitat, BoxLayout.Y_AXIS));

		JPanel linea1 = new JPanel();
		linea1.setLayout(new BoxLayout(linea1, BoxLayout.X_AXIS));
		linea1.add(Box.createHorizontalStrut(20));
		linea1.add(new JLabel("Llista d'inscrits a l'activitat:"));
		linea1.add(Box.createHorizontalStrut(20));
		this.llistaInscripcions = new JTextArea("DNI, telefon", 20, 25);
		this.llistaInscripcions.setFont(new Font("Arial", Font.PLAIN, 12));
		this.llistaInscripcions.setWrapStyleWord(true);
		this.llistaInscripcions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.llistaInscripcions.setEditable(false);
		JScrollPane s = new JScrollPane(this.llistaInscripcions);
		s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		linea1.add(s);
		linea1.add(Box.createHorizontalStrut(200));
		this.panelGestionarActivitat.add(linea1);
		this.panelGestionarActivitat.add(Box.createVerticalStrut(15));

		JPanel linea2 = new JPanel();
		linea2.setLayout(new BoxLayout(linea2, BoxLayout.X_AXIS));
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Inscriure Participant:"));
		linea2.add(Box.createHorizontalStrut(10));
		linea2.add(new JLabel("DNI"));
		linea2.add(Box.createHorizontalStrut(10));
		this.gestionaDNI = new JTextField(10);
		linea2.add(this.gestionaDNI);
		linea2.add(Box.createHorizontalStrut(20));
		linea2.add(new JLabel("Telèfon"));
		linea2.add(Box.createHorizontalStrut(10));
		this.gestionaTelefon = new JTextField(10);
		linea2.add(this.gestionaTelefon);
		linea2.add(Box.createHorizontalStrut(20));
		this.inscriureBoto = new JButton("Inscriure Participant");
		linea2.add(this.inscriureBoto);
		linea2.add(Box.createHorizontalStrut(20));
		this.panelGestionarActivitat.add(linea2);
		this.panelGestionarActivitat.add(Box.createVerticalStrut(20));

		JPanel linea3 = new JPanel();
		linea3.setLayout(new BoxLayout(linea3, BoxLayout.X_AXIS));
		linea3.add(Box.createHorizontalStrut(20));
		linea3.add(new JLabel("Cancelar Inscripció:"));
		linea3.add(Box.createHorizontalStrut(10));
		linea3.add(new JLabel("DNI a cancel·lar"));
		linea3.add(Box.createHorizontalStrut(10));
		this.DNIACancelar =new JTextField(10);
		linea3.add(this.DNIACancelar);
		linea3.add(Box.createHorizontalStrut(20));
		this.cancelarInsBoto = new JButton("Cancel·lar Inscripció");
		linea3.add(this.cancelarInsBoto);
		linea3.add(Box.createHorizontalStrut(20));
		this.panelGestionarActivitat.add(linea3);
		this.panelGestionarActivitat.add(Box.createVerticalStrut(10));

		return (this.panelGestionarActivitat);
	}

	public void addListenerCrearActivitatEsportiva(ActionListener l)
	{
		this.botoInserirNovaActivitatE.addActionListener(l);
	}

	public void addListenerCrearActivitatCultural(ActionListener l)
	{
		this.botoInserirNovaActivitatC.addActionListener(l);
	}
	
	public void addListenerCrearActivitatFormativa(ActionListener l)
	{
		this.botoInserirNovaActivitatF.addActionListener(l);
	}

	public void addListenerConsultarActivitat(ActionListener l)
	{
		this.consultarActivitat.addActionListener(l);
	}

	public void addListenerEliminarActivitat(ActionListener l)
	{
		this.eliminarActivitat.addActionListener(l);
	}

	public void addListenerAplicarFiltresLlistaActivitats(ActionListener l)
	{
		this.aplicar.addActionListener(l);
	}

	public void addListenerInscriureParticipant(ActionListener l)
	{
		this.inscriureBoto.addActionListener(l);
	}

	public void addListenerCancelarInscripcio(ActionListener l)
	{
		this.cancelarInsBoto.addActionListener(l);
	}

	public void mostrarLlistaActivitats(Iterable<Activitat> llista)
	{
		String	s = "Activitats:\n";

		if (!llista.iterator().hasNext())
			this.llistaActivitats.setText("No hi ha cap activitat");
		else
		{
			for (Activitat a : llista)
			{
				s += "-" + a + "\n";
			}
			this.llistaActivitats.setText(s);
		}
		this.llistaActivitats.setCaretPosition(0);
		showCardPanelPrincipal("PanelLlistaActivitats");
		showCardPanelLlistarActivitats("Opcions");
	}

	public void mostrarLlistaParticipants(Iterable<Persona> llista)
	{
		String s = "Inscrits (DNI, telèfon):\n";

		if (!llista.iterator().hasNext())
			this.llistaInscripcions.setText("No hi ha inscrits");
		else
		{
			for (Persona p : llista)
			{
				s += p.toString() + "\n";
			}
			this.llistaInscripcions.setText(s);
		}
	}

	//tipus: 1 esportiva, 2 cultural, 3 formativa
	public void mostrarInformacioActivitat(Activitat a)
	{
		int tipus  = a.tipusActivitat();
		
		consultaNom.setText(a.getNom());
		consultaDescripcio.setText(a.getDescripcio());
		consultaNumMaxParticipants.setText(String.valueOf(a.getMaximParticipants()));
		consultaAdresa.setText(a.getAdresa());
		consultaPoblacio.setText(a.getPoblacio());
		if (tipus == 1)
		{
			mostrarActivitatEsportiva((ActivitatEsportiva)a);
			panelConsultaInfoParticular("PanellConsultaEsportiva");
		}
		else if (tipus == 2)
		{
			mostrarActivitatCultural((ActivitatCultural)a);
			panelConsultaInfoParticular("PanellConsultaCultural");
		}
		else if (tipus == 3)
		{
			mostrarActivitatFormativa((ActivitatFormativa)a);
			panelConsultaInfoParticular("PanellConsultaFormativa");
		}
		showCardPanelLlistarActivitats("ConsultarActivitat");
	}

	private void mostraHoresEsportiva(ActivitatEsportiva a)
	{
		String		dies[] = a.getDies();
		LocalTime	hIni[] = a.getHoresInici();
		LocalTime	hFi[] = a.getHoresFi();
		String		h;

		for (int i = 0; i < dies.length; i++)
		{
			if (dies[i].equals("Dilluns"))
			{
				if (hIni[i].getMinute() == 0)
					h = hIni[i].getHour() + ":00";
				else if (hIni[i].getMinute() < 10)
					h = hIni[i].getHour() + ":0" + hIni[i].getMinute();
				else
					h = hIni[i].getHour() + ":" + hIni[i].getMinute();
				if (hIni[i].getHour() < 10)
					h = "0" + h;
				conHIDill.setText(h);
				if (hFi[i].getMinute() == 0)
					h = hFi[i].getHour() + ":00";
				else if (hFi[i].getMinute() < 10)
					h = hFi[i].getHour() + ":0" + hFi[i].getMinute();
				else
					h = hFi[i].getHour() + ":" + hFi[i].getMinute();
				if (hFi[i].getHour() < 10)
					h = "0" + h;
				conHFDill.setText(h);
			}
			else if (dies[i].equals("Dimarts"))
			{
				if (hIni[i].getMinute() == 0)
					h = hIni[i].getHour() + ":00";
				else if (hIni[i].getMinute() < 10)
					h = hIni[i].getHour() + ":0" + hIni[i].getMinute();
				else
					h = hIni[i].getHour() + ":" + hIni[i].getMinute();
				if (hIni[i].getHour() < 10)
					h = "0" + h;
				conHIDima.setText(h);
				if (hFi[i].getMinute() == 0)
					h = hFi[i].getHour() + ":00";
				else if (hFi[i].getMinute() < 10)
					h = hFi[i].getHour() + ":0" + hFi[i].getMinute();
				else
					h = hFi[i].getHour() + ":" + hFi[i].getMinute();
				if (hFi[i].getHour() < 10)
					h = "0" + h;
				conHFDima.setText(h);
			}
			else if (dies[i].equals("Dimecres"))
			{
				if (hIni[i].getMinute() == 0)
					h = hIni[i].getHour() + ":00";
				else if (hIni[i].getMinute() < 10)
					h = hIni[i].getHour() + ":0" + hIni[i].getMinute();
				else
					h = hIni[i].getHour() + ":" + hIni[i].getMinute();
				if (hIni[i].getHour() < 10)
					h = "0" + h;
				conHIDime.setText(h);
				if (hFi[i].getMinute() == 0)
					h = hFi[i].getHour() + ":00";
				else if (hFi[i].getMinute() < 10)
					h = hFi[i].getHour() + ":0" + hFi[i].getMinute();
				else
					h = hFi[i].getHour() + ":" + hFi[i].getMinute();
				if (hFi[i].getHour() < 10)
					h = "0" + h;
				conHFDime.setText(h);
			}
			else if (dies[i].equals("Dijous"))
			{
				if (hIni[i].getMinute() == 0)
					h = hIni[i].getHour() + ":00";
				else if (hIni[i].getMinute() < 10)
					h = hIni[i].getHour() + ":0" + hIni[i].getMinute();
				else
					h = hIni[i].getHour() + ":" + hIni[i].getMinute();
				if (hIni[i].getHour() < 10)
					h = "0" + h;
				conHIDij.setText(h);
				if (hFi[i].getMinute() == 0)
					h = hFi[i].getHour() + ":00";
				else if (hFi[i].getMinute() < 10)
					h = hFi[i].getHour() + ":0" + hFi[i].getMinute();
				else
					h = hFi[i].getHour() + ":" + hFi[i].getMinute();
				if (hFi[i].getHour() < 10)
					h = "0" + h;
				conHFDij.setText(h);
			}
			else if (dies[i].equals("Divendres"))
			{
				if (hIni[i].getMinute() == 0)
					h = hIni[i].getHour() + ":00";
				else if (hIni[i].getMinute() < 10)
					h = hIni[i].getHour() + ":0" + hIni[i].getMinute();
				else
					h = hIni[i].getHour() + ":" + hIni[i].getMinute();
				if (hIni[i].getHour() < 10)
					h = "0" + h;
				conHIDiv.setText(h);
				if (hFi[i].getMinute() == 0)
					h = hFi[i].getHour() + ":00";
				else if (hFi[i].getMinute() < 10)
					h = hFi[i].getHour() + ":0" + hFi[i].getMinute();
				else
					h = hFi[i].getHour() + ":" + hFi[i].getMinute();
				if (hFi[i].getHour() < 10)
					h = "0" + h;
				conHFDiv.setText(h);
			}
			else if (dies[i].equals("Dissabte"))
			{
				if (hIni[i].getMinute() == 0)
					h = hIni[i].getHour() + ":00";
				else if (hIni[i].getMinute() < 10)
					h = hIni[i].getHour() + ":0" + hIni[i].getMinute();
				else
					h = hIni[i].getHour() + ":" + hIni[i].getMinute();
				if (hIni[i].getHour() < 10)
					h = "0" + h;
				conHIDis.setText(h);
				if (hFi[i].getMinute() == 0)
					h = hFi[i].getHour() + ":00";
				else if (hFi[i].getMinute() < 10)
					h = hFi[i].getHour() + ":0" + hFi[i].getMinute();
				else
					h = hFi[i].getHour() + ":" + hFi[i].getMinute();
				if (hFi[i].getHour() < 10)
					h = "0" + h;
				conHFDis.setText(h);
			}
		}
	}

	private void mostrarActivitatEsportiva(ActivitatEsportiva a)
	{
		String		dies = "";
		String		dataInici = "";
		String		dataFi = "";

		for (String s : a.getDies())
		{
			if (s != null)
				dies += s + ", ";
		}
		if (dies.endsWith(", "))
			dies = dies.substring(0, dies.length() - 2);
		dataInici += a.getDataInici().getDayOfMonth() + "/" +
					a.getDataInici().getMonthValue() + "/" +
					a.getDataInici().getYear();
		dataFi += a.getDataFi().getDayOfMonth() + "/" +
				a.getDataFi().getMonthValue() + "/" +
				a.getDataFi().getYear();
		mostraHoresEsportiva(a);
		consultaDiesAE.setText(dies);
		consultaDataIniciAE.setText(dataInici);
		consultaDataFiAE.setText(dataFi);
	}

	private void mostrarActivitatCultural(ActivitatCultural a)
	{
		LocalDateTime	ldt = a.getHorariActivitat();
		String			s;
		
		consultarPreuAC.setText("" + a.getPreu() + "€");
		if (ldt != null)
		{
			consultarDataAC.setText("" + ldt.getDayOfMonth() + "/" + ldt.getMonthValue() + "/" + ldt.getYear());
			if (ldt.getMinute() == 0)
				s = ldt.getHour() + ":00";
			else if (ldt.getMinute() < 10)
				s = ldt.getHour() + ":0" + ldt.getMinute();
			else
				s = ldt.getHour() + ":" + ldt.getMinute();
			if (ldt.getHour() < 10)
				s = "0" + s;
			consultarHoraAC.setText(s);
		}
	}

	private void mostrarActivitatFormativa(ActivitatFormativa a)
	{
		LocalDateTime	ldt = a.getDiaIHora();
		LocalDate		ld = a.getDataLimit();
		String			s;
	
		if (ldt != null)
		{	
			consultarDataAF.setText("" + ldt.getDayOfMonth() + "/" + ldt.getMonthValue() + "/" + ldt.getYear());
			if (ldt.getMinute() == 0)
				s = ldt.getHour() + ":00";
			else if (ldt.getMinute() < 10)
				s = ldt.getHour() + ":0" + ldt.getMinute();
			else
				s = ldt.getHour() + ":" + ldt.getMinute();
			if (ldt.getHour() < 10)
				s = "0" + s;
			consultarHoraAF.setText(s);
		}
		if (ld != null)
		{
			consultarDataLimitAF.setText("" + ld.getDayOfMonth() + "/" + ld.getMonthValue() + "/" + ld.getYear());
			consultarDuracioAF.setText("" + (int)a.getDuradaActivitat() + "h");
		}
	}
	
	private String[] diesEsportiva()
	{
		int cont = 0;
		String[] dies;

		if (dilluns.isSelected())
			cont++;
		if (dimarts.isSelected())
			cont++;
		if (dimecres.isSelected())
			cont++;
		if (dijous.isSelected())
			cont++;
		if (divendres.isSelected())
			cont++;
		if (dissabte.isSelected())
			cont++;
		if (cont == 0)
			return (null);
		dies = new String[cont];
		cont = 0;
		if (dilluns.isSelected())
		{
			dies[cont] = "Dilluns";
			cont++;
		}
		if (dimarts.isSelected())
		{
			dies[cont] = "Dimarts";
			cont++;
		}
		if (dimecres.isSelected())
		{
			dies[cont] = "Dimecres";
			cont++;
		}
		if (dijous.isSelected())
		{
			dies[cont] = "Dijous";
			cont++;
		}
		if (divendres.isSelected())
		{
			dies[cont] = "Divendres";
			cont++;
		}
		if (dissabte.isSelected())
		{
			dies[cont] = "Dissabte";
			cont++;
		}
		return (dies);
	}

	private LocalTime[] getHoresIniciActivitatEsportiva(String[] dies)
	{
		LocalTime	t[] = new LocalTime[dies.length];
		int			i = 0;

		for (String s: dies)
		{
			if (s.equals("Dilluns"))
			{
				t[i] = LocalTime.of((int)dillHor.getValue(), (int)dillMin.getValue());
			}
			else if (s.equals("Dimarts"))
			{
				t[i] = LocalTime.of((int)dimaHor.getValue(), (int)dimaMin.getValue());
			}
			else if (s.equals("Dimecres"))
			{
				t[i] = LocalTime.of((int)dimeHor.getValue(), (int)dimeMin.getValue());
			}
			else if (s.equals("Dijous"))
			{
				t[i] = LocalTime.of((int)dijHor.getValue(), (int)dijMin.getValue());
			}
			else if (s.equals("Divendres"))
			{
				t[i] = LocalTime.of((int)divHor.getValue(), (int)divMin.getValue());
			}
			else if (s.equals("Dissabte"))
			{
				t[i] = LocalTime.of((int)dissHor.getValue(), (int)dissMin.getValue());
			}
			i++;
		}
		return (t);
	}

	private LocalTime[] getHoresFiActivitatEsportiva(String[] dies)
	{
		LocalTime t[] = new LocalTime[dies.length];
		int			i = 0;

		for (String s: dies)
		{
			if (s.equals("Dilluns"))
			{
				t[i] = LocalTime.of((int)dillHorFi.getValue(), (int)dillMinFi.getValue());
			}
			else if (s.equals("Dimarts"))
			{
				t[i] = LocalTime.of((int)dimaHorFi.getValue(), (int)dimaMinFi.getValue());
			}
			else if (s.equals("Dimecres"))
			{
				t[i] = LocalTime.of((int)dimeHorFi.getValue(), (int)dimeMinFi.getValue());
			}
			else if (s.equals("Dijous"))
			{
				t[i] = LocalTime.of((int)dijHorFi.getValue(), (int)dijMinFi.getValue());
			}
			else if (s.equals("Divendres"))
			{
				t[i] = LocalTime.of((int)divHorFi.getValue(), (int)divMinFi.getValue());
			}
			else if (s.equals("Dissabte"))
			{
				t[i] = LocalTime.of((int)dissHorFi.getValue(), (int)dissMinFi.getValue());
			}
			i++;
		}
		return (t);
	}

	public ActivitatEsportiva getActivitatEsportivaNova()
	{
		String nom, des, adr, pob;
		int	maxPart;
		String[] dies;
		LocalDate dataInici, dataFi, dataActual;
		Date		aux1;
		Calendar	aux2, aux3;
		LocalTime[] horaInici, horaFi;

		nom = nomActivitatE.getText();
		des = descripcioActivitatE.getText();
		maxPart = (int)numMaximParticipantsE.getValue();
		if (maxPart == 0)
		{
			mostrarMissatgeError("Hi ha d'haver almenys 1 participant com a mínim", "Error al entrar les dades");
			return (null);
		}
		adr = adresaActivitatE.getText();
		pob = poblacioActivitatE.getText();
		pob = ferPrimeraLletraMajuscula(pob);
		dies = diesEsportiva();
		if (dies == null)
		{
			mostrarMissatgeError("Escull els dies en que es realitzara l'activitat", "Error al crear l'activitat");
			return (null);
		}
		aux1 = dataIniciEsportiva.getDate();
		if (aux1 == null)
		{
			mostrarMissatgeError("Selecciona una data d'inici", "Error al crear l'activitat");
			return (null);
		}
		aux2 = Calendar.getInstance();
		aux2.setTime(aux1);
		dataInici = LocalDate.of(aux2.get(Calendar.YEAR), aux2.get(Calendar.MONTH) + 1, aux2.get(Calendar.DAY_OF_MONTH));
		aux1 = dataFiEsportiva.getDate();
		if (aux1 == null)
		{
			mostrarMissatgeError("Selecciona una data de fi", "Error al crear l'activitat");
			return (null);
		}
		aux3 = Calendar.getInstance();
		aux3.setTime(aux1);
		dataFi = LocalDate.of(aux3.get(Calendar.YEAR), aux3.get(Calendar.MONTH) + 1, aux3.get(Calendar.DAY_OF_MONTH));
		if (dataInici.isAfter(dataFi))
		{
			mostrarMissatgeError("La data d'inici no pot ser posterior a la de fi", "Error al entrar les dades");
			return (null);
		}
		dataActual = LocalDate.now();
		if (dataInici.isBefore(dataActual))
		{
			mostrarMissatgeError("La data d'inici seleccionada ja ha passat", "Error al entrar les dades");
			return (null);
		}
		horaInici = getHoresIniciActivitatEsportiva(dies);
		horaFi = getHoresFiActivitatEsportiva(dies);
		for (int i = 0; i < dies.length; i++)
		{
			if (horaInici[i].isAfter(horaFi[i]) || horaInici[i].equals(horaFi[i]))
			{
				mostrarMissatgeError("Error al introduïr les hores d'inici i fi", "Error al entrar les dades");
				return (null);
			}
		}
		if (comprobar(nom, "el nom") && comprobar(des, "la descripció") && comprobar(adr, "l'adreça") && comprobar(pob, "la població"))
		{
			ActivitatEsportiva	a = new ActivitatEsportiva(nom, des, maxPart, adr, pob, dies, dataInici, dataFi, horaInici, horaFi);
			return (a);
		}
		return (null);
	}

	public ActivitatCultural getActivitatCulturalNova()
	{
		String nom, des, adr, pob;
		int	maxPart;
		double preu;
		LocalDateTime horari;
		LocalDate data, dataActual;
		LocalTime hora;
		Date auxData;
		Calendar auxCal;

		nom = nomActivitatC.getText();
		des = descripcioActivitatC.getText();
		maxPart = (int)numMaximParticipantsC.getValue();
		if (maxPart == 0)
		{
			mostrarMissatgeError("Hi ha d'haver almenys 1 participant com a mínim", "Error al entrar les dades");
			return (null);
		}
		adr = adresaActivitatC.getText();
		pob = poblacioActivitatC.getText();
		pob = ferPrimeraLletraMajuscula(pob);
		preu = (double)preuActivitatC.getValue();
		auxData = dataActivitatCultural.getDate();
		if (auxData == null)
		{
			mostrarMissatgeError("Selecciona una data", "Error al crear l'activitat");
			return (null);
		}
		auxCal = Calendar.getInstance();
		auxCal.setTime(auxData);
		data = LocalDate.of(auxCal.get(Calendar.YEAR), auxCal.get(Calendar.MONTH) + 1, auxCal.get(Calendar.DAY_OF_MONTH));
		dataActual = LocalDate.now();
		if (data.isBefore(dataActual))
		{
			mostrarMissatgeError("La data escollida no és possible", "Error al crear l'activitat");
			return (null);
		}
		hora = LocalTime.of((int)horaIniciACultural.getValue(), (int)minIniciACultural.getValue());
		horari = LocalDateTime.of(data, hora);
		if (comprobar(nom, "el nom") && comprobar(des, "la descripció") && comprobar(adr, "l'adreça") && comprobar(pob, "la població"))
		{
			ActivitatCultural	a = new ActivitatCultural(nom, des, maxPart, adr, pob, preu, horari);
			return (a);
		}
		return (null);
	}

	public ActivitatFormativa getActivitatFormativaNova()
	{
		String	nom, des, adr, pob;
		int		maxPart;
		double	durada;
		LocalDateTime	dataHora;
		LocalDate		limit, data, dataActual;
		LocalTime		hora;
		Date auxData, auxData2;
		Calendar auxCal, auxCal2;

		nom = nomActivitatF.getText();
		des = descripcioActivitatF.getText();
		maxPart = (int)numMaximParticipantsF.getValue();
		if (maxPart == 0)
		{
			mostrarMissatgeError("Hi ha d'haver almenys 1 participant com a mínim", "Error al entrar les dades");
			return (null);
		}
		adr = adresaActivitatF.getText();
		pob = poblacioActivitatF.getText();
		pob = ferPrimeraLletraMajuscula(pob);
		durada = (double)duracioActivitatF.getValue();
		if (durada == 0)
		{
			mostrarMissatgeError("La durada mínima és de 0,5h", "Error al entrar les dades");
			return (null);
		}
		auxData = dataActivitatFormativa.getDate();
		if (auxData == null)
		{
			mostrarMissatgeError("Selecciona una data d'inici", "Error al crear l'activitat");
			return (null);
		}
		auxCal = Calendar.getInstance();
		auxCal.setTime(auxData);
		data = LocalDate.of(auxCal.get(Calendar.YEAR), auxCal.get(Calendar.MONTH) + 1, auxCal.get(Calendar.DAY_OF_MONTH));
		dataActual = LocalDate.now();
		if (data.isBefore(dataActual))
		{
			mostrarMissatgeError("La data escollida no és possible", "Error al crear l'activitat");
			return (null);
		}
		hora = LocalTime.of((int)horaIniciActivitatF.getValue(), 0);
		dataHora = LocalDateTime.of(data, hora);
		auxData2 = dataLimitInscripcioF.getDate();
		if (auxData2 == null)
		{
			mostrarMissatgeError("Selecciona una data límit", "Error al crear l'activitat");
			return (null);
		}
		auxCal2 = Calendar.getInstance();
		auxCal2.setTime(auxData2);
		limit = LocalDate.of(auxCal2.get(Calendar.YEAR), auxCal2.get(Calendar.MONTH) + 1, auxCal2.get(Calendar.DAY_OF_MONTH));
		if (limit.isBefore(dataActual))
		{
			mostrarMissatgeError("La data escollida no és possible", "Error al crear l'activitat");
			return (null);
		}
		if (data.isBefore(limit))
		{
			mostrarMissatgeError("Error en seleccionar una data límit", "Error al crear l'activitat");
			return (null);
		}
		if (comprobar(nom, "el nom") && comprobar(des, "la descripció") && comprobar(adr, "l'adreça") && comprobar(pob, "la població"))
		{
			ActivitatFormativa	a = new ActivitatFormativa(nom, des, maxPart, adr, pob, dataHora, limit, durada);
			return (a);
		}
		return (null);
	}

	private boolean comprobar(String s, String missatge)
	{
		String error = "Error en ";
	
		if (s.isEmpty() || s.isBlank())
		{
			mostrarMissatgeError(error + missatge, "Error al entrar les dades");
			return (false);
		}
		return (true);
	}

	public String getNomActivitatABuscar()
	{
		String s = activitatAModificar.getText();
		
		if (s.isEmpty() || s.isBlank())
			return (null);
		return (s);
	}

	public void	mostrarMissatgeError(String missatge, String titol)
	{
		JOptionPane.showMessageDialog(this, missatge, titol, JOptionPane.ERROR_MESSAGE);
	}

	public void	mostrarMissatgeWarning(String missatge, String titol)
	{
		JOptionPane.showMessageDialog(this, missatge, titol, JOptionPane.WARNING_MESSAGE);
	}

	public void mostrarMissatgeSuccess(String missatge, String titol)
	{
		JOptionPane.showMessageDialog(this, missatge, titol, JOptionPane.PLAIN_MESSAGE);
	}

	public int	getFiltreInscripcions()
	{
		String s = admetInscripcions.getSelectedItem().toString();

		if (s.equals("Si"))
			return (2);
		else if (s.equals("No"))
			return (3);
		else
			return (1);
	}

	public int	getFiltreTipusActivitat()
	{
		String s = tipusActivitatFiltre.getSelectedItem().toString();

		if (s.equals("Esportiva"))
			return (1);
		else if (s.equals("Cultural"))
			return (2);
		else if (s.equals("Formació"))
			return (3);
		else
			return (0);
	}

	public String	getFiltrePoblacio()
	{
		String s = poblacioFiltre.getText();

		if (s.isEmpty() || s.isBlank())
			return (null);
		s = ferPrimeraLletraMajuscula(s);
		return (s);
	}

	public String	getNomActivitatConsultada()
	{
		String s = consultaNom.getText();

		if (s.isEmpty() || s.isBlank())
			return (null);
		return (s);
	}

	public String	getDNIinscripcio()
	{
		String s = gestionaDNI.getText();

		if (s.isEmpty() || s.isBlank())
			return (null);
		return (s);
	}

	public String	getTelefonInscripcio()
	{
		String s = gestionaTelefon.getText();

		if (s.isEmpty() || s.isBlank())
			return (null);
		return (s);
	}

	public String	getDNICancelarInscripcio()
	{
		String s = DNIACancelar.getText();

		if (s.isEmpty() || s.isBlank())
			return (null);
		return (s);
	}

	private String ferPrimeraLletraMajuscula(String str)
	{
		char	c;
		String	nova;
	
        if (str == null || str.isEmpty() || str.isBlank())
            return str;
        c = Character.toUpperCase(str.charAt(0));
        nova = str.substring(1);
        return (c + nova);
    }
}
