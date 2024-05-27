package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Application.DBPrincipal;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.GeneroModel;
import Models.MusicaModel;

public class ArtistaDAO {
	
	private Connection con;	
	
	public ArtistaDAO(){
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
	
	
	public void InserirArtista(ArtistaModel artista) 
	{
		try 
		{
			PreparedStatement pstmt = null;			
			
			pstmt = con.prepareStatement("insert into artista(nome, caminhoImagem, descricao) values (?, ?, ?)");
			
			pstmt.setString(1, artista.getNome());
			pstmt.setString(2, artista.getCaminho());
			pstmt.setString(3, artista.getDescricao());

			pstmt.executeUpdate();
			
			MessageHelpers.MostraMensagem("Artista Cadastrado");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}							
	}
	
	public void AtualizaArtista(ArtistaModel artista) 
	{
		try 
		{
			PreparedStatement pstmt = null;			
			
			pstmt = con.prepareStatement(" update artista set nome = ?, caminhoImagem = ?, descricao = ? where id = ?");
			
			pstmt.setString(1, artista.getNome());
			pstmt.setString(2, artista.getCaminho());
			pstmt.setString(3, artista.getDescricao());
			pstmt.setInt(4, artista.getId());

			pstmt.executeUpdate();
			
			MessageHelpers.MostraMensagem("Artista Editado");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}							
	}
	
	public ArtistaModel ProcuraArtista(ArtistaModel artista) 
	{
		try {

			PreparedStatement ptsmt = null;
			ResultSet rs = null;
						
			ptsmt = con.prepareStatement("SELECT Artista.Id , Artista.Nome, Artista.caminhoImagem, Artista.Descricao "
						+ "				  FROM Artista"
						+ "				  WHERE  Artista.Nome = ?");			

			ptsmt.setString(1, artista.getNome());
			ptsmt.setString(2, artista.getCaminho());
			ptsmt.setString(3, artista.getDescricao());

			rs = ptsmt.executeQuery();
			
			ArtistaModel retorno = null;
			
			while (rs.next()) 
			{			
				retorno = new ArtistaModel( rs.getInt("Id"),rs.getString("Nome"), rs.getString("caminhoImagem"), rs.getString("Descricao"));
				break;
			}
			
			return retorno;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 						
	}
	
	public List<ArtistaModel> ProcuraTodosArtistas()
	{
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		
		try {
			ptsmt = con.prepareStatement(" SELECT Artista.Id, Artista.Nome, Artista.CaminhoImagem, Artista.Descricao "
					+ "					   FROM Artista ");
			
			
			rs = ptsmt.executeQuery();
			
			List<ArtistaModel> retorno = new ArrayList();
			
			while(rs.next()) 
			{
				retorno.add(new ArtistaModel(rs.getInt("Id"), rs.getString("Nome"), rs.getString("CaminhoImagem"), rs.getString("Descricao")));							
			}
			
			return retorno;						
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}				
	}
	
	public void DeletaArtista(int id) {
		
		PreparedStatement pstmt = null;
		
		try 
		{
			//Deleta as relações das musicas
			
			pstmt = con.prepareStatement(" DELETE FROM MusicaPlaylist WHERE Musicaid IN (SELECT id FROM musica WHERE artistaid = ?) ");					
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			//Deleta a musica
			pstmt = con.prepareStatement(" DELETE FROM Musica WHERE Musica.ArtistaId = ? ");					
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			//Deleta o artista
			
			pstmt = con.prepareStatement(" DELETE FROM Artista WHERE Id = ? ");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public ArrayList<MusicaModel> ProcuraMusicasArtista(ArtistaModel artista){
		try 
		{
			ArrayList<MusicaModel> retorno = new ArrayList<MusicaModel>();
			
			PreparedStatement pstmt = null;
			
			ResultSet rs = null;
			
			pstmt = con.prepareStatement(" SELECT  musica.id, musica.nome, musica.caminhomusica, musica.descricao, genero.id AS Genero_Id, genero.nome AS Genero_Nome, artista.id AS Artista_Id"
									+ "    FROM musica "
									+ "    LEFT JOIN genero on genero.id = musica.generoid "
									+ "    LEFT JOIN artista on artista.id = musica.artistaid "
									+ "	   WHERE artista.Id = ? ");
			
			pstmt.setInt(1, artista.getId());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GeneroModel genero = new GeneroModel(rs.getInt("Genero_Id"), rs.getString("Genero_Nome"));							
				
				retorno.add(new MusicaModel(rs.getInt("id"), rs.getString("nome"), rs.getString("caminhomusica"), rs.getString("descricao"), artista, genero, false));
			}
			
			return retorno;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
