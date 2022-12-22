SELECT 
COUNT(CASE WHEN tm.`status`= 0 THEN 1 END)  AS openticketcount,
COUNT(CASE WHEN tm.`status`= 1 THEN 1 END)  AS progressticketcount,
COUNT(CASE WHEN tm.`status`= 2 THEN 1 END)  AS closedtcketcount,
COUNT(*) AS totalticketscount
FROM ticketmetadata tm
WHERE tm.orgid = 2 AND tm.depid = 4
GROUP BY tm.orgid,tm.depid;
CALL getPieChartCounts(2, 4);