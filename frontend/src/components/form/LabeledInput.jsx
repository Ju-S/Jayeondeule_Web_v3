import {Form, Row, Col} from "react-bootstrap";
import {useEffect, useState} from "react";

export default function LabeledInput({label, name, smLabel = 2, errorMsg, value, pattern, required = false, ...props}) {
    const [error, setError] = useState("");

    useEffect(() => {
        if (!pattern) {
            setError("");
            return;
        }

        const regex = new RegExp(pattern);
        if (value && !regex.test(value)) {
            setError(`${errorMsg}`);
        } else {
            setError("");
        }
    }, [value]);

    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>
                {label} {required && <span style={{color: "red"}}>*</span>}
            </Form.Label>

            <Col sm={12 - smLabel}>
                <Form.Control
                    name={name}
                    value={value}
                    required={required}
                    pattern={pattern}
                    {...props}
                />
                {error && <Form.Text className="text-danger">{error}</Form.Text>}
            </Col>
        </Form.Group>
    );
}