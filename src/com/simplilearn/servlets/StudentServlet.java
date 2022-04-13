package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Address;
import com.simplilearn.entity.Courses;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/add-student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("add-student.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student s = populateStudent(request);

		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		session.save(s);
		tx.commit();

		PrintWriter out = response.getWriter();
		out.println(
				"<html><body>Records successfully inserted in DB. <a href='list-student'>Click Here</a> to view students report</body></html>");
	}

	private Student populateStudent(HttpServletRequest request) {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");

		String phone_number1 = request.getParameter("phone_1");
		String phone_type1 = request.getParameter("types1");

		String phone_number2 = request.getParameter("phone_2");
		String phone_type2 = request.getParameter("types2");

		String phone_number3 = request.getParameter("phone_3");
		String phone_type3 = request.getParameter("types3");
		
		
		String courseName1 = request.getParameter("course_1");
		String courseType1 = request.getParameter("courseType_1");
		
		String courseName2 = request.getParameter("course_2");
		String courseType2 = request.getParameter("courseType_2");
		
		String courseName3 = request.getParameter("course_3");
		String courseType3 = request.getParameter("courseType_3");
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		

		Student s = new Student();
		s.setFname(fname);
		s.setLname(lname);

		// Populate Students
		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();

		PhoneNumber phone1 = new PhoneNumber();
		phone1.setPhoneNumber(phone_number1);
		phone1.setPhoneType(phone_type1);
		phone1.setStudent(s);

		PhoneNumber phone2 = new PhoneNumber();
		phone2.setPhoneNumber(phone_number2);
		phone2.setPhoneType(phone_type2);
		phone2.setStudent(s);

		PhoneNumber phone3 = new PhoneNumber();
		phone3.setPhoneNumber(phone_number3);
		phone3.setPhoneType(phone_type3);
		phone3.setStudent(s);

		phones.add(phone1);
		phones.add(phone2);
		phones.add(phone3);
		s.setPhones(phones);
		
		
		// Populate courses
		List<Student> students = new ArrayList<>();
		students.add(s);
		
		Courses course1 = new Courses();
		course1.setCourseName(courseName1);
		course1.setCourseType(courseType1);
		course1.setStudents(students);
		
		Courses course2 = new Courses();
		course2.setCourseName(courseName2);
		course2.setCourseType(courseType2);
		course2.setStudents(students);
		
		Courses course3 = new Courses();
		course3.setCourseName(courseName3);
		course3.setCourseType(courseType3);
		course3.setStudents(students);
		
		List<Courses> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		
		s.setCourses(courses);
		
		// Populate Address details
		Address addr = new Address();
		addr.setCity(city);
		addr.setState(state);
		addr.setStreet(street);
		
		s.setAddress(addr);
		return s;
	}

}
