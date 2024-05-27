package Views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import DAOs.ArtistaDAO;
import DAOs.GeneroDAO;
import DAOs.MusicaDAO;
import DAOs.PlaylistDAO;
import DAOs.UsuarioDAO;
import Helpers.FrontHelpers;
import Helpers.MessageHelpers;
import Models.ArtistaModel;
import Models.GeneroModel;
import Models.MusicaModel;
import Models.PlaylistModel;
import Models.UsuarioModel;

public class CadastroView extends JFrame implements ActionListener{
	
	private UsuarioDAO userDAO;
	private MusicaDAO musicaDAO;
	private ArtistaDAO artistaDAO;
	private PlaylistDAO playlistDAO = new PlaylistDAO();
	private GeneroDAO generoDAO;
	private JButton btn_cadastrar_usuario, btn_cadastrar_musica, btn_cadastrar_artista, btn_caminhos, btn_cadastrar_playlist;
	private JLabel lbl_usuario, lbl_senha, lbl_confirmaSenha, lbl_nome, lbl_caminho, lbl_descricao, lbl_artista, lbl_genero, lbl_titulo;
	private JTextField txt_usuario, txt_nome, txt_caminho, txt_descricao;
	private JPasswordField txt_senha, txt_confirmaSenha;	
	private JComboBox<String> combo_artista;
	private JComboBox<String> combo_genero;
	private List<ArtistaModel> artistas; 
	private List<GeneroModel> generos; 
	
	
	public CadastroView(String tipo_cadastro) 
	{		
		artistas = new ArrayList();
		generos = new ArrayList();
		
		this.setTitle("Cadastro");		
		this.setSize(800, 600);		
		this.setLayout(null);
		getContentPane().setBackground(FrontHelpers.getCorFundo());
		
		if (tipo_cadastro == "usuario") {
			
			lbl_titulo = new JLabel("Informe seus dados!");
			lbl_titulo.setBounds(280, 135, 300, 50);
			lbl_titulo.setForeground(Color.white);
			lbl_titulo.setFont(FrontHelpers.getFontTitulo());
			
			
			lbl_usuario = new JLabel("Usuário");
			lbl_usuario.setBounds(200, 200, 100, 40);
			lbl_usuario.setForeground(Color.white);
										
			lbl_senha = new JLabel("Senha");
			lbl_senha.setBounds(200, 250, 100, 40);
			lbl_senha.setForeground(Color.white);
			
			lbl_confirmaSenha = new JLabel("Confirmar Senha");
			lbl_confirmaSenha.setBounds(200, 300, 100, 40);
			lbl_confirmaSenha.setForeground(Color.white);

			txt_usuario = new JTextField();
			txt_usuario.setBounds(300, 200, 300, 40);
			
			txt_senha = new JPasswordField();
			txt_senha.setBounds(300, 250, 300, 40);
			
			txt_confirmaSenha = new JPasswordField();
			txt_confirmaSenha.setBounds(300, 300, 300, 40);
			

			btn_cadastrar_usuario = new JButton("Cadastrar");
			btn_cadastrar_usuario.setBounds(350, 400, 100, 40);
			btn_cadastrar_usuario.addActionListener(this);
			btn_cadastrar_usuario.setBackground(FrontHelpers.getCorBotao());
			btn_cadastrar_usuario.setForeground(Color.white);
			
			userDAO = new UsuarioDAO();
			
			this.add(lbl_usuario);
			this.add(lbl_senha);
			this.add(lbl_confirmaSenha);
			this.add(lbl_titulo);
			
			this.add(txt_usuario);
			this.add(txt_senha);
			this.add(txt_confirmaSenha);
					
			this.add(btn_cadastrar_usuario);

			this.setVisible(true);
		}
		
		else if (tipo_cadastro == "musica") {
			generoDAO = new GeneroDAO();
			musicaDAO = new MusicaDAO();
			artistaDAO = new ArtistaDAO();
			lbl_nome = new JLabel("Nome");
			lbl_nome.setBounds(200, 200, 100, 40);
			lbl_nome.setForeground(Color.white);
										
			lbl_caminho = new JLabel("Caminho");
			lbl_caminho.setBounds(200, 250, 100, 40);
			lbl_caminho.setForeground(Color.white);
			
			lbl_descricao = new JLabel("Descrição");
			lbl_descricao.setBounds(200, 300, 100, 40);
			lbl_descricao.setForeground(Color.white);
			
			lbl_artista = new JLabel("Artista");
			lbl_artista.setBounds(200, 350, 100, 40);
			lbl_artista.setForeground(Color.white);

			txt_nome = new JTextField();
			txt_nome.setBounds(300, 200, 300, 40);
			
			txt_caminho = new JTextField();
			txt_caminho.setBounds(300, 250, 250, 40);
			
			btn_caminhos = new JButton("...");
			btn_caminhos.setBounds(560, 250, 40, 40);
			btn_caminhos.addActionListener(this);
			btn_caminhos.setBackground(FrontHelpers.getCorBotao());
			btn_caminhos.setForeground(Color.white);
			
			txt_descricao = new JTextField();
			txt_descricao.setBounds(300, 300, 300, 40);		
		
			
			lbl_genero = new JLabel("Gênero");
			lbl_genero.setBounds(200, 400, 100, 40);
			lbl_genero.setForeground(Color.white);
			
			combo_artista = new JComboBox<String>();
			combo_artista.setBounds(300, 350, 300, 40);
			CarregaComboArtista();
					
			combo_genero  = new JComboBox<String>();
			combo_genero.setBounds(300, 400, 300, 40);
			CarregaComboGenero();
			
			btn_cadastrar_musica = new JButton("Cadastrar");
			btn_cadastrar_musica.setBounds(350, 500, 100, 40);	
			btn_cadastrar_musica.addActionListener(this);
			btn_cadastrar_musica.setBackground(FrontHelpers.getCorBotao());
			btn_cadastrar_musica.setForeground(Color.white);
			
			
			this.add(lbl_nome);
			this.add(lbl_caminho);
			this.add(lbl_descricao);
			this.add(lbl_artista);
			this.add(lbl_genero);		
			this.add(btn_caminhos);
			
			this.add(txt_nome);
			this.add(txt_caminho);
			this.add(txt_descricao);
			this.add(combo_artista);
			this.add(combo_genero);
					
			this.add(btn_cadastrar_musica);

			this.setVisible(true);
		}		
		else if (tipo_cadastro == "artista") {								
			
			lbl_nome = new JLabel("Nome");
			lbl_nome.setBounds(200, 200, 100, 40);
			lbl_nome.setForeground(Color.white);
										
			lbl_caminho = new JLabel("Caminho Imagem");
			lbl_caminho.setBounds(200, 250, 100, 40);
			lbl_caminho.setForeground(Color.white);
			
			lbl_descricao = new JLabel("Descrição");
			lbl_descricao.setBounds(200, 300, 100, 40);
			lbl_descricao.setForeground(Color.white);
			
			txt_nome = new JTextField();
			txt_nome.setBounds(300, 200, 300, 40);
			
			txt_caminho = new JTextField();
			txt_caminho.setBounds(300, 250, 240, 40);
			
			txt_descricao = new JTextField();
			txt_descricao.setBounds(300, 300, 300, 40);
			
			btn_caminhos = new JButton("...");
			btn_caminhos.setBounds(550, 250, 50, 40);
			btn_caminhos.addActionListener(this);
			btn_caminhos.setBackground(FrontHelpers.getCorBotao());
			btn_caminhos.setForeground(Color.white);
			this.add(btn_caminhos);

			btn_cadastrar_artista = new JButton("Cadastrar");
			btn_cadastrar_artista.setBounds(350, 350, 100, 40);
			btn_cadastrar_artista.addActionListener(this);
			btn_cadastrar_artista.setBackground(FrontHelpers.getCorBotao());
			btn_cadastrar_artista.setForeground(Color.white);
			
			artistaDAO = new ArtistaDAO();
			
			this.add(lbl_nome);
			this.add(lbl_caminho);
			this.add(lbl_descricao);
			
			this.add(txt_nome);
			this.add(txt_caminho);
			this.add(txt_descricao);
					
			this.add(btn_cadastrar_artista);

			this.setVisible(true);
		}
		else if (tipo_cadastro == "playlist") {
			lbl_nome = new JLabel("Nome");
			lbl_nome.setBounds(200, 200, 100, 40);
			lbl_nome.setForeground(Color.white);
										
			lbl_caminho = new JLabel("Caminho Imagem");
			lbl_caminho.setBounds(200, 250, 100, 40);
			lbl_caminho.setForeground(Color.white);
			
			lbl_descricao = new JLabel("Descrição");
			lbl_descricao.setBounds(200, 300, 100, 40);
			lbl_descricao.setForeground(Color.white);

			txt_nome = new JTextField();
			txt_nome.setBounds(300, 200, 300, 40);
			
			txt_caminho = new JTextField();
			txt_caminho.setBounds(300, 250, 250, 40);
			
			btn_caminhos = new JButton("...");
			btn_caminhos.setBounds(560, 250, 40, 40);
			btn_caminhos.addActionListener(this);
			btn_caminhos.setBackground(FrontHelpers.getCorBotao());
			btn_caminhos.setForeground(Color.white);
			
			txt_descricao = new JTextField();
			txt_descricao.setBounds(300, 300, 300, 40);		
			
			btn_cadastrar_playlist = new JButton("Cadastrar");
			btn_cadastrar_playlist.setBounds(350, 500, 100, 40);	
			btn_cadastrar_playlist.addActionListener(this);
			btn_cadastrar_playlist.setBackground(FrontHelpers.getCorBotao());
			btn_cadastrar_playlist.setForeground(Color.white);
			
			
			this.add(lbl_nome);
			this.add(lbl_caminho);
			this.add(lbl_descricao);					
			this.add(btn_caminhos);
			
			this.add(txt_nome);
			this.add(txt_caminho);
			this.add(txt_descricao);			
					
			this.add(btn_cadastrar_playlist);

			this.setVisible(true);
		}
		
	}
	
