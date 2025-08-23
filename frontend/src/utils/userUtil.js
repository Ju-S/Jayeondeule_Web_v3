import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function registerUser(userInfo) {
    const { url, method } = apiRoutes.users.register;
    return api({url, method, data: userInfo});
}

export async function idDuplCheck(userId) {
    const { url, method } = apiRoutes.users.checkUserId(userId);
    return api({url, method});
}

export async function patchUserPassword(passwordInfo) {
    const {url, method} = apiRoutes.users.passwordChange;
    return api({url, method, data: passwordInfo});
}

export async function patchUser(patchInfo) {
    const {url, method} = apiRoutes.users.patch;
    return api({url, method, data: patchInfo});
}

export async function getUser() {
    const {url, method} = apiRoutes.users.me;
    return api({url, method});
}