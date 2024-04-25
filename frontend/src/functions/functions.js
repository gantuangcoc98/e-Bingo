import axios from "axios";

function generateBingoCard() {
    const playerCard = {
        'B': generateRandomNumbers(1, 10),
        'I': generateRandomNumbers(11, 20),
        'N': generateRandomNumbers(21, 30),
        'G': generateRandomNumbers(31, 40),
        'O': generateRandomNumbers(41, 50)
    };

    console.log(playerCard);
    return playerCard;
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

const generatePlayerToken = async () => {
    try {
      const response = await axios.get("http://localhost:8080/player/generateToken");
      if (response.data) {
        const _playerToken = response.data;
        console.log(_playerToken);
        return _playerToken;
      }
    } catch (error) {
      console.error('Error:', error);
    }

    return null;
}

export {
    generateBingoCard,
    generatePlayerToken
}