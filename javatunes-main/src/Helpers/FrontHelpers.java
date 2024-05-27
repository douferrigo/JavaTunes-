package Helpers;

import java.awt.Color;
import java.awt.Font;

public class FrontHelpers {	
	
	private static Font fontTitulo = new Font("Arial", Font.PLAIN, 30);
	private static Color corFundo = new Color(92, 85, 212);		
	private static Color corBotao = new Color(230, 69, 98);		
	
	public static Color getCorFundo() {
		return corFundo;
	}
	
	public static Color getCorBotao() {
		return corBotao;
	}
	
	public static Font getFontTitulo() {
		return fontTitulo;
	}
	
}
