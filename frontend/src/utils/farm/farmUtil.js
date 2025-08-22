import api from "../common/jwtUtil.js";
import apiRoutes from "../common/apiRoutes.js";

export async function registerFarm(farmInfo) {
    const { url, method } = apiRoutes.farms.register;
    return api({url, method, data: farmInfo});
}