import React from "react";
import {OverlayTrigger, Tooltip} from "react-bootstrap";

export default function TruncatedOverlayTrigger({tooltipId, overlayText, divText, maxWidth}) {
    // 공통 스타일
    const truncateStyle = {
        whiteSpace: "nowrap",
        overflow: "hidden",
        textOverflow: "ellipsis",
        verticalAlign: "middle",
    };

    return (
        <div style={{...truncateStyle, maxWidth: maxWidth}}>
            <OverlayTrigger
                placement="top"
                overlay={<Tooltip id={`tooltip-addr-${tooltipId}`}>{overlayText}</Tooltip>}>
                <div style={{...truncateStyle, maxWidth: "100%"}}>{divText}</div>
            </OverlayTrigger>
        </div>
    )
}