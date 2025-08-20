import {useState} from "react";
import {Form, Button, Card, Container} from "react-bootstrap";
import LabeledInput from "../../components/form/LabeledInput";
import {registerUser} from "../../utils/user/userUtil.js";
import {useNavigate} from "react-router-dom";

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

    const formHandleChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});
    };

    const passwdHandleChange = (e) => {
        const {value} = e.target;
        setConfirmPasswd(value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await registerUser(form);
            navigate("/");
        } catch (err) {
            alert(err.response.data.message);
        }
    };

    const handleCheck = async (e) => {
        if (!value) return;
        setChecking(true);
        try {
            const res = await checkUserId(value);
            setIsAvailable(res.data.available); // 서버에서 { available: true/false } 반환
        } catch (err) {
            console.error(err);
            setIsAvailable(false);
        } finally {
            setChecking(false);
        }
    };

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "800px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">회원가입</h2>

                    <LabeledInput
                        label="아이디"
                        name="userId"
                        value={form.userId}
                        onChange={formHandleChange}
                        placeholder="아이디 입력"
                        pattern="^[a-zA-Z0-9]{4,12}$"
                        // 영문 + 숫자, 4~12자
                    />
                    <LabeledInput
                        label="비밀번호"
                        type="password"
                        name="passwd"
                        value={form.passwd}
                        onChange={formHandleChange}
                        placeholder="비밀번호 입력"
                        pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,30}$"
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
                        // confirm는 폼 검증 외에도 handleSubmit에서 직접 체크 필요
                    />
                    <LabeledInput
                        label="이름"
                        name="userName"
                        value={form.userName}
                        onChange={formHandleChange}
                        placeholder="이름 입력"
                        pattern="^[가-힣a-zA-Z]{2,20}$"
                        // 한글/영문, 2~20자
                    />
                    <LabeledInput
                        label="휴대폰번호"
                        name="hpNo"
                        value={form.hpNo}
                        onChange={formHandleChange}
                        placeholder="휴대폰번호 입력"
                        pattern="^01[016789]-?\d{3,4}-?\d{4}$"
                        // 한국 휴대폰번호
                    />
                    <LabeledInput
                        label="이메일"
                        type="email"
                        name="email"
                        value={form.email}
                        onChange={formHandleChange}
                        placeholder="이메일 입력"
                        pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
                        // 일반 이메일 패턴
                    />

                    <Button type="submit" variant="success" className="w-100 mt-3">회원가입</Button>
                </Form>
            </Card>
        </Container>
    );
}

export default RegisterPage;
