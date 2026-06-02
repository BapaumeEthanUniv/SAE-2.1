import javax.swing.*;
public class frame extends JFrame
{
	private JPanel[] tabPanels;

	private JPanel panelActif;
	public frame()
	{
		this.tabPanels = new JPanel[3];

		this.setSize(500,500);

		this.tabPanels[0] = new panel1(this,0);
		this.tabPanels[1] = new panel2(this,1);
		this.tabPanels[2] = new panel3(this,2);

		this.panelActif=this.tabPanels[0];
		this.add(this.panelActif);

		this.setVisible(true);
	}
	public static void main(String[] args) {
		new frame();
	}
	public void setPnl(JPanel pnl)
	{
		this.remove(this.panelActif);
		this.panelActif = pnl;
		this.add(this.panelActif);

		this.setVisible(false);
		this.setVisible(true);
	}
	public JPanel getPnl(int i) { return this.tabPanels[i]; }
}