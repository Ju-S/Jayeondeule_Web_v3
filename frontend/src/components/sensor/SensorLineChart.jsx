import {CartesianGrid, Legend, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis} from "recharts";
import {useState} from "react";
import './SensorLineChart.css';

export default function SensorLineChart({ data }) {
    // 센서 라인 설정
    const lineConfig = [
        { key: "indrTprtValu", name: "실내 온도", stroke: "#8884d8", unit: "℃" },
        { key: "oudrTprtValu", name: "실외 온도", stroke: "#82ca9d", unit: "℃" },
        { key: "indrHmdtValu", name: "실내 습도", stroke: "#ffc658", unit: "%" },
        { key: "oudrHmdtValu", name: "실외 습도", stroke: "#ff8042", unit: "%" },
        { key: "co2Valu", name: "이산화탄소 농도", stroke: "#8dd1e1", unit: "ppm" },
        { key: "watrTprtValu", name: "수온", stroke: "#a4de6c", unit: "℃" }
    ];

    // 라인 보이기/숨기기 상태
    const [visibleLines, setVisibleLines] = useState({
        indrTprtValu: true,
        oudrTprtValu: true,
        indrHmdtValu: true,
        oudrHmdtValu: true,
        co2Valu: true,
        watrTprtValu: true
    });

    // Legend 클릭 시 토글
    const onToggleLine = (lineKey) => {
        setVisibleLines(prev => ({ ...prev, [lineKey]: !prev[lineKey] }));
    };

    return (
        <ResponsiveContainer width="100%" height={400}>
            <LineChart data={data}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="recdDttm" />
                <YAxis />
                <Tooltip formatter={(value, name) => {
                    const line = lineConfig.find(l => l.name === name);
                    if (!line || !visibleLines[line.key]) return null;
                    return [`${value}${line.unit}`, name];
                }} />
                <Legend content={() => (
                    <div style={{ display: "flex", flexWrap: "wrap", justifyContent: "center" }}>
                        {lineConfig.map(line => (
                            <div
                                key={line.key}
                                onClick={() => onToggleLine(line.key)}
                                style={{ display: "flex", alignItems: "center", marginRight: 10, cursor: "pointer" }}
                            >
                                <input type="checkbox" checked={visibleLines[line.key]} readOnly style={{ marginRight: 4 }} />
                                <span style={{ color: visibleLines[line.key] ? line.stroke : "#ccc" }}>{line.name}</span>
                            </div>
                        ))}
                    </div>
                )} />
                {lineConfig.map(line => visibleLines[line.key] && (
                    <Line key={line.key} type="monotone" dataKey={line.key} stroke={line.stroke} name={line.name} dot={false}/>
                ))}
            </LineChart>
        </ResponsiveContainer>
    );
}
