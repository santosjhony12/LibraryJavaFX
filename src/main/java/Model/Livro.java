package Model;

public class Livro {
    private Long id;
    private String titulo;
    private Integer ano;
    private String editora;
    public Livro(){

    }

    public Livro(String titulo, Integer ano, String editora){
        this.titulo = titulo;
        this.ano = ano;
        this.editora = editora;
    }
    public Livro(Long id, String titulo, Integer ano, String editora){
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.editora = editora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getAno() {
        return ano;
    }

    public String getEditora() {
        return editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
