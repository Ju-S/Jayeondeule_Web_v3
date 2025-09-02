import {useQuery, useMutation, useQueryClient} from "@tanstack/react-query";
import {useEffect, useState} from "react";
import {Row, Col, Card, Button, OverlayTrigger, Tooltip} from "react-bootstrap";
import {InfoCircle} from "react-bootstrap-icons";

import {getRelayStatus, patchRelayStatus} from "../../utils/relayUtil.js";
import {patchHouse} from "../../utils/houseUtil.js";
import RelayCard from "./RelayCard.jsx";
import LoadingPage from "../../pages/common/LoadingPage.jsx";
import ErrorPage from "../../pages/common/ErrorPage.jsx";

export default function RelayDashboard({farmId, house}) {
    const queryClient = useQueryClient();

    const [relayLabels, setRelayLabels] = useState([]);

    useEffect(() => {
        if (farmId === 1 && house.housId === 2) {
            // 릴레이 라벨(2동 임시)
            setRelayLabels([
                {label: "흡입팬(7)", num: 7},
                {label: "배출팬(8)", num: 8},
                {label: "순환밸브(9)", num: 9},
                {label: "흡입밸브(10)", num: 10},
                {label: "배출밸브(11)", num: 11},
                {label: "배수밸브(12)", num: 12},
                {label: "포그생성(순환모터)(2)", num: 2},
                {label: "조명(5)", num: 5},
                {label: "관수(6)", num: 6},
                {label: "칠러Ⅰ(1)", num: 1},
                {label: "칠러Ⅱ(4)", num: 4},
                {label: "라디에이터(3)", num: 3},
            ]);
        } else {
            // 릴레이 라벨(1, 3동)
            setRelayLabels([
                {label: "흡입팬(5)", num: 5},
                {label: "배출팬(6)", num: 6},
                {label: "순환밸브(10)", num: 10},
                {label: "흡입밸브(11)", num: 11},
                {label: "배출밸브(14)", num: 14},
                {label: "배수밸브(3)", num: 3},
                {label: "포그생성(순환모터)(2)", num: 2},
                {label: "조명(7)", num: 7},
                {label: "관수(8)", num: 8},
                {label: "수온히터(1)", num: 1},
                {label: "실내히터(9)", num: 9},
                {label: "히터밸브(15)", num: 15},
            ]);
        }
    }, [farmId, house])

    // relay 상태 조회 (polling)
    const {data: relayStatus = {}, isLoading: isRelayLoading, error: relayError} = useQuery({
        queryKey: ["relayStatus", farmId, house.housId],
        queryFn: () => getRelayStatus(farmId, house.housId).then(res => res.data),
        refetchInterval: 5000,
        enabled: !!farmId && !!house.housId,
    });

    // relay toggle mutation
    const toggleRelayMutation = useMutation({
        mutationFn: (updatedStatus) => patchRelayStatus(farmId, house.housId, updatedStatus),
        onSuccess: () => queryClient.invalidateQueries(["relayStatus", farmId, house.housId]),
    });

    const handleToggleRelay = (relayNum) => {
        const key = `relay${relayNum}stFlag`;
        const updatedStatus = {...relayStatus, [key]: !relayStatus[key]};
        queryClient.setQueryData(["relayStatus", farmId, house.housId], updatedStatus);
        toggleRelayMutation.mutate(updatedStatus);
    };

    // 수동/자동 모드 mutation
    const toggleModeMutation = useMutation({
        mutationFn: (updatedHouse) => patchHouse({farmId, ...updatedHouse}),
        onSuccess: () => queryClient.invalidateQueries(["houses", farmId]),
    });

    const handleToggleMode = () => {
        const updatedHouse = {...house, mnulCtrlFlag: !house.mnulCtrlFlag};
        toggleModeMutation.mutate(updatedHouse);
    };

    if (isRelayLoading) return <LoadingPage/>;
    if (relayError) return <ErrorPage/>;

    return (
        <div>
            <Row className="mt-4 d-flex justify-content-center">
                <Col xs={6} md={4} lg={3} key={house.housId} className="mb-3">
                    <Card
                        className={`text-center shadow-sm ${house.mnulCtrlFlag ? "border-warning" : "border-success"}`}>
                        <Card.Body className="position-relative">
                            <div style={{position: "absolute", top: "0.2rem", right: "0.35rem", zIndex: 10}}>
                                <OverlayTrigger
                                    placement="top"
                                    overlay={
                                        <Tooltip id={`tooltip-info`}>
                                            수동: 개별 릴레이를 제어할 수 있습니다.<br/>
                                            자동: 사전 설정된 값에 따라 릴레이를 제어합니다.
                                        </Tooltip>
                                    }
                                >
                                    <InfoCircle size={16}/>
                                </OverlayTrigger>
                            </div>
                            <Card.Title className="mb-2">작동방식</Card.Title>
                            <Button
                                variant={house.mnulCtrlFlag ? "warning" : "success"}
                                className="w-100"
                                onClick={handleToggleMode}
                                disabled={toggleModeMutation.isLoading}
                            >
                                {house.mnulCtrlFlag ? "수동" : "자동"}
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <hr/>
            <Row>
                {relayLabels.map((item) => (
                    <RelayCard
                        key={item.num}
                        label={item.label}
                        relayNum={item.num}
                        relayStatus={relayStatus}
                        manualMode={house.mnulCtrlFlag}
                        handleToggle={handleToggleRelay}
                        toggleRelayMutation={toggleRelayMutation}
                    />
                ))}
            </Row>
        </div>
    );
}
