import React, {useState, useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {Container, Tabs, Tab, Accordion} from "react-bootstrap";
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";

import {getHouseList} from "../../utils/houseUtil.js";
import {getLatestSensorData, getSensorData} from "../../utils/sensorUtil.js";
import {getFarm} from "../../utils/farmUtil.js";

import FarmKebabMenu from "../../components/farm/FarmKebabMenu.jsx";
import HouseList from "../../components/house/HouseList.jsx";
import LatestSensorMonitor from "../../components/sensor/LatestSensorMonitor.jsx";
import HouseSensorDashboard from "../../components/sensor/HouseSensorDashboard.jsx";
import RelayDashboard from "../../components/relay/RelayDashboard.jsx";
import LoadingPage from "../../pages/common/LoadingPage.jsx";
import ErrorPage from "../../pages/common/ErrorPage.jsx";

import "./FarmMonitoringPage.css";
import MemoDashboard from "../../components/memo/MemoDashboard.jsx";
import {getUser} from "../../utils/userUtil.js";
import SensorSettingDashboard from "../../components/sensor/SensorSettingDashboard.jsx";
import LatestSensorItem from "../../components/sensor/LatestSensorItem.jsx";
import LatestSensorSelected from "../../components/sensor/LatestSensorSelected.jsx";

export default function FarmMonitoringPage() {
    const {farmId} = useParams();
    const auth = useSelector(state => state.auth);
    const navigate = useNavigate();

    const [isAccordionOpened, setIsAccordionOpened] = useState(true);

    const [selectedHouse, setSelectedHouse] = useState(null);
    const [startDate, setStartDate] = useState(new Date().toISOString().split("T")[0]);
    const [endDate, setEndDate] = useState(new Date().toISOString().split("T")[0]);

    // 농장 정보
    const {data: farm, isLoading: farmLoading, error: farmError} = useQuery({
        queryKey: ["farm", farmId],
        queryFn: () => getFarm({farmId}).then(res => res.data),
        enabled: !!farmId,
    });

    // 재배사 리스트
    const {data: houses = [], isLoading: housesLoading, error: housesError} = useQuery({
        queryKey: ["houses", farmId],
        queryFn: () => getHouseList({farmId}).then(res => res.data),
        enabled: !!farmId,
    });

    // 선택된 하우스 센서 데이터
    const {
        data: sensorData = [],
        isLoading: sensorLoading,
        error: sensorError,
        refetch: refetchSensor
    } = useQuery({
        queryKey: ["sensorData", farmId, selectedHouse],
        queryFn: () => {
            if (!selectedHouse) return [];
            const startDateTime = `${startDate}T00:00:00`;
            const endDateTime = `${endDate}T23:59:59`;
            return getSensorData(farmId, selectedHouse.housId, startDateTime, endDateTime).then(res => res.data);
        },
        enabled: !!selectedHouse,
    });

    // 선택된 하우스 센서 데이터
    const {
        data: latestSensorData = {},
        isLoading: latestSensorLoading,
        error: latestSensorError,
        refetch: refetchLatestSensor
    } = useQuery({
        queryKey: ["latestSensorData", farmId, selectedHouse],
        queryFn: () => {
            if (!selectedHouse) return [];
            return getLatestSensorData(farmId, selectedHouse.housId).then(res => res.data);
        },
        refetchInterval: 5000, // 5초마다 polling
        enabled: !!selectedHouse,
    });

    // 사용자의 아이디로 접근권한을 가진 farm을 호출
    useEffect(() => {
        if (!farmId) {
            getUser().then((res) => navigate(`/farm/${res.data.farmId}/monitor`));
        }
    }, [])

    // selectedHouse 초기값
    useEffect(() => {
        if (!selectedHouse && houses[0] != null) {
            setSelectedHouse(houses[0]);
        }
    }, [houses]);

    const onSettingsClick = () => {
        navigate(`/farm/${farmId}/house/${selectedHouse.housId}/sensor/setting`);
    }

    if (farmLoading) return <LoadingPage/>;
    if (farmError || housesError || sensorError) return <ErrorPage/>;

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

            {/* 최신 센서 데이터 */}
            <Accordion onSelect={(key) => setIsAccordionOpened(key)} defaultActiveKey="0" className="mb-3">
                <Accordion.Item eventKey="0">
                    <Accordion.Header>
                        <span style={{fontWeight: "bold"}}>실시간 재배사 현황</span>
                    </Accordion.Header>
                    <Accordion.Body className="p-0">
                        {!latestSensorLoading &&
                            <LatestSensorMonitor
                                latestSensorData={latestSensorData}
                                houses={houses}
                                setSelectedHouse={setSelectedHouse}
                                selectedHouse={selectedHouse}
                            />
                        }
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>

            {/* 선택된 재배사 최신 센서 데이터 */}
            {isAccordionOpened === null && !latestSensorLoading && selectedHouse && (() => {
                const data = latestSensorData[selectedHouse.housId];
                if (!data) return null;
                return (
                    <LatestSensorSelected latestSensorData={data}
                                      house={selectedHouse}/>
                )
            })()}

            {/* 재배사 리스트 */}
            <HouseList
                selectedHouse={selectedHouse?.housId}
                authLvel={auth.userInfo.authLvel}
                houses={houses}
                setSelectedHouse={setSelectedHouse}
                farmId={farmId}
            />

            {/* 탭 */}
            <Tabs id="custom-tabs" className="d-flex justify-content-end">
                <Tab
                    eventKey="sensor"
                    title={
                        <>
                            <span className="d-none d-md-inline">센서 기록 조회</span>
                            <span className="d-inline d-md-none">센서</span>
                        </>
                    }
                >
                    <HouseSensorDashboard
                        sensorData={sensorData}
                        startDate={startDate}
                        setStartDate={setStartDate}
                        endDate={endDate}
                        setEndDate={setEndDate}
                        fetchSensorData={refetchSensor}
                        loading={sensorLoading}
                    />
                </Tab>

                <Tab
                    eventKey="relay"
                    title={
                        <>
                            <span className="d-none d-md-inline">릴레이 현황 및 조작</span>
                            <span className="d-inline d-md-none">릴레이</span>
                        </>
                    }
                >
                    {selectedHouse &&
                        <RelayDashboard farmId={farmId} house={selectedHouse}/>
                    }
                </Tab>

                <Tab
                    eventKey="memo"
                    title={
                        <>
                            <span className="d-none d-md-inline">재배사 메모</span>
                            <span className="d-inline d-md-none">메모</span>
                        </>
                    }
                >
                    {/* 메모 컴포넌트 */}
                    {selectedHouse &&
                        <MemoDashboard farmId={farmId} houseId={selectedHouse.housId}/>
                    }
                </Tab>

                <Tab
                    eventKey="sensorSetting"
                    title={
                        <>
                            <span className="d-none d-md-inline">설정</span>
                            <span className="d-inline d-md-none">설정</span>
                        </>
                    }
                >
                    {selectedHouse &&
                        <SensorSettingDashboard farmId={farmId} selectedHouse={selectedHouse.housId}/>
                    }
                </Tab>
            </Tabs>
        </Container>
    )

}
