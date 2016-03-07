package gui;

import java.awt.EventQueue;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import algo.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gui.*;

public class Encryptor {

	JFrame frame;
	int xmouse;
	int ymouse;
	private JTextField textField;
	private JFileChooser fileChooser;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JProgressBar progressBar;
	File fopen;
	FileInputStream fin;
	FileOutputStream fout;
	int n=0;
	private String src;
	private String des;
	private StringBuilder builder;
	File encfile;
	int col;
	int ceaserkey;
	boolean flag;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Encryptor window = new Encryptor();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Encryptor() {
//		
//		try
//		 {
//		 UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		 }catch(Exception e){}
	try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {}
		initialize();
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private boolean check()
	{	
		if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) 
			{
				return true;
			}
			else 
			{	
				if (flag && comboBox_1.getSelectedIndex() !=4 )
				{
					return false;
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Please type same password in both the fields","ERROR",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}		
	}
	
	private boolean checkpass()
	{
		int i;
		i=comboBox_1.getSelectedIndex();
		col=8*(i+1);
		if (passwordField.getPassword().length==col) 
		{
			
			return true;			
		}
		else
		{	
			if(flag)
				{
					return false;
				}
			else
			{
				JOptionPane.showMessageDialog(frame, "Password length should be:-" + col,"ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}
	
	private void progress()
	{
		int i=0;
		try
        {
           while(i<=100)
           {
	            Thread.sleep(25);
			progressBar.paintImmediately(0, 0, 800, 75);
			progressBar.setValue(i);
			             i++;
           }
         }
         catch(Exception e1)
         {
        	 System.out.print("Caughted exception is ="+e1);
         }
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 844, 538);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(805, 0, 39, 34);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 xmouse = e.getX();
			     ymouse = e.getY();
			}
		});
		lblNewLabel_1.setBounds(0, 0, 844, 34);
		lblNewLabel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
		        int y = e.getYOnScreen();
		        frame.setLocation(x - xmouse, y - ymouse);
		        
			}
		});
		
