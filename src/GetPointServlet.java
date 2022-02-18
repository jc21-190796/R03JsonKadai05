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
	 *      response)
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
			PreparedStatement st = connection.prepareStatement("select POINT from point_list where TENPO_ID=? AND USER_ID=?");
			String sa = request.getParameter("TENPO_ID");
			String a =  request.getParameter("USER_ID");
			st.setString(1, sa);
			st.setString(2, a);
			ResultSet result = st.executeQuery();

			List<String[]> list = new ArrayList<>();

			if (result.next() == true) {
				String[] s = new String[1];
				s[0] = result.getString("point");
				list.add(s);
			} else {
				PreparedStatement st2 = connection
						.prepareStatement("insert into point_list(TENPO_ID,USER_ID,POINT) values(?,?,500)");
				st2.setString(1, sa);
				st2.setString(2, a);

				int x = st2.executeUpdate();

				if (x == 1) {
					System.out.println("新規追加成功");
					st.setString(1, sa);
					st.setString(2, a);
					result = st.executeQuery();
					if (result.next() == true) {
						String[] s = new String[1];
						s[0] = result.getString("point");
						list.add(s);
					}

				} else {
					System.out.println("新規追加失敗");
				}
			}

			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO 閾ｪ蜍慕函謌舌＆繧後◆ catch 繝悶Ο繝�繧ｯ
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 閾ｪ蜍慕函謌舌＆繧後◆ catch 繝悶Ο繝�繧ｯ
			e.printStackTrace();
		}
	}

}