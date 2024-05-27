package Views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAOs.ArtistaDAO;
import Helpers.FrontHelpers;
import Models.ArtistaModel;

public class EditarArtistaView extends JFrame implements ActionListener{
	
	private JButton btn_editar_artista, btn_caminhos;
	private JLabel lbl_nome, lbl_caminho, lbl_descricao;
	private JTextField txt_nome, txt_caminho, txt_descricao;
	private ArtistaModel artista;
	private ArtistaDAO artistaDAO = new ArtistaDAO();
	
	public EditarArtistaView(ArtistaModel artista) {
		
		this.artista = artista;		
		this.setTitle("Editar");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		getContentPane().setBackground(FrontHelpers.getCorFundo());
		
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

		btn_editar_artista = new JButton("Editar");
		btn_editar_artista.setBounds(350, 350, 100, 40);
		btn_editar_artista.addActionListener(this);
		btn_editar_artista.setBackground(FrontHelpers.getCorBotao());
		btn_editar_artista.setForeground(Color.white);
		
		artistaDAO = new ArtistaDAO();
		
		this.add(lbl_nome);
		this.add(lbl_caminho);
		this.add(lbl_descricao);
		
		this.add(txt_nome);
		this.add(txt_caminho);
		this.add(txt_descricao);
		
		txt_nome.setText(artista.getNome());
		txt_caminho.setText(artista.getCaminho());
		txt_nome.setText(artista.getNome());
				
		this.add(btn_editar_artista);

		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btn_editar_artista) 
		{
			artista.setNome(txt_nome.getText());
			artista.setCaminho(txt_caminho.getText());
			artista.setDescricao(txt_descricao.getText());			
			artistaDAO.AtualizaArtista(artista);			
			this.dispose();
		}
		else if(e.getSource() == btn_caminhos)
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
		
	}
}
