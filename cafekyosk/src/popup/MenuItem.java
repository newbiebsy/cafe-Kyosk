package popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuItem extends JPanel {

	public ImageIcon img;
	public JLabel jlbImage, jlbName, jlbPrice;
	public JPanel jp;
	
	public String category, state;
	public int stock;

	public MenuItem(String url, String name, String price, String category, int stock) {
		
		this.category = category;
		this.stock = stock;
		
		state = "";
		
		if (stock == 0) {
			state = "(Ç°Àý)";
		}

		Color c = new Color(18, 59, 53);
		setBackground(c);

		setLayout(new BorderLayout());
		
		jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.setBackground(c);

		img = new ImageIcon(url);

		jlbImage = new JLabel();
		jlbImage.setHorizontalAlignment(JLabel.CENTER);
		jlbImage.setIcon(img);

		jlbName = new JLabel(name);
		jlbName.setHorizontalAlignment(JLabel.CENTER);
		jlbName.setForeground(Color.WHITE);
		jlbName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));

		jlbPrice = new JLabel(price + state);
		jlbPrice.setHorizontalAlignment(JLabel.CENTER);
		jlbPrice.setForeground(Color.WHITE);
		jlbPrice.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));

		jp.add(jlbImage,"Center");
		jp.add(jlbName,"South");
		add(jp);
		add(jlbPrice, "South");
	}

}
