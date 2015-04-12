import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;

public class Debug{
	//private static ArrayList<VObject> currentLowest;
	//private static VObject currentHighest = null;
	private static ArrayList<VObject> stack;
	private static int currentNode = 0;
	private static boolean isStepping = false;
	
	private static void startStep(){
		stack = new ArrayList<VObject>(Arrays.asList(Main.entryPoint));
		isStepping = true;
		stack.get(0).setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
	}

	private static void step(){
		
		if(stack.get(0) instanceof Args){
			
		}else if(stack.get(0) instanceof Function){
			
		}
		
		/*if(currentHighest == null && currentLowest.size() == 1){
			if(currentLowest.get(0).getClass() == EntryPoint.class){
				ArrayList<Node> children = ((EntryPoint) currentLowest.get(0)).startNode.children;
				if(children.size() != 0){
					currentLowest.set(0,children.get(0).parentObject);
				}
			}else if(currentLowest.get(0).getClass() == Args.class){
				
			}
		}*/
		/*if(currentHighest != null)
			currentHighest.setBorder(BorderFactory.createEmptyBorder());
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
		if(currentHighest != null)
			currentHighest.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		currentLowest.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		*/
	}
	
	public static void tab() {
		if(!isStepping){
			startStep();
		}else{
			step();
		}
		
	}
	
}