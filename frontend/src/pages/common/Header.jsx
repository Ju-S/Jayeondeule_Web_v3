import React, {useEffect, useState} from "react";
import {Navbar, Container, Nav} from "react-bootstrap";
import {Link} from "react-router-dom";
import AdminNavLink from "./navLinks/AdminNavLink.jsx";
import CommonNavLink from "./navLinks/CommonNavLink.jsx";
import GuestNavLink from "./navLinks/GuestNavLink.jsx";
import {useSelector} from "react-redux";
import {getUser} from "../../utils/userUtil.js";

export default function Header() {
    const auth = useSelector(state => state.auth);

    return (
        <Navbar bg="light" variant="white" fixed="top">
            <Container>
                <Navbar.Brand as={Link} to="/">Jayeondeule</Navbar.Brand>
                <Nav className="ms-auto">
                    {auth.token != null ? (
                        <>
                            {auth.userInfo.authLvel === "ADMIN" && (
                                <AdminNavLink/>
                            )}
                            <CommonNavLink/>
                        </>
                    ) : (
                        <>
                            <GuestNavLink/>
                        </>
                    )}
                </Nav>
            </Container>
        </Navbar>
    );
}