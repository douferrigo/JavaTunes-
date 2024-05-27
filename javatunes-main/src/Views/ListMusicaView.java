package Views;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import DAOs.ArtistaDAO;
import DAOs.MusicaDAO;
import Helpers.ActionJList;
import Helpers.FrontHelpers;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.MusicListListener;
import Models.MusicaModel;
import Models.PlayerThreadModel;
import Models.PlaylistModel;
import gui.ProdutoTableModel;
import gui.mdi.CadastraProdutoInternalFrame;
import gui.mdi.CadastroView;
import gui.mdi.ListaProdutosInternalFrame;
import model.Produto;

public class ListMusicaView extends JPanel implements ActionListener{

	private JButton btn_add_musica = new JButton("Adicionar musica");
	private JButton btn_refresh = new JButton(new ImageIcon("./static/img/refresh.png"));
	protected JTable tabelaDeMusicas;
	private JScrollPane scrollPane = new JScrollPane();
	private List<MusicaModel> musicas = new ArrayList();
	private MusicaDAO musicaDAO = new MusicaDAO();
    private MusicListListener listaMusicaListener;
    private ArrayList<MusicaModel> listaMusicas = new ArrayList<MusicaModel>();
	
	
	public ListMusicaView() 
	{		
		try
		{
			this.setLayout(new BorderLayout());
			this.setBackground(FrontHelpers.getCorFundo());		
			this.setForeground(FrontHelpers.getCorFundo());
					
			btn_add_musica.addActionListener(this);					
			btn_add_musica.setBackground(FrontHelpers.getCorBotao());
			btn_add_musica.setForeground(Color.white);	
			
			btn_refresh.addActionListener(this);
			btn_refresh.setBackground(FrontHelpers.getCorBotao());
			btn_refresh.setForeground(Color.white);
			
			this.tabelaDeMusicas = new JTable(new MusicasTableView());
			
			tabelaDeMusicas.setBackground(FrontHelpers.getCorFundo());				
			tabelaDeMusicas.setForeground(Color.WHITE);
			this.tabelaDeMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (e.getValueIsAdjusting()) {
						return;
					}
					MusicasTableView musicaTableModel = (MusicasTableView) tabelaDeMusicas.getModel();

					int indice = tabelaDeMusicas.getSelectedRow();
					int indiceColuna = tabelaDeMusicas.getSelectedColumn();
					
					if (indice >= 0 && (indiceColuna == 0 || indiceColuna == 1)) {
						
						MusicaModel musica = musicaTableModel.getMusica(indice);
						
						listaMusicas = musicaDAO.ProcuraMusicasPorGenero(musica);
												
						acaoListaMusica();
					}
					else if(indice >= 0 && indiceColuna == 2) {
						
						boolean opcao = MessageHelpers.CaixaOpcao("Você tem certeza que deseja APAGAR a música selecionada?");
						
						if(opcao) {
							musicaDAO.DeletaMusica(musicaTableModel.getMusica(indice).getId());	
							
							ProcuraMusicas();
						}
						else {
							return;
						}
					}

				}
			});
			
			this.scrollPane = new JScrollPane(tabelaDeMusicas);	
			scrollPane.setBackground(FrontHelpers.getCorFundo());			
			this.add(scrollPane);	
			this.add(btn_add_musica, BorderLayout.SOUTH);
			this.add(btn_refresh, BorderLayout.EAST);
			this.setVisible(true);
			
			ProcuraMusicas();
		}
		catch(Exception ex) 
		{
			MessageHelpers.MostraMensagem(ex.getMessage());
			return;
		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_add_musica) 
		{						
			ArtistaDAO artistaDAO = new ArtistaDAO();
			
			List<ArtistaModel> artistas = artistaDAO.ProcuraTodosArtistas(); 
			
			if(artistas == null || artistas.size() == 0) {
				MessageHelpers.MostraMensagem("Nenhum artista cadastrado!");
				return;
			}
			
			this.setVisible(false);
			
			CadastroView formCadastro = new CadastroView("musica");
		}
		
		if(e.getSource() == btn_refresh) 
		{		
			ProcuraMusicas();
		}
	}
	
	private void ProcuraMusicas() {		
		musicas = musicaDAO.ProcuraTodasMusicas();
		
		MusicasTableView musicaTableView = new MusicasTableView();
		musicaTableView.setMusicas(musicas);

		tabelaDeMusicas.setModel(musicaTableView);
	}
	
	public void setMusicListInterface(MusicListListener listener) {
        this.listaMusicaListener = listener;
    }
	
	private void acaoListaMusica() {
        if (listaMusicaListener != null) {
        	listaMusicaListener.onAcaoListaMusica(listaMusicas);
        }
    }
}