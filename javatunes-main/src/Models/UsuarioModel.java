package Models;

public class UsuarioModel {
	
	private int id;
	private String username;
	private String senha;
	
	public UsuarioModel(int id, String username, String senha) 
	{
		setId(id);
		setUsername(username);
		setSenha(senha);
	}
	
	public UsuarioModel(String username, String senha) 
	{
		setUsername(username);
		setSenha(senha);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
