import javax.swing.*;
import java.awt.event.*;

public class panel3 extends JPanel implements ActionListener
{
	private JButton suiv;
	private JButton prec;
	private frame f;
	private int indice;
	public panel3(frame f, int i)
	{
		this.f = f;
		this.indice = i;

		this.suiv = new JButton("suivant");
		this.prec = new JButton("precedent");

		this.prec.addActionListener(this);
		this.suiv.addActionListener(this);

		this.add(new JLabel("3"));
		this.add(this.prec);
		this.add(this.suiv);
	}
	public void actionPerformed(ActionEvent a)
	{
		if (a.getSource()==this.prec)
		{
			this.f.setPnl(this.f.getPnl(this.indice-1));
		}
	}
}