/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rahul
 */
public class QShowServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QShowServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QShowServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       // processRequest(request, response);
            
         ArrayList<String> l = new ArrayList<String>();
                response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       /* String name = request.getParameter("name");
        String mail = request.getParameter("mail");
         String pass = request.getParameter("pass");
          String cpass = request.getParameter("cpass");*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Quora?useSSL=false", "root", "2262002");
            PreparedStatement pst = conn.prepareStatement("Select * from Question");         
             ResultSet rs =  pst.executeQuery();

             while(rs.next())
             {
               String Question = rs.getString("ques"); 
               
               l.add(Question); 
               request.setAttribute("m1", l);
               
               
                 
             }
                    RequestDispatcher rd=request.getRequestDispatcher("ShowJSP.jsp");
          rd.forward(request, response);
             
             
              }
              
                //out.println("Incorrect login credentials");
             catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
       
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  
