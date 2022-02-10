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



final String driverName = "com.mysql.jdbc.Driver";
final String url = "jdbc:mysql://192.168.54.190:3306/jsonkadai05";
final String id = "jsonkadai05";
final String pass = "JsonKadai05";



try {
Class.forName(driverName);
Connection connection = DriverManager.getConnection(url, id, pass);
String TENPO_ID = request.getParameter("TENPO_ID");
String USER_ID = request.getParameter("USER_ID");

int POINT = 30;
PreparedStatement st = connection.prepareStatement("INSERT IGNORE point_list set TENPO_ID =?,USER_ID=?,POINT = ? ");
st.setString(1,TENPO_ID );
st.setString(2, USER_ID);
st.setInt(3, POINT);

st.executeUpdate();

PreparedStatement stmt = connection.prepareStatement("select POINT from point_list where TENPO_ID=? and USER_ID=?");
stmt.setString(1, TENPO_ID);
stmt.setString(2, USER_ID);



ResultSet result = stmt.executeQuery();

List<String[]> list = new ArrayList<>();

while( result.next() == true) {
	POINT = result.getInt("POINT");
	
}
System.out.println(POINT);


request.setAttribute("POINT",POINT);
request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp").forward(request,response);

} catch (ClassNotFoundException e ) {
e.printStackTrace();
} catch (SQLException e ) {
e.printStackTrace();
}
}
}
// RequestDispatcher rd =
// request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp");
// rd.forward(request, response);