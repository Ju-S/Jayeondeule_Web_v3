import api from "./jwtUtil.js";
import apiRoutes from "./apiRoutes.js";

export async function loginUser(loginInfo) {
    const {url, method} = apiRoutes.auth.login;
    return api({url, method, data: loginInfo});
}

export async function authUser() {
    const {url, method} = apiRoutes.auth.getUser;
    return api({url, method});
}