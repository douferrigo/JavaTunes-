package Views;


import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Models.MusicaModel;
import Models.PlayerThreadModel;


public class HomeView extends JFrame implements ActionListener {
	
	private JButton btn_tab_home, btn_tab_playlists, btn_tab_musica, btn_tab_artistas, btn_tab_sair;
	private JLabel lbl_musica_tocando, lbl_categoria, lbl_artista, lbl_descricao, lbl_proxima_musica;
	private JPanel pnl_sidebar, pnl_principal, pnl_music_player;

	PlayerThreadModel fila_de_musicas = new PlayerThreadModel();
	
	public HomeView() {
		
		this.setTitle("Home");		
		this.setSize(800, 400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		// Panels
		pnl_sidebar = new JPanel();
		pnl_sidebar.setBackground(Color.white);
		pnl_sidebar.setBounds(0, 0, 170, 400);

		pnl_principal = new JPanel();
		pnl_principal.setBackground(Color.white);
		pnl_principal.setBounds(170, 0, 630, 275);

		pnl_music_player = new JPanel();
		pnl_music_player.setBackground(Color.white);
		pnl_music_player.setBounds(170, 275, 630, 125);

		// Buttons
		btn_tab_home = new JButton("HOME");
		btn_tab_home.setBounds(15, 15, 140, 35);
		btn_tab_home.addActionListener(this);

		btn_tab_playlists = new JButton("PLAYLISTS");
		btn_tab_playlists.setBounds(15, 65, 140, 35);
		btn_tab_playlists.addActionListener(this);

		btn_tab_musica = new JButton("MÚSICAS");
		btn_tab_musica.setBounds(15, 115, 140, 35);
		btn_tab_musica.addActionListener(this);

		btn_tab_artistas = new JButton("ARTISTAS");
		btn_tab_artistas.setBounds(15, 165, 140, 35);
		btn_tab_artistas.addActionListener(this);

		btn_tab_sair = new JButton("SAIR");
		btn_tab_sair.setBounds(15, 310, 140, 35);
		btn_tab_sair.addActionListener(this);
		
		// Labels
		lbl_musica_tocando = new JLabel("MUSICA QUE TA TOCANDO");
		lbl_musica_tocando.setVerticalAlignment(JLabel.TOP);
		lbl_musica_tocando.setHorizontalAlignment(JLabel.CENTER);
		lbl_musica_tocando.setFont(new Font("Serif", Font.PLAIN, 35));

//		lbl_categoria = new JLabel("Categoria:");
//		lbl_categoria.setBounds(15, 15, 140, 35);
//
//		lbl_artista = new JLabel("Artista:");
//		lbl_artista.setBounds(15, 15, 140, 35);
//
//		lbl_descricao = new JLabel("Descrição");
//		lbl_descricao.setBounds(15, 15, 140, 35);
		
		pnl_sidebar.add(btn_tab_home); 
		pnl_sidebar.add(btn_tab_playlists); 
		pnl_sidebar.add(btn_tab_musica); 
		pnl_sidebar.add(btn_tab_artistas); 
		pnl_sidebar.add(btn_tab_sair);
		pnl_principal.add(lbl_musica_tocando);
		
//		pnl_principal.add(lbl_categoria); pnl_principal.add(lbl_artista); pnl_principal.add(lbl_descricao);
		this.add(pnl_sidebar); 
		this.add(pnl_principal); 
		this.add(pnl_music_player);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_tab_musica) {

		}
		if(e.getSource() == btn_tab_sair) {
		}
	}
}
