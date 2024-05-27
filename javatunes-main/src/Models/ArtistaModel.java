package Models;

public class ArtistaModel {

	private int id;
	private String nome;
	private String caminhoImagem;
	private String descricao;
	
	public ArtistaModel() {
		
	}
	
	public ArtistaModel(int id, String nome, String caminhoImagem, String descricao) {
		setId(id);
		setNome(nome);
		setCaminho(caminhoImagem);
		setDescricao(descricao);
	}

	public ArtistaModel(String nome, String caminhoImagem, String descricao) {
		setNome(nome);
		setCaminho(caminhoImagem);
		setDescricao(descricao);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCaminho() {
		return caminhoImagem;
	}
	public void setCaminho(String caminho) {
		this.caminhoImagem = caminho;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}