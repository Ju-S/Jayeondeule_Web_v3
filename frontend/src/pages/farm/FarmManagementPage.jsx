import React from "react";
import {useSelector} from "react-redux";

export default function FarmManagementPage() {
    const auth = useSelector((state) => (state.auth));

    return (
        <>
            {auth.userInfo.authLvel}
        </>
    )
}