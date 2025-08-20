import React from "react";
import {Nav} from "react-bootstrap";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {logout} from "../../../store/auth/authSlice.js";

export default function CommonNavLink() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleLogout = (e) => {
        e.preventDefault(); // href 이동 방지
        try {
            dispatch(logout()); // Redux 상태 초기화
            navigate("/login"); // 로그인 페이지로 이동
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <>
            <Nav.Link onClick={handleLogout}>로그아웃</Nav.Link>
        </>
    )
}