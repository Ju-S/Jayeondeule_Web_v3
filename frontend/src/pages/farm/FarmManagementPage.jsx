import {useEffect, useState, useRef, useCallback} from "react";
import {
    Table,
    Form,
    Container,
    Row,
    Col,
    Spinner,
    InputGroup,
    Dropdown,
} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import TruncatedOverlayTrigger from "../../components/farm/TruncatedOverlayTrigger.jsx";
import {getFarmList} from "../../utils/farmUtil.js";

export default function FarmManagementPage() {
    const navigate = useNavigate();
    const [farms, setFarms] = useState([]);
    const [search, setSearch] = useState("");
    const [searchField, setSearchField] = useState("farmName"); // 기본값: 농장이름
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    const [loading, setLoading] = useState(false);
    const observer = useRef();

    const fetchFarms = async (page) => {
        if (loading) return;
        setLoading(true);
        try {
            const res = await getFarmList(page, 20);
            const newFarms = res.data.content || [];

            // 중복 제거
            setFarms(prev => {
                const existingIds = new Set(prev.map(f => f.farmId));
                const filteredNew = newFarms.filter(f => !existingIds.has(f.farmId));
                return [...prev, ...filteredNew];
            });

            if (newFarms.length === 0) setHasMore(false);
        } catch (err) {
            console.error(err);
        }
        setLoading(false);
    };

    useEffect(() => {
        fetchFarms(page);
    }, [page]);

    const lastFarmRef = useCallback(
        (node) => {
            if (loading) return;
            if (observer.current) observer.current.disconnect();
            observer.current = new IntersectionObserver(entries => {
                if (entries[0].isIntersecting && hasMore) {
                    setPage(prev => prev + 1);
                }
            });
            if (node) observer.current.observe(node);
        },
        [loading, hasMore]
    );

    const filteredFarms = farms.filter(farm =>
        farm[searchField]?.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <Container className="mt-2 pt-3">
            <Row className="mb-3">
                <Col xs={12} md={6}>
                    <InputGroup>
                        <Dropdown onSelect={(key) => setSearchField(key)}>
                            <Dropdown.Toggle variant="secondary" id="dropdown-search">
                                {searchField === "farmName" ? "농장이름" : "주소"}
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                                <Dropdown.Item eventKey="farmName">농장이름</Dropdown.Item>
                                <Dropdown.Item eventKey="addr">주소</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                        <Form.Control
                            type="text"
                            placeholder={`${searchField === "farmName" ? "농장이름" : "주소"} 검색...`}
                            value={search}
                            onChange={(e) => setSearch(e.target.value)}
                        />
                    </InputGroup>
                </Col>
            </Row>

            <Table striped bordered hover responsive>
                <thead>
                <tr>
                    <th style={{width: "5%"}}>ID</th>
                    <th style={{width: "25%"}}>농장 이름</th>
                    <th style={{width: "50%"}}>주소</th>
                    <th className="d-none d-md-table-cell" style={{width: "20%"}}>등록일</th>
                </tr>
                </thead>
                <tbody>
                {filteredFarms.map((farm, index) => (
                    <tr key={farm.farmId} ref={filteredFarms.length === index + 1 ? lastFarmRef : null}>
                        <td className="text-success"
                            style={{verticalAlign: "middle", cursor: "pointer"}}
                            onClick={() => navigate(`/farm/${farm.farmId}/monitor`)}>
                            {farm.farmId}
                        </td>

                        <td>
                            <TruncatedOverlayTrigger
                                tooltipId={farm.farmId}
                                overlayText={farm.farmName}
                                divText={farm.farmName}
                                maxWidth="20vw"
                            />
                        </td>

                        <td>
                            <TruncatedOverlayTrigger
                                tooltipId={farm.farmId}
                                overlayText={farm.addr}
                                divText={farm.addr}
                                maxWidth="50vw"
                            />
                        </td>

                        <td className="d-none d-md-table-cell" style={{verticalAlign: "middle"}}>
                            {new Date(farm.rgstDttm).toLocaleDateString()}
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>

            {loading && (
                <div className="text-center my-3">
                    <Spinner animation="border"/>
                </div>
            )}

            {!hasMore && !loading && (
                <div className="text-center text-muted mb-3">마지막 데이터입니다.</div>
            )}
        </Container>
    );
}
