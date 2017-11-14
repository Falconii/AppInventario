package Models;

/*
 *
 * Created by Falconi on 13/11/2017.
 *
 */

public class Lancamento {

    private String  etiqueta;
    private String  data;
    private String  codProduto;
    private String  armazem;
    private String  status;
    private String  contagem;
    private String  cor;
    private Usuario usuario;
    private String  dataHora;
    private String  inventariado;
    private Float   qtd;
    private String  descricaoProduto;

    public Lancamento(String etiqueta, String data, String codProduto, String armazem, String status, String contagem, String cor, Usuario usuario, String dataHora, String inventariado, Float qtd, String descricaoProduto) {
        this.etiqueta = etiqueta;
        this.data = data;
        this.codProduto = codProduto;
        this.armazem = armazem;
        this.status = status;
        this.contagem = contagem;
        this.cor = cor;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.inventariado = inventariado;
        this.qtd = qtd;
        this.descricaoProduto = descricaoProduto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getArmazem() {
        return armazem;
    }

    public void setArmazem(String armazem) {
        this.armazem = armazem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContagem() {
        return contagem;
    }

    public void setContagem(String contagem) {
        this.contagem = contagem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getInventariado() {
        return inventariado;
    }

    public void setInventariado(String inventariado) {
        this.inventariado = inventariado;
    }

    public Float getQtd() {
        return qtd;
    }

    public void setQtd(Float qtd) {
        this.qtd = qtd;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
}
