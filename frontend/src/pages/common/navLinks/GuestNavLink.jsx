import React from "react";
import {Nav} from "react-bootstrap";

export default function GuestNavLink() {
    return (
        <>
            <Nav.Link href="/login">로그인</Nav.Link>
            <Nav.Link href="/register">회원가입</Nav.Link>
        </>
    )
}