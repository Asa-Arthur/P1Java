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
public class FornecedorDAO {
    private Conexão conexao;
    private Connection con;

    public FornecedorDAO() {
        this.conexao = new Conexão();
        this.con = this.conexao.getConexao();
    }
    
    public void inserirFornecedor(Fornecedor fornecedor){
        String sql = "INSERT INTO fornecedor (for_nome, for_nomeFantasia, for_cnpj, for_endereco, for_email, for_tel) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getNomeFantasia());
            stmt.setString(3, fornecedor.getCNPJ());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getEmail());
            stmt.setString(6, fornecedor.getTelefone());
            
            stmt.execute();
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar fornecedor: "+e.getMessage());
        }
    }
    
    public List<Fornecedor> getFornecedor(){
        String sql = "SELECT * FROM fornecedor";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Fornecedor> listaFornecedor = new ArrayList();
            while(rs.next()){
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("for_id"));
                f.setNome(rs.getString("for_nome"));
                f.setNomeFantasia(rs.getString("for_nomeFantasia"));
                f.setCNPJ(rs.getString("for_cnpj"));
                f.setEndereco(rs.getString("for_endereco"));
                f.setEmail(rs.getString("for_email"));
                f.setTelefone(rs.getString("for_tel"));
                listaFornecedor.add(f);
            }
            return listaFornecedor;
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todas as pessoas: "+e.getMessage());
            return null;
        }
    }
}
