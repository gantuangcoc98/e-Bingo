import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

export default function Player() {

    const [player, setPlayer] = useState({});

    const { playerToken } = useParams();

    const navigate = useNavigate();

    const handleQuitButton = () => {
        navigate('/');
    }

    useEffect(
        () => {
            fetchPlayer();
        }, []
    )

    const fetchPlayer = async () => {
        try {
            const player = await axios.get("http://localhost:8080/player/getPlayer?token=" + playerToken);
            if (player != null) {
                console.log(player.data);
                setPlayer(player.data);
            } else {
                console.log("Failed to fetch get request player/getPlayer?token");
            }
        } catch (error) {
            console.error("Error:", error)
        }
    }
    

    return (
        <div className='flex w-full h-screen'>
            <div className='flex flex-col gap-[20px] w-[40%] h-full'>
                <div className='flex gap-[10px] items-center'>
                    <button className='pt-[5px] pb-[5px] pl-[15px] pr-[15px] text-[16px] font-semibold border border-black hover:bg-slate-100'
                        onClick={()=>handleQuitButton()}>
                        Quit
                    </button>
                </div>

                <div className='flex flex-col gap-[10px]'>
                    <span className='text-[30px] font-semibold'>Player: {playerToken}</span>
                    <span className='text-[20px]'>{JSON.stringify(player)}</span>
                </div>
            </div>
        </div>
    )
}