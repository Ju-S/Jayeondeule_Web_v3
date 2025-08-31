import React from "react";
import {Button, Modal} from "react-bootstrap";

export default function AlertModal({
                                       show,
                                       hideModalFunc,
                                       onClickFunc = hideModalFunc,
                                       title,
                                       body,
                                       variant = "success",
                                       buttonMsg = "확인"
                                   }) {
    return (
        <Modal show={show} onHide={hideModalFunc} centered>
            <Modal.Header closeButton>
                <Modal.Title>{title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>{body}</Modal.Body>
            <Modal.Footer>
                <Button variant={variant} onClick={onClickFunc}>
                    {buttonMsg}
                </Button>
            </Modal.Footer>
        </Modal>
    )
}