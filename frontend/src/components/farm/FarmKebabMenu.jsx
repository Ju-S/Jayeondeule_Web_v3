import React, {useState} from "react";
import {Dropdown, DropdownDivider} from "react-bootstrap";
import {ThreeDotsVertical} from "react-bootstrap-icons";
import AlertModal from "../common/AlertModal.jsx";
import {useNavigate} from "react-router-dom";
import {deleteFarm} from "../../utils/farmUtil.js";

export default function FarmKebabMenu({farmId}) {
    const navigate = useNavigate();
    const [show, setShow] = useState(false);

    const handleDelete = () => {
        deleteFarm(farmId).then(() => navigate("/farm-management"));
    }

    const handlePatch = () => {
        navigate(`/farm/${farmId}/edit`);
    }

    return (
        <>
            <Dropdown align="end">
                <Dropdown.Toggle
                    variant="light"
                    id="dropdown-kebab"
                    bsPrefix="p-0 border-0 bg-transparent"
                >
                    <ThreeDotsVertical size={20}/>
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item onClick={handlePatch}>수정</Dropdown.Item>
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
    )
}