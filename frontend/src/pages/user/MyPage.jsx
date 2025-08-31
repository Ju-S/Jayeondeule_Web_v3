import {useEffect, useState} from "react";
import {Form, Button, Card, Container, Col, Row, Modal, InputGroup} from "react-bootstrap";
import LabeledPhoneInput from "../../components/form/LabeledPhoneInput.jsx";
import LabeledInput from "../../components/form/LabeledInput.jsx";
import {useNavigate} from "react-router-dom";
import {deleteUser, getUser, patchUser} from "../../utils/userUtil.js";
import AlertModal from "../../components/common/AlertModal.jsx";
import {useDispatch} from "react-redux";
import {logout} from "../../store/auth/authSlice.js";

export default function MyPage() {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [form, setForm] = useState({
        userName: "",   // 서버에서 가져온 사용자 정보로 초기화
        pstn: "",
        hpNo: "",
    });

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const res = await getUser(); // GET /users/me
                setForm({
                    userName: res.data.userName,
                    pstn: res.data.pstn,
                    hpNo: res.data.hpNo,
                });
            } catch (err) {
                console.error(err);
            }
        };
        fetchUser();
    }, []);

    const [showModal, setShowModal] = useState(false);
    const [showWithdrawModal, setShowWithdrawModal] = useState(false);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await patchUser(form);
            setShowModal(true);
        } catch (err) {
            alert("수정 실패");
        }
    };

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "600px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">마이페이지</h2>

                    <LabeledInput
                        label="이름"
                        type="text"
                        name="userName"
                        value={form.userName}
                        onChange={handleChange}
                        placeholder="이름 입력"
                        pattern="^[가-힣a-zA-Z]{2,20}$"
                        errorMsg="한글, 영문으로 이루어진 2~20자로 입력해주세요."
                        required
                    />

                    <LabeledInput
                        label="직위"
                        name="pstn"
                        value={form.pstn}
                        onChange={handleChange}
                        placeholder="직위 입력"
                        pattern="^[a-zA-Z가-힣]*$"
                        errorMsg="한글, 영문으로 입력해주세요."
                        required
                    />

                    <LabeledPhoneInput
                        label="휴대폰번호"
                        name="hpNo"
                        value={form.hpNo}
                        onChange={handleChange}
                        required
                    />

                    <Row>
                        <Col>
                            <div className="d-flex justify-content-start mt-3">
                                <Button variant="secondary" type="button" onClick={() => navigate("/mypage/password")}>
                                    비밀번호 변경
                                </Button>
                            </div>
                        </Col>
                        <Col>
                            <div className="d-flex justify-content-end mt-3">
                                <InputGroup>
                                    <Button variant="success" type="submit">
                                        수정하기
                                    </Button>
                                    <Button variant="outline-danger" onClick={() => setShowWithdrawModal(true)}>
                                        회원탈퇴
                                    </Button>
                                </InputGroup>
                            </div>
                        </Col>
                    </Row>
                </Form>
            </Card>

            {/* 수정 알림 Modal */}
            <AlertModal
                show={showModal}
                hideModalFunc={() => setShowModal(false)}
                title="알림"
                body="수정이 완료되었습니다."
            />

            {/* 수정 알림 Modal */}
            <AlertModal
                show={showWithdrawModal}
                hideModalFunc={() => setShowWithdrawModal(false)}
                onClickFunc={async () => {
                    await deleteUser();
                    dispatch(logout()); // Redux 상태 초기화
                    navigate("/login"); // 로그인 페이지로 이동
                }}
                title="정말로 회원탈퇴 하시겠습니다?"
                body="회원탈퇴 후에는 복구가 불가능합니다."
                variant="danger"
            />
        </Container>
    )
}