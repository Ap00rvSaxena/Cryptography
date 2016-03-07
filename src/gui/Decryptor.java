package gui;

import java.io.*;

import java.security.InvalidKeyException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextField;
import javax.swing.UIManager;

import algo.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.crypto.BadPaddingException;
import javax.lang.model.type.ErrorType;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Decryptor {

	JFrame frame;
	int xmouse;
	int ymouse;
	private JTextField textField;
	private JFileChooser fileChooser;
	private JComboBox comboBox;
	File fopen;
	FileInputStream fin;
	FileOutputStream fout;
	int n=0;
	private String src;
	private String des;
	private StringBuilder builder;
	File decfile;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Decryptor window = new Decryptor();
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
	public Decryptor() {
		
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }catch (Exception e) {}
		
		initialize();
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(500, 270));
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 501, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Main.frame.setVisible(true);
			}
		});
		label_1.setBounds(0, 0, 37, 31);
		frame.getContentPane().add(label_1);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				frame.setLocation(x-xmouse, y-ymouse);
			}
		});
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xmouse = e.getX();
				ymouse = e.getY();
			}
		});
		
		JLabel label_2 = new JLabel("");
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label_2.setBounds(464, 0, 37, 31);
		frame.getContentPane().add(label_2);
		
		JLabel lblKey = new JLabel("KEY :-");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKey.setBounds(10, 171, 44, 34);
		frame.getContentPane().add(lblKey);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{   boolean pro;
				try
				{	
					char [] pass = passwordField.getPassword();
					String key = new String(pass);
					System.out.println(key);
					if(comboBox.getSelectedIndex()==0)
					{
					AES a = new AES(key);
					des=a.decrypt(src);
					}
					else if (comboBox.getSelectedIndex()==1) 
					{
						DES d = new DES(key);
						des=d.decrypt(src);
					}
					else if ( comboBox.getSelectedIndex()==2 )
						{
						int k = Integer.parseInt(key);
						System.out.println(k);
						CaesarCipher c = new CaesarCipher();
						des= c.decrypt(src,k);
						}
					else if ( comboBox.getSelectedIndex()==3 )
						{
						XOR x = new XOR();
						des = x.EncryptDecrypt(src, pass);
						}
					else if (comboBox.getSelectedIndex()==4)
					{
						VigenereCipher v = new VigenereCipher();
						des = v.decrypt(src, key);
					}
										
					String filename = fileChooser.getSelectedFile().getName().replaceFirst("[.][^.]+$", "(decrypted).txt");
					fout = new FileOutputStream(fileChooser.getSelectedFile().getParent()+ "\\" +filename);
					byte[] contentInBytes = des.getBytes();
					fout.write(contentInBytes);
					fout.flush();
					fout.close();
					
					pro = true;
				}
				catch (InvalidKeyException i)
				{
				JOptionPane.showMessageDialog(frame, "Invalid Key Length");	
				pro = false;
				}
				
				catch (BadPaddingException e2) {
					JOptionPane.showMessageDialog(frame, "Invalid Key!", "Invalid",JOptionPane.ERROR_MESSAGE);
					pro = false;
				}
				
				catch(Exception e1)
				{
					e1.printStackTrace();
					pro = false;
				}
				if(pro)
				JOptionPane.showMessageDialog(frame, "File Sucessfully Decrypted");
					
			}
		});
		btnDecrypt.setBounds(187, 218, 118, 37);
		frame.getContentPane().add(btnDecrypt);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AES", "DES", "Caesar Cipher", "XOR", "Vigenere Cipher"}));
		comboBox.setBounds(288, 117, 97, 22);
		frame.getContentPane().add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(88, 169, 356, 36);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Decryptor");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(196, 0, 109, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblEncryptionAlgo = new JLabel("Encryption ALGO:-");
		lblEncryptionAlgo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEncryptionAlgo.setBounds(88, 110, 135, 37);
		frame.getContentPane().add(lblEncryptionAlgo);
		
		JButton btnBrowse = new JButton("Browse..");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				fileChooser.showOpenDialog(null);
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				builder = new StringBuilder();
				fin = new FileInputStream(fileChooser.getSelectedFile());
				 
					while((n=fin.read())!=-1){
						 builder.append((char)n);
					    }
					src = builder.toString();
				    fin.close();
				} 
				catch (IOException i) 
				{
					i.printStackTrace();
				}
				 	
			}
		});
		btnBrowse.setBounds(182, 85, 97, 25);
		frame.getContentPane().add(btnBrowse);
		
		textField = new JTextField();
		textField.setBounds(88, 44, 356, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("File:-");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 44, 44, 41);
		frame.getContentPane().add(lblNewLabel);
		label_4.setBounds(0, 0, 385, 22);
		frame.getContentPane().add(label_4);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Decryptor.class.getResource("/img/frame2.jpg")));
		label.setBounds(0, 0, 501, 269);
		frame.getContentPane().add(label);
		
		fileChooser = new JFileChooser();
		fileChooser.setBounds(0, 228, 49, 31);
		frame.getContentPane().add(fileChooser);
	}
}
