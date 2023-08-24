package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	public static PreparedStatement psinsert, psdelete, psupdate, psselect;

	public static void dbConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishadb", "root", "Ishamysql@0902");
			psinsert = con.prepareStatement("insert into student_info values(?,?,?,?,?)");
			psupdate = con.prepareStatement("update student_info set Name=? ,Email =?, Contact_No=? , Course_Name=? where id=?  ");
			psdelete = con.prepareStatement("delete from student_indo where id=?");
			psselect = con.prepareStatement("select * from student_info where id=?");
			System.out.println("Database connected...");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
