/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.intefaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ricardotorres
 */
public interface IRepository <T,I> {
    
    void save(T Entity) throws SQLException;
    
    void update(T Entity) throws SQLException;
    
    List<T> getAll () throws SQLException;
    
    T getById (I id) throws SQLException;
    
    void delete (I id ) throws SQLException;
}
