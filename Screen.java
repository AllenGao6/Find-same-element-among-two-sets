import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 
import java.util.ListIterator;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

 
public class Screen extends JPanel implements ActionListener {
    private JTextArea textArea;
    private JTextArea textArea2;
    private JTextArea textArea3;
	private String text = "";
	private String text2 = "";
	private String text3 = "";
	
	private int price;
	private String name = "";
 
    Set<Item> hashlist1;
    Set<Item> hashlist2;
    Set<Item> hashlist3;
    
    
    private int index;
    private int index2;
    private int index3;
    
    private Iterator<Item> it1;
    private Iterator<Item> it2;
    private Iterator<Item> it3;
    
    private JButton samebetween, displayAll, displayUniqueA, displayUniqueB, searchItem, addA, addB;
	private JTextField nameAdd, priceAdd, nameSearch, priceSearch;
    
    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
         
         hashlist1 = new HashSet<Item>();
         hashlist2 = new HashSet<Item>();
         hashlist3 = new HashSet<Item>();
        //TextArea1
        textArea = new JTextArea(200,400); //sets the location and size
        textArea.setText(text);
         
        //JScrollPane1
        JScrollPane scrollPane = new JScrollPane(textArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10,10,200,400);
		this.add(scrollPane);
        
        //TextArea2
        textArea2 = new JTextArea(200,400); //sets the location and size
        textArea2.setText(text2);
         
        //JScrollPane2
        JScrollPane scrollPane2 = new JScrollPane(textArea2); 
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setBounds(225,10,200,400);
		this.add(scrollPane2);
		
		//TextArea3
        textArea3 = new JTextArea(200,400); //sets the location and size
        textArea3.setText(text3);
         
        //JScrollPane2
        JScrollPane scrollPane3 = new JScrollPane(textArea3); 
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane3.setBounds(675,10,200,400);
		this.add(scrollPane3);
        
		
		//Store A
		try{
			Scanner scan = new Scanner(new File("StoreA.txt"));
            while (scan.hasNext()){
               String[] part = scan.nextLine().split(",");
               System.out.println(part[0] + " " + part[1]);
               name = part[0];
               price = Integer.parseInt(part[1]);
               hashlist1.add(new Item(name,price));
               
               //added = false;
               /*while(itStudent.hasNext()){
               		if(lastName.compareTo(itStudent.next().getLast()) <= 0){
               			itStudent.previous();
               			itStudent.add(new Student(firstName, lastName, age));
               			added = true;
               			break;
               		}
               }
               if(added == false){
               		itStudent.add(new Student(firstName, lastName, age));
               }*/
            }     
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Store B
        try{
			Scanner scan = new Scanner(new File("StoreB.txt"));
            while (scan.hasNextLine()){
               String[] part = scan.nextLine().split(",");
               name = part[0];
               price = Integer.parseInt(part[1]);
               hashlist2.add(new Item(name,price));
            }     
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		
		samebetween = new JButton("Same items between store A and B");
        samebetween.setBounds(450, 15, 200, 40);
        samebetween.addActionListener(this);
        this.add(samebetween);
		
		displayAll = new JButton("Display All");
        displayAll.setBounds(450,60, 180, 40);
        displayAll.addActionListener(this);
        this.add(displayAll);
		
		displayUniqueA = new JButton("Unique StoreA Items");
        displayUniqueA.setBounds(450,110, 180, 40);
        displayUniqueA.addActionListener(this);
        this.add(displayUniqueA);
		
		displayUniqueB = new JButton("Unique StoreB Items");
        displayUniqueB.setBounds(450,160, 180, 40);
        displayUniqueB.addActionListener(this);
        this.add(displayUniqueB);
		
		nameSearch = new JTextField(50);
        nameSearch.setBounds(450, 210, 85, 40);
		nameSearch.setText("Input name");
        this.add(nameSearch);
		
		priceSearch = new JTextField(50);
        priceSearch.setBounds(545, 210,85, 40);
		priceSearch.setText("Input Price");
        this.add(priceSearch);
		
		searchItem = new JButton("Search Item");
        searchItem.setBounds(450,260, 180, 40);
        searchItem.addActionListener(this);
        this.add(searchItem);
		
		nameAdd = new JTextField(50);
        nameAdd.setBounds(450, 310,85, 40);
		nameAdd.setText("Input Name");
        this.add(nameAdd);
		
		priceAdd = new JTextField(50);
        priceAdd.setBounds(545, 310,85, 40);
		priceAdd.setText("Input Price");
        this.add(priceAdd);
		
		addA = new JButton("Add A");
        addA.setBounds(450,370, 85, 40);
        addA.addActionListener(this);
        this.add(addA);
		
		addB = new JButton("Add B");
        addB.setBounds(545,370, 85, 40);
        addB.addActionListener(this);
        this.add(addB);
		
	}
 
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(900,600);
    }
 
