const BASE_URL = process.env.REACT_APP_API_BASE_URL;

const apiRoutes = {
    auth: {
        /**
         * 로그인 API<br>
         * POST /api/auth<br>
         * body: { userId, passwd }<br>
         * response: 사용자 정보 + 토큰
         */
        login: {url: `${BASE_URL}/auth`, method: "POST"},

        /**
         * 사용자 정보 API<br>
         * GET /api/auth<br>
         * param: {token}
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
        checkUserId: (userId) => ({url: `${BASE_URL}/user/${userId}`, method: "GET"}),

        /**
         * 회원가입 API<br>
         * POST /api/user<br>
         * body: {UserInsertDTO}
         */
        register: {url: `${BASE_URL}/user`, method: "POST"},

        /**
         * 특정 사용자 관리 API<br>
         * GET/PATCH/DELETE /api/users<br>
         * param: {userId}
         */
        management: (userId) => `${BASE_URL}/user/${userId}`
    },
    farm: {
        /**
         * 소유 농장 목록 조회 API<br>
         * GET /api/farm<br>
         * param: {userId}<br>
         * response: {List<FarmDTO>}
         */
        list: (userId) => ({url: `${BASE_URL}/farm/${userId}`, method: "GET"}),

        /**
         * 농장 등록 API<br>
         * POST /api/farm<br>
         * body: {FarmInsertDTO}
         */
        register: {url: `${BASE_URL}/farm`, method: "POST"},

        /**
         * 특정 농장 관리 API<br>
         * GET/PATCH/DELETE /api/farm<br>
         * param: {farmId}
         */
        management: (farmId) => `${BASE_URL}/farm/${farmId}`
    },
    house: {
        /**
         * 농장 재배사 목록 조회 API<br>
         * GET /api/house<br>
         * param: {farmId}<br>
         * response: {List<FarmHouseDTO>}
         */
        list: (farmId) => ({url: `${BASE_URL}/house/${farmId}`, method: "GET"}),

        /**
         * 재배사 등록 API<br>
         * POST /api/farm<br>
         * body: {FarmHouseInsertDTO}
         */
        register: {url: `${BASE_URL}/house`, method: "POST"},

        /**
         * 특정 재배사 관리 API<br>
         * GET/PATCH/DELETE /api/house<br>
         * param: {houseId}
         */
        management: (houseId) => `${BASE_URL}/house/${houseId}`
    },
    memo: {
        /**
         * 재배사 메모 목록 조회 API<br>
         * GET /api/memo<br>
         * param: {houseId}<br>
         * response: {List<MemoDTO>}
         */
        list: (houseId) => ({url: `${BASE_URL}/memo/${houseId}`, method: "GET"}),

        /**
         * 메모 등록 API<br>
         * POST /api/memo<br>
         * body: {MemoInsertDTO}
         */
        insert: {url: `${BASE_URL}/memo`, method: "POST"},

        /**
         * 특정 메모 관리 API<br>
         * GET/PATCH/DELETE /api/memo<br>
         * param: {memoId}
         */
        management: (memoId) => `${BASE_URL}/memo/${memoId}`
    },
    relay: {
        /**
         * 재배사 릴레이 상태 조회 API<br>
         * GET /api/relay<br>
         * param: {houseId}<br>
         * response: {RelayDTO}
         */
        list: (houseId) => ({url: `${BASE_URL}/relay/${houseId}`, method: "GET"}),

        /**
         * 릴레이 상태 기록 API<br>
         * POST /api/relay<br>
         * param: {houseId}<br>
         * body: {RelayDTO}
         */
        insert: (houseId) => ({url: `${BASE_URL}/relay/${houseId}`, method: "POST"}),
    },
    sensor: {
        /**
         * 재배사 센서 상태 조회 API<br>
         * GET /api/sensor<br>
         * param: {houseId}<br>
         * response: {SensorDataDTO}
         */
        list: (houseId) => ({url: `${BASE_URL}/sensor/${houseId}`, method: "GET"}),
    },
    setting: {
        /**
         * 재배사 설정 상태 조회 API<br>
         * GET /api/setting<br>
         * param: {houseId}<br>
         * response: {SensorSettingDTO, List<LightIrrigationSettingDTO>}
         */
        list: (houseId) => ({url: `${BASE_URL}/setting/${houseId}`, method: "GET"}),

        /**
         * 설정 등록 API<br>
         * POST /api/setting<br>
         * param: {houseId}<br>
         * body: {SensorSettingDTO, List<LightIrrigationSettingDTO>}
         */
        insert: (houseId) => ({url: `${BASE_URL}/setting/${houseId}`, method: "POST"}),

        /**
         * 관수, 조명 설정 관리 API<br>
         * GET/PATCH /api/setting<br>
         * param: {lightIrrigationSettingId}
         */
        management: (lightIrrigationSettingId) => `${BASE_URL}/setting/${lightIrrigationSettingId}`
    },
};

export default apiRoutes;
