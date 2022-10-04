package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UsuarioController;

/**
 * Servlet implementation class ServletUsuarioSumarKGDonados
 */
@WebServlet("/ServletUsuarioSumarKGDonados")
public class ServletUsuarioSumarKGDonados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioSumarKGDonados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
		UsuarioController usuario = new UsuarioController();
		
		String username = request.getParameter("username");
		double cant_donada = Double.parseDouble(request.getParameter("cant_donada"));
		
		String usuarioStr = usuario.sumarKGDonados(username, cant_donada);
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(usuarioStr);
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}