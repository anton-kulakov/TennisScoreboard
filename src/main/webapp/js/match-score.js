document.addEventListener('DOMContentLoaded', function() {
    function updatePlayerPoints(playerPointsElement, playerPoints, mode) {
        if (mode === 'tiebreak') {
            return;
        } else if (mode === 'deuce') {
            if (playerPoints === 0) {
                playerPointsElement.textContent = '';
            } else if (playerPoints === 1) {
                playerPointsElement.textContent = 'AD';
            } else if (playerPoints === 3) {
                playerPointsElement.textContent = '40';
            }
        } else {
            if (playerPoints === 1) {
                playerPointsElement.textContent = '15';
            } else if (playerPoints === 2) {
                playerPointsElement.textContent = '30';
            } else if (playerPoints === 3) {
                playerPointsElement.textContent = '40';
            }
        }
    }

    const firstPlayerPointsElement = document.getElementById('firstPlayerPoints');
    let firstPlayerPoints = parseInt(firstPlayerPointsElement.textContent);
    let firstPlayerMode = firstPlayerPointsElement.getAttribute('data-mode');
    updatePlayerPoints(firstPlayerPointsElement, firstPlayerPoints, firstPlayerMode);

    const secondPlayerPointsElement = document.getElementById('secondPlayerPoints');
    let secondPlayerPoints = parseInt(secondPlayerPointsElement.textContent);
    let secondPlayerMode = firstPlayerPointsElement.getAttribute('data-mode');
    updatePlayerPoints(secondPlayerPointsElement, secondPlayerPoints, secondPlayerMode);
});