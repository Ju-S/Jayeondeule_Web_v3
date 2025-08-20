import api from "../common/jwtUtil.js";

export async function registerUser(userInfo) {
    return api.post("http://localhost:8088/api/users", userInfo);
}

export async function idDuplCheck(userId) {
    return api.get("http://localhost:8088/api", userId);
}