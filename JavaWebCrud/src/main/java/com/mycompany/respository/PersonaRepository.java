/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.respository;

import com.mycompany.servlet.SvPersona;
import com.mycompany.intefaces.IRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.models.Persona;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ricardotorres
 */
public class PersonaRepository implements IRepository<Persona, Integer> {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(PersonaRepository.class.getName());

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

        String sql = "insert into \"Persona\" (nombre,edad,correo,status)"
                + "values(?,?,?,'Activo')";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setString(3, persona.getCorreo());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Persona persona) throws SQLException {
        String sql = "update \"Persona\" set nombre =?,edad =?,correo =? where id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setString(3, persona.getCorreo());
            ps.setInt(4, persona.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Persona> getAll() throws SQLException {

        String sql = "select * from \"Persona\" WHERE status<>'Eliminado' order by id desc";
        var listaPersona = new ArrayList<Persona>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                var persona = new Persona();
                persona.setCorreo(rs.getString("correo"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEdad(rs.getInt("edad"));
                persona.setId(rs.getInt("id"));
                listaPersona.add(persona);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersona;
    }

    @Override
    public Persona getById(Integer id) throws SQLException {
        String sql = "select * from \"Persona\" where id=?";

        var persona = new Persona();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                persona.setCorreo(rs.getString("correo"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEdad(rs.getInt("edad"));
                persona.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "update \"Persona\" set status='Eliminado'where id=? and status='Activo'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            boolean isResultSet = ps.execute();
            if (isResultSet) {
                // Si es un ResultSet (no debería serlo en este caso, ya que es un UPDATE)
                try (ResultSet rs = ps.getResultSet()) {
                    logger.warning("La consulta devolvió un ResultSet, pero se esperaba una actualización.");
                }
            } else {
                // Si no es un ResultSet, obtener el número de filas afectadas
                int rowsAffected = ps.getUpdateCount();

                // Registrar el resultado en el log
                if (rowsAffected > 0) {
                    logger.info("Se actualizó el estado de la persona con ID " + id + " a 'Eliminado'. Filas afectadas: " + rowsAffected);
                } else {
                    logger.info("No se encontró ninguna persona con ID " + id + " y estado 'Activo'.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
