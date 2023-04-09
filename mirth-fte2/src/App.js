// @ts-nocheck
import "./App.css";
import Login from "./components/Login";
import { Route, Routes } from "react-router-dom";
import { Patients } from "./pages/Patients";
import { Radiologists } from "./pages/Radiologists";
import { Home } from "./pages/Home";
import { Modalities } from "./pages/Modalities";
import { NoPage } from "./pages/NoPage";
import ResponsiveAppBar from "./components/toolbars/ResponsiveAppBar";
import { Mirth } from "./pages/Mirth";
import React from "react";
import { useSelector } from "react-redux";
import { CssBaseline } from "@mui/material";

const App = () => {
    const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

    return (
        <div className='App'>
            <CssBaseline />
            <ResponsiveAppBar />
            {!isAuthenticated && <Login />}
            {isAuthenticated && (
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='patients' element={<Patients />} />
                    <Route path='radiologists' element={<Radiologists />} />
                    <Route path='modalities' element={<Modalities />} />
                    <Route path='mirth' element={<Mirth />} />
                    <Route path='*' element={<NoPage />} />
                </Routes>
            )}
        </div>
    );
};
export default App;
