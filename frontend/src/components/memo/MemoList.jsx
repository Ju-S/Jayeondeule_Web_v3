import React from "react";
import {Button, Form, ListGroup, OverlayTrigger, Spinner, Tooltip} from "react-bootstrap";
import {PencilFill, TrashFill} from "react-bootstrap-icons";
import {useSelector} from "react-redux";

export default function MemoList({
                                     memos,
                                     lastMemoRef,
                                     editText,
                                     setEditText,
                                     editingId,
                                     setEditingId,
                                     handleDeleteMemo,
                                     handleEditMemo,
                                     isLoading,
                                     hasMore
                                 }) {
    const auth = useSelector((state) => state.auth);
    return (
        <div style={{maxHeight: "500px", overflowY: "auto"}}>
            <ListGroup>
                {memos && memos.map((memo, index) => {
                    const isLast = memos.length === index + 1;
                    return (
                        <ListGroup.Item
                            key={memo.recdDttm}
                            ref={isLast ? lastMemoRef : null}
                            className="d-flex flex-column"
                        >
                            <div className="d-flex justify-content-between align-items-center mb-1">
                                <span className="fw-bold">{memo.athrName}</span>
                                <span className="text-muted"
                                      style={{fontSize: "0.8rem"}}>{new Date(memo.recdDttm).toLocaleString()}</span>
                            </div>

                            {editingId === memo.recdDttm ? (
                                <div className="d-flex align-items-center">
                                    <Form.Control
                                        type="text"
                                        value={editText}
                                        onChange={(e) => setEditText(e.target.value)}
                                        className="me-2"
                                    />
                                    <Button
                                        size="sm"
                                        variant="success"
                                        className="me-2"
                                        onClick={() => handleEditMemo({recdDttm: memo.recdDttm, memo: editText})}
                                    >
                                        저장
                                    </Button>
                                    <Button
                                        size="sm"
                                        variant="secondary"
                                        onClick={() => {
                                            setEditingId(null);
                                            setEditText("");
                                        }}
                                    >
                                        취소
                                    </Button>
                                </div>
                            ) : (
                                memo.athr === auth.userInfo.userId || auth.userInfo.authLvel === "ADMIN" ? (
                                    <div className="d-flex justify-content-between align-items-center">
                                        <span>{memo.rmks}</span>
                                        <div>
                                            <OverlayTrigger
                                                placement="top"
                                                overlay={<Tooltip>수정</Tooltip>}
                                            >
                                                <Button
                                                    size="sm"
                                                    variant="outline-success"
                                                    className="me-2"
                                                    onClick={() => {
                                                        setEditingId(memo.recdDttm);
                                                        setEditText(memo.rmks);
                                                    }}
                                                >
                                                    <PencilFill/>
                                                </Button>
                                            </OverlayTrigger>
                                            <OverlayTrigger
                                                placement="top"
                                                overlay={<Tooltip>삭제</Tooltip>}
                                            >
                                                <Button
                                                    size="sm"
                                                    variant="outline-danger"
                                                    onClick={() => handleDeleteMemo(memo.recdDttm)}
                                                >
                                                    <TrashFill/>
                                                </Button>
                                            </OverlayTrigger>
                                        </div>
                                    </div>
                                ) : (
                                    <div className="d-flex justify-content-between align-items-center">
                                        <span>{memo.rmks}</span>
                                    </div>
                                ))}
                        </ListGroup.Item>
                    );
                })}
            </ListGroup>
            {isLoading && (
                <div className="text-center my-2">
                    <Spinner animation="border"/>
                </div>
            )}
            {!hasMore && !isLoading && (
                <div className="text-center text-muted my-2">
                    마지막 데이터입니다.
                </div>
            )}
        </div>
    )
}