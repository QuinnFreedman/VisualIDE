import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

public class PrimativeFunction extends Function{
	public String name;
	protected Node nodeFromParent;
	Primative.DataType type;
	private JLabel label;
	private String text = "";
	PrimativeFunction(Point pos, Primative.DataType type, Node parentNode, Primative parent, String name){
		super();
		this.type = type;
		this.color = Main.colors.get(type);
		this.setBounds(new Rectangle(pos,new Dimension(90,40)));
		this.nodeFromParent = new Node(Node.Direction.WEST, Node.NodeType.SENDING, this, Node.NodeStyle.INVISIBLE);
		this.body.add(nodeFromParent);
		this.body.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.text = name;
		label = new JLabel(name);
		this.body.add(label);
		Main.nodes.add(nodeFromParent);
		Main.curves.add(new Curve(parentNode,nodeFromParent));
		Main.panel.add(this);
	}
	PrimativeFunction() {
		super();
	}
	PrimativeFunction(Point pos, Node parentNode, Primative parent) {
		super();
		System.out.println("you shouldn't be using this");
	}
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(0, 
				0, 
				new Color(Math.min(color.getRed()+64,255),
						Math.min(color.getGreen()+64,255),
						Math.min(color.getBlue()+64,255),
						color.getAlpha()),
				0, 
				this.getHeight()/2,
				new Color(20,20,20,127),true);
		
		g2.setPaint(gradient);
	    g2.fill(new RoundRectangle2D.Double(0, 0, this.getSize().width, this.getSize().height, 20, 20));
	    g2.setPaint(Color.black);
	}
}
