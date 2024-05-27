package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Application.DBPrincipal;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.GeneroModel;
import Models.MusicaModel;
import Models.PlaylistModel;

public class PlaylistDAO {
	
	private Connection con;	
	
	public PlaylistDAO(){
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
	
	public void InserirPlaylist(PlaylistModel playlist) 
	{
		try 
		{			
			PreparedStatement pstmt = null;			
			
			pstmt = con.prepareStatement("insert into playlist( nome, caminhoImagem, descricao) values ( ?, ?, ?)");
						
			pstmt.setString(1, playlist.getNome());
			pstmt.setString(2, playlist.getCaminho());			
			pstmt.setString(3, playlist.getDescricao());			

			pstmt.executeUpdate();
			
			MessageHelpers.MostraMensagem(" Playlist Cadastrada ");

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
	
	public int IdPlaylistAdicionada() {
		PreparedStatement pstmt = null;
		
		try 
		{
			pstmt = con.prepareStatement(" select Max(ID) as MaiorId from playlist ");
			
			ResultSet rs = null;
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("MaiorId");
			}
			
			return 0;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}			
	}
	
	public List<PlaylistModel> ProcuraTodasPlaylists()
	{
		try 
		{
			List<PlaylistModel> retorno = new ArrayList();
			PreparedStatement ptsmt = null;
			ResultSet rs = null;
			
			ptsmt = con.prepareStatement(" SELECT playlist.Id , playlist.Nome, playlist.caminhoImagem, playlist.descricao "
					+ "				  	   FROM playlist ");			

			rs = ptsmt.executeQuery();
			
			while(rs.next()) {
				retorno.add(new PlaylistModel(rs.getInt("Id"), rs.getString("Nome"), rs.getString("CaminhoImagem"), rs.getString("Descricao"), false));
			}					
			
			return retorno;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void AtualizaPlaylist(PlaylistModel playlist) 
	{
		try 
		{
			PreparedStatement pstmt = null;						
			
			pstmt = con.prepareStatement(" update playlist set nome = ?, caminhoImagem = ?, descricao = ? where id = ?");
			
			pstmt.setString(1, playlist.getNome());
			pstmt.setString(2, playlist.getCaminho());
			pstmt.setString(3, playlist.getDescricao());			
			pstmt.setInt(4, playlist.getId());

			pstmt.executeUpdate();
			
			MessageHelpers.MostraMensagem("Playlist editada!");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}							
	}
	
	public void DeletaPlaylistMusica(int id) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(" delete from musicaplaylist where playlistid = ? ");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(" delete from playlist where id = ? ");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<MusicaModel> ProcuraMusicasPlaylist(int id){
		try 
		{
			ArrayList<MusicaModel> retorno = new ArrayList<MusicaModel>();
			
			PreparedStatement pstmt = null;
			
			ResultSet rs = null;
			
			pstmt = con.prepareStatement(" SELECT  musica.id, musica.nome, musica.caminhomusica, musica.descricao, genero.id AS Genero_Id, genero.nome AS Genero_Nome, artista.id AS Artista_Id, artista.nome AS Artista_Model "
									+ "    FROM musica "
									+ "    LEFT JOIN genero on genero.id = musica.generoid "
									+ "    LEFT JOIN artista on artista.id = musica.artistaid "
									+ "    LEFT JOIN musicaplaylist on musicaplaylist.musicaid = musica.id "
									+ "    LEFT JOIN playlist on playlist.id = musicaplaylist.playlistid "
									+ "	   WHERE playlist.Id = ? ");
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GeneroModel genero = new GeneroModel(rs.getInt("Genero_Id"), rs.getString("Genero_Nome"));
				ArtistaModel artista = new ArtistaModel();
				
				artista.setId(rs.getInt("Artista_Id"));
				artista.setNome(rs.getString("Artista_Model"));
				
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