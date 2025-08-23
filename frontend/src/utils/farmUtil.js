import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function registerFarm(farmInfo) {
    const { url, method } = apiRoutes.farms.register;
    return api({url, method, data: farmInfo});
}

export async function getFarmList({page, pageSize}) {
    const { url, method } = apiRoutes.farms.getFarm;
    return api({url, method});
}