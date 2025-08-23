import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getHouseList({farmId}) {
    const { url, method } = apiRoutes.houses.list(farmId);
    return api({url, method});
}