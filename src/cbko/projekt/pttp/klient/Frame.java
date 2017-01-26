package cbko.projekt.pttp.klient;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cbko.projekt.pttp.klient.KlientConfig.Protokol;


public class Frame extends JFrame {


	 private JPanel panel = new JPanel();
	 
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
	      
	      JTextArea wyniki = new JTextArea();
	      wyniki.setMaximumSize(new Dimension(400, 200));
	      //wyniki.setEnabled(false);
	      wyniki.setEditable(false);
	      wyniki.setText("1sssfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nssfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\nsfgd\n");
	      	      
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
	    		      .addComponent(sciezka)
	    		      .addComponent(scroll)
	    		      
	    		);
	     /* layout.setVerticalGroup(
	    		   layout.createSequentialGroup()
	    		      //.addComponent(panel2)
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		  
	    		    		  .addComponent(nazwaUrl))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		
	    		    		  .addComponent(url))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		    		  
	    		    		  .addComponent(wyslij))
	    		      
	    		      .addComponent(wyniki)
	    		      
	    		);
	      layout2.setVerticalGroup(
	    		  layout2.createSequentialGroup()
    		      .addComponent(polecenie)
    		      .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		           .addComponent(pttp)
    		           .addComponent(pttpu)
    		           )
    		      .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		    		  .addComponent(nazwaHosta)
    		    	)
    		      .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		    		  .addComponent(host)
    		    		 ) 
    		      .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		    		  .addComponent(nazwaSciezki)
    		    		)
    		      .addComponent(sciezka)
    		      
	    		  
	    		  );
	      layout2.setHorizontalGroup(
	    		  layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		  .addComponent(polecenie)
				  .addGroup(layout2.createSequentialGroup()
	    				  .addComponent(pttp)
	    		          .addComponent(pttpu))
	    		  .addComponent(nazwaHosta)
	    		  .addComponent(host)
	    		  .addComponent(nazwaSciezki)
    		      .addComponent(sciezka)
	    		  	  
	    		  );
	    		  
	      
	      
	      layout.setHorizontalGroup(
	    		   layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		   .addGroup(layout.createSequentialGroup()
	    				   	  //.addComponent(panel2)
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	    		    		  .addComponent(nazwaUrl)
	    	    		      .addComponent(url)
	    	    		      .addComponent(wyslij)))
	    		   .addComponent(wyniki)
	    		    );*/
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
	    	    		      .addComponent(wyslij)))
	    		   .addComponent(scroll)
	    		    );
	      
	      this.add(panel);
	      this.setVisible(true);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Frame f = new Frame();
		f.setVisible(true);
	}
}
