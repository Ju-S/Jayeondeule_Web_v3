import React from "react";
import {NavDropdown} from "react-bootstrap";
import {Link} from "react-router-dom";

export default function AdminNavLink() {

    return (
        <>
            <NavDropdown title="농장" id="basic-nav-dropdown">
                <NavDropdown.Item as={Link} to="/farm-management">농장 관리</NavDropdown.Item>
                <NavDropdown.Divider/>
                <NavDropdown.Item as={Link} to="/farm-register">농장 등록</NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="사용자 관리" id="basic-nav-dropdown">
                <NavDropdown.Item as={Link} to="/assign-farm-roles">권한부여</NavDropdown.Item>
            </NavDropdown>
        </>
    )
}