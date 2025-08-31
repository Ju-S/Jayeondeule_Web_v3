import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getHouseList({farmId}) {
    const { url, method } = apiRoutes.houses.list(farmId);
    return api({url, method});
}

export async function registerHouse(form) {
    const { url, method } = apiRoutes.houses.register(form.farmId);
    return api({url, method, data: form});
}

export async function getHouse(farmId, houseId) {
    const { url, method } = apiRoutes.houses.get(farmId, houseId);
    return api({url, method});
}

export async function patchHouse(form) {
    const { url, method } = apiRoutes.houses.patch(form.farmId, form.houseId);
    return api({url, method, data: form});
}

export async function deleteHouse(farmId, houseId){
    const { url, method } = apiRoutes.houses.delete(farmId, houseId);
    return api({url, method});
}