import React from "react";
import {Navigate, Route, Routes} from "react-router-dom";
import RegisterPage from "../pages/user/RegisterPage.jsx";
import LoginPage from "../pages/auth/LoginPage.jsx";

export default function GuestRoutes() {
    return (
        <Routes>
            <Route
                path="/register"
                element={<RegisterPage/>}
            />
            <Route
                path="/login"
                element={<LoginPage/>}
            />
            <Route path="*" element={<Navigate to="/login" replace/>}/>
        </Routes>
    )
}