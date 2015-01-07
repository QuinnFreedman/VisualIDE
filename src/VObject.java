import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.util.Iterator;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VObject extends JPanel{
	String id;
	Point position;
	Boolean isDragged;
	int width;
	int height;
	int borderWidth;
	Color color;
	JPanel header;
	JPanel body;
	JLabel headerLabel;
	protected static Point getFreePosition(){
		return new Point(10,10);
	}
	VObject(){
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		header = new JPanel();
		header.setOpaque(false);
		((FlowLayout) header.getLayout()).setHgap(7);
		((FlowLayout) header.getLayout()).setVgap(2);
		headerLabel = new JLabel();
		headerLabel.setOpaque(false);
		header.add(headerLabel);
		Main.componentMover.registerComponent(this);
		this.add(header,BorderLayout.PAGE_START);
		body = new JPanel();
		body.setOpaque(false);
		this.add(body,BorderLayout.CENTER);
	}
	
	public void delete(){
		Main.objects.remove(this);
		Main.panel.remove(this);
		Iterator<Curve> itr = Main.curves.iterator();
		Curve c = null;
		while(itr.hasNext()){
			c = itr.next();
			if((c.isNode[0] && c.nodes[0].parentObject == this) || (c.isNode[1] && c.nodes[1].parentObject == this)){
				itr.remove();
			}
		}
		Iterator<Node> itrN = Main.nodes.iterator();
		Node n = null;
		while(itrN.hasNext()){
			n = itrN.next();
			if(n.parentObject == this){
				itrN.remove();
			}
		}
		Main.panel.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(0, 0, color, 0, header.getPreferredSize().height,
				new Color(20,20,20,127));
		g2.setPaint(gradient);
	    g2.fill(new RoundRectangle2D.Double(0, 0, this.getSize().width, this.getSize().height+20, 20, 20));
	    g2.setPaint(Color.black);
	}
	
	
	/*static class inlineEditor extends JEditorPane{
		
		
		@Override
		public boolean getScrollableTracksViewportWidth() {
		    Component parent = getParent();
		    ComponentUI ui = getUI();

		    return parent != null ? (ui.getPreferredSize(this).width <= parent
		        .getSize().width) : true;
		}
	}*/
}