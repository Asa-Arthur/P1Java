package provadejava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Conexão conexao;
    private Connection con;

    public PedidoDAO() {
        this.conexao = new Conexão();
        this.con = this.conexao.getConexao();
    }
    
    public void registrarPedido(Pedido pedido){
        String sql;
        try {
            if (pedido.getTipoNota() == 1) {
                // Pedido de Cliente
                sql = "INSERT INTO pedido (cli_id, not_data, tpn_id) VALUES (?, ?, ?)";
                PreparedStatement stmt = this.con.prepareStatement(sql);
                stmt.setInt(1, pedido.getIdCliente());
                stmt.setTimestamp(2, pedido.getData());
                stmt.setInt(3, pedido.getTipoNota());
                stmt.execute();
                stmt.close();
                System.out.println("Pedido de cliente registrado com sucesso!");
            } 
            else if (pedido.getTipoNota() == 2) {
                // Pedido de Fornecedor
                sql = "INSERT INTO pedido (for_id, not_data, tpn_id) VALUES (?, ?, ?)";
                PreparedStatement stmt = this.con.prepareStatement(sql);
                stmt.setInt(1, pedido.getIdFornecedor());
                stmt.setTimestamp(2, pedido.getData());
                stmt.setInt(3, pedido.getTipoNota());
                stmt.execute();
                stmt.close();
                System.out.println("Pedido de fornecedor registrado com sucesso!");
            } 
            else {
                System.out.println("Tipo de nota inválido. Pedido não registrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao registrar pedido: " + e.getMessage());
        }
    }
    
    public List<Pedido> getPedido(){
        String sql = "SELECT * FROM pedido";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Pedido> listaPedidos = new ArrayList<>();
            while(rs.next()){
                Pedido p = new Pedido();
                p.setId(rs.getInt("ped_id"));
                p.setIdCliente(rs.getInt("cli_id"));
                p.setIdFornecedor(rs.getInt("for_id"));
                p.setData(rs.getTimestamp("ped_data"));
                p.setTipoNota(rs.getInt("tpn_id"));
                listaPedidos.add(p);
            }
            return listaPedidos;
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a busca: "+e.getMessage());
            return null;
        }
    }
}
