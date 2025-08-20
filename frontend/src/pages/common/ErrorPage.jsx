import React from "react";
import { Container, Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export default function ErrorPage({ code = 404, message = "페이지를 찾을 수 없습니다." }) {
    const navigate = useNavigate();

    const handleHome = () => {
        navigate("/"); // 홈으로 이동
    };

    return (
        <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: "80vh" }}>
            <Card className="text-center p-4" style={{ maxWidth: "500px", width: "100%", border: "0" }}>
                <Card.Body>
                    <Card.Title className="display-1">{code}</Card.Title>
                    <Card.Text className="lead">{message}</Card.Text>
                    <Button variant="primary" onClick={handleHome}>
                        홈으로 돌아가기
                    </Button>
                </Card.Body>
            </Card>
        </Container>
    );
}