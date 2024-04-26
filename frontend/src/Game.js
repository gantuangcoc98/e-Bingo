import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import hostGameCard from './data/host-game-card.json';

export default function Game() {
    const { code } = useParams();

    const [gameCode, setGameCode] = useState('');
    const [game, setGame] = useState({});
    const [gameReady, setGameReady] = useState(false);

    const navigate = useNavigate();

    useEffect(
        () => {
            fetchGame();
        }, [gameReady]
    )

    const fetchGame = async () => {
        try {
            const url = "http://localhost:8080/game/getGame?code=" + code;
            const _game = await axios.get(url);
            if (_game.data) {
                setGame(_game.data);
                setGameCode(code);
                setGameReady(true);

                console.log("Successfully fetched game:", game);
            } else {
                console.log("Game not found!");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    }

    const createNewGame = async () => {
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
                navigate(`/game/${gameHost.gameCode}/display`);
                window.location.reload();
                console.log("New game:", game.data);
            } else {
                console.log("Failed to fetch post request game/create!");
            }
        
            } catch (error) {
            console.error('Error:', error);
        }
    }

    const handleHomeButton = () => {
        navigate('/');
    }

    return (
        <>
            <div className="flex w-full h-full">
                {gameReady ? 
                    <div className="flex flex-col gap-[10px] h-full w-[30%]">
                        <div className="flex gap-[10px] items-center">
                            <button className="text-[16px] w-fit h-fit font-semibold p-[5px] border border-black hover:bg-slate-100"
                                onClick={()=>createNewGame()}>
                                Create New Game
                            </button>
                            <button className='border border-black p-[5px] w-fit h-fit text-[16px] font-semibold hover:bg-slate-100'
                                onClick={()=>handleHomeButton()}>
                                Home
                            </button>
                        </div>
                        <span className="text-[30px] font-semibold">Game Code: {gameCode}</span>
                        <span className="h-fit w-full">{JSON.stringify(game)}</span>
                    </div>
                    :
                    <span className="text-[30px] font-semibold">Game not found!</span>
                }
            </div>
        </>
    )
}