package com.empresa.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.model.db.UserService;
import com.empresa.model.external.UserCommand;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Injected DAO EJB:
    @EJB 
    UserService userService;
 
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Usuarios:
    	List<UserCommand> usuarios = userService.getAllUsuarios();
    	if (usuarios == null) {
    		System.out.println("Lista de usuarios NULL, consulta servlet");
    	} else { 
    		System.out.println(usuarios.size());
    	}
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }
 
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Crea un nuevo cliente:
        String name = request.getParameter("name");
        String correo = request.getParameter("correo");
        String pwd = request.getParameter("pwd");
        if (name != null) {
        	UserCommand user = new UserCommand();
            user.setNombre(name);
            user.setCorreo(correo);
            user.setPassword(pwd);
            userService.persist(user);
        }
 
        // Display the list of guests:
        doGet(request, response);
    }
}