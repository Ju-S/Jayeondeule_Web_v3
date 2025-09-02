import React, {useEffect} from "react";
import {Button, Card} from "react-bootstrap";

export default function LatestSensorMonitor({latestSensorData, houseName}) {
    return (
        <>
            {latestSensorData && (
                <div style={{position: "relative", marginBottom: "1rem", marginTop: "2rem"}}>
                    {/* 카드 바깥 오른쪽 상단에 측정 시간 */}
                    <div style={{
                        position: "absolute",
                        top: "-1.5rem",
                        right: "0",
                        fontWeight: "bold",
                        fontSize: "0.9rem"
                    }}>
                        {new Date(latestSensorData.recdDttm).toLocaleString()}
                    </div>

                    {houseName && (
                        <div style={{
                            position: "absolute",
                            top: "-1.5rem",
                            left: "0",
                            fontWeight: "bold",
                            fontSize: "0.9rem"
                        }}>
                            {houseName}
                        </div>
                    )}

                    <Card className="p-3 mb-3"
                          style={{overflowX: "auto", minHeight: "100px", display: "flex", alignItems: "center", overflow: "hidden"}}>
                        {/* 카드 안쪽 오른쪽 상단 설정 버튼 */}
                        {/*<Button*/}
                        {/*    variant="outline-secondary"*/}
                        {/*    size="sm"*/}
                        {/*    onClick={onSettingsClick}*/}
                        {/*    style={{*/}
                        {/*        position: "absolute",*/}
                        {/*        top: "0",*/}
                        {/*        right: "0",*/}
                        {/*        padding: "0.4rem 0.4rem",*/}
                        {/*        borderTopLeftRadius: "0",*/}
                        {/*        borderTopRightRadius: "0.25rem",*/}
                        {/*        borderBottomLeftRadius: "0.25rem",*/}
                        {/*        borderBottomRightRadius: "0",*/}
                        {/*        borderLeft: "1px solid #dee2e6",*/}
                        {/*        borderBottom: "1px solid #dee2e6",*/}
                        {/*        borderTop: "none",*/}
                        {/*        borderRight: "none",*/}
                        {/*        zIndex: 10,*/}
                        {/*    }}*/}
                        {/*    className="gear-button"*/}
                        {/*>*/}
                        {/*    <GearFill size={20}/>*/}
                        {/*</Button>*/}
                        <table className="table table-borderless mb-0 text-center" style={{minWidth: "300px"}}>
                            <thead>
                            <tr className="d-none d-md-table-row">
                                <th>실내 온도</th>
                                <th>실외 온도</th>
                                <th>실내 습도</th>
                                <th>실외 습도</th>
                                <th>CO2</th>
                                <th>수온</th>
                            </tr>
                            </thead>
                            <tbody>
                            {/* PC용 한 줄 */}
                            <tr className="d-none d-md-table-row">
                                <td>{Math.round(latestSensorData.indrTprtValu * 100) / 100 + "℃" ?? "-"}</td>
                                <td>{Math.round(latestSensorData.oudrTprtValu * 100) / 100 + "℃" ?? "-"}</td>
                                <td>{Math.round(latestSensorData.indrHmdtValu * 100) / 100 + "%" ?? "-"}</td>
                                <td>{Math.round(latestSensorData.oudrHmdtValu * 100) / 100 + "%" ?? "-"}</td>
                                <td>{Math.round(latestSensorData.co2Valu * 100) / 100 + "ppm" ?? "-"}</td>
                                <td>{Math.round(latestSensorData.watrTprtValu * 100) / 100 + "℃" ?? "-"}</td>
                            </tr>

                            {/* 모바일용 2열씩 3줄 */}
                            <tr className="d-md-none">
                                <td><b>실내 온도:</b> {Math.round(latestSensorData.indrTprtValu * 100) / 100 + "℃"  ?? "-"}</td>
                                <td><b>실외 온도:</b> {Math.round(latestSensorData.oudrTprtValu * 100) / 100 + "℃"  ?? "-"}</td>
                            </tr>
                            <tr className="d-md-none">
                                <td><b>실내 습도:</b> {Math.round(latestSensorData.indrHmdtValu * 100) / 100 + "%"  ?? "-"}</td>
                                <td><b>실외 습도:</b> {Math.round(latestSensorData.oudrHmdtValu * 100) / 100 + "%"  ?? "-"}</td>
                            </tr>
                            <tr className="d-md-none">
                                <td><b>CO2:</b> {Math.round(latestSensorData.co2Valu * 100) / 100 + "ppm"  ?? "-"}</td>
                                <td><b>수온:</b> {Math.round(latestSensorData.watrTprtValu * 100) / 100 + "℃"  ?? "-"}</td>
                            </tr>
                            </tbody>
                        </table>
                    </Card>
                </div>
            )}
        </>
    )
}