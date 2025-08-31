import React from "react";
import {Navigate, Route, Routes} from "react-router-dom";
import PrivateRoute from "../pages/common/route/PrivateRoute.jsx";
import ErrorPage from "../pages/common/ErrorPage.jsx";
import MyPage from "../pages/user/MyPage.jsx";
import PasswordChangePage from "../pages/user/PasswordChangePage.jsx";
import FarmMonitoringPage from "../pages/farm/FarmMonitoringPage.jsx";
import SensorSettingDashboard from "../components/sensor/SensorSettingDashboard.jsx";

export default function CommonRoutes() {
    return (
        <Routes>
            <Route
                path="/"
                element={
                    <PrivateRoute>
                        <FarmMonitoringPage/>
                    </PrivateRoute>
                }
            />
            <Route
                path="/mypage"
                element={
                    <PrivateRoute>
                        <MyPage/>
                    </PrivateRoute>
                }
            />
            <Route
                path="/mypage/password"
                element={
                    <PrivateRoute>
                        <PasswordChangePage/>
                    </PrivateRoute>
                }
            />
            <Route
                path="/farm/:farmId/monitor"
                element={
                    <PrivateRoute>
                        <FarmMonitoringPage/>
                    </PrivateRoute>
                }
            />
            <Route
                path="/farm/:farmId/house/:houseId/sensor/setting"
                element={
                    <PrivateRoute>
                        <SensorSettingDashboard/>
                    </PrivateRoute>
                }
            />
            <Route path="*" element={<ErrorPage/>}/>
        </Routes>
    )
}