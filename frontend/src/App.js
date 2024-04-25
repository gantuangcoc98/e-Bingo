import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import hostGameCard from './data/host-game-card.json';

function App() {

  const [inputGameCode, setInputGameCode] = useState('');

  const navigate = useNavigate();

  const handleCreateGame = async () => {
    try {
      const generatedGameCode = await axios.get("http://localhost:8080/game/generateCode");

      const players = []

      const gameHost = {
        'gameCode': generatedGameCode.data,
        'players': players,
        'gameCard': hostGameCard
      }

      const game = await axios.post("http://localhost:8080/game/create", gameHost)
      if (game.data) {
        navigate(`game/${gameHost.gameCode}`);

        console.log("New game:", game.data);
      } else {
        console.log("Failed to fetch post request game/create!");
      }

    } catch (error) {
      console.error('Error:', error);
    }
  }

  const handleJoinGame = async () => {

  }

  return (
    <div className='flex w-full h-screen items-center justify-center'>
      <div className='flex w-[20%] h-fit border border-black'>
        <div className='flex flex-col gap-[20px] w-full h-full items-start'>
          <button className='border border-black p-[5px] w-full h-fit' 
            onClick={()=>handleCreateGame()}>
            Create New Game
          </button>

          <div className='flex flex-col items-end w-full h-fit'>
            <label htmlFor='inputGameCode' className='w-fit h-fit text-[20px] font-semibold'>
              Join a game
              <input 
                id='inputGameCode'
                type='text'
                value={inputGameCode}
                onChange={(e) => setInputGameCode(e.target.value)}
                placeholder='Input game code here'
                className='p-[10px] font-normal text-[16px]'
              />
            </label>

            <button className='border border-black p-[5px] '
              onClick={()=>handleJoinGame()}>
                Join Game
            </button>
          
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
