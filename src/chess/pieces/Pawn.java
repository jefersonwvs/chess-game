package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position aux = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			// pawn 1st movement
			aux.setValues(position.getRow() - 2, position.getColumn());
			Position aux2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux) && getMoveCount() == 0
							&& getBoard().positionExists(aux2) && !getBoard().thereIsAPiece(aux2)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			// pawn generic movements
			aux.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			// pawn capture movements
			aux.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			aux.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			// special move en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		
		} else {
			// pawn 1st movement
			aux.setValues(position.getRow() + 2, position.getColumn());
			Position aux2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux) && getMoveCount() == 0
							&& getBoard().positionExists(aux2) && !getBoard().thereIsAPiece(aux2)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			// pawn generic movements
			aux.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			// pawn capture movements
			aux.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			aux.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
			// special move en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return mat;
	}
	
}
