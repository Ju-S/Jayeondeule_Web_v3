import React, {useEffect, useState} from "react";
import {NavDropdown} from "react-bootstrap";
import {useDispatch} from "react-redux";
import {Link, useNavigate} from "react-router-dom";
import {logout} from "../../../store/auth/authSlice.js";
import {getUser} from "../../../utils/userUtil.js";

export default function CommonNavLink() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [userInfo, setUserInfo] = useState({
        userName: "",   // 서버에서 가져온 사용자 정보로 초기화
        authLvel: "",
    });

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const res = await getUser(); // GET /users/me
                setUserInfo({
                    userName: res.data.userName,
                    authLvel: res.data.authLvel,
                });
            } catch (err) {
                console.error(err);
            }
        };
        fetchUser();
    }, []);

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
            <NavDropdown title={userInfo.userName} id="basic-nav-dropdown" align="end">
                <NavDropdown.Item as={Link} to="/mypage">정보수정</NavDropdown.Item>
                <NavDropdown.Divider/>
                <NavDropdown.Item onClick={handleLogout}>로그아웃</NavDropdown.Item>
            </NavDropdown>
        </>
    )
}