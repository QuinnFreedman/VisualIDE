import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;

public class Debug{
	private static VObject currentLowest;
	private static VObject currentHighest;
	private static int currentNode = 0;
	private static boolean isStepping = false;
	
	private static void startStep(){
		currentLowest = Main.entryPoint;
		isStepping = true;
		currentLowest.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
	}

	private static void step(){
		currentLowest.setBorder(BorderFactory.createEmptyBorder());
		if(currentLowest.getClass() == EntryPoint.class){
			ArrayList<Node> children = ((EntryPoint) currentLowest).startNode.children;
			if(children.size() != 0){
				currentLowest = children.get(0).parentObject;
			}
		}else if(currentLowest.getClass() == Args.class){
			if(currentHighest == null){
				if(currentNode == ((Args) currentLowest).inputNodes.size()){
					//goto next lowest
				}else{
					currentHighest = ((Args) currentLowest).inputNodes.get(currentNode).parentObject;
					currentNode++;
				}
			}
		}
		currentLowest.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
	}
	
	public static void tab() {
		if(!isStepping){
			startStep();
		}else{
			step();
		}
		
	}
	
}