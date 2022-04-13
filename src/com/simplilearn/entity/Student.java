package com.simplilearn.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student_13042022")
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "student_id")
	private int student_id;

	@Column(name = "f_name")
	private String fname;

	@Column(name = "l_name")
	private String lname;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	List<PhoneNumber> phones;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_course_link", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	List<Courses> courses;

	@Embedded
	Address address;

	public String getAddress() {
		StringBuilder sb = new StringBuilder();

		if (this.address != null) {
			sb.append(this.address.getStreet() + "," + this.address.getCity() + "," + this.address.getState());
		}

		return sb.toString();
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public String getCourseNames() {
		StringBuilder sb = new StringBuilder();

		if (courses != null) {
			for (Courses course : this.courses) {
				sb.append(course.getCourseName() + ",");
			}
		}
		return sb.toString();
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<PhoneNumber> getPhones() {
		return phones;
	}

	public String getPhoneNumbers() {
		StringBuilder sb = new StringBuilder();

		if (this.phones != null) {
			for (PhoneNumber pn : this.phones) {
				sb.append(pn.getPhoneNumber() + ",");
			}
		}
		return sb.toString();
	}

	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}

}
