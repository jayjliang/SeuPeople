package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextArea;

import SQLoperation.mysqlOpeartion;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
    private int i;
    private String result;
    String url = "jdbc:mysql://localhost:3306/seupeople";
	String user = "root";
	String pwd = "084358";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				i=1;
			}
		}).start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.getContentPane().setFont(new Font("方正舒体", Font.PLAIN, 44));
		frame.setBounds(100, 100, 450, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(160, 162, 122, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(96, 165, 54, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(96, 226, 54, 15);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 223, 122, 21);
		frame.getContentPane().add(passwordField);
		
		JButton Loginbutton = new JButton("登录");
		Loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			LoginButtonListener();
			}
		});
		Loginbutton.setBounds(112, 296, 62, 23);
		frame.getContentPane().add(Loginbutton);
		
		JButton registerButton = new JButton("注册");
		registerButton.setBounds(220, 296, 62, 23);
		frame.getContentPane().add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegistButtonListener();
			}
		});
		
		JLabel lblSeu = new JLabel("SEU");
		lblSeu.setForeground(Color.RED);
		lblSeu.setBackground(Color.RED);
		lblSeu.setFont(new Font("方正舒体", Font.PLAIN, 44));
		lblSeu.setBounds(74, 33, 100, 47);
		frame.getContentPane().add(lblSeu);
		
		JLabel lblPeople = new JLabel("People");
		lblPeople.setForeground(Color.PINK);
		lblPeople.setFont(new Font("方正舒体", Font.PLAIN, 45));
		lblPeople.setBounds(154, 80, 185, 49);
		frame.getContentPane().add(lblPeople);
	}
	
	
	
	public void LoginButtonListener() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				String pass = passwordField.getText();
				String name = textField.getText();
				mysqlOpeartion opeartion=new mysqlOpeartion(url, user, pwd);
					// int i=sql.executeUpdate("insert login values('陈',3)");
					// System.out.println(i);
				//opeartion.Connect();
					String queryString = "select password from login WHERE username="
							+ name;
						String passwordString = opeartion.mysqlquery(queryString, "password");
						if (passwordString == null)
							// System.out.println("用户名错误");
							new Dialog("用户名错误");
						else if (passwordString.equals(pass)) {
							// System.out.println("登录成功");
							new Dialog("登录成功");
						} else {
							// System.out.println("密码错误");
							new Dialog("密码错误");
						}
						opeartion.close();
						System.out.println(pass);
						System.out.println(passwordString);
			}
		}).start();
	}
//		


public void RegistButtonListener(){
	String pass = passwordField.getText();
	String name = textField.getText();
	mysqlOpeartion opeartion=new mysqlOpeartion(url, user, pwd);
	opeartion.Connect();
	String insertString="insert login values("+name+","+pass+","+8+")";
	opeartion.insert(insertString);
	opeartion.close();
}
}








class Dialog{
	public Dialog(String string) {
		JOptionPane.showMessageDialog(null, string,"SeuPeople",JOptionPane.INFORMATION_MESSAGE);
	}
}

