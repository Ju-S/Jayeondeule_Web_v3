import React from "react";
import {Button, Card, Col} from "react-bootstrap";

export default function RelayCard({relayNum, label, relayStatus, handleToggle, manualMode, toggleRelayMutation}) {
    const key = `relay${relayNum}stFlag`;
    const isOn = relayStatus[key];

    return (
        <Col xs={6} md={4} lg={3} key={key} className="mb-3">
            <Card
                className={`text-center shadow-sm ${
                    isOn ? "border-success" : "border-secondary"
                }`}
            >
                <Card.Body>
                    <Card.Title className="mb-2">{label}</Card.Title>
                    <Button
                        variant={isOn ? "success" : "outline-secondary"}
                        className="w-100"
                        onClick={() => handleToggle(relayNum)}
                        disabled={!manualMode || toggleRelayMutation.isLoading}
                    >
                        {isOn ? "ON" : "OFF"}
                    </Button>
                </Card.Body>
            </Card>
        </Col>
    );
}