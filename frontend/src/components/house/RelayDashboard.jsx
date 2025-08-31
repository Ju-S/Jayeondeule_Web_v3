import {useState, useEffect} from "react";
import {Card, Row, Col, Button, OverlayTrigger, Tooltip} from "react-bootstrap";
import {getRelayStatus, patchRelayStatus} from "../../utils/relayUtil.js";
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";
import LoadingPage from "../../pages/common/LoadingPage.jsx";
import ErrorPage from "../../pages/common/ErrorPage.jsx";
import {InfoCircle} from "react-bootstrap-icons";

export default function RelayDashboard({farmId, houseId}) {
    const [manualMode, setManualMode] = useState(true);

    const queryClient = useQueryClient();

    // 릴레이 라벨 (DB나 서버에서 받아와도 됨)
    const relayLabels = [
        {label: "수온히터(1)", num: 1},
        {label: "실내히터(9)", num: 9},
        {label: "순환모터(2)", num: 2},
        {label: "배수밸브(3)", num: 3},
        {label: "조명(7)", num: 7},
        {label: "관수(8)", num: 8},
        {label: "흡입팬(5)", num: 5},
        {label: "배출팬(6)", num: 6},
        {label: "순환밸브(10)", num: 10},
        {label: "흡입밸브(11)", num: 11},
        {label: "배출밸브(14)", num: 14},
        {label: "히터밸브(15)", num: 15}
    ];

    // useQuery: DB 상태를 주기적으로 가져오기 (polling)
    const {data: relayStatus = {}, isLoading, error} = useQuery({
        queryKey: ["relayStatus", farmId, houseId],
        queryFn: () => getRelayStatus(farmId, houseId).then(res => res.data),
        refetchInterval: 5000, // 5초마다 polling
        enabled: !!farmId && !!houseId,
    });

    // useMutation: toggle 시 서버에 반영
    const toggleRelayMutation = useMutation({
        mutationFn: (updatedStatus) => patchRelayStatus(farmId, houseId, updatedStatus),
        onSuccess: () => {
            queryClient.invalidateQueries(["relayStatus", farmId, houseId]);
        },
    });

    const handleToggle = async (relayNum) => {
        const key = `relay${relayNum}stFlag`;

        const updatedStatus = {
            ...relayStatus,
            [key]: !relayStatus[key]
        };

        queryClient.setQueryData(["relayStatus", farmId, houseId], updatedStatus);
        toggleRelayMutation.mutate(updatedStatus);
    };

    const renderRelayCard = (relayNum, label) => {
        const key = `relay${relayNum}stFlag`;
        const isOn = relayStatus[key];

        return (
            <Col xs={6} md={4} lg={3} key={relayNum} className="mb-3">
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
                            disabled={manualMode || toggleRelayMutation.isLoading}
                        >
                            {isOn ? "ON" : "OFF"}
                        </Button>
                    </Card.Body>
                </Card>
            </Col>
        );
    };

    if (isLoading) return <LoadingPage/>
    if (error) return <ErrorPage/>

    return (
        <div>
            <Row className="mt-4 d-flex justify-content-center">
                <Col xs={6} md={4} lg={3} key={manualMode} className="mb-3">
                    <Card
                        className={`text-center shadow-sm ${
                            manualMode ? "border-success" : "border-warning"
                        }`}
                    >
                        <Card.Body className="position-relative">
                            <div style={{
                                position: "absolute",
                                top: "0.2rem",
                                right: "0.35rem",
                                zIndex: 10
                            }}>
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
                                variant={manualMode ? "success" : "warning"}
                                className="w-100"
                                onClick={() => setManualMode(!manualMode)}
                            >
                                {manualMode ? "자동" : "수동"}
                            </Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <hr/>
            <Row>
                {relayLabels.map((item) => renderRelayCard(item.num, item.label))}
            </Row>
        </div>
    );
}
