pd2-Michael-Huang-Jerry-Dai
===========================

FINAL PROJECT
Michael Huang
Jerry Dai

~CANDYLAND BOARDGAME~

Description

Candyland BoardGame
  - 4 player board game
  - goal: be the first to reach the end of the track
  - draw cards from a deck to determine where to move

Instructions

- each player draws a card during their turn

- the player moves according to what the card says:
  - move to next tile of the card's color (RED,YELLOW,GREEN,BLUE)
  - move to 2 color tiles ahead (RED,YELLOW,GREEN,BLUE)
  - move backwards to tile of the card's color (RED,YELLOW,GREEN,BLUE)
  - switch spots with player that is directly ahead of them (GRAY)
  - do nothing (WHITE)
  - shuffle the deck (BLACK)
  - some cards allow you to draw repeatedly

- the first player to reach the end (Black tile) is the winner

GUI

- the game track is represented by a Linked List
  - the pattern of the tiles is RED YELLOW GREEN BLUE
  - the start tile is WHITE and the end tile is BLACK

- on the right, you will see descriptions
  - press the Draw Card button to draw the next card
  - the text dialogue underneath describes the round
  - a leader board shows how many wins each player has
  - upon finding a winner, you may restart the game

Data Structure/Algorithms
- Stacks (deck of cards that the players will draw cards from)
- Linked List (Track of the boardgame)
- GUI


