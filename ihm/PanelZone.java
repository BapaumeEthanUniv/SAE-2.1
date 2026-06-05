package ihm;

import controleur.Controleur;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelZone extends JPanel implements ActionListener
{
	private Controleur 	ctrl;
	
	private FrameCreation 	frameSaisie;

	private JButton 	btnSuivant;
	private JButton 	btnPrecedent ;

	private int indice;

	public PanelZone(Controleur ctrl, FrameCreation f, int indice)
	{
		this.ctrl = ctrl;
		this.frameSaisie = f;
		this.indice = indice;

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.btnSuivant 	= new JButton("Suivant");
		this.btnPrecedent  	= new JButton("Précédent ");

		this.add(this.btnSuivant);
		this.add(this.btnPrecedent );
		
		this.btnSuivant.addActionListener(this);
		this.btnPrecedent .addActionListener(this);
	}

	public void actionPerformed(ActionEvent a)
	{
		if (a.getSource() == this.btnSuivant)
		{
			this.frameSaisie.setPnl(this.frameSaisie.getPnl(this.indice+1));
		}

		if (a.getSource() == this.btnPrecedent)
		{
			this.frameSaisie.setPnl(this.frameSaisie.getPnl(this.indice-1));
		}
	}
}
