import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChildPicker extends VObject{
	private static final long serialVersionUID = 1L;
	VObject parent;
	Node parentNode;
	ChildPicker(VObject parent, Node parentNode, Point position){
		this.parent = parent;
		this.position = position;
		this.setBounds(new Rectangle(position,new Dimension(100,100)));
		this.color = Color.black;
		this.headerLabel.setForeground(Color.white);
		this.headerLabel.setText("Pick one...");
		this.body.setLayout(new BoxLayout(body, 1));
		
		Node n = new Node(Node.Direction.WEST,Node.NodeType.RECIEVING,this);
		Main.nodes.add(n);
		this.header.add(n);
		this.header.remove(headerLabel);
		this.header.add(headerLabel);
		this.parentNode = parentNode;
		Main.curves.add(new Curve(parentNode,n));
		
		/*for(Class<? extends PrimativeFunction> c : ((Primative) this.parent).functions){
			System.out.println((c.getClass()));
		}*/

		if(parent instanceof ContainsChildFunctions){ //TODO should be implied
			for(PrimativeFunction f : ((ContainsChildFunctions) parent).getFunctions()){
				this.body.add(new MenuItem(f,this));
			}
		}
	}
	
	private class MenuItem extends JLabel implements MouseListener{
		String name;
		ChildPicker childPicker;
		private PrimativeFunction f;
		MenuItem(PrimativeFunction f, ChildPicker parent){
			super();
			this.f = f;
			this.name = f.name;
			this.childPicker = parent;
			this.setBackground(Color.RED);
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.setText(name);
			this.addMouseListener(this);
		}
		/*@Override
		public Dimension getPreferredSize(){
			return new Dimension(30,100);
		}*/
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			PrimativeFunction pf;
			try {
				Constructor<?> constructor = f.getClass().getConstructor(Point.class, Node.class, Primative.class);
				pf = (PrimativeFunction) constructor.newInstance(Node.getLocationOnPanel(this.childPicker), ((Primative) this.childPicker.parent).dataType, parentNode, (Primative) this.childPicker.parent);
			} catch (InstantiationException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}finally{
				pf = new PrimativeFunction(Node.getLocationOnPanel(this.childPicker), ((Primative) this.childPicker.parent).dataType, parentNode, (Primative) this.childPicker.parent,"error");
			}
			Main.objects.add(pf);
			Main.panel.repaint();
			Main.panel.revalidate();
			
			this.childPicker.delete();
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// Auto-generated method stub
			
		}
	}
	
}
