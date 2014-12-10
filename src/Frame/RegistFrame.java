package Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import SQLoperation.mysqlOpeartion;

public class RegistFrame {
	private JFrame registFrame;
	private JTextField textField_Name;
	private JTextField textField_R;
	private JPasswordField passwordFieldR;

	// 构造注册窗口
	public RegistFrame() {
		registFrame = new JFrame();
		registFrame.getContentPane().setBackground(Color.CYAN);
		registFrame.getContentPane().setFont(new Font("方正舒体", Font.PLAIN, 44));
		registFrame.setBounds(100, 100, 450, 409);
		registFrame.getContentPane().setLayout(null);
		registFrame.setLocation(200, 200);
		registFrame.setTitle("SEU People 注册");
		registFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		textField_Name = new JTextField();
		textField_Name.setBounds(160, 101, 122, 21);
		registFrame.getContentPane().add(textField_Name);
		textField_Name.setColumns(10);

		textField_R = new JTextField();
		textField_R.setBounds(160, 162, 122, 21);
		registFrame.getContentPane().add(textField_R);
		textField_R.setColumns(10);

		JLabel labelName = new JLabel("昵  称");
		labelName.setBounds(96, 104, 54, 15);
		registFrame.getContentPane().add(labelName);

		JLabel labelID = new JLabel("账 号");
		labelID.setBounds(96, 165, 54, 15);
		registFrame.getContentPane().add(labelID);

		JLabel label_key = new JLabel("密码");
		label_key.setBounds(96, 226, 54, 15);
		registFrame.getContentPane().add(label_key);

		passwordFieldR = new JPasswordField();
		passwordFieldR.setBounds(160, 223, 122, 21);
		registFrame.getContentPane().add(passwordFieldR);

		JButton Registbutton_R = new JButton("注册");
		Registbutton_R.setBounds(112, 296, 62, 23);
		registFrame.getContentPane().add(Registbutton_R);
		
//注册按钮响应事件
		Registbutton_R.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Registbutton_RListener();
			}
		});

		JButton ExitButton = new JButton("退出");
		ExitButton.setBounds(220, 296, 62, 23);
		registFrame.getContentPane().add(ExitButton);
//退出按钮响应事件
		ExitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				registFrame.dispose();
				// TODO 自动生成的方法存根

			}

		});

		JLabel lblSeu_R = new JLabel("SEU People");
		lblSeu_R.setForeground(Color.RED);
		lblSeu_R.setBackground(Color.RED);
		lblSeu_R.setFont(new Font("方正舒体", Font.PLAIN, 30));
		lblSeu_R.setBounds(74, 20, 200, 47);
		registFrame.getContentPane().add(lblSeu_R);

		JLabel lblSeu_R2 = new JLabel("Regist");
		lblSeu_R2.setForeground(Color.PINK);
		lblSeu_R2.setBackground(Color.PINK);
		lblSeu_R2.setFont(new Font("方正舒体", Font.PLAIN, 25));
		lblSeu_R2.setBounds(260, 30, 100, 47);
		registFrame.getContentPane().add(lblSeu_R2);

		registFrame.setVisible(true);

	}

	//注册按钮事件函数
	public void Registbutton_RListener() {
		boolean regist;
		String name_R = textField_Name.getText();
		String password_R = passwordFieldR.getText();
		String ID_R = textField_R.getText();

		//判断输入是否为空
		if (name_R.equals(""))
			new Dialog("昵称不能为空!");
		else if (ID_R.equals(""))
			new Dialog("账号不能为空");
		else if (password_R.equals(""))
			new Dialog("密码不能为空");

		else {
			mysqlOpeartion operation = new mysqlOpeartion(LoginFrame.url,
					LoginFrame.user, LoginFrame.pwd);
			operation.Connect();
			
			//检查数据库中ID是否已经存在
			boolean judge = checkRegist(ID_R, operation);
			if (!judge)
			{
				new Dialog("账号已存在,请重新填写!");
				operation.close();
			}

			//注册数据帐户到数据库中
			else {
				String registSql = String
						.format("insert into login (username,id,password) values ('%s','%s','%s')",
								name_R, password_R, ID_R);
				regist = operation.insert(registSql);
				if (regist)
					new Dialog("恭喜您注册成功，赶紧登陆吧！");
				
				operation.close();
				registFrame.dispose();
			}

			
		}

	}

	//检查ID是否已经存在的函数
	public boolean checkRegist(String cID, mysqlOpeartion op) {
		String checkSql = "select id from login";
		ResultSet resultCheck = op.mysqlquery(checkSql);
		try {
			while (resultCheck.next()) {
				if (resultCheck.getString(1).equals(cID))
					return false;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
