import {Form, Row, Col} from "react-bootstrap";

export default function LabeledInput({label, name, smLabel = 2, ...props}) {
    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>{label}</Form.Label>
            <Col sm={12 - smLabel}>
                <Form.Control
                    name={name}
                    {...props}
                />
            </Col>
        </Form.Group>
    );
}