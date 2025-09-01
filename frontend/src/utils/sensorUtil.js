import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getSensorData(farmId, houseId, start, end) {
    const {url, method} = apiRoutes.sensors.list(farmId, houseId, start, end);
    return api({url, method});
}

export async function getLatestSensorData(farmId, houseId) {
    const {url, method} = apiRoutes.sensors.latest(farmId, houseId);
    return api({url, method});
}