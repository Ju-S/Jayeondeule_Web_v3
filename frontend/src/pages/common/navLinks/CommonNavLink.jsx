import React from "react";
import {Nav, NavDropdown} from "react-bootstrap";
import {useDispatch} from "react-redux";
import {Link, useNavigate} from "react-router-dom";
import {logout} from "../../../store/auth/authSlice.js";

export default function CommonNavLink({userName}) {
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
            <NavDropdown title={userName} id="basic-nav-dropdown" align="end">
                <NavDropdown.Item as={Link} to="/farm-management">정보수정</NavDropdown.Item>
                <NavDropdown.Divider/>
                <NavDropdown.Item onClick={handleLogout}>로그아웃</NavDropdown.Item>
            </NavDropdown>
        </>
    )
}