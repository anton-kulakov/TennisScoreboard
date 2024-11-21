const MAX_NAME_LENGTH = 55;

function validateForm() {
    const player1 = document.getElementById('player1').value.trim();
    const player2 = document.getElementById('player2').value.trim();

    if (player1 === player2) {
        alert('The names of the players must be different');
        return false;
    }

    if (player1.length > MAX_NAME_LENGTH) {
        alert('The name of the first player must not exceed 20 characters');
        return false;
    }

    if (player2.length > MAX_NAME_LENGTH) {
        alert('The name of the second player must not exceed 20 characters');
        return false;
    }

    return true;
}