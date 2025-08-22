import {Form, Row, Col} from "react-bootstrap";

export default function LabeledSelect({
                                          label,
                                          name,
                                          smLabel = 2,
                                          option = [],
                                          placeholder = "",
                                          required = false,
                                          ...props
                                      }) {
    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>
                {label} {required && <span style={{color: "red"}}>*</span>}
            </Form.Label>
            <Col sm={12 - smLabel}>
                <Form.Select name={name} required={required} {...props}>
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