import api from "../common/jwtUtil.js";
import apiRoutes from "../common/apiRoutes.js";

export async function registerUser(userInfo) {
    const { url, method } = apiRoutes.users.register;
    return api({url, method, data: userInfo});
}

export async function idDuplCheck(userId) {
    const { url, method } = apiRoutes.users.checkUserId(userId);
    return api({url, method});
}