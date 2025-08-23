import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Card, Container, Row, Col, Spinner, Nav, Form, Button, InputGroup } from "react-bootstrap";
import { getHouseList } from "../../utils/houseUtil.js";
import { getSensorData } from "../../utils/sensorUtil.js";
import SensorLineChart from "../../components/sensor/SensorLineChart.jsx";

export default function FarmMonitoringPage() {
    const { farmId } = useParams();
    const [houses, setHouses] = useState([]);
    const [selectedHouse, setSelectedHouse] = useState(null);
    const [sensorData, setSensorData] = useState([]);
    const [loading, setLoading] = useState(true);

    // 날짜 선택 state
    const [startDate, setStartDate] = useState(new Date().toISOString().split("T")[0]);
    const [endDate, setEndDate] = useState(new Date().toISOString().split("T")[0]);

    // 농장 하우스 리스트 가져오기
    useEffect(() => {
        const fetchHouses = async () => {
            try {
                const res = await getHouseList({ farmId });
                setHouses(res.data);
                if (res.data.length > 0) {
                    setSelectedHouse(res.data[0].housId);
                }
            } catch (err) {
                console.error(err);
            }
        };
        fetchHouses();
    }, [farmId]);

    // 선택된 하우스의 센서 데이터 가져오기
    const fetchSensorData = async () => {
        if (!selectedHouse) return;
        setLoading(true);
        try {
            const startDateTime = `${startDate}T00:00:00`;
            const endDateTime = `${endDate}T23:59:59`;

            const res = await getSensorData(farmId, selectedHouse, startDateTime, endDateTime);

            setSensorData(res.data);
        } catch (err) {
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    // 기본 데이터 로드 (최초 마운트 / 하우스 변경 시)
    useEffect(() => {
        fetchSensorData();
    }, [selectedHouse]);

    return (
        <Container className="mt-4">
            <h3>{farmId} 현황</h3>

            {/* 하우스 선택 탭 */}
            <Nav variant="tabs" activeKey={selectedHouse} onSelect={(key) => setSelectedHouse(key)}>
                {houses.map((house) => (
                    <Nav.Item key={house.housId}>
                        <Nav.Link eventKey={house.housId}>{house.housName}</Nav.Link>
                    </Nav.Item>
                ))}
            </Nav>

            {/* 날짜 범위 선택 */}
            <Form className="d-flex align-items-center gap-2 mt-3">
                <InputGroup>
                    <Form.Control
                        type="date"
                        value={startDate}
                        onChange={(e) => setStartDate(e.target.value)}
                    />
                    <InputGroup.Text>~</InputGroup.Text>
                    <Form.Control
                        type="date"
                        value={endDate}
                        onChange={(e) => setEndDate(e.target.value)}
                    />
                    <Button variant="success" onClick={fetchSensorData}>
                        조회
                    </Button>
                </InputGroup>
            </Form>

            {/* line chart로 표시 */}
            <Row className="mt-3 mb-3">
                <Col>
                    <Card className="p-3">
                        {loading ? (
                            <Spinner animation="border"/>
                        ) : (
                            <SensorLineChart
                                data={sensorData}
                            />
                        )}
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}
