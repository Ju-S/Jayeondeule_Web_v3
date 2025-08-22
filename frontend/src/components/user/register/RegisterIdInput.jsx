import {Button, Form, InputGroup} from "react-bootstrap";

export default function RegisterIdInput({value, onChange, onClick, required = false, isAvailable}) {
    return (
        <>
            {/* 아이디 입력 + 중복체크 버튼 */}
            <Form.Group className="mb-3">
                <Form.Label column={true}>
                    아이디 {required && <span style={{color: "red"}}>*</span>}
                </Form.Label>
                <InputGroup>
                    <Form.Control
                        name="userId"
                        value={value}
                        onChange={onChange}
                        placeholder="아이디 입력"
                        pattern="^[a-zA-Z0-9]{4,12}$"
                        required={required}
                    />
                    <Button variant="secondary" onClick={onClick}>중복확인</Button>
                </InputGroup>
                {isAvailable !== null && (
                    <Form.Text className={!isAvailable ? "text-success" : "text-danger"}>
                        {!isAvailable ? "사용 가능한 아이디입니다." : "이미 사용 중인 아이디입니다."}
                    </Form.Text>
                )}
            </Form.Group>
        </>
    );
}