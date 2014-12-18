import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Args extends VObject{
	private ArrayList inputNodes;
	private Node actionNode;
	private Node outputNode;
	Args(Node A, Node B){
		Node recieveNode;
		Node sendNode;
		if(A.type == Node.NodeType.SENDING){
			recieveNode = B;
			sendNode = A;
		}else{
			recieveNode = A;
			sendNode = B;
		}
		this.color = Color.black;
		this.setBounds(
				((Node.getLocationOnPanel(recieveNode).x+(recieveNode.getPreferredSize().width/2))+(Node.getLocationOnPanel(sendNode).x+(sendNode.getPreferredSize().width/2)))/2, 
				((Node.getLocationOnPanel(recieveNode).y+(recieveNode.getPreferredSize().height/2))+(Node.getLocationOnPanel(sendNode).y+(sendNode.getPreferredSize().height/2)))/2, 
				80, 
				80);
		this.setOpaque(false);
		ArrayList<Primative.DataType> inputDataType = new ArrayList<Primative.DataType>();
			inputDataType.addAll(sendNode.dataType);
		ArrayList<Primative.DataType> outputDataType = recieveNode.dataType;
		actionNode = new Node(Node.Direction.EAST, Node.NodeType.RECIEVING, this, inputDataType);
		outputNode = new Node(Node.Direction.WEST, Node.NodeType.SENDING, this, outputDataType);
		Main.nodes.add(actionNode);
		Main.nodes.add(outputNode);
		this.add(actionNode,BorderLayout.AFTER_LINE_ENDS);
		this.add(outputNode,BorderLayout.BEFORE_LINE_BEGINS);
		Main.objects.add(this);
		Main.panel.add(this);
		this.repaint();
		this.revalidate();
		Main.curves.add(new Curve(outputNode,recieveNode));
		Main.curves.add(new Curve(actionNode,sendNode));
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
}