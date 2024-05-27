package Views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Models.ArtistaModel;
import Models.PlaylistModel;

public class PlaylistTableView extends AbstractTableModel{
	private List<PlaylistModel> playlists;
	private static final String nomes[] = {"Nome", "Editar", "DELETAR"};
	
	public PlaylistTableView() {
		this.playlists = new ArrayList<PlaylistModel>();				
	}
	
	public PlaylistTableView(List<PlaylistModel> playlists) {
		this.playlists = playlists;
	}
	
	public void setPlaylistst(List<PlaylistModel> playlists) {
		this.playlists = playlists;
		this.fireTableDataChanged();
	}
	
	public String getColumnName(int number) {
		return nomes[number];
	}
	
	@Override
	public int getRowCount() {
		if (playlists != null) {
			return playlists.size();
		} else {
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		PlaylistModel playlist = playlists.get(rowIndex);						
		
		switch (columnIndex) {
		case 0:
			return playlist.getNome();
		case 1: 
			return "...";
		case 2:
			return "X";
		}				
		

		return null;
	}
	
	public PlaylistModel getPlaylist(int rowIndex) {
		return playlists.get(rowIndex);
	}
	
	public void adicionaPlaylist(PlaylistModel playlist) {
		this.playlists.add(playlist);
		this.fireTableDataChanged();
	}
	
	public void atualizaLista() {
		this.fireTableDataChanged();
	}
}
