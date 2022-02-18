import java.beans.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;



import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



import java.util.ArrayList;
/**
* Servlet implementation class GetPointServlet
*/
@WebServlet("/getPoint")
public class GetPointServlet extends HttpServlet {
private static final long serialVersionUID = 1L;



/**
* @see HttpServlet#HttpServlet()
*/
public GetPointServlet() {
super();
// TODO Auto-generated constructor stub
}



/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
* response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	String tenpo_Id = request.getParameter("TENPO_ID");
	String user_Id = request.getParameter("USER_ID");
	
	final String driverName = "com.mysql.jdbc.Driver";
	final String url = "jdbc:mysql://192.168.54.190:3306/jsonkadai05";
	final String id = "jsonkadai05";
	final String pass = "JsonKadai05";
//	String user_Id = "190000@jc-21.jp";
//	String tenpo_Id = "0000000002";
	try {
		InitialContext 	ic 	= new InitialContext();	//データソースを取得する前処理
		Class.forName(driverName);
		Connection con = DriverManager.getConnection(url, id, pass);

		String sql = "SELECT * FROM point_list WHERE TENPO_ID = ? AND USER_ID = ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet result = st.executeQuery();
		String[] s = new String[3];
		s[0]=result.getString("TENPO_ID");
		s[1]=result.getString("USER_ID");
		s[2]=result.getString("POINT");
		
		
		int point= 0;
		int line = 0;
		while(result.next()) {
			line++;
			point = result.getInt("Point");
		}
		
		if(line==0) {	//該当するデータがないときに登録
			sql = "INSERT INTO Point(TENPO_ID,USER_ID,Point) VALUES(?,?,500)";
			st = con.prepareStatement(sql);
			st.setString(1,tenpo_Id);
			st.setString(2,user_Id);
			point = 500;
			st.executeUpdate();
		}
		
		request.setAttribute("point", point);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp");
		rd.forward(request, response);
		
		st.close();
		con.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
