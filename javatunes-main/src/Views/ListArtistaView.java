package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import DAOs.ArtistaDAO;
import DAOs.MusicaDAO;
import DAOs.PlaylistDAO;
import Helpers.FrontHelpers;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.MusicListListener;
import Models.MusicaModel;

public class ListArtistaView extends JPanel implements ActionListener{

	private JButton btn_refresh = new JButton(new ImageIcon("./static/img/refresh.png"));
	private JButton btn_add_artista= new JButton("Adicionar artista");
	private JScrollPane scrollPane = new JScrollPane();	
	protected JTable tabelaDeArtistas;
	private List<ArtistaModel> artistas = new ArrayList();
	private ArtistaDAO artistaDAO = new ArtistaDAO();
    private MusicListListener listaMusicaListener;
    private List<MusicaModel> musicasArtista;
	
	public ListArtistaView() {
		this.setLayout(new BorderLayout());
		this.setBackground(FrontHelpers.getCorFundo());		
		this.setForeground(FrontHelpers.getCorFundo());
		
		btn_add_artista.addActionListener(this);					
		btn_add_artista.setBackground(FrontHelpers.getCorBotao());
		btn_add_artista.setForeground(Color.white);
		btn_add_artista.setSize(this.getWidth() - 40, 40);
		btn_refresh.addActionListener(this);
		btn_refresh.setBackground(FrontHelpers.getCorBotao());
		
		this.tabelaDeArtistas = new JTable(new MusicasTableView());
		
		tabelaDeArtistas.setBackground(FrontHelpers.getCorFundo());			
		tabelaDeArtistas.setForeground(Color.WHITE);
		this.tabelaDeArtistas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ArtistasTableView artistaTableModel = (ArtistasTableView)tabelaDeArtistas.getModel();

				int indice = tabelaDeArtistas.getSelectedRow();
				int indiceColuna = tabelaDeArtistas.getSelectedColumn();
				
				if (indice >= 0 && indiceColuna == 0) {
					ArtistaModel artista = artistaTableModel.getArtista(indice);										
					
					ArtistaDAO artistaDAO = new ArtistaDAO();
					
					musicasArtista = artistaDAO.ProcuraMusicasArtista(artista);
					acaoListaMusica();
  					}
				else if(indice >= 0 && indiceColuna == 1) {
					boolean opcao = MessageHelpers.CaixaOpcao("Você tem certeza que deseja APAGAR o artista selecionado e todas as suas músicas?");
					
					if(opcao) {
						artistaDAO.DeletaArtista(artistaTableModel.getArtista(indice).getId());
						
						ProcuraArtistas();
					}
					else {
						return;
					}
				}
			}
		});						
		
		this.scrollPane = new JScrollPane(tabelaDeArtistas);	
		scrollPane.setBackground(FrontHelpers.getCorFundo());			
		this.add(scrollPane);
		this.add(btn_add_artista, BorderLayout.SOUTH);		
		this.add(btn_refresh, BorderLayout.EAST);
		this.setVisible(true);
		
		ProcuraArtistas();
	}
	
	private void ProcuraArtistas() {
		
		artistas.clear();
		
		artistas = artistaDAO.ProcuraTodosArtistas();	
		
		ArtistasTableView artistaTableView = new ArtistasTableView();
		artistaTableView.setArtistas(artistas);

		tabelaDeArtistas.setModel(artistaTableView);
			
	}

	private void acaoListaMusica() {
        if (listaMusicaListener != null) {
        	listaMusicaListener.onAcaoListaMusica(musicasArtista);
        }
    }
		
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_add_artista) 
		{						
			this.setVisible(false);		

			CadastroView formCadastro = new CadastroView("artista");
		}
		else if(e.getSource() == btn_refresh) 
		{												
			ProcuraArtistas();													
		}			
	}

	public void setMusicListInterface(MusicListListener listener) {
        this.listaMusicaListener = listener;
    }
}
