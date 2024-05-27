package DAOs;

import java.sql.*;

import Application.DBPrincipal;
import Helpers.MessageHelpers;
import Models.UsuarioModel;

public class UsuarioDAO {
	
	private Connection con;	
	
	public UsuarioDAO(){
		try {
			con = DBPrincipal.retornaConexao();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	public void InserirUsuario(UsuarioModel usuario) 
	{
		try 
		{
			PreparedStatement pstmt = null;			
			
			pstmt = con.prepareStatement("insert into usuario(username, senha) values (?, ?)");
			
			pstmt.setString(1, usuario.getUsername());
			pstmt.setString(2, usuario.getSenha());

			pstmt.executeUpdate();
			
			MessageHelpers.MostraMensagem("Usuario Cadastrado");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}							
	}
	
	public UsuarioModel ProcuraUsuario(UsuarioModel usuario) 
	{
		try {

			PreparedStatement ptsmt = null;
			ResultSet rs = null;
						
			ptsmt = con.prepareStatement("SELECT Usuario.Id , Usuario.Username, Usuario.Senha "
						+ "				  FROM Usuario"
						+ "				  WHERE  Usuario.Username = ? AND Usuario.Senha = ? ");			

			ptsmt.setString(1, usuario.getUsername());
			ptsmt.setString(2, usuario.getSenha());
			
			
			rs = ptsmt.executeQuery();
			
			UsuarioModel retorno = null;
			
			while (rs.next()) 
			{			
				retorno = new UsuarioModel( rs.getInt("Id"),rs.getString("Username"), rs.getString("Senha"));				
				break;
			}
			
			return retorno;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 						
	}

}
