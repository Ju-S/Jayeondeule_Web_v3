import React from "react";
import {Col, Container, Row, Spinner} from "react-bootstrap";

export default function LoadingPage() {
    return (
        <Container fluid className="vh-100 d-flex justify-content-center align-items-center">
            <Row>
                <Col className="text-center">
                    <Spinner animation="border" role="status" variant="primary" />
                    <p className="mt-3">로딩 중...</p>
                </Col>
            </Row>
        </Container>
    );
}