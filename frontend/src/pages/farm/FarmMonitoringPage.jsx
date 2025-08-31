import React, {useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import {Container, Tabs, Tab} from "react-bootstrap";
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";

import {getHouseList} from "../../utils/houseUtil.js";
import {getSensorData} from "../../utils/sensorUtil.js";
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

export default function FarmMonitoringPage() {
    const {farmId} = useParams();
    const auth = useSelector(state => state.auth);

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
        refetchInterval: 5000, // 5초마다 polling
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
            return getSensorData(farmId, selectedHouse, startDateTime, endDateTime).then(res => res.data);
        },
        refetchInterval: 5000, // 5초마다 polling
        enabled: !!selectedHouse,
    });

    // selectedHouse 초기값
    useEffect(() => {
        if (!selectedHouse && houses.length > 0) {
            setSelectedHouse(houses[0].housId);
        }
    }, [houses]);

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

            {/* 재배사 리스트 */}
            <HouseList
                selectedHouse={selectedHouse}
                authLvel={auth.userInfo.authLvel}
                houses={houses}
                setSelectedHouse={setSelectedHouse}
                farmId={farmId}
            />

            {/* 최신 센서 데이터 */}
            {sensorData &&
                <LatestSensorMonitor sensorData={sensorData}/>
            }

            {/* 탭 */}
            <Tabs id="custom-tabs">
                <Tab
                    eventKey="sensor"
                    title={
                        <>
                            <span className="d-none d-sm-inline">센서 기록 조회</span>
                            <span className="d-inline d-sm-none">센서</span>
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
                            <span className="d-none d-sm-inline">릴레이 현황 및 조작</span>
                            <span className="d-inline d-sm-none">릴레이</span>
                        </>
                    }
                >
                    {selectedHouse &&
                        <RelayDashboard farmId={farmId} house={houses[selectedHouse - 1]}/>
                    }
                </Tab>

                <Tab
                    eventKey="memo"
                    title={
                        <>
                            <span className="d-none d-sm-inline">재배사 메모</span>
                            <span className="d-inline d-sm-none">메모</span>
                        </>
                    }
                >
                    {/* 메모 컴포넌트 */}
                    {selectedHouse &&
                        <MemoDashboard farmId={farmId} houseId={houses[selectedHouse - 1].housId}/>
                    }
                </Tab>
            </Tabs>
        </Container>
    );
}
