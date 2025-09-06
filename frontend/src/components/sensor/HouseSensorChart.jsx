import React, {useState} from "react";
import {Card, Col, Row, Spinner} from "react-bootstrap";
import SensorLineChart from "./SensorLineChart.jsx";

export default function HouseSensorChart({loading, sensorData}) {
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
                        />
                    </Card>
                    <Card className="p-3 mb-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "indrHmdtValu", name: "실내 습도", stroke: "#6157fa", unit: "%"},
                                {key: "oudrHmdtValu", name: "실외 습도", stroke: "#007cbf", unit: "%"},
                            ]}
                        />
                    </Card>
                    <Card className="p-3 mb-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "co2Valu", name: "이산화탄소 농도", stroke: "#7d7d80", unit: "ppm"},
                            ]}
                        />
                    </Card>
                    <Card className="p-3">
                        <SensorLineChart
                            data={sensorData}
                            lineConfig={[
                                {key: "watrTprtValu", name: "수온", stroke: "#549c00", unit: "℃"},
                            ]}
                        />
                    </Card>
                </Col>
            )}
        </Row>
    )
}