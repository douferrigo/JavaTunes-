package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Application.DBPrincipal;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.GeneroModel;
import Models.MusicaModel;

public class MusicaDAO {
	
	private Connection con;	
	
	public MusicaDAO(){
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
	
	public ArrayList<MusicaModel> ProcuraMusicasPorGenero(MusicaModel musica){
		try 
		{
			
			ArrayList<MusicaModel> retorno = new ArrayList<MusicaModel>();
			
			retorno.add(musica);
			
			PreparedStatement pstmt = null;
			
			ResultSet rs = null;
			
			pstmt = con.prepareStatement(" SELECT  musica.id, musica.nome, musica.caminhomusica, musica.descricao, " +
                                             "     genero.id AS Genero_Id, genero.nome AS Genero_Nome, " + 
                                          "        Artista.Id AS Artista_Id, Artista.Nome AS Artista_Nome, Artista.CaminhoImagem AS Artista_CaminhoImagem, Artista.Descricao AS Artista_Descricao "
									+ "    FROM musica "
									+ "    LEFT JOIN genero on genero.id = musica.generoid "
									+ "    LEFT JOIN artista on artista.id = musica.artistaid "
									+ "	   WHERE musica.id <> ? and musica.generoid = ? ");
			
			pstmt.setInt(1, musica.getId());
            pstmt.setInt(2, musica.getGenero().getId());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
                ArtistaModel artista = new ArtistaModel(rs.getInt("Artista_Id"), rs.getString("Artista_Nome"), rs.getString("Artista_CaminhoImagem"), rs.getString("Artista_Descricao"));

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
	
	
	public void InserirMusica(MusicaModel musica) 
	{
		try 
		{			
			PreparedStatement pstmt = null;			
			
			pstmt = con.prepareStatement("insert into musica(artistaId, generoId, nome, caminhomusica, descricao ) values (?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, musica.getArtista().getId());
			pstmt.setInt(2, musica.getGenero().getId());
			pstmt.setString(3, musica.getNome());			
			pstmt.setString(4, musica.getCaminho());
			pstmt.setString(5, musica.getDescricao());

			pstmt.executeUpdate();
			
			MessageHelpers.MostraMensagem("Musica Cadastrada");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}			
		catch(Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public MusicaModel ProcuraMusica(String nomeMusica) 
	{
		try {

			PreparedStatement ptsmt = null;
			ResultSet rs = null;
			
			ptsmt = con.prepareStatement("SELECT musica.Id , musica.Nome, musica.caminhomusica, musica.Descricao, musica.ArtistaId, musica.GeneroId, "
					+ "							 Genero.Id AS Genero_Id, Genero.Nome AS Genero_Nome,"
					+ "							 Artista.Id AS Artista_Id, Artista.Nome AS Artista_Nome, Artista.CaminhoImagem AS Artista_CaminhoImagem, Artista.Descricao AS Artista_Descricao "												 
					+ "				  	  FROM musica "
					+ "					  LEFT JOIN Genero ON Genero.Id = musica.GeneroId "
					+ "				      LEFT JOIN Artista ON Artista.Id = musica.ArtistaId "
					+ "				      WHERE Musica.Nome = ?");			

			ptsmt.setString(1, nomeMusica);
			
			rs = ptsmt.executeQuery();
					
			MusicaModel retorno = null;
			
			while(rs.next()) 
			{
				ArtistaModel artista = new ArtistaModel(rs.getInt("Artista_Id"), rs.getString("Artista_Nome"), rs.getString("Artista_CaminhoImagem"), rs.getString("Artista_Descricao"));
				
				GeneroModel genero = new GeneroModel(rs.getInt("Genero_Id"), rs.getString("Genero_Nome"));
				
				retorno = new MusicaModel(rs.getInt("Id"),rs.getString("Nome"), rs.getString("caminhomusica"), rs.getString("Descricao"), artista, genero, true);
				
				break;
			}
			
			return retorno;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 						
	}
	
	public List<MusicaModel> ProcuraTodasMusicas()
	{
		try 
		{
			PreparedStatement ptsmt = null;
			ResultSet rs = null;
			
			ptsmt = con.prepareStatement("SELECT musica.Id , musica.Nome, musica.caminhomusica, musica.Descricao, musica.ArtistaId, musica.GeneroId, "
					+ "							 Genero.Id AS Genero_Id, Genero.Nome AS Genero_Nome,"
					+ "							 Artista.Id AS Artista_Id, Artista.Nome AS Artista_Nome, Artista.CaminhoImagem AS Artista_CaminhoImagem, Artista.Descricao AS Artista_Descricao "												 
					+ "				  	  FROM musica "
					+ "					  LEFT JOIN Genero ON Genero.Id = musica.GeneroId "
					+ "				      LEFT JOIN Artista ON Artista.Id = musica.ArtistaId ");			

			rs = ptsmt.executeQuery();
			
			List<MusicaModel> retorno = new ArrayList();
			
			while(rs.next()) 
			{
				ArtistaModel artista = new ArtistaModel(rs.getInt("Artista_Id"), rs.getString("Artista_Nome"), rs.getString("Artista_CaminhoImagem"), rs.getString("Artista_Descricao"));
				
				GeneroModel genero = new GeneroModel(rs.getInt("Genero_Id"), rs.getString("Genero_Nome"));
				
				retorno.add(new MusicaModel(rs.getInt("Id"),rs.getString("Nome"), rs.getString("caminhomusica"), rs.getString("Descricao"), artista, genero, true));
			}
			
			return retorno;
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void InsereMusicaNaPlaylist(int musica_id, int playlist_id) {
				
		PreparedStatement ptsmt = null;
		PreparedStatement ptsmt2 = null;
		ResultSet rs = null;				
		
		try 
		{						
			ptsmt2 = con.prepareStatement(" INSERT INTO musicaplaylist (PlaylistId, MusicaId) "
					+ "						VALUES (? ,?)" );
			
			ptsmt2.setInt(1, playlist_id);
			ptsmt2.setInt(2, musica_id);
			
			ptsmt2.executeUpdate();
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public void DeletaMusica(int id) {
		
		PreparedStatement pstmt = null;
		
		try {
						
			pstmt = con.prepareStatement(" DELETE FROM MusicaPlaylist  WHERE Musicaid = ? ");
						
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			pstmt = null;
			
			//Deleta a musica
			pstmt = con.prepareStatement(" DELETE FROM Musica WHERE Id = ? ");					
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
	}

}
