package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAOs.MusicaDAO;
import DAOs.PlaylistDAO;
import Helpers.FrontHelpers;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.GeneroModel;
import Models.MusicaModel;
import Models.PlaylistModel;
import Models.UsuarioModel;

public class CadastrarPlaylistView extends JFrame implements ActionListener{
	private PlaylistDAO playlistDAO = new PlaylistDAO();
	private JTextField txt_nome, txt_caminho, txt_descricao;
	private JLabel lbl_nome, lbl_caminho, lbl_descricao, lbl_todas_musicas, lbl_musicas_adicionadas;
	private JButton btn_caminhos, btn_cadastrar_playlist;
	private MusicaDAO musicaDAO = new MusicaDAO();
	private List<MusicaModel> todasMusicas;	
	
	protected JTable tabelaDeTodasMusicas;	
	private JScrollPane scrollPaneTodasMusicas = new JScrollPane();
	public JPanel painelScroll1;
	
	protected JTable tabelaDeMusicasAdicionadas;	
	private JScrollPane scrollPaneMusicasAdicionadas = new JScrollPane();
	public JPanel painelScroll2;
	
	public PlaylistModel playlistAtual;	
	
	public CadastrarPlaylistView(PlaylistModel playlist){		
		
		this.playlistAtual = playlist;				
		
		this.setTitle("Cadastro");		
		this.setSize(900, 780);		
		this.setLayout(null);
		getContentPane().setBackground(FrontHelpers.getCorFundo());

		lbl_todas_musicas = new JLabel("Todas Músicas");
		lbl_todas_musicas.setBounds(160, 130, 120, 40);
		lbl_todas_musicas.setForeground(Color.white);		
				
		lbl_musicas_adicionadas = new JLabel("Músicas Adicionadas");
		lbl_musicas_adicionadas.setBounds(520, 130, 150, 40);
		lbl_musicas_adicionadas.setForeground(Color.white);
		
		painelScroll1 = new JPanel();
		painelScroll1.setLayout(new BorderLayout());
		painelScroll1.setBounds(10,180,360,360);
		
		painelScroll2 = new JPanel();
		painelScroll2.setLayout(new BorderLayout());
		painelScroll2.setBounds(400,180,360,360);
		
		lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(10, 10, 70, 40);
		lbl_nome.setForeground(Color.white);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(80, 10, 200, 40);								
		
		lbl_caminho = new JLabel("Caminho Imagem");
		lbl_caminho.setBounds(290, 10, 100, 40);
		lbl_caminho.setForeground(Color.white);
		
		txt_caminho = new JTextField();
		txt_caminho.setBounds(400, 10, 250, 40);
		
		btn_caminhos = new JButton("...");
		btn_caminhos.setBounds(650, 10, 40, 40);
		btn_caminhos.addActionListener(this);
		btn_caminhos.setBackground(FrontHelpers.getCorBotao());
		btn_caminhos.setForeground(Color.white);

		
		lbl_descricao = new JLabel("Descrição");
		lbl_descricao.setBounds(10, 70, 70, 40);
		lbl_descricao.setForeground(Color.white);
	
		txt_descricao = new JTextField();
		txt_descricao.setBounds(120, 70, 570, 40);		
		
		btn_cadastrar_playlist = new JButton("Cadastrar");
		btn_cadastrar_playlist.setBounds(320, 650, 100, 40);	
		btn_cadastrar_playlist.addActionListener(this);
		btn_cadastrar_playlist.setBackground(FrontHelpers.getCorBotao());
		btn_cadastrar_playlist.setForeground(Color.white);
		
		this.tabelaDeTodasMusicas = new JTable(new MusicasTableView());
		tabelaDeTodasMusicas.setBackground(FrontHelpers.getCorBotao());			
		this.tabelaDeTodasMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				MusicasTableView musicaTableModel = (MusicasTableView) tabelaDeTodasMusicas.getModel();

				int indice = tabelaDeTodasMusicas.getSelectedRow();
				if (indice >= 0) {
					MusicaModel musica = musicaTableModel.getMusica(indice);
					
					boolean escolha = MessageHelpers.CaixaOpcao("Você deseja adicionar a música " + musica.getNome() + "?");
					
					if(escolha) {
						playlistAtual.getMusicas().add(musica);
						todasMusicas.remove(musica);
						
						MontaListaTodasMusicas();
						MontaListaMusicasPlaylist();
					}									
				}

			}
		});
		
		this.tabelaDeMusicasAdicionadas = new JTable(new MusicasTableView());
		tabelaDeMusicasAdicionadas.setBackground(FrontHelpers.getCorBotao());			
		this.tabelaDeMusicasAdicionadas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				MusicasTableView musicaTableModel = (MusicasTableView) tabelaDeMusicasAdicionadas.getModel();

				int indice = tabelaDeMusicasAdicionadas.getSelectedRow();
				if (indice >= 0) {
					MusicaModel musica = musicaTableModel.getMusica(indice);
					
					boolean escolha = MessageHelpers.CaixaOpcao("Você deseja remover a música " + musica.getNome() + "?");
					
					if(escolha) {
						playlistAtual.getMusicas().remove(musica);
						todasMusicas.add(musica);
						
						MontaListaTodasMusicas();
						MontaListaMusicasPlaylist();
					}									
				}

			}
		});
		
		
		this.add(lbl_nome);
		this.add(lbl_caminho);
		this.add(lbl_descricao);
		
		this.add(lbl_todas_musicas);
		this.add(lbl_musicas_adicionadas);
		
		this.add(btn_caminhos);
		
		this.add(txt_nome);
		this.add(txt_caminho);
		this.add(txt_descricao);			
		
		this.scrollPaneTodasMusicas = new JScrollPane(tabelaDeTodasMusicas);				
		scrollPaneTodasMusicas.setBackground(FrontHelpers.getCorFundo());
		painelScroll1.add(scrollPaneTodasMusicas,BorderLayout.CENTER);
		
		this.scrollPaneMusicasAdicionadas = new JScrollPane(tabelaDeMusicasAdicionadas);				
		scrollPaneMusicasAdicionadas.setBackground(FrontHelpers.getCorFundo());
		painelScroll2.add(scrollPaneMusicasAdicionadas,BorderLayout.CENTER);
		
		this.add(painelScroll1);
		this.add(painelScroll2);
		this.add(btn_cadastrar_playlist);			
		
		if(playlistAtual == null) {
			playlistAtual = new PlaylistModel();
		}
		else {
			txt_nome.setText(playlistAtual.getNome());
			txt_caminho.setText(playlistAtual.getCaminho());
			txt_descricao.setText(playlistAtual.getDescricao());
		}
		
		MontaListaMusicasPlaylist();
		MontaListaTodasMusicas();		
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		try
		{
			if(e.getSource() == btn_caminhos ) 
			{
				
				txt_caminho.setText("");
				
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    txt_caminho.setText(filePath);
                }																			
			}				
			if(e.getSource() == btn_cadastrar_playlist ) 
			{				
				playlistAtual.setNome(txt_nome.getText());
				playlistAtual.setCaminho(txt_caminho.getText());
				playlistAtual.setDescricao(txt_descricao.getText());							
				InserirPlaylist();			
				this.setVisible(false);	
			}
		}
		catch(Exception ex)
		{
			MessageHelpers.MostraMensagem("Algo deu errado");			
		}	
	}
	
	private void InserirPlaylist() {
		try{			
			if(playlistAtual.getNome() == "" || playlistAtual.getDescricao() == "") 
			{
				MessageHelpers.MostraMensagem("Informe dados válidos!!!");
				return;
			}
			else {
		
				
				if(playlistAtual.getId() == 0) {
					
					playlistDAO.InserirPlaylist(playlistAtual);		
					
					int idPlaylist = playlistAtual.getId();
					
					if(idPlaylist == 0) {
						idPlaylist = playlistDAO.IdPlaylistAdicionada();
					}			
					
					if(playlistAtual.getMusicas() != null) {
						for (MusicaModel musica : playlistAtual.getMusicas()) {
							musicaDAO.InsereMusicaNaPlaylist(musica.getId(), idPlaylist);
						}
					}
				}	
				else {
					playlistDAO.AtualizaPlaylist(playlistAtual);
					
					playlistDAO.DeletaPlaylistMusica(playlistAtual.getId());
					
					if(playlistAtual.getMusicas() != null) {
						for (MusicaModel musica : playlistAtual.getMusicas()) {
							musicaDAO.InsereMusicaNaPlaylist(musica.getId(), playlistAtual.getId());
						}
					}
				}
				
				
				
				this.dispose();								
			}	
		}
		catch(Exception ex){
			MessageHelpers.MostraMensagem("Algo deu errado");
			this.dispose();
		}		
	}
	
	private void MontaListaTodasMusicas() {
		
		if(todasMusicas == null) {
			todasMusicas = musicaDAO.ProcuraTodasMusicas();
		}		
		

		if(playlistAtual != null && playlistAtual.getMusicas() != null) {
			for(MusicaModel musica : playlistAtual.getMusicas()) {
				for(int i =0; i < todasMusicas.size(); i++){
					if(musica.getId() == todasMusicas.get(i).getId()) {
						todasMusicas.remove(i);
					}
				}
			}
		}
		
		MusicasTableView musicaTableView = new MusicasTableView();
		musicaTableView.setMusicas(todasMusicas);

		tabelaDeTodasMusicas.setModel(musicaTableView);		
	}
	
	private void MontaListaMusicasPlaylist() {
		
		if(playlistAtual != null && playlistAtual.getId() != 0 && todasMusicas == null) {
			playlistAtual.setMusicas(playlistDAO.ProcuraMusicasPlaylist(playlistAtual.getId()));
		}		
		
		MusicasTableView musicaTableView = new MusicasTableView();
		musicaTableView.setMusicas(playlistAtual.getMusicas());

		tabelaDeMusicasAdicionadas.setModel(musicaTableView);
	}
	
	
}
