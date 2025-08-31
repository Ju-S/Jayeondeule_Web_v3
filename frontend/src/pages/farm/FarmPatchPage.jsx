import React, {useEffect, useRef, useState} from "react";
import {Button, Card, Container, Form} from "react-bootstrap";
import LabeledInput from "../../components/form/LabeledInput.jsx";
import LabeledAddress from "../../components/form/LabeledAddress.jsx";
import LabeledSelect from "../../components/form/LabeledSelect.jsx";
import LabeledPhoneInput from "../../components/form/LabeledPhoneInput.jsx";
import {useNavigate, useParams} from "react-router-dom";
import {getFarm, patchFarm} from "../../utils/farmUtil.js";
import AlertModal from "../../components/common/AlertModal.jsx";

export default function FarmPatchPage() {
    const navigate = useNavigate();
    const {farmId} = useParams();

    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        const script = document.createElement("script");
        script.src = "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
        script.async = true;
        document.body.appendChild(script);
    }, []);

    useEffect(() => {
        const fetchFarm = async () => {
            try {
                const res = await getFarm({farmId});
                setFarm(res.data);
            } catch (err) {
                console.error(err);
            }
        };

        fetchFarm();
    }, [farmId]);

    const [farm, setFarm] = useState({
        farmId: "",
        farmName: "",
        farmDomi: "",
        openDate: "",
        closeDate: "",
        telNo: "",
        hpNo: "",
        faxNo: "",
        mail: "",
        ipAddr: "",
        port: "",
        addr: "",
        mainPrdt: "",
        rmks: "",
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFarm({...farm, [name]: value});
    };

    const handleAddressSearch = (e) => {
        new window.daum.Postcode({
            oncomplete: function (data) {
                setFarm((state) => ({
                    ...state,
                    addr: data.address,
                }));
            },
        }).open();
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await patchFarm(farm).then(() => setShowModal(true));
    };

    return (
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
            <Card className="p-4 m-5" style={{width: "800px"}}>
                <Form className="p-4" onSubmit={handleSubmit}>
                    <h2 className="mb-4">농장수정</h2>

                    <LabeledInput
                        label="농장 이름"
                        name="farmName"
                        value={farm.farmName}
                        onChange={handleChange}
                        placeholder="농장 이름 입력"
                        pattern="^[가-힣a-zA-Z0-9 ]{2,20}$"
                        errorMsg="한글, 영문, 숫자로 이루어진 2~20자로 입력해주세요."
                        required
                    />
                    <LabeledInput
                        label="농장 도메인"
                        name="farmDomi"
                        value={farm.farmDomi}
                        onChange={handleChange}
                        placeholder="농장 도메인 입력"
                        pattern="^[a-zA-Z0-9.]*$"
                        errorMsg="영문, 숫자로 입력해주세요."
                        required
                    />
                    <LabeledInput
                        label="개업일자"
                        type="date"
                        name="openDate"
                        value={farm.openDate}
                        onChange={handleChange}
                        required
                    />
                    <LabeledInput
                        label="폐업일자"
                        type="date"
                        name="closeDate"
                        value={farm.closeDate}
                        onChange={handleChange}
                    />
                    <LabeledPhoneInput
                        label="대표번호"
                        name="telNo"
                        value={farm.telNo}
                        onChange={handleChange}
                        required
                    />
                    <LabeledPhoneInput
                        label="전화번호"
                        name="hpNo"
                        value={farm.hpNo}
                        placeholder="063-1234-5678"
                        onChange={handleChange}
                    />
                    <LabeledPhoneInput
                        label="팩스번호"
                        name="faxNo"
                        value={farm.faxNo}
                        placeholder="063-1234-5678"
                        onChange={handleChange}
                    />
                    <LabeledInput
                        label="대표메일"
                        type="email"
                        name="mail"
                        value={farm.mail}
                        onChange={handleChange}
                        placeholder="대표메일 입력"
                    />
                    <LabeledInput
                        label="농장 IP"
                        name="ipAddr"
                        value={farm.ipAddr}
                        onChange={handleChange}
                        placeholder="농장 IP 입력"
                        required
                    />
                    <LabeledInput
                        label="농장 포트"
                        name="port"
                        value={farm.port}
                        onChange={handleChange}
                        placeholder="농장 포트 입력"
                        pattern="^[0-9]{2,5}$"
                        errorMsg="숫자 2~5자로 입력해주세요."
                        required
                    />
                    <LabeledAddress
                        label="주소"
                        name="addr"
                        value={farm.addr}
                        onChange={handleChange}
                        onSearch={handleAddressSearch}
                        required
                    />
                    <LabeledSelect
                        label="주요 작물"
                        name="mainPrdt"
                        placeholder="주요 작물 선택"
                        option={[
                            { value: "10", label: "상황버섯"},
                        ]}
                        value={farm.mainPrdt}
                        onChange={handleChange}
                        required
                    />
                    <LabeledInput
                        label="농장 설명"
                        name="rmks"
                        value={farm.rmks}
                        onChange={handleChange}
                        placeholder="농장 설명 입력"
                    />

                    <Button type="submit" variant="success" className="w-100 mt-3">농장수정</Button>
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
    );
}