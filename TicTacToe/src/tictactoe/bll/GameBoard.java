/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.Arrays;

/**
 *
 * @author Stegger
 */

public class GameBoard implements IGameModel
{
    int currentPlayerId = 0;
        private static final int Board_Size = 3;
        private final int[][] board;
        public GameBoard()
        {
            currentPlayerId = 0;
            board = new int [Board_Size] [Board_Size];
            resetBoard();
        }

        private void resetBoard()
        {
            for(int[] row : board)
            {
                Arrays.fill(row, -1);
            }
            currentPlayerId = 0;
        }
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return getCurrentPlayer();
    }
    @Override
    public int getCurrentPlayer() { return currentPlayerId;}
   @Override
    public void nextPlayer()
   {
        currentPlayerId = switch (currentPlayerId) {
            case 0 -> 1;
            case 1 -> 0;
            default -> -1;
        };
   }
    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */

    public boolean play(int col, int row)
    {
        if(board[row][col] == -1){
            board[row][col] = currentPlayerId;
            checkWinner();
            nextPlayer();
            return true;
        }
        return false;
    }

    private int checkWinner()
    {
        if (checkRow() == true || checkCol() == true || checkDiagonals() == true)
        {
            return currentPlayerId;
        }
        else
        {
            return -1;
        }
    }

    private boolean checkRow()
    {
        for(int row=0; row < 3; row++)
        {
            if(board[row] [0] == board[row] [1] && board[row] [2] == board[row] [0])
            {
                return true;
            }
        }
        return false;
    }
    private boolean checkCol()
    {
        for(int col=0; col < 3; col++)
        {
            if(board[0] [col] == board[1] [col] && board[2] [col] == board[0] [col])
            {
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonals()
    {
        if(board[0] [0] == board[1] [1] && board[2] [2] == board[0] [0])
        {
            return true;
        }
        else if (board[0] [2] == board[1] [1] && board[2] [0] == board[0] [2])
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isGameOver()
    {
        if (checkWinner() == 0 || checkWinner() == 1 || checkWinner() == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return checkWinner();
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //TODO Implement this method
        resetBoard();
    }

}
