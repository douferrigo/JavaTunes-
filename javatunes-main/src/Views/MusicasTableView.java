package Views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Models.ArtistaModel;
import Models.MusicaModel;

public class MusicasTableView extends AbstractTableModel{
	private List<MusicaModel> musicas;
	private static final String nomes[] = {"Nome","Artista", "DELETAR"};
	
	public MusicasTableView() {
		this.musicas = new ArrayList<MusicaModel>();
	}
	
	public MusicasTableView(List<MusicaModel> musicas) {
		this.musicas = musicas;
	}
	
	public void setMusicas(List<MusicaModel> musicas) {
		this.musicas = musicas;
		this.fireTableDataChanged();
	}
	
	public String getColumnName(int number) {
		return nomes[number];
	}
	
	@Override
	public int getRowCount() {
		if (musicas != null) {
			return musicas.size();
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

		MusicaModel musica = musicas.get(rowIndex);
		ArtistaModel artista = musica.getArtista();				
		musica.setArtista(artista);
		
		switch (columnIndex) {
		case 0:
			return musica.getNome();
		case 1:
			return musica.getArtista().getNome();
		case 2:
			return "X";
		}		

		return null;
	}
	
	public MusicaModel getMusica(int rowIndex) {
		return musicas.get(rowIndex);
	}
	
	public void adicionaMusica(MusicaModel musica) {
		this.musicas.add(musica);
		this.fireTableDataChanged();
	}
	
	public void atualizaLista() {
		this.fireTableDataChanged();
	}
}
