package cotemig.com.br.expressomovel.Entidades;

import java.io.Serializable;

/**
 * Created by pedro on 08/06/16.
 */
public class Usuario implements Serializable{

    public Long idUsuario;

    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private String telefone;
    private String email;

    private String tipoPerfil;
    private String tipoVeiculo;
    private String placa;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getTipoVeiculo() { return tipoVeiculo; }

    public void setTipoVeiculo(String tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }

    public String getPlaca() { return placa; }

    public void setPlaca(String placa) { this.placa = placa; }
}
