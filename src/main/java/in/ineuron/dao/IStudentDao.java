package in.ineuron.dao;

import in.ineuron.dto.*;
public interface IStudentDao {
	
	//operations need to be implemented
	public String addStudent(Student std);
	public Student searchStudent(Integer sid);
	public String updateStudent(Student std);
	public String deleteStudent(Integer sid);

}
