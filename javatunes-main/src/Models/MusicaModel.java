package Models;

public class MusicaModel {

	private int id;
	private int artistaid;
	private ArtistaModel artista;
	private int generoid;
	private GeneroModel genero;
	private String nome;
	private String caminho;
	private String descricao;
	private boolean curtida;			
	
	public MusicaModel(String caminho) {
		this.caminho = caminho;
	}
	
	public MusicaModel(String nome, String caminho, String descricao, ArtistaModel artista, GeneroModel genero, boolean curtida) {		
		setNome(nome);
		setCaminho(caminho);
		setDescricao(descricao);
		setArtista(artista);
		setGenero(genero);
		setCurtida(curtida);
	}
	
	public MusicaModel(int id, String nome, String caminho, String descricao, ArtistaModel artista, GeneroModel genero, boolean curtida) {		
		setId(id);
		setNome(nome);
		setCaminho(caminho);
		setDescricao(descricao);
		setArtista(artista);
		setGenero(genero);
		setCurtida(curtida);
	}
	
	public MusicaModel(int id, String nome, String caminho, String descricao, int artistaid, int generoid, boolean curtida) {
		setId(id);
		setNome(nome);
		setCaminho(caminho);
		setDescricao(descricao);
		setArtistaid(artistaid);
		setGeneroid(generoid);
		setCurtida(curtida);
	}

	public MusicaModel(String nome, String caminho, String descricao, int artistaid, int generoid, boolean curtida) {
		setNome(nome);
		setCaminho(caminho);
		setDescricao(descricao);
		setArtistaid(artistaid);
		setGeneroid(generoid);
		setCurtida(curtida);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ArtistaModel getArtista() {
		return this.artista;
	}
	public void setArtista(ArtistaModel artista) {
		this.artista = artista;
	}
	
	public GeneroModel getGenero() {
		return this.genero;
	}
	public void setGenero(GeneroModel genero) {
		this.genero = genero;
	}
	
	public int getArtistaid() {
		return artistaid;
	}
	public void setArtistaid(int artistaid) {
		this.artistaid = artistaid;
	}
	public int getGeneroid() {
		return generoid;
	}
	public void setGeneroid(int generoid) {
		this.generoid = generoid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isCurtida() {
		return curtida;
	}
	public void setCurtida(boolean curtida) {
		this.curtida = curtida;
	}

}