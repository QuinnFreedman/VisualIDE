import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JEditorPane;

public class VMath extends Function{
	
	JEditorPane valueField;
	
	VMath(Point p){
		super();
		this.color = Color.ORANGE;
		this.setBounds(new Rectangle(p,new Dimension(100,60)));
		this.headerLabel.setText("Math");
		this.header.setLayout(new FlowLayout(FlowLayout.LEFT));
		body.setLayout(new BorderLayout());
		valueField = new JEditorPane();
		valueField.setPreferredSize(new Dimension(90,18));
		valueField.setOpaque(false);
	//	valueField.getDocument().addDocumentListener(this);
		valueField.setBorder(Primative.bodyPadding);
		this.body.add(valueField,BorderLayout.CENTER);
		this.inputNode = new Node(Node.Direction.WEST,Node.NodeType.RECIEVING,this,new ArrayList<Primative.DataType>(Arrays.asList(Primative.DataType.GENERIC)));
		inputNode.setBorder(Primative.bodyPadding);
		inputNode.canHaveMultipleInputs = false;
		Main.nodes.add(inputNode);
		this.body.add(inputNode,BorderLayout.LINE_START);
		Main.panel.repaint();
		Main.panel.revalidate();
		Main.panel.add(this);
	}
}