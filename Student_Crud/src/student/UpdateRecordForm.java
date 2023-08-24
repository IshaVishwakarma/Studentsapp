package student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;


public class UpdateRecordForm {
	JFrame fr = new JFrame("4Achivers - Update Record");
	JLabel la = new JLabel("Enter Student Id");
	JTextField txt = new JTextField();
	JButton btn = new JButton("Get Record");
	
	JLabel[] lb = new JLabel[4];
	JTextField[] tb = new JTextField[4];
	JButton btn1=new JButton("Update Record");
	
	JPanel panel = new JPanel();
	
	PreparedStatement ps , ps1;
	
	
	public UpdateRecordForm() {
		fr.setSize(700,700);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
	    fr.getContentPane().setBackground(Color.DARK_GRAY);
	    addComponents();
	    fr.setVisible(true);
		
	}
	
	private void addComponents() {
		fr.setLayout(null);
		int y =70;
		Font fo = new Font("Verdana",0,19);
		la.setBounds(20, y, 200, 30);
		la.setFont(fo);
		la.setForeground(Color.WHITE);
		fr.add(la);
		btn.setBounds(250, 150, 180, 40);
		btn.setFont(fo);
		btn.setBackground(Color.LIGHT_GRAY);
		fr.add(btn);
		txt.setBounds(220, y, 250, 30);
		txt.setFont(fo);
		fr.add(txt);
		btn.addActionListener(new GetListener());
		panel.setBounds(0,200,1200,400);
		//panel.setBackground(Color.blue);
		panel.setBackground(Color.DARK_GRAY);
		y += 70;
		fr.add(panel);
		panel.setVisible(false);
		addeditpanel();
	}
	
	private void dbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishadb", "root", "Ishamysql@0902");
			String query="select * from student_info where id=?";
			ps=con.prepareStatement(query);
			System.out.println("Database connected");
			ps1 = con.prepareStatement("update student_info set Name=? ,Email =?, Contact_No=? , Course_Name=? where id=? ");

			
			
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	 
	class GetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
			dbConnection();
		    ps.setString(1, txt.getText());
				ResultSet rst = ps.executeQuery();
				if(rst.next()) {
					panel.setVisible(true);
					tb[0].setText(rst.getString(2));
					tb[1].setText(rst.getString(3));
					tb[2].setText(rst.getString(4));
					tb[3].setText(rst.getString(5));
					//tb[4].setText(rst.getString(5));
					}
			}catch(Exception ex) {
				
		}
	}

		
		
	}
	
	private void addeditpanel() {
		panel.setLayout(null);
		int y = 70;
		String[] arr = {"Name","Email","Contact","Course Name"};
		Font fo = new Font("Verdana",0,19);
		for(int i =0;i<4;i++) {
			lb[i]=new JLabel("Enter Student " +arr[i]);
			lb[i].setBounds(30, y, 300, 50);
			lb[i].setFont(fo);
			lb[i].setForeground(Color.WHITE);
			panel.add(lb[i]);
			tb[i]=new JTextField();
			tb[i].setBounds(300, y, 320, 40);
			tb[i].setFont(fo);
		
			panel.add(tb[i]);
			y+=70;
		}
		btn1.setBounds(200, 350, 280, 40);
		btn1.setFont(fo);
		btn1.setBackground(Color.LIGHT_GRAY);
		panel.add(btn1);
		btn1.addActionListener(new UpdateListener());
	}
	
	class UpdateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				dbConnection();
				ps1.setString(1,tb[0].getText());
				ps1.setString(2,tb[1].getText());
				ps1.setString(3,tb[2].getText());
				ps1.setString(4,tb[3].getText());
				ps1.setString(5,txt.getText());
				ps1.executeUpdate();
				JOptionPane.showMessageDialog(fr, "Record Updated Succesfully");
				panel.setVisible(true);
				
			}catch(Exception ex) {
				System.out.print(ex);
			}
	}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UpdateRecordForm();

	}

}
