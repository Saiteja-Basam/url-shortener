local key = KEYS[1]
local limit = tonumber(ARGV[1])
local window = tonumber(ARGV[2])
local currentTime = tonumber(ARGV[3])

-- remove old requests
redis.call("ZREMRANGEBYSCORE", key, 0, currentTime - window)

-- count requests
local count = redis.call("ZCARD", key)

if count >= limit then
    return 0
end

-- add new request
redis.call("ZADD", key, currentTime, currentTime)

-- set expiry
redis.call("EXPIRE", key, window)

return 1