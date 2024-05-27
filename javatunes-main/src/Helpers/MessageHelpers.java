package Helpers;

import javax.swing.JOptionPane;

public class MessageHelpers {
	
	public static void MostraMensagem(String s){
		   JOptionPane.showMessageDialog(null, s);
	}
	
	public static boolean CaixaOpcao(String perguntaSimOuNao ) {
        int resposta = JOptionPane.showConfirmDialog(null, perguntaSimOuNao, "Pergunta", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            return true;
        } else if (resposta == JOptionPane.NO_OPTION) {
            return false;
        } else if (resposta == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        
        return false;
    }
}
