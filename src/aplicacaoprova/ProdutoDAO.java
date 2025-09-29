/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacaoprova;

import aplicacaoprova.Conexao;
import aplicacaoprova.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author 2830482411034
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection con;
    
    public ProdutoDAO(){
        this.conexao = new Conexao();
        this.con = this.conexao.getConexao();
    }
    
    public void insertProduto(Produto produto){
        String sql = "INSERT INTO produto (pro_nome, pro_desc, pro_precoVenda, pro_qtdEstoque) values (?,?,?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDesc());
            stmt.setFloat(3, produto.getPreco());
            stmt.setInt(4, produto.getQtdEstoque());
            
            stmt.execute();
            System.out.println("Produto inserido com sucesso");
        } catch (Exception e) {
        }
    }
    
}
