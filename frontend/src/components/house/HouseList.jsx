import React, {useState} from "react";
import {Button, Dropdown, InputGroup} from "react-bootstrap";
import {PencilSquare} from "react-bootstrap-icons";
import {useNavigate} from "react-router-dom";
import AlertModal from "../common/AlertModal.jsx";
import "./HouseList.css";
import {deleteHouse} from "../../utils/houseUtil.js";

export default function HouseList({houses, selectedHouse, setSelectedHouse, authLvel, farmId}) {
    const navigate = useNavigate();
    const [show, setShow] = useState(false);

    const handleDelete = async () => {
        await deleteHouse(farmId, selectedHouse).then(() => location.reload());
    }

    return (
        <div className="d-inline-flex align-items-start mb-3">
            <InputGroup className="mb-3">
                {/* 드롭다운 */}
                <Dropdown onSelect={(key) => setSelectedHouse(key)}>
                    <Dropdown.Toggle variant="outline-success">
                        {houses.find((h) => h.housId == selectedHouse)?.housName || "하우스 선택"}
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                        {houses.map((house) => (
                            <Dropdown.Item key={house.housId} eventKey={house.housId}>
                                {house.housName}
                            </Dropdown.Item>
                        ))}
                    </Dropdown.Menu>
                </Dropdown>

                {/* 재배사 수정 버튼 */}
                {authLvel === "ADMIN" && (
                    <>
                        <Dropdown align="end">
                            <Dropdown.Toggle variant="outline-success" className="no-caret">
                                <PencilSquare size={16}/>
                            </Dropdown.Toggle>

                            <Dropdown.Menu>
                                <Dropdown.Item
                                    onClick={() => navigate(`/farm/${farmId}/house/${selectedHouse}/edit`)}>수정</Dropdown.Item>
                                <Dropdown.Item onClick={() => setShow(true)}>삭제</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                        <AlertModal
                            show={show}
                            hideModalFunc={() => setShow(false)}
                            onClickFunc={() => handleDelete()}
                            title="정말 삭제하시겠습니까?"
                            body="삭제 후 복구가 불가능할 수 있습니다."
                            variant="danger"
                            buttonMsg="삭제"
                        />
                    </>
                )}
            </InputGroup>
            {authLvel === "ADMIN" &&
                <Button
                    variant="success"
                    onClick={() => navigate(`/farm/${farmId}/house-register`)}
                    style={{marginLeft: '0.5rem', whiteSpace: "nowrap"}}
                >
                    재배사 추가
                </Button>
            }
        </div>
    )
}