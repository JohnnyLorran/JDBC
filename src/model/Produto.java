package model;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;

    private Integer categoria_id;


    public Produto(String nome, String descricao){
        super();
        this.nome = nome;
        this.descricao = descricao;

    }

    public Produto(Integer id,String nome, String descricao){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public Integer getId() {
        return id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    @Override
    public String toString(){
        return String.format("O produto é : %d, %s, %s" , this.id, this.nome, this.descricao);
    }
}
