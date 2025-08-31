import React, {useCallback, useEffect, useRef, useState} from "react";
import {Button, Modal, Form, ListGroup, Spinner, Dropdown, InputGroup, Tooltip, OverlayTrigger} from "react-bootstrap";
import {DashCircle} from "react-bootstrap-icons";
import {patchUserFarmId, searchUserList} from "../../utils/userUtil.js";

export default function UserAddModal({
                                         show,
                                         hideModalFunc,
                                         farmId
                                     }) {
    const [searchText, setSearchText] = useState("");
    const [results, setResults] = useState([]);
    const [searchLoading, setSearchLoading] = useState(false);
    const [existLoading, setExistLoading] = useState(false);
    const [existingUsers, setExistingUsers] = useState([]);
    const [searchField, setSearchField] = useState("userId"); // 기본값: 사용자ID
    const [searchHasMore, setSearchHasMore] = useState(true);
    const [existHasMore, setExistHasMore] = useState(true);
    const [searchPage, setSearchPage] = useState(0);
    const [existPage, setExistPage] = useState(0);
    const searchObserver = useRef();
    const existObserver = useRef();

    const fetchUsers = async (page) => {
        if (searchLoading) return;
        setSearchLoading(true);
        try {
            const res = await searchUserList(page, 20);
            const newUsers = res.data.content || [];

            // 중복 제거
            setResults(prev => {
                return [...prev, ...newUsers];
            });
            console.log(newUsers);

            if (newUsers.length === 0) setSearchHasMore(false);
        } catch (err) {
            console.error(err);
        }
        setSearchLoading(false);
    };

    const fetchExistUsers = async (page) => {
        if (existLoading) return;
        setExistLoading(true);
        try {
            const res = await searchUserList(page, 20, farmId);
            const newUsers = res.data.content || [];

            // 권한이 있는 유저만
            setExistingUsers(prev => {
                return [...prev, ...newUsers];
            });

            if (newUsers.length === 0) setExistHasMore(false);
        } catch (err) {
            console.error(err);
        }
        setSearchLoading(false);
    };

    useEffect(() => {
        fetchUsers(searchPage);
    }, [searchPage]);

    useEffect(() => {
        fetchExistUsers(existPage);
    }, [existPage])

    const lastUserRef = useCallback(
        (node) => {
            if (searchLoading) return;
            if (searchObserver.current) searchObserver.current.disconnect();
            searchObserver.current = new IntersectionObserver(entries => {
                if (entries[0].isIntersecting && searchHasMore) {
                    setSearchPage(prev => prev + 1);
                }
            });
            if (node) searchObserver.current.observe(node);
        },
        [searchLoading, searchHasMore]
    );

    const lastUserExistRef = useCallback(
        (node) => {
            if (existLoading) return;
            if (existObserver.current) existObserver.current.disconnect();
            existObserver.current = new IntersectionObserver(entries => {
                if (entries[0].isIntersecting && existHasMore) {
                    setExistPage(prev => prev + 1);
                }
            });
            if (node) existObserver.current.observe(node);
        },
        [existLoading, existHasMore]
    );

    const filteredUsers = results.filter(item =>
        item[searchField]?.toLowerCase().includes(searchText.toLowerCase()) &&
        item.farmId != farmId
    );

    async function addUser(user) {
        await patchUserFarmId(user.userId, {farmId: farmId});
        setResults((prev) => prev.filter((item) => item.userId !== user.userId));
        setExistingUsers([...existingUsers, {userName: user.userName, userId: user.userId, farmId: farmId}]);
    }

    async function onRemoveUser(user) {
        await patchUserFarmId(user.userId, {farmId: 0});
        setExistingUsers((prev) => prev.filter((item) => item.userId !== user.userId));
        setResults([...results, {userName: user.userName, userId: user.userId, farmId: 0}]);
    }

    return (
        <Modal show={show} onHide={hideModalFunc} centered size="lg">
            <Modal.Header closeButton>
                <Modal.Title>사용자 등록</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {/* 검색 입력 */}
                <InputGroup>
                    <Dropdown onSelect={(key) => setSearchField(key)}>
                        <Dropdown.Toggle variant="secondary" id="dropdown-search">
                            {searchField === "userId" ? "ID" : "이름"}
                        </Dropdown.Toggle>
                        <Dropdown.Menu>
                            <Dropdown.Item eventKey="userId">ID</Dropdown.Item>
                            <Dropdown.Item eventKey="userName">이름</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                    <Form.Control
                        type="text"
                        placeholder={`${searchField === "userId" ? "사용자ID" : "사용자이름"} 검색...`}
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                    />
                </InputGroup>

                {/* 검색 결과 */}
                {searchLoading && <Spinner animation="border" className="my-2"/>}
                {filteredUsers.map((user, index) => (
                    <ListGroup ref={filteredUsers.length === index + 1 ? lastUserRef : null} className="mt-2"
                               style={{maxHeight: "200px", overflowY: "auto"}}>
                        <ListGroup.Item
                            key={user.userId}
                            style={{cursor: "pointer"}}
                            onClick={() => addUser(user)}
                        >
                            <OverlayTrigger
                                placement="top-start"
                                overlay={<Tooltip>현재 등록된 농장ID: {user.farmId}</Tooltip>}>
                                <div style={{maxWidth: "100%"}}>{user.userName} ({user.userId})</div>
                            </OverlayTrigger>
                        </ListGroup.Item>
                    </ListGroup>
                ))}
                {filteredUsers.length === 0 && !searchLoading && (
                    <ListGroup>
                        <ListGroup.Item className="text-muted mt-2">검색 결과가 없습니다.</ListGroup.Item>
                    </ListGroup>
                )}

                {/* 이미 등록된 사용자 */}
                <hr/>
                <h6>등록된 사용자</h6>
                {existingUsers.map((user, index) => (
                    <ListGroup ref={existingUsers.length === index + 1 ? lastUserExistRef : null}
                               style={{maxHeight: "200px", overflowY: "auto"}}>
                        <ListGroup.Item key={user.userId}
                                        className="d-flex justify-content-between align-items-center mb-2">
                            {user.userName} ({user.userId})
                            <Button
                                size="sm"
                                variant="outline-danger"
                                onClick={() => onRemoveUser(user)}
                            >
                                <DashCircle size={14}/>
                            </Button>
                        </ListGroup.Item>
                    </ListGroup>
                ))}
                {existingUsers.length === 0 && (
                    <ListGroup>
                        <ListGroup.Item className="text-muted">등록된 사용자가 없습니다.</ListGroup.Item>
                    </ListGroup>
                )}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={hideModalFunc}>
                    닫기
                </Button>
            </Modal.Footer>
        </Modal>
    );
}
