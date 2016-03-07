package gui;

import java.awt.EventQueue;

import gui.*;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.awt.Font;

public class Main {

	static JFrame frame;
	int xmouse;
	int ymouse;
	Decryptor dec;
	Encryptor enc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 80, 80));
		frame.getContentPane().setPreferredSize(new Dimension(463, 329));
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enc = new Encryptor();
				enc.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.setBounds(27, 55, 206, 228);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dec = new Decryptor();
				dec.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		label_4.setBounds(245, 55, 209, 228);
		frame.getContentPane().add(label_4);
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setBounds(433, 0, 33, 31);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				frame.setLocation(x-xmouse, y- ymouse);
			}
		});
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xmouse = e.getX();
				ymouse = e.getY();
			}
		});
		
		JLabel lblCryptographer = new JLabel("Cryptographer");
		lblCryptographer.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblCryptographer.setForeground(Color.BLACK);
		lblCryptographer.setBounds(161, 0, 139, 31);
		frame.getContentPane().add(lblCryptographer);
		label_2.setBounds(0, 0, 466, 31);
		frame.getContentPane().add(label_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Main.class.getResource("/img/frame11.png")));
		label.setBounds(0, 0, 463, 329);
		frame.getContentPane().add(label);
		frame.setPreferredSize(new Dimension(463, 239));
		frame.setResizable(false);
		frame.setBounds(100, 100, 466, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
