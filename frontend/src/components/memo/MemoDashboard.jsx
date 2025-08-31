import React, {useState, useRef, useCallback, useEffect} from "react";
import {
    Card,
    Form,
    Button,
    Row,
    Col,
    InputGroup,
} from "react-bootstrap";
import {
    useQuery,
    useMutation, useQueryClient,
} from "@tanstack/react-query";
import {createMemo, deleteMemo, getMemos, updateMemo} from "../../utils/memoUtil.js";
import AlertModal from "../common/AlertModal.jsx";
import MemoList from "./MemoList.jsx";

export default function MemoDashboard({farmId, houseId}) {
    const queryClient = useQueryClient();
    const [newMemo, setNewMemo] = useState("");
    const [editingId, setEditingId] = useState(null);
    const [editText, setEditText] = useState("");
    const [page, setPage] = useState(0);
    const [memos, setMemos] = useState([]);
    const [hasMore, setHasMore] = useState(true);
    const [show, setShow] = useState(false);
    const observer = useRef();
    const pageSize = 10;

    // 메모 조회
    const {data, isLoading, refetch} = useQuery({
        queryKey: ["memos", farmId, houseId, page],
        queryFn: async () => {
            const res = await getMemos(farmId, houseId, page, pageSize);
            return res.data.content || [];
        },
        enabled: !!farmId && !!houseId,
        keepPreviousData: true,
    });

    // 새 데이터 들어올 때 합치기
    useEffect(() => {
        if (data) {
            if (page === 0) {
                setMemos(data);
            } else {
                setMemos((prev) => [...prev, ...data]);
            }
            if (data.length < pageSize) setHasMore(false);
        } else if (page === 0) {
            setMemos([]);
        }
    }, [data]);

    useEffect(() => {
        setPage(0);
        setHasMore(true);
        queryClient.invalidateQueries(["memos", farmId, houseId]);
    }, [houseId])

    // 무한 스크롤
    const lastMemoRef = useCallback(
        (node) => {
            if (isLoading) return;
            if (observer.current) observer.current.disconnect();
            observer.current = new IntersectionObserver((entries) => {
                if (entries[0].isIntersecting && hasMore) {
                    setPage((prev) => prev + 1);
                }
            });
            if (node) observer.current.observe(node);
        },
        [isLoading, hasMore]
    );

    // 메모 추가
    const addMemoMutation = useMutation({
        mutationFn: async () =>
            await createMemo(farmId, houseId, {memo: newMemo}),
        onSuccess: () => {
            setMemos([]);
            setPage(0);
            setHasMore(true);
            queryClient.invalidateQueries(["memos", farmId, houseId]);
            setNewMemo("");
        },
    });

    // 메모 수정
    const updateMemoMutation = useMutation({
        mutationFn: async (content) =>
            await updateMemo(farmId, houseId, content),
        onSuccess: () => {
            setEditingId(null);
            setEditText("");
            setMemos([]);
            setPage(0);
            setHasMore(true);
            queryClient.invalidateQueries(["memos", farmId, houseId]);
            setNewMemo("");
        },
    });

    // 메모 삭제
    const deleteMemoMutation = useMutation({
        mutationFn: async (recdDttm) =>
            await deleteMemo(farmId, houseId, recdDttm),
        onSuccess: (_, memoId) => {
            console.log(memoId);
            setMemos((prev) => prev.filter((m) => m.recdDttm !== memoId));
        },
    });

    const handleAddMemo = (e) => {
        e.preventDefault();
        if (!newMemo.trim()) return;
        addMemoMutation.mutate();
    };

    const handleEditMemo = (content) => {
        if (!editText.trim()) return;
        updateMemoMutation.mutate(content);
    };

    const handleDeleteMemo = (recdDttm) => {
        setEditingId(recdDttm);
        setShow(true);
    };

    function handleDelete() {
        deleteMemoMutation.mutate(editingId);
        setShow(false);
        setEditingId(null);
    }

    return (
        <Row>
            <Col md={12}>
                {/* 새 메모 작성 */}
                <Card className="mt-3 mb-3 p-3">
                    <Form onSubmit={handleAddMemo}>
                        <InputGroup>
                            <Form.Control
                                type="text"
                                placeholder="새 메모 입력..."
                                value={newMemo}
                                onChange={(e) => setNewMemo(e.target.value)}
                            />
                            <Button type="submit" variant="success">
                                추가
                            </Button>
                        </InputGroup>
                    </Form>
                </Card>

                {/* 메모 리스트 */}
                <MemoList
                    memos={memos}
                    lastMemoRef={lastMemoRef}
                    editText={editText}
                    setEditText={setEditText}
                    editingId={editingId}
                    setEditingId={setEditingId}
                    handleDeleteMemo={handleDeleteMemo}
                    handleEditMemo={handleEditMemo}
                    isLoading={isLoading}
                    hasMore={hasMore}
                />
            </Col>
            <AlertModal
                show={show}
                hideModalFunc={() => setShow(false)}
                onClickFunc={() => handleDelete()}
                title="정말 삭제하시겠습니까?"
                body="삭제 후 복구가 불가능할 수 있습니다."
                variant="danger"
                buttonMsg="삭제"
            />
        </Row>
    );
}