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
 * @since 13/10/2025
 */
public class ItemPedidoDAO {
    private Conexão conexao;
    private Connection con;

    public ItemPedidoDAO() {
        this.conexao = new Conexão();
        this.con = this.conexao.getConexao();
    }
    
    public void inserirItemPedido(ItemPedido i){
        String sql = "INSERT INTO itempedido (pro_id, itm_quantidade, itm_valorUnitario, ped_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setInt(1, i.getProdutoId());
            stmt.setInt(2, i.getQuantidade());
            stmt.setFloat(3, i.getValorUnitario());
            stmt.setInt(4, i.getPedidoId());
            stmt.execute();
            System.out.println("Item do pedido cadastrado com sucesso!!!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir item do pedido: "+e.getMessage());
        }
    }
    
    public List<ItemPedido> getItemPedido(){
        String sql = "SELECT * FROM itempedido";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<ItemPedido> listaItemPedidos = new ArrayList<>();
            while(rs.next()){
                ItemPedido i = new ItemPedido();
                i.setId(rs.getInt("itm_id"));
                i.setProdutoId(rs.getInt("pro_id"));
                i.setQuantidade(rs.getInt("itm_quantidade"));
                i.setValorUnitario(rs.getFloat("itm_valorUnitario"));
                i.setPedidoId(rs.getInt("ped_id"));
                listaItemPedidos.add(i);
            }
            return listaItemPedidos;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Itens dos pedidos: "+e.getMessage());
            return null;
        }
    }
}
