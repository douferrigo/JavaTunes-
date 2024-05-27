package Views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Models.ArtistaModel;
import Models.MusicaModel;

public class ArtistasTableView extends AbstractTableModel{
	private List<ArtistaModel> artistas;
	private static final String nomes[] = {"Nome" , "DELETAR"};
	
	public ArtistasTableView() {
		this.artistas = new ArrayList<ArtistaModel>();				
	}
	
	public ArtistasTableView(List<ArtistaModel> artistas) {
		this.artistas = artistas;
	}
	
	public void setArtistas(List<ArtistaModel> artistas) {
		this.artistas = artistas;
		this.fireTableDataChanged();
	}
	
	public String getColumnName(int number) {
		return nomes[number];
	}
	
	@Override
	public int getRowCount() {
		if (artistas != null) {
			return artistas.size();
		} else {
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		ArtistaModel artista = artistas.get(rowIndex);						
		
		switch (columnIndex) {
		case 0:
			return artista.getNome();
		case 1:
			return "X";
		}				

		return null;
	}
	
	public ArtistaModel getArtista(int rowIndex) {
		return artistas.get(rowIndex);
	}
	
	public void adicionaArtista(ArtistaModel artista) {
		this.artistas.add(artista);
		this.fireTableDataChanged();
	}
	
	public void atualizaLista() {
		this.fireTableDataChanged();
	}
}
