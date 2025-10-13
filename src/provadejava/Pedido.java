/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provadejava;

import java.sql.Timestamp;

/**
 *
 * @author Arthur
 */
public class Pedido {
    private int id;
    private int idCliente;
    private int idFornecedor;
    private Timestamp data;
    private int tipoNota;

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(int tipoNota) {
        this.tipoNota = tipoNota;
    }
}
