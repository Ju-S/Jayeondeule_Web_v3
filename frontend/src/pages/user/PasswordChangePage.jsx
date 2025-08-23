import React, {useState} from "react";
import {Form, Button, Card, Container, Alert} from "react-bootstrap";
import LabeledInput from "../../components/form/LabeledInput.jsx";
import {patchUserPassword} from "../../utils/userUtil.js";
import {useNavigate} from "react-router-dom";
import AlertModal from "../../components/common/AlertModal.jsx";

export default function PasswordChangePage() {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
    });

    const [errorMessage, setErrorMessage] = useState("");
    const [showModal, setShowModal] = useState(false);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const {confirmPassword, ...payload} = form;

        try{
            await patchUserPassword(payload);
            setShowModal(true);
        } catch(err) {
            setErrorMessage(err.response.data);
        }
    };

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "500px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">비밀번호 변경</h2>

                    {errorMessage && (
                        <Alert variant="danger">
                            {errorMessage}
                        </Alert>
                    )}

                    <LabeledInput
                        label="현재 비밀번호"
                        type="password"
                        name="oldPassword"
                        value={form.oldPassword}
                        onChange={handleChange}
                        placeholder="현재 비밀번호 입력"
                        required
                    />

                    <LabeledInput
                        label="새 비밀번호"
                        type="password"
                        name="newPassword"
                        value={form.newPassword}
                        onChange={handleChange}
                        placeholder="새 비밀번호 입력"
                        pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,30}$"
                        errorMsg="영문, 숫자, 특수문자가 1개 이상 들어간 8~30자로 입력해주세요."
                        required
                    />

                    <LabeledInput
                        label="비밀번호 확인"
                        type="password"
                        name="confirmPassword"
                        value={form.confirmPassword}
                        onChange={handleChange}
                        placeholder="비밀번호 확인"
                        pattern={form.newPassword}
                        errorMsg="입력된 비밀번호와 다릅니다."
                        required
                    />

                    <div className="d-flex justify-content-end mt-3">
                        <Button variant="success" type="submit">
                            변경하기
                        </Button>
                    </div>
                </Form>
            </Card>

            {/* 수정 알림 Modal */}
            <AlertModal
                show={showModal}
                hideModalFunc={() => navigate("/")}
                title="알림"
                body="비밀번호가 변경되었습니다."
            />
        </Container>
    );
}
