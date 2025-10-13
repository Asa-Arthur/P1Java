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
public class ProdutoDAO {
    private Conexão conexao;
    private Connection con;

    public ProdutoDAO() {
        this.conexao = new Conexão();
        this.con = this.conexao.getConexao();
    }
    
    public void inserirProduto(Produto produto){
        String sql = "INSERT INTO produto (pro_nome, pro_desc, pro_precoVenda, pro_qtdEstoque) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            if(produto.getDescricao().equals(""))
                stmt.setString(2, null);
            else
                stmt.setString(2, produto.getDescricao());
            stmt.setFloat(3, produto.getPrecoVenda());
            stmt.setInt(4, produto.getQtdEstoque());
            
            stmt.execute();
            System.out.println("Produto cadastrado com Sucesso!!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: "+e.getMessage());
        }
    }
    
    public List<Produto> getProduto(){
        String sql = "SELECT * FROM produto";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList<>();
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("pro_id"));
                p.setNome(rs.getString("pro_nome"));
                p.setDescricao(rs.getString("pro_desc"));
                p.setPrecoVenda(rs.getFloat("pro_precoVenda"));
                p.setQtdEstoque(rs.getInt("pro_qtdEstoque"));
                listaProdutos.add(p);
            }
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produtos: "+e.getMessage());
            return null;
        }
    }
}
