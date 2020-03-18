var grid = [];

for (i = 0; i < 12; i++) {
    grid[i] = "init";
}

var number_of_cells = 12;
var r = 0;
while (r < number_of_cells) {
    var colour;
    if (r === 0 || r === 1) { colour = "red"; }
    if (r === 2 || r === 3) { colour = "blue"; }
    if (r === 4 || r === 5) { colour = "green"; }
    if (r === 6 || r === 7) { colour = "yellow"; }
    if (r === 8 || r === 9) { colour = "orange"; }
    if (r === 10 || r === 11) { colour = "purple"; }

    var number = Math.floor(Math.random() * 12);
    
    if (grid[number] === "init") {
        grid[number] = colour;
        r++;
    }
}

var firstGuess;
var secondGuess;
var numberOfClicks = 0;
var correctMatches = 0;
var attempts = 0;
var inProgress = 0;

function checkMatch() {

    guess = arguments[0];

    if (inProgress === 1 ) {
        return;
    }

    if (numberOfClicks === 0) {
        firstGuess = guess;

        var image = grid[firstGuess] + ".jpg";
        var element = document.getElementById(firstGuess);
        element.setAttribute('src', image);

        numberOfClicks ++;

    } else if (numberOfClicks === 1) {
        secondGuess = guess;

        var image = grid[secondGuess] + ".jpg";
        var element = document.getElementById(secondGuess);
        element.setAttribute('src', image); 

        if (firstGuess === secondGuess){

            alert("Please select a different cell.")
            inProgress = 0;
            return;

        } else if (grid[firstGuess] === grid[secondGuess]){

            hit_sound.play();
            correctMatches++;
            attempts++;
            numberOfClicks = 0;

        } else {

            //console.log("Did not match");
            miss_sound.play();
            setTimeout(function() {
                document.getElementById(firstGuess).setAttribute('src',"init.jpg");
                document.getElementById(secondGuess).setAttribute('src',"init.jpg");
                numberOfClicks = 0;
                attempts++;
                inProgress = 0;}, 1000);

                inProgress = 1;
        }
    }

    if (correctMatches == 6){

        alert("Well done, you've matched all of the pairs in " + attempts + " guesses.");
        applause.play();
    }
}

function restart() {
    location.reload();
}