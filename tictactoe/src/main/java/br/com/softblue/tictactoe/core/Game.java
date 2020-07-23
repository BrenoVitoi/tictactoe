package br.com.softblue.tictactoe.core;

import br.com.softblue.tictactoe.Constants;
import br.com.softblue.tictactoe.ui.UI;

public class Game {
	
	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	
	
	
	public void play() {
		UI.printGameTitle();
		
		
		board.print();
		
		//UI.readInput("Nome do Jogador:");
		
	}
	
	private createPlayer(int index) {
		UI.readInput("Jogador")
	}
}
