import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Ticket;



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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		final String driverName = "com.mysql.jdbc.Driver";
		final String url = "jdbc:mysql://192.168.54.190:3306/jsonkadai05";
		final String id = "jsonkadai05";
		final String pass = "JsonKadai05";
		try {
		InitialContext ic = new InitialContext();
		Class.forName(driverName);
		Connection con = DriverManager.getConnection(url,id,pass);//コネクションを取得
		String user_Id = request.getParameter("USER_ID");
		String tenpo_Id = request.getParameter("TENPO_ID");
		String sql1 = "SELECT * FROM ticket_list WHERE TENPO_ID = ? AND USER_ID = ?";
		PreparedStatement st = con.prepareStatement(sql1);
		ResultSet rsPoint = st.executeQuery();
		String[] s = new String[4];
		s[0]=rsPoint.getString("TENPO_ID");
		s[1]=rsPoint.getString("TICKET_ID");
		s[2]=rsPoint.getString("TICKET_NAME");
		s[3]=rsPoint.getString("POINT");
		int userPoint= 0;
		while(rsPoint.next()) {
		userPoint = rsPoint.getInt("Point");
		}
		/****************************/
		String sql2 = "SELECT TENPO_ID,TICKET_ID,TICKET_NAME,Point FROM ticket WHERE TENPO_ID = ? AND point <= ?";
		st = con.prepareStatement(sql2);
		st.setString(1, tenpo_Id);
		st.setInt(2, userPoint);
		ResultSet rsTicket = st.executeQuery();
		List<Ticket> list = new ArrayList<Ticket>();
		while(rsTicket.next()) {
		int TeId = rsTicket.getInt("TENPO_ID");
		int TiId = rsTicket.getInt("TICKET_ID");
		String optName= rsTicket.getString("TICKET_NAME");
		int point = rsTicket.getInt("Point");
		list.add(new Ticket(TeId,TiId,optName,point));
		}
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/getTicketList.jsp");
		rd.forward(request, response);
		st.close();
		con.close();
		}catch(Exception e) {
		e.printStackTrace();
		}
		}
}
