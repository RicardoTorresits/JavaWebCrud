/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycomopany.servlet;

import com.mycompany.models.DbConnection;
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
 *
 * @author ricardotorres
 */
@WebServlet(name = "SvPersona", urlPatterns = {"/SvPersona"})
public class SvPersona extends HttpServlet {

    DbConnection db = DbConnection.getInstance();
    PersonaRepository personaRepository;
    PersonaService personaService;

    @Override
    public void init() throws ServletException {
        personaRepository = new PersonaRepository();
        personaService = new PersonaService();
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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String idstr = "";
        idstr = req.getParameter("id");
        try (Connection conn = db.getConnection()) {
            personaRepository.setConn(conn);
            personaService.setPersonaRepository(personaRepository);
            var httpsession = req.getSession();
            if (idstr != "") {
               var result = personaService.getPersonas();
               var personas = result.getData();
               httpsession.setAttribute("ListaPersona", personas);
               response.sendRedirect("listaPersona.jsp");
            }else {
                var result = personaService.getPersona(Integer.valueOf(idstr));
                var persona = result.getData();
                httpsession.setAttribute("persona", persona);
                response.sendRedirect("Persona.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SvPersona.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
