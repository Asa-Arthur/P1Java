/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provadejava;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Arthur
 */
public class Conexão {
    public Connection getConexao(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdprovajava?useTimezone=true&serverTimezone=UTC","root","root");
            System.out.println("Conectado com Sucesso!!!");
            return con;
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados: "+e.getMessage());
            return null;
        }
    }
}
