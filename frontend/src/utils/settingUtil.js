import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getSettings(farmId, houseId) {
    const { url, method } = apiRoutes.settings.list(farmId, houseId);
    return api({url, method});
}

export async function insertSettings(farmId, houseId, settingInfo) {
    const { url, method } = apiRoutes.settings.insert(farmId, houseId);
    return api({url, method, data: settingInfo});
}

export async function patchSetting(farmId, houseId, settingInfo) {
    const { url, method } = apiRoutes.settings.update(farmId, houseId, settingInfo.setnDttm);
    return api({url, method, data: settingInfo});
}

export async function deleteSetting(farmId, houseId, setnDttm) {
    const { url, method } = apiRoutes.settings.delete(farmId, houseId, setnDttm);
    return api({url, method});
}