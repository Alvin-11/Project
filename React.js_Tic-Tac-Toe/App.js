//import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function Square({ value, onSquareClick,name }) {
  return (
    <button className={name} onClick={onSquareClick}>
      {value}
    </button>
  );
}
function Board({xIsNext,array,onPlay,amountMoves}){
  
  function result(i){
    if (isWinner(array) || array[i]) {
      return;
    }
    const newarray = array.slice();
    newarray[i] = xIsNext ? 'X' : 'O';
    //alert(newarray[i]);
    onPlay(newarray);
  }

 // alert(array[0]);
  const winner = isWinner(array);
  //alert(winner);
  let status;
  if (winner) {
    status = 'WINNER: ' + winner;
  } 
  else if(amountMoves===9){
    status = 'TIE ' ;
  }
  else{
    status = 'Next player: ' + (xIsNext ? 'X' : 'O');
  }
  
  return(
    <> 
    
    <div className="board-row">
    <Square value={array[0]} onSquareClick={() => result(0)} name="square" />
    <Square value={array[1]} onSquareClick={() => result(1)} name="squareq" />
    <Square value={array[2]} onSquareClick={() => result(2)} name="square" />
  </div>
  <div className="board-row">
  <Square value={array[3]} onSquareClick={() => result(3)} name="squareq"/>
    <Square value={array[4]} onSquareClick={() => result(4)} name="square" />
    <Square value={array[5]} onSquareClick={() => result(5)} name="squareq"/>
  </div>
  <div className="board-row">
  <Square value={array[6]} onSquareClick={() => result(6)} name="square"/>
    <Square value={array[7]} onSquareClick={() => result(7)} name="squareq"/>
    <Square value={array[8]} onSquareClick={() => result(8)} name="square"/>
  </div>
  <div className="status">{status}</div></>
   
  );
}




export default function Gallery() {
  const [history, setHistory] = useState([Array(9).fill(null)]);
  const [currentMove, setCurrentMove] = useState(0);
  const xIsNext = currentMove % 2 === 0;
  const currentSquares = history[currentMove];
  
  function update(newPosition){
    
    const newhistory = [...history.slice(0,currentMove+1),newPosition];
    setHistory(newhistory);
    setCurrentMove(newhistory.length-1);
    
  }

  function undoButton(nextMove){
    setCurrentMove(nextMove);
    setHistory(history.slice(0,nextMove+1));
  }
  const moves = history.map((squares, move) => {
    let description;
    if (move > 0) {
      description = 'Go to move #' + move;
    } else {
      description = 'Reset';
    }
    return (
      <li key={move}>
        <button class="game-undo" onClick={() => undoButton(move)}>{description}</button>
      </li>
    );
  });

  return (
    <>
     <header class="game-header"><h1>Tic-Tac-Toe</h1></header>
    <div className="game">
    <div className="game-board">
      <Board xIsNext={xIsNext} array={currentSquares} onPlay={update} amountMoves={currentMove} />
    </div>
    <div className="game-info">
      <ol>{moves}</ol>
    </div>
  </div>
  <footer class="game-footer"><p>© 2024   Alvin Biju.</p></footer>
  </>
  );
}
function isWinner(array){
 

 const winningposition = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6],
];
for(let i=0; i< winningposition.length;i++){
  const [a, b, c] = winningposition[i];
  
    if(array[a]===array[b]  && array[b]===array[c] ){
    return array[a];
  }
  
}
return null;

}



