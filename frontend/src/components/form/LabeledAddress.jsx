import {Form, Row, Col, Button, InputGroup} from "react-bootstrap";

export default function LabeledAddress({label, name, value, onChange, onSearch, required = false, smLabel = 2}) {
    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>
                {label} {required && <span style={{color: "red"}}>*</span>}
            </Form.Label>
            <Col sm={12 - smLabel}>
                <InputGroup>
                    <Form.Control
                        type="text"
                        name={name}
                        value={value}
                        readOnly
                        placeholder="주소 검색 버튼 클릭"
                        onChange={onChange}
                        disabled={true}
                        required={required}
                    />
                    <Button variant="secondary" onClick={onSearch}>주소 검색</Button>
                </InputGroup>
            </Col>
        </Form.Group>
    );
}
