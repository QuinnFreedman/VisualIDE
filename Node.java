import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Node extends JPanel implements MouseListener, MouseMotionListener{
	static Node currentlyDragging;
	ArrayList<Node> parents = new ArrayList<Node>();
	ArrayList<Node> children = new ArrayList<Node>();
	VObject parentObject;
	Direction facing;
	NodeType type;
	NodeStyle style = null;
	Node(NodeType type,VObject parentObj,NodeStyle style){
		this.style = style;
		this.setBounds(0, 0, getPreferredSize().width, getPreferredSize().height);
		this.setOpaque(false);
		this.addMouseListener(this);
		this.facing = Direction.EAST;
		this.type = type;
		this.parentObject = parentObj;
	}
	Node(NodeType type,VObject parentObj){
		this(type,parentObj,NodeStyle.VISIBLE);
	}
	Node(Direction dir, NodeType type, VObject parentObj,NodeStyle style){
		this(type,parentObj,style);
		this.facing = dir;
	}
	Node(Direction dir, NodeType type, VObject parentObj){
		this(type,parentObj,NodeStyle.VISIBLE);
		this.facing = dir;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		switch(style){
		case VISIBLE:
			g.fillArc(0, 0, 30, 20, 0, 360);
		case INVISIBLE:
			
		}
	}
	/*@Override
	public Dimension getSize(){
		return new Dimension(300,300);
	}*/
	@Override
	public Dimension getPreferredSize(){
		switch(style){
		case VISIBLE:
			return new Dimension(30,20);
		case INVISIBLE:
			return new Dimension(0,0);
		}
		return null;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		currentlyDragging = this;
		this.addMouseMotionListener(this);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		currentlyDragging = null;
		Point mouse = getLocationOnPanel(e);
		Rectangle rect = new Rectangle();
		boolean isHoverAnotherNode = false;
		for(Node node : Main.nodes){
			rect = new Rectangle(getLocationOnPanel(node),new Dimension(node.getWidth(),node.getHeight()));
			if(rect.contains(mouse)){
				isHoverAnotherNode = true;
				if(this.type == NodeType.SENDING && node.type == NodeType.RECIEVING){
					node.parents.add(this);
					this.children.add(node);
				}else if(this.type == NodeType.RECIEVING && node.type == NodeType.SENDING){
					this.parents.add(this);
					node.children.add(this);
				}
				break;
			}
		}
		if(!isHoverAnotherNode){
			if(this.parentObject instanceof Primative){
				ChildPicker cp = new ChildPicker(this.parentObject,this,new Point(mouse.x-20,mouse.y-20));
				Main.objects.add(this.parentObject);
				Main.panel.add(cp);
				Main.objects.add(cp);
				Main.panel.repaint();
				Main.panel.revalidate();
				//TODO create cild
				//TODO make createChild method for each primative type, instead of doing it here
			}
		}
		Main.panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Main.mousePos = getLocationOnPanel(e);
		Main.panel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*Main.mousePos.x = e.getLocationOnScreen().x-this.getLocationOnScreen().x;
		Main.mousePos.x = e.getLocationOnScreen().y-this.getLocationOnScreen().y;
		Main.panel.repaint();*/
	}
	public static Point getLocationOnPanel(Component c){
		return new Point(c.getLocationOnScreen().x-Main.panel.getLocationOnScreen().x,c.getLocationOnScreen().y-Main.panel.getLocationOnScreen().y);
	}
	public static Point getLocationOnPanel(MouseEvent e){
		return new Point(e.getLocationOnScreen().x-Main.panel.getLocationOnScreen().x,e.getLocationOnScreen().y-Main.panel.getLocationOnScreen().y);
	}
	public enum Direction{
		NORTH,SOUTH,EAST,WEST
	}
	public enum NodeType{
		SENDING,RECIEVING
	}
	public enum NodeStyle{
		INVISIBLE,VISIBLE
	}
}
