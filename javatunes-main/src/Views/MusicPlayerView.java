package Views;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

import javax.swing.*;

import Helpers.MessageHelpers;
import Models.MusicPlayerModel;
import Models.MusicaModel;
//import Models.PlayerThreadModel;
import Models.PlayerThreadModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MusicPlayerView extends JPanel implements ActionListener {
	private JLabel tituloMusca, artistaLabel, musicaLabel, emptyLabel;
    private JButton playButton, pauseButton, skipButton, backButton;
    private JPanel labelPanel, buttonPanel, emptyPanel;
    private boolean isPlaying = false;
    private int indiceAtual;
    private List<MusicaModel> filaMusicas;

	private MusicPlayerModel player;
    
    public MusicPlayerView() {
    	montaTela();
    }
    
    public void playNext() {
        if (indiceAtual <= filaMusicas.size()) {
        	encerraFila();
            MusicaModel musicaAtual = filaMusicas.get(indiceAtual);
            System.out.println("Musica atual:" + musicaAtual);
            try {
				playMusic(musicaAtual);
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
            indiceAtual++;
        } else {
            System.out.println("Fila de músicas encerrada.");
        }
    }
    
    public void playPrevious() {
        if (indiceAtual <= filaMusicas.size()) {
        	encerraFila();
            indiceAtual--;
            MusicaModel musicaAnterior = filaMusicas.get(indiceAtual);
            
            if(musicaAnterior != null) {
            	try {
    				playMusic(musicaAnterior);
    			} catch (JavaLayerException e) {
    				e.printStackTrace();
    			}
            }
        	if(indiceAtual == 0) {
        		indiceAtual++;
        	}                                              
        } else {
            System.out.println("Não existem músicas antes desta!");
        }
    }
    
    public void playMusic(MusicaModel musica) throws JavaLayerException {
        System.out.println("Nome: " + musica.getNome());
        System.out.println("Artista: " + musica.getArtista().getNome());
        setMusicaLabel(musica.getNome());
        setArtistaLabel(musica.getArtista().getNome());
        playButton.setVisible(false);
        pauseButton.setVisible(true);
        isPlaying = true;

        FileInputStream input;
		try {
			input = new FileInputStream(musica.getCaminho());
			player = new MusicPlayerModel(input);
	        player.play();
		} catch (FileNotFoundException e) {
			MessageHelpers.MostraMensagem("O Endereço do arquivo de música não foi encontrado!");
			return;			
		} 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            if (!isPlaying && player != null) {
	        	playButton.setVisible(false);
	            pauseButton.setVisible(true);
	            isPlaying = true;
            	player.resume();
            } else {
            	MessageHelpers.MostraMensagem("Selecione uma música/artísta/playlist");
            }
        } else if (e.getSource() == pauseButton) {
            if (isPlaying && player != null) {
	        	pauseButton.setVisible(false);
	            playButton.setVisible(true);
	            isPlaying = false;
            	player.pause();
            }
        } else if (e.getSource() == skipButton) {
            if (isPlaying && player != null) {
            	playNext();
            } else {
            	MessageHelpers.MostraMensagem("Selecione uma música/artísta/playlist");
            }
        } else if (e.getSource() == backButton) {
        	if (isPlaying && player != null) 
        	{
        		playPrevious();    		
        	}
        }
        
    }
    
    public void montaTela() {
    	labelPanel = new JPanel();
        labelPanel.setLayout(new OverlayLayout(labelPanel));

    	emptyPanel = new JPanel();
    	emptyLabel = new JLabel("                                                        ");
    	emptyPanel.add(emptyLabel);
        
    	musicaLabel = new JLabel("           ");
    	musicaLabel.setAlignmentX(0f);
    	musicaLabel.setAlignmentY(0.9f);

    	artistaLabel = new JLabel("             ");
        artistaLabel.setAlignmentX(0f);
        artistaLabel.setAlignmentY(0.1f);
        
        buttonPanel = new JPanel();

    	backButton = new JButton(new ImageIcon("./static/img/back.png"));
    	backButton.addActionListener(this);
    	backButton.setContentAreaFilled(false);
    	backButton.setBorderPainted(false);

    	playButton = new JButton(new ImageIcon("./static/img/play.png"));
        playButton.addActionListener(this);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);

        pauseButton = new JButton(new ImageIcon("./static/img/pause.png"));
        pauseButton.addActionListener(this);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);

    	skipButton = new JButton(new ImageIcon("./static/img/skip.png"));
    	skipButton.addActionListener(this);
    	skipButton.setContentAreaFilled(false);
    	skipButton.setBorderPainted(false);
    	

        pauseButton.setVisible(false);

        labelPanel.add(musicaLabel);
        labelPanel.add(artistaLabel);        

        buttonPanel.add(backButton);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(skipButton);

        add(labelPanel);
        add(emptyPanel);
        add(buttonPanel);
    }
    
    public void encerraFila() {
    	if(player != null) {    		
    		player.close();
    	}
    }

	public JLabel getTituloMusca() {
		return tituloMusca;
	}

	public void setTituloMusca(JLabel tituloMusca) {
		this.tituloMusca = tituloMusca;
	}

	public JLabel getArtistaLabel() {
		return artistaLabel;
	}

	public void setArtistaLabel(String label) {
		artistaLabel.setText(label);
	}

	public JLabel getMusicaLabel() {
		return musicaLabel;
	}

	public void setMusicaLabel(String label) {
		musicaLabel.setText(label);
	}

	public JLabel getEmptyLabel() {
		return emptyLabel;
	}

	public void setEmptyLabel(JLabel emptyLabel) {
		this.emptyLabel = emptyLabel;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public JButton getSkipButton() {
		return skipButton;
	}

	public void setSkipButton(JButton skipButton) {
		this.skipButton = skipButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JPanel getLabelPanel() {
		return labelPanel;
	}

	public void setLabelPanel(JPanel labelPanel) {
		this.labelPanel = labelPanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JPanel getEmptyPanel() {
		return emptyPanel;
	}

	public void setEmptyPanel(JPanel emptyPanel) {
		this.emptyPanel = emptyPanel;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public MusicPlayerModel getPlayer() {
		return player;
	}

	public void setPlayer(MusicPlayerModel player) {
		this.player = player;
	}
	
	public void setFilaMusicas(List<MusicaModel> listaMusicas) {
		this.filaMusicas = listaMusicas;
		this.indiceAtual = 0;
	}
}