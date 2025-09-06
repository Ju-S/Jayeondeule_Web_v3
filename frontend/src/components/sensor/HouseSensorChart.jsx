import React, {useState} from "react";
import {Card, Col, Row, Spinner} from "react-bootstrap";
import SensorLineChart from "./SensorLineChart.jsx";

export default function HouseSensorChart({loading, sensorData}) {
    const [visibleLines, setVisibleLines] = useState({});

    return (
        <Row className="mt-3 mb-3">
            {loading ? (
                <Spinner animation="border"/>
            ) : (
                <Col>
                    <Card className="p-3 mb-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "indrTprtValu", name: "실내 온도", stroke: "rgba(159,130,15,0.61)", unit: "℃"},
                                {key: "oudrTprtValu", name: "실외 온도", stroke: "rgba(170,53,26,0.68)", unit: "℃"},
                            ]}
                            visibleLines={visibleLines}
                            setVisibleLines={setVisibleLines}
                        />
                    </Card>
                    <Card className="p-3 mb-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "indrHmdtValu", name: "실내 습도", stroke: "#6157fa", unit: "%"},
                                {key: "oudrHmdtValu", name: "실외 습도", stroke: "#007cbf", unit: "%"},
                            ]}
                            visibleLines={visibleLines}
                            setVisibleLines={setVisibleLines}
                        />
                    </Card>
                    <Card className="p-3 mb-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "co2Valu", name: "이산화탄소 농도", stroke: "#7d7d80", unit: "ppm"},
                            ]}
                            visibleLines={visibleLines}
                            setVisibleLines={setVisibleLines}
                        />
                    </Card>
                    <Card className="p-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "watrTprtValu", name: "수온", stroke: "#549c00", unit: "℃"},
                            ]}
                            visibleLines={visibleLines}
                            setVisibleLines={setVisibleLines}
                        />
                    </Card>
                </Col>
            )}
        </Row>
    )
}