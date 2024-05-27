package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAOs.ArtistaDAO;
import DAOs.PlaylistDAO;
import Helpers.FrontHelpers;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.MusicListListener;
import Models.MusicaModel;
import Models.PlaylistModel;

public class ListPlaylistView extends JPanel implements ActionListener{
	private JButton btn_refresh = new JButton(new ImageIcon("./static/img/refresh.png"));
	private JButton btn_add_playlist= new JButton("Adicionar playlist");
	private JScrollPane scrollPane = new JScrollPane();	
	protected JTable tabelaDePlaylist;
	private List<PlaylistModel> playlists = new ArrayList();
	private PlaylistDAO playlistDAO = new PlaylistDAO();
    private MusicListListener listaMusicaListener;
    private List<MusicaModel> musicasPlaylist;
	
	public ListPlaylistView() {
		this.setLayout(new BorderLayout());
		this.setBackground(FrontHelpers.getCorFundo());		
		this.setForeground(FrontHelpers.getCorFundo());
		
		btn_add_playlist.addActionListener(this);					
		btn_add_playlist.setBackground(FrontHelpers.getCorBotao());
		btn_add_playlist.setForeground(Color.white);
		btn_add_playlist.setSize(this.getWidth() - 40, 40);
		btn_refresh.addActionListener(this);
		btn_refresh.setBackground(FrontHelpers.getCorBotao());
		
		this.tabelaDePlaylist = new JTable(new MusicasTableView());
		
		tabelaDePlaylist.setBackground(FrontHelpers.getCorFundo());
		tabelaDePlaylist.setForeground(Color.WHITE);
		this.tabelaDePlaylist.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				PlaylistTableView playlistTableModel = (PlaylistTableView)tabelaDePlaylist.getModel();

				int indice = tabelaDePlaylist.getSelectedRow();
				int indiceColuna = tabelaDePlaylist.getSelectedColumn();
				
				if (indice >= 0 && indiceColuna == 0) {
					PlaylistModel playlist = playlistTableModel.getPlaylist(indice);
										
					PlaylistDAO playlistDAO = new PlaylistDAO();
					
					musicasPlaylist = playlistDAO.ProcuraMusicasPlaylist(playlist.getId());
					acaoListaMusica();
				}
				else if(indice >= 0 && indiceColuna == 1) {
					
					PlaylistModel playlist = playlistTableModel.getPlaylist(indice);
					
					editarPlaylist(playlist);
				}
				else if(indice >= 0 && indiceColuna == 2) {
					
					boolean opcao = MessageHelpers.CaixaOpcao("Você tem certeza que deseja APAGAR a playlist selecionada?");
					
					if(opcao) {
						
						PlaylistModel playlist = playlistTableModel.getPlaylist(indice);
						
						playlistDAO.DeletaPlaylistMusica(playlist.getId());
						
						ProcuraPlaylists();
					}
				}
				

			}
		});	
		
		this.scrollPane = new JScrollPane(tabelaDePlaylist);	
		scrollPane.setBackground(FrontHelpers.getCorFundo());			
		this.add(scrollPane);
		this.add(btn_add_playlist, BorderLayout.SOUTH);		
		this.add(btn_refresh, BorderLayout.EAST);
		this.setVisible(true);
		
		ProcuraPlaylists();
	}
	
	private void editarPlaylist(PlaylistModel playlist) {
		
		this.setVisible(false);		

		CadastrarPlaylistView formCadastro = new CadastrarPlaylistView(playlist);
	}
	
	private void ProcuraPlaylists() {
		playlists.clear();
		
		playlists = playlistDAO.ProcuraTodasPlaylists();	
		
		PlaylistTableView playlistTableView = new PlaylistTableView();
		
		playlistTableView.setPlaylistst(playlists);

		tabelaDePlaylist.setModel(playlistTableView);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_add_playlist) 
		{						
			this.setVisible(false);		

			CadastrarPlaylistView formCadastro = new CadastrarPlaylistView(null);
		}
		else if(e.getSource() == btn_refresh) 
		{												
			ProcuraPlaylists();													
		}			
	}

	public void setMusicListInterface(MusicListListener listener) {
        this.listaMusicaListener = listener;
    }
	
	private void acaoListaMusica() {
        if (listaMusicaListener != null) {
        	listaMusicaListener.onAcaoListaMusica(musicasPlaylist);
        }
    }
}
