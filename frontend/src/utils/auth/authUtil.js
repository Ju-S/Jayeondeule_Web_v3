import api from "../common/jwtUtil.js";
import apiRoutes from "../common/apiRoutes.js";

export async function loginUser(loginInfo) {
    return api.post(apiRoutes.auth.login, loginInfo);
}