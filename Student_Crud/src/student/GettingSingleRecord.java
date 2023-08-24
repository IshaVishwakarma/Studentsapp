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

public class GettingSingleRecord {
	JFrame fr = new JFrame("4Achivers - Information");
	JLabel la = new JLabel("Enter Student ID");
	JTextField tb = new JTextField();
	JButton bt = new JButton("Show Record");
	PreparedStatement ps;
	JPanel pa = new JPanel();
	JLabel[] la1 = new JLabel[4];
	JLabel[] la2=new JLabel[4];
	
	
	public GettingSingleRecord() {
		fr.setSize(700,700);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
	    fr.getContentPane().setBackground(Color.DARK_GRAY);
	    //ps=Database.psselect;
	    addComponents();
	    fr.setVisible(true);
	}
	
	
	
	private void addComponents() {
		fr.setLayout(null);
		int y = 70;
		Font fo = new Font("Verdana",0,19);
		la.setBounds(20, y, 200, 30);
		la.setFont(fo);
		la.setForeground(Color.WHITE);
		fr.add(la);

		tb.setBounds(220, y, 250, 30);
		tb.setFont(fo);
		fr.add(tb);

		bt.setBounds(250, 150, 180, 40);
		bt.setBackground(Color.LIGHT_GRAY);
		fr.add(bt);
		bt.addActionListener(new GetRecordListener());
		pa.setBounds(0,200,1200,400);
		pa.setBackground(Color.DARK_GRAY);
		fr.add(pa);
		pa.setVisible(false);
		addShowPanel();
	}
	
	class GetRecordListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				dbConnection();
				ps.setString(1, tb.getText());
				ResultSet rst = ps.executeQuery();
				if(rst.next()) {
					pa.setVisible(true);
					la2[0].setText(rst.getString(2));
					la2[1].setText(rst.getString(3));
					la2[2].setText(rst.getString(4));
					la2[3].setText(rst.getString(5));
				} else {
					JOptionPane.showMessageDialog(fr, "Student Record does not exist.");
					pa.setVisible(false);
					
				}
			}catch(Exception ex) {
				System.out.println(ex);
			}
			
		}
	}
	
	private void addShowPanel() {
		pa.setLayout(null);
		int y = 70;
		String[] arr = {"Name","Email","Contact","Course Name"};
		Font fo = new Font("Verdana",0,19);
		for(int i =0;i<4;i++) {
			la1[i]=new JLabel("Student " +arr[i]);
			la1[i].setBounds(30, y, 300, 50);
			la1[i].setFont(fo);
			la1[i].setForeground(Color.WHITE);
			pa.add(la1[i]);
			
			la2[i]=new JLabel();
			la2[i].setBounds(300, y, 320, 40);
			la2[i].setFont(fo);
			la2[i].setForeground(Color.WHITE);
			pa.add(la2[i]);
			y+=70;
			
			
		}
	}
	
	private void dbConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishadb", "root", "Ishamysql@0902");
			String query = "select * from student_info where id=?";
			ps = con.prepareStatement(query);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	
	
	

	public static void main(String[] args) {
		new GettingSingleRecord();

	}

}
