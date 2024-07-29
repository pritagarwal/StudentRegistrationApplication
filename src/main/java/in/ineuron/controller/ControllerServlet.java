package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doProcess(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
         
		IStudentService service=StudentServiceFactory.getStudentService();
		
		System.out.println("Request URI :: "+request.getRequestURI());
		System.out.println("Path INFO :: "+request.getPathInfo());
		
		String path = request.getPathInfo();
		if(path.endsWith("addform"))
		{
			String sname = request.getParameter("sname");
			Integer sage = Integer.parseInt(request.getParameter("sage"));
			String saddr = request.getParameter("saddr");
			
			Student std = new Student();
			std.setSname(sname);
			std.setSage(sage);
			std.setSaddress(saddr);
			String msg=null;
			msg = service.addStudent(std);
			PrintWriter out = response.getWriter();
			if(msg.equalsIgnoreCase("success"))
			{
				out.println("<h1 style='color:green; text-align:center;'>REGISTRATION SUCCESFULL</h1>");
			}
			else
			{
				out.println("<h1 style='color:green; text-align:center;'>REGISTRATION FAILED</h1>");
			}
			
			out.close();
		}
		
		if(path.endsWith("deleteform"))
		{
			
			Integer sid = Integer.parseInt(request.getParameter("sid"));
			String msg = service.deleteStudent(sid);
			PrintWriter out = response.getWriter();
			if(msg.equalsIgnoreCase("success"))
			{
				out.println("<body>");
				out.println("<h1 style='color:green;text-align:center;'>RECORD DELETED SUCCESFULLY</h1>");
				out.println("</body>");
			}
			else if(msg.equalsIgnoreCase("not found"))
			{
				out.println("<body>");
				out.println("<h1 style='color:green;text-align:center;'>RECORD NOT FOUND FOR DELETION</h1>");
				out.println("</body>");
			}
			else
			{
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>RECORD DELETION FAILED</h1>");
				out.println("</body>");
			}
			out.close();
			
		}
		
		if(path.endsWith("searchform"))
		{
			Integer sid = Integer.parseInt(request.getParameter("sid"));
			Student std = service.searchStudent(sid);
			PrintWriter out = response.getWriter();
			if(std!=null)
			{
	
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>" + std.getSid() + "</td></tr>");
				out.println("<tr><th>NAME</th><td>" + std.getSname() + "</td></tr>");
				out.println("<tr><th>AGE</th><td>" + std.getSage() + "</td></tr>");
				out.println("<tr><th>ADDRESS</th><td>" + std.getSaddress() + "</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			}
			else
			{
			out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID "+sid+"</h1>");
			}
			out.close();
		}
		
		if(path.endsWith("editform"))
		{
			Integer sid = Integer.parseInt(request.getParameter("sid"));
			Student std = service.searchStudent(sid);
			PrintWriter out = response.getWriter();
			if(std!=null)
			{
				// display student records as a form data so it is editable
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>" + std.getSid() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='" + std.getSid() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + std.getSname()
						+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + std.getSage()
						+ "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + std.getSaddress()
						+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
				
			}
			else
			{
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: "+ sid+"</h1>");
				out.println("</body>");
			}
			out.close();
		}

		if(path.endsWith("updateRecord"))
		{
			Integer sid  = Integer.parseInt(request.getParameter("sid"));
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			Integer sage = Integer.parseInt(request.getParameter("sage"));
			
			Student std = new Student();
			std.setSid(sid);
			std.setSname(sname);
			std.setSage(sage);
			std.setSaddress(saddr);
			String msg = service.updateStudent(std);
			
			PrintWriter out = response.getWriter();
			if(msg.equalsIgnoreCase("success"))
			{
				out.println("<h1 style='color:green; text-align:center;'>STUDENT RECORD UPDATED SUCCESSFULLY</h1>");
			}
			else
			{
				out.println("<h1 style='color:green; text-align:center;'>STUDENT RECORD UPDATION FAILED</h1>");
			}
             out.close();
		}
	}
}
