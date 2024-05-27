package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Models.MusicListListener;
import Models.MusicaModel;

public class TabbedPaneView extends JFrame implements MusicListListener {
	JButton btn_tocar_musica = new JButton("Tocar musica");	
	JButton btn_add_artista = new JButton("Adicionar artista");

    JPanel pnl_tabbed_pane = new JPanel(new BorderLayout());
    JPanel pnl_music_player = new JPanel(new BorderLayout());		
   
	JList<String> lista_musica = new JList<String>(); 
	JFrame janela;

    MusicPlayerView music_player;

	public TabbedPaneView() {
		JFrame frame = new JFrame("JavaTunes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        music_player = new MusicPlayerView();
        ListMusicaView musica_view = new ListMusicaView();
        ListArtistaView artista_view = new ListArtistaView();
        ListPlaylistView playlist_view = new ListPlaylistView();
        musica_view.setMusicListInterface(this);
        artista_view.setMusicListInterface(this);
        playlist_view.setMusicListInterface(this);

        tabbedPane.addTab("MÚSICAS", musica_view);
        tabbedPane.addTab("ARTISTAS", artista_view);
        tabbedPane.addTab("PLAYLISTS", playlist_view);

        pnl_music_player.setPreferredSize(new Dimension(800, 100));
        pnl_music_player.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_music_player.add(music_player);

        pnl_tabbed_pane.add(tabbedPane, BorderLayout.CENTER);
        pnl_tabbed_pane.add(pnl_music_player, BorderLayout.SOUTH);

        frame.add(pnl_tabbed_pane);
        frame.setVisible(true);
	}
	@Override
	public void onAcaoListaMusica(List<MusicaModel> listaMusicas) {
        System.out.println("Lista recebida: " + listaMusicas);
        music_player.setFilaMusicas(listaMusicas);
        music_player.playNext();
		
	}
} 