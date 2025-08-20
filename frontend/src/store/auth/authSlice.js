import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name: "auth",
    initialState: { token: null, userInfo: null },
    reducers: {
        loginSuccess: (state, action) => {
            state.token = action.payload.token;
            state.userInfo = action.payload.userInfo;
            localStorage.setItem("token", action.payload.token);
        },
        logout: (state) => {
            state.token = null;
            state.userInfo = null;
            localStorage.removeItem("token");
        },
        restoreSession: (state) => {
            const token = localStorage.getItem("token");
            if (token) {
                state.token = token;
            }
        },
        setUser: (state, action) => {
            state.token = localStorage.getItem("token");
            state.userInfo = action.payload;
        }
    }
});

export const { loginSuccess, logout, restoreSession, setUser } = authSlice.actions;
export default authSlice.reducer;
