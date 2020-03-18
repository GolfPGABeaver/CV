$(document).ready(function() {

  var player1Name = "";
  var player2Name = "";
  var turn = "";
  var grid = [[0,0,0],[0,0,0],[0,0,0]];
  var hasWinner = 0;
  var moveCount = 0;

  function boardMsg(x) { // Display message function.
    return $("#message_area").text(x);
  }

  function setTurn() { // Randomly select a player to go first.
    var r = Math.floor((Math.random() * 2) + 1);
    hasWinner=0;
    if(r==1) {
      turn = player1Name;
      boardMsg("It's " + player1Name + "'s turn.");
    } else {
      turn = player2Name;
      boardMsg("It's " + player2Name + "'s turn.");
    }
  }

  function init() { // Initialise the grid.
    turn = "";
    grid =  [[0,0,0],[0,0,0],[0,0,0]];
    boardMsg("");
    $(".col").map(function() {
      $(this).text("");
    }).get();
    hasWinner = 0;
    moveCount = 0;
  }

  $("#playButton").click(function () { // Start a new game.
    if(hasWinner == 1) {
      init();
    }
    player1Name = $("#player-1-inp").val();
    player2Name = $("#player-2-inp").val();
    if(player1Name == "" || player2Name == "") {
      $("#alert_dialog").html("Please enter player names.").dialog();
      return;
    }
    setTurn();
  });

  $(".col").click(function () { // Process mouse clicks for both players.
    player1Name = $("#player-1-inp").val();
    player2Name = $("#player-2-inp").val();
    if(player1Name == "" || player2Name == "") {
      $("#alert_dialog").html("Please enter player names.").dialog();
      return;
    }
    var row = $(this).parent().index();
    var col = $(this).index();
    if(grid[row][col] !== 0) {
      $("#alert_dialog").html("This position has already been filled.").dialog();
      return;
    }
    if(hasWinner==1) {
      $("#alert_dialog").html("To start another game, click Play again.").dialog();
      return;
    }
    if(turn == player1Name){
      moveCount++;
      $(this).text("O");
      grid[row][col] = 1;
      var ifWon = winnerCheck(1,player1Name);
      if(!ifWon) {
        if(moveCount>=9){
          boardMsg("Match Drawn!");
          moveCount=0;
          hasWinner=1;
          $("#alert_dialog").html("Match Drawn! To start a new game, click the Play button.").dialog();
          return;
        } else {
          turn = player2Name;
          boardMsg("It's " + player2Name + "'s turn.");
        }
        return;	
      } else {
        return;
    }
  }
    else if (turn == player2Name) {
      moveCount++;
      $(this).text("X");
      grid[row][col] = 2;
      var ifWon = winnerCheck(2, player2Name);
      if(!ifWon) {
        if(moveCount>=9){
          boardMsg("Match Drawn!");
          moveCount = 0 ;
          hasWinner = 1;
          $("#alert_dialog").html("Match Drawn! To start a new game, click the Play button.").dialog();
          return;
        } else {
          turn = player1Name;
          boardMsg("It's " + player1Name+"'s turn.");
        }
        return;	
      } else {
        return;
      }
    }
  });

  function winnerCheck(n,playerName){ // Check for winner.
    if (
      (grid[0][0]==n && grid[0][1]==n && grid[0][2]==n) ||
      (grid[1][0]==n && grid[1][1]==n && grid[1][2]==n) ||
      (grid[2][0]==n && grid[2][1]==n && grid[2][2]==n) ||
      (grid[0][0]==n && grid[1][0]==n && grid[2][0]==n) ||
      (grid[0][1]==n && grid[1][1]==n && grid[2][1]==n) ||
      (grid[0][2]==n && grid[1][2]==n && grid[2][2]==n) ||
      (grid[0][0]==n && grid[1][1]==n && grid[2][2]==n)||
      (grid[0][2]==n && grid[1][1]==n && grid[2][0]==n)
       )
      {
      boardMsg(playerName+" has won; congratulations!");
      hasWinner = 1;
      moveCount=0;
      $("#alert_dialog").html("Congratulations! To start a new game, click the Play button.").dialog();
      return true;
    }
    return false;
  }

  $("header").addClass("ui-widget-header").addClass("ui-corner-all");
  $("#game_board").addClass("ui-widget").addClass("ui-corner-all");

});