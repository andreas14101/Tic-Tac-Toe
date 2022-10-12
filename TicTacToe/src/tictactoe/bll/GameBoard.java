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

    /**
     * resets the game board
     */
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
        return currentPlayerId;
    }

    /**
     * changes currentPlayerId
     */
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
            checkForWinner();
            nextPlayer();
            return true;
        }
        return false;
    }

    /**
     * checks for Winner
     * @return true if there is a winner found, otherwise false.
     */
    private boolean checkForWinner()
    {
        if (checkRow() || checkCol() || checkDiagonals()|| checkTie())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * checks if the celles are full
     * @param c1 makes sure that cell1 is not -1
     * @param c2 makes sure that cell2 is the same value as cell1
     * @param c3 makes sure that cell3 is the same value as cell1
     * @return a condition for use in if-statements and for-loops
     */
    private boolean checkCells(int c1, int c2, int c3)
    {
        return ((c1!=-1) && (c2==c1) && (c3==c1));
    }

    /**
     * checks the rows for a winner
     * @return true if there is a winner found, otherwise false
     */
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

    /**
     * checks the columns for a winner
     * @return true if a winner is found, otherwise false
     */
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

    /**
     * checks the diagonals for a winner
     * @return true if a winner is found, otherwise false
     */
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

    /**
     * checks if the game is over
     * @return true if the game is over, otherwise false
     */
    public boolean isGameOver()
    {
        if (checkForWinner() == true)
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
        if (checkRow() || checkCol() || checkDiagonals())
        {
            if(currentPlayerId == 1)
            {
                currentPlayerId = 0;
                return currentPlayerId;
            }
            else
            {
                currentPlayerId = 1;
                return currentPlayerId;
            }
        }
        if (checkTie())
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
        resetBoard();
    }

    /**
     * checks for a tie
     * @return true if all spaces are filled and no winner is found, otherwise false.
     */
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
        if (counter == 0)
        {
            return true;
        }
        return  false;
    }

}
