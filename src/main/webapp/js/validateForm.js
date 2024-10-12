function validateForm() {
    const player1 = document.getElementById('player1').value.trim();
    const player2 = document.getElementById('player2').value.trim();

    if (player1 === player2) {
        alert('The names of the players must be different');
        return false;
    }
    return true;
}