import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetPointServlet
 */
@WebServlet("/getTicketList")
public class GetTicketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTicketListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



final String driverName = "com.mysql.jdbc.Driver";
final String url = "jdbc:mysql://192.168.54.190:3306/jsonkadai05";
final String id = "jsonkadai05";
final String pass = "JsonKadai05";



try {
Class.forName(driverName);
Connection connection = DriverManager.getConnection(url, id, pass);
PreparedStatement st = connection.prepareStatement("select * from ticket_list ");
ResultSet result = st.executeQuery();
List<String[]> list = new ArrayList<>();

while( result.next() == true) {
String[] s = new String[4];



s[0]=result.getString("TENPO_ID");
s[1]=result.getString("TICKET_ID");
s[2]=result.getString("TICKET_NAME");
s[3]=result.getString("POINT");



list.add(s);
}



request.setAttribute("json",list);
request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp").forward(request,response);

} catch (ClassNotFoundException e ) {
e.printStackTrace();
} catch (SQLException e ) {
e.printStackTrace();
}
}

// RequestDispatcher rd =
// request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp");
// rd.forward(request, response);
	}