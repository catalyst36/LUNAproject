package vo;

public class EmployeeVO {
	
	String emp_pw;
	String emp_name;
	String emp_ph;
	String emp_id;
	String emp_no;

	
	public EmployeeVO(String empId, String empPw) {
		this.emp_id = empId;
		this.emp_pw = empPw;
	}


	public String getEmp_pw() {
		return emp_pw;
	}


	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}


	public String getEmp_name() {
		return emp_name;
	}


	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}


	public String getEmp_ph() {
		return emp_ph;
	}


	public void setEmp_ph(String emp_ph) {
		this.emp_ph = emp_ph;
	}


	public String getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}


	public String getEmp_no() {
		return emp_no;
	}


	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
	
}


