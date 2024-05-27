package Helpers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.ListModel;

import DAOs.MusicaDAO;
import Models.MusicaModel;
import Models.PlayerThreadModel;
import Views.MusicPlayerView;

public class ActionJList extends MouseAdapter{
	private MusicaDAO musicaDAO = new MusicaDAO();
	protected JList list;
	private PlayerThreadModel player = new PlayerThreadModel();
	    
	public ActionJList(JList l){	   
	   list = l;
	}
	    
	public void mouseClicked(MouseEvent e){
		
	   if(e.getClickCount() == 2)
	   {
		    int index = list.locationToIndex(e.getPoint());
		    ListModel dlm = list.getModel();
		    Object item = dlm.getElementAt(index);;
		    list.ensureIndexIsVisible(index);
		    //System.out.println("Double clicked on " + item);
		    
		    MusicaModel musica = musicaDAO.ProcuraMusica(item.toString());

            MusicPlayerView musicPlayer = new MusicPlayerView();
            musicPlayer.setVisible(true);
//		    player = new PlayerThreadModel(musica);
//		    player.tocar();
		    
	   }
	}
}

