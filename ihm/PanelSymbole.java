package ihm;

import javax.swing.*;

import controleur.Controleur;

public class PanelSymbole extends JPanel
{
	private Controleur    ctrl;
	private FrameCreation frame;
	private int           indice;

	public PanelSymbole(Controleur ctrl, FrameCreation f, int indice)
	{
		/*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/
		this.ctrl 	= ctrl	;
		this.frame      = f ;
		this.indice 	= indice;

		/*------------------------------------------*/
		/*  Positionnement des composants           */
		/*------------------------------------------*/

		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
	}
}
