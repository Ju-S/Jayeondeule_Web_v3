import React from "react";
import "./LatestSensorItem.css";

export default function LatestSensorItem({latestSensorData, house, setSelectedHouse, selectedHouse}) {
    const trStyle = {};
    const now = new Date();
    const recdTime = new Date(latestSensorData.recdDttm);

    if ((now - recdTime) / 1000 > 60) { // 1분(60초) 이상 차이면
        trStyle.color = "red";
    }

    if(selectedHouse.housId == house.housId) {
        trStyle.backgroundColor = "#f4f8fd";
        trStyle.fontWeight = "bold";
    }

    const formatDate = (dateString) => {
        const d = new Date(dateString);
        const yyyy = d.getFullYear();
        const mm = String(d.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작
        const dd = String(d.getDate()).padStart(2, "0");
        return `${yyyy}-${mm}-${dd}`;
    };

    const formatTime = (dateString) => {
        const d = new Date(dateString);
        const hh = String(d.getHours()).padStart(2, "0");
        const min = String(d.getMinutes()).padStart(2, "0");
        const ss = String(d.getSeconds()).padStart(2, "0");
        return `${hh}:${min}:${ss}`;
    };

    return (
            <tr className="sensorItem" onClick={() => setSelectedHouse(house)} style={{cursor: "pointer"}}>
                <td style={{...trStyle, padding: "0"}}>{house.housName}</td>
                <td style={trStyle}>{Math.round(latestSensorData.indrTprtValu * 100) / 100 + "℃" ?? "-"}</td>
                <td style={trStyle}>{Math.round(latestSensorData.oudrTprtValu * 100) / 100 + "℃" ?? "-"}</td>
                <td style={trStyle}>{Math.round(latestSensorData.indrHmdtValu * 100) / 100 + "%" ?? "-"}</td>
                <td style={trStyle}>{Math.round(latestSensorData.oudrHmdtValu * 100) / 100 + "%" ?? "-"}</td>
                <td style={trStyle}>{Math.round(latestSensorData.co2Valu * 100) / 100 + "ppm" ?? "-"}</td>
                <td style={trStyle}>{Math.round(latestSensorData.watrTprtValu * 100) / 100 + "℃" ?? "-"}</td>
                <td style={{...trStyle, fontSize: "12px"}}>
                    {formatDate(latestSensorData.recdDttm)}<br/>
                    {formatTime(latestSensorData.recdDttm)}
                </td>
            </tr>
    )
}