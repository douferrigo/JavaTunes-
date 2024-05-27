package Models;

import java.util.List;

import javazoom.jl.player.Player;

/**
	 * Manipula threads para executar as músicas
	 */
public class PlayerThreadModel extends Thread {
	
	private List<MusicPlayerModel> players;
	//private AdvancedPlayer aPlayer;
	
	public PlayerThreadModel(List<MusicPlayerModel> players) {
		this.players = players;
	}
	
	public PlayerThreadModel() {

	}
	
	/**
	 * Cria uma thread para tocar uma música
	 */
	public void run() {
		try {
			
			Thread threadLoka = null;									
			
			for(int i = 1; i < players.size(); i++) {
				threadLoka = players.get(i).play(threadLoka);
			}																											
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
	}
	
	/**
	 * Encerra a thread que toca música
	 */
	public void end() {
		try {
			for(MusicPlayerModel player : players) {
				player.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}