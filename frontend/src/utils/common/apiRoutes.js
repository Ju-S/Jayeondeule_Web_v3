const BASE_URL = import.meta.env.VITE_API_BASE_URL;

const apiRoutes = {
    auth: {
        /**
         * 로그인 API<br>
         * POST /api/auth<br>
         * body: { userId, passwd }<br>
         * response: {UserInfoDTO} + 토큰(JWT)
         */
        login: {url: `${BASE_URL}/auth`, method: "POST"},

        /**
         * 사용자 정보 API<br>
         * GET /api/auth
         * response: {UserInfoDTO}
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
         * 특정 사용자 관리 API<br>
         * GET/PATCH/DELETE /api/users<br>
         * pathVariable: {userId}
         */
        management: (userId) => `${BASE_URL}/users/${userId}`
    },
    farms: {
        /**
         * 소유 농장 목록 조회 API<br>
         * GET /api/farms<br>
         * pathVariable: {userId}<br>
         * response: {List<FarmDTO>}
         */
        list: (userId) => ({url: `${BASE_URL}/farms/${userId}`, method: "GET"}),

        /**
         * 농장 등록 API<br>
         * POST /api/farms<br>
         * body: {FarmInsertDTO}
         */
        register: {url: `${BASE_URL}/farms`, method: "POST"},

        /**
         * 특정 농장 관리 API<br>
         * GET/PATCH/DELETE /api/farms<br>
         * pathVariable: {farmId}
         */
        management: (farmId) => `${BASE_URL}/farms/${farmId}`
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
         * 특정 재배사 관리 API<br>
         * GET/PATCH/DELETE /api/farms/${farmId}/house/${houseId}<br>
         * pathVariable: {farmId, houseId}
         */
        management: (farmId, houseId) => `${BASE_URL}/farms/${farmId}/houses/${houseId}`
    },
    memos: {
        // farmhouse_l_crops가 memo의 역할을 함.
        /**
         * 재배사 메모 목록 조회 API<br>
         * GET /api/farms/${farmId}/houses/${houseId}/memos<br>
         * pathVariable: {farmId, houseId}<br>
         * response: {List<MemoDTO>}
         */
        list: (farmId, houseId) => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/memos`,
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
         * 특정 메모 관리 API<br>
         * GET/PATCH/DELETE /api/farms/${farmId}/houses/${houseId}/memos/${recdDttm}<br>
         * pathVariable: {farmId, houseId, recdDttm}
         */
        management: (farmId, houseId, recdDttm) =>
            `${BASE_URL}/farms/${farmId}/houses/${houseId}/memos/${recdDttm}`
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
        list: (farmId, houseId, limits="") => ({
            url: `${BASE_URL}/farms/${farmId}/houses/${houseId}/sensors?limits=${limits}`,
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
