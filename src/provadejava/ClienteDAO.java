/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provadejava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class ClienteDAO {
    private Conexão conexao;
    private Connection con;
    
    public ClienteDAO(){
        this.conexao = new Conexão();
        this.con = this.conexao.getConexao();
    }
    
    public void inserirCliente(Cliente cli){
        String sql = "INSERT INTO cliente (cli_nome, cli_email, cli_tel) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getEmail());
            stmt.setString(3, cli.getTelefone());
            
            stmt.execute();
            System.out.println("Cliente cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar cliente: "+e.getMessage());
        }
    }
    public List<Cliente> getCliente(){
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listaClientes = new ArrayList();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("cli_id"));
                c.setNome(rs.getString("cli_nome"));
                c.setEmail(rs.getString("cli_email"));
                c.setTelefone(rs.getString("cli_tel"));
                listaClientes.add(c);
            }
            return listaClientes;
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todas as pessoas: "+e.getMessage());
            return null;
        }
    }
}
