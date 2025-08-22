import React from "react";
import {Form, Col, Row} from "react-bootstrap";

export default function LabeledPhoneInput({label, name, smLabel = 2, value, placeholder="010-1234-5678", required = false, onChange}) {
    const handleChange = (e) => {
        let val = e.target.value.replace(/\D/g, ""); // 숫자만
        if (val.length > 3 && val.length <= 7) {
            val = val.replace(/(\d{3})(\d+)/, "$1-$2");
        } else if (val.length > 7) {
            val = val.replace(/(\d{3})(\d{4})(\d+)/, "$1-$2-$3");
        }
        onChange({target: {name, value: val}});
    };

    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>
                {label} {required && <span style={{color: "red"}}>*</span>}
            </Form.Label>
            <Col sm={12 - smLabel}>
                <Form.Control
                    name={name}
                    value={value}
                    onChange={handleChange}
                    placeholder={placeholder}
                    required={required}
                    maxLength={13}
                />
            </Col>
        </Form.Group>
    );
}