import java.io.*;
import java.util.*;

class Board 
{
   private char board[][];
   private int size = 3;
   private char p1symbol,p2symbol;
   private int count;
   private static final char EMPTY = ' ';
   public static final int PLAYER1WINS=1;
   public static final int PLAYER2WINS=2;
   public static final int DRAW=3;
   public static final int INCOMPLETE=4;
   public static final int INVALIDMOVE=5;

   public Board(char p1symbol,char p2symbol)
   {
	board = new char[size][size];
	for(int i=0;i<size;i++)
	{
	    for(int j=0;j<size;j++)
	    {
		board[i][j]=EMPTY;
	    }
	}
	this.p1symbol = p1symbol;
	this.p2symbol = p2symbol;
   }
   public int move(char symbol,int x,int y)
   {
	if(x<0 ||  y<0 || x>=size || y>=size || board[x][y]!=EMPTY)
		return INVALIDMOVE;
	board[x][y] = symbol;
 	count++;
	if(board[x][0] == board[x][1]  && board[x][0] == board[x][2])
	{
		return symbol == p1symbol ? PLAYER1WINS : PLAYER2WINS;
	}
	if(board[0][y] == board[1][y]  && board[0][y] == board[2][y])
	{
		return symbol == p1symbol ? PLAYER1WINS : PLAYER2WINS;
	}	
	if(board[0][2] != EMPTY && board[0][2] == board[1][1]  && board[0][2] == board[2][0])
	{
		return symbol == p1symbol ? PLAYER1WINS : PLAYER2WINS;
	}	
	if(board[0][0] != EMPTY && board[0][0] == board[1][1]  && board[0][0] == board[2][2])
	{
		return symbol == p1symbol ? PLAYER1WINS : PLAYER2WINS;
	} 
	if(count==size*size)
	{
	    return DRAW;
	}
	return INCOMPLETE;
   }
   public void print()
   {
	System.out.println("----------------");
	for(int i=0;i<size;i++)
	{
	    for(int j=0;j<size;j++)
	    {
		System.out.print("| "+board[i][j]+" |");
	    }
	    System.out.println();
	}
	System.out.println();
	System.out.println("----------------");
   }
}


class Player
{
   private String name;
   private char symbol;
   public Player(String name,char symbol)
   {
	setname(name);
	setsymbol(symbol);
   }
   public void setname(String name)
   {
	if(!(name.isEmpty()))
	    this.name=name;
   }
   public void setsymbol(char symbol)
   {
	if((symbol!='\0'))
	    this.symbol=symbol;
   }
   public String getname()
   {
	return this.name;
   }
   public char getsymbol()
   {
	return this.symbol;
   }
}
public class TicTacToe
{
   private Player player1, player2;
   private Board board;
   private int num=0;
   
   public static void main(String args[])
   {
	TicTacToe t = new TicTacToe();
	t.startGame();
   }
   public void startGame()
   {
	Scanner s = new Scanner(System.in);
	player1 = takePlayerInput(++num);
	player2 = takePlayerInput(++num);
	while(player1.getsymbol() == player2.getsymbol())
	{
	    System.out.println("Symbol is already taken! Choose a different symbol");
	    player2.setsymbol(s.next().charAt(0));
	}
	board = new Board(player1.getsymbol(),player2.getsymbol());
	boolean turn = true;
	int status = board.INCOMPLETE;
	while(status == board.INCOMPLETE || status == board.INVALIDMOVE)
	{
	    if(turn)
	    {
		System.out.println("Player 1: "+player1.getname()+" turn");
		System.out.println("Enter x: ");
		int x = s.nextInt();
		System.out.println("Enter y: ");
		int y = s.nextInt();
		status = board.move(player1.getsymbol(),x,y);
		if(status==board.INVALIDMOVE)
		{
		   System.out.println("Invalid Move! Try Again");
		   continue; 
		}
	    }
	    else
	    {
		System.out.println("Player 2: "+player2.getname()+" turn");
		System.out.println("Enter x: ");
		int x = s.nextInt();
		System.out.println("Enter y: ");
		int y = s.nextInt();
		status = board.move(player2.getsymbol(),x,y);
		if(status==board.INVALIDMOVE)
		{
		   System.out.println("Invalid Move! Try Again");
		   continue;
		}
		    
	    }
	    turn = !turn;
	    board.print();
	}
	    if(status == board.PLAYER1WINS)
	    {
		System.out.println("Player 1: "+ player1.getname()+"wins");
	    }
	    else if(status == board.PLAYER2WINS)
	    {
		
		System.out.println("Player 2: "+ player2.getname()+"wins");
	    }
	    else
	    {
		
		System.out.println("Draw");
	    }
	
   }
   private Player takePlayerInput(int num)
   {
	Scanner s = new Scanner(System.in);
 	System.out.println("Enter Player "+num+" name: ");
 	String name = s.nextLine();
	System.out.println("Enter Player "+num+" symbol: ");
	char symbol = s.next().charAt(0);
	Player p = new Player(name,symbol);
	return p;
   }
}

