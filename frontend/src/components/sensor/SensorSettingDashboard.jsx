import React, {useState} from "react";
import {Card, Form, Row, Col, Button, InputGroup} from "react-bootstrap";
import {Dash} from "react-bootstrap-icons";

export default function SensorSettingDashboard() {
    const [lightingSchedules, setLightingSchedules] = useState([
        {strtTime: "", fnshTime: "", enable: false}
    ]);
    const [wateringSchedules, setWateringSchedules] = useState([
        {strtTime: "", fnshTime: "", enable: false}
    ]);

    const handleScheduleChange = (index, type, field, value) => {
        const newSchedules = type === "LIGHT" ? [...lightingSchedules] : [...wateringSchedules];
        newSchedules[index][field] = value;
        type === "LIGHT" ? setLightingSchedules(newSchedules) : setWateringSchedules(newSchedules);
    };

    const addSchedule = (type) => {
        const newSchedule = {strtTime: "", fnshTime: "", enable: false};
        if (type === "LIGHT") {
            setLightingSchedules([...lightingSchedules, newSchedule]);
        } else {
            setWateringSchedules([...wateringSchedules, newSchedule]);
        }
    };

    return (
        <div className="p-3">
            <Card className="mb-3 p-3">
                <h5>온도(℃)</h5>
                <Row className="mb-2">
                    <Col><Form.Control type="number" placeholder="최소 온도"/></Col>
                    <Col><Form.Control type="number" placeholder="최대 온도"/></Col>
                </Row>
                <h5>습도(%)</h5>
                <Row className="mb-2">
                    <Col><Form.Control type="number" placeholder="최소 습도"/></Col>
                    <Col><Form.Control type="number" placeholder="최대 습도"/></Col>
                </Row>
                <h5>이산화탄소(ppm)</h5>
                <Row className="mb-2">
                    <Col><Form.Control type="number" placeholder="최소 CO₂"/></Col>
                    <Col><Form.Control type="number" placeholder="최대 CO₂"/></Col>
                </Row>
            </Card>

            <Card className="mb-3 p-3">
                <h5>조명 시간 설정</h5>
                {lightingSchedules.map((schedule, idx) => (
                    <Row key={idx} className="align-items-center mb-2">
                        <Col>
                            <InputGroup>
                                <Form.Control type="time" value={schedule.strtTime}
                                              onChange={(e) => handleScheduleChange(idx, "LIGHT", "start", e.target.value)}/>
                                <InputGroup.Text>~</InputGroup.Text>
                                <Form.Control type="time" value={schedule.fnshTime}
                                              onChange={(e) => handleScheduleChange(idx, "LIGHT", "end", e.target.value)}/>
                                <InputGroup.Text>
                                    <Form.Check
                                        type="switch"
                                        checked={schedule.enable}
                                        onClick={() => handleScheduleChange(idx, 'LIGHT', 'enabled', !schedule.enable)}
                                        disabled={!(schedule.strtTime && schedule.fnshTime)}
                                    />
                                </InputGroup.Text>
                                <Button
                                    style={{
                                        maxWidth: "20px",
                                        display: "flex",
                                        alignItems: "center",
                                        justifyContent: "center",
                                        padding: "0"
                                    }}
                                    variant="danger"
                                >
                                    <Dash/>
                                </Button>
                            </InputGroup>
                        </Col>
                    </Row>
                ))}
                <Button variant="success" size="sm" onClick={() => addSchedule("LIGHT")}>+ 시간 추가</Button>
            </Card>

            <Card className="p-3">
                <h5>관수 시간 설정</h5>
                {wateringSchedules.map((schedule, idx) => (
                    <Row key={idx} className="align-items-center mb-2">
                        <Col>
                            <InputGroup>
                                <Form.Control type="time" value={schedule.strtTime}
                                              onChange={(e) => handleScheduleChange(idx, "WATER", "start", e.target.value)}/>
                                <InputGroup.Text>~</InputGroup.Text>
                                <Form.Control type="time" value={schedule.fnshTime}
                                              onChange={(e) => handleScheduleChange(idx, "WATER", "end", e.target.value)}/>
                                <InputGroup.Text>
                                    <Form.Check
                                        type="switch"
                                        checked={schedule.enable}
                                        onClick={() => handleScheduleChange(idx, 'WATER', 'enabled', !schedule.enable)}
                                        disabled={!(schedule.strtTime && schedule.fnshTime)}
                                    />
                                </InputGroup.Text>
                                <Button
                                    style={{
                                        maxWidth: "20px",
                                        display: "flex",
                                        alignItems: "center",
                                        justifyContent: "center",
                                        padding: "0"
                                    }}
                                    variant="danger"
                                >
                                    <Dash/>
                                </Button>
                            </InputGroup>
                        </Col>
                    </Row>
                ))}
                <Button variant="success" size="sm" onClick={() => addSchedule("WATER")}>+ 시간 추가</Button>
            </Card>
        </div>
    );
}
