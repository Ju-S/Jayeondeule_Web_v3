package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.sensor.SensorRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRecordingRepository extends JpaRepository<SensorRecording, Long> {
    SensorRecording findTopByFarmIdAndHousIdOrderByRecdDttmDesc(long farmId, long housId);

    @Query(value = """
            WITH data AS (
            SELECT *
            FROM sensor_l_recording
            WHERE farm_id = :farmId
              AND hous_id = :housId
              AND recd_dttm BETWEEN :start AND :end
        ),
        bucketed AS (
            SELECT
                width_bucket(
                    EXTRACT(EPOCH FROM recd_dttm),
                    EXTRACT(EPOCH FROM CAST(:start AS timestamp))::double precision,
                    EXTRACT(EPOCH FROM CAST(:end AS timestamp))::double precision,
                    LEAST(:maxCount, COUNT(*) OVER())::int
                ) AS bucket,
                recd_dttm,
                indr_tprt_valu,
                indr_hmdt_valu,
                oudr_tprt_valu,
                oudr_hmdt_valu,
                co2_valu,
                watr_tprt_valu
            FROM data
        )
        SELECT
            (to_timestamp(AVG(EXTRACT(EPOCH FROM recd_dttm))::double precision) AT TIME ZONE 'UTC') AS avg_time,
            ROUND(AVG(indr_tprt_valu)::numeric, 2) AS avgIndrTprt,
            ROUND(AVG(indr_hmdt_valu)::numeric, 2) AS avgIndrHmdt,
            ROUND(AVG(oudr_tprt_valu)::numeric, 2) AS avgOudrTprt,
            ROUND(AVG(oudr_hmdt_valu)::numeric, 2) AS avgOudrHmdt,
            ROUND(AVG(co2_valu)::numeric, 2) AS avgCo2,
            ROUND(AVG(watr_tprt_valu)::numeric, 2) AS avgWatrTprt
        FROM bucketed
        GROUP BY bucket
        ORDER BY bucket;
        """, nativeQuery = true)
    List<Object[]> findAveragedDownsampled(
            @Param("farmId") long farmId,
            @Param("housId") long housId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("maxCount") long maxCount
    );

}
