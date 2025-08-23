import React from "react";
import {Button, Modal} from "react-bootstrap";

export default function AlertModal({show, hideModalFunc, title, body}) {
    return (
        <Modal show={show} onHide={hideModalFunc} centered>
            <Modal.Header closeButton>
                <Modal.Title>{title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>{body}</Modal.Body>
            <Modal.Footer>
                <Button variant="success" onClick={hideModalFunc}>
                    확인
                </Button>
            </Modal.Footer>
        </Modal>
    )
}