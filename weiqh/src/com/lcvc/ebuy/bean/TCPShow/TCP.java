package com.lcvc.ebuy.bean.TCPShow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcvc.ebuy.bean.TCP.PopUp;
import com.lcvc.ebuy.bean.TCP.TCPClient;
import com.lcvc.ebuy.bean.TCP.TCPServer;


@WebServlet("/TCP")
public class TCP extends HttpServlet{
	TCPServer tcpServer = new TCPServer();
	TCPClient tcpClient = new TCPClient();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    Integer port = request.getServerPort();
		    String Local = request.getLocalAddr();
		    tcpServer.TCPServer(Local);		
		    tcpClient.TCPClient(Local);
			
			
			
		request.getRequestDispatcher("/jsp/admin/adminmanage/adminAdd.jsp").forward(request, response);
	}
}
