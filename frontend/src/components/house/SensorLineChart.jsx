import {
    Area,
    AreaChart,
    CartesianGrid,
    Legend,
    Line,
    LineChart,
    ResponsiveContainer,
    Tooltip,
    XAxis,
    YAxis
} from "recharts";
import {useEffect, useState} from "react";
import './SensorLineChart.css';
import dayjs from "dayjs";

export default function SensorLineChart({data, lineConfig}) {
    // 라인 보이기/숨기기 상태
    const [visibleLines, setVisibleLines] = useState({});

    // visibleLines 초기 세팅
    useEffect(() => {
        const initialState = lineConfig.reduce((acc, item) => {
            acc[item.key] = true;
            return acc;
        }, {});
        setVisibleLines(initialState);
    }, [lineConfig]);

    // Legend 클릭 시 토글
    const onToggleLine = (lineKey) => {
        setVisibleLines(prev => ({...prev, [lineKey]: !prev[lineKey]}));
    };

    // 4등분으로 tick 선택
    const xTicks = [];
    if (data.length > 0) {
        const step = Math.floor(data.length / 3);
        for (let i = 0; i < data.length - 1; i += step) {
            xTicks.push(data[i].recdDttm);
        }
    }

    return (
        <ResponsiveContainer width="100%" height={200}>
            <AreaChart data={data}
                       margin={{top: 5, right: 20, bottom: 5, left: -12}}>
                <CartesianGrid strokeDasharray="3 3"/>
                <XAxis
                    dataKey="recdDttm"
                    ticks={xTicks}
                    interval={0}
                    tickFormatter={(value) => dayjs(value).format("MM-DD HH:mm")}
                    label={{value: "시간", position: "insideBottomRight", offset: -5}}
                />
                <YAxis/>
                <Tooltip
                    labelFormatter={(label) => dayjs(label).format("YYYY-MM-DD HH:mm")}
                    formatter={(value, name, props) => {
                        const line = lineConfig.find(l => l.key === props.dataKey);
                        if (!line || !visibleLines[line.key]) return null;
                        return [`${value}${line.unit}`, line.name];
                    }}
                />
                <Legend content={() => (
                    <div style={{display: "flex", flexWrap: "wrap", justifyContent: "center"}}>
                        {lineConfig.map(line => (
                            <div
                                key={line.key}
                                onClick={() => onToggleLine(line.key)}
                                style={{
                                    display: "flex",
                                    alignItems: "center",
                                    marginRight: 10,
                                    cursor: "pointer",
                                }}
                            >
                                <input type="checkbox" checked={visibleLines[line.key]} readOnly
                                       style={{marginRight: 4, accentColor: line.stroke,}}/>
                                <span style={{color: visibleLines[line.key] ? "#000" : "#ccc"}}>{line.name}</span>
                            </div>
                        ))}
                    </div>
                )}/>
                {lineConfig.map(line => visibleLines[line.key] && (
                    <Area key={line.key} type="monotone" dataKey={line.key} stroke={line.stroke} fill={line.stroke}
                          name={line.name}
                          dot={false}/>
                ))}
            </AreaChart>
        </ResponsiveContainer>
    );
}
