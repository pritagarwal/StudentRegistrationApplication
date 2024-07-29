package in.ineuron.dao;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

import java.sql.*;
import java.io.*;

public class StudentDaoImpl implements IStudentDao  {
	
	Connection con =null;
	PreparedStatement pstat = null;
	ResultSet res=null;

	@Override
	public String addStudent(Student std) {
		int rowCount=0;
		String sql = "insert into student(name,age,address) values(?,?,?)";
		try 
		{
			con = JdbcUtil.getJdbcConnection();
			if(con!=null)
				pstat = con.prepareStatement(sql);
			if(pstat!=null)
			{
				pstat.setString(1, std.getSname());
				pstat.setInt(2, std.getSage());
				pstat.setString(3,std.getSaddress());
				rowCount = pstat.executeUpdate();
			}
			if(rowCount>0)
				return "Success";

		}
		catch(SQLException |IOException e)
		{
			e.printStackTrace();
		}
		return "failed";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String sqlSelectQuery = "select id,name,age,address from student where id = ?";
		Student student = null;

		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstat = con.prepareStatement(sqlSelectQuery);

			if (pstat != null)
				pstat.setInt(1, sid);

			if (pstat != null) {
				res = pstat.executeQuery();
			}

			if (res != null) {

				if (res.next()) {
					student = new Student();

					// copy resultSet data to student object
					student.setSid(res.getInt(1));
					student.setSname(res.getString(2));
					student.setSage(res.getInt(3));
					student.setSaddress(res.getString(4));

					return student;
				}

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return student;
	}


	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "delete from student where id = ?";
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstat = con.prepareStatement(sqlDeleteQuery);

			if (pstat != null) {

				pstat.setInt(1, sid);

				int rowAffected = pstat.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return "failure";

	}

	@Override
	public String updateStudent(Student student) {
		String sqlUpdateQuery = "update student set name=?,age=?,address=? where id=?";
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstat = con.prepareStatement(sqlUpdateQuery);

			if (pstat != null) {

				pstat.setString(1, student.getSname());
				pstat.setInt(2, student.getSage());
				pstat.setString(3, student.getSaddress());
				pstat.setInt(4, student.getSid());

				int rowAffected = pstat.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

}
