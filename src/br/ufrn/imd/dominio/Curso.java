package br.ufrn.imd.dominio;

/*
 * Classes that represents courses. Example: BTI, Music, ECT;
 */
public class Curso {
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
