package cotemig.com.br.expressomovel.Entidades;

import java.io.Serializable;

/**
 * Created by desenvolvedor3 on 24/05/2016.
 */
public class Item implements Serializable{
    private Long idItem;
    private String descricao;
    private String dataRetirada;
    private String dataEntrega;
    private String localRetirada;
    private String localEntrega;
    private Long idEntregador;
    private Long idCliente;
    private Double preco;

    public Item() {
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long iditem) {
        this.idItem = iditem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public Long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(Long idEntregador) {
        this.idEntregador = idEntregador;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return idItem + " - " + descricao;
    }
}
