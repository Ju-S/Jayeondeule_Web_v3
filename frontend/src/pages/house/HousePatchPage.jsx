import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {Button, Card, Container, Form} from "react-bootstrap";
import LabeledInput from "../../components/form/LabeledInput.jsx";
import {getHouse, patchHouse, registerHouse} from "../../utils/houseUtil.js";
import AlertModal from "../../components/common/AlertModal.jsx";
import {getFarm} from "../../utils/farmUtil.js";

export default function HousePatchPage() {
    const navigate = useNavigate();

    const [showModal, setShowModal] = useState(false);

    const [form, setForm] = useState({
        farmId: useParams().farmId,
        houseId: useParams().houseId,
        housName: "",
        cropKind: "",
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await patchHouse(form).then(() => setShowModal(true));
    };

    useEffect(() => {
        const fetchFarmHouse = async () => {
            try {
                const res = await getHouse(form.farmId, form.houseId);
                setForm({...form, ...res.data});
            } catch (err) {
                console.error(err);
            }
        };

        fetchFarmHouse();
    }, [form.farmId, form.houseId]);

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "800px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">재배사수정</h2>

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

                    <Button type="submit" variant="success" className="w-100 mt-3">재배사수정</Button>
                </Form>
            </Card>

            {/* 수정 알림 Modal */}
            <AlertModal
                show={showModal}
                hideModalFunc={() => setShowModal(false)}
                title="알림"
                body="수정이 완료되었습니다."
            />
        </Container>
    )
}