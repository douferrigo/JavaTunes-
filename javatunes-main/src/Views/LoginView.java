package Views;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DAOs.UsuarioDAO;
import Helpers.ColorHelpers;
import Helpers.FrontHelpers;
import Helpers.MessageHelpers;
import Models.UsuarioModel;

public class LoginView extends JFrame implements ActionListener {
	
	private UsuarioDAO userDAO;
	private JButton btn_login, btn_cadastro;
	private JLabel lbl_usuario, lbl_senha, lbl_titulo;
	private JTextField txt_usuario;
	private JPasswordField txt_senha;	
	
	public LoginView() {
		
		userDAO = new UsuarioDAO();
		
		this.setTitle("JavaTunes Login");		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		
		getContentPane().setBackground(FrontHelpers.getCorFundo());

		lbl_titulo = new JLabel("Bem Vindo!");
		lbl_titulo.setBounds(325, 125, 200, 50);
		lbl_titulo.setForeground(Color.white);
		lbl_titulo.setFont(FrontHelpers.getFontTitulo());
		
		
		lbl_usuario = new JLabel("Usuário");
		lbl_usuario.setBounds(250, 200, 100, 40);
		lbl_usuario.setForeground(Color.white);
	
		txt_usuario = new JTextField();
		txt_usuario.setBounds(250, 230, 300, 40);
		
		lbl_senha = new JLabel("Senha");
		lbl_senha.setBounds(250, 275, 100, 40);
		lbl_senha.setForeground(Color.white);
			
		txt_senha = new JPasswordField();
		txt_senha.setBounds(250, 305, 300, 40);
		
		btn_login = new JButton("Login");
		btn_login.setBounds(320, 370, 150, 40);
		btn_login.addActionListener(this);
		btn_login.setBackground(FrontHelpers.getCorBotao());
		btn_login.setForeground(Color.white);		
		
		btn_cadastro = new JButton("Cadastro");
		btn_cadastro.setBounds(320, 420, 150, 40);
		btn_cadastro.addActionListener(this);
		btn_cadastro.setBackground(FrontHelpers.getCorBotao());
		btn_cadastro.setForeground(Color.white);
		
		this.add(lbl_usuario);
		this.add(lbl_senha);
		this.add(lbl_titulo);
		this.add(txt_usuario);
		this.add(txt_senha);
		
		this.add(btn_login);
		this.add(btn_cadastro);	
		
		this.setVisible(true);	
			
		
	}

	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getSource() == btn_cadastro ) {
			
			this.dispose();
			
			CadastroView formCadastro = new CadastroView("usuario");
					
		}
		
		if(e.getSource() == btn_login ) {
			
			UsuarioModel usuarioAutenticado = Autenticar();
			
			if(usuarioAutenticado == null) 
			{
				MessageHelpers.MostraMensagem("Erro ao autenticar!");
				return;
			}
			else 
			{
				this.setVisible(false);
				this.dispose();
				TabbedPaneView tab = new TabbedPaneView();
			}					
		}		
	}
	
	private UsuarioModel Autenticar() 
	{
		String usuario = txt_usuario.getText();
		String senha = new String(txt_senha.getPassword());
		
		UsuarioModel user = new UsuarioModel(usuario,senha);
		
		UsuarioModel retorno = userDAO.ProcuraUsuario(user);
		
		if(retorno == null || retorno.getId() <= 0) 
		{		
			return null;
		}
		else 
		{
			return retorno;
		}				
	}
}
