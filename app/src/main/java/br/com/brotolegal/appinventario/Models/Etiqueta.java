package br.com.brotolegal.appinventario.Models;

/**
 * Created by Falconi on 14/11/2017.
 */

public class Etiqueta {

    private String etiqueta;
    private String contagem;
    private String codpro;
    private String armazen;


    public Etiqueta() {    }

    public Etiqueta(String etiqueta, String contagem, String codpro, String armazen) {
        this.etiqueta = etiqueta;
        this.contagem = contagem;
        this.codpro = codpro;
        this.armazen = armazen;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getContagem() {
        return contagem;
    }

    public void setContagem(String contagem) {
        this.contagem = contagem;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public String getArmazen() {
        return armazen;
    }

    public void setArmazen(String armazen) {
        this.armazen = armazen;
    }
}
