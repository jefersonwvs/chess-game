package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.isCheckMate()) {
			try {
				UI.clearScreen();
				
				System.out.printf("=================== CHESS GAME ===================\n\n");
				UI.printMatch(chessMatch, captured);
				System.out.println();
				
				System.out.print("Source >>> ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				System.out.printf("=================== CHESS GAME ===================\n\n");
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				
				System.out.print("Target >>> ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
			} catch (ChessException | InputMismatchException e) {
				System.err.println("\n" + e.getMessage());
				System.err.println("Press any key to continue...");
				sc.nextLine();
			}
		}
		
		UI.clearScreen();
		System.out.printf("=================== CHESS GAME ===================\n\n");
		UI.printMatch(chessMatch, captured);
		
	}
	
}
