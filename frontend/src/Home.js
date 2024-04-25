import { Routes, Route } from "react-router-dom"
import App from "./App"
import Game from "./Game"

export default function Home() {
    return (
        <div className="flex w-full h-screen">
            <Routes>
                <Route path="/" element={<App />} />
                <Route path="/game/:code" element={<Game />} />
            </Routes>
        </div>
    )
}