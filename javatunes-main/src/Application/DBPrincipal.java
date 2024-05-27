package Application;

import java.sql.*;

public class DBPrincipal {
	private static Statement stm;
	static final String URL = "jdbc:postgresql://localhost:5432/JavaTunes";
	static final String USER = "postgres";
	static final String PASS = "0501";
	private static Connection conecta;
	
	public static Connection retornaConexao() throws ClassNotFoundException, SQLException  
	{
		if (conecta != null){						
			return conecta;
		}		
		else {
			criarConexao();
			return conecta;
		}					
			
	}
	
	public static void criarConexao() throws ClassNotFoundException, SQLException{
				
		Class.forName("org.postgresql.Driver");
		
		conecta = DriverManager.getConnection(URL, USER, PASS);		
				
		if (conecta != null){						
			ComecaTransacoes(conecta);
		}		
		else {
			System.out.println("Deu pau aq");	
		}
	}
	
	
	public static void ComecaTransacoes(Connection con) {
		try {
			stm = con.createStatement();
			
			DatabaseMetaData dbm = con.getMetaData();
			
			ResultSet tables = dbm.getTables(null, null, "Usuario", null);								
			
			if(tables.next() == false) {
				CriaTabelas(stm);
			}	
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void CriaTabelas(Statement stm){						
		
		try 
		{
			String query = "create table Usuario (Id SERIAL, Username VARCHAR(30), Senha VARCHAR(30), primary key (id))";
			stm.executeUpdate(query);
			
			query = "create table Genero (Id SERIAL, Nome VARCHAR(30), primary key (id))";
			stm.executeUpdate(query);
			
			query = "create table Artista (Id SERIAL, Nome VARCHAR(100), CaminhoImagem VARCHAR(255), Descricao VARCHAR(255), primary key (id))";
			stm.executeUpdate(query);
			
			query = "create table Playlist (Id SERIAL, Nome VARCHAR(100), CaminhoImagem VARCHAR(255), Descricao VARCHAR(255), primary key (id))";
			stm.executeUpdate(query);
						
			query = "create table Musica (Id SERIAL, ArtistaId int, GeneroId int, Nome VARCHAR(100), CaminhoMusica VARCHAR(255), Descricao VARCHAR (100), "
					+ "PRIMARY KEY (id), FOREIGN KEY(ArtistaId) REFERENCES Artista(Id), FOREIGN KEY(GeneroId) REFERENCES Genero(Id))";
			stm.executeUpdate(query);
			
			query = " create table MusicaPlaylist (Id SERIAL, PlaylistId int, MusicaId int, "
					+ "PRIMARY KEY (id), FOREIGN KEY(PlaylistId) REFERENCES Playlist(Id), FOREIGN KEY(MusicaId) REFERENCES Musica(Id))";
			stm.executeUpdate(query);
			
			InsereGenero(stm);
			
			System.out.println("Tabelas Criadas");	
		} 
		catch (SQLException e) 
		{
			return;
		}		
	}
	
	public static void InsereGenero(Statement stm) {
		
		try {
			stm.executeQuery(" INSERT INTO Genero (Id , Nome) VALUES\r\n"
					+ "    (1,  'Pop'),\r\n"
					+ "    (2,  'Rock'),\r\n"
					+ "    (3,  'Pop Rock'),\r\n"
					+ "    (4 , 'Hip Hop'),    \r\n"
					+ "    (5 , 'Eletrônica'),    \r\n"
					+ "    (6,  'Jazz'),\r\n"
					+ "    (7,  'Blues'),\r\n"
					+ "    (8 , 'Classica'),\r\n"
					+ "    (9 , 'Reggae'),    \r\n"
					+ "    (10, 'Funk'),\r\n"
					+ "    (11, 'Metal'),\r\n"
					+ "    (12, 'Indie'),\r\n"
					+ "    (13, 'Disco'),\r\n"
					+ "    (14, 'Samba'),\r\n"
					+ "    (15, 'Pagode'),\r\n"
					+ "    (16, 'Sertanejo'),\r\n"
					+ "    (17, 'MPB'),\r\n"
					+ "    (18, 'Rap'),\r\n"
					+ "    (19, 'Trap');");		
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
}
