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
            if (idstr != null && !idstr.isEmpty()) {
                var result = personaService.getPersona(Integer.valueOf(idstr));
                var persona = result.getData();
                httpsession.setAttribute("persona", persona);
                response.sendRedirect("Persona.jsp");
            } else {
                var result = personaService.getPersonas();
                var personas = result.getData();
                httpsession.setAttribute("ListaPersona", personas);
                response.sendRedirect("listaPersona.jsp");

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
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        try (Connection conn = db.getConnection()) {
            personaRepository.setConn(conn);
            personaService.setPersonaRepository(personaRepository);
            String nombre = req.getParameter("nombre");
            String edad = req.getParameter("edad");
            String correo = req.getParameter("correo");
            var newUser = new Persona(nombre, Integer.parseInt(edad), correo);
            personaService.createPersona(newUser);
            response.sendRedirect("SvPersona");
        } catch (SQLException ex) {
            Logger.getLogger(SvPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = db.getConnection()) {
                StringBuilder jsonBuilder = new StringBuilder();
                String line;
                while ((line = req.getReader().readLine()) != null) {
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = db.getConnection()) {
            String idstr = "";
            idstr = req.getParameter("id");
            if (idstr != null && !idstr.isEmpty()) {
                personaRepository.setConn(conn);
                personaService.setPersonaRepository(personaRepository);
                personaService.deletePersona(Integer.valueOf(idstr));
                response.sendRedirect("SvPersona");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SvPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
