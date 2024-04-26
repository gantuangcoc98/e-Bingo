import { Routes, Route } from "react-router-dom"
import Home from "./Home"
import Game from "./Game"
import App from "./App"
import Player from "./Player"

export default function Routing() {
    return (
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="game/:code" element={<Home />}>
                <Route path="display" element={<Game />} />
                <Route path="player/:playerToken" element={<Player />} />
            </Route>
        </Routes>
    )
}