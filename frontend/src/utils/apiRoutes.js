const BASE_URL = import.meta.env.VITE_API_BASE_URL;

const apiRoutes = {
    auth: {
        /**
         * 로그인 API<br>
         * POST /api/auth<br>
         * body: { userId, passwd }<br>
         * response: {UserClaimDTO} + 토큰(JWT)
         */
        login: {url: `${BASE_URL}/auth`, method: "POST"},

        /**
         * 사용자 토큰 정보 조회 API<br>
         * GET /api/auth
         * response: {UserClaimDTO}
         */
        getUser: {url: `${BASE_URL}/auth`, method: "GET"},
    },
    users: {
        /**
         * 아이디 중복 확인 API<br>
         * GET /api/users<br>
         * param: {userId}<br>
         * response: true(중복)/false(중복아님)
         */
        checkUserId: (userId) => ({url: `${BASE_URL}/users/id-dupl-check?userId=${userId}`, method: "GET"}),

        /**
         * 회원가입 API<br>
         * POST /api/user<br>
         * body: {UserInsertDTO}
         */
        register: {url: `${BASE_URL}/users`, method: "POST"},

        /**
         * 회원탈퇴 API<br>
         * PATCH /api/users<br>
         * body: {UserPatchDTO}
         */
        delete: {url: `${BASE_URL}/users`, method: "DELETE"},

        /**
         * 현재 사용자 정보 조회 API<br>
         * GET /api/user/me<br>
         * response: {UserDTO}
         */
        me: {url: `${BASE_URL}/users/me`, method: "GET"},

        /**
         * 사용자 정보 목록 조회 API(관리자용)<br>
         * GET /api/user<br>
         * response: {List<UserDTO>}
         */
        getList: (page, size, searchQuery=-1) => ({
            url: `${BASE_URL}/users?page=${page}&size=${size}&searchQuery=${searchQuery}`,
            method: "GET"
        }),

        /**
         * 사용자 정보 수정 API<br>
         * PATCH /api/users<br>
         * body: {UserPatchDTO}
         */
        patch: {url: `${BASE_URL}/users`, method: "PATCH"},

        /**
         * 사용자 농장 접근권한 부여 API<br>
         * PATCH /api/users/{userId}<br>
         * body: {UserFarmIdPatchDTO}
         */
        patchFarmId: (userId) => ({
            url: `${BASE_URL}/users/${userId}`,
            method: "PATCH"
        }),

        /**
         * 비밀번호 변경 API<br>
         * PATCH /api/users/password<br>
         * body: {UserPasswordPatchDTO}<br>
         * response: {errorMessage: message}
         */
        passwordChange: {url: `${BASE_URL}/users/password`, method: "PATCH"},
    },
    farms: {
        /**
         * 소유 농장 목록 조회 API<br>
         * GET /api/farms<br>
         * response:<br>
         * 일반 사용자 - {FarmDTO}<br>
         * 관리자 - {List<FarmDTO>}
         */
        getFarmList: (page = 0, size = 20) => ({
            url: `${BASE_URL}/farms?page=${page}&size=${size}`,
            method: "GET"
        }),

        /**
         * 특정 농장 목록 조회 API<br>
         * GET /api/farms/{farmId}<br>
         * response: {FarmDTO}<br>
         */
        getFarm: (farmId) => ({
            url: `${BASE_URL}/farms/${farmId}`,
            method: "GET"
        }),

        /**
         * 소유 농장 목록 조회 API<br>
         * GET /api/farms/me<br>
         * response: {FarmDTO}<br>
         */
        getMyFarm: {
            url: `${BASE_URL}/farms/me}`,
            method: "GET"
        },

        /**
         * 농장 등록 API<br>
         * POST /api/farms<br>
         * body: {FarmInsertDTO}
         */
        register: {url: `${BASE_URL}/farms`, method: "POST"},

        /**
         * 특정 농장 삭제 API<br>
         * DELETE /api/farms/{farmId}<br>
         * pathVariable: {farmId}
         */
        delete: (farmId) => ({
            url: `${BASE_URL}/farms/${farmId}`,
            method: "DELETE"
        }),

        /**
         * 특정 농장 수정 API<br>
         * PATCH /api/farms/{farmId}<br>
         * pathVariable: {farmId}
         */
        edit: (farmId) => ({
            url: `${BASE_URL}/farms/${farmId}`,
            method: "PATCH"
        }),
    },
    houses: {
        /**
         * 농장 재배사 목록 조회 API<br>
         * GET /api/farms/${farmId}/houses<br>
         * pathVariable: {farmId}<br>
         * response: {List<FarmHouseDTO>}
         */
        list: (farmId) => ({url: `${BASE_URL}/farms/${farmId}/houses`, method: "GET"}),

        /**
         * 재배사 등록 API<br>
         * POST /api/farms/${farmId}/houses<br>
         * pathVariable: {farmId}<br>
         * body: {FarmHouseInsertDTO}
         */
        register: (farmId) => ({url: `${BASE_URL}/farms/${farmId}/houses`, method: "POST"}),

        /**
         * 특정 재배사 수정 API<br>
         * PATCH /api/farms/${farmId}/house/${houseId}<br>
         * pathVariable: {farmId, houseId}
         */
        patch: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}`,
            method: "PATCH"
        }),

        /**
         * 특정 재배사 조회 API<br>
         * GET /api/farms/${farmId}/house/${houseId}<br>
         * pathVariable: {farmId, houseId}
         */
        get: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}`,
            method: "GET"
        }),

        /**
         * 특정 재배사 삭제 API<br>
         * DELETE /api/farms/${farmId}/house/${houseId}<br>
         * pathVariable: {farmId, houseId}
         */
        delete: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}`,
            method: "DELETE"
        }),
    },
    memos: {
        // farmhouse_l_crops가 memo의 역할을 함.
        /**
         * 재배사 메모 목록 조회 API<br>
         * GET /api/farms/${farmId}/houses/${houseId}/memos<br>
         * pathVariable: {farmId, houseId}<br>
         * response: {List<MemoDTO>}
         */
        list: (farmId, houseId, page, size) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/memos?page=${page}&size=${size}`,
            method: "GET"
        }),

        /**
         * 메모 등록 API<br>
         * POST /api/farms/${farmId}/houses/${houseId}/memos<br>
         * body: {MemoInsertDTO}
         */
        insert: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/memos`,
            method: "POST"
        }),

        /**
         * 특정 메모 수정 API<br>
         * PATCH /api/farms/${farmId}/houses/${houseId}/memos/${recdDttm}<br>
         * pathVariable: {farmId, houseId, recdDttm}
         */
        patch: (farmId, houseId, recdDttm) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/memos/${recdDttm}`,
            method: "PATCH"
        }),

        /**
         * 특정 메모 삭제 API<br>
         * DELETE /api/farms/${farmId}/houses/${houseId}/memos/${recdDttm}<br>
         * pathVariable: {farmId, houseId, recdDttm}
         */
        delete: (farmId, houseId, recdDttm) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/memos/${recdDttm}`,
            method: "DELETE"
        }),
    },
    relays: {
        /**
         * 재배사 릴레이 상태 조회 API<br>
         * GET /api/farms/${farmId}/houses/${houseId}/relays<br>
         * pathVariable: {farmId, houseId}<br>
         * response: {RelayDTO}
         */
        list: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/relays`,
            method: "GET"
        }),

        /**
         * 릴레이 상태 기록 API<br>
         * POST /api/farms/${farmId}/houses/${houseId}/relays<br>
         * pathVariable: {farmId, houseId}<br>
         * body: {RelayInsertDTO}
         */
        insert: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/relays`,
            method: "POST"
        }),
    },
    sensors: {
        /**
         * 재배사 센서 상태 조회 API<br>
         * GET /api/farms/${farmId}/houses/${houseId}/sensors?limits=${limits}<br>
         * pathVariable: {farmId, houseId}<br>
         * param: {limits}<br>
         * response: {SensorDataDTO}
         */
        list: (farmId, houseId, start, end) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/sensors?start=${start}&end=${end}`,
            method: "GET"
        }),
    },
    settings: {
        /**
         * 재배사 설정 상태 조회 API<br>
         * GET /api/farms/${farmId}/houses/${houseId}/settings<br>
         * pathVariable: {farmId, houseId}<br>
         * response: {SensorSettingDTO, List<LightIrrigationSettingDTO>}
         */
        list: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/settings`,
            method: "GET"
        }),

        /**
         * 설정 등록 API<br>
         * POST /api/setting<br>
         * pathVariable: {farmId, houseId}<br>
         * body: {SensorSettingDTO, List<LightIrrigationSettingDTO>}
         */
        insert: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/settings`,
            method: "POST"
        }),

        /**
         * 관수, 조명 설정 관리 API<br>
         * PATCH /api/setting<br>
         * pathVariable: {farmId, houseId, setDttm}<br>
         * body: {LightIrrigationSettingPatchDTO}
         */
        update: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/settings/${setDttm}`,
            method: "PATCH"
        }),
    },
};

export default apiRoutes;