	private void CarregaComboArtista() 
	{
		artistas = artistaDAO.ProcuraTodosArtistas();
		
		if(artistas == null )		
		{
			return;
		}
		
		for(int i = 0; i < artistas.size() ; i++) 
		{
			combo_artista.addItem(artistas.get(i).getNome());
		}
		
		return;
	}
	
	private void CarregaComboGenero() 
	{
		generos = generoDAO.ProcuraGeneros();
		
		if(generos == null)
		{
			return;
		}
		
		for(int i = 0; i < generos.size() ; i++) 
		{
			combo_genero.addItem(generos.get(i).getNome());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		try
		{
			if(e.getSource() == btn_cadastrar_usuario ) 
			{
				
				String usuario = txt_usuario.getText();
				String senha = new String(txt_senha.getPassword());
				String senhaConfirmada = new String(txt_confirmaSenha.getPassword());			
				
				InserirUsuario(new UsuarioModel(usuario,senha), senhaConfirmada);
				
				LoginView formCadastro = new LoginView();
				this.setVisible(false);
			}
			
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

			if(e.getSource() == btn_cadastrar_musica ) 
			{
				
				String nome = txt_nome.getText();
				String caminho = new String(txt_caminho.getText());
				String descricao = new String(txt_descricao.getText());
				
				ArtistaModel artista = null;										
				String nome_artista = (String) combo_artista.getSelectedItem();
				
				for (int i = 0; i < artistas.size(); i++) {
					if(nome_artista == artistas.get(i).getNome()) 
					{
						artista = artistas.get(i);
					}
				}
				
				
				GeneroModel genero = null;
				String nome_genero = (String) combo_genero.getSelectedItem();
				
				for (int i = 0; i < generos.size(); i++) {
					if(nome_genero == generos.get(i).getNome()) 
					{
						genero = generos.get(i);
					}
				}
				
				MusicaModel musica = new MusicaModel(nome, caminho, descricao, artista, genero, false); 
				
				musicaDAO.InserirMusica(musica);
				
				this.setVisible(false);							
			}
			if(e.getSource() == btn_cadastrar_artista ) 
			{
				
				String nome = txt_nome.getText();
				String caminho = new String(txt_caminho.getText());
				String descricao = new String(txt_descricao.getText());							
				InserirArtista(new ArtistaModel(nome,caminho,descricao));			
				this.setVisible(false);	
			}			
			if(e.getSource() == btn_cadastrar_playlist ) 
			{				
				String nome = txt_nome.getText();
				String caminho = new String(txt_caminho.getText());
				String descricao = new String(txt_descricao.getText());							
				InserirPlaylist(new PlaylistModel(nome,caminho,descricao, false));			
				this.setVisible(false);	
			}
		}
		catch(Exception ex)
		{
			MessageHelpers.MostraMensagem("Algo deu errado");			
		}	
	}
	
	private void InserirUsuario(UsuarioModel user, String senhaConfirmada) 
	{
		String usuario = user.getUsername();
		String senha = user.getSenha();
		
		
		if(usuario == "" || senha == "") 
		{
			MessageHelpers.MostraMensagem("Informe dados válidos!!!");
			return;
		}
		
		else if(senha == senhaConfirmada || senha.contentEquals(senhaConfirmada) || senha.length() == senhaConfirmada.length()) 
		{
			userDAO.InserirUsuario(user);				
			
			this.dispose();
			
			LoginView login = new LoginView();
			
		}
		else 
		{				
			MessageHelpers.MostraMensagem("As senhas diferem!!!");
		}
	}
	
	private void InserirArtista(ArtistaModel artista) 
	{
		String nome = artista.getNome();
		String caminho = artista.getCaminho();
		
		if(nome == "" || caminho == "") 
		{
			MessageHelpers.MostraMensagem("Informe dados válidos!!!");
			return;
		}
		else {
			artistaDAO.InserirArtista(artista);				
			
			this.dispose();								
		}
	}
	
	private void InserirPlaylist(PlaylistModel playlist) {
		String nome = playlist.getNome();
		String caminho = playlist.getCaminho();
		
		if(nome == "" || caminho == "") 
		{
			MessageHelpers.MostraMensagem("Informe dados válidos!!!");
			return;
		}
		else {
	
			playlistDAO.InserirPlaylist(playlist);				
			
			this.dispose();								
		}
	}
}
