import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class VInt extends Primative{
	static final String name = "Int";
	static int idCounter = 0;
	int value = 0;
	VInt(){
		super();
		this.dataType = Primative.DataType.INTEGER;
		this.headerLabel.setText(name);
		this.id = "Int"+Integer.toString(idCounter);
		idCounter++;
		this.color = Main.colors.get(Primative.DataType.INTEGER);
		this.valueField.setText(Integer.toString(value));
		new IntDocumentFilter((AbstractDocument) valueField.getDocument());
		this.width = 12;
		this.height = 5;
		getFreePosition();
		this.borderWidth = 10;
		this.setBounds(this.position.x*Main.gridWidth, this.position.y*Main.gridWidth, this.width*Main.gridWidth, this.height*Main.gridWidth);
		Main.panel.add(this);
		Main.panel.repaint();
		Main.panel.revalidate();
		//this.functions = new ArrayList<Class<? extends PrimativeFunction>>();
		//this.functions.add(set.class);
		//this.functions.add(subtractFrom.class);
		this.functions.add(new get());
		this.functions.add(new set());
		this.functions.add(new subtractFrom());
		this.functions.add(new multiplyBy());
	}
	@Override
	protected void setValue(String s){
		value = Integer.parseInt(s);
		valueField.getDocument().removeDocumentListener(this);
	}
	
	static class get extends PrimativeFunction{
		public ArrayList<Primative.DataType> input = new ArrayList<Primative.DataType>();
		get(Point pos, Node parentNode, Primative parent) {
			super(pos, Primative.DataType.INTEGER, parentNode, parent, "Get", null, Primative.DataType.INTEGER);
			this.setSize(90,40);
		}
		get(){
			super();
			this.name = "Get";
		}
		
	}
	static class set extends PrimativeFunction{
		public ArrayList<Primative.DataType> input = new ArrayList<Primative.DataType>();
		set(Point pos, Node parentNode, Primative parent) {
			super(pos, Primative.DataType.INTEGER, parentNode, parent, "Set", new ArrayList<Primative.DataType>(){{this.add(Primative.DataType.INTEGER);}},null);
		}
		set(){
			super();
			this.name = "Set";
		}
		
	}
	static class subtractFrom extends PrimativeFunction{
		subtractFrom(Point pos, Node parentNode, Primative parent) {
			super(pos, Primative.DataType.INTEGER, parentNode, parent,"Subtract From", new ArrayList<Primative.DataType>(){{this.add(Primative.DataType.INTEGER);}},null);
		}
		subtractFrom(){
			super();
			this.name = "Subtract From";
		}
		
	}
	static class multiplyBy extends PrimativeFunction{
		multiplyBy(Point pos, Node parentNode, Primative parent) {
			super(pos, Primative.DataType.INTEGER, parentNode, parent,"Multiply By", new ArrayList<Primative.DataType>(){{this.add(Primative.DataType.INTEGER);}},null);
		}
		multiplyBy(){
			super();
			this.name = "Multiply By";
		}
		
	}
	static class IntDocumentFilter extends DocumentFilter {
		
		AbstractDocument doc;
		
		public IntDocumentFilter(AbstractDocument doc) {
			this.doc = doc;
			this.doc.setDocumentFilter(this);
		}
		
		@Override
		public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException {
		    System.out.println("insert string"+ string);
		    System.out.println(offset);
		    super.insertString(fb, offset, string, attr);
		}
		
		@Override
		public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
			throws BadLocationException {
			
		    super.remove(fb, offset, length);
		}
		  
		@Override
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException {
			System.out.println(text);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < text.length(); i++){
				char c = text.charAt(i);
				if(c == '0'|| c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'){
					sb.append(c);
				}
			}
			text = sb.toString();
			super.replace(fb, offset, length, text, attrs);
		}
	}
}