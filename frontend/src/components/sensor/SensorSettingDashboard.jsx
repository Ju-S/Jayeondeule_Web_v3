import React, {useEffect, useState} from "react";
import {Card, Form, Row, Col, Button, InputGroup} from "react-bootstrap";
import {Dash} from "react-bootstrap-icons";
import {deleteSetting, getSettings, insertSettings, patchSetting} from "../../utils/settingUtil.js";

export default function SensorSettingDashboard({farmId, selectedHouse}) {
    const [sensorSetting, setSensorSetting] = useState({
        tprtMin: "",
        tprtMax: "",
        watrTprtMin: "",
        watrTprtMax: "",
        hmdtMin: "",
        hmdtMax: "",
        co2Min: "",
        co2Max: "",
    });

    const [lightSchedules, setLightSchedules] = useState([]);
    const [waterSchedules, setWaterSchedules] = useState([]);

    const handleScheduleChange = (index, type, field, value) => {
        const newSchedules = type === "LIGHT" ? [...lightSchedules] : [...waterSchedules];
        newSchedules[index][field] = value;
        type === "LIGHT" ? setLightSchedules(newSchedules) : setWaterSchedules(newSchedules);

        patchSetting(farmId, selectedHouse, newSchedules[index]);
    };

    const addSchedule = (type) => {
        const newSchedule = {strtTime: "00:00", fnshTime: "00:00", dlteYn: false, unitType: type};
        if (type === "LIGHT") {
            setLightSchedules([...lightSchedules, newSchedule]);
        } else {
            setWaterSchedules([...waterSchedules, newSchedule]);
        }
        insertSettings(farmId, selectedHouse, {lightIrrigationSettingInsertDTO: newSchedule}).then(fetchSettings);
    };

    const deleteSchedule = (idx, type) => {
        if (type === "LIGHT") {
            deleteSetting(farmId, selectedHouse, lightSchedules[idx].setnDttm);
            setLightSchedules(prev => prev.filter((_, i) => i !== idx));
        } else {
            deleteSetting(farmId, selectedHouse, waterSchedules[idx].setnDttm);
            setWaterSchedules(prev => prev.filter((_, i) => i !== idx));
        }
    };

    useEffect(() => {
        fetchSettings();
    }, [selectedHouse]);

    const handleSensorSettingChange = (type, value) => {
        setSensorSetting({...sensorSetting, [type]: value});
    }

    const handleSensorSettingInsert = () => {
        insertSettings(farmId, selectedHouse, {sensorSettingInsertDTO: sensorSetting});
    }

    const fetchSettings = () => getSettings(farmId, selectedHouse).then((res) => {
        const newSensorSetting = res.data.sensorSettingDTO;
        if (newSensorSetting) {
            newSensorSetting.setnDttm = "";
            setSensorSetting(newSensorSetting);
        } else {
            setSensorSetting({
                tprtMin: "",
                tprtMax: "",
                watrTprtMin: "",
                watrTprtMax: "",
                hmdtMin: "",
                hmdtMax: "",
                co2Min: "",
                co2Max: "",
            });
        }

        setLightSchedules([]);
        setWaterSchedules([]);
        res.data.lightIrrigationSettingDTO.map(item => {
            const newSchedule = {
                strtTime: item.strtTime,
                fnshTime: item.fnshTime,
                dlteYn: item.dlteYn,
                setnDttm: item.setnDttm,
                unitType: item.unitType
            };

            if (item.unitType === "LIGHT") {
                setLightSchedules(prev => [...prev, newSchedule]);
            } else {
                setWaterSchedules(prev => [...prev, newSchedule]);
            }
        });
    });

    return (
        <div className="p-3">
            <Card className="mb-3 p-3">
                <h5>온도(℃)</h5>
                <Row className="mb-2">
                    <Col>
                        <InputGroup>
                            <Form.Control
                                value={sensorSetting && sensorSetting.tprtMin}
                                onChange={(e) => handleSensorSettingChange("tprtMin", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최소 온도"
                            />
                            <InputGroup.Text>~</InputGroup.Text>
                            <Form.Control
                                value={sensorSetting && sensorSetting.tprtMax}
                                onChange={(e) => handleSensorSettingChange("tprtMax", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최대 온도"
                            />
                        </InputGroup>
                    </Col>
                </Row>
                <h5>수온(℃)</h5>
                <Row className="mb-2">
                    <Col>
                        <InputGroup>
                            <Form.Control
                                value={sensorSetting && sensorSetting.watrTprtMin}
                                onChange={(e) => handleSensorSettingChange("watrTprtMin", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최소 수온"
                            />
                            <InputGroup.Text>~</InputGroup.Text>
                            <Form.Control
                                value={sensorSetting && sensorSetting.watrTprtMax}
                                onChange={(e) => handleSensorSettingChange("watrTprtMax", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최대 수온"
                            />
                        </InputGroup>
                    </Col>
                </Row>
                <h5>습도(%)</h5>
                <Row className="mb-2">
                    <Col>
                        <InputGroup>
                            <Form.Control
                                value={sensorSetting && sensorSetting.hmdtMin}
                                onChange={(e) => handleSensorSettingChange("hmdtMin", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최소 습도"
                            />
                            <InputGroup.Text>~</InputGroup.Text>
                            <Form.Control
                                value={sensorSetting && sensorSetting.hmdtMax}
                                onChange={(e) => handleSensorSettingChange("hmdtMax", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최대 습도"
                            />
                        </InputGroup>
                    </Col>
                </Row>
                <h5>이산화탄소(ppm)</h5>
                <Row className="mb-2">
                    <Col>
                        <InputGroup>
                            <Form.Control
                                value={sensorSetting && sensorSetting.co2Min}
                                onChange={(e) => handleSensorSettingChange("co2Min", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최소 CO₂"
                            />
                            <InputGroup.Text>~</InputGroup.Text>
                            <Form.Control
                                value={sensorSetting && sensorSetting.co2Max}
                                onChange={(e) => handleSensorSettingChange("co2Max", e.target.value)}
                                onBlur={handleSensorSettingInsert}
                                type="number"
                                placeholder="최대 CO₂"
                            />
                        </InputGroup>
                    </Col>
                </Row>
            </Card>

            <Card className="mb-3 p-3">
                <h5>조명 시간 설정</h5>
                {lightSchedules.map((schedule, idx) => (
                    <Row key={idx} className="align-items-center mb-2">
                        <Col>
                            <InputGroup>
                                <Form.Control type="time" value={schedule.strtTime}
                                              onChange={(e) => handleScheduleChange(idx, "LIGHT", "strtTime", e.target.value)}/>
                                <InputGroup.Text>~</InputGroup.Text>
                                <Form.Control type="time" value={schedule.fnshTime}
                                              onChange={(e) => handleScheduleChange(idx, "LIGHT", "fnshTime", e.target.value)}/>
                                <InputGroup.Text>
                                    <Form.Check
                                        type="switch"
                                        checked={!schedule.dlteYn}
                                        onClick={() => handleScheduleChange(idx, 'LIGHT', 'dlteYn', !schedule.dlteYn)}
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
                                    onClick={() => deleteSchedule(idx, 'LIGHT')}
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
                {waterSchedules.map((schedule, idx) => (
                    <Row key={idx} className="align-items-center mb-2">
                        <Col>
                            <InputGroup>
                                <Form.Control type="time" value={schedule.strtTime}
                                              onChange={(e) => handleScheduleChange(idx, "WATER", "strtTime", e.target.value)}/>
                                <InputGroup.Text>~</InputGroup.Text>
                                <Form.Control type="time" value={schedule.fnshTime}
                                              onChange={(e) => handleScheduleChange(idx, "WATER", "fnshTime", e.target.value)}/>
                                <InputGroup.Text>
                                    <Form.Check
                                        type="switch"
                                        checked={!schedule.dlteYn}
                                        onClick={() => handleScheduleChange(idx, 'WATER', 'dlteYn', !schedule.dlteYn)}
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
                                    onClick={() => deleteSchedule(idx, 'WATER')}
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
