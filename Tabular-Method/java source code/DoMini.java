import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

public class DoMini extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Vector<Integer> minterms = new Vector<Integer>();
		Vector<Integer> dontCare = new Vector<Integer>();
		int inputs = Integer.parseInt(request.getParameter("input"));
		String[] varsName = new String[inputs];
		
		//find minterms (terms with value one)
		for (int i = 0; i < (1 << inputs); i++) {
			String m = request.getParameter("m" + i);
			if (m.equals("1"))
				minterms.add(i);
			else if (!m.equals("0"))
				dontCare.add(i);
		}
		
		//get variables name
		try{
			for (int i=0; i<inputs; i++){
				varsName[i] = request.getParameter("v" + i);
				if (varsName[i] == null){
					varsName = new String[0];
					break;
				}
			}
		} catch (Exception e){
			varsName = new String[0];
		}
		
		// solve
		Minimizer solver = new Minimizer();
		String[] ans = solver.minimized(inputs, minterms, dontCare,
				varsName);
		String[] allMini = solver.allMini;
		
		// send response
		response.setContentType("text/html");
		RequestDispatcher view;
		request.setAttribute("mini", ans);
		request.setAttribute("allMini", allMini);
		view = request.getRequestDispatcher("minimized.jsp");
		view.forward(request, response);
	}

}
