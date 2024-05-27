package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Application.DBPrincipal;
import Models.GeneroModel;

public class GeneroDAO {
	
	private Connection con;	
	
	public GeneroDAO(){
		try {
			con = DBPrincipal.retornaConexao();			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public List<GeneroModel> ProcuraGeneros()
	{
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		
		try {
			ptsmt = con.prepareStatement(" SELECT Genero.Id, Genero.Nome "
					+ "					   FROM Genero ");
			
			
			rs = ptsmt.executeQuery();
			
			List<GeneroModel> retorno = new ArrayList();
			
			while(rs.next()) 
			{
				retorno.add(new GeneroModel(rs.getInt("Id"), rs.getString("Nome")));							
			}
			
			return retorno;									
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	
}
