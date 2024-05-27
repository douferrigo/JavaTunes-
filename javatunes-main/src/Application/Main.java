package Application;

import java.io.FileInputStream;
import java.sql.*;

import javax.swing.SwingUtilities;

import Models.MusicPlayerModel;
import Views.HomeView;
import Views.LoginView;
import Views.TabbedPaneView;

public class Main { 		
	
	public static void main(String[] args) 
	{			
		try 
		{			
			DBPrincipal.criarConexao();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			
			//LoginView a = new LoginView();					
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//            FileInputStream input = new FileInputStream("teste.mp3"); 
//            MusicPlayerModel player = new MusicPlayerModel(input);
//
//            // start playing
//            player.play();
//
//            // after 5 secs, pause
//            Thread.sleep(5000);
//            player.pause();     
//
//            // after 5 secs, resume
//            Thread.sleep(5000);
//            player.resume();
//        } catch (final Exception e) {
//            throw new RuntimeException(e);
//        }
		LoginView tela = new LoginView();				
	}		
}