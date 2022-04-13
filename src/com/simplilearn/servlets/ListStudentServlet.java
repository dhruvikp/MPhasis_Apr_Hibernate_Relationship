package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class ListStudentServlet
 */
@WebServlet("/list-student")
public class ListStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		List<Student> students = session.createQuery("from Student").list();
		
		out.println("<h1> Student List :- </h1>");
		out.println("<style> table,td,th { border:2px solid red; padding: 10px;}</style>");
		out.println("<table");
		out.println("<tr>");
			out.println("<th>Student Id</th>");
			out.println("<th>Student First Name</th>");
			out.println("<th>Student Last Name</th>");
			out.println("<th>Student PhoneNumbers</th>");
			out.println("<th>Student Courses</th>");
			out.println("<th>Student Address</th>");
			
		out.println("</tr>");
		
		for(Student student: students) {
			out.println("<tr>");
				out.println("<td>"+student.getStudent_id()+"</td>");
				out.println("<td>"+student.getFname()+"</td>");
				out.println("<td>"+student.getLname()+"</td>");
				out.println("<td>"+student.getPhoneNumbers()+"</td>");
				out.println("<td>"+student.getCourseNames()+"</td>");
				out.println("<td>"+student.getAddress()+"</td>");
				
			
			out.println("</tr>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
