import {Button, Form, InputGroup} from "react-bootstrap";
import HouseSensorChart from "./HouseSensorChart.jsx";

export default function HouseSensorDashboard({
                                                 fetchSensorData,
                                                 loading,
                                                 sensorData,
                                                 startDate,
                                                 setStartDate,
                                                 endDate,
                                                 setEndDate
                                             }) {
    return (
        <>
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
        </>
    )
}