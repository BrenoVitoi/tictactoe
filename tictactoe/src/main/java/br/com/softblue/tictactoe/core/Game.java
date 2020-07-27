package br.com.softblue.tictactoe.core;

import br.com.softblue.tictactoe.Constants;
import br.com.softblue.tictactoe.score.ScoreManager;
import br.com.softblue.tictactoe.ui.UI;

public class Game {

	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	private int currentPlayerIndex = -1;
	private ScoreManager scoreManager;

	@SuppressWarnings("unused")
	public void play() {
		scoreManager = createScoreManager();
		
		UI.printGameTitle();

		for (int i = 0; i < players.length; i++) {

			players[i] = createPlayer(i);
		}

		boolean gameEnded = false;
		Player currentPlayer = nextPlayer();
		Player winner = null;

		while (!gameEnded) {
			board.print();
			
			boolean sequenceFound;
			
			try {
				
			 sequenceFound = currentPlayer.play();
			
			}catch (InvalidMoveException e) {
				UI.printText("ERRO: " + e.getMessage());
				continue;
			}
			if (sequenceFound) {
				gameEnded = true;
			} else if (board.isFull()) {
				gameEnded = true;
			} else {
			
			currentPlayer = nextPlayer();
			}
		}

		
		if (winner == null) {
			UI.printText("O jogo terminou empatado");
		} else {
			UI.printText("O jogodor '" + winner.getName() + "' venceu o jogo!");
			
			scoreManager.saveScore(winner);
		}
		
		board.print();
		UI.printText("Fim do jogo!");

	}

	private Player createPlayer(int index) {
		String name = UI.readInput("Jogador " + (index + 1) + " => ");
		char symbol = Constants.SYMBOL_PLAYERS[index];
		Player player = new Player(name, board, symbol);
		
		Integer score = scoreManager.getScore(player);
		
		if(score != null) {
			
			UI.printText("O jogador '" + player.getName() + "' já possui " + score + " vitória(s)!");
		}

		UI.printText("O jogador '" + name + "' vai usar o simbolo '" + symbol + "'");

		return player;

	}

	private Player nextPlayer() {
		// Duas abordagens iguais

		/*
		 * currentPlayerIndex++;
		 * 
		 * 
		 * if (currentPlayerIndex >= players.length) { currentPlayerIndex = 0; } return
		 * players[currentPlayerIndex];
		 * 
		 */

		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
		return players[currentPlayerIndex];
	}
	
	private ScoreManager createScoreManager() {
		//TODO Retornar tipo correto
		return null;
	}
}