		JButton btnNewButton = new JButton("Browse..");
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				try {
				builder = new StringBuilder();
				fileChooser.showOpenDialog(null);
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				fin = new FileInputStream(fileChooser.getSelectedFile());
				 while((n=fin.read())!=-1){
					 builder.append((char)n);
				    }
				 	src = builder.toString();
				    fin.close();
				} 
				catch (Exception e1) 
				{
				 e1.printStackTrace();
				}
			}
		});
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Main.frame.setVisible(true);
			}
		});
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setBounds(0, 0, 39, 34);
		frame.getContentPane().add(label_1);
		btnNewButton.setBounds(717, 65, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(121, 60, 565, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null && comboBox.getSelectedIndex() != 2 && comboBox.getSelectedIndex() != 3 && comboBox.getSelectedIndex() != 4 )
				{
		        comboBox_1.setEnabled(true);
				}
				else if( comboBox.getSelectedIndex() == 3)
				{
					flag = true;
				}
				else if ( comboBox.getSelectedIndex() ==2 )
				{
					ceaserkey = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Shift:-"));
					passwordField.setEnabled(false);
					passwordField_1.setEnabled(false);
					flag = true;
				}
				else if( comboBox.getSelectedIndex() == 4)
				{
					flag = true;
				}
				
			}
		});
		comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AES", "DES", "Caesar Cipher", "XOR", "Vigenere Cipher"}));
		comboBox.setToolTipText("Choose one....");
		comboBox.setBounds(38, 172, 222, 34);
		frame.getContentPane().add(comboBox);
		
		passwordField = new JPasswordField(col);
		passwordField.setBounds(261, 269, 425, 34);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField(col);
		passwordField_1.setBounds(261, 332, 425, 34);
		frame.getContentPane().add(passwordField_1);
		
		JButton btnNewButton_1 = new JButton("Encrypt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean pro;
				
				if((check() && checkpass()) || flag )
				{	
					try {
					
					System.out.println(comboBox.getSelectedIndex());
					System.out.println(comboBox_1.getSelectedIndex());
					
					char [] pass = passwordField.getPassword();
					
					String passstring = new String(pass);
					System.out.println(passstring);
					System.out.println(passwordField.getPassword().length);
					
					if(comboBox.getSelectedIndex()==0)
						{
						AES a = new AES(passstring);
						des=a.encrypt(src);
						}
					else if (comboBox.getSelectedIndex()==1) 
					{
						DES d = new DES(passstring);
						des=d.encrypt(src);
					}
					else if (comboBox.getSelectedIndex()==2)
					{
						CaesarCipher c = new CaesarCipher();
						des=c.encrypt(src, ceaserkey);
					}
					else if (comboBox.getSelectedIndex()==3)
					{
						XOR x = new XOR();
						des = x.EncryptDecrypt(src,pass);
					}
					else if (comboBox.getSelectedIndex()==4)
					{
						VigenereCipher v = new VigenereCipher();
						des = v.encrypt(src, passstring);
					}
					
					String filename = fileChooser.getSelectedFile().getName().replaceFirst("[.][^.]+$", ".enc");
					fout = new FileOutputStream(fileChooser.getSelectedFile().getParent()+ "\\" +filename);
					byte[] contentInBytes = des.getBytes();
					fout.write(contentInBytes);
					fout.flush();
					fout.close();
					
					progress();
					
					pro = true;
					
					}
					catch (InvalidKeyException i)
					{
					JOptionPane.showMessageDialog(frame, "Invalid Key Length");
					pro = false;
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
						pro = false;
					}
				}
				else
				{
					pro = false;
				}
				if(pro)
					JOptionPane.showMessageDialog(frame, "File Sucessfully Decrypted","Sucess",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(358, 389, 176, 51);
		frame.getContentPane().add(btnNewButton_1);
		
	    progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(38, 473, 776, 34);
		frame.getContentPane().add(progressBar);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				i=comboBox_1.getSelectedIndex();
				col=8*(i+1);
			}
		});
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"8", "16", "24", "32", "64"}));
		comboBox_1.setBounds(644, 170, 97, 34);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblEncryptor = new JLabel("Encryptor");
		lblEncryptor.setForeground(Color.WHITE);
		lblEncryptor.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblEncryptor.setBounds(371, 0, 129, 34);
		frame.getContentPane().add(lblEncryptor);
		
		JLabel lblPasswordLength = new JLabel("Password Length:-");
		lblPasswordLength.setForeground(Color.ORANGE);
		lblPasswordLength.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblPasswordLength.setBounds(417, 170, 215, 34);
		frame.getContentPane().add(lblPasswordLength);
		
		JLabel lblChooseEncryptionMethod = new JLabel("Choose Encryption Method:-");
		lblChooseEncryptionMethod.setForeground(Color.ORANGE);
		lblChooseEncryptionMethod.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblChooseEncryptionMethod.setBounds(38, 125, 334, 34);
		frame.getContentPane().add(lblChooseEncryptionMethod);
		
		JLabel lblCreatePassword = new JLabel("Password:-");
		lblCreatePassword.setForeground(Color.ORANGE);
		lblCreatePassword.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblCreatePassword.setBounds(27, 265, 222, 34);
		frame.getContentPane().add(lblCreatePassword);
		
		JLabel lblRetypePassword = new JLabel("Confirm Password:-");
		lblRetypePassword.setForeground(Color.ORANGE);
		lblRetypePassword.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblRetypePassword.setBounds(27, 332, 222, 34);
		frame.getContentPane().add(lblRetypePassword);
		
		JLabel lblNewLabel_2 = new JLabel("File:-");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel_2.setBounds(38, 60, 89, 34);
		frame.getContentPane().add(lblNewLabel_2);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 844, 536);
		label.setIcon(new ImageIcon(Encryptor.class.getResource("/img/bg2.jpg")));
		frame.getContentPane().add(label);
		
		fileChooser = new JFileChooser();
		fileChooser.setBounds(93, 203, -64, 51);
		frame.getContentPane().add(fileChooser);
		}
	
}
