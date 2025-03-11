/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.models.DbConnection;
import com.mycompany.models.Persona;
import com.mycompany.respository.PersonaRepository;
import com.mycompany.service.PersonaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
/**
 *
 * @author it-seekers
 */
@WebServlet(name = "SvPersona2", urlPatterns = {"/SvPersona2"})
public class SvPersona2 extends SvPersona {

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
            out.println("<title>Servlet SvPersona2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvPersona2 at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
           response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (Connection conn = db.getConnection()) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                jsonBuilder.append(line);
            }
            String json = jsonBuilder.toString();

            // Convertir JSON a objeto Persona
            Gson gson = new Gson();
            Persona persona = gson.fromJson(json, Persona.class);
            personaRepository.setConn(conn);
            personaService.setPersonaRepository(personaRepository);
            personaService.updatePersona(persona);
            response.sendRedirect("SvPersona");

        } catch (SQLException ex) {
            Logger.getLogger(SvPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
