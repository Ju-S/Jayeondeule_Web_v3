import React, {useState} from "react";
import {Form, Button, Card, Container, Alert} from "react-bootstrap";
import {useDispatch} from "react-redux";
import LabeledInput from "../../components/form/LabeledInput.jsx";
import {loginUser} from "../../utils/auth/authUtil.js";
import {useNavigate} from "react-router-dom";
import {loginSuccess} from "../../store/auth/authSlice.js";

export default function LoginPage() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [loginInfo, setLoginInfo] = useState({
        userId: "",
        passwd: "",
    });

    const [errorMessage, setErrorMessage] = useState("");

    const handleChange = (e) => {
        const {name, value} = e.target;
        setLoginInfo({...loginInfo, [name]: value});
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await loginUser(loginInfo);
            dispatch(loginSuccess(res.data));
            navigate("/");
        } catch (err) {
            setErrorMessage(err.response.data.message);
        }
    };

    return (
        <div className="mt-5">
            <Container className="d-flex justify-content-center align-items-center flex-grow-1">
                <Card className="p-4" style={{width: "400px"}}>
                    <Card.Body>
                        <Card.Title className="mb-4 text-center cardTitle">로그인</Card.Title>
                        {errorMessage && (
                            <Alert variant="danger">
                                {errorMessage}
                            </Alert>
                        )}
                        <Form onSubmit={handleSubmit}>
                            <LabeledInput
                                smLabel={12}
                                label="아이디"
                                name="userId"
                                placeholder="아이디 입력"
                                onChange={handleChange}
                                value={loginInfo.userId}
                                required={true}
                            />

                            <LabeledInput
                                smLabel={12}
                                label="비밀번호"
                                type="password"
                                name="passwd"
                                placeholder="비밀번호 입력"
                                onChange={handleChange}
                                value={loginInfo.passwd}
                                required={true}
                            />

                            <Button variant="success" type="submit" className="w-100">
                                로그인
                            </Button>
                        </Form>
                    </Card.Body>
                </Card>
            </Container>
        </div>
    );
}