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

    private boolean checkWinner()
    {
        if (checkRow() == true || checkCol() == true || checkDiagonals() == true || checkTie() == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean checkCells(int c1, int c2, int c3)
    {
        return ((c1!=-1) && (c2==c1) && (c3==c1));
    }
    private boolean checkRow()
    {
        for(int r=0; r < 3; r++)
        {
            if (checkCells(board[r][0],board[r][1],board[r][2]))
            {
                return true;
            }
        }
        return false;
    }
    private boolean checkCol()
    {
        for(int c=0; c < 3; c++)
        {
            if (checkCells(board[0][c],board[1][c],board[2][c]))
            {
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonals()
    {
        if (checkCells(board[0][0],board[1][1],board[2][2]))
        {
            return true;
        }
        else if (checkCells(board[0][2],board[1][1],board[2][0]))
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
        if (checkWinner() == true)
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
        if (checkTie() == true)
        {
            return -1;
        }
        else
        {
            return currentPlayerId;
        }
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //TODO Implement this method
        resetBoard();
    }

    private boolean checkTie()
    {
        int counter =9;
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                if(board[r][c] != -1)
                {
                    counter--;
                }
            }
        }
        if ((counter == 0 && checkDiagonals() == true) || (counter == 0 && checkRow() == true) || (counter == 0 && checkCol() == true) )
        {
            return false;
        }
        else if (counter == 0)
        {
            return true;
        }
        return  false;
    }

}
