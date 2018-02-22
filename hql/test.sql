insert overwrite  table default.kwu_tracklog PARTITION (day='${yesterday}',hour='${hour}')
select datetime,ip,cookieid,userid, logserverip,referer,
requesturl ,remark1,remark2,alexaflag,ua,wirelessflag from ods.tracklog where  day='${today}'  and length(datetime)=12 limit 25000