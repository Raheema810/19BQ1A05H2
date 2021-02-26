package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileExplorerUI extends JFrame implements ActionListener {
	private JPanel treeView;
	private JPanel contentView;
	private JTree myTree;
	private JScrollPane scrollPane;
	
	public FileExplorerUI() {
		treeView = new JPanel();
		contentView = new JPanel();
		
		myTree = new JTree();
		treeView.setSize(new Dimension(150,300));
		contentView.setSize(new Dimension(200,300));
		
		
		JMenuItem selectMenuItem = new JMenuItem("Select");
		selectMenuItem.addActionListener(this);
		selectMenuItem.setActionCommand("select");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		closeMenuItem.addActionListener(this);
		closeMenuItem.setActionCommand("close");
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(selectMenuItem);
		fileMenu.add(closeMenuItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		
		setJMenuBar(menuBar);
		//setLayout(new GridLayout(1,0));
		//add(treeView);
		setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
		add(treeView);
		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.VERTICAL);
		s.setMaximumSize(new Dimension(Integer.MAX_VALUE,2));
		add(s);
		//add(contentView);
		add(contentView);
		 treeView.add(new JScrollPane(myTree));
		setVisible(true);
		setTitle("File Explorer");
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("close")) {
				dispose();
		}
		else if(e.getActionCommand().equals("select")) {
			JFileChooser chooser  = new JFileChooser("/Users/mulugu/");

			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int c = chooser.showOpenDialog(chooser);
			if(c == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				DefaultMutableTreeNode rootNode = getFiles(file.getAbsolutePath());
				//myTree = new JTree(rootNode);
				  DefaultTreeModel model = new DefaultTreeModel(rootNode);
				  myTree.setModel(model);
				 
					
					/*
					 * scrollPane = new JScrollPane(myTree);
					 * scrollPane.setHorizontalScrollBarPolicy(JScrollPane.
					 * HORIZONTAL_SCROLLBAR_ALWAYS);
					 * scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					 */
					 
					 
				  
				revalidate();
				//JOptionPane.showMessageDialog(chooser, file.getName());	
				
				}
		}
		
	}
 private DefaultMutableTreeNode getFiles(String path) {
	 File file = new File(path);
	 DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(file.getName());
	 if(file.isDirectory()) {
		 File[] list = file.listFiles();
		 for(File f:list) {
			if(f.isDirectory()) {
				DefaultMutableTreeNode internalNode = getFiles(f.getAbsolutePath());
				rootNode.add(internalNode);
			}
			else if(f.isFile()){
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(f.getName());
				rootNode.add(childNode);
			}
		 }
	 } 
	 else if(file.isFile()){
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName());
			rootNode.add(childNode);
		}
	 return rootNode;
 }
	

}
