import React, {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {Button, Card, Container, Form} from "react-bootstrap";
import LabeledInput from "../../components/form/LabeledInput.jsx";
import {registerHouse} from "../../utils/houseUtil.js";

export default function HouseRegisterPage() {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        farmId: useParams().farmId,
        housName: "",
        cropKind: "",
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(form);
        await registerHouse(form).then(() => navigate(`/farm/${form.farmId}/monitor`));
    };

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "800px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">재배사등록</h2>

                    <LabeledInput
                        label="재배사 이름"
                        name="housName"
                        value={form.housName}
                        onChange={handleChange}
                        placeholder="재배사 이름 입력"
                        pattern="^[가-힣a-zA-Z0-9 ]{2,20}$"
                        errorMsg="한글, 영문, 숫자로 이루어진 2~20자로 입력해주세요."
                        required
                    />
                    <LabeledInput
                        label="작물 종류"
                        name="cropKind"
                        value={form.cropKind}
                        onChange={handleChange}
                        placeholder="작물 종류 입력"
                        required
                    />

                    <Button type="submit" variant="success" className="w-100 mt-3">재배사등록</Button>
                </Form>
            </Card>
        </Container>
    )
}