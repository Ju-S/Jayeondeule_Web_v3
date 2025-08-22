import React from "react";
import {Navigate, Route, Routes} from "react-router-dom";
import PrivateRoute from "../pages/common/route/PrivateRoute.jsx";
import FarmManagementPage from "../pages/farm/FarmManagementPage.jsx";
import FarmRegisterPage from "../pages/farm/FarmRegisterPage.jsx";
import ErrorPage from "../pages/common/ErrorPage.jsx";

export default function FarmAdminRoutes() {
    return (
        <Routes>
            <Route
                path="/"
                element={<Navigate to="/farm-management" replace/>
                }
            />
            <Route
                path="/farm-management"
                element={
                    <PrivateRoute>
                        <FarmManagementPage/>
                    </PrivateRoute>
                }
            />
            <Route
                path="/farm-register"
                element={
                    <PrivateRoute>
                        <FarmRegisterPage/>
                    </PrivateRoute>
                }
            />
            <Route path="*" element={<ErrorPage/>}/>
        </Routes>
    )
}