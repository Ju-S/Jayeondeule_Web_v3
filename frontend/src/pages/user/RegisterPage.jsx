import {useState} from "react";
import {Form, Button, Card, Container, InputGroup} from "react-bootstrap";
import LabeledInput from "../../components/form/LabeledInput";
import {idDuplCheck, registerUser} from "../../utils/user/userUtil.js";
import {useNavigate} from "react-router-dom";
import RegisterIdInput from "../../components/user/register/RegisterIdInput.jsx";
import LabeledPhoneInput from "../../components/form/LabeledPhoneInput.jsx";

function RegisterPage() {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        userId: "",
        passwd: "",
        email: "",
        userName: "",
        hpNo: "",
    });

    const [confirmPasswd, setConfirmPasswd] = useState("");
    const [isDuplicate, setIsDuplicate] = useState(null); // null/true/false

    const formHandleChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});

        if(name === "userId") {
            setIsDuplicate(null);
        }
    };

    const passwdHandleChange = (e) => {
        const {value} = e.target;
        setConfirmPasswd(value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if(isDuplicate === true || isDuplicate === null) {
            alert("아이디 중복 확인을 해주세요.");
            return false;
        }

        try {
            await registerUser(form);
            navigate("/");
        } catch (err) {
            alert(err.response.data.message);
        }
    };

    const handleCheck = async (e) => {
        if (!form.userId) return;
        try {
            const res = await idDuplCheck(form.userId);
            setIsDuplicate(res.data); // 서버에서 { available: true/false } 반환
        } catch (err) {
            console.error(err);
            setIsDuplicate(false);
        }
    };

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "800px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">회원가입</h2>

                    {/* 아이디 입력 + 중복체크 버튼 */}
                    <RegisterIdInput
                        value={form.userId}
                        onChange={formHandleChange}
                        onClick={handleCheck}
                        isAvailable={isDuplicate}
                        required={true}
                    />

                    <LabeledInput
                        label="비밀번호"
                        type="password"
                        name="passwd"
                        value={form.passwd}
                        onChange={formHandleChange}
                        placeholder="비밀번호 입력"
                        pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,30}$"
                        errorMsg="영문, 숫자, 특수문자가 1개 이상 들어간 8~30자로 입력해주세요."
                        required={true}
                        // 8-30자, 영문+숫자+특수문자 포함
                    />
                    <LabeledInput
                        label="비밀번호 확인"
                        type="password"
                        name="confirmPasswd"
                        value={confirmPasswd}
                        onChange={passwdHandleChange}
                        placeholder="비밀번호 확인"
                        pattern={form.passwd}
                        errorMsg="입력된 비밀번호와 다릅니다."
                        required={true}
                        // confirm는 폼 검증 외에도 handleSubmit에서 직접 체크 필요
                    />
                    <LabeledInput
                        label="이름"
                        name="userName"
                        value={form.userName}
                        onChange={formHandleChange}
                        placeholder="이름 입력"
                        pattern="^[가-힣a-zA-Z]{2,20}$"
                        errorMsg="한글, 영문으로 이루어진 2~20자로 입력해주세요."
                        required={true}
                        // 한글/영문, 2~20자
                    />
                    <LabeledPhoneInput
                        label="휴대폰번호"
                        name="hpNo"
                        value={form.hpNo}
                        onChange={formHandleChange}
                        required={true}
                    />
                    <LabeledInput
                        label="이메일"
                        type="email"
                        name="email"
                        value={form.email}
                        onChange={formHandleChange}
                        placeholder="이메일 입력"
                        pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
                        errorMsg="이메일 형식으로 입력해주세요.(example@example.com)"
                        required={true}
                        // 일반 이메일 패턴
                    />

                    <Button type="submit" variant="success" className="w-100 mt-3">회원가입</Button>
                </Form>
            </Card>
        </Container>
    );
}

export default RegisterPage;
