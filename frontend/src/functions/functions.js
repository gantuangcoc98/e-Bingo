import axios from "axios";

function generateBingoCard() {
    const playerCard = {
        'B': generateRandomNumbers(1, 10),
        'I': generateRandomNumbers(11, 20),
        'N': generateRandomNumbers(21, 30),
        'G': generateRandomNumbers(31, 40),
        'O': generateRandomNumbers(41, 50)
    };

    return convertToMap(playerCard);
}

function generateRandomNumbers(min, max) {
    const numbers = [];
    while (numbers.length < 5) {
        const num = Math.floor(Math.random() * (max - min + 1)) + min;
        if (!numbers.includes(num)) {
            numbers.push(num);
        }
    }
    return numbers.join(',');
}

function convertToMap(bingoCard) {
    const map = {};
    Object.keys(bingoCard).forEach(key => {
      map[key] = bingoCard[key].split(',');
    });
    return map;
}

export {
    generateBingoCard
}