    public void paintComponent(Graphics g) {
        //draw background
        g.setColor(Color.white);
        g.fillRect(0,0,900,600);
        //text1
        index = 0;
        text = "";
        it1 = hashlist1.iterator();
        while(it1.hasNext()){
            index++;
            text += index + ") " + it1.next().toString() + "\n";
        }
        textArea.setText(text);
        //text2
        index2 = 0;
        text2 = "";
        it2 = hashlist2.iterator();
        while(it2.hasNext()){
            index2++;
            text2 += index2 + ") " + it2.next().toString() + "\n";
        }
        textArea2.setText(text2);
        //text3
        
        index2 = 0;
        it3 = hashlist3.iterator();
        while(it3.hasNext()){
            index2++;
            text3 += index2 + ") " + it3.next().toString() + "\n";
        }
        textArea3.setText(text3);
        text3 = "";
        hashlist3.clear();
        
		
    }
	/*public void checksame(){
	    it1 = hashlist1.iterator();
		    //it2 = hashlist2.iterator();
		    while(it1.hasNext()){
		        it2 = hashlist2.iterator();
		        while(it2.hasNext()){
		            if(it1.next().equals(it2.next())){
		                hashlist3.add(new Item(it1.next().getName(),it1.next().getPrice()));
		            }
		        }
		        it1.next();
		    }
	}*/
	public void actionPerformed(ActionEvent e) 
	{
	    text = "";
		text2 = "";
		text3 = "";
		
		if(e.getSource() == samebetween){
		    System.out.println("sameBetween Button");
		    for(Item each : hashlist1){
		        if(hashlist2.contains(each)){
		            text3 += each + "\n";
		        }
		    }
		}
		
		if (e.getSource() == displayAll){
			for(Item each : hashlist1){
			    hashlist3.add(new Item(each.getName(),each.getPrice()));
			}
			for(Item each : hashlist2){
			    hashlist3.add(new Item(each.getName(),each.getPrice()));
			}
			
		} 
			
		if (e.getSource() == displayUniqueA){
			for(Item each : hashlist1){
			    if(!hashlist2.contains(each));
			        text3 += each + "\n";
			}
		} 
		if (e.getSource() == displayUniqueB){
			for(Item each : hashlist2){
			    if(!hashlist1.contains(each));
			        text3 += each + "\n";
			}
		} 
		if (e.getSource() == searchItem){
		    boolean A = false;
		    boolean B = false;
			String name = nameSearch.getText();
			int price = Integer.parseInt(priceSearch.getText());
			Item check = new Item(name,price);
			for(int i = 0; i<hashlist1.size();i++){
			    if(hashlist1.contains(check)){
			        A = true;
			    }
			}
			for(int i = 0; i<hashlist2.size();i++){
			    if(hashlist2.contains(check)){
			        B = true;
			    }
			}
			if(A && B)
			    text3 = "Both";
			else if(!A && B)
			    text3 = "Store B";
			else if(A && !B)
			    text3 = "Store A";
			else
			    text3 = "None";
		} 
		if (e.getSource() == addA){
		    String name = nameAdd.getText();
			int price = Integer.parseInt(priceAdd.getText());
			hashlist1.add(new Item(name,price));
		} 
		if (e.getSource() == addB){
		    String name = nameAdd.getText();
			int price = Integer.parseInt(priceAdd.getText());
			hashlist2.add(new Item(name,price));
		}
		repaint();
	} 	
}