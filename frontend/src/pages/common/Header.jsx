import React from "react";
import {Navbar, Container, Nav} from "react-bootstrap";
import {Link} from "react-router-dom";
import AdminNavLink from "./navLinks/AdminNavLink.jsx";
import CommonNavLink from "./navLinks/CommonNavLink.jsx";
import GuestNavLink from "./navLinks/GuestNavLink.jsx";
import {useSelector} from "react-redux";

export default function Header() {
    const auth = useSelector((state) => state.auth);

    return (
        <Navbar bg="light" variant="white">
            <Container>
                <Navbar.Brand as={Link} to="/">Jayeondeule</Navbar.Brand>
                <Nav className="ms-auto">
                    {auth.userInfo != null ? (
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