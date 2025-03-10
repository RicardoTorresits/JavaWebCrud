/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.respository;

import com.mycomopany.servlet.SvPersona;
import com.mycompany.intefaces.IRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.models.Persona;
import java.util.ArrayList;

/**
 *
 * @author ricardotorres
 */
public class  PersonaRepository implements IRepository <Persona,Integer>{
    
    private Connection conn;

    public PersonaRepository() {
    }

    public PersonaRepository(Connection conn) {
        this.conn = conn;
    }

    
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Persona persona) throws SQLException {
         
        String sql = "inset into Persona (nombre,edad,correo,stauts)"
                + "values(?,?,?,'Activo')";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setString(3, persona.getCorreo());
            ps.execute();
        }catch (SQLException ex){
             Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(Persona persona) throws SQLException {
         String sql = "update Persona set nombre =?,edad =?,correo =? where id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setString(3, persona.getCorreo());
            ps.setInt(4, persona.getId());
            ps.execute();
        }catch (SQLException ex){
             Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<Persona> getAll() throws SQLException {
        
        String sql = "select * from personas where status<>'Elimado' order by id desc";
        var listaPersona = new ArrayList<Persona>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            while(rs.next()){
                var persona = new Persona();
                persona.setCorreo(rs.getString("correo"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEdad(rs.getInt("edad"));
                listaPersona.add(persona);
            }
        }catch (SQLException ex){
             Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersona;
    }

    @Override
    public Persona getById(Integer id) throws SQLException {
        String sql = "select * from personas order where id=?";
       
        var persona = new Persona();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if(rs.next()){
                persona.setCorreo(rs.getString("correo"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEdad(rs.getInt("edad"));
            }
        }catch (SQLException ex){
             Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "update Persona set status='Eliminado'where id=? and status='Activo'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }catch (SQLException ex){
             Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
