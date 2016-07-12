import redis
import config

pool = redis.ConnectionPool(host=config.REDIS_HOST, port=config.REDIS_PORT, db=0)
redis_client = redis.StrictRedis(connection_pool=pool)