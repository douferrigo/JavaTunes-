package Models;

import java.util.ArrayList;

public class PlaylistModel {
	
	private int id;
	private String nome;
	private String caminhoImagem;
	private String descricao;
	private boolean curtida;
	private ArrayList<MusicaModel> musicas;
	
	public PlaylistModel() 
	{
		musicas = new ArrayList<MusicaModel>();		
	}
	
	public PlaylistModel(String nome, String caminhoImagem, String descricao, boolean curtida) {		
		setNome(nome);
		setCaminho(caminhoImagem);
		setDescricao(descricao);
		setCurtida(curtida);
	}
	
	public PlaylistModel(int id, String nome, String caminhoImagem, String descricao, boolean curtida) {		
		setId(id);
		setNome(nome);
		setCaminho(caminhoImagem);
		setDescricao(descricao);
		setCurtida(curtida);
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

	public boolean getCurtida() {
		return curtida;
	}

	public void setCurtida(boolean curtida) {
		this.curtida = curtida;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public ArrayList<MusicaModel> getMusicas() {
		return musicas;
	}

	public void setMusicas(ArrayList<MusicaModel> musicas) {
		this.musicas = musicas;
	}		
}