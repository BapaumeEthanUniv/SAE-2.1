package ihm;

import controleur.Controleur;

import metier.CreateurPlateau;
import metier.Casting;
import metier.Role;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.*;

import javax.swing.*;

public class PanelAccueil extends JPanel //implements ActionListener
{
	private Controleur 	ctrl;
	private FrameSaisie 	frameSaisie;
	private int		indice;
	
	public PanelAccueil(Controleur ctrl, FrameSaisie f, int indice)
	{
		this.ctrl 		= ctrl;
		this.frameSaisie 	= frameSaisie;
		this.indice 		= indice;
	}
}

