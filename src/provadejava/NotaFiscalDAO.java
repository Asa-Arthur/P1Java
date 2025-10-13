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
public class NotaFiscalDAO {
    private Conexão conexao;
    private Connection con;

    public NotaFiscalDAO() {
        this.conexao = new Conexão();
        this.con = this.conexao.getConexao();
    }
    
    public List<NotaFiscal> gerarNotaFiscal(int i){
        String sql = ("select p.ped_id, pro.pro_nome, date(p.ped_data) as data, i.itm_quantidade, i.itm_valorUnitario, (i.itm_quantidade * i.itm_valorUnitario) as itm_valorTotal, t.tpn_nome\n" +
                        "from itempedido as i\n" +
                    "inner join produto as pro using(pro_id)\n" +
                    "inner join pedido as p using(ped_id)\n" +
                    "inner join tiponota as t on p.tpn_id = t.tpn_id\n" +
                    "where p.ped_id = ?");
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, i);
            ResultSet rs = stmt.executeQuery();
            List<NotaFiscal> listaNotaFiscal = new ArrayList<>();
            while(rs.next()){
                NotaFiscal n = new NotaFiscal();
                n.setIdPedido(rs.getInt("ped_id"));
                n.setProduto(rs.getString("pro_nome"));
                n.setData(rs.getTimestamp("data"));
                n.setQuantidade(rs.getInt("itm_quantidade"));
                n.setValorUnitario(rs.getFloat("itm_valorUnitario"));
                n.setValorTotal(rs.getFloat("itm_valorTotal"));
                n.setTipoNota(rs.getString("tpn_nome"));
                listaNotaFiscal.add(n);
            }
            return listaNotaFiscal;
        } catch (SQLException e) {
            System.out.println("Erro ao gerar nota fiscal: "+e.getMessage());
            return null;
        }
    }
}
