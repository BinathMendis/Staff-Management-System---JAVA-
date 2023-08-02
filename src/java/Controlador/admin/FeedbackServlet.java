package Controlador.admin;

import Controlador.staff.*;
import Models.Feedback;
import ModeloDAO.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FeedbackServlet", urlPatterns = {"/FeedbackServlet"})
public class FeedbackServlet extends HttpServlet {
    
    // Get url patterns to variables
    String list="admin/feedback/list.jsp";
    String add="admin/feedback/add.jsp";
    String edit="admin/feedback/edit.jsp";    
    
    Feedback st=new Feedback();
    FeedbackDAO dao=new FeedbackDAO();
    int id;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StaffServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="";
        String action=request.getParameter("action");
        
        if(action.equalsIgnoreCase("list")){
            url=list;            
        }else if(action.equalsIgnoreCase("add")){
            url=add;
        }else if(action.equalsIgnoreCase("addrow")){
            String member_code=request.getParameter("txtMemberCode");
            String feedback=request.getParameter("txtFeedback");
            
            st.setMemberCode(member_code);
            st.setFeedback(feedback);
           
            dao.add(st);
            url=list;
        }else if(action.equalsIgnoreCase("edit")){
            request.setAttribute("feedbackId",request.getParameter("id"));
            url=edit;
        }else if(action.equalsIgnoreCase("editrow")){
            id=Integer.parseInt(request.getParameter("txtFeedbackId"));
            String member_code=request.getParameter("txtMemberCode");
            String feedback=request.getParameter("txtFeedback");
            
            st.setFeedbackId(id);
            st.setMemberCode(member_code);
            st.setFeedback(feedback);
            dao.edit(st);
            url=list;
        }else if(action.equalsIgnoreCase("delete")){
            id=Integer.parseInt(request.getParameter("id"));
            st.setFeedbackId(id);
            dao.delete(id);
            url=list;
        }
        
        RequestDispatcher event=request.getRequestDispatcher(url);
        event.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}