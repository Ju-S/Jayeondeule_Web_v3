import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function getMemos(farmId, houseId, page, size) {
    const { url, method } = apiRoutes.memos.list(farmId, houseId, page, size);
    return api({url, method});
}

export async function createMemo(farmId, houseId, content) {
    const { url, method } = apiRoutes.memos.insert(farmId, houseId);
    return api({url, method, data:content});
}

export async function updateMemo(farmId, houseId, content) {
    console.log(content);
    const { url, method } = apiRoutes.memos.patch(farmId, houseId, content.recdDttm);
    return api({url, method, data:content});
}

export async function deleteMemo(farmId, houseId, recdDttm) {
    const { url, method } = apiRoutes.memos.delete(farmId, houseId, recdDttm);
    return api({url, method});
}