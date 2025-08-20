import React, {useEffect, useState} from "react";
import {useSelector, useDispatch} from "react-redux";
import {BrowserRouter, Routes, Route, Navigate} from "react-router-dom";

import Header from "./pages/common/Header.jsx";
import Footer from "./pages/common/Footer.jsx";
import LoginPage from "./pages/auth/LoginPage.jsx";
import RegisterPage from "./pages/user/RegisterPage.jsx";
import FarmManagementPage from "./pages/farm/FarmManagementPage.jsx";
import FarmRegisterPage from "./pages/farm/FarmRegisterPage.jsx";
import api from "./utils/common/jwtUtil.js";
import {setUser} from "./store/auth/authSlice.js";
import PrivateRoute from "./pages/common/route/PrivateRoute.jsx";
import ErrorPage from "./pages/common/ErrorPage.jsx";

export default function App() {
    // 인증 정보 관리
    const dispatch = useDispatch();

    const auth = useSelector(state => state.auth);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            api.get("/api/auth")
                .then(res => {
                    dispatch(setUser(res.data)); // 사용자 정보 Redux에 저장
                })
                .catch(() => {
                    localStorage.removeItem("token"); // 토큰 만료 시 제거
                });
        }
    }, [dispatch]);

    return (
        <BrowserRouter>
            <div className="d-flex flex-column min-vh-100">
                {/* Header: 로그인 상태에 따라 메뉴 변경 */}
                <Header/>

                {/* 페이지 영역 */}
                <div className="flex-grow-1">
                    <Routes>
                        {!auth.token ? (
                            <>
                                <Route
                                    path="/register"
                                    element={<RegisterPage/>}
                                />
                                <Route
                                    path="/login"
                                    element={<LoginPage/>}
                                />
                                <Route path="*" element={<Navigate to="/login" replace/>}/>
                            </>

                        ) : (
                            <>
                                <Route
                                    path="/"
                                    element={
                                        <PrivateRoute>
                                            <FarmManagementPage/>
                                        </PrivateRoute>
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
                            </>
                        )}
                    </Routes>
                </div>
                {/* Footer는 공통 */}
                <Footer/>
            </div>
        </BrowserRouter>
    )
}
