import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function registerFarm(farmInfo) {
    const {url, method} = apiRoutes.farms.register;
    return api({url, method, data: farmInfo});
}

export async function getFarmList({page, pageSize}) {
    const {url, method} = apiRoutes.farms.getFarmList;
    return api({url, method});
}

export async function getFarm({farmId}) {
    const {url, method} = apiRoutes.farms.getFarm(farmId);
    return api({url, method});
}

export async function getMyFarm() {
    const {url, method} = apiRoutes.farms.getMyFarm;
    return api({url, method});
}

export async function deleteFarm(farmId) {
    const {url, method} = apiRoutes.farms.delete(farmId);
    return api({url, method});
}

export async function patchFarm(farm) {
    const {url, method} = apiRoutes.farms.edit(farm.farmId);
    return api({url, method, data: farm});
}