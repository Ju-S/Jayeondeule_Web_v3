import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {Container, Form, Button, InputGroup, Tabs, Tab} from "react-bootstrap";
import {getHouseList} from "../../utils/houseUtil.js";
import {getSensorData} from "../../utils/sensorUtil.js";
import FarmKebabMenu from "../../components/farm/FarmKebabMenu.jsx";
import {getFarm} from "../../utils/farmUtil.js";
import {useSelector} from "react-redux";
import LatestSensorMonitor from "../../components/house/LatestSensorMonitor.jsx";
import HouseSensorChart from "../../components/house/HouseSensorChart.jsx";
import HouseList from "../../components/house/HouseList.jsx";
import "./FarmMonitoringPage.css";
import RelayDashboard from "../../components/house/RelayDashboard.jsx";

export default function FarmMonitoringPage() {
    const auth = useSelector(state => state.auth);

    const {farmId} = useParams();
    const [farm, setFarm] = useState(null);
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
                const res = await getHouseList({farmId});
                setHouses(res.data);
                if (res.data.length > 0) {
                    setSelectedHouse(res.data[0].housId);
                }
            } catch (err) {
                console.error(err);
            }
        };

        const fetchFarm = async () => {
            try {
                const res = await getFarm({farmId});
                setFarm(res.data);
            } catch (err) {
                console.error(err);
            }
        };

        fetchHouses();
        fetchFarm();
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
            <div className="d-flex justify-content-between align-items-center mb-3">
                {farm && (
                    auth.userInfo.authLvel === "ADMIN" ? (
                        <>
                            <h3 className="mb-0">{farm.farmName} 현황</h3>
                            <FarmKebabMenu farmId={farmId}/>
                        </>
                    ) : (
                        <h3 className="mb-0">{farm.farmName} 현황</h3>
                    )
                )}
            </div>

            {/* 농장의 재배사 리스트 */}
            <HouseList
                selectedHouse={selectedHouse}
                authLvel={auth.userInfo.authLvel}
                houses={houses}
                setSelectedHouse={setSelectedHouse}
                farmId={farmId}
            />

            {/* 최신 센서 데이터 */}
            <LatestSensorMonitor sensorData={sensorData}/>

            <Tabs id="custom-tabs">
                <Tab
                    eventKey="sensor"
                    title={<><span className="d-none d-sm-inline">센서 기록 조회</span><span className="d-inline d-sm-none">센서</span></>}
                >
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
                    <HouseSensorChart
                        loading={loading}
                        sensorData={sensorData}
                    />
                </Tab>
                <Tab
                    eventKey="relay"
                    title={<><span className="d-none d-sm-inline">릴레이 현황 및 조작</span><span className="d-inline d-sm-none">릴레이</span></>}
                >
                    <RelayDashboard farmId={farmId} houseId={selectedHouse}/>
                </Tab>
                <Tab
                    eventKey="memo"
                    title={<><span className="d-none d-sm-inline">재배사 메모</span><span className="d-inline d-sm-none">메모</span></>}
                >

                </Tab>
            </Tabs>
        </Container>
    );
}
