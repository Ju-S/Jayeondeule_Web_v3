import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getSensorData(farmId, houseId, start, end) {
    const {url, method} = apiRoutes.sensors.list(farmId, houseId, start, end);
    return api({url, method});
}