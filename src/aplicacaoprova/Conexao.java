/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaoprova;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 2830482411034
 */
public class Conexao {
    public Connection getConexao(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdprovajava?useTimezone=true&serverTimezone=UTC","root","root");
            System.out.println("Conexão realizada com sucesso");
            return con;
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados: "+e.getMessage());
            return null;
        }
    }
}
