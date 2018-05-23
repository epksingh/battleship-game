# BattleShip Game


Assumption: 
===========
    - If missile target location is out of battle ground then it will treated as missile miss the target. 
  
Brief Design:
============= 
Classes :  
	BattleGroundGame (BattleGroundGame.java):  
		- Having references of all players. 
		- playGame(): Responsibility of starting game and taking cate of turn of player and announce result. 
	Player (Player.java): 
 		- Having reference of BattleGround, and locations of attack 
		- fireMissile(Player): Player will attack his opponent player and print result on console based on Result of attack 
	BattleGround (BattleGround.java): 
		- Have all cell and ship references. 
		- Its keeping count of destroyed ship. 
	Cell: 
		- There are three Type of Cell are available (P-Type, Q-Type,W-Type)  
		- W-Type(Water-Type) cell is used to fill empty Space. 
		- Cell maintain hit count for result. 
	Ship: 
		- There are two type of ship is available (P-Type, Q-Type); 
 		- Ship have references of available cell to

		
Extra
======
- Used JUnit 4.10



How to Run:
===========

 	If Input provided form file:  
        BattleShipGame bsg = new BattleShipGame(filePath); // filePath: is absolute path of input file 
		bsg.playGame(); 
    If Input provided from console: 
		BattleShipGame bsg = new BattleShipGame( ); 
		bsg.playGame(); 
	If input provided form any other stream input 
		BattleShipGame bsg = new BattleShipGame(br ); // br : is BufferedReader object 
		bsg.playGame(); 