import {Form, Row, Col, Button} from "react-bootstrap";

export default function LabeledAddress({label, name, value, onChange, onSearch, smLabel = 2}) {
    return (
        <Form.Group as={Row} className="mb-3" controlId={`form${name}`}>
            <Form.Label column sm={smLabel}>{label}</Form.Label>
            <Col sm={8}>
                <Form.Control
                    type="text"
                    name={name}
                    value={value}
                    readOnly
                    placeholder="주소 검색 버튼 클릭"
                    onChange={onChange}
                    disabled={true}
                />
            </Col>
            <Col sm={12 - smLabel - 8}>
                <Button variant="secondary" onClick={onSearch}>주소 검색</Button>
            </Col>
        </Form.Group>
    );
}
