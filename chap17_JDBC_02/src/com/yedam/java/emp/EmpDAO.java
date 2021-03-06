package com.yedam.java.emp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//데이터 베이스와 데이터를 교환하는 기능이 있는곳
public class EmpDAO {

	//싱글톤
	private static EmpDAO empDAO = null; 
	
	private EmpDAO() {} //private로 만듦
	
	 //getinstance 실행하면 empdao가 호출이 됨,호출되는 순간에 new 연산자가 호출되면서 instance가 실행이됨
	public static EmpDAO getInstance() { //이 인스턴스를 통해서만 접속이 가능하게 함
		if(empDAO == null) {
			empDAO = new EmpDAO(); 
		}
		return empDAO; 
	}
		
	//데이터 베이스와 연결하는데 필요한 정보
	String jdbcDriver;
	String oracleUrl;
	String connectedId;
	String connectedPwd;
	
	//데이터 베이스를 사용하는데 필요한 정보 - 각 메소드에서 공통적으로 사용하는 필드 - 현재 아무 값도 안들어가있는 상태
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//메소드 실행해서 값을 넣을거임
	public void connect() { //db에 연결을 해야함
		dbConfig();
		
		try {
	//1.JDBC Driver 로딩 - 너 어떤 드라이브 쓸거임?
	Class.forName(jdbcDriver);	//oracle.jdbc.driver.OracleDriver - 윤철씨는 이거로 함 
	
	//2. DB 서버 접속
	conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC Driver 로딩 실패");
		}catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
	}
	
	private void dbConfig() { // 파일에 저장된것을 가져옴, 파일값만 바꾸면 되니까 편리함
		String resource = "config/db.properties";
		Properties properties = new Properties();
		
		try {                //class 정보를 가져오는것, 지금실행되는 클래스의 정보, 접근위치 넘겨 자원찾기, 경로를 가져옴
			String filePath = ClassLoader.getSystemClassLoader().getResource(resource).getPath();
			properties.load(new FileInputStream(filePath)); //파일 읽어들이는 방식
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		//map에 key과 value 의 형태
		jdbcDriver = properties.getProperty("driver");
		oracleUrl = properties.getProperty("url");
		connectedId = properties.getProperty("id");
		connectedPwd = properties.getProperty("password");
	}
	
	
	//4. 자원 해제
	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//3. CRUD 실행
	//1.전체 조회 - 몇개가 들어올지 모르기떄문에 lsit형태로 써야함 배열못씀
	public List<Employee> selectAll(){
		List<Employee> list = new ArrayList<>(); //여기에 정보가 저장이 되어있음
		try {
			connect(); //연결하고
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM employees ORDER BY employee_id ");
			while(rs.next()) { //값을 넘겨줄뿐 연산은 하지 않음 - 전체조횐까 while 문을 써야함
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommission(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				
				list.add(emp);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect(); //끊고!!!!!!
		}
		return list; //넘겨줘야함
	}
	//2.단건 조회
	public Employee selectOne(int employeeId) { //employeeid를 찾겠다.
		Employee emp = null; //데이터가 없으면 널이 반환되기 때문에 조회하는 조건에 대응하는 값이 있는지 없는지 확인할 수 있음
		try {
			connect();
			//string 형식이니까 + employeeid 해도 알아먹음
			String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID =" + employeeId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//단건조회는 하나만 반환하면 된까 while 문이 필요가 없음
			if(rs.next()) {
				emp = new Employee(); //값이 있을때 인스턴스를 만들어라			
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommission(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return emp;
	}
	
	//3.등록
	public void insert(Employee emp) {
		try {
			connect();
			String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmployeeId()); 
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getLastName());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhoneNumber());
			pstmt.setDate(6, emp.getHireDate());
			pstmt.setString(7, emp.getJobId()); 
			pstmt.setDouble(8, emp.getSalary());
			pstmt.setDouble(9, emp.getCommission());
			pstmt.setInt(10,emp.getManagerId());
			pstmt.setInt(11,emp.getDepartmentId());	
			
			//int를 반환한다. 숫자가 의미하는건 몇개를 업데이트 했냐
			int result = pstmt.executeUpdate(); //값이 나오게 하는것 꼭 필요함
			
			if(result >0) {
				System.out.println("등록이 완료되었습니다.");
			}else {
				System.out.println("등록되지 않았습니다.");
			}
			
			System.out.println(result + "건이 완료되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
	//4.수정
	public void update(Employee emp) {
		try {
		connect();
		String sql = "INSERT INTO employees SET salary = ? where employee_id = ?";
		pstmt = conn.prepareStatement(sql);	
		pstmt.setDouble(1, emp.getSalary());
		pstmt.setInt(2, emp.getEmployeeId()); 
		
		int result = pstmt.executeUpdate(); //값이 나오게 하는것 꼭 필요함
		
		if(result >0) {
			System.out.println("수정이 완료되었습니다.");
		}else {
			System.out.println("수정이 완료되지 않았습니다.");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//5.삭제
	public void delete(int employeeId) {
		try {
			connect();
			String sql = "DELETE FROM employees WHERE employee_id =" + employeeId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result >0 ) {
				System.out.println("정상적으로 삭제되었습니다.");
			}else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}


}
