package cbko.projekt.pttp.klient;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import cbko.projekt.pttp.klient.KlientConfig.Protokol;
import cbko.projekt.pttp.serwer.Serwer;


public class Frame extends JFrame {


	 private JPanel panel = new JPanel();
	 JButton zapis;
	 
	 private JTextArea wyniki;
	 
	public Frame() throws UnknownHostException, IOException 
	{
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 // this.pack();
		  //this.setLocationRelativeTo;
		  this.setLocationByPlatform(true);
		  this.setSize(440, 415);
		  this.setTitle("Klient PTTP/PTTPU");
		  
		  
		  GroupLayout layout = new GroupLayout(panel);
		  panel.setLayout(layout);
		  layout.setAutoCreateGaps(true);
		  layout.setAutoCreateContainerGaps(true);
		  		  
		  JLabel nazwaUrl= new JLabel("Podaj url");
	      JTextField url = new JTextField();
	      url.setMaximumSize(new Dimension(200, 25));
	      url.setText(KlientConfig.buildURL());
	     
		  
		  JLabel polecenie= new JLabel("Wybierz protokó³:");
		  JRadioButton pttp = new JRadioButton("PPTP", true);

		  pttp.addActionListener(new ActionListener() 
		  {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlientConfig.protokol = Protokol.PTTP;
				System.out.println("protokol: " + KlientConfig.protokol);
				url.setText(KlientConfig.buildURL());
			}
		  });
		  
		  JRadioButton pttpu = new JRadioButton("PPTPU", false);
		  pttpu.addActionListener(new ActionListener() 
		  {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlientConfig.protokol = Protokol.PTTPU;
				System.out.println("protokol: " + KlientConfig.protokol);
				url.setText(KlientConfig.buildURL());
			}
		  });
		  
		  ButtonGroup group = new ButtonGroup();
		  group.add(pttpu);
	      group.add(pttp);
	      
	      JLabel nazwaHosta= new JLabel("Podaj numer IP (hosta)");
	      JTextField host = new JTextField();
	      host.setMaximumSize(new Dimension(120, 25));
	      host.setText(KlientConfig.ip);
	      host.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				KlientConfig.ip = host.getText();
				System.out.println("IP: " + KlientConfig.ip);
				url.setText(KlientConfig.buildURL());
			}
			@Override
			public void keyPressed(KeyEvent e) {}
	      });
	      
	      JLabel nazwaSciezki= new JLabel("Podaj œcie¿kê");
	      JTextField sciezka = new JTextField();
	      sciezka.setMaximumSize(new Dimension(200, 25));
	      sciezka.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {
					KlientConfig.sciezka = sciezka.getText();
					System.out.println("sciezka: " + KlientConfig.sciezka);
					url.setText(KlientConfig.buildURL());
				}
				@Override
				public void keyPressed(KeyEvent e) {}
		      });
	      
	      wyniki = new JTextArea();
	      wyniki.setMaximumSize(new Dimension(400, 200));
	      wyniki.setEditable(false);
	      wyniki.setText("");
	      DefaultCaret caret = (DefaultCaret)wyniki.getCaret();
	      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	      	      
	      JScrollPane scroll = new JScrollPane(wyniki);
	      
	     
	      JButton wyslij = new JButton("Wyœlij");
	      
	      
	      wyslij.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{				
					url.setText(KlientConfig.buildURL());
					Klient.polacz();
			}
		});
	      
	      zapis = new JButton("Wybierz œcie¿kê zapisu");
	      
	      zapis.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				wyborSciezkiZapisu();
			}
		});
	      
	      
			
	      
	      layout.setVerticalGroup(
	    		   layout.createSequentialGroup()
	    		      .addComponent(polecenie)
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(pttp)
	    		           .addComponent(pttpu)
	    		           )
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		  .addComponent(nazwaHosta)
	    		    		  .addComponent(nazwaUrl))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		  .addComponent(host)
	    		    		  .addComponent(url))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		  .addComponent(nazwaSciezki)
	    		    		  .addComponent(wyslij))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		  .addComponent(zapis)
	    		    		  .addComponent(sciezka))
	    		      .addComponent(scroll)
	    		      
	    		);
	     
	      url.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {
					KlientConfig.url = url.getText();
				//	System.out.println("URL: " + KlientConfig.url);
					KlientConfig.wypelnianiePol();
					sciezka.setText(KlientConfig.sciezka);
					host.setText(KlientConfig.ip);
					
					if(KlientConfig.protokol==Protokol.PTTP)
					{
						pttp.setSelected(true);
					System.out.println("Zmieniam pttp");
					System.out.println("Zmienna protokol   "+KlientConfig.protokol);
					}
					else if(KlientConfig.protokol==Protokol.PTTPU)
					{
						pttpu.setSelected(true);
					System.out.println("Zmieniam pttpu   ");
					}
					else
					{
						host.setText("");
					}
				}
				@Override
				public void keyPressed(KeyEvent e) {}
		      });
	      
	      
	      layout.setHorizontalGroup(
	    		   layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		   .addGroup(layout.createSequentialGroup()
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		    		  .addComponent(polecenie)
	    		    		  .addGroup(layout.createSequentialGroup()
	    		    				  .addComponent(pttp)
	    		    		          .addComponent(pttpu))
	    		    		  .addComponent(nazwaHosta)
	    		    		  .addComponent(host)
	    		    		  .addComponent(nazwaSciezki)
	    	    		      .addComponent(sciezka))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	    		    		  .addComponent(nazwaUrl)
	    	    		      .addComponent(url)
	    	    		      .addComponent(wyslij)
	    	    		      .addComponent(zapis)))
	    		   
	    		   .addComponent(scroll)
	    		    );
	      
	      this.add(panel);
	      this.setVisible(true);
	}
	
	public void println(String str)
	{
		wyniki.append(str);
		wyniki.append("\n");
	}
	
	public void wyborSciezkiZapisu()
	{
		JFileChooser fo = new JFileChooser();
	    fo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    if(fo.showOpenDialog(zapis) == JFileChooser.APPROVE_OPTION)
	    {
	    	  Path path = fo.getSelectedFile().toPath();
	    	  println(path.toString());
	    	  KlientConfig.sciezkaZapisu = path;
	    }
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Frame f = new Frame();
		f.setVisible(true);
	}
}
