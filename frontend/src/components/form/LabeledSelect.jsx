import {Form, Row, Col} from "react-bootstrap";

export default function LabeledSelect({
                                          label,
                                          name,
                                          smLabel = 2,
                                          option = [],
                                          placeholder = "",
                                          ...props
                                      }) {
    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>{label}</Form.Label>
            <Col sm={12 - smLabel}>
                <Form.Select name={name} {...props}>
                    <option>{placeholder}</option>
                    {option.map((opt, idx) => (
                        <option key={idx} value={opt.value ?? opt}>
                            {opt.label ?? opt}
                        </option>
                    ))}
                </Form.Select>
            </Col>
        </Form.Group>
    );
}