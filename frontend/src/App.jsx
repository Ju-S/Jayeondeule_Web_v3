import React, {useEffect, useState} from "react";
import {useSelector, useDispatch} from "react-redux";
import {BrowserRouter} from "react-router-dom";

import Header from "./pages/common/Header.jsx";
import Footer from "./pages/common/Footer.jsx";
import {setUser} from "./store/auth/authSlice.js";
import {authUser} from "./utils/authUtil.js";
import AdminRoutes from "./routes/AdminRoutes.jsx";
import LoadingPage from "./pages/common/LoadingPage.jsx";
import GuestRoutes from "./routes/GuestRoutes.jsx";
import FarmAdminRoutes from "./routes/FarmAdminRoutes.jsx";
import MonitorRoutes from "./routes/MonitorRoutes.jsx";

export default function App() {
    // 인증 정보 관리
    const dispatch = useDispatch();
    const auth = useSelector(state => state.auth);

    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            const authFunc = async () => {
                try {
                    setIsLoading(true);
                    const res = await authUser();
                    await dispatch(setUser(res.data)); // 사용자 정보 Redux에 저장
                } catch (err) {
                    localStorage.removeItem("token"); // 토큰 만료 시 제거
                } finally {
                    setIsLoading(false);
                }
            }
            authFunc();
        } else {
            setIsLoading(false);
        }
    }, [dispatch]);

    if (isLoading) return (
        <LoadingPage/>
    );

    return (
        <BrowserRouter>
            <div className="d-flex flex-column min-vh-100">
                <Header/>

                <div className="flex-grow-1 mt-5 overflow-x-hidden">
                    {!auth.token ? (
                        <GuestRoutes/>
                    ) : auth.userInfo.authLvel === "ADMIN" ? (
                        <AdminRoutes/>
                    ) : auth.userInfo.authLvel === "FARM_ADMIN" ? (
                        <FarmAdminRoutes/>
                    ) : (
                        <MonitorRoutes/>
                    )}
                </div>

                <Footer/>
            </div>
        </BrowserRouter>
    )
}
