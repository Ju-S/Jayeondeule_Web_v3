import React from "react";
import {Navigate, Route, Routes} from "react-router-dom";
import PrivateRoute from "../pages/common/route/PrivateRoute.jsx";
import FarmManagementPage from "../pages/farm/FarmManagementPage.jsx";
import FarmRegisterPage from "../pages/farm/FarmRegisterPage.jsx";
import CommonRoutes from "./CommonRoutes.jsx";

export default function MonitorRoutes() {
    return (
        <Routes>
            <Route path="*" element={<CommonRoutes/>}/>
        </Routes>
    )
}