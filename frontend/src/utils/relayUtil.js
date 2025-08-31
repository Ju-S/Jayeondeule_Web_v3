import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getRelayStatus(farmId, houseId) {
    const { url, method } = apiRoutes.relays.list(farmId, houseId);
    return api({url, method});
}

export async function patchRelayStatus(farmId, houseId, relayInfo) {
    const { url, method } = apiRoutes.relays.insert(farmId, houseId);
    return api({url, method, data:relayInfo});
}