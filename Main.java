import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;

public class Main implements ActionListener{
	static ArrayList<VObject> objects = new ArrayList<VObject>();
	static ArrayList<Curve> curves = new ArrayList<Curve>();
	static ArrayList<Node> nodes = new ArrayList<Node>();
	static final int gridWidth = 10;
	static Point mousePos = new Point();
	static HashMap<Primative.DataType,Color> colors = new HashMap<Primative.DataType,Color>();
	/**
	 * @wbp.parser.entryPoint
	 */
	static ComponentMover componentMover;
	static JPanel panel;

	public static void main(String[] args){
		new Main();
		colors.put(Primative.DataType.BOOLEAN, Color.green);
		colors.put(Primative.DataType.INTEGER, Color.red);
		colors.put(Primative.DataType.DOUBLE, new Color(255,0,255));
	}
	
	Main(){
		JFrame window = new JFrame();
		window.setTitle("VisualIDE");
		window.setSize(555,325);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		
		JMenuBar menuBar = new JMenuBar();
		
		// File Menu, F - Mnemonic
	    JMenu fileMenu = new JMenu("File");
	    menuBar.add(fileMenu);
	    
	    JMenuItem mntmOpen = new JMenuItem("Open");
	    mntmOpen.setEnabled(false);
	    fileMenu.add(mntmOpen);
	    
	    JMenuItem mntmSave = new JMenuItem("Save");
	    mntmSave.setEnabled(false);
	    fileMenu.add(mntmSave);
	    
	    JMenuItem mntmSaveAs = new JMenuItem("Save As...");
	    mntmSaveAs.setEnabled(false);
	    fileMenu.add(mntmSaveAs);
	    
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);
		
		JMenuItem mntmBoolean = new JMenuItem("Boolean");
		mntmBoolean.addActionListener(this);
		mnAdd.add(mntmBoolean);
		
		JMenuItem mntmInt = new JMenuItem("Integer");
		mntmInt.addActionListener(this);
		mnAdd.add(mntmInt);
		
		JMenuItem mntmDouble = new JMenuItem("Double");
		mntmDouble.addActionListener(this);
		mnAdd.add(mntmDouble);
		
		mnAdd.addSeparator();
		
		JMenuItem mntmFunction = new JMenuItem("Function");
		mntmFunction.addActionListener(this);
		mnAdd.add(mntmFunction);
		
		JMenuItem mntmTimeline = new JMenuItem("Timeline");
		mntmTimeline.addActionListener(this);
		mnAdd.add(mntmTimeline);
		
		JMenuItem mntmBlueprint = new JMenuItem("Blueprint");
		mntmBlueprint.addActionListener(this);
		mnAdd.add(mntmBlueprint);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		panel = new DisplayPanel();
		panel.setPreferredSize(new Dimension(1000, 1000));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		
		
		window.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		window.setJMenuBar(menuBar);
		
		window.setVisible(true);
		
		componentMover = new ComponentMover();
		componentMover.setEdgeInsets(new Insets(10, 10, 10, 10));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		if(c == "Boolean"){
			objects.add(new VBoolean());
		}else if(c == "Integer"){
			objects.add(new VInt());
		}else if(c == "Double"){
			objects.add(new VDouble());
		}else{
			System.out.println("null Action:"+c);
		}
    }

	static class DisplayPanel extends JPanel{
		DisplayPanel(){
			this.setLayout(null);
			this.setBackground(new Color(0x5D5D5D));
		}
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(new Color(0x4A4A4A));
			for(int x = 0; x < this.getPreferredSize().width/gridWidth; x++){
				g.drawLine(x*gridWidth, 0, x*gridWidth, this.getPreferredSize().height);
			}
			for(int y = 0; y < this.getPreferredSize().height/gridWidth; y++){
				g.drawLine(0, y*gridWidth, this.getPreferredSize().width,y*gridWidth);
			}
			if(mousePos != null && Node.currentlyDragging != null)
				(new Curve(Node.currentlyDragging,mousePos)).draw(g);
			for(Curve curve : curves){
				curve.draw(g);
			}
		}
	}
}